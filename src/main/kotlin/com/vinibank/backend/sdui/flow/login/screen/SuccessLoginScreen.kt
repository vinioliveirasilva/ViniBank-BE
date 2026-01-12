package com.vinibank.backend.sdui.flow.login.screen

import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.BusinessSuccessAction
import com.vini.designsystemsdui.component.Blank
import com.vini.designsystemsdui.template.DefaultTemplate
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
            stage = screenId,
            version = "1",
            content =  listOf(
                Blank(
                    onAppear = BusinessSuccessAction(
                        screenData = request.screenData
                    )
                )
            )
        )
    }
}