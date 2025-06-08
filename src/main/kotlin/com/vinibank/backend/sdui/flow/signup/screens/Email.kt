package com.vinibank.backend.sdui.flow.signup.screens

import com.vinibank.backend.sdui.SdUiScreen
import com.vinibank.backend.sdui.model.ActionModel
import com.vinibank.backend.sdui.model.ComponentModel
import com.vinibank.backend.sdui.model.PropertyModel
import com.vinibank.backend.sdui.model.ScreenModel
import com.vinibank.backend.sdui.model.ValidatorModel

class Email(val isError: Boolean = false) : SdUiScreen {
    override fun getScreenModel(screenData: String): ScreenModel = ScreenModel(
        flow = "SignUp",
        stage = "Email",
        version = "1",
        template = "",
        shouldCache = false,
        components = listOf(
            ComponentModel(
                type = "topAppBar",
                dynamicProperty = listOf(
                    PropertyModel(name = "text", value = "Email")
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
                    PropertyModel(name = "text", value = "", id = "SignUp.Email.emailInput"),
                ),
                staticProperty = mutableMapOf(
                    "horizontalFillType" to "Max",
                    "paddingHorizontal" to "20",
                    "label" to "Digite seu email",
                ).apply {
                    if(isError) {
                        this["isError"] = "true"
                        this["errorMessage"] = "Email já cadastrado"
                    }
                },
                validators = listOf(
                    ValidatorModel(
                        type = "emailValid",
                        id = "SignUp.Email.isEmailValid",
                        required = listOf(
                            "SignUp.Email.emailInput",
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
                                id = "SignUp.Email.isEmailValid"
                            ),
                        ),
                        staticProperty = mapOf(
                            "horizontalFillType" to "Max"
                        ),
                        action = ActionModel(
                            type = "continue",
                            data = mapOf(
                                "flowId" to "SignUp",
                                "nextScreenId" to "PersonalInfo",
                                "currentScreenId" to "Email",
                                "screenRequestData" to """{ "SignUp.Email.emailInput" : "email" }""",
                                "screenData" to screenData
                            ),
                        )
                    ),
                    ComponentModel(
                        type = "button",
                        dynamicProperty = listOf(
                            PropertyModel(name = "text", value = "Fechar")
                        ),
                        staticProperty = mapOf(
                            "horizontalFillType" to "Max"
                        ),
                        action = ActionModel(
                            type = "close"
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