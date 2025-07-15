package com.vinibank.backend.sdui.flow.signup.screens

import com.vinibank.backend.sdui.SdUiScreen
import com.vinibank.backend.sdui.flow.ScreenUtil.screen
import com.vinibank.backend.sdui.flow.ScreenUtil.property
import com.vinibank.backend.sdui.flow.ScreenUtil.component
import com.vinibank.backend.sdui.flow.ScreenUtil.action
import com.vinibank.backend.sdui.flow.ScreenUtil.jsonObject
import kotlinx.serialization.json.JsonObject

class PasswordScreen : SdUiScreen {
    override fun getScreenModel(screenData: JsonObject?): JsonObject = screen(
        flow = "SignUp",
        stage = "Password",
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
                            property("text", "Criar Senha")
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
                type = "createPassword",
                properties = listOf(
                    property("isPasswordValid", "false", "SignUp.PasswordScreen.isPasswordValid"),
                    property("text", "", "SignUp.PasswordScreen.passwordInput"),
                    property("horizontalFillType", "Max"),
                    property("paddingHorizontal", "20")
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
                            property("enabled", "false", "SignUp.PasswordScreen.isPasswordValid"),
                            property("horizontalFillType", "Max")
                        ),
                        action = action(
                            type = "continue",
                            data = jsonObject(
                                "flowId" to "SignUp",
                                "nextScreenId" to "Success",
                                "currentScreenId" to "Password",
                                "screenRequestData" to jsonObject("SignUp.PasswordScreen.passwordInput" to "password"),
                                "screenData" to screenData,
                            ),
                        )
                    ),
                    component(
                        type = "outlinedButton",
                        properties = listOf(
                            property("text", "Voltar"),
                            property("horizontalFillType", "Max")
                        ),
                        action = action("back")
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