package com.vinibank.backend.sdui.flow.signup.screen

import com.vinibank.backend.db.UserDatabase
import com.vinibank.backend.sdui.flow.signup.SignUpScreen
import com.vinibank.backend.sdui.oldflow.ScreenUtil
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import org.springframework.stereotype.Component

@Component
class PasswordScreen(
    private val userDb: UserDatabase,
) : SignUpScreen {
    override val screenId: String = "Password"

    override fun getRule(request: SdUiRequest): JsonObject? {
        val model = Json.decodeFromJsonElement<PasswordScreenState>(
            request.screenData ?: JsonObject(emptyMap())
        )
        userDb.addUser(model.name, model.email, model.password)
        return null
    }

    override fun getScreen(request: SdUiRequest): JsonObject? {
        val screenFlowId = "${request.flow}.${screenId}"

        return ScreenUtil.screen(
            flow = request.flow,
            stage = screenId,
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
                                ScreenUtil.property("text", "Criar Senha")
                            )
                        )
                    )
                ),
                ScreenUtil.component(
                    type = "spacer",
                    properties = listOf(
                        ScreenUtil.property("size", "20")
                    )
                ),
                ScreenUtil.component(
                    type = "createPassword",
                    properties = listOf(
                        ScreenUtil.property(
                            "isPasswordValid",
                            "false",
                            "$screenFlowId.isPasswordValid"
                        ),
                        ScreenUtil.property("text", "", "$screenFlowId.passwordInput"),
                        ScreenUtil.property("horizontalFillType", "Max"),
                        ScreenUtil.property("paddingHorizontal", "20")
                    )
                ),
                ScreenUtil.component(
                    type = "column",
                    properties = listOf(
                        ScreenUtil.property("horizontalAlignment", "Center"),
                        ScreenUtil.property("paddingHorizontal", "20"),
                        ScreenUtil.property("horizontalFillType", "Max"),
                        ScreenUtil.property("verticalFillType", "Max"),
                        ScreenUtil.property("weight", "1"),
                        ScreenUtil.property("verticalArrangement", "Bottom")
                    ),
                    components = listOf(
                        ScreenUtil.component(
                            type = "button",
                            properties = listOf(
                                ScreenUtil.property("text", "Continuar"),
                                ScreenUtil.property(
                                    "enabled",
                                    "false",
                                    "$screenFlowId.isPasswordValid"
                                ),
                                ScreenUtil.property("horizontalFillType", "Max")
                            ),
                            actions = listOf(
                                ScreenUtil.action(
                                    type = "continue",
                                    data = ScreenUtil.jsonObject(
                                        "flowId" to request.flow,
                                        "nextScreenId" to "Success",
                                        "currentScreenId" to screenId,
                                        "screenRequestData" to ScreenUtil.jsonObject("$screenFlowId.passwordInput" to "password"),
                                        "screenData" to request.screenData,
                                    ),
                                )
                            )
                        ),
                        ScreenUtil.component(
                            type = "outlinedButton",
                            properties = listOf(
                                ScreenUtil.property("text", "Voltar"),
                                ScreenUtil.property("horizontalFillType", "Max")
                            ),
                            actions = listOf(
                                ScreenUtil.action("back")
                            )
                        )
                    )
                ),
                ScreenUtil.component(
                    type = "spacer",
                    properties = listOf(
                        ScreenUtil.property("size", "20")
                    )
                )
            )
        )
    }
}