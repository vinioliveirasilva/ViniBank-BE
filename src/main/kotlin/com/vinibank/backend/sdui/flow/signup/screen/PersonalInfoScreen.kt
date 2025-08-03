package com.vinibank.backend.sdui.flow.signup.screen

import com.vini.designsystemsdui.action.backAction
import com.vini.designsystemsdui.action.continueAction
import com.vini.designsystemsdui.component.button
import com.vini.designsystemsdui.component.column
import com.vini.designsystemsdui.component.outlinedButton
import com.vini.designsystemsdui.component.outlinedTextInput
import com.vini.designsystemsdui.component.spacer
import com.vini.designsystemsdui.component.text
import com.vini.designsystemsdui.component.topBar
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.IsEnabledProperty
import com.vini.designsystemsdui.property.KeyboardOptionsProperty
import com.vini.designsystemsdui.property.LabelProperty
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
import com.vini.designsystemsdui.validator.allTrueValidator
import com.vini.designsystemsdui.validator.minLengthValidator
import com.vinibank.backend.sdui.flow.signup.SignUpScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen
import kotlinx.serialization.json.JsonObject
import org.springframework.stereotype.Component

@Component
class PersonalInfoScreen : SignUpScreen {
    override val screenId: String = "PersonalInfo"

    override fun getScreen(request: SdUiRequest): JsonObject? {
        val screenFlowId = "${request.flow}.${screenId}"
        return screen(
            flow = request.flow,
            stage = screenId,
            version = "1",
            template = "",
            shouldCache = false,
            components = listOf(
                topBar(
                    horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    paddingHorizontal = PaddingHorizontalProperty(20),
                    components = listOf(
                        text(textProperty = TextProperty("Informações Pessoais"))
                    )
                ),
                spacer(size = SizeProperty(20)),
                outlinedTextInput(
                    text = TextProperty("", "$screenFlowId.nameInput"),
                    horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    paddingHorizontal = PaddingHorizontalProperty(20),
                    label = LabelProperty("Nome completo"),
                    validators = listOf(
                        minLengthValidator(
                            id = "$screenFlowId.isNameFilled",
                            idsToValidate = listOf("$screenFlowId.nameInput"),
                            length = 3
                        )
                    )
                ),
                spacer(size = SizeProperty(20)),
                outlinedTextInput(
                    text = TextProperty("", "$screenFlowId.documentInput"),
                    horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    paddingHorizontal = PaddingHorizontalProperty(20),
                    label = LabelProperty("CPF"),
                    visualTransformation = VisualTransformationProperty(VisualTransformationOption.CpfDocument),
                    keyboardOptions = KeyboardOptionsProperty(KeyboardOptionsOption.Number),
                    validators = listOf(
                        minLengthValidator(
                            id = "$screenFlowId.isCpfValid",
                            idsToValidate = listOf("$screenFlowId.documentInput"),
                            length = 11
                        )
                    )

                ),
                spacer(size = SizeProperty(20)),
                outlinedTextInput(
                    text = TextProperty("", "$screenFlowId.phoneInput"),
                    horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    paddingHorizontal = PaddingHorizontalProperty(20),
                    label = LabelProperty("Telefone"),
                    visualTransformation = VisualTransformationProperty(VisualTransformationOption.Phone),
                    keyboardOptions = KeyboardOptionsProperty(KeyboardOptionsOption.Phone),
                    validators = listOf(
                        minLengthValidator(
                            id = "$screenFlowId.isPhoneFilled",
                            idsToValidate = listOf("$screenFlowId.phoneInput"),
                            length = 11
                        )
                    )
                ),
                column(
                    horizontalAlignment = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
                    paddingHorizontal = PaddingHorizontalProperty(20),
                    horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    verticalFillType = VerticalFillTypeProperty(VerticalFillTypeOption.Max),
                    weight = WeightProperty(1),
                    verticalArrangement = VerticalArrangementProperty(VerticalArrangementOption.Bottom),
                    components = listOf(
                        button(
                            text = TextProperty("Continuar"),
                            horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                            isEnabled = IsEnabledProperty(false, "$screenFlowId.continueButton"),
                            actions = listOf(
                                continueAction(
                                    flowId = request.flow,
                                    currentScreenId = screenId,
                                    nextScreenId = "Password",
                                    screenData = request.screenData,
                                    screenRequestData = listOf(
                                        "$screenFlowId.nameInput" to "name",
                                        "$screenFlowId.documentInput" to "document",
                                        "$screenFlowId.phoneInput" to "phone"
                                    )
                                )
                            ),
                            validators = listOf(
                                allTrueValidator(
                                    id = "$screenFlowId.continueButton",
                                    toValidate = listOf(
                                        "$screenFlowId.isNameFilled",
                                        "$screenFlowId.isCpfValid",
                                        "$screenFlowId.isPhoneFilled"
                                    )
                                )
                            )
                        ),
                        outlinedButton(
                            text = TextProperty("Voltar"),
                            horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                            actions = listOf(
                                backAction()
                            )
                        ),
                    )
                ),
                spacer(size = SizeProperty(20)),
            )
        )
    }
}