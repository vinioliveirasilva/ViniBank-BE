package com.vinibank.backend.sdui.flow.signup.screen

import com.vinibank.backend.sdui.flow.signup.SignUpScreen
import com.vinibank.backend.sdui.oldflow.ScreenUtil.action
import com.vinibank.backend.sdui.oldflow.ScreenUtil.component
import com.vinibank.backend.sdui.oldflow.ScreenUtil.jsonObject
import com.vinibank.backend.sdui.oldflow.ScreenUtil.property
import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen
import com.vinibank.backend.sdui.oldflow.ScreenUtil.validator
import com.vinibank.backend.sdui.model.SdUiRequest
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
                component(
                    type = "topAppBar",
                    properties = listOf(
                        property("horizontalFillType", "Max"),
                        property("paddingHorizontal", "20"),
                        property("textAlign", "Center")
                    ),
                    components = listOf(
                        component(
                            type = "text",
                            properties = listOf(
                                property("text", "Informações Pessoais"),
                            )
                        )
                    )
                ),
                component(
                    type = "spacer",
                    properties = listOf(
                        property("size", "20")
                    )
                ),
                component(
                    type = "outlinedTextInput",
                    properties = listOf(
                        property("text", "", "$screenFlowId.nameInput"),
                        property("horizontalFillType", "Max"),
                        property("paddingHorizontal", "20"),
                        property("label", "Nome completo")
                    ),
                    validators = listOf(
                        validator(
                            type = "minLength",
                            id = "$screenFlowId.isNameFilled",
                            required = listOf("$screenFlowId.nameInput"),
                            data = jsonObject("length" to "3")
                        )
                    )
                ),
                component(
                    type = "spacer",
                    properties = listOf(
                        property("size", "20")
                    )
                ),
                component(
                    type = "outlinedTextInput",
                    properties = listOf(
                        property("text", "", "$screenFlowId.documentInput"),
                        property("horizontalFillType", "Max"),
                        property("paddingHorizontal", "20"),
                        property("label", "CPF"),
                        property("visualTransformation", "CpfDocument"),
                        property("keyboardOptions", "Number")
                    ),
                    validators = listOf(
                        validator(
                            type = "minLength",
                            id = "$screenFlowId.isCpfValid",
                            required = listOf("$screenFlowId.documentInput"),
                            data = jsonObject("length" to "11")
                        )
                    )
                ),
                component(
                    type = "spacer",
                    properties = listOf(
                        property("size", "20")
                    )
                ),
                component(
                    type = "outlinedTextInput",
                    properties = listOf(
                        property("text", "", "$screenFlowId.phoneInput"),
                        property("horizontalFillType", "Max"),
                        property("paddingHorizontal", "20"),
                        property("label", "Telefone"),
                        property("visualTransformation", "Phone"),
                        property("keyboardOptions", "Phone")
                    ),
                    validators = listOf(
                        validator(
                            type = "minLength",
                            id = "$screenFlowId.isPhoneFilled",
                            required = listOf("$screenFlowId.phoneInput"),
                            data = jsonObject("length" to "11")
                        )
                    )
                ),
                component(
                    type = "column",
                    properties = listOf(
                        property("horizontalAlignment", "Center"),
                        property("paddingHorizontal", "20"),
                        property("horizontalFillType", "Max"),
                        property("verticalFillType", "Max"),
                        property("weight", "1"),
                        property("verticalArrangement", "Bottom")
                    ),
                    components = listOf(
                        component(
                            type = "button",
                            properties = listOf(
                                property("text", "Continuar"),
                                property("enabled", "true", "$screenFlowId.continueButton1"),
                                property("horizontalFillType", "Max")
                            ),
                            actions = listOf(
                                action(
                                    type = "continue",
                                    data = jsonObject(
                                        "flowId" to request.flow,
                                        "nextScreenId" to "Password",
                                        "currentScreenId" to screenId,
                                        "screenRequestData" to jsonObject(
                                            "$screenFlowId.nameInput" to "name",
                                            "$screenFlowId.documentInput" to "document",
                                            "$screenFlowId.phoneInput" to "phone"
                                        ),
                                        "screenData" to request.screenData,
                                    )
                                )
                            ),
                            validators = listOf(
                                validator(
                                    type = "allTrue",
                                    id = "$screenFlowId.continueButton",
                                    required = listOf(
                                        "$screenFlowId.isNameFilled",
                                        "$screenFlowId.isCpfValid",
                                        "$screenFlowId.isPhoneFilled"
                                    )
                                )
                            )
                        ),
                        component(
                            type = "outlinedButton",
                            properties = listOf(
                                property("text", "Voltar"),
                                property("horizontalFillType", "Max")
                            ),
                            actions = listOf(
                                action(type = "back")
                            )
                        )
                    )
                ),
                component(
                    type = "spacer",
                    properties = listOf(
                        property("size", "20")
                    )
                )
            )
        )
    }
}