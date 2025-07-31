package com.vinibank.backend.sdui.flow

import com.vinibank.backend.CryptographicFilter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping
class RootController(
    private val routingController: RoutingController,
    private val cryptoFilter: CryptographicFilter,
) {

    @PostMapping("/sdui_screens")
    fun getSdUiScreen(
        @RequestHeader(value = "iv") iv: String,
        @RequestHeader(value = "sessionId") sessionId: String,
        @RequestBody request: String,
    ): ResponseEntity<String> {
        return cryptoFilter.handleRequest(request, iv, sessionId) { output ->
            Json.encodeToString(routingController.getSdUiScreen(output))
        }
    }
}