package com.vinibank.backend.sdui.flow.signup

import com.vinibank.backend.sdui.flow.BaseFlowController
import com.vinibank.backend.sdui.flow.SdUiScreen
import com.vinibank.backend.sdui.flow.signup.screen.EmailScreen
import org.springframework.stereotype.Component

interface SignUpScreen : SdUiScreen

@Component()
class SignUpController(
    screens: List<SignUpScreen>,
    defaultScreen: EmailScreen,
) : BaseFlowController(screens, defaultScreen, "SignUp")