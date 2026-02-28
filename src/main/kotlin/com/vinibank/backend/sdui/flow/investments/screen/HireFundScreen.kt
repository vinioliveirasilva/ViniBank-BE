package com.vinibank.backend.sdui.flow.investments.screen

import com.vini.designsystemsdui.CacheStrategy
import com.vini.designsystemsdui.SceneStrategy
import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.MultipleActions
import com.vini.designsystemsdui.action.ToModifierAction
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
import com.vini.designsystemsdui.modifier.BaseModifier
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.height
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.visible
import com.vini.designsystemsdui.property.ConfirmedDateProperty
import com.vini.designsystemsdui.property.EnabledProperty
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.KeyboardOptionsProperty
import com.vini.designsystemsdui.property.ShapeProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
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

    override fun getScreen(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? {

        val fundId = request.screenData?.get("fundId")?.jsonPrimitive?.content ?: ""
        val fundInfo = fundsDatabase.getFundInfo(fundId)

        if (fundInfo == null) return null

        val hasMinimumToInvestId = PropertyIdWrapper<Boolean>("valueToInvest")
        val valueToInvestId = PropertyIdWrapper<String>("HireFundMonetaryInput")
        val dateHasBeenSelected = PropertyIdWrapper<Boolean>("dateHasBeenSelected")
        val dateSelectionInfoVisibilityId =
            PropertyIdWrapper<BaseModifier>("dateSelectionInfoVisibility")
        val selectedDateId = PropertyIdWrapper<String>("dateParsed")
        val confirmedDateId = PropertyIdWrapper<Long>("confirmedDateId")
        val showDatePickerId = PropertyIdWrapper<BaseModifier>("showDatePicker")
        val allValidationConfirmed = PropertyIdWrapper<Boolean>("allValidationConfirmed")

        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            cacheStrategy = CacheStrategy.NoCache(),
            scene = SceneStrategy.DualPane(id = "1"),
            content = listOf(
                LazyColumn(
                    verticalArrangementProperty = VerticalArrangementProperty(
                        VerticalArrangementOption.SpaceBetween
                    ),
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
                                Spacer(modifier = SdUiModifier().height(10)),
                                Card(
                                    modifier = SdUiModifier().padding(horizontal = 20)
                                        .fillMaxWidth(),
                                    content = listOf(
                                        Column(
                                            modifier = SdUiModifier().padding(horizontal = 10)
                                                .padding(vertical = 10),
                                            content = listOf(
                                                Row(
                                                    modifier = SdUiModifier().fillMaxWidth(),
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
                                                Spacer(modifier = SdUiModifier().height(10)),
                                                cardRow("Rentabilidade", fundInfo.rentability),
                                                cardRow(
                                                    "Investimento Mínimo",
                                                    fundInfo.minimumInvestment.toBrl()
                                                ),
                                                cardRow(
                                                    "Taxa de Administração",
                                                    fundInfo.productTax
                                                ),
                                                cardRow("Risco", fundInfo.riskLevel),
                                                cardRow(
                                                    "Tempo sugerido de permanencia",
                                                    fundInfo.suggestedTimeStay
                                                ),
                                                cardRow(
                                                    "Tempo limite para operação",
                                                    fundInfo.hourLimitToInvestment
                                                ),
                                            ),
                                        )
                                    )
                                ),
                                Spacer(modifier = SdUiModifier().height(10)),
                                OutlinedTextInput(
                                    modifier = SdUiModifier().fillMaxWidth()
                                        .padding(horizontal = 20),
                                    label = listOf(
                                        Text(
                                            modifier = SdUiModifier().fillMaxWidth()
                                                .padding(horizontal = 20),
                                            textProperty = TextProperty(value = "Valor da aplicação"),
                                        )
                                    ),
                                    textProperty = TextProperty(idWrapper = valueToInvestId),
                                    keyboardOptionsProperty = KeyboardOptionsProperty(
                                        KeyboardOptionsOption.Number
                                    ),
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
                                    modifier = SdUiModifier().height(10)
                                        .visible(false, dateSelectionInfoVisibilityId),
                                ),
                                Text(
                                    modifier = SdUiModifier().padding(horizontal = 20)
                                        .visible(false, dateSelectionInfoVisibilityId),
                                    textProperty = TextProperty("Data selecionada:"),
                                ),
                                OutlinedButton(
                                    modifier = SdUiModifier().fillMaxWidth()
                                        .padding(horizontal = 20),
                                    content = listOf(
                                        Text(
                                            textProperty = TextProperty(
                                                value = "Seleciona a data de investimento",
                                                idWrapper = selectedDateId
                                            )
                                        )
                                    ),
                                    onClick = ToModifierAction(
                                        SdUiModifier().visible(true, showDatePickerId)
                                    ),
                                    shapeProperty = ShapeProperty(ShapeOptions.Small),
                                ),
                                ModalDatePicker(
                                    modifier = SdUiModifier().visible(false, showDatePickerId),
                                    confirmedDateProperty = ConfirmedDateProperty(idWrapper = confirmedDateId),
                                    onConfirmAction = MultipleActions(
                                        listOf(
                                            ToModifierAction(
                                                SdUiModifier().visible(false, showDatePickerId)
                                            ),
                                            ToModifierAction(
                                                SdUiModifier().visible(
                                                    true,
                                                    dateSelectionInfoVisibilityId
                                                )
                                            )
                                        )
                                    ),
                                    onCancelAction = ToModifierAction(
                                        SdUiModifier().visible(false, showDatePickerId)
                                    ),
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
                                Spacer(modifier = SdUiModifier().height(20)),
                                Button(
                                    modifier = SdUiModifier().fillMaxWidth()
                                        .padding(horizontal = 20),
                                    enabledProperty = EnabledProperty(
                                        idWrapper = allValidationConfirmed,
                                        value = false
                                    ),
                                    content = listOf(
                                        Text(
                                            textProperty = TextProperty(value = "Continuar")
                                        )
                                    ),
                                    validators = listOf(
                                        allTrueValidator(
                                            idWrapper = allValidationConfirmed,
                                            toValidate = listOf(
                                                dateHasBeenSelected,
                                                hasMinimumToInvestId
                                            )
                                        )
                                    )
                                ),
                                Spacer(modifier = SdUiModifier().height(20)),
                            )
                        )
                    ),
                ),
            )
        )
    }
}
