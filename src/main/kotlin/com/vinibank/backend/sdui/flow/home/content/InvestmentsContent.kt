package com.vinibank.backend.sdui.flow.home.content

import com.vini.designsystemsdui.action.navigateAction
import com.vini.designsystemsdui.component.button
import com.vini.designsystemsdui.component.column
import com.vini.designsystemsdui.component.lazyColumn
import com.vini.designsystemsdui.component.text
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen
import kotlinx.serialization.json.JsonObject
import org.springframework.stereotype.Component

@Component
class InvestmentsContent : HomeScreen {
    override val screenId: String
        get() = "Investimentos"

    override fun getScreen(request: SdUiRequest): JsonObject? = screen(
        flow = "Home",
        stage = "Investimentos",
        version = "1",
        template = "",
        shouldCache = false,
        content =  listOf(
            lazyColumn(
                weightProperty = WeightProperty(1f),
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                verticalArrangementProperty = VerticalArrangementProperty(VerticalArrangementOption.Center),
                horizontalAlignmentProperty = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
                content =  listOf(
                    column(
                        content =  listOf(
                            text(textProperty = TextProperty("Conteudo de investimentos"))
                        )
                    ),
                    column(
                        verticalArrangementProperty = VerticalArrangementProperty(VerticalArrangementOption.Bottom),
                        content =  listOf(
                            button(
                                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                                paddingHorizontalProperty = PaddingHorizontalProperty(10),
                                content = listOf(
                                    text(textProperty = TextProperty("Ver mais"))
                                ),
                                onClick = navigateAction(flow = "Investments")
                            )
                        )
                    ),
                )
            ),
        )
    )
}