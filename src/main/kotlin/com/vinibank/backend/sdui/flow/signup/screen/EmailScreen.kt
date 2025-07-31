package com.vinibank.backend.sdui.flow.signup.screen

import com.vinibank.backend.db.UserDatabase
import com.vinibank.backend.sdui.flow.signup.SignUpScreen
import com.vinibank.backend.sdui.oldflow.ScreenUtil
import com.vinibank.backend.sdui.model.SdUiError
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import org.springframework.stereotype.Component

@Component()
class EmailScreen(
    private val userDb: UserDatabase,
) : SignUpScreen {
    override val screenId: String = "Email"

    override fun getScreen(request: SdUiRequest): JsonObject? {
        return getInternalScreen(request)
    }

    override fun getRule(request: SdUiRequest): JsonObject? {
        if(request.fromScreen.isBlank()) return null

        val model = Json.decodeFromJsonElement<EmailScreenState>(request.screenData ?: JsonObject(emptyMap()))

        if (userDb.users.any { it.key == model.email }) {
            val response = getInternalScreen(request, model.copy(isError = true))
            throw SdUiError("Email ja cadastrado", 400, response)
        }

        return null
    }


    private fun getInternalScreen(request: SdUiRequest, state: EmailScreenState = EmailScreenState()) = ScreenUtil.screen(
        flow = "SignUp",
        stage = "Email",
        version = "1",
        template = "",
        shouldCache = false,
        components = listOf(
            ScreenUtil.component(
                type = "topAppBar",
                properties = listOf(
                    ScreenUtil.property("horizontalFillType", "Max"),
                    ScreenUtil.property("paddingHorizontal", "20"),
                    ScreenUtil.property("textAlign", "Center")
                ),
                components = listOf(
                    ScreenUtil.component(
                        type = "text",
                        properties = listOf(
                            ScreenUtil.property("text", "Email"),
                        )
                    )
                )
            ),
            ScreenUtil.component(
                type = "lazyColumn",
                properties = listOf(
                    ScreenUtil.property("verticalArrangement", "SpaceBetween"),
                    ScreenUtil.property("weight", "1"),
                    ScreenUtil.property("paddingVertical", "20"),
                ),
                components = listOf(
                    ScreenUtil.component(
                        type = "outlinedTextInput",
                        properties = listOf(
                            ScreenUtil.property("text", state.email, "SignUp.Email.emailInput"),
                            ScreenUtil.property(
                                "isError",
                                "${state.isError}",
                                "SignUp.Email.emailInput.isError"
                            ),
                            ScreenUtil.property("errorMessage", "Email já cadastrado"),
                            ScreenUtil.property("horizontalFillType", "Max"),
                            ScreenUtil.property("paddingHorizontal", "20"),
                            ScreenUtil.property("label", "Digite seu email")
                        ),
                        validators = listOf(
                            ScreenUtil.validator(
                                "emailValid",
                                "SignUp.Email.isEmailValid",
                                listOf("SignUp.Email.emailInput")
                            )
                        )
                    ),
                    ScreenUtil.component(
                        type = "column",
                        properties = listOf(
                            ScreenUtil.property("horizontalAlignment", "Center"),
                            ScreenUtil.property("paddingHorizontal", "20"),
                            ScreenUtil.property("horizontalFillType", "Max"),
                        ),
                        components = listOf(
                            ScreenUtil.component(
                                type = "button",
                                properties = listOf(
                                    ScreenUtil.property("text", "Continuar"),
                                    ScreenUtil.property(
                                        "enabled",
                                        false,
                                        "SignUp.Email.isEmailValid"
                                    ),
                                    ScreenUtil.property("horizontalFillType", "Max")
                                ),
                                actions = listOf(
                                    ScreenUtil.action(
                                        type = "continue",
                                        data = ScreenUtil.jsonObject(
                                            "flowId" to "SignUp",
                                            "nextScreenId" to "PersonalInfo",
                                            "currentScreenId" to "Email",
                                            "screenRequestData" to ScreenUtil.jsonObject("SignUp.Email.emailInput" to "email"),
                                            "screenData" to request.screenData,
                                        )
                                    )
                                )
                            ),
                            ScreenUtil.component(
                                type = "outlinedButton",
                                properties = listOf(
                                    ScreenUtil.property("text", "Fechar"),
                                    ScreenUtil.property("horizontalFillType", "Max")
                                ),
                                actions = listOf(
                                    ScreenUtil.action("close")
                                )
                            )
                        )
                    )
                )
            )
        )
    )
}