package com.vinibank.backend.sdui.flow.login

import com.vinibank.backend.sdui.flow.BaseFlowController
import com.vinibank.backend.sdui.flow.SdUiScreen
import com.vinibank.backend.sdui.flow.login.screen.LoginScreen
import org.springframework.stereotype.Component

interface LoginScreen : SdUiScreen

@Component()
class LoginController(
    screens: List<com.vinibank.backend.sdui.flow.login.LoginScreen>,
    defaultScreen: LoginScreen,
) : BaseFlowController(screens, defaultScreen, "Login")