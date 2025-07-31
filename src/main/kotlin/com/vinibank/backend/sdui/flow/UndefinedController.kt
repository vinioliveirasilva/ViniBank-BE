package com.vinibank.backend.sdui.flow

import com.vinibank.backend.sdui.components.topBarWithBackAction
import com.vinibank.backend.sdui.model.SdUiRequest
import com.vinibank.backend.sdui.oldflow.ScreenUtil
import org.springframework.stereotype.Component

@Component
class UndefinedController {
    fun getSdUiScreen(
        request: SdUiRequest
    ) = ScreenUtil.screen(
        flow = request.flow,
        stage = request.toScreen,
        version = "1",
        template = "",
        shouldCache = false,
        components = listOf(
            topBarWithBackAction("TODO"),
            ScreenUtil.component(
                type = "text",
                properties = listOf(
                    ScreenUtil.property("text", "Tela não cadastrada"),
                    ScreenUtil.property("paddingVertical", "30"),
                    ScreenUtil.property("horizontalFillType", "Max"),
                    ScreenUtil.property("textAlign", "Center")
                )
            )
        )
    )
}