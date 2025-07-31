package com.vinibank.backend.sdui.flow.login.screen

import com.vinibank.backend.sdui.flow.login.LoginScreen
import com.vinibank.backend.sdui.oldflow.ScreenUtil
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.JsonObject
import org.springframework.stereotype.Component

@Component()
class SuccessLoginScreen() : LoginScreen {
    override val screenId: String
        get() = "Success"

    override fun getScreen(request: SdUiRequest): JsonObject? {
        return ScreenUtil.screen(
            flow = request.flow,
            stage = request.fromScreen,
            version = "1",
            template = "",
            shouldCache = false,
            components = listOf(
                ScreenUtil.component(
                    type = "blank",
                    properties = listOf(),
                    actions = listOf(
                        ScreenUtil.action(
                            type = "businessSuccess",
                            data = ScreenUtil.jsonObject(
                                "screenData" to request.screenData
                            )
                        )
                    )
                )
            )
        )
    }
}