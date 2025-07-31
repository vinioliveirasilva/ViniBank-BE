package com.vinibank.backend

import kotlinx.serialization.Serializable
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Serializable
data class Error(
    val message: String,
    val code: Int,
)

@RestController
@SpringBootApplication
class Application(
    private val cryptoFilter: CryptographicFilter
) {
    @RequestMapping("/initialize")
    fun handShake(@RequestHeader(value = "publicKey") publicKey: String): ResponseEntity<String> {
        val (newSessionId, result) = cryptoFilter.startFilter(publicKey)

        return ResponseEntity
            .ok()
            .header("Content-Type", "text/plain")
            .header("sessionId", newSessionId)
            .body(result)
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
