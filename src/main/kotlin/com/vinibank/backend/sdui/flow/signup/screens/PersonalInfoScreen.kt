package com.vinibank.backend.sdui.flow.signup.screens

import com.vinibank.backend.sdui.SdUiScreen
import com.vinibank.backend.sdui.flow.ScreenUtil.action
import com.vinibank.backend.sdui.flow.ScreenUtil.component
import com.vinibank.backend.sdui.flow.ScreenUtil.jsonObject
import com.vinibank.backend.sdui.flow.ScreenUtil.property
import com.vinibank.backend.sdui.flow.ScreenUtil.screen
import com.vinibank.backend.sdui.flow.ScreenUtil.validator
import kotlinx.serialization.json.JsonObject

class PersonalInfoScreen : SdUiScreen {
    override fun getScreenModel(screenData: JsonObject?): JsonObject = screen(
        flow = "SignUp",
        stage = "PersonalInfo",
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
                    property("text", "", "SignUp.PersonalInfo.nameInput"),
                    property("horizontalFillType", "Max"),
                    property("paddingHorizontal", "20"),
                    property("label", "Nome completo")
                ),
                validators = listOf(
                    validator(
                        type = "minLength",
                        id = "SignUp.PersonalInfo.isNameFilled",
                        required = listOf("SignUp.PersonalInfo.nameInput"),
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
                    property("text", "", "SignUp.PersonalInfo.documentInput"),
                    property("horizontalFillType", "Max"),
                    property("paddingHorizontal", "20"),
                    property("label", "CPF"),
                    property("textFormatter", "Documento.CPF")
                ),
                validators = listOf(
                    validator(
                        type = "minLength",
                        id = "SignUp.PersonalInfo.isCpfValid",
                        required = listOf("SignUp.PersonalInfo.documentInput"),
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
                    property("text", "", "SignUp.PersonalInfo.phoneInput"),
                    property("horizontalFillType", "Max"),
                    property("paddingHorizontal", "20"),
                    property("label", "Telefone"),
                    property("textFormatter", "Telefone")
                ),
                validators = listOf(
                    validator(
                        type = "minLength",
                        id = "SignUp.PersonalInfo.isPhoneFilled",
                        required = listOf("SignUp.PersonalInfo.phoneInput"),
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
                            property("enabled", "false", "SignUp.PersonalInfo.continueButton"),
                            property("horizontalFillType", "Max")
                        ),
                        action = action(
                            type = "continue",
                            data = jsonObject(
                                "flowId" to "SignUp",
                                "nextScreenId" to "Password",
                                "currentScreenId" to "PersonalInfo",
                                "screenRequestData" to jsonObject(
                                    "SignUp.PersonalInfo.nameInput" to "name",
                                    "SignUp.PersonalInfo.documentInput" to "document",
                                    "SignUp.PersonalInfo.phoneInput" to "phone"
                                ),
                                "screenData" to screenData,
                            )
                        ),
                        validators = listOf(
                            validator(
                                type = "allTrue",
                                id = "SignUp.PersonalInfo.continueButton",
                                required = listOf(
                                    "SignUp.PersonalInfo.isNameFilled",
                                    "SignUp.PersonalInfo.isCpfValid",
                                    "SignUp.PersonalInfo.isPhoneFilled"
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
                        action = action(type = "back")
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