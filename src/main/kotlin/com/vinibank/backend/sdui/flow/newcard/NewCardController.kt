package com.vinibank.backend.sdui.flow.newcard

import com.vinibank.backend.sdui.flow.BaseFlowController
import com.vinibank.backend.sdui.flow.SdUiScreen
import com.vinibank.backend.sdui.flow.newcard.screen.NewCardIntroScreen
import org.springframework.stereotype.Component

interface NewCardScreen : SdUiScreen

@Component()
class NewCardController(
    screens: List<NewCardScreen>,
    defaultScreen: NewCardIntroScreen,
) : BaseFlowController(screens, defaultScreen, "NewCard")