package com.vinibank.backend.sdui.flow.login.screen

import com.vini.designsystemsdui.action.businessSuccessAction
import com.vini.designsystemsdui.component.Blank
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vini.designsystemsdui.template.Template
import com.vinibank.backend.sdui.flow.login.LoginScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component

@Component()
class SuccessLoginScreen() : LoginScreen {
    override val screenId: String
        get() = "Success"

    override fun getScreen(request: SdUiRequest): Template? {
        return DefaultTemplate(
            flow = request.flow,
            stage = request.fromScreen,
            version = "1",
            template = "",
            content =  listOf(
                Blank(
                    onAppear = businessSuccessAction(
                        screenData = request.screenData
                    )
                )
            )
        )
    }
}