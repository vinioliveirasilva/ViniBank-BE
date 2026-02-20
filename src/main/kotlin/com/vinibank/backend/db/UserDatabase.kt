package com.vinibank.backend.db

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Service
import java.io.InputStream

@Serializable
data class User(
    @SerialName("name") val name: String,
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
    @SerialName("phoneNumber") val phone: String,
)

@Service
class UserDatabase {
    val users: MutableMap<String, User> = mutableMapOf()

    init {
        loadUsersFromResource()
    }

    private fun loadUsersFromCsv(inputStream: InputStream) {
        inputStream.bufferedReader().useLines { lines ->
            lines.drop(1).forEach { line ->
                val parts = line.split(" -> ")
                if (parts.size >= 2) {
                    val email = parts.first().trim()
                    val model = parts.last().trim()

                    users[email] = Json.decodeFromString(model)
                }
            }
        }
    }

    private fun loadUsersFromResource() {
        val inputStream = javaClass.getResourceAsStream("/userDb.csv")
        inputStream?.run { loadUsersFromCsv(this) }
    }

    private fun saveUserToCsv(user: User, filePath: String = "src/main/resources/userDb.csv") {
        val file = java.io.File(filePath)
        val writeHeader = !file.exists()
        file.appendText(
            buildString {
                if (writeHeader) {
                    append("email -> model\n")
                }
                append("${user.email} -> ${Json.encodeToString(user)}\n")
            }
        )
    }

    fun addUser(name: String, email: String, password: String, phone: String) {
        val user = User(name = name, email = email, password = password, phone = phone)
        saveUserToCsv(user)
        users[user.email] = user
    }
}