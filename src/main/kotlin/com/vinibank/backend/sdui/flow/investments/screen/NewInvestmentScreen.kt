package com.vinibank.backend.sdui.flow.investments.screen

import com.vini.designsystemsdui.SceneStrategy
import com.vini.designsystemsdui.core.SdUiNode.Template
import com.vini.designsystemsdui.ui.action.ContinueAction
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
import com.vini.designsystemsdui.ui.modifier.height
import com.vini.designsystemsdui.ui.modifier.padding
import com.vini.designsystemsdui.ui.modifier.option.HorizontalArrangementOption
import com.vini.designsystemsdui.ui.modifier.option.VerticalArrangementOption
import com.vini.designsystemsdui.ui.template.ScreenTemplate
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
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20),
                        content = {
                            Row(
                                modifier = Modifier.fillMaxWidth().clickable(
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
                                        modifier = Modifier.padding(horizontal = 20)
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
        return ScreenTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            scene = SceneStrategy.DualPane(id = "1"),
            content = {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    content = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Familias disponiveis",
                                    fontSize = 18f
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(10))
                        availableProducts(request)
                    }
                )
            }
        )
    }
}
