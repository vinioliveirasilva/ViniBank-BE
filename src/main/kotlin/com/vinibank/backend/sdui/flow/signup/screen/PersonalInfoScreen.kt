package com.vinibank.backend.sdui.flow.signup.screen

import com.vini.designsystemsdui.CacheStrategy
import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.BackAction
import com.vini.designsystemsdui.action.ContinueAction
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.OutlinedButton
import com.vini.designsystemsdui.component.OutlinedTextInput
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.component.TopAppBar
import com.vini.designsystemsdui.property.EnabledProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.KeyboardOptionsProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.SizeProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.VerticalFillTypeProperty
import com.vini.designsystemsdui.property.VisualTransformationProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.KeyboardOptionsOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.property.options.VerticalFillTypeOption
import com.vini.designsystemsdui.property.options.VisualTransformationOption
import com.vini.designsystemsdui.property.util.PropertyIdWrapper
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vini.designsystemsdui.validator.allTrueValidator
import com.vini.designsystemsdui.validator.minLengthValidator
import com.vinibank.backend.sdui.flow.UpdateSdUiTemplateRequest
import com.vinibank.backend.sdui.flow.signup.SignUpScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component

@Component
class PersonalInfoScreen : SignUpScreen {
    override val screenId: String = "PersonalInfo"

    override fun getScreenUpdate(request: UpdateSdUiTemplateRequest): List<com.vini.designsystemsdui.Component> {
        return listOf(
            TopAppBar(
                id = "Salve.topAppBar",
                cacheStrategy = CacheStrategy.AlwaysCache(),
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                paddingHorizontalProperty = PaddingHorizontalProperty(20),
                title = listOf(
                    Text(textProperty = TextProperty("Informações Pessoais"))
                )
            )
        )
    }

    override fun getScreen(request: SdUiRequest): Template? {
        val screenFlowId = "${request.flow}.${screenId}"

        val nameInputId = PropertyIdWrapper<String>("$screenFlowId.nameInput")
        val isNameFilled = PropertyIdWrapper<Boolean>("$screenFlowId.isNameFilled")
        val documentInputId = PropertyIdWrapper<String>("$screenFlowId.documentInput")
        val isDocumentValid = PropertyIdWrapper<Boolean>("$screenFlowId.isCpfValid")
        val phoneInputId = PropertyIdWrapper<String>("$screenFlowId.phoneInput")
        val isPhoneFilled = PropertyIdWrapper<Boolean>("$screenFlowId.isPhoneFilled")

        val isContinueButtonEnabled = PropertyIdWrapper<Boolean>("$screenFlowId.continueButton")

        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            content = listOf(
                TopAppBar(
                    id = "Salve.topAppBar",
                    cacheStrategy = CacheStrategy.NoCache(),
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                    title =  listOf(
                        Text(textProperty = TextProperty("Informações Pessoais"))
                    )
                ),
                Spacer(sizeProperty = SizeProperty(20)),
                OutlinedTextInput(
                    textProperty = TextProperty(idWrapper = nameInputId),
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                    label = listOf(
                        Text(textProperty = TextProperty(value = "Nome completo"))
                    ),
                    validators = listOf(
                        minLengthValidator(
                            idWrapper = isNameFilled,
                            idsToValidate = listOf(nameInputId),
                            length = 3
                        )
                    )
                ),
                Spacer(sizeProperty = SizeProperty(20)),
                OutlinedTextInput(
                    textProperty = TextProperty(idWrapper = documentInputId),
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                    label = listOf(
                        Text(textProperty = TextProperty(value = "CPF"))
                    ),
                    visualTransformationProperty = VisualTransformationProperty(
                        VisualTransformationOption.CpfDocument
                    ),
                    keyboardOptionsProperty = KeyboardOptionsProperty(KeyboardOptionsOption.Number),
                    validators = listOf(
                        minLengthValidator(
                            idWrapper = isDocumentValid,
                            idsToValidate = listOf(documentInputId),
                            length = 11
                        )
                    )
                ),
                Spacer(sizeProperty = SizeProperty(20)),
                OutlinedTextInput(
                    textProperty = TextProperty(idWrapper = phoneInputId),
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                    label = listOf(
                        Text(textProperty = TextProperty(value = "Telefone"))
                    ),
                    visualTransformationProperty = VisualTransformationProperty(
                        VisualTransformationOption.Phone
                    ),
                    keyboardOptionsProperty = KeyboardOptionsProperty(KeyboardOptionsOption.Phone),
                    validators = listOf(
                        minLengthValidator(
                            idWrapper = isPhoneFilled,
                            idsToValidate = listOf(phoneInputId),
                            length = 11
                        )
                    )
                ),
                Column(
                    horizontalAlignmentProperty = HorizontalAlignmentProperty(
                        HorizontalAlignmentOption.Center
                    ),
                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    verticalFillTypeProperty = VerticalFillTypeProperty(VerticalFillTypeOption.Max),
                    weightProperty = WeightProperty(1f),
                    verticalArrangementProperty = VerticalArrangementProperty(
                        VerticalArrangementOption.Bottom
                    ),
                    content = listOf(
                        Button(
                            content = listOf(
                                Text(textProperty = TextProperty(value = "Continuar"))
                            ),
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                HorizontalFillTypeOption.Max
                            ),
                            enabledProperty = EnabledProperty(
                                false,
                                isContinueButtonEnabled
                            ),
                            onClick = ContinueAction(
                                flowId = request.flow,
                                currentScreenId = screenId,
                                nextScreenId = "Password",
                                screenData = request.screenData,
                                screenRequestData = listOf(
                                    "$screenFlowId.nameInput" to "name",
                                    "$screenFlowId.documentInput" to "document",
                                    "$screenFlowId.phoneInput" to "phone"
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
                        ),
                        OutlinedButton(
                            content = listOf(
                                Text(textProperty = TextProperty(value = "Voltar"))
                            ),
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                HorizontalFillTypeOption.Max
                            ),
                            onClick = BackAction()
                        ),
                    )
                ),
                Spacer(sizeProperty = SizeProperty(20)),
            )
        )
    }
}