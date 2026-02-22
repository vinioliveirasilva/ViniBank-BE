package com.vinibank.backend.db

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Service
import java.io.File
import java.io.InputStream
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@Serializable
enum class TransactionCategory {
    MARKET,
    PHARMACY,
    SALARY,
    PIX,
    STREAMING,
    TRANSPORT,
    FOOD,
    UTILITIES,
    OTHER
}

@Serializable
data class CheckingAccountTransaction(
    val id: String,
    val establishmentName: String,
    val transactionDate: String, // yyyy-MM-dd
    val transactionTime: String, // HH:mm
    val amount: Double,
    val category: TransactionCategory
)

@Service
class CheckingAccountDatabase {

    private companion object {
        const val RESOURCE_NAME = "/checkingAccountDb.csv"
        const val RESOURCE_LOCATION = "src/main/resources$RESOURCE_NAME"
        val DATE_FORMAT: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE
        val TIME_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    }

    // email -> transactions
    private val cache: MutableMap<String, MutableList<CheckingAccountTransaction>> = mutableMapOf()
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
                    val transactions = json.decodeFromString<List<CheckingAccountTransaction>>(model)
                    cache[email] = sortByMostRecent(transactions).toMutableList()
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
                appendLine("email -> transactions")
                cache.forEach { (email, transactions) ->
                    appendLine("$email -> ${json.encodeToString(sortByMostRecent(transactions))}")
                }
            }
        )
    }

    private fun toDateTime(transaction: CheckingAccountTransaction): LocalDateTime {
        return try {
            val date = LocalDate.parse(transaction.transactionDate, DATE_FORMAT)
            val time = LocalTime.parse(transaction.transactionTime, TIME_FORMAT)
            LocalDateTime.of(date, time)
        } catch (_: Exception) {
            LocalDateTime.MIN
        }
    }

    private fun sortByMostRecent(
        transactions: List<CheckingAccountTransaction>
    ): List<CheckingAccountTransaction> {
        return transactions.sortedByDescending { toDateTime(it) }
    }

    fun get(email: String?): List<CheckingAccountTransaction> {
        val transactions = cache[email] ?: return emptyList()
        return sortByMostRecent(transactions)
    }

    fun add(
        email: String,
        establishmentName: String,
        transactionDate: String,
        transactionTime: String,
        amount: Double,
        category: TransactionCategory
    ): CheckingAccountTransaction {
        val transaction = CheckingAccountTransaction(
            id = UUID.randomUUID().toString(),
            establishmentName = establishmentName,
            transactionDate = transactionDate,
            transactionTime = transactionTime,
            amount = amount,
            category = category
        )
        val current = cache.getOrPut(email) { mutableListOf() }
        current.add(transaction)
        cache[email] = sortByMostRecent(current).toMutableList()
        persistAll()
        return transaction
    }

    fun upsert(email: String, transaction: CheckingAccountTransaction) {
        val current = cache.getOrPut(email) { mutableListOf() }
        val index = current.indexOfFirst { it.id == transaction.id }
        if (index >= 0) {
            current[index] = transaction
        } else {
            current.add(transaction)
        }
        cache[email] = sortByMostRecent(current).toMutableList()
        persistAll()
    }

    fun remove(email: String, transactionId: String): Boolean {
        val current = cache[email] ?: return false
        val removed = current.removeIf { it.id == transactionId }
        if (removed) {
            cache[email] = sortByMostRecent(current).toMutableList()
            persistAll()
        }
        return removed
    }
}
