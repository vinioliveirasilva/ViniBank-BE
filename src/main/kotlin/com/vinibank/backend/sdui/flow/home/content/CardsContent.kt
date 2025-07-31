package com.vinibank.backend.sdui.flow.home.content

import com.vinibank.backend.sdui.flow.RoutingController
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.oldflow.ScreenUtil
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.JsonObject
import org.springframework.stereotype.Component
import org.springframework.context.annotation.Lazy

@Component
class CardsContent(
    @Lazy private val routingController: RoutingController
) : HomeScreen {
    override val screenId: String = "Cartoes"

    override fun getScreen(request: SdUiRequest): JsonObject? = ScreenUtil.screen(
        flow = "Home",
        stage = "Cartoes",
        version = "1",
        template = "",
        shouldCache = false,
        components = listOf(
            ScreenUtil.component(
                "sdui",
                properties = listOf(
                    ScreenUtil.property("flow", "Card"),
                    ScreenUtil.property("stage", ""),
                    ScreenUtil.property("currentScreen", screenId),
                    ScreenUtil.property(
                        "horizontalFillType",
                        "Max"
                    ),
                ),
                components = routingController.getSdUiComponents(
                    SdUiRequest(
                        "Card",
                        screenId,
                        "",
                        request.screenData
                    )
                ),
            ),
        )
    )
}