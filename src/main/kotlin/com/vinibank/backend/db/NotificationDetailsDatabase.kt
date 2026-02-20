package com.vinibank.backend.db

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Service
import java.io.File
import java.io.InputStream

@Serializable
data class NotificationDetail(
    val notificationId: String,
    val screenTitle: String,
    val categoryLabel: NotificationCategory,
    val title: String,
    val subtitle: String,
    val highlightedText: String? = null,
    val dateTimeText: String? = null,
    val detailMessage: String? = null,
    val detailItems: Map<String, String> = emptyMap(),
    val mapImageUrl: String? = null,
    val primaryActionLabel: String? = null,
    val secondaryActionLabel: String? = null,
    val footerMessage: String? = null,
)

@Service
class NotificationDetailsDatabase {

    private companion object {
        const val RESOURCE_NAME = "/notificationDetailsDb.csv"
        const val RESOURCE_LOCATION = "src/main/resources$RESOURCE_NAME"
    }

    // email -> (notificationId -> detail model)
    private val cache: MutableMap<String, MutableMap<String, NotificationDetail>> = mutableMapOf()
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
                appendLine("email -> notificationDetails")
                cache.forEach { (email, detailsById) ->
                    appendLine("$email -> ${json.encodeToString(detailsById)}")
                }
            }
        )
    }

    fun get(email: String?, notificationId: String?): NotificationDetail? {
        return cache[email]?.get(notificationId)
    }

    fun listByEmail(email: String): Map<String, NotificationDetail> {
        return cache[email].orEmpty()
    }

    fun upsert(email: String, notificationId: String, detail: NotificationDetail) {
        val current = cache.getOrPut(email) { mutableMapOf() }
        current[notificationId] = detail.copy(notificationId = notificationId)
        persistAll()
    }

    fun remove(email: String, notificationId: String): Boolean {
        val current = cache[email] ?: return false
        val removed = current.remove(notificationId) != null
        if (removed) persistAll()
        return removed
    }
}
