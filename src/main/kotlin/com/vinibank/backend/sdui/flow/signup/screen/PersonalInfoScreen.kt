package com.vinibank.backend.sdui.flow.signup.screen

import com.vini.designsystemsdui.action.backAction
import com.vini.designsystemsdui.action.continueAction
import com.vini.designsystemsdui.component.button
import com.vini.designsystemsdui.component.column
import com.vini.designsystemsdui.component.outlinedButton
import com.vini.designsystemsdui.component.outlinedTextInput
import com.vini.designsystemsdui.component.spacer
import com.vini.designsystemsdui.component.text
import com.vini.designsystemsdui.component.topAppBar
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.EnabledProperty
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
            content =  listOf(
                topAppBar(
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                    title =  listOf(
                        text(textProperty = TextProperty("Informações Pessoais"))
                    )
                ),
                spacer(sizeProperty = SizeProperty(20)),
                outlinedTextInput(
                    textProperty = TextProperty("", "$screenFlowId.nameInput"),
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                    labelProperty = LabelProperty("Nome completo"),
                    validators = listOf(
                        minLengthValidator(
                            id = "$screenFlowId.isNameFilled",
                            idsToValidate = listOf("$screenFlowId.nameInput"),
                            length = 3
                        )
                    )
                ),
                spacer(sizeProperty = SizeProperty(20)),
                outlinedTextInput(
                    textProperty = TextProperty("", "$screenFlowId.documentInput"),
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                    labelProperty = LabelProperty("CPF"),
                    visualTransformationProperty = VisualTransformationProperty(VisualTransformationOption.CpfDocument),
                    keyboardOptionsProperty = KeyboardOptionsProperty(KeyboardOptionsOption.Number),
                    validators = listOf(
                        minLengthValidator(
                            id = "$screenFlowId.isCpfValid",
                            idsToValidate = listOf("$screenFlowId.documentInput"),
                            length = 11
                        )
                    )

                ),
                spacer(sizeProperty = SizeProperty(20)),
                outlinedTextInput(
                    textProperty = TextProperty("", "$screenFlowId.phoneInput"),
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                    labelProperty = LabelProperty("Telefone"),
                    visualTransformationProperty = VisualTransformationProperty(VisualTransformationOption.Phone),
                    keyboardOptionsProperty = KeyboardOptionsProperty(KeyboardOptionsOption.Phone),
                    validators = listOf(
                        minLengthValidator(
                            id = "$screenFlowId.isPhoneFilled",
                            idsToValidate = listOf("$screenFlowId.phoneInput"),
                            length = 11
                        )
                    )
                ),
                column(
                    horizontalAlignmentProperty = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    verticalFillTypeProperty = VerticalFillTypeProperty(VerticalFillTypeOption.Max),
                    weightProperty = WeightProperty(1f),
                    verticalArrangementProperty = VerticalArrangementProperty(VerticalArrangementOption.Bottom),
                    content =  listOf(
                        button(
                            textProperty = TextProperty("Continuar"),
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                            enabledProperty = EnabledProperty(false, "$screenFlowId.continueButton"),
                            onClick = continueAction(
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
                            textProperty = TextProperty("Voltar"),
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                            onClick = backAction()
                        ),
                    )
                ),
                spacer(sizeProperty = SizeProperty(20)),
            )
        )
    }
}