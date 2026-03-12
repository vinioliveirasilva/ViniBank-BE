package com.vinibank.backend.sdui.flow.investments.screen

import com.vini.designsystemsdui.CacheStrategy
import com.vini.designsystemsdui.SceneStrategy
import com.vini.designsystemsdui.core.SdUiNode.Template
import com.vini.designsystemsdui.ui.action.MultipleActions
import com.vini.designsystemsdui.ui.action.ToModifierAction
import com.vini.designsystemsdui.ui.component.Button
import com.vini.designsystemsdui.ui.component.Card
import com.vini.designsystemsdui.ui.component.Column
import com.vini.designsystemsdui.ui.component.LazyColumn
import com.vini.designsystemsdui.ui.component.ModalDatePicker
import com.vini.designsystemsdui.ui.component.OutlinedButton
import com.vini.designsystemsdui.ui.component.OutlinedTextInput
import com.vini.designsystemsdui.ui.component.Row
import com.vini.designsystemsdui.ui.component.Spacer
import com.vini.designsystemsdui.ui.component.Text
import com.vini.designsystemsdui.ui.component.TopAppBar
import com.vini.designsystemsdui.core.SdUiComposer
import com.vini.designsystemsdui.ui.modifier.Modifier
import com.vini.designsystemsdui.ui.modifier.fillMaxHeight
import com.vini.designsystemsdui.ui.modifier.fillMaxWidth
import com.vini.designsystemsdui.ui.modifier.height
import com.vini.designsystemsdui.ui.modifier.padding
import com.vini.designsystemsdui.ui.modifier.visible
import com.vini.designsystemsdui.ui.modifier.option.HorizontalArrangementOption
import com.vini.designsystemsdui.ui.modifier.option.KeyboardOptionsOption
import com.vini.designsystemsdui.ui.modifier.option.ShapeOption
import com.vini.designsystemsdui.ui.modifier.option.VerticalArrangementOption
import com.vini.designsystemsdui.InteractionId
import com.vini.designsystemsdui.JsonProvider
import com.vini.designsystemsdui.ui.data.ButtonInteractionModel
import com.vini.designsystemsdui.ui.data.ModalDatePickerInteractionModel
import com.vini.designsystemsdui.ui.data.OutlinedTextInputInteractionModel
import com.vini.designsystemsdui.ui.data.TextInteractionModel
import com.vini.designsystemsdui.ui.template.ScreenTemplate
import com.vini.designsystemsdui.ui.validator.allTrueValidator
import com.vini.designsystemsdui.ui.validator.doubleComparatorValidator
import com.vini.designsystemsdui.ui.validator.millisToDateStringValidator
import com.vini.designsystemsdui.ui.validator.notNullValidator
import com.vini.designsystemsdui.ui.validator.onlyNumberValidator
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
            modifier = Modifier.fillMaxWidth(),
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
            InteractionId<Modifier>("dateSelectionInfoVisibility")
        val selectedDateId = InteractionId<String>("dateParsed")
        val confirmedDateId = InteractionId<Long>("confirmedDateId")
        val showDatePickerId = InteractionId<Modifier>("showDatePicker")
        val allValidationConfirmed = InteractionId<Boolean>("allValidationConfirmed")

        return ScreenTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            cacheStrategy = CacheStrategy.NoCache(),
            scene = SceneStrategy.DualPane(id = "1"),
            content = {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = (
                        VerticalArrangementOption.SpaceBetween()
                    ),
                    content = {
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            content = {
                                TopAppBar(
                                    title = {
                                        Text(
                                            text = "Detalhes do Fundo",
                                            fontSize = 18f
                                        )
                                    }
                                )
                                Spacer(modifier = Modifier.height(10))
                                Card(
                                    modifier = Modifier.padding(horizontal = 20)
                                        .fillMaxWidth(),
                                    content = {
                                        Column(
                                            modifier = Modifier.padding(horizontal = 10)
                                                .padding(vertical = 10),
                                            content = {
                                                Row(
                                                    modifier = Modifier.fillMaxWidth(),
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
                                                Spacer(modifier = Modifier.height(10))
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
                                Spacer(modifier = Modifier.height(10))
                                OutlinedTextInput(
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20),
                                    label = {
                                        Text(
                                            modifier = Modifier.fillMaxWidth()
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
                                    modifier = Modifier.height(10)
                                        .visible(false, dateSelectionInfoVisibilityId),
                                )
                                Text(
                                    modifier = Modifier.padding(horizontal = 20)
                                        .visible(false, dateSelectionInfoVisibilityId),
                                    text = "Data selecionada:",
                                )
                                OutlinedButton(
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20),
                                    content = {
                                        Text(
                                            text = "Seleciona a data de investimento",
                                            interactionModel = TextInteractionModel(
                                                text = selectedDateId
                                            )
                                        )
                                    },
                                    onClickAction = ToModifierAction(
                                        Modifier.visible(true, showDatePickerId)
                                    ),
                                    shape = ShapeOption.RoundedCorner(8),
                                )
                                ModalDatePicker(
                                    interactionModel = ModalDatePickerInteractionModel(
                                        confirmedDate = confirmedDateId
                                    ),
                                    modifier = Modifier.visible(false, showDatePickerId),
                                    onConfirmAction = MultipleActions(
                                        listOf(
                                            ToModifierAction(
                                                Modifier.visible(false, showDatePickerId)
                                            ),
                                            ToModifierAction(
                                                Modifier.visible(true, dateSelectionInfoVisibilityId)
                                            )
                                        )
                                    ),
                                    onCancelAction = ToModifierAction(
                                        Modifier.visible(false, showDatePickerId)
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
                                Spacer(modifier = Modifier.height(20))
                                Button(
                                    interactionModel = ButtonInteractionModel(
                                        enabled = allValidationConfirmed
                                    ),
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20),
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
                                Spacer(modifier = Modifier.height(20))
                            }
                        )
                    },
                )
            }
        )
    }
}
