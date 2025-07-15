package com.vinibank.backend.sdui.flow.carddetail

import com.vinibank.backend.db.cardsDatabaseInstance
import com.vinibank.backend.sdui.flow.UndefinedController
import com.vinibank.backend.sdui.flow.carddetail.screens.CardsContent
import com.vinibank.backend.sdui.flow.carddetail.screens.cardcontentdetails.GoldCardDetails
import com.vinibank.backend.sdui.flow.carddetail.screens.cardcontentdetails.NewCardDetails
import com.vinibank.backend.sdui.flow.carddetail.screens.cardcontentdetails.PlatinumCardDetails
import com.vinibank.backend.sdui.flow.carddetail.screens.cardcontentdetails.SilverCardDetails
import com.vinibank.backend.sdui.flow.carddetail.screens.cardcontentdetails.goldcarddetails.Billing
import com.vinibank.backend.sdui.flow.carddetail.screens.cardcontentdetails.goldcarddetails.BillingItem
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.JsonObject
import org.springframework.http.ResponseEntity

class CardsController(private val email: String) {
    //const val IDENTIFIER = "Cartoes"

    fun getSdUiScreen(
        request: SdUiRequest,
    ) = getScreen(request)


    private fun getScreen(request: SdUiRequest): Pair<JsonObject, ResponseEntity<String>?> {
        if (request.fromScreen.isBlank())
            return Pair(getInternalScreen(request), null)

        return getRule(request)
    }

    private fun getRule(request: SdUiRequest) = when (request.fromScreen) {
        "Cartoes" -> noRule(request)
        else -> noRule(request)
    }

    private fun noRule(request: SdUiRequest): Pair<JsonObject, ResponseEntity<String>?> {
        return Pair(getInternalScreen(request), null)
    }

    private fun getInternalScreen(request: SdUiRequest) = when (request.toScreen) {
        "Cartoes/card1" -> PlatinumCardDetails.getScreenModel(request.screenData)
        "Cartoes/card2" -> GoldCardDetails.getScreenModel(request.screenData)
        "Cartoes/card2/billing" -> Billing.getScreenModel(request.screenData)
        "Cartoes/card2/billing/1" -> BillingItem.getScreenModel(request.screenData)
        "Cartoes/card3" -> SilverCardDetails.getScreenModel(request.screenData)
        "Cartoes" -> CardsContent(cardsDatabaseInstance.getCards(email)).getScreenModel(request.screenData)
        "Cartoes/newCard" -> NewCardDetails.getScreenModel(request.screenData)
        else -> UndefinedController.undefinedScreen.getScreenModel(request.screenData)
    }
}