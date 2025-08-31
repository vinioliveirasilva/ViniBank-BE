package com.vinibank.backend.sdui.flow.home.content

import com.vini.designsystemsdui.component.sdUi
import com.vini.designsystemsdui.property.FromScreenIdentifierProperty
import com.vini.designsystemsdui.property.FlowIdentifierProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.StageIdentifierProperty
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vinibank.backend.sdui.flow.RoutingController
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen
import kotlinx.serialization.json.JsonObject
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class CardsContent(
    @Lazy private val routingController: RoutingController
) : HomeScreen {
    override val screenId: String = "Cartoes"

    override fun getScreen(request: SdUiRequest): JsonObject? = screen(
        flow = "Home",
        stage = "Cartoes",
        version = "1",
        template = "",
        shouldCache = false,
        content =  listOf(
            sdUi(
                flowIdentifierProperty = FlowIdentifierProperty("Card"),
                stageIdentifierProperty = StageIdentifierProperty(""),
                fromScreenIdentifierProperty = FromScreenIdentifierProperty(screenId),
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                components = routingController.getSdUiComponents(
                    SdUiRequest(
                        flow = "Card",
                        fromScreen = "",
                        toScreen = "",
                        screenData = request.screenData
                    )
                )
            ),
        )
    )
}