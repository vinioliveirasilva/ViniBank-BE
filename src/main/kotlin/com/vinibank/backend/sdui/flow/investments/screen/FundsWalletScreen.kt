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
import com.vini.designsystemsdui.property.TextAlignProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.TextAlignOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.sdui.flow.investments.InvestmentsScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.springframework.stereotype.Component

@Component
class FundsWalletScreen : InvestmentsScreen {
    override val screenId: String = "Fundos"

    private val availableOptions = listOf(
        InvestmentOption(
            id = "1",
            name = "Fundo de Renda Fixa",
            balance = "R$ 296,30",
            rentability = "12,5﹪",
            availableToRedeem = "R$ 246,00",
        ),
        InvestmentOption(
            id = "2",
            name = "Fundo de Ações",
            balance = "R$ 312,33",
            rentability = "6,99﹪",
            availableToRedeem = "R$ 312,33",
        ),
        InvestmentOption(
            id = "3",
            name = "Fundo Multimercado",
            balance = "R$ 124,07",
            rentability = "2,3﹪",
            availableToRedeem = "R$ 100,00",
        )
    )

    private fun cardRow(label: String, value: String) = Row(
        modifier = SdUiModifier().fillMaxWidth(),
        horizontalArrangementProperty = HorizontalArrangementProperty(
            HorizontalArrangementOption.SpaceBetween
        ),
        content = listOf(
            Text(
                textProperty = TextProperty(value = label),
                fontSizeProperty = FontSizeProperty(16f)
            ),
            Text(
                textProperty = TextProperty(value = value),
                fontSizeProperty = FontSizeProperty(16f)
            ),
        ),
    )

    private fun availableFundOption(
        request: SdUiRequest,
    ) = availableOptions.map {
        Card(
            modifier = SdUiModifier().padding(vertical = 10).padding(horizontal = 10)
            .fillMaxWidth(),
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
                    modifier = SdUiModifier().padding(horizontal = 20).padding(vertical = 10),
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
                        cardRow("Saldo atual", it.balance),
                        cardRow("Disponivel para resgate", it.availableToRedeem),
                        cardRow("Rentabilidade", it.rentability),
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
            scene = SceneStrategy.DualPane(),
            cacheStrategy = CacheStrategy.NoCache(),
            content = listOf(
                LazyColumn(
                    content = listOf(
                        TopAppBar(
                            title = listOf(
                                Text(
                                    textProperty = TextProperty(value = "Carteira de Fundos"),
                                    fontSizeProperty = FontSizeProperty(18f)
                                )
                            )
                        ),
                        Text(
                            modifier = SdUiModifier().padding(vertical = 10)
                                .padding(horizontal = 10).fillMaxWidth(),
                            textProperty = TextProperty(value = "Ativos"),
                            fontSizeProperty = FontSizeProperty(18f),
                            textAlignProperty = TextAlignProperty(TextAlignOption.Center)
                        ),
                        Column(
                            content = availableFundOption(request)
                        ),
                        Spacer(modifier = SdUiModifier().height(10)),
                    )
                )
            )
        )
    }
}