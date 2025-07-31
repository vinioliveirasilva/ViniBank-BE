package com.vinibank.backend.sdui.flow.card

import com.vinibank.backend.sdui.flow.BaseFlowController
import com.vinibank.backend.sdui.flow.SdUiScreen
import com.vinibank.backend.sdui.flow.card.screen.CardIntroScreen
import org.springframework.stereotype.Component

interface CardScreen : SdUiScreen

@Component()
class CardController(
    screens: List<CardScreen>,
    defaultScreen: CardIntroScreen,
) : BaseFlowController(screens, defaultScreen, "Card")