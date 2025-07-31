package com.vinibank.backend.sdui.flow.home.content

import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.oldflow.ScreenUtil
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.JsonObject
import org.springframework.stereotype.Component

@Component
class InvestmentsContent : HomeScreen {
    override val screenId: String
        get() = "Investimentos"

    override fun getScreen(request: SdUiRequest): JsonObject? = ScreenUtil.screen(
        flow = "Home",
        stage = "Investimentos",
        version = "1",
        template = "",
        shouldCache = false,
        components = listOf(
            ScreenUtil.component(
                type = "lazyColumn",
                properties = listOf(
                    ScreenUtil.property(name = "weight", value = "1"),
                    ScreenUtil.property(name = "horizontalFillType", value = "Max"),
                    ScreenUtil.property(name = "verticalArrangement", value = "Center"),
                    ScreenUtil.property(name = "horizontalAlignment", value = "Center"),
                ),
                components = listOf(
                    ScreenUtil.component(
                        type = "text",
                        properties = listOf(
                            ScreenUtil.property(name = "text", value = "Conteudo de investimentos")
                        )
                    )
                )
            )
        )
    )
}