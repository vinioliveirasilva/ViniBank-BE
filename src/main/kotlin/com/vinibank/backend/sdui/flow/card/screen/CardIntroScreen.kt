package com.vinibank.backend.sdui.flow.card.screen

import com.vini.designsystemsdui.Template
import com.vinibank.backend.db.Card
import com.vinibank.backend.sdui.flow.RoutingController
import com.vinibank.backend.sdui.flow.card.CardScreen
import com.vinibank.backend.sdui.flow.card.content.AddNewCardContent
import com.vinibank.backend.sdui.flow.card.content.CardDetailContent
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class CardIntroScreen(
    private val newCardContent: AddNewCardContent = AddNewCardContent(),
    @Lazy private val cardDetailContent: CardDetailContent,
    @Lazy private val internalRoutingController: RoutingController,
) : CardScreen {

    private val cards = listOf<Card>()//TODO

    override val screenId: String = "Start"

    override fun getScreen(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? {
        return if (cards.isEmpty()) {
            newCardContent.defaultScreen()
        } else {
            cardDetailContent.defaultScreen(cards)
        }
    }
}