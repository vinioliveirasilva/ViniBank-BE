package com.vinibank.backend.db

import org.springframework.stereotype.Service
import java.io.File
import java.io.InputStream
import java.util.Base64

@Service
class SessionDatabase {

    val sessions = mutableMapOf<String, ByteArray>()

    init {
        loadUsersFromResource()
    }

    private fun loadUsersFromCsv(inputStream: InputStream) {
        inputStream.bufferedReader().useLines { lines ->
            lines.drop(1).forEach { line ->
                val parts = line.split(" -> ")
                if (parts.size >= 2) {
                    val sessionId = parts.first().trim()
                    val secret = parts.last().trim()

                    sessions[sessionId] = Base64.getDecoder().decode(secret)
                }
            }
        }
    }

    private fun loadUsersFromResource() {
        val inputStream = javaClass.getResourceAsStream("/sessionDb.csv")
        inputStream?.run {  loadUsersFromCsv(this) }
    }

    private fun saveUserToCsv(sessionId: String, secret: ByteArray) {
        val file = File("src/main/resources/sessionDb.csv")
        val writeHeader = !file.exists()
        file.appendText(
            buildString {
                if (writeHeader) {
                    append("session,secret\n")
                }
                append("$sessionId -> ${Base64.getEncoder().encodeToString(secret)}\n")
            }
        )
    }

    fun addSession(sessionId: String, secret: ByteArray) {
        saveUserToCsv(sessionId, secret)
        sessions[sessionId] = secret
    }
}