package com.vinibank.backend.sdui.flow.investments.screen

import com.vini.designsystemsdui.CacheStrategy
import com.vini.designsystemsdui.SceneStrategy
import com.vini.designsystemsdui.core.SdUiNode.Template
import com.vini.designsystemsdui.ui.action.ContinueAction
import com.vini.designsystemsdui.ui.component.Button
import com.vini.designsystemsdui.ui.component.Card
import com.vini.designsystemsdui.ui.component.Column
import com.vini.designsystemsdui.ui.component.LazyColumn
import com.vini.designsystemsdui.ui.component.Row
import com.vini.designsystemsdui.ui.component.Spacer
import com.vini.designsystemsdui.ui.component.Text
import com.vini.designsystemsdui.ui.component.TopAppBar
import com.vini.designsystemsdui.core.SdUiComposer
import com.vini.designsystemsdui.ui.modifier.Modifier
import com.vini.designsystemsdui.ui.modifier.clickable
import com.vini.designsystemsdui.ui.modifier.fillMaxHeight
import com.vini.designsystemsdui.ui.modifier.fillMaxWidth
import com.vini.designsystemsdui.ui.modifier.option.HorizontalAlignmentOption
import com.vini.designsystemsdui.ui.modifier.padding
import com.vini.designsystemsdui.ui.modifier.size
import com.vini.designsystemsdui.ui.modifier.option.HorizontalArrangementOption
import com.vini.designsystemsdui.ui.modifier.option.VerticalArrangementOption
import com.vini.designsystemsdui.ui.template.ScreenTemplate
import com.vinibank.backend.sdui.flow.investments.InvestmentsScreen
import com.vinibank.backend.sdui.flow.investments.toBrl
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component

@Component
class ConsolidatedPositionScreen : InvestmentsScreen {
    override val screenId = "Start"

    private val consolidatedPositionValue = 1000.0
    private val products = listOf("Fundos" to 732.7, "CDB" to 167.3)

    private fun SdUiComposer.availableProducts(request: SdUiRequest) {
        Column(
            verticalArrangement = (
                VerticalArrangementOption.SpacedBy(10)
            ),
            content = {
                products.forEach {
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20),
                        content = {
                            Row(
                                modifier = Modifier.fillMaxWidth().clickable(
                                    action = ContinueAction(
                                        flowId = request.flow,
                                        nextScreenId = it.first,
                                        currentScreenId = screenId
                                    )
                                ),
                                horizontalArrangement = (
                                    HorizontalArrangementOption.SpaceBetween()
                                ),
                                content = {
                                    Text(
                                        modifier = Modifier.padding(horizontal = 20)
                                            .padding(vertical = 20),
                                        text = it.first
                                    )
                                    Text(
                                        modifier = Modifier.padding(horizontal = 20)
                                            .padding(vertical = 20),
                                        text = it.second.toBrl()
                                    )
                                }
                            )
                        }
                    )
                }
            }
        )
    }

    override fun getScreen(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? {
        return ScreenTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            scene = SceneStrategy.DualPane(),
            cacheStrategy = CacheStrategy.NoCache(),
            content = {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(),
                    content = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Investimentos",
                                    fontSize = 18f
                                )
                            }
                        )
                        Column(
                            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                            horizontalAlignment = (
                                HorizontalAlignmentOption.Center()
                            ),
                            content = {
                                Spacer(modifier = Modifier.size(10))
                                Text(
                                    modifier = Modifier.padding(vertical = 10),
                                    text = "Consolidado",
                                    fontSize = 18f,
                                )
                                Card(
                                    modifier = Modifier.fillMaxWidth()
                                        .padding(horizontal = 20),
                                    content = {
                                        Column(
                                            modifier = Modifier.padding(horizontal = 20)
                                                .padding(vertical = 20),
                                            horizontalAlignment = (
                                                HorizontalAlignmentOption.Center()
                                            ),
                                            content = {
                                                Row(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    horizontalArrangement = (
                                                        HorizontalArrangementOption.SpaceBetween()
                                                    ),
                                                    content = {
                                                        Text(text = "Valor Total")
                                                        Text(text = consolidatedPositionValue.toBrl())
                                                    }
                                                )
                                                Row(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    horizontalArrangement = (
                                                        HorizontalArrangementOption.SpaceBetween()
                                                    ),
                                                    content = {
                                                        Text(text = "Disponivel para resgate")
                                                        Text(text = consolidatedPositionValue.toBrl())
                                                    }
                                                )
                                            }
                                        )
                                    }
                                )
                                Spacer(modifier = Modifier.size(20))
                                Text(
                                    modifier = Modifier.padding(vertical = 10),
                                    text = "Consolidado por produto",
                                    fontSize = 18f
                                )
                                availableProducts(request)
                            }
                        )
                    }
                )
                Column(
                    content = {
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 20)
                                .padding(vertical = 20),
                            content = {
                                Text(text = "Investir")
                            },
                            onClickAction = ContinueAction(
                                flowId = request.flow,
                                nextScreenId = "NewInvestment",
                                currentScreenId = screenId
                            )
                        )
                    }
                )
            }
        )
    }
}
