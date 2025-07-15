package com.vinibank.backend.sdui.flow.newcard

import com.vinibank.backend.sdui.flow.newcard.screens.IntroScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.JsonObject
import org.springframework.http.ResponseEntity

object NewCardController {
    const val IDENTIFIER = "NewCard"

    fun getSdUiScreen(
        request: SdUiRequest
    ) = getScreen(request)


    private fun getScreen(request: SdUiRequest): Pair<JsonObject, ResponseEntity<String>?> {
        if (request.fromScreen.isBlank())
            return noRule(request)
        return getRule(request)
    }

    private fun getRule(request: SdUiRequest) = when (request.fromScreen) {
        else -> noRule(request)
    }

    private fun noRule(request: SdUiRequest): Pair<JsonObject, ResponseEntity<String>?> {
        return Pair(getInternalScreen(request), null)
    }

    private fun getInternalScreen(request: SdUiRequest) = when (request.toScreen) {
        //"Intro" -> IntroScreen.getScreenModel(request.screenData)
        else -> IntroScreen.getScreenModel(request.screenData)
    }
}