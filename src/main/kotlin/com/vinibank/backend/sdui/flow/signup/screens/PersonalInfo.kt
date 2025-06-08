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
                    PropertyModel(name = "text", value = "Informações Pessoais")
                ),
                staticProperty = mapOf(
                    "horizontalFillType" to "Max",
                    "paddingHorizontal" to "20",
                    "textAlign" to "Center",
                )
            ),
            ComponentModel(
                type = "spacer",
                staticProperty = mapOf(
                    "size" to "20"
                )
            ),
            ComponentModel(
                type = "textInput",
                dynamicProperty = listOf(
                    PropertyModel(name = "text", value = "", id = "SignUp.PersonalInfo.nameInput")
                ),
                staticProperty = mapOf(
                    "horizontalFillType" to "Max",
                    "paddingHorizontal" to "20",
                    "label" to "Nome completo",
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
                staticProperty = mapOf(
                    "size" to "20"
                )
            ),
            ComponentModel(
                type = "textInput",
                dynamicProperty = listOf(
                    PropertyModel(name = "text", value = "", id = "SignUp.PersonalInfo.documentInput")
                ),
                staticProperty = mapOf(
                    "horizontalFillType" to "Max",
                    "paddingHorizontal" to "20",
                    "label" to "CPF",
                    "textFormatter" to "Documento.CPF"
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
                staticProperty = mapOf(
                    "size" to "20"
                )
            ),
            ComponentModel(
                type = "textInput",
                dynamicProperty = listOf(
                    PropertyModel(name = "text", value = "", id = "SignUp.PersonalInfo.phoneInput")
                ),
                staticProperty = mapOf(
                    "horizontalFillType" to "Max",
                    "paddingHorizontal" to "20",
                    "label" to "Telefone",
                    "textFormatter" to "Telefone",
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
                staticProperty = mapOf(
                    "horizontalAlignment" to "Center",
                    "paddingHorizontal" to "20",
                    "horizontalFillType" to "Max",
                    "weight" to "1",
                    "verticalArrangement" to "Bottom",
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
                        ),
                        staticProperty = mapOf(
                            "horizontalFillType" to "Max"
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
                        type = "button",
                        dynamicProperty = listOf(
                            PropertyModel(name = "text", value = "Voltar")
                        ),
                        staticProperty = mapOf(
                            "horizontalFillType" to "Max"
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
                staticProperty = mapOf(
                    "size" to "20"
                )
            )
        )
    )
}