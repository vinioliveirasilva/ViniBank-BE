package com.vinibank.backend.sdui.flow

import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.JsonObject
import org.springframework.stereotype.Component

@Component
interface SdUiScreen {
    val screenId: String
    fun getScreen(request: SdUiRequest) : JsonObject?
    fun getRule(request: SdUiRequest) : JsonObject? = null
}