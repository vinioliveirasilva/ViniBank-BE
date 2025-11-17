package com.vinibank.backend.sdui.flow.investments.screen

import com.vini.designsystemsdui.action.continueAction
import com.vini.designsystemsdui.action.toBooleanAction
import com.vini.designsystemsdui.component.Card
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.LazyColumn
import com.vini.designsystemsdui.component.ModalDatePicker
import com.vini.designsystemsdui.component.OutlinedButton
import com.vini.designsystemsdui.component.Row
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.component.TopAppBar
import com.vini.designsystemsdui.property.ConfirmedDateProperty
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.VisibilityProperty
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vini.designsystemsdui.template.Template
import com.vini.designsystemsdui.validator.millisToDateStringValidator
import com.vinibank.backend.sdui.flow.investments.InvestmentsScreen
import com.vinibank.backend.sdui.flow.investments.toBrl
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put
import org.springframework.stereotype.Component

@Component
class AvailableFundsScreen : InvestmentsScreen {
    override val screenId: String = "AvailableFunds"

    data class FundOption(
        val id: String,
        val name: String,
        val type: String,
        val rentability: String,
        val minInvestment: Double,
    )

    private val availableFundOptions = listOf(
        FundOption(
            id = "1",
            name = "ViniBank Fundo de Renda Fixa",
            type = "Renda Fixa",
            rentability = "12,5﹪ a.a.",
            minInvestment = 1000.00
        ),
        FundOption(
            id = "2",
            name = "ViniBank Fundo de Ações",
            type = "Ações",
            rentability = "15,0﹪ a.a.",
            minInvestment = 5000.00
        ),
        FundOption(
            id = "3",
            name = "ViniBank Fundo Multimercado",
            type = "Multimercado",
            rentability = "10,0﹪ a.a.",
            minInvestment = 2000.00
        )
    )

    private fun availableFundOption(
        request: SdUiRequest,
    ) = availableFundOptions.map {
        Card(
            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
            onClick = continueAction(
                flowId = request.flow,
                nextScreenId = "hireFund",
                currentScreenId = screenId,
                screenData = buildJsonObject {
                    put("fundId", it.id)
                }
            ),
            content = listOf(
                Column(
                    paddingHorizontalProperty = PaddingHorizontalProperty(10),
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
                        Row(
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                HorizontalFillTypeOption.Max
                            ),
                            horizontalArrangementProperty = HorizontalArrangementProperty(
                                HorizontalArrangementOption.SpaceBetween
                            ),
                            content = listOf(
                                Text(
                                    textProperty = TextProperty(value = "Tipo"),
                                    fontSizeProperty = FontSizeProperty(16f)
                                ),
                                Text(
                                    textProperty = TextProperty(value = it.type),
                                    fontSizeProperty = FontSizeProperty(16f)
                                ),
                            ),
                        ),
                        Row(
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                HorizontalFillTypeOption.Max
                            ),
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
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                HorizontalFillTypeOption.Max
                            ),
                            horizontalArrangementProperty = HorizontalArrangementProperty(
                                HorizontalArrangementOption.SpaceBetween
                            ),
                            content = listOf(
                                Text(
                                    textProperty = TextProperty(value = "Investimento Mínimo"),
                                    fontSizeProperty = FontSizeProperty(16f)
                                ),
                                Text(
                                    textProperty = TextProperty(value = it.minInvestment.toBrl()),
                                    fontSizeProperty = FontSizeProperty(16f)
                                ),
                            ),
                        ),
                    ),
                )
            )
        )
    }

    override fun getScreen(request: SdUiRequest): Template? {
        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            template = "",
            content = listOf(
                TopAppBar(
                    title = listOf(
                        Text(
                            textProperty = TextProperty(value = "Fundos Disponíveis"),
                            fontSizeProperty = FontSizeProperty(18f)
                        )
                    )
                ),
                Spacer(heightProperty = HeightProperty(10)),
                LazyColumn(
                    verticalArrangementProperty = VerticalArrangementProperty(
                        VerticalArrangementOption.SpacedBy(
                            10
                        )
                    ),
                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
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
    val tax: Double,
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
            tax = 2.0,
            period = "a.a."
        ),
        FundInfo(
            id = "2",
            name = "ViniBank Fundo de Ações",
            type = "Ações",
            rentability = "15,0﹪ a.a.",
            minimumValue = 5000.00,
            tax = 1.0,
            period = "a.a."
        ),
        FundInfo(
            id = "3",
            name = "ViniBank Fundo Multimercado",
            type = "Multimercado",
            rentability = "10,0﹪ a.a.",
            minimumValue = 2000.00,
            tax = 0.8,
            period = "a.a."
        )
    )

    fun getFundDetails(fundId: String): FundInfo? {
        return fundDetails.find { it.id == fundId }
    }
}

@Component
class HireFundScreen(
    private val fundRepository: FundDetailsRepository,
) : InvestmentsScreen {
    override val screenId: String = "hireFund"

    override fun getScreen(request: SdUiRequest): Template? {

        val fundId = (request.screenData?.get("fundId")?.jsonPrimitive?.content ?: "")
        val fundInfo = fundRepository.getFundDetails(fundId)

        if (fundInfo == null) return null

        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            template = "",
            content = listOf(
                LazyColumn(
                    content = listOf(
                        TopAppBar(
                            title = listOf(
                                Text(
                                    textProperty = TextProperty(value = "Detalhes do Fundo"),
                                    fontSizeProperty = FontSizeProperty(18f)
                                ),
                            )
                        ),
                        Spacer(heightProperty = HeightProperty(10)),
                        Card(
                            paddingHorizontalProperty = PaddingHorizontalProperty(20),
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                HorizontalFillTypeOption.Max
                            ),
                            content = listOf(
                                Column(
                                    paddingHorizontalProperty = PaddingHorizontalProperty(10),
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
                                                    textProperty = TextProperty(value = fundInfo.name),
                                                    fontSizeProperty = FontSizeProperty(16f)
                                                ),
                                            ),
                                        ),
                                        Spacer(heightProperty = HeightProperty(10)),
                                        Row(
                                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                                HorizontalFillTypeOption.Max
                                            ),
                                            horizontalArrangementProperty = HorizontalArrangementProperty(
                                                HorizontalArrangementOption.SpaceBetween
                                            ),
                                            content = listOf(
                                                Text(
                                                    textProperty = TextProperty(value = "Tipo"),
                                                    fontSizeProperty = FontSizeProperty(16f)
                                                ),
                                                Text(
                                                    textProperty = TextProperty(value = fundInfo.type),
                                                    fontSizeProperty = FontSizeProperty(16f)
                                                ),
                                            ),
                                        ),
                                        Row(
                                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                                HorizontalFillTypeOption.Max
                                            ),
                                            horizontalArrangementProperty = HorizontalArrangementProperty(
                                                HorizontalArrangementOption.SpaceBetween
                                            ),
                                            content = listOf(
                                                Text(
                                                    textProperty = TextProperty(value = "Rentabilidade"),
                                                    fontSizeProperty = FontSizeProperty(16f)
                                                ),
                                                Text(
                                                    textProperty = TextProperty(value = fundInfo.rentability),
                                                    fontSizeProperty = FontSizeProperty(16f)
                                                ),
                                            ),
                                        ),
                                        Row(
                                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                                HorizontalFillTypeOption.Max
                                            ),
                                            horizontalArrangementProperty = HorizontalArrangementProperty(
                                                HorizontalArrangementOption.SpaceBetween
                                            ),
                                            content = listOf(
                                                Text(
                                                    textProperty = TextProperty(value = "Investimento Mínimo"),
                                                    fontSizeProperty = FontSizeProperty(16f)
                                                ),
                                                Text(
                                                    textProperty = TextProperty(value = fundInfo.minimumValue.toBrl()),
                                                    fontSizeProperty = FontSizeProperty(16f)
                                                ),
                                            ),
                                        ),
                                        Row(
                                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                                HorizontalFillTypeOption.Max
                                            ),
                                            horizontalArrangementProperty = HorizontalArrangementProperty(
                                                HorizontalArrangementOption.SpaceBetween
                                            ),
                                            content = listOf(
                                                Text(
                                                    textProperty = TextProperty(value = "Taxa de Administração"),
                                                    fontSizeProperty = FontSizeProperty(16f)
                                                ),
                                                Text(
                                                    textProperty = TextProperty(value = fundInfo.tax.toString()),
                                                    fontSizeProperty = FontSizeProperty(16f)
                                                ),
                                            ),
                                        ),
                                    ),
                                )
                            )
                        ),
                        OutlinedButton(
                            content = listOf(
                                Text(
                                    textProperty = TextProperty(
                                        value = "Seleciona a data de investimento",
                                        "dateParsed"
                                    )
                                )
                            ),
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                HorizontalFillTypeOption.Max
                            ),
                            paddingHorizontalProperty = PaddingHorizontalProperty(16),
                            onClick = toBooleanAction("showDatePicker", true)
                        ),
                        ModalDatePicker(
                            visibilityProperty = VisibilityProperty(false, "showDatePicker"),
                            confirmedDateProperty = ConfirmedDateProperty(id = "confirmedDate"),
                            onConfirmAction = toBooleanAction("showDatePicker", false),
                            onCancelAction = toBooleanAction("showDatePicker", false),
                            validators = listOf(
                                millisToDateStringValidator(
                                    id = "dateParsed",
                                    required = "confirmedDate",
                                    datePattern = "dd/MM/yyyy"
                                )
                            )
                        ),
                        Row(
                            content = listOf(
                                Text(textProperty = TextProperty(value = "Selecione a data de investimento: ")),
                                Text(textProperty = TextProperty(id = "dateParsed"))
                            )
                        ),
                    )
                )
            )
        )
    }
}