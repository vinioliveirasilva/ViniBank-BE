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
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.TextAlignProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.TextAlignOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.sdui.flow.investments.InvestmentsScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.springframework.stereotype.Component

@Component
class CDBWalletScreen : InvestmentsScreen {
    override val screenId: String = "CDB"

    private val availableOptions = listOf(
        InvestmentOption(
            id = "1",
            name = "CDB Pós-fixado",
            balance = "R$ 13,30",
            rentability = "7,85﹪",
            availableToRedeem = "R$ 13,30",
        ),
        InvestmentOption(
            id = "2",
            name = "CDB Prefixado",
            balance = "R$ 100,00",
            rentability = "2,50﹪",
            availableToRedeem = "R$ 94,90",
        ),
        InvestmentOption(
            id = "3",
            name = "CDB IPCA+",
            balance = "R$ 54,00",
            rentability = "1,44﹪",
            availableToRedeem = "R$ 18,00",
        )
    )

    private fun cardRow(label: String, value: String) = Row(
        horizontalFillTypeProperty = HorizontalFillTypeProperty(
            HorizontalFillTypeOption.Max
        ),
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
            paddingVerticalProperty = PaddingVerticalProperty(10),
            paddingHorizontalProperty = PaddingHorizontalProperty(10),
            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
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
                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                    paddingVerticalProperty = PaddingVerticalProperty(10),
                    content = listOf(
                        Row(
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                HorizontalFillTypeOption.Max
                            ),
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
                        Spacer(heightProperty = HeightProperty(10)),
                        cardRow("Saldo atual", it.balance),
                        cardRow("Disponivel para resgate", it.availableToRedeem),
                        cardRow("Rentabilidade", it.rentability),
                    ),
                )
            )
        )
    }

    override fun getScreen(request: SdUiRequest, parameters: Map<String, String>, screenId: String): Template? {
        println("CDB: $request")
        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            scene = SceneStrategy.DualPane(),
            cacheStrategy = CacheStrategy.NoCache(),
            content =  listOf(
                LazyColumn(
                    content = listOf(
                        TopAppBar(
                            title = listOf(
                                Text(
                                    textProperty = TextProperty(value = "Carteira de CDB"),
                                    fontSizeProperty = FontSizeProperty(18f)
                                )
                            )
                        ),
                        Text(
                            textProperty = TextProperty(value = "Ativos"),
                            fontSizeProperty = FontSizeProperty(18f),
                            paddingVerticalProperty = PaddingVerticalProperty(10),
                            paddingHorizontalProperty = PaddingHorizontalProperty(10),
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                            textAlignProperty = TextAlignProperty(TextAlignOption.Center)
                        ),
                        Column(
                            content = availableFundOption(request)
                        ),
                        Spacer(heightProperty = HeightProperty(10)),
                    )
                )
            )
        )
    }
}

