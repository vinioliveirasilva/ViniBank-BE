package com.vinibank.backend.sdui.flow.login

import com.vinibank.backend.sdui.flow.BaseFlowController
import com.vinibank.backend.sdui.flow.SdUiScreen
import com.vinibank.backend.sdui.flow.login.screen.MainLoginScreen
import org.springframework.stereotype.Component

interface LoginScreen : SdUiScreen

@Component()
class LoginController(
    screens: List<LoginScreen>,
    defaultScreen: MainLoginScreen,
) : BaseFlowController<LoginScreen>(screens, defaultScreen, "Login")