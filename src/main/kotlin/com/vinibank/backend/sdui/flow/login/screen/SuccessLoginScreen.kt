package com.vinibank.backend.sdui.flow.login.screen

import com.vini.designsystemsdui.action.businessSuccessAction
import com.vini.designsystemsdui.component.blank
import com.vinibank.backend.sdui.flow.login.LoginScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen
import kotlinx.serialization.json.JsonObject
import org.springframework.stereotype.Component

@Component()
class SuccessLoginScreen() : LoginScreen {
    override val screenId: String
        get() = "Success"

    override fun getScreen(request: SdUiRequest): JsonObject? {
        return screen(
            flow = request.flow,
            stage = request.fromScreen,
            version = "1",
            template = "",
            shouldCache = false,
            components = listOf(
                blank(
                    actions = listOf(
                        businessSuccessAction(
                            screenData = request.screenData
                        )
                    )
                )
            )
        )
    }
}