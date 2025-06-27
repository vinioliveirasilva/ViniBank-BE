package com.vinibank.backend.sdui.flow.home

import com.google.gson.Gson
import com.vinibank.backend.sdui.flow.home.screens.CardsContent
import com.vinibank.backend.sdui.flow.home.screens.CheckingAccountContent
import com.vinibank.backend.sdui.flow.home.screens.HomeScreen
import com.vinibank.backend.sdui.flow.home.screens.InvestmentsContent
import com.vinibank.backend.sdui.model.SdUiRequest
import org.json.JSONObject
import org.springframework.http.ResponseEntity

object HomeController {
    const val IDENTIFIER = "Home"
    private val gson = Gson()

    fun getSdUiScreen(
        request: SdUiRequest
    ) = getScreen(request)


    private fun getScreen(request: SdUiRequest): Pair<JSONObject, ResponseEntity<String>?> {
        if (request.currentStage.isBlank())
            return Pair(getInternalScreen(request), null)

        return getRule(request)
    }

    private fun getRule(request: SdUiRequest) = when (request.currentStage) {
        "Home" -> noRule(request)
        "ContaCorrente" -> noRule(request)
        else -> noRule(request)
    }

    private fun noRule(request: SdUiRequest): Pair<JSONObject, ResponseEntity<String>?> {
        return Pair(getInternalScreen(request), null)
    }

    private fun getInternalScreen(request: SdUiRequest) = when (request.nextStage) {
        "Home" -> HomeScreen.getScreenModel(request.flowData)
        "ContaCorrente" -> CheckingAccountContent.getScreenModel(request.flowData)
        "Cartoes" -> CardsContent.getScreenModel(request.flowData)
        "Investimentos" -> InvestmentsContent.getScreenModel(request.flowData)
        else -> HomeScreen.getScreenModel(request.flowData)
    }


    private inline fun <reified T> createModel(model: String): T {
        return gson.fromJson<T>(model, T::class.java)
    }
}