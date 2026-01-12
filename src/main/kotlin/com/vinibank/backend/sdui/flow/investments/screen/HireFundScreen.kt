package com.vinibank.backend.sdui.flow.investments.screen

import com.vini.designsystemsdui.CacheStrategy
import com.vini.designsystemsdui.SceneStrategy
import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.ToBooleanAction
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Card
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.LazyColumn
import com.vini.designsystemsdui.component.ModalDatePicker
import com.vini.designsystemsdui.component.OutlinedButton
import com.vini.designsystemsdui.component.OutlinedTextInput
import com.vini.designsystemsdui.component.Row
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.component.TopAppBar
import com.vini.designsystemsdui.property.ConfirmedDateProperty
import com.vini.designsystemsdui.property.EnabledProperty
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.KeyboardOptionsProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.ShapeProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.VisibilityProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.KeyboardOptionsOption
import com.vini.designsystemsdui.property.options.ShapeOptions
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.property.util.PropertyIdWrapper
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vini.designsystemsdui.validator.allTrueValidator
import com.vini.designsystemsdui.validator.doubleComparatorValidator
import com.vini.designsystemsdui.validator.millisToDateStringValidator
import com.vini.designsystemsdui.validator.notNullValidator
import com.vini.designsystemsdui.validator.onlyNumberValidator
import com.vinibank.backend.db.FundsDatabase
import com.vinibank.backend.sdui.flow.investments.InvestmentsScreen
import com.vinibank.backend.sdui.flow.investments.toBrl
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.jsonPrimitive
import org.springframework.stereotype.Component

@Component
class HireFundScreen(
    private val fundsDatabase: FundsDatabase,
) : InvestmentsScreen {
    override val screenId: String = "hireFund"

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

    override fun getScreen(request: SdUiRequest): Template? {

        val fundId = request.screenData?.get("fundId")?.jsonPrimitive?.content ?: ""
        val fundInfo = fundsDatabase.getFundInfo(fundId)

        if (fundInfo == null) return null

        val hasMinimumToInvestId = PropertyIdWrapper<Boolean>("valueToInvest")
        val valueToInvestId = PropertyIdWrapper<String>("HireFundMonetaryInput")
        val dateHasBeenSelected = PropertyIdWrapper<Boolean>("dateHasBeenSelected")
        val selectedDateId = PropertyIdWrapper<String>("dateParsed")
        val confirmedDateId = PropertyIdWrapper<Long>("confirmedDateId")
        val showDatePickerId = PropertyIdWrapper<Boolean>("showDatePicker")
        val allValidationConfirmed = PropertyIdWrapper<Boolean>("allValidationConfirmed")

        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            cacheStrategy = CacheStrategy.NoCache(),
            scene = SceneStrategy.DualPanel(id = "1" ),
            content = listOf(
                LazyColumn(
                    verticalArrangementProperty = VerticalArrangementProperty(VerticalArrangementOption.SpaceBetween),
                    weightProperty = WeightProperty(1f),
                    content = listOf(
                        Column(
                            weightProperty = WeightProperty(1f),
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
                                                cardRow("Rentabilidade", fundInfo.rentability),
                                                cardRow("Investimento Mínimo", fundInfo.minimumInvestment.toBrl()),
                                                cardRow("Taxa de Administração", fundInfo.productTax),
                                                cardRow("Risco", fundInfo.riskLevel),
                                                cardRow("Tempo sugerido de permanencia", fundInfo.suggestedTimeStay),
                                                cardRow("Tempo limite para operação", fundInfo.hourLimitToInvestment),
                                            ),
                                        )
                                    )
                                ),
                                Spacer(heightProperty = HeightProperty(10)),
                                OutlinedTextInput(
                                    label = listOf(
                                        Text(
                                            textProperty = TextProperty(value = "Valor da aplicação"),
                                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                                HorizontalFillTypeOption.Max
                                            ),
                                            paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                        )
                                    ),
                                    textProperty = TextProperty(idWrapper = valueToInvestId),
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                    keyboardOptionsProperty = KeyboardOptionsProperty(KeyboardOptionsOption.Number),
                                    prefix = listOf(
                                        Text(
                                            textProperty = TextProperty(value = "R$"),
                                        )
                                    ),
                                    validators = listOf(
                                        onlyNumberValidator(idWrapper = valueToInvestId),
                                        doubleComparatorValidator(
                                            idWrapper = hasMinimumToInvestId,
                                            idsToValidate = listOf(valueToInvestId),
                                            minValue = fundInfo.minimumInvestment,
                                        ),
                                    )
                                ),
                                Spacer(
                                    heightProperty = HeightProperty(10),
                                    visibilityProperty = VisibilityProperty(idWrapper = dateHasBeenSelected, value = false),
                                ),
                                Text(
                                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                    textProperty = TextProperty("Data selecionada:"),
                                    visibilityProperty = VisibilityProperty(idWrapper = dateHasBeenSelected, value = false),
                                ),
                                OutlinedButton(
                                    content = listOf(
                                        Text(
                                            textProperty = TextProperty(
                                                value = "Seleciona a data de investimento",
                                                idWrapper = selectedDateId
                                            )
                                        )
                                    ),
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                    onClick = ToBooleanAction(showDatePickerId, true),
                                    shapeProperty = ShapeProperty(ShapeOptions.Small),
                                ),
                                ModalDatePicker(
                                    visibilityProperty = VisibilityProperty(false, showDatePickerId),
                                    confirmedDateProperty = ConfirmedDateProperty(idWrapper = confirmedDateId),
                                    onConfirmAction = ToBooleanAction(showDatePickerId, false),
                                    onCancelAction = ToBooleanAction(showDatePickerId, false),
                                    validators = listOf(
                                        millisToDateStringValidator(
                                            idWrapper = selectedDateId,
                                            required = confirmedDateId,
                                            datePattern = "dd/MM/yyyy"
                                        ),
                                        notNullValidator(
                                            idWrapper = dateHasBeenSelected,
                                            toValidate = listOf(confirmedDateId),
                                        )
                                    )
                                ),
                            )
                        ),
                        Column(
                            content = listOf(
                                Spacer(heightProperty = HeightProperty(20)),
                                Button(
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                    enabledProperty = EnabledProperty(idWrapper = allValidationConfirmed, value = false),
                                    content = listOf(
                                        Text(
                                            textProperty = TextProperty(value = "Continuar")
                                        )
                                    ),
                                    validators = listOf(
                                        allTrueValidator(
                                            idWrapper = allValidationConfirmed,
                                            toValidate = listOf(dateHasBeenSelected, hasMinimumToInvestId)
                                        )
                                    )
                                ),
                                Spacer(heightProperty = HeightProperty(20)),
                            )
                        )
                    ),
                ),
            )
        )
    }
}