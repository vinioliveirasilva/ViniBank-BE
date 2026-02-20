package com.vinibank.backend.sdui.flow.home.screen

import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.component.SdUi
import com.vini.designsystemsdui.property.FlowIdentifierProperty
import com.vini.designsystemsdui.property.FromScreenIdentifierProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.StageIdentifierProperty
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.sdui.flow.RoutingController
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class CreditCardScreen(
    @Lazy private val routingController: RoutingController
) : HomeScreen {
    override val screenId: String = "Cartoes"

    override fun getScreen(request: SdUiRequest): Template? = DefaultTemplate(
        flow = request.flow,
        stage = screenId,
        version = "1",
        content = listOf(
            SdUi(
                flowIdentifierProperty = FlowIdentifierProperty("Card"),
                stageIdentifierProperty = StageIdentifierProperty("Start"),
                fromScreenIdentifierProperty = FromScreenIdentifierProperty(screenId),
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                template = routingController.getTemplate(
                    SdUiRequest(
                        flow = "Card",
                        fromScreen = screenId,
                        toScreen = "Start",
                        screenData = request.screenData
                    )
                )
            ),
        )
    )
}