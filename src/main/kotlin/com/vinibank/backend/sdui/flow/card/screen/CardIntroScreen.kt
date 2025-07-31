package com.vinibank.backend.sdui.flow.card.screen

import com.vinibank.backend.db.Card
import com.vinibank.backend.sdui.flow.RoutingController
import com.vinibank.backend.sdui.flow.card.CardScreen
import com.vinibank.backend.sdui.flow.card.content.AddNewCardContent
import com.vinibank.backend.sdui.flow.card.content.CardDetailContent
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.JsonObject
import org.springframework.stereotype.Component
import org.springframework.context.annotation.Lazy

@Component
class CardIntroScreen(
    private val newCardContent: AddNewCardContent = AddNewCardContent(),
    private val cardDetailContent: CardDetailContent = CardDetailContent(),
    @Lazy private val internalRoutingController: RoutingController,
) : CardScreen {

    private val cards = listOf<Card>()//TODO

    override val screenId: String = "Intro"

    override fun getScreen(request: SdUiRequest): JsonObject? {
        return if (cards.isEmpty()) {
            newCardContent.screen()
        } else {
            cardDetailContent.screen(cards)
        }
    }
}