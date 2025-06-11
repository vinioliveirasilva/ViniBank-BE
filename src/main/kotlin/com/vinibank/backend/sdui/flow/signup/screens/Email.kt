package com.vinibank.backend.sdui.flow.signup.screens

import com.vinibank.backend.sdui.SdUiScreen
import com.vinibank.backend.sdui.model.*

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
                    PropertyModel(name = "text", value = "Email"),
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
                dynamicProperty = mutableListOf(
                    PropertyModel(name = "text", value = "", id = "SignUp.Email.emailInput"),
                    PropertyModel(name = "isError", value = "$isError"),
                    PropertyModel(name = "errorMessage", value = "Email já cadastrado"),
                    PropertyModel(name = "horizontalFillType", value = "Max"),
                    PropertyModel(name = "paddingHorizontal", value = "20"),
                    PropertyModel(name = "label", value = "Digite seu email")
                ),
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
                dynamicProperty = listOf(
                    PropertyModel(name = "weight", value = "1"),
                    PropertyModel(name = "horizontalAlignment", value = "Center"),
                    PropertyModel(name = "paddingHorizontal", value = "20"),
                    PropertyModel(name = "horizontalFillType", value = "Max"),
                    PropertyModel(name = "verticalFillType", value = "Max"),
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
                                id = "SignUp.Email.isEmailValid"
                            ),
                            PropertyModel(name = "horizontalFillType", value = "Max")
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
                        type = "outlinedButton",
                        dynamicProperty = listOf(
                            PropertyModel(name = "text", value = "Fechar"),
                            PropertyModel(name = "horizontalFillType", value = "Max")
                        ),
                        action = ActionModel(
                            type = "close"
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