package com.vinibank.backend.sdui.flow.home

import com.vinibank.backend.sdui.flow.BaseFlowController
import com.vinibank.backend.sdui.flow.SdUiScreen
import com.vinibank.backend.sdui.flow.home.screen.HomeScreen
import org.springframework.stereotype.Component

interface HomeScreen : SdUiScreen

@Component()
class HomeController(
    screens: List<com.vinibank.backend.sdui.flow.home.HomeScreen>,
    defaultScreen: HomeScreen,
) : BaseFlowController(screens, defaultScreen, "Home")

