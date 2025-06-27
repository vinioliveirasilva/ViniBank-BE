package com.vinibank.backend.sdui.flow.signup.screens

import com.vinibank.backend.sdui.SdUiScreen
import org.json.JSONObject

class PersonalInfoScreen : SdUiScreen {
    override fun getScreenModel(screenData: String) = JSONObject(
        mapOf(
            "flow" to "SignUp",
            "stage" to "PersonalInfo",
            "version" to "1",
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
                                mapOf("name" to "text", "value" to "Informações Pessoais"),
                            )
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
                    "type" to "outlinedTextInput",
                    "properties" to listOf(
                        mapOf(
                            "name" to "text",
                            "value" to "",
                            "id" to "SignUp.PersonalInfo.nameInput"
                        ),
                        mapOf("name" to "horizontalFillType", "value" to "Max"),
                        mapOf("name" to "paddingHorizontal", "value" to "20"),
                        mapOf("name" to "label", "value" to "Nome completo")
                    ),
                    "validators" to listOf(
                        mapOf(
                            "type" to "minLength",
                            "data" to mapOf("length" to "3"),
                            "id" to "SignUp.PersonalInfo.isNameFilled",
                            "required" to listOf(
                                "SignUp.PersonalInfo.nameInput",
                            )
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
                    "type" to "outlinedTextInput",
                    "properties" to listOf(
                        mapOf(
                            "name" to "text",
                            "value" to "",
                            "id" to "SignUp.PersonalInfo.documentInput"
                        ),
                        mapOf("name" to "horizontalFillType", "value" to "Max"),
                        mapOf("name" to "paddingHorizontal", "value" to "20"),
                        mapOf("name" to "label", "value" to "CPF"),
                        mapOf("name" to "textFormatter", "value" to "Documento.CPF")
                    ),
                    "validators" to listOf(
                        mapOf(
                            "type" to "minLength",
                            "data" to mapOf("length" to "11"),
                            "id" to "SignUp.PersonalInfo.isCpfValid",
                            "required" to listOf(
                                "SignUp.PersonalInfo.documentInput",
                            )
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
                    "type" to "outlinedTextInput",
                    "properties" to listOf(
                        mapOf(
                            "name" to "text",
                            "value" to "",
                            "id" to "SignUp.PersonalInfo.phoneInput"
                        ),
                        mapOf("name" to "horizontalFillType", "value" to "Max"),
                        mapOf("name" to "paddingHorizontal", "value" to "20"),
                        mapOf("name" to "label", "value" to "Telefone"),
                        mapOf("name" to "textFormatter", "value" to "Telefone")
                    ),
                    "validators" to listOf(
                        mapOf(
                            "type" to "minLength",
                            "data" to mapOf("length" to "11"),
                            "id" to "SignUp.PersonalInfo.isPhoneFilled",
                            "required" to listOf(
                                "SignUp.PersonalInfo.phoneInput",
                            )
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
                        mapOf("name" to "weight", "value" to "1"),
                        mapOf("name" to "verticalArrangement", "value" to "Bottom")
                    ),
                    "components" to listOf(
                        mapOf(
                            "type" to "button",
                            "properties" to listOf(
                                mapOf("name" to "text", "value" to "Continuar"),
                                mapOf(
                                    "name" to "enabled",
                                    "value" to "false",
                                    "id" to "SignUp.PersonalInfo.continueButton"
                                ),
                                mapOf("name" to "horizontalFillType", "value" to "Max")
                            ),
                            "action" to mapOf(
                                "type" to "continue",
                                "data" to mapOf(
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
                            "validators" to listOf(
                                mapOf(
                                    "type" to "allTrue",
                                    "id" to "SignUp.PersonalInfo.continueButton",
                                    "required" to listOf(
                                        "SignUp.PersonalInfo.isNameFilled",
                                        "SignUp.PersonalInfo.isCpfValid",
                                        "SignUp.PersonalInfo.isPhoneFilled",
                                    )
                                )
                            )
                        ),
                        mapOf(
                            "type" to "outlinedButton",
                            "properties" to listOf(
                                mapOf("name" to "text", "value" to "Voltar"),
                                mapOf("name" to "horizontalFillType", "value" to "Max")
                            ),
                            "action" to mapOf(
                                "type" to "back",
                                "data" to mapOf<String, String>()
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