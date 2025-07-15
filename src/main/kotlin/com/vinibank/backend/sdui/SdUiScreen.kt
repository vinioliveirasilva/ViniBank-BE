package com.vinibank.backend.sdui

import kotlinx.serialization.json.JsonObject

interface SdUiScreen {
    fun getScreenModel(screenData: JsonObject?): JsonObject
}