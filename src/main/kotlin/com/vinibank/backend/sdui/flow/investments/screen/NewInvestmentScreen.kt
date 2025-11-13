package com.vinibank.backend.sdui.flow.investments.screen

import com.vini.designsystemsdui.action.continueAction
import com.vini.designsystemsdui.component.button
import com.vini.designsystemsdui.component.card
import com.vini.designsystemsdui.component.column
import com.vini.designsystemsdui.component.lazyColumn
import com.vini.designsystemsdui.component.row
import com.vini.designsystemsdui.component.spacer
import com.vini.designsystemsdui.component.text
import com.vini.designsystemsdui.component.topAppBar
import com.vini.designsystemsdui.property.ColorProperty
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.ColorOption
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vinibank.backend.sdui.flow.investments.InvestmentsScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import com.vinibank.backend.sdui.oldflow.ScreenUtil
import kotlinx.serialization.json.JsonObject
import org.springframework.stereotype.Component

@Component
class NewInvestmentScreen : InvestmentsScreen {
    override val screenId: String = "NewInvestment"

    private val availableProducts =
        listOf("Fundos" to "AvailableFunds", "CDB" to "", "LCI" to "", "LCA" to "")

    private fun availableProducts(request: SdUiRequest) = lazyColumn(
        verticalArrangementProperty = VerticalArrangementProperty(VerticalArrangementOption.SpacedBy(10)),
        content =  availableProducts.map {
            card(
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                paddingHorizontalProperty = PaddingHorizontalProperty(20),
                content =  listOf(
                    row(
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        horizontalArrangementProperty = HorizontalArrangementProperty(
                            HorizontalArrangementOption.SpaceBetween
                        ),
                        content =  listOf(
                            text(
                                paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                paddingVerticalProperty = PaddingVerticalProperty(20),
                                textProperty = TextProperty(value = it.first),
                            ),
                        ),
                        onClick = continueAction(
                            flowId = request.flow,
                            nextScreenId = it.second,
                            currentScreenId = screenId
                        )
                    )
                )
            )
        }
    )

    override fun getScreen(request: SdUiRequest): JsonObject? {
        return ScreenUtil.screen(
            flow = request.flow,
            stage = screenId,
            version = "1",
            template = "",
            shouldCache = false,
            content =  listOf(
                column(
                    weightProperty = WeightProperty(1f),
                    content = listOf(
                        topAppBar(
                            title = listOf(
                                text(
                                    textProperty = TextProperty(value = "Familias disponiveis"),
                                    fontSizeProperty = FontSizeProperty(18f)
                                ),
                            )
                        ),
                        spacer(heightProperty = HeightProperty(10)),
                        availableProducts(request)
                    )
                ),
                column(
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    weightProperty = WeightProperty(1f),
                    verticalArrangementProperty = VerticalArrangementProperty(
                        VerticalArrangementOption.Center
                    ),
                    horizontalAlignmentProperty = HorizontalAlignmentProperty(
                        HorizontalAlignmentOption.Center),
                    content = listOf(
                        text(
                            colorProperty = ColorProperty(ColorOption.White),
                            textProperty = TextProperty(value = "teste"),
                        ),
                        button(
                            content = listOf(
                                text(
                                    textProperty = TextProperty(
                                        value = "teste",
                                    )
                                )
                            ),
                        )
                    )
                )
            )
        )
    }
}