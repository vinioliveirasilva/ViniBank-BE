package com.vinibank.backend.db

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.InputStream

data class User(
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
)

class UserDatabase {

    val gson = Gson()

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

                    users[email] = gson.fromJson(model, User::class.java)
                }
            }
        }
    }

    private fun loadUsersFromResource() {
        val inputStream = javaClass.getResourceAsStream("/userDb.csv")
        inputStream?.run {  loadUsersFromCsv(this) }
    }

    private fun saveUserToCsv(user: User, filePath: String = "src/main/resources/userDb.csv") {
        val file = java.io.File(filePath)
        val writeHeader = !file.exists()
        file.appendText(
            buildString {
                if (writeHeader) {
                    append("email,model\n")
                }
                append("${user.email} -> ${gson.toJson(user)}\n")
            }
        )
    }

    fun addUser(name: String, email: String, password: String) {
        val user = User(name = name, email = email, password = password)
        saveUserToCsv(user)
        users[user.email] = user
    }
}

val userDatabaseInstance = UserDatabase()