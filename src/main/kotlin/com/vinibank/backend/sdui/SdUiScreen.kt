package com.vinibank.backend.sdui

import com.vinibank.backend.sdui.model.ScreenModel

interface SdUiScreen {
    fun getScreenModel(screenData: String): ScreenModel
}