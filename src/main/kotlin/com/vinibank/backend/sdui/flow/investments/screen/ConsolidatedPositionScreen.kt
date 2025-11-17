package com.vinibank.backend.sdui.flow.investments.screen

import com.vini.designsystemsdui.action.continueAction
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Card
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.LazyColumn
import com.vini.designsystemsdui.component.Row
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.component.TopAppBar
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.SizeProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vini.designsystemsdui.template.Template
import com.vinibank.backend.sdui.flow.investments.InvestmentsScreen
import com.vinibank.backend.sdui.flow.investments.toBrl
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component

@Component
class ConsolidatedPositionScreen : InvestmentsScreen {
    override val screenId = "ConsolidatedPosition"

    private val consolidatedPositionValue = 1000.0
    private val products = listOf("Fundos" to 732.7, "CDB" to 167.3)

    private fun availableProducts(request: SdUiRequest) = LazyColumn(
        verticalArrangementProperty = VerticalArrangementProperty(VerticalArrangementOption.SpacedBy(10)),
        content =  products.map {
            Card(
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                paddingHorizontalProperty = PaddingHorizontalProperty(20),
                content =  listOf(
                    Row(
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        horizontalArrangementProperty = HorizontalArrangementProperty(
                            HorizontalArrangementOption.SpaceBetween
                        ),
                        content =  listOf(
                            Text(
                                paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                paddingVerticalProperty = PaddingVerticalProperty(20),
                                textProperty = TextProperty(value = it.first)
                            ),
                            Text(
                                paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                paddingVerticalProperty = PaddingVerticalProperty(20),
                                textProperty = TextProperty(value = it.second.toBrl())
                            )
                        ),
                        onClick = continueAction(
                            flowId = request.flow,
                            nextScreenId = it.first,
                            currentScreenId = screenId
                        )
                    )
                )
            )
        }
    )

    override fun getScreen(request: SdUiRequest): Template? {
        return DefaultTemplate(
            flow = "Investments",
            stage = screenId,
            version = "1",
            template = "",
            content =  listOf(
                Column(
                    content =  listOf(
                        TopAppBar(
                            title = listOf(
                                Text(
                                    textProperty = TextProperty(value = "Investimentos"),
                                    fontSizeProperty = FontSizeProperty(18f)
                                )
                            )
                        ),
                        Column(
                            weightProperty = WeightProperty(1f),
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                HorizontalAlignmentOption.Center
                            ),
                            content =  listOf(
                                Spacer(sizeProperty = SizeProperty(10)),
                                Text(
                                    textProperty = TextProperty(value = "Consolidado"),
                                    fontSizeProperty = FontSizeProperty(18f),
                                    paddingVerticalProperty = PaddingVerticalProperty(10),
                                ),
                                Card(
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                    content =  listOf(
                                        Column(
                                            paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                            paddingVerticalProperty = PaddingVerticalProperty(20),
                                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                                HorizontalAlignmentOption.Center
                                            ),
                                            content =  listOf(
                                                Row(
                                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                                        HorizontalFillTypeOption.Max
                                                    ),
                                                    horizontalArrangementProperty = HorizontalArrangementProperty(
                                                        HorizontalArrangementOption.SpaceBetween
                                                    ),
                                                    content =  listOf(
                                                        Text(textProperty = TextProperty(value = "Valor Total")),
                                                        Text(textProperty = TextProperty(value = consolidatedPositionValue.toBrl()))
                                                    )
                                                ),
                                                Row(
                                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                                        HorizontalFillTypeOption.Max
                                                    ),
                                                    horizontalArrangementProperty = HorizontalArrangementProperty(
                                                        HorizontalArrangementOption.SpaceBetween
                                                    ),
                                                    content =  listOf(
                                                        Text(textProperty = TextProperty(value = "Disponivel para resgate")),
                                                        Text(textProperty = TextProperty(value = consolidatedPositionValue.toBrl()))
                                                    )
                                                )
                                            )
                                        )
                                    )
                                ),
                                Spacer(sizeProperty = SizeProperty(20)),
                                Text(
                                    textProperty = TextProperty(value = "Consolidado por produto"),
                                    fontSizeProperty = FontSizeProperty(18f),
                                    paddingVerticalProperty = PaddingVerticalProperty(10)
                                ),
                                availableProducts(request),
                            )
                        ),
                        Column(
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                HorizontalAlignmentOption.Center
                            ),
                            paddingVerticalProperty = PaddingVerticalProperty(20),
                            content =  listOf(
                                Button(
                                    content = listOf(
                                        Text(
                                            textProperty = TextProperty(
                                                value = "Investir",
                                            )
                                        )
                                    ),
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                    onClick = continueAction(
                                        flowId = request.flow,
                                        nextScreenId = "NewInvestment",
                                        currentScreenId = screenId
                                    )
                                )
                            )
                        )
                    )
                )
            )
        )
    }
}