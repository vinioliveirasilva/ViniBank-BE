package com.vinibank.backend.db

import java.io.File
import java.io.InputStream

class SessionDatabase {

    val sessions = mutableMapOf<String, String>()

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

                    sessions[sessionId] = secret
                }
            }
        }
    }

    private fun loadUsersFromResource() {
        val inputStream = javaClass.getResourceAsStream("/sessionDb.csv")
        inputStream?.run {  loadUsersFromCsv(this) }
    }

    private fun saveUserToCsv(sessionId: String, secret: String) {
        val file = File("src/main/resources/sessionDb.csv")
        val writeHeader = !file.exists()
        file.appendText(
            buildString {
                if (writeHeader) {
                    append("session,secret\n")
                }
                append("${sessionId} -> ${secret}\n")
            }
        )
    }

    fun addSession(sessionId: String, secret: String) {
        saveUserToCsv(sessionId, secret)
        sessions[sessionId] = secret
    }
}

val sessionDatabaseInstance = SessionDatabase()