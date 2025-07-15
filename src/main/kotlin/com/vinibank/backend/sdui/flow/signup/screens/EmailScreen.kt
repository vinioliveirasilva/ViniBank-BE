package com.vinibank.backend.sdui.flow.signup.screens

import com.vinibank.backend.sdui.SdUiScreen
import com.vinibank.backend.sdui.flow.ScreenUtil.action
import com.vinibank.backend.sdui.flow.ScreenUtil.component
import com.vinibank.backend.sdui.flow.ScreenUtil.jsonObject
import com.vinibank.backend.sdui.flow.ScreenUtil.property
import com.vinibank.backend.sdui.flow.ScreenUtil.screen
import com.vinibank.backend.sdui.flow.ScreenUtil.validator
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.JsonObject


class EmailScreen(val model: EmailStateModel = EmailStateModel("")) : SdUiScreen {

    @Serializable
    data class EmailStateModel(
        @SerialName("email") val email: String,
        @Transient val isError: Boolean = false
    )

    override fun getScreenModel(screenData: JsonObject?): JsonObject = screen(
        flow = "SignUp",
        stage = "Email",
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
                            property("text", "Email"),
                        )
                    )
                )
            ),
            component(
                type = "lazyColumn",
                properties = listOf(
                    property("verticalArrangement", "SpaceBetween"),
                    property("weight", "1"),
                    property("paddingVertical", "20"),
                ),
                components = listOf(
                    component(
                        type = "outlinedTextInput",
                        properties = listOf(
                            property("text", model.email, "SignUp.Email.emailInput"),
                            property(
                                "isError",
                                "${model.isError}",
                                "SignUp.Email.emailInput.isError"
                            ),
                            property("errorMessage", "Email já cadastrado"),
                            property("horizontalFillType", "Max"),
                            property("paddingHorizontal", "20"),
                            property("label", "Digite seu email")
                        ),
                        validators = listOf(
                            validator(
                                "emailValid",
                                "SignUp.Email.isEmailValid",
                                listOf("SignUp.Email.emailInput")
                            )
                        )
                    ),
                    component(
                        type = "column",
                        properties = listOf(
                            property("horizontalAlignment", "Center"),
                            property("paddingHorizontal", "20"),
                            property("horizontalFillType", "Max"),
                        ),
                        components = listOf(
                            component(
                                type = "button",
                                properties = listOf(
                                    property("text", "Continuar"),
                                    property("enabled", "false", "SignUp.Email.isEmailValid"),
                                    property("horizontalFillType", "Max")
                                ),
                                action = action(
                                    type = "continue",
                                    data = jsonObject(
                                        "flowId" to "SignUp",
                                        "nextScreenId" to "PersonalInfo",
                                        "currentScreenId" to "Email",
                                        "screenRequestData" to jsonObject("SignUp.Email.emailInput" to "email"),
                                        "screenData" to screenData,
                                    )
                                )
                            ),
                            component(
                                type = "outlinedButton",
                                properties = listOf(
                                    property("text", "Fechar"),
                                    property("horizontalFillType", "Max")
                                ),
                                action = action("close")
                            )
                        )
                    )
                )
            )
        )
    )
}