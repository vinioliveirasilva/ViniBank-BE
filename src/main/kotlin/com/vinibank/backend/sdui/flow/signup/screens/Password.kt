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
                    PropertyModel(name = "text", value = "Criar Senha")
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
                ),
                staticProperty = mapOf(
                    "horizontalFillType" to "Max",
                    "paddingHorizontal" to "20",
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
                                id = "SignUp.PasswordScreen.isPasswordValid"
                            ),
                        ),
                        staticProperty = mapOf(
                            "horizontalFillType" to "Max"
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
                        type = "button",
                        dynamicProperty = listOf(
                            PropertyModel(name = "text", value = "Voltar")
                        ),
                        staticProperty = mapOf(
                            "horizontalFillType" to "Max"
                        ),
                        action = ActionModel(
                            type = "back"
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