package com.vinibank.backend.db

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Service
import java.io.File
import java.io.InputStream
import java.util.UUID

@Serializable
enum class NotificationCategory {
    ALL,
    TRANSACTIONS,
    SECURITY,
    OFFER,
    LOGIN,
    STATEMENT,
}

@Serializable
data class UserNotification(
    val id: String,
    val title: String,
    val message: String,
    val category: NotificationCategory,
    val createdAtEpochMillis: Long,
    val isRead: Boolean = false
)

@Service
class NotificationsDatabase {

    private companion object {
        const val RESOURCE_NAME = "/notificationsDb.csv"
        const val RESOURCE_LOCATION = "src/main/resources$RESOURCE_NAME"
    }

    private val cache: MutableMap<String, MutableMap<String, UserNotification>> = mutableMapOf()
    private val json = Json {}

    init {
        loadFromResource()
    }

    private fun loadFromCsv(inputStream: InputStream) {
        inputStream.bufferedReader().useLines { lines ->
            lines.drop(1).forEach { line ->
                val parts = line.split(" -> ")
                if (parts.size >= 2) {
                    val email = parts.first().trim()
                    val model = parts.last().trim()
                    cache[email] = json.decodeFromString(model)
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
                appendLine("email -> notifications")
                cache.forEach { (email, notifications) ->
                    appendLine("$email -> ${json.encodeToString(notifications)}")
                }
            }
        )
    }

    fun add(
        email: String,
        title: String,
        message: String,
        category: NotificationCategory,
        createdAtEpochMillis: Long = System.currentTimeMillis(),
        isRead: Boolean = false
    ): UserNotification {
        val notification = UserNotification(
            id = UUID.randomUUID().toString(),
            title = title,
            message = message,
            category = category,
            createdAtEpochMillis = createdAtEpochMillis,
            isRead = isRead
        )
        val current = cache.getOrPut(email) { mutableMapOf() }
        current[notification.id] = notification
        persistAll()
        return notification
    }

    fun list(email: String?, category: NotificationCategory = NotificationCategory.ALL): List<UserNotification> {
        val userNotifications = cache[email] ?: return emptyList()
        val all = userNotifications.values.sortedByDescending { it.createdAtEpochMillis }
        return when {
            category == NotificationCategory.ALL -> all
            else -> all.filter { it.category == category }
        }
    }

    fun markAsRead(email: String?, notificationId: String): Boolean {
        val current = cache[email] ?: return false
        val notification = current[notificationId] ?: return false
        if (notification.isRead) return false
        current[notificationId] = notification.copy(isRead = true)
        persistAll()
        return true
    }

    fun markAllAsRead(email: String): Int {
        val current = cache[email] ?: return 0
        val unreadCount = current.values.count { !it.isRead }
        if (unreadCount == 0) return 0
        current.entries.forEach { (id, notification) ->
            if (!notification.isRead) {
                current[id] = notification.copy(isRead = true)
            }
        }
        persistAll()
        return unreadCount
    }

    fun hasUnreadNotification(email: String?): Boolean {
        val current = cache[email] ?: return false
        return current.values.any { !it.isRead }

    }
}
