package com.vinibank.backend.sdui

import org.json.JSONObject

interface SdUiScreen {
    fun getScreenModel(screenData: String): JSONObject
}