package com.vinibank.backend.sdui.flow.signup.screens

import com.vinibank.backend.sdui.SdUiScreen
import org.json.JSONObject


class EmailScreen(val isError: Boolean = false) : SdUiScreen {
    override fun getScreenModel(screenData: String) = JSONObject(
        mapOf(
            "flow" to "SignUp",
            "stage" to "Email",
            "version"  to "1",
            "template" to "",
            "shouldCache" to false,
            "components" to listOf(
                mapOf(
                    "type" to "topAppBar",
                    "properties" to listOf(
                        mapOf("name" to "horizontalFillType", "value" to "Max"),
                        mapOf("name" to "paddingHorizontal", "value" to "20"),
                        mapOf("name" to "textAlign", "value" to "Center")
                    ),
                    "components" to listOf(
                        mapOf(
                            "type" to "text",
                            "properties" to listOf(
                                mapOf("name" to "text", "value" to "Email"),
                            ),
                        )
                    )
                ),
                mapOf(
                    "type" to "lazyColumn",
                    "properties" to listOf(
                        mapOf("name" to "verticalArrangement", "value" to "SpaceBetween"),
                        mapOf("name" to "weight", "value" to "1"),
                        mapOf("name" to "paddingVertical", "value" to "20"),
                    ),
                    "components" to listOf(
                        mapOf(
                            "type" to "outlinedTextInput",
                            "properties" to mutableListOf(
                                mapOf(
                                    "name" to "text",
                                    "value" to "",
                                    "id" to "SignUp.Email.emailInput"
                                ),
                                mapOf("name" to "isError", "value" to "$isError"),
                                mapOf("name" to "errorMessage", "value" to "Email já cadastrado"),
                                mapOf("name" to "horizontalFillType", "value" to "Max"),
                                mapOf("name" to "paddingHorizontal", "value" to "20"),
                                mapOf("name" to "label", "value" to "Digite seu email")
                            ),
                            "validators" to listOf(
                                mapOf(
                                    "type" to "emailValid",
                                    "id" to "SignUp.Email.isEmailValid",
                                    "required" to listOf(
                                        "SignUp.Email.emailInput",
                                    ),
                                )
                            )
                        ),
                        mapOf(
                            "type" to "column",
                            "properties" to listOf(
                                mapOf("name" to "horizontalAlignment", "value" to "Center"),
                                mapOf("name" to "paddingHorizontal", "value" to "20"),
                                mapOf("name" to "horizontalFillType", "value" to "Max"),
                                mapOf("name" to "verticalFillType", "value" to "Max"),
                            ),
                            "components" to listOf(
                                mapOf(
                                    "type" to "button",
                                    "properties" to listOf(
                                        mapOf("name" to "text", "value" to "Continuar"),
                                        mapOf(
                                            "name" to "enabled",
                                            "value" to "false",
                                            "id" to "SignUp.Email.isEmailValid"
                                        ),
                                        mapOf("name" to "horizontalFillType", "value" to "Max")
                                    ),
                                    "action" to mapOf(
                                        "type" to "continue",
                                        "data" to mapOf(
                                            "flowId" to "SignUp",
                                            "nextScreenId" to "PersonalInfo",
                                            "currentScreenId" to "Email",
                                            "screenRequestData" to """{ "SignUp.Email.emailInput" : "email" }""",
                                            "screenData" to screenData
                                        ),
                                    )
                                ),
                                mapOf(
                                    "type" to "outlinedButton",
                                    "properties" to listOf(
                                        mapOf("name" to "text", "value" to "Fechar"),
                                        mapOf("name" to "horizontalFillType", "value" to "Max")
                                    ),
                                    "action" to mapOf("type" to "close")
                                )
                            )
                        ),
                    )
                )
            )
        )
    )
}