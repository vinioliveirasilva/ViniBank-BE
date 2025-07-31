package com.vinibank.backend.db

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Service
import java.io.File
import java.io.InputStream

@Serializable
data class Card(
    val identifier: String,     //unique id
    val name: String,           // Platinum card
    val type: String,           // credit
    val number: String,         //1234 1234 1234 1234
    val validUntil: String,     //05/25
    val cvv: String,            //432
)

@Service
class CardsDatabase {

    private companion object {
        const val RESOURCE_NAME = "/cards.csv"
        const val RESOURCE_LOCATION = "src/main/resources$RESOURCE_NAME"
    }

    private val cache: MutableMap<String, List<Card>> = mutableMapOf()

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
                    cache[email] = Json.decodeFromString(model)
                }
            }
        }
    }

    private fun loadFromResource() {
        javaClass.getResourceAsStream(RESOURCE_NAME)?.run { loadFromCsv(this) }
    }

    private fun saveToCsv(email: String, cards: List<Card>, filePath: String = RESOURCE_LOCATION) {
        val file = File(filePath)
        val writeHeader = !file.exists()
        file.appendText(
            buildString {
                if (writeHeader) {
                    appendLine("email -> models")
                }
                appendLine("$email -> ${Json.encodeToString(cards)}\n")//TODO override
            }
        )
    }

    fun add(email: String, card: Card) {
        val currentCards = cache[email] ?: emptyList()
        val updatedCards = currentCards + card
        cache[email] = updatedCards
        saveToCsv(email, updatedCards)
    }

    fun getCards(email: String) = cache[email] ?: emptyList()
}