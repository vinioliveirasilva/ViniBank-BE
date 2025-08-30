package com.vinibank.backend.sdui.flow.investments.screen

import com.vini.designsystemsdui.action.continueAction
import com.vini.designsystemsdui.component.card
import com.vini.designsystemsdui.component.lazyColumn
import com.vini.designsystemsdui.component.row
import com.vini.designsystemsdui.component.spacer
import com.vini.designsystemsdui.component.text
import com.vini.designsystemsdui.component.topBar
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
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
        verticalArrangement = VerticalArrangementProperty(VerticalArrangementOption.SpacedBy(10)),
        components = availableProducts.map {
            card(
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                paddingHorizontalProperty = PaddingHorizontalProperty(20),
                components = listOf(
                    row(
                        horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        horizontalArrangement = HorizontalArrangementProperty(
                            HorizontalArrangementOption.SpaceBetween
                        ),
                        components = listOf(
                            text(
                                paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                paddingVerticalProperty = PaddingVerticalProperty(20),
                                textProperty = TextProperty(value = it.first),
                            ),
                        ),
                        actions = listOf(
                            continueAction(
                                flowId = request.flow,
                                nextScreenId = it.second,
                                currentScreenId = screenId
                            )
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
            components = listOf(
                topBar(
                    components = listOf(
                        text(
                            textProperty = TextProperty(value = "Familias disponiveis"),
                            fontSizeProperty = FontSizeProperty(18f)
                        ),
                    )
                ),
                spacer(heightProperty = HeightProperty(10)),
                availableProducts(request)
            )
        )
    }
}