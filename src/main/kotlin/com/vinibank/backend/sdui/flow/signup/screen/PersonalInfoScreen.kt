package com.vinibank.backend.sdui.flow.signup.screen

import com.vini.designsystemsdui.CacheStrategy
import com.vini.designsystemsdui.core.SdUiNode.Template
import com.vini.designsystemsdui.ui.action.BackAction
import com.vini.designsystemsdui.ui.action.ContinueAction
import com.vini.designsystemsdui.ui.component.Button
import com.vini.designsystemsdui.ui.component.Column
import com.vini.designsystemsdui.ui.component.OutlinedButton
import com.vini.designsystemsdui.ui.component.OutlinedTextInput
import com.vini.designsystemsdui.ui.component.Spacer
import com.vini.designsystemsdui.ui.component.Text
import com.vini.designsystemsdui.ui.component.TopAppBar
import com.vini.designsystemsdui.ui.modifier.Modifier
import com.vini.designsystemsdui.ui.modifier.fillMaxHeight
import com.vini.designsystemsdui.ui.modifier.fillMaxWidth
import com.vini.designsystemsdui.ui.modifier.option.HorizontalAlignmentOption
import com.vini.designsystemsdui.ui.modifier.padding
import com.vini.designsystemsdui.ui.modifier.size
import com.vini.designsystemsdui.ui.modifier.option.KeyboardOptionsOption
import com.vini.designsystemsdui.ui.modifier.option.VerticalArrangementOption
import com.vini.designsystemsdui.ui.modifier.option.VisualTransformationOption
import com.vini.designsystemsdui.InteractionId
import com.vini.designsystemsdui.ui.data.ButtonInteractionModel
import com.vini.designsystemsdui.ui.data.OutlinedTextInputInteractionModel
import com.vini.designsystemsdui.ui.modifier.fillMaxSize
import com.vini.designsystemsdui.ui.template.ScreenTemplate
import com.vini.designsystemsdui.ui.validator.allTrueValidator
import com.vini.designsystemsdui.ui.validator.minLengthValidator
import com.vinibank.backend.sdui.flow.UpdateSdUiTemplateRequest
import com.vinibank.backend.sdui.flow.signup.SignUpScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.JsonObject
import org.springframework.stereotype.Component

@Component
class PersonalInfoScreen : SignUpScreen {
    override val screenId: String = "PersonalInfo"

    override fun getScreenUpdate(request: UpdateSdUiTemplateRequest): List<JsonObject> {
        return emptyList()
    }

    override fun getScreen(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? {
        val screenFlowId = "${request.flow}.${screenId}"

        val nameInputId = InteractionId<String>("$screenFlowId.nameInput")
        val isNameFilled = InteractionId<Boolean>("$screenFlowId.isNameFilled")
        val documentInputId = InteractionId<String>("$screenFlowId.documentInput")
        val isDocumentValid = InteractionId<Boolean>("$screenFlowId.isCpfValid")
        val phoneInputId = InteractionId<String>("$screenFlowId.phoneInput")
        val isPhoneFilled = InteractionId<Boolean>("$screenFlowId.isPhoneFilled")

        val isContinueButtonEnabled = InteractionId<Boolean>("$screenFlowId.continueButton")

        return ScreenTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            content = {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    TopAppBar(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20),
                        id = "Salve.topAppBar",
                        cacheStrategy = CacheStrategy.NoCache(),
                        title = {
                            Text(text = "Informações Pessoais")
                        }
                    )
                    Spacer(modifier = Modifier.size(20))
                    OutlinedTextInput(
                        interactionModel = OutlinedTextInputInteractionModel(
                            text = nameInputId,
                        ),
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20),
                        label = {
                            Text(text = "Nome completo")
                        },
                        validators = listOf(
                            minLengthValidator(
                                idWrapper = isNameFilled,
                                idsToValidate = listOf(nameInputId),
                                length = 3
                            )
                        )
                    )
                    Spacer(modifier = Modifier.size(20))
                    OutlinedTextInput(
                        interactionModel = OutlinedTextInputInteractionModel(
                            text = documentInputId,
                        ),
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20),
                        label = {
                            Text(text = "CPF")
                        },
                        visualTransformation = (
                                VisualTransformationOption.CpfDocument()
                                ),
                        keyboardOptions = KeyboardOptionsOption.Number,
                        validators = listOf(
                            minLengthValidator(
                                idWrapper = isDocumentValid,
                                idsToValidate = listOf(documentInputId),
                                length = 11
                            )
                        )
                    )
                    Spacer(modifier = Modifier.size(20))
                    OutlinedTextInput(
                        interactionModel = OutlinedTextInputInteractionModel(
                            text = phoneInputId,
                        ),
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20),
                        label = {
                            Text(text = "Telefone")
                        },
                        visualTransformation = (
                                VisualTransformationOption.Phone()
                                ),
                        keyboardOptions = KeyboardOptionsOption.Phone,
                        validators = listOf(
                            minLengthValidator(
                                idWrapper = isPhoneFilled,
                                idsToValidate = listOf(phoneInputId),
                                length = 11
                            )
                        )
                    )
                    Column(
                        modifier = Modifier.padding(horizontal = 20).fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalAlignment = (
                                HorizontalAlignmentOption.Center()
                                ),
                        verticalArrangement = (
                                VerticalArrangementOption.Bottom()
                                ),
                        content = {
                            Button(
                                interactionModel = ButtonInteractionModel(
                                    enabled = isContinueButtonEnabled
                                ),

                                modifier = Modifier.fillMaxWidth(),
                                content = {
                                    Text(text = "Continuar")
                                },
                                enabled = false,
                                onClickAction = ContinueAction(
                                    flowId = request.flow,
                                    currentScreenId = screenId,
                                    nextScreenId = "Password",
                                    screenData = request.screenData,
                                    screenRequestData = listOf(
                                        nameInputId.id to "name",
                                        documentInputId.id to "document",
                                        phoneInputId.id to "phone"
                                    )
                                ),
                                validators = listOf(
                                    allTrueValidator(
                                        idWrapper = isContinueButtonEnabled,
                                        toValidate = listOf(
                                            isNameFilled,
                                            isDocumentValid,
                                            isPhoneFilled
                                        )
                                    )
                                )
                            )
                            OutlinedButton(
                                modifier = Modifier.fillMaxWidth(),
                                content = {
                                    Text(text = "Voltar")
                                },
                                onClickAction = BackAction()
                            )
                        }
                    )
                    Spacer(modifier = Modifier.size(20))
                }
            }
        )
    }
}
