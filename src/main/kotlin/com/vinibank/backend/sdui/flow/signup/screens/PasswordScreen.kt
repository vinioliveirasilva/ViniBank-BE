package com.vinibank.backend.sdui.flow.signup.screens

import com.vinibank.backend.sdui.SdUiScreen
import org.json.JSONObject

class PasswordScreen : SdUiScreen {
    override fun getScreenModel(screenData: String) = JSONObject(
        mapOf(
            "flow" to "SignUp",
            "stage" to "Password",
            "version" to "1",
            "template" to "",
            "shouldCache" to true,
            "components" to listOf(
                mapOf(
                    "type" to "topAppBar",
                    "properties" to listOf(
                        mapOf("name" to "horizontalFillType", "value" to "Max"),
                        mapOf("name" to "paddingHorizontal", "value" to "20"),
                        mapOf("name" to "textAlign", "value" to "Center"),
                    ),
                    "components" to listOf(
                        mapOf(
                            "type" to "text",
                            "properties" to listOf(
                                mapOf("name" to "text", "value" to "Criar Senha"),
                            ),
                        )
                    )
                ),
                mapOf(
                    "type" to "spacer",
                    "properties" to listOf(
                        mapOf("name" to "size", "value" to "20")
                    )
                ),
                mapOf(
                    "type" to "createPassword",
                    "properties" to listOf(
                        mapOf(
                            "name" to "isPasswordValid",
                            "value" to "false",
                            "id" to "SignUp.PasswordScreen.isPasswordValid"
                        ),
                        mapOf(
                            "name" to "text",
                            "value" to "",
                            "id" to "SignUp.PasswordScreen.passwordInput"
                        ),
                        mapOf("name" to "horizontalFillType", "value" to "Max"),
                        mapOf("name" to "paddingHorizontal", "value" to "20"),
                    )
                ),
                mapOf(
                    "type" to "column",
                    "properties" to listOf(
                        mapOf("name" to "horizontalAlignment", "value" to "Center"),
                        mapOf("name" to "paddingHorizontal", "value" to "20"),
                        mapOf("name" to "horizontalFillType", "value" to "Max"),
                        mapOf("name" to "verticalFillType", "value" to "Max"),
                        mapOf("name" to "weight", "value" to "1"),
                        mapOf("name" to "verticalArrangement", "value" to "Bottom"),
                    ),
                    "components" to listOf(
                        mapOf(
                            "type" to "button",
                            "properties" to listOf(
                                mapOf("name" to "text", "value" to "Continuar"),
                                mapOf(
                                    "name" to "enabled",
                                    "value" to "false",
                                    "id" to "SignUp.PasswordScreen.isPasswordValid"
                                ),
                                mapOf("name" to "horizontalFillType", "value" to "Max")
                            ),
                            "action" to mapOf(
                                "type" to "continue",
                                "data" to mapOf(
                                    "flowId" to "SignUp",
                                    "nextScreenId" to "Success",
                                    "currentScreenId" to "Password",
                                    "screenRequestData" to """{ "SignUp.PasswordScreen.passwordInput" : "password" }""",
                                    "screenData" to screenData
                                ),
                            )
                        ),
                        mapOf(
                            "type" to "outlinedButton",
                            "properties" to listOf(
                                mapOf("name" to "text", "value" to "Voltar"),
                                mapOf("name" to "horizontalFillType", "value" to "Max")
                            ),
                            "action" to mapOf(
                                "type" to "back"
                            )
                        )
                    )
                ),
                mapOf(
                    "type" to "spacer",
                    "properties" to listOf(
                        mapOf("name" to "size", "value" to "20")
                    )
                )
            )
        )
    )
}