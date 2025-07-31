package com.vinibank.backend.sdui.oldflow

import kotlinx.serialization.json.JsonObject

interface SdUiScreenOLD {
    fun getScreenModel(screenData: JsonObject?): JsonObject
}