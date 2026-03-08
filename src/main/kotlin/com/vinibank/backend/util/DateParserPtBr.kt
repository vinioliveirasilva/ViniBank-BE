package com.vinibank.backend.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateParserPtBr {
    private val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val outputFormatter = DateTimeFormatter.ofPattern("dd 'de' MMM", Locale("pt", "BR"))

    fun parse(date: String): String {
        val localDate = LocalDate.parse(date, inputFormatter)
        return outputFormatter.format(localDate).split(" ")
            .joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }
    }
}