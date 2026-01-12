package com.vinibank.backend.sdui.flow.investments.screen

import com.vini.designsystemsdui.SceneStrategy
import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.ContinueAction
import com.vini.designsystemsdui.component.Card
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.LazyColumn
import com.vini.designsystemsdui.component.Row
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.component.TopAppBar
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.sdui.flow.investments.InvestmentsScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component

@Component
class NewInvestmentScreen : InvestmentsScreen {
    override val screenId: String = "NewInvestment"

    private val availableProducts =
        listOf("Fundos" to "AvailableFunds", "CDB" to "")

    private fun availableProducts(request: SdUiRequest) = LazyColumn(
        verticalArrangementProperty = VerticalArrangementProperty(
            VerticalArrangementOption.SpacedBy(
                10
            )
        ),
        content = availableProducts.map { (investmentName: String, screenName: String) ->
            Card(
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                paddingHorizontalProperty = PaddingHorizontalProperty(20),
                content = listOf(
                    Row(
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(
                            HorizontalFillTypeOption.Max
                        ),
                        horizontalArrangementProperty = HorizontalArrangementProperty(
                            HorizontalArrangementOption.SpaceBetween
                        ),
                        content = listOf(
                            Text(
                                paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                paddingVerticalProperty = PaddingVerticalProperty(20),
                                textProperty = TextProperty(value = investmentName),
                            ),
                        ),
                        onClick = ContinueAction(
                            flowId = request.flow,
                            nextScreenId = screenName,
                            currentScreenId = screenId
                        )
                    )
                )
            )
        }
    )

    override fun getScreen(request: SdUiRequest): Template? {
        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            scene = SceneStrategy.DualPanel(id = "1"),
            content = listOf(
                Column(
                    weightProperty = WeightProperty(1f),
                    content = listOf(
                        TopAppBar(
                            title = listOf(
                                Text(
                                    textProperty = TextProperty(value = "Familias disponiveis"),
                                    fontSizeProperty = FontSizeProperty(18f)
                                ),
                            )
                        ),
                        Spacer(heightProperty = HeightProperty(10)),
                        availableProducts(request)
                    )
                ),
            )
        )
    }
}