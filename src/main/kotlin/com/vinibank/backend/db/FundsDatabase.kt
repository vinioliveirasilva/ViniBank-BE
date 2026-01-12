package com.vinibank.backend.db

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Service
import java.io.InputStream

@Serializable
data class Fund(
    val id: String,
    val name: String,
    val category: String,
    val minimumInvestment: Double,
    val riskLevel: String,
    val rentability: String,
)

class FundInfo(
    val id: String,
    val name: String,
    val rentability: String,
    val minimumInvestment: Double,
    val riskLevel: String,
    val hourLimitToInvestment: String,
    val suggestedTimeStay: String,
    val productTax: String,
    val unavailableDaysToInvest: List<Int>
)

@Service
class FundsDatabase {

    private companion object {
        const val RESOURCE_NAME = "/fundDb.csv"
    }

    private val funds: MutableMap<String, Fund> = mutableMapOf()
    private val json = Json {  }

    init {
        loadFromResource()
    }

    private fun loadFromCsv(inputStream: InputStream) {
        inputStream.bufferedReader().useLines { lines ->
            lines.drop(1).forEach { line ->
                val parts = line.split(",", limit = 2)
                if (parts.size == 2) {
                    val fundId = parts[0]
                    val fundJson = parts[1]
                    try {
                        funds[fundId] = json.decodeFromString<Fund>(fundJson)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    private fun loadFromResource() {
        javaClass.getResourceAsStream(RESOURCE_NAME)?.run {
            loadFromCsv(this)
            close()
        }
    }

    fun getAllFunds(): Map<String, Fund> = funds
    fun getFund(id: String) = funds[id]
    fun getFundInfo(id: String): FundInfo? {
        val fund = getFund(id) ?: return null
        return FundInfo(
            id = fund.id,
            name = fund.name,
            rentability = fund.rentability,
            minimumInvestment = fund.minimumInvestment,
            riskLevel = fund.riskLevel,
            hourLimitToInvestment = "18:00h",
            unavailableDaysToInvest = listOf(),
            suggestedTimeStay = "2 Anos",
            productTax = "2.2%",
        )
    }
}
