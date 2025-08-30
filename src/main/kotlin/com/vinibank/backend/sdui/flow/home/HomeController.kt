package com.vinibank.backend.sdui.flow.home

import com.vinibank.backend.sdui.flow.BaseFlowController
import com.vinibank.backend.sdui.flow.SdUiScreen
import com.vinibank.backend.sdui.flow.home.screen.MainScreen
import org.springframework.stereotype.Component

interface HomeScreen : SdUiScreen

@Component()
class HomeController(
    screens: List<HomeScreen>,
    defaultScreen: MainScreen,
) : BaseFlowController<HomeScreen>(screens, defaultScreen, "Home")

