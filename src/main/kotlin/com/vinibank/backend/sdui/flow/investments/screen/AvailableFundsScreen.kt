package com.vinibank.backend.sdui.flow.investments.screen

import com.vini.designsystemsdui.CacheStrategy
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
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.height
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.option.HorizontalArrangementOption
import com.vini.designsystemsdui.modifier.option.VerticalArrangementOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.db.FundsDatabase
import com.vinibank.backend.sdui.flow.investments.InvestmentsScreen
import com.vinibank.backend.sdui.flow.investments.toBrl
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.springframework.stereotype.Component

@Component
class AvailableFundsScreen(
    private val fundsDatabase: FundsDatabase,
) : InvestmentsScreen {
    override val screenId: String = "AvailableFunds"

    private fun SdUiComposer.availableFundOption(request: SdUiRequest) {
        fundsDatabase.getAllFunds().values.forEach {
            Card(
                modifier = SdUiModifier().fillMaxWidth().clickable(
                    action = ContinueAction(
                        flowId = request.flow,
                        nextScreenId = "hireFund",
                        currentScreenId = screenId,
//                        screenData = buildJsonObject {
//                            put("fundId", it.id)
//                        }
                    )
                ),
                content = {
                    Column(
                        modifier = SdUiModifier().padding(horizontal = 10).padding(vertical = 10),
                        content = {
                            Row(
                                modifier = SdUiModifier().fillMaxWidth(),
                                horizontalArrangement = (
                                    HorizontalArrangementOption.Center()
                                ),
                                content = {
                                    Text(
                                        text = it.name,
                                        fontSize = 16f
                                    )
                                }
                            )
                            Spacer(modifier = SdUiModifier().height(10))
                            Row(
                                modifier = SdUiModifier().fillMaxWidth(),
                                horizontalArrangement = (
                                    HorizontalArrangementOption.SpaceBetween()
                                ),
                                content = {
                                    Text(
                                        text = "Rentabilidade",
                                        fontSize = 16f
                                    )
                                    Text(
                                        text = it.rentability,
                                        fontSize = 16f
                                    )
                                }
                            )
                            Row(
                                modifier = SdUiModifier().fillMaxWidth(),
                                horizontalArrangement = (
                                    HorizontalArrangementOption.SpaceBetween()
                                ),
                                content = {
                                    Text(
                                        text = "Investimento Mínimo",
                                        fontSize = 16f
                                    )
                                    Text(
                                        text = it.minimumInvestment.toBrl(),
                                        fontSize = 16f
                                    )
                                }
                            )
                            Row(
                                modifier = SdUiModifier().fillMaxWidth(),
                                horizontalArrangement = (
                                    HorizontalArrangementOption.SpaceBetween()
                                ),
                                content = {
                                    Text(
                                        text = "Risco",
                                        fontSize = 16f
                                    )
                                    Text(
                                        text = it.riskLevel,
                                        fontSize = 16f
                                    )
                                }
                            )
                        },
                    )
                }
            )
        }
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
            cacheStrategy = CacheStrategy.NoCache(),
            content = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Fundos Disponíveis",
                            fontSize = 18f
                        )
                    }
                )
                Spacer(modifier = SdUiModifier().height(10))
                LazyColumn(
                    modifier = SdUiModifier().padding(horizontal = 20),
                    verticalArrangement = (
                        VerticalArrangementOption.SpacedBy(10)
                    ),
                    content = {
                        availableFundOption(request)
                    }
                )
            }
        )
    }
}


data class FundInfo(
    val id: String,
    val name: String,
    val type: String,
    val rentability: String,
    val minimumValue: Double,
    val tax: String,
    val period: String,
)

@Component
class FundDetailsRepository {
    private val fundDetails = listOf(
        FundInfo(
            id = "1",
            name = "ViniBank Fundo de Renda Fixa",
            type = "Renda Fixa",
            rentability = "12,5﹪ a.a.",
            minimumValue = 1000.00,
            tax = "2.0%",
            period = "a.a."
        ),
        FundInfo(
            id = "2",
            name = "ViniBank Fundo de Ações",
            type = "Ações",
            rentability = "15,0﹪ a.a.",
            minimumValue = 5000.00,
            tax = "1.0%",
            period = "a.a."
        ),
        FundInfo(
            id = "3",
            name = "ViniBank Fundo Multimercado",
            type = "Multimercado",
            rentability = "10,0﹪ a.a.",
            minimumValue = 2000.00,
            tax = "0.8%",
            period = "a.a."
        )
    )

    fun getFundDetails(fundId: String): FundInfo? {
        return fundDetails.find { it.id == fundId }
    }
}
