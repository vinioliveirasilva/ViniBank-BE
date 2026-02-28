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
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.height
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
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

    private fun availableFundOption(
        request: SdUiRequest,
    ) = fundsDatabase.getAllFunds().values.map {
        Card(
            modifier = SdUiModifier().fillMaxWidth(),
            onClick = ContinueAction(
                flowId = request.flow,
                nextScreenId = "hireFund",
                currentScreenId = screenId,
                screenData = buildJsonObject {
                    put("fundId", it.id)
                }
            ),
            content = listOf(
                Column(
                    modifier = SdUiModifier().padding(horizontal = 10).padding(vertical = 10),
                    content = listOf(
                        Row(
                            modifier = SdUiModifier().fillMaxWidth(),
                            horizontalArrangementProperty = HorizontalArrangementProperty(
                                HorizontalArrangementOption.Center
                            ),
                            content = listOf(
                                Text(
                                    textProperty = TextProperty(value = it.name),
                                    fontSizeProperty = FontSizeProperty(16f)
                                ),
                            ),
                        ),
                        Spacer(modifier = SdUiModifier().height(10)),
                        Row(
                            modifier = SdUiModifier().fillMaxWidth(),
                            horizontalArrangementProperty = HorizontalArrangementProperty(
                                HorizontalArrangementOption.SpaceBetween
                            ),
                            content = listOf(
                                Text(
                                    textProperty = TextProperty(value = "Rentabilidade"),
                                    fontSizeProperty = FontSizeProperty(16f)
                                ),
                                Text(
                                    textProperty = TextProperty(value = it.rentability),
                                    fontSizeProperty = FontSizeProperty(16f)
                                ),
                            ),
                        ),
                        Row(
                            modifier = SdUiModifier().fillMaxWidth(),
                            horizontalArrangementProperty = HorizontalArrangementProperty(
                                HorizontalArrangementOption.SpaceBetween
                            ),
                            content = listOf(
                                Text(
                                    textProperty = TextProperty(value = "Investimento Mínimo"),
                                    fontSizeProperty = FontSizeProperty(16f)
                                ),
                                Text(
                                    textProperty = TextProperty(value = it.minimumInvestment.toBrl()),
                                    fontSizeProperty = FontSizeProperty(16f)
                                ),
                            ),
                        ),
                        Row(
                            modifier = SdUiModifier().fillMaxWidth(),
                            horizontalArrangementProperty = HorizontalArrangementProperty(
                                HorizontalArrangementOption.SpaceBetween
                            ),
                            content = listOf(
                                Text(
                                    textProperty = TextProperty(value = "Risco"),
                                    fontSizeProperty = FontSizeProperty(16f)
                                ),
                                Text(
                                    textProperty = TextProperty(value = it.riskLevel),
                                    fontSizeProperty = FontSizeProperty(16f)
                                ),
                            ),
                        ),
                    ),
                )
            )
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
            cacheStrategy = CacheStrategy.NoCache(),
            content = listOf(
                TopAppBar(
                    title = listOf(
                        Text(
                            textProperty = TextProperty(value = "Fundos Disponíveis"),
                            fontSizeProperty = FontSizeProperty(18f)
                        )
                    )
                ),
                Spacer(modifier = SdUiModifier().height(10)),
                LazyColumn(
                    modifier = SdUiModifier().padding(horizontal = 20),
                    verticalArrangementProperty = VerticalArrangementProperty(
                        VerticalArrangementOption.SpacedBy(
                            10
                        )
                    ),
                    content = availableFundOption(request)
                )
            )
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