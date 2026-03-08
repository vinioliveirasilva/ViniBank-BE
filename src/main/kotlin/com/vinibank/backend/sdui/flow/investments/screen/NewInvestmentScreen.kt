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
import com.vini.designsystemsdui.core.SdUiComposer
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.clickable
import com.vini.designsystemsdui.modifier.fillMaxHeight
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.height
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.option.HorizontalArrangementOption
import com.vini.designsystemsdui.modifier.option.VerticalArrangementOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.sdui.flow.investments.InvestmentsScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component

@Component
class NewInvestmentScreen : InvestmentsScreen {
    override val screenId: String = "NewInvestment"

    private val availableProducts = listOf("Fundos" to "AvailableFunds", "CDB" to "")

    private fun SdUiComposer.availableProducts(request: SdUiRequest) {
        LazyColumn(
            verticalArrangement = (
                VerticalArrangementOption.SpacedBy(10)
            ),
            content = {
                availableProducts.forEach { (investmentName: String, screenName: String) ->
                    Card(
                        modifier = SdUiModifier().fillMaxWidth().padding(horizontal = 20),
                        content = {
                            Row(
                                modifier = SdUiModifier().fillMaxWidth().clickable(
                                    action = ContinueAction(
                                        flowId = request.flow,
                                        nextScreenId = screenName,
                                        currentScreenId = screenId
                                    )
                                ),
                                horizontalArrangement = (
                                    HorizontalArrangementOption.SpaceBetween()
                                ),
                                content = {
                                    Text(
                                        modifier = SdUiModifier().padding(horizontal = 20)
                                            .padding(vertical = 20),
                                        text = investmentName,
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
        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            scene = SceneStrategy.DualPane(id = "1"),
            content = {
                Column(
                    modifier = SdUiModifier().fillMaxHeight(),
                    content = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Familias disponiveis",
                                    fontSize = 18f
                                )
                            }
                        )
                        Spacer(modifier = SdUiModifier().height(10))
                        availableProducts(request)
                    }
                )
            }
        )
    }
}
