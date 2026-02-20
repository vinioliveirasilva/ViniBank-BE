package com.vinibank.backend.db

import org.springframework.stereotype.Service
import java.io.File
import java.io.InputStream

@Service
class UserLoginDb {

    private companion object {
        const val RESOURCE_NAME = "/userLoginDb.csv"
        const val RESOURCE_LOCATION = "src/main/resources$RESOURCE_NAME"
    }

    private val sessionToEmail: MutableMap<String, String> = mutableMapOf()

    init {
        loadFromResource()
    }

    private fun loadFromCsv(inputStream: InputStream) {
        inputStream.bufferedReader().useLines { lines ->
            lines.drop(1).forEach { line ->
                val parts = line.split(" -> ")
                if (parts.size >= 2) {
                    val sessionId = parts.first().trim()
                    val email = parts.last().trim()
                    if (sessionId.isNotEmpty() && email.isNotEmpty()) {
                        sessionToEmail[sessionId] = email
                    }
                }
            }
        }
    }

    private fun loadFromResource() {
        javaClass.getResourceAsStream(RESOURCE_NAME)?.run { loadFromCsv(this) }
    }

    private fun persistAll(filePath: String = RESOURCE_LOCATION) {
        val file = File(filePath)
        file.writeText(
            buildString {
                appendLine("sessionId -> email")
                sessionToEmail.forEach { (sessionId, email) ->
                    appendLine("$sessionId -> $email")
                }
            }
        )
    }

    fun bind(sessionId: String, userEmail: String) {
        sessionToEmail[sessionId] = userEmail
        persistAll()
    }

    fun getUserEmail(sessionId: String): String? = sessionToEmail[sessionId]

    fun remove(sessionId: String): Boolean {
        val removed = sessionToEmail.remove(sessionId) != null
        if (removed) persistAll()
        return removed
    }

    fun hasSession(sessionId: String): Boolean = sessionToEmail.containsKey(sessionId)
}
