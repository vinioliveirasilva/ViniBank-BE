package com.vinibank.backend.sdui.flow.login.screen

import com.vinibank.backend.db.UserDatabase
import com.vinibank.backend.db.userDatabaseInstance
import com.vinibank.backend.sdui.flow.login.LoginScreen
import com.vinibank.backend.sdui.oldflow.ScreenUtil
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import org.springframework.stereotype.Component

@Component()
class LoginScreen(
    private val userDb: UserDatabase
) : LoginScreen {
    override val screenId: String
        get() = "InformationInput"

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
                    type = "lazyColumn",
                    properties = listOf(
                        ScreenUtil.property("horizontalFillType", "Max"),
                        ScreenUtil.property("verticalArrangement", "SpaceBetween"),
                        ScreenUtil.property("paddingHorizontal", "30"),
                        ScreenUtil.property("weight", 1)
                    ),
                    components = listOf(
                        ScreenUtil.component(
                            type = "column",
                            properties = listOf(),
                            components = listOf(
                                ScreenUtil.component(
                                    type = "topAppBar",
                                    properties = listOf(),
                                    components = listOf(
                                        ScreenUtil.component(
                                            type = "text",
                                            properties = listOf(
                                                ScreenUtil.property("text", "Login")
                                            ),
                                        ),
                                    )
                                ),
                                ScreenUtil.component(
                                    type = "spacer",
                                    properties = listOf(
                                        ScreenUtil.property("height", 20),
                                    ),
                                ),
                                ScreenUtil.component(
                                    type = "outlinedTextInput",
                                    properties = listOf(
                                        ScreenUtil.property("horizontalFillType", "Max"),
                                        ScreenUtil.property("label", "Email"),
                                        ScreenUtil.property(
                                            "text",
                                            "123@123.com",
                                            "$screenFlowId.Email"
                                        ),
                                    ),
                                    validators = listOf(
                                        ScreenUtil.validator(
                                            type = "emailValid",
                                            id = "$screenFlowId.Email.EmailValid",
                                            required = listOf("$screenFlowId.Email")
                                        )
                                    )
                                ),
                                ScreenUtil.component(
                                    type = "outlinedTextInput",
                                    properties = listOf(
                                        ScreenUtil.property("horizontalFillType", "Max"),
                                        ScreenUtil.property("label", "Senha"),
                                        ScreenUtil.property("keyboardOptions", "Password"),
                                        ScreenUtil.property(
                                            "text",
                                            "123@123A",
                                            "$screenFlowId.Password"
                                        ),
                                        ScreenUtil.property(
                                            "visualTransformation",
                                            "Password",
                                            "$screenFlowId.Password.VisualTransformation"
                                        ),
                                    ),
                                    validators = listOf(
                                        ScreenUtil.validator(
                                            type = "minLength",
                                            id = "$screenFlowId.Password.MinLength",
                                            required = listOf("$screenFlowId.Password"),
                                            data = ScreenUtil.jsonObject(
                                                "length" to 8
                                            )
                                        )
                                    ),
                                    customComponents = arrayOf(
                                        "trailingIcon" to listOf(
                                            ScreenUtil.component(
                                                type = "iconButton",
                                                properties = listOf(
                                                    ScreenUtil.property(
                                                        "isVisible",
                                                        true,
                                                        "$screenFlowId.PasswordIsVisible"
                                                    ),
                                                ),
                                                components = listOf(
                                                    ScreenUtil.component(
                                                        type = "icon",
                                                        properties = listOf(
                                                            ScreenUtil.property(
                                                                "icon",
                                                                "Visibility"
                                                            ),
                                                        ),
                                                    ),
                                                ),
                                                actions = listOf(
                                                    ScreenUtil.multipleActions(
                                                        listOf(
                                                            ScreenUtil.action(
                                                                type = "toString",
                                                                data = ScreenUtil.jsonObject(
                                                                    "id" to "$screenFlowId.Password.VisualTransformation",
                                                                    "value" to "None"
                                                                ),
                                                            ),
                                                            ScreenUtil.action(
                                                                type = "toBoolean",
                                                                data = ScreenUtil.jsonObject(
                                                                    "id" to "$screenFlowId.PasswordIsNotVisible",
                                                                    "value" to true
                                                                ),
                                                            ),
                                                            ScreenUtil.action(
                                                                type = "toBoolean",
                                                                data = ScreenUtil.jsonObject(
                                                                    "id" to "$screenFlowId.PasswordIsVisible",
                                                                    "value" to false
                                                                ),
                                                            ),
                                                        )
                                                    )
                                                )
                                            ),
                                            ScreenUtil.component(
                                                type = "iconButton",
                                                properties = listOf(
                                                    ScreenUtil.property(
                                                        "isVisible",
                                                        false,
                                                        "$screenFlowId.PasswordIsNotVisible"
                                                    ),
                                                ),
                                                components = listOf(
                                                    ScreenUtil.component(
                                                        type = "icon",
                                                        properties = listOf(
                                                            ScreenUtil.property(
                                                                "icon",
                                                                "VisibilityOff"
                                                            ),
                                                        ),
                                                    ),
                                                ),
                                                actions = listOf(
                                                    ScreenUtil.multipleActions(
                                                        listOf(
                                                            ScreenUtil.action(
                                                                type = "toString",
                                                                data = ScreenUtil.jsonObject(
                                                                    "id" to "$screenFlowId.Password.VisualTransformation",
                                                                    "value" to "Password"
                                                                ),
                                                            ),

                                                            ScreenUtil.action(
                                                                type = "toBoolean",
                                                                data = ScreenUtil.jsonObject(
                                                                    "id" to "$screenFlowId.PasswordIsVisible",
                                                                    "value" to true
                                                                ),
                                                            ),
                                                            ScreenUtil.action(
                                                                type = "toBoolean",
                                                                data = ScreenUtil.jsonObject(
                                                                    "id" to "$screenFlowId.PasswordIsNotVisible",
                                                                    "value" to false
                                                                ),
                                                            ),
                                                        )
                                                    )
                                                )
                                            ),
                                        )
                                    )
                                ),
                            )
                        ),
                        ScreenUtil.component(
                            type = "column",
                            properties = listOf(),
                            components = listOf(
                                ScreenUtil.component(
                                    type = "button",
                                    properties = listOf(
                                        ScreenUtil.property("text", "Fazer Login"),
                                        ScreenUtil.property("horizontalFillType", "Max"),
                                        ScreenUtil.property(
                                            "enabled",
                                            "false",
                                            id = "$screenFlowId.LoginButton.Enabled"
                                        )
                                    ),
                                    validators = listOf(
                                        ScreenUtil.validator(
                                            type = "allTrue",
                                            id = "$screenFlowId.LoginButton.Enabled",
                                            required = listOf(
                                                "$screenFlowId.Email.EmailValid",
                                                "$screenFlowId.Password.MinLength"
                                            )
                                        )
                                    ),
                                    actions = listOf(
                                        ScreenUtil.action(
                                            type = "continue",
                                            name = "OnClick",
                                            id = "$screenFlowId.LoginButton",
                                            data = ScreenUtil.jsonObject(
                                                "flowId" to request.flow,
                                                "nextScreenId" to "Success",
                                                "currentScreenId" to screenId,
                                                "screenRequestData" to ScreenUtil.jsonObject(
                                                    "$screenFlowId.Email" to "email",
                                                    "$screenFlowId.Password" to "password"
                                                ),
                                                "screenData" to request.screenData,
                                            )
                                        )
                                    )
                                ),
                                ScreenUtil.component(
                                    type = "spacer",
                                    properties = listOf(
                                        ScreenUtil.property("height", 10),
                                    ),
                                ),
                                ScreenUtil.component(
                                    type = "elevatedButton",
                                    properties = listOf(
                                        ScreenUtil.property("text", "Fazer Cadastro"),
                                        ScreenUtil.property("horizontalFillType", "Max"),
                                    ),
                                    actions = listOf(
                                        ScreenUtil.action(
                                            type = "navigate",
                                            data = ScreenUtil.jsonObject(
                                                "flow" to "SignUp",
                                                "screenData" to request.screenData,
                                                "screenRequestData" to ScreenUtil.jsonObject(
                                                    "$screenFlowId.Email" to "email",
                                                    "$screenFlowId.Password" to "password"
                                                ),
                                                "actionId" to "$screenFlowId.LoginButton",
                                            )
                                        )
                                    )
                                ),
                                ScreenUtil.component(
                                    type = "spacer",
                                    properties = listOf(
                                        ScreenUtil.property("height", 20),
                                    ),
                                ),
                            )
                        )
                    )
                )
            )
        )
    }

    override fun getRule(request: SdUiRequest): JsonObject? {
        val model = Json.Default.decodeFromJsonElement<LoginScreenState>(request.screenData ?: throw IllegalArgumentException("LoginScreen Model is null"))

        val prefetchUser = userDb.users[model.email]
        val user = prefetchUser?.takeIf { it.password == model.password }

        if (user == null) {
            TODO()
        } else {
            return null
        }
    }
}