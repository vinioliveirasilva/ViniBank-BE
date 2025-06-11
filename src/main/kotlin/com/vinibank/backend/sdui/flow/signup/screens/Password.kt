package com.vinibank.backend.sdui.flow.signup.screens

import com.vinibank.backend.sdui.SdUiScreen
import com.vinibank.backend.sdui.model.ActionModel
import com.vinibank.backend.sdui.model.ComponentModel
import com.vinibank.backend.sdui.model.PropertyModel
import com.vinibank.backend.sdui.model.ScreenModel

class Password : SdUiScreen {
    override fun getScreenModel(screenData: String): ScreenModel = ScreenModel(
        flow = "SignUp",
        stage = "Password",
        version = "1",
        template = "",
        shouldCache = false,
        components = listOf(
            ComponentModel(
                type = "topAppBar",
                dynamicProperty = listOf(
                    PropertyModel(name = "text", value = "Criar Senha"),
                    PropertyModel(name = "horizontalFillType", value = "Max"),
                    PropertyModel(name = "paddingHorizontal", value = "20"),
                    PropertyModel(name = "textAlign", value = "Center"),
                )
            ),
            ComponentModel(
                type = "spacer",
                dynamicProperty = listOf(
                    PropertyModel(name = "size", value = "20")
                )
            ),
            ComponentModel(
                type = "createPassword",
                dynamicProperty = listOf(
                    PropertyModel(
                        name = "isPasswordValid",
                        value = "false",
                        id = "SignUp.PasswordScreen.isPasswordValid"
                    ),
                    PropertyModel(
                        name = "text",
                        value = "",
                        id = "SignUp.PasswordScreen.passwordInput"
                    ),
                    PropertyModel(name = "horizontalFillType", value = "Max"),
                    PropertyModel(name = "paddingHorizontal", value = "20"),
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
                    PropertyModel(name = "verticalArrangement", value = "Bottom"),
                ),
                components = listOf(
                    ComponentModel(
                        type = "button",
                        dynamicProperty = listOf(
                            PropertyModel(name = "text", value = "Continuar"),
                            PropertyModel(
                                name = "enabled",
                                value = "false",
                                id = "SignUp.PasswordScreen.isPasswordValid"
                            ),
                            PropertyModel(name = "horizontalFillType", value = "Max")
                        ),
                        action = ActionModel(
                            type = "continue",
                            data = mapOf(
                                "flowId" to "SignUp",
                                "nextScreenId" to "Success",
                                "currentScreenId" to "Password",
                                "screenRequestData" to """{ "SignUp.PasswordScreen.passwordInput" : "password" }""",
                                "screenData" to screenData
                            ),
                        )
                    ),
                    ComponentModel(
                        type = "outlinedButton",
                        dynamicProperty = listOf(
                            PropertyModel(name = "text", value = "Voltar"),
                            PropertyModel(name = "horizontalFillType", value = "Max")
                        ),
                        action = ActionModel(
                            type = "back"
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