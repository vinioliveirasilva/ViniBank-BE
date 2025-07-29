package com.vinibank.backend.sdui.flow.home

import com.vinibank.backend.sdui.flow.carddetail.CardsController
import com.vinibank.backend.sdui.flow.home.screens.BalanceScreen
import com.vinibank.backend.sdui.flow.home.screens.CheckingAccountContent
import com.vinibank.backend.sdui.flow.home.screens.HomeScreen
import com.vinibank.backend.sdui.flow.home.screens.InvestmentsContent
import com.vinibank.backend.sdui.flow.home.screens.UserDetailScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import org.springframework.http.ResponseEntity

object HomeController {
    const val IDENTIFIER = "Home"
    private lateinit var cardsController: CardsController

    fun getSdUiScreen(
        request: SdUiRequest,
        email: String,
    ) = getScreen(request, email)


    private fun getScreen(
        request: SdUiRequest,
        email: String,
    ): Pair<JsonObject, ResponseEntity<String>?> {
        if (request.toScreen.contains("Cartoes")) {
            if (!::cardsController.isInitialized) {
                cardsController = CardsController(email)
            }
            return cardsController.getSdUiScreen(request)
        }

        if (request.fromScreen.isBlank()) {
            return Pair(getInternalScreen(request), null)
        }
        return getRule(request)
    }

    private fun getRule(request: SdUiRequest) = when (request.fromScreen) {
        else -> noRule(request)
    }

    private fun noRule(request: SdUiRequest): Pair<JsonObject, ResponseEntity<String>?> {
        return Pair(getInternalScreen(request), null)
    }

    private fun getInternalScreen(request: SdUiRequest) = when (request.toScreen) {
        "UserDetail" -> UserDetailScreen.getScreenModel(request.screenData)
        "ContaCorrente" -> CheckingAccountContent.getScreenModel(request.screenData) { request -> routeInternalScreen(request) }
        "Home" -> HomeScreen.getScreenModel(request.screenData)
        "Investimentos" -> InvestmentsContent.getScreenModel(request.screenData)
        "Balance" -> BalanceScreen.getScreenModel(request.screenData)
        else -> HomeScreen.getScreenModel(request.screenData)
    }

    private fun routeInternalScreen(request: SdUiRequest) = when (request.toScreen) {
        "Balance" -> BalanceScreen.getScreenModel(request.screenData)
        else -> JsonObject(emptyMap())
    }.getValue("components").jsonArray.map { it.jsonObject }
}