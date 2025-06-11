package com.vinibank.backend.sdui.flow.signup.screens

import com.vinibank.backend.sdui.SdUiScreen
import com.vinibank.backend.sdui.model.ActionModel
import com.vinibank.backend.sdui.model.ComponentModel
import com.vinibank.backend.sdui.model.PropertyModel
import com.vinibank.backend.sdui.model.ScreenModel
import com.vinibank.backend.sdui.model.ValidatorModel

class PersonalInfo : SdUiScreen {
    override fun getScreenModel(screenData: String): ScreenModel = ScreenModel(
        flow = "SignUp",
        stage = "PersonalInfo",
        version = "1",
        template = "",
        shouldCache = false,
        components = listOf(
            ComponentModel(
                type = "topAppBar",
                dynamicProperty = listOf(
                    PropertyModel(name = "text", value = "Informações Pessoais"),
                    PropertyModel(name = "horizontalFillType", value = "Max"),
                    PropertyModel(name = "paddingHorizontal", value = "20"),
                    PropertyModel(name = "textAlign", value = "Center")
                )
            ),
            ComponentModel(
                type = "spacer",
                dynamicProperty = listOf(
                    PropertyModel(name = "size", value = "20")
                )
            ),
            ComponentModel(
                type = "outlinedTextInput",
                dynamicProperty = listOf(
                    PropertyModel(name = "text", value = "", id = "SignUp.PersonalInfo.nameInput"),
                    PropertyModel(name = "horizontalFillType", value = "Max"),
                    PropertyModel(name = "paddingHorizontal", value = "20"),
                    PropertyModel(name = "label", value = "Nome completo")
                ),
                validators = listOf(
                    ValidatorModel(
                        type = "minLength",
                        data = mapOf("length" to "3"),
                        id = "SignUp.PersonalInfo.isNameFilled",
                        required = listOf(
                            "SignUp.PersonalInfo.nameInput",
                        ),
                    )
                )
            ),
            ComponentModel(
                type = "spacer",
                dynamicProperty = listOf(
                    PropertyModel(name = "size", value = "20")
                )
            ),
            ComponentModel(
                type = "outlinedTextInput",
                dynamicProperty = listOf(
                    PropertyModel(name = "text", value = "", id = "SignUp.PersonalInfo.documentInput"),
                    PropertyModel(name = "horizontalFillType", value = "Max"),
                    PropertyModel(name = "paddingHorizontal", value = "20"),
                    PropertyModel(name = "label", value = "CPF"),
                    PropertyModel(name = "textFormatter", value = "Documento.CPF")
                ),
                validators = listOf(
                    ValidatorModel(
                        type = "minLength",
                        id = "SignUp.PersonalInfo.isCpfValid",
                        data = mapOf("length" to "11"),
                        required = listOf(
                            "SignUp.PersonalInfo.documentInput",
                        )
                    )
                )
            ),
            ComponentModel(
                type = "spacer",
                dynamicProperty = listOf(
                    PropertyModel(name = "size", value = "20")
                )
            ),
            ComponentModel(
                type = "outlinedTextInput",
                dynamicProperty = listOf(
                    PropertyModel(name = "text", value = "", id = "SignUp.PersonalInfo.phoneInput"),
                    PropertyModel(name = "horizontalFillType", value = "Max"),
                    PropertyModel(name = "paddingHorizontal", value = "20"),
                    PropertyModel(name = "label", value = "Telefone"),
                    PropertyModel(name = "textFormatter", value = "Telefone")
                ),
                validators = listOf(
                    ValidatorModel(
                        type = "minLength",
                        data = mapOf("length" to "11"),
                        id = "SignUp.PersonalInfo.isPhoneFilled",
                        required = listOf(
                            "SignUp.PersonalInfo.phoneInput",
                        ),
                    )
                )
            ),
            ComponentModel(
                type = "column",
                dynamicProperty = listOf(
                    PropertyModel(name = "horizontalAlignment", value = "Center"),
                    PropertyModel(name = "paddingHorizontal", value = "20"),
                    PropertyModel(name = "horizontalFillType", value = "Max"),
                    PropertyModel(name = "verticalFillType", value = "Max"),
                    PropertyModel(name = "weight", value = "1"),
                    PropertyModel(name = "verticalArrangement", value = "Bottom")
                ),
                components = listOf(
                    ComponentModel(
                        type = "button",
                        dynamicProperty = listOf(
                            PropertyModel(name = "text", value = "Continuar"),
                            PropertyModel(
                                name = "enabled",
                                value = "false",
                                id = "SignUp.PersonalInfo.continueButton"
                            ),
                            PropertyModel(name = "horizontalFillType", value = "Max")
                        ),
                        action = ActionModel(
                            type = "continue",
                            data = mapOf(
                                "flowId" to "SignUp",
                                "nextScreenId" to "Password",
                                "currentScreenId" to "PersonalInfo",
                                "screenRequestData" to """{ 
                                "SignUp.PersonalInfo.nameInput" : "name",
                                "SignUp.PersonalInfo.documentInput" : "document", 
                                "SignUp.PersonalInfo.phoneInput" : "phone" 
                                }""".trimMargin(),
                                "screenData" to screenData
                            ),
                        ),
                        validators = listOf(
                            ValidatorModel(
                                type = "allTrue",
                                id = "SignUp.PersonalInfo.continueButton",
                                required = listOf(
                                    "SignUp.PersonalInfo.isNameFilled",
                                    "SignUp.PersonalInfo.isCpfValid",
                                    "SignUp.PersonalInfo.isPhoneFilled",
                                ),
                            )
                        )
                    ),
                    ComponentModel(
                        type = "outlinedButton",
                        dynamicProperty = listOf(
                            PropertyModel(name = "text", value = "Voltar"),
                            PropertyModel(name = "horizontalFillType", value = "Max")
                        ),
                        action = ActionModel(
                            type = "back",
                            data = mapOf()
                        )
                    )
                )
            ),
            ComponentModel(
                type = "spacer",
                dynamicProperty = listOf(
                    PropertyModel(name = "size", value = "20")
                )
            )
        )
    )
}