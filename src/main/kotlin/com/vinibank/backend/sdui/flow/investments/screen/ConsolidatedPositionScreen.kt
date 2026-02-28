package com.vinibank.backend.sdui.flow.investments.screen

import com.vini.designsystemsdui.CacheStrategy
import com.vini.designsystemsdui.SceneStrategy
import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.ContinueAction
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Card
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.LazyColumn
import com.vini.designsystemsdui.component.Row
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.component.TopAppBar
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.size
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.sdui.flow.investments.InvestmentsScreen
import com.vinibank.backend.sdui.flow.investments.toBrl
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component

@Component
class ConsolidatedPositionScreen : InvestmentsScreen {
    override val screenId = "Start"

    private val consolidatedPositionValue = 1000.0
    private val products = listOf("Fundos" to 732.7, "CDB" to 167.3)

    private fun availableProducts(request: SdUiRequest) = Column(
        verticalArrangementProperty = VerticalArrangementProperty(
            VerticalArrangementOption.SpacedBy(
                10
            )
        ),
        content = products.map {
            Card(
                modifier = SdUiModifier().fillMaxWidth().padding(horizontal = 20),
                content = listOf(
                    Row(
                        modifier = SdUiModifier().fillMaxWidth(),
                        horizontalArrangementProperty = HorizontalArrangementProperty(
                            HorizontalArrangementOption.SpaceBetween
                        ),
                        content = listOf(
                            Text(
                                modifier = SdUiModifier().padding(horizontal = 20)
                                    .padding(vertical = 20),
                                textProperty = TextProperty(value = it.first)
                            ),
                            Text(
                                modifier = SdUiModifier().padding(horizontal = 20)
                                    .padding(vertical = 20),
                                textProperty = TextProperty(value = it.second.toBrl())
                            )
                        ),
                        onClick = ContinueAction(
                            flowId = request.flow,
                            nextScreenId = it.first,
                            currentScreenId = screenId
                        )
                    )
                )
            )
        }
    )

    override fun getScreen(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? {
        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            scene = SceneStrategy.DualPane(),
            cacheStrategy = CacheStrategy.NoCache(),
            content = listOf(
                LazyColumn(
                    weightProperty = WeightProperty(1f),
                    content = listOf(
                        TopAppBar(
                            title = listOf(
                                Text(
                                    textProperty = TextProperty(value = "Investimentos"),
                                    fontSizeProperty = FontSizeProperty(18f)
                                )
                            )
                        ),
                        Column(
                            modifier = SdUiModifier().fillMaxWidth(),
                            weightProperty = WeightProperty(1f),
                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                HorizontalAlignmentOption.Center
                            ),
                            content = listOf(
                                Spacer(modifier = SdUiModifier().size(10)),
                                Text(
                                    modifier = SdUiModifier().padding(vertical = 10),
                                    textProperty = TextProperty(value = "Consolidado"),
                                    fontSizeProperty = FontSizeProperty(18f),
                                ),
                                Card(
                                    modifier = SdUiModifier().fillMaxWidth()
                                        .padding(horizontal = 20),
                                    content = listOf(
                                        Column(
                                            modifier = SdUiModifier().padding(horizontal = 20)
                                                .padding(vertical = 20),
                                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                                HorizontalAlignmentOption.Center
                                            ),
                                            content = listOf(
                                                Row(
                                                    modifier = SdUiModifier().fillMaxWidth(),
                                                    horizontalArrangementProperty = HorizontalArrangementProperty(
                                                        HorizontalArrangementOption.SpaceBetween
                                                    ),
                                                    content = listOf(
                                                        Text(textProperty = TextProperty(value = "Valor Total")),
                                                        Text(textProperty = TextProperty(value = consolidatedPositionValue.toBrl()))
                                                    )
                                                ),
                                                Row(
                                                    modifier = SdUiModifier().fillMaxWidth(),
                                                    horizontalArrangementProperty = HorizontalArrangementProperty(
                                                        HorizontalArrangementOption.SpaceBetween
                                                    ),
                                                    content = listOf(
                                                        Text(textProperty = TextProperty(value = "Disponivel para resgate")),
                                                        Text(textProperty = TextProperty(value = consolidatedPositionValue.toBrl()))
                                                    )
                                                )
                                            )
                                        )
                                    )
                                ),
                                Spacer(modifier = SdUiModifier().size(20)),
                                Text(
                                    modifier = SdUiModifier().padding(vertical = 10),
                                    textProperty = TextProperty(value = "Consolidado por produto"),
                                    fontSizeProperty = FontSizeProperty(18f)
                                ),
                                availableProducts(request),
                            )
                        ),
                    )
                ),
                Column(
                    content = listOf(
                        Button(
                            modifier = SdUiModifier().fillMaxWidth().padding(horizontal = 20)
                                .padding(vertical = 20),
                            content = listOf(
                                Text(
                                    textProperty = TextProperty(
                                        value = "Investir",
                                    )
                                )
                            ),
                            onClick = ContinueAction(
                                flowId = request.flow,
                                nextScreenId = "NewInvestment",
                                currentScreenId = screenId
                            )
                        )
                    )
                )
            )
        )
    }
}