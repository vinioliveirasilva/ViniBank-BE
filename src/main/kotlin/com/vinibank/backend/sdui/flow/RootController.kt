package com.vinibank.backend.sdui.flow

import com.vinibank.backend.CryptographicFilter
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
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
        return cryptoFilter.handleRequest(request, iv, sessionId) { output: SdUiRequest ->
            Thread.sleep(1000)
            Json.encodeToString(routingController.getSdUiScreen(output))
        }
    }

    @PostMapping("/sdui_screens_update")
    fun getSdUiScreenUpdate(
        @RequestHeader(value = "iv") iv: String,
        @RequestHeader(value = "sessionId") sessionId: String,
        @RequestBody request: String,
    ): ResponseEntity<String> {
        return cryptoFilter.handleUpdateRequest(request, iv, sessionId) { output: UpdateSdUiTemplateRequest ->
            Thread.sleep(200)
            Json.encodeToString(routingController.getSdUiScreenUpdate(output))
        }
    }
}

@Serializable
data class UpdateSdUiTemplateRequest(
    @SerialName("flow")
    val flow: String,
    @SerialName("fromScreen")
    val fromScreen: String,
    @SerialName("toScreen")
    val toScreen: String,
    @SerialName("screenData")
    val screenData: JsonObject,
    @SerialName("idsMustUpdate")
    val idsMustUpdate: List<String>,
    @SerialName("version")
    val version: String,
)

fun UpdateSdUiTemplateRequest.toSdUiRequest(): SdUiRequest {
    return SdUiRequest(
        flow = flow,
        fromScreen = fromScreen,
        toScreen = toScreen,
        screenData = screenData,
    )
}