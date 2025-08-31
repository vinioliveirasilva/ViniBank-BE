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
import com.vinibank.backend.sdui.flow.investments.InvestmentsScreen
import com.vinibank.backend.sdui.flow.investments.toBrl
import com.vinibank.backend.sdui.model.SdUiRequest
import com.vinibank.backend.sdui.oldflow.ScreenUtil
import kotlinx.serialization.json.JsonObject
import org.springframework.stereotype.Component

@Component
class ConsolidatedPositionScreen : InvestmentsScreen {
    override val screenId = "ConsolidatedPosition"

    private val consolidatedPositionValue = 1000.0
    private val products = listOf("Fundos" to 732.7, "CDB" to 167.3)

    private fun availableProducts(request: SdUiRequest) = lazyColumn(
        verticalArrangementProperty = VerticalArrangementProperty(VerticalArrangementOption.SpacedBy(10)),
        content =  products.map {
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
                                textProperty = TextProperty(value = it.first)
                            ),
                            text(
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

    override fun getScreen(request: SdUiRequest): JsonObject? {
        return ScreenUtil.screen(
            flow = "Investments",
            stage = screenId,
            version = "1",
            template = "",
            shouldCache = false,
            content =  listOf(
                column(
                    content =  listOf(
                        topAppBar(
                            title = listOf(
                                text(
                                    textProperty = TextProperty(value = "Investimentos"),
                                    fontSizeProperty = FontSizeProperty(18f)
                                )
                            )
                        ),
                        column(
                            weightProperty = WeightProperty(1f),
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                HorizontalAlignmentOption.Center
                            ),
                            content =  listOf(
                                spacer(sizeProperty = SizeProperty(10)),
                                text(
                                    textProperty = TextProperty(value = "Consolidado"),
                                    fontSizeProperty = FontSizeProperty(18f),
                                    paddingVerticalProperty = PaddingVerticalProperty(10),
                                ),
                                card(
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                    content =  listOf(
                                        column(
                                            paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                            paddingVerticalProperty = PaddingVerticalProperty(20),
                                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                                HorizontalAlignmentOption.Center
                                            ),
                                            content =  listOf(
                                                row(
                                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                                        HorizontalFillTypeOption.Max
                                                    ),
                                                    horizontalArrangementProperty = HorizontalArrangementProperty(
                                                        HorizontalArrangementOption.SpaceBetween
                                                    ),
                                                    content =  listOf(
                                                        text(textProperty = TextProperty(value = "Valor Total")),
                                                        text(textProperty = TextProperty(value = consolidatedPositionValue.toBrl()))
                                                    )
                                                ),
                                                row(
                                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                                        HorizontalFillTypeOption.Max
                                                    ),
                                                    horizontalArrangementProperty = HorizontalArrangementProperty(
                                                        HorizontalArrangementOption.SpaceBetween
                                                    ),
                                                    content =  listOf(
                                                        text(textProperty = TextProperty(value = "Disponivel para resgate")),
                                                        text(textProperty = TextProperty(value = consolidatedPositionValue.toBrl()))
                                                    )
                                                )
                                            )
                                        )
                                    )
                                ),
                                spacer(sizeProperty = SizeProperty(20)),
                                text(
                                    textProperty = TextProperty(value = "Consolidado por produto"),
                                    fontSizeProperty = FontSizeProperty(18f),
                                    paddingVerticalProperty = PaddingVerticalProperty(10)
                                ),
                                availableProducts(request),
                            )
                        ),
                        column(
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                HorizontalAlignmentOption.Center
                            ),
                            paddingVerticalProperty = PaddingVerticalProperty(20),
                            content =  listOf(
                                button(
                                    textProperty = TextProperty("Investir"),
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