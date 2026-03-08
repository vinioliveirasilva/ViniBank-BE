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
import com.vini.designsystemsdui.core.SdUiComposer
import com.vini.designsystemsdui.modifier.BaseModifier
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.fillMaxHeight
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.height
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.visible
import com.vini.designsystemsdui.modifier.option.HorizontalArrangementOption
import com.vini.designsystemsdui.modifier.option.KeyboardOptionsOption
import com.vini.designsystemsdui.modifier.option.ShapeOption
import com.vini.designsystemsdui.modifier.option.VerticalArrangementOption
import com.vini.designsystemsdui.InteractionId
import com.vini.designsystemsdui.JsonProvider
import com.vini.designsystemsdui.component.ButtonInteractionModel
import com.vini.designsystemsdui.component.ModalDatePickerInteractionModel
import com.vini.designsystemsdui.component.OutlinedTextInputInteractionModel
import com.vini.designsystemsdui.component.TextInteractionModel
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
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.springframework.stereotype.Component

@Component
class HireFundScreen(
    private val fundsDatabase: FundsDatabase,
) : InvestmentsScreen {
    override val screenId: String = "hireFund"

    private fun SdUiComposer.cardRow(label: String, value: String) {
        Row(
            modifier = SdUiModifier().fillMaxWidth(),
            horizontalArrangement = (
                HorizontalArrangementOption.SpaceBetween()
            ),
            content = {
                Text(
                    text = label,
                    fontSize = 16f
                )
                Text(
                    text = value,
                    fontSize = 16f
                )
            }
        )
    }

    override fun getScreen(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? {
        val screenData = JsonProvider.json.encodeToJsonElement(request.screenData)
        val fundId = screenData.jsonObject["fundId"]?.jsonPrimitive?.content ?: ""
        val fundInfo = fundsDatabase.getFundInfo(fundId) ?: return null

        val hasMinimumToInvestId = InteractionId<Boolean>("valueToInvest")
        val valueToInvestId = InteractionId<String>("HireFundMonetaryInput")
        val dateHasBeenSelected = InteractionId<Boolean>("dateHasBeenSelected")
        val dateSelectionInfoVisibilityId =
            InteractionId<BaseModifier>("dateSelectionInfoVisibility")
        val selectedDateId = InteractionId<String>("dateParsed")
        val confirmedDateId = InteractionId<Long>("confirmedDateId")
        val showDatePickerId = InteractionId<BaseModifier>("showDatePicker")
        val allValidationConfirmed = InteractionId<Boolean>("allValidationConfirmed")

        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            cacheStrategy = CacheStrategy.NoCache(),
            scene = SceneStrategy.DualPane(id = "1"),
            content = {
                LazyColumn(
                    modifier = SdUiModifier().fillMaxHeight(),
                    verticalArrangement = (
                        VerticalArrangementOption.SpaceBetween()
                    ),
                    content = {
                        Column(
                            modifier = SdUiModifier().fillMaxHeight(),
                            content = {
                                TopAppBar(
                                    title = {
                                        Text(
                                            text = "Detalhes do Fundo",
                                            fontSize = 18f
                                        )
                                    }
                                )
                                Spacer(modifier = SdUiModifier().height(10))
                                Card(
                                    modifier = SdUiModifier().padding(horizontal = 20)
                                        .fillMaxWidth(),
                                    content = {
                                        Column(
                                            modifier = SdUiModifier().padding(horizontal = 10)
                                                .padding(vertical = 10),
                                            content = {
                                                Row(
                                                    modifier = SdUiModifier().fillMaxWidth(),
                                                    horizontalArrangement = (
                                                        HorizontalArrangementOption.Center()
                                                    ),
                                                    content = {
                                                        Text(
                                                            text = fundInfo.name,
                                                            fontSize = 16f
                                                        )
                                                    }
                                                )
                                                Spacer(modifier = SdUiModifier().height(10))
                                                cardRow("Rentabilidade", fundInfo.rentability)
                                                cardRow(
                                                    "Investimento Mínimo",
                                                    fundInfo.minimumInvestment.toBrl()
                                                )
                                                cardRow(
                                                    "Taxa de Administração",
                                                    fundInfo.productTax
                                                )
                                                cardRow("Risco", fundInfo.riskLevel)
                                                cardRow(
                                                    "Tempo sugerido de permanencia",
                                                    fundInfo.suggestedTimeStay
                                                )
                                                cardRow(
                                                    "Tempo limite para operação",
                                                    fundInfo.hourLimitToInvestment
                                                )
                                            }
                                        )
                                    }
                                )
                                Spacer(modifier = SdUiModifier().height(10))
                                OutlinedTextInput(
                                    modifier = SdUiModifier().fillMaxWidth().padding(horizontal = 20),
                                    label = {
                                        Text(
                                            modifier = SdUiModifier().fillMaxWidth()
                                                .padding(horizontal = 20),
                                            text = "Valor da aplicação",
                                        )
                                    },
                                    interactionModel = OutlinedTextInputInteractionModel(
                                        text = valueToInvestId
                                    ),
                                    keyboardOptions = (
                                        KeyboardOptionsOption.Number
                                    ),
                                    prefix = {
                                        Text(text = "R$")
                                    },
                                    validators = listOf(
                                        onlyNumberValidator(idWrapper = valueToInvestId),
                                        doubleComparatorValidator(
                                            idWrapper = hasMinimumToInvestId,
                                            idsToValidate = listOf(valueToInvestId),
                                            minValue = fundInfo.minimumInvestment,
                                        ),
                                    )
                                )
                                Spacer(
                                    modifier = SdUiModifier().height(10)
                                        .visible(false, dateSelectionInfoVisibilityId),
                                )
                                Text(
                                    modifier = SdUiModifier().padding(horizontal = 20)
                                        .visible(false, dateSelectionInfoVisibilityId),
                                    text = "Data selecionada:",
                                )
                                OutlinedButton(
                                    modifier = SdUiModifier().fillMaxWidth().padding(horizontal = 20),
                                    content = {
                                        Text(
                                            text = "Seleciona a data de investimento",
                                            interactionModel = TextInteractionModel(
                                                text = selectedDateId
                                            )
                                        )
                                    },
                                    onClickAction = ToModifierAction(
                                        SdUiModifier().visible(true, showDatePickerId)
                                    ),
                                    shape = ShapeOption.RoundedCorner(8),
                                )
                                ModalDatePicker(
                                    interactionModel = ModalDatePickerInteractionModel(
                                        confirmedDate = confirmedDateId
                                    ),
                                    modifier = SdUiModifier().visible(false, showDatePickerId),
                                    onConfirmAction = MultipleActions(
                                        listOf(
                                            ToModifierAction(
                                                SdUiModifier().visible(false, showDatePickerId)
                                            ),
                                            ToModifierAction(
                                                SdUiModifier().visible(true, dateSelectionInfoVisibilityId)
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
                                )
                            }
                        )
                        Column(
                            content = {
                                Spacer(modifier = SdUiModifier().height(20))
                                Button(
                                    interactionModel = ButtonInteractionModel(
                                        enabled = allValidationConfirmed
                                    ),
                                    modifier = SdUiModifier().fillMaxWidth().padding(horizontal = 20),
                                    enabled = false,
                                    content = {
                                        Text(text = "Continuar")
                                    },
                                    validators = listOf(
                                        allTrueValidator(
                                            idWrapper = allValidationConfirmed,
                                            toValidate = listOf(
                                                dateHasBeenSelected,
                                                hasMinimumToInvestId
                                            )
                                        )
                                    )
                                )
                                Spacer(modifier = SdUiModifier().height(20))
                            }
                        )
                    },
                )
            }
        )
    }
}
