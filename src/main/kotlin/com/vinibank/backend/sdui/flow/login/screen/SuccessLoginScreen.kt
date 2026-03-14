package com.vinibank.backend.sdui.flow.login.screen

import com.vini.designsystemsdui.core.SdUiNode.Template
import com.vini.designsystemsdui.ui.action.NavigateAction
import com.vini.designsystemsdui.ui.template.ScreenTemplate
import com.vinibank.backend.sdui.flow.login.LoginScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component

@Component()
class SuccessLoginScreen() : LoginScreen {
    override val screenId: String
        get() = "Success"

    override fun getScreen(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? {
        return ScreenTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            onAppearAction = NavigateAction(flow = "Home"),
            content = { }
        )
    }
}