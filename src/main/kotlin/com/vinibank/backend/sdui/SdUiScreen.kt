package com.vinibank.backend.sdui

import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.JsonObject

interface SdUiScreen {
    fun getScreenModel(screenData: JsonObject?): JsonObject
}

interface SdUiScreen2 {
    fun getScreenModel(screenData: JsonObject?, internal: (SdUiRequest) -> List<JsonObject>): JsonObject
}