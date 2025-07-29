package com.vinibank.backend.sdui.flow.login

import com.vinibank.backend.User
import com.vinibank.backend.db.userDatabaseInstance
import com.vinibank.backend.sdui.SdUiScreen
import com.vinibank.backend.sdui.flow.ScreenUtil.action
import com.vinibank.backend.sdui.flow.ScreenUtil.component
import com.vinibank.backend.sdui.flow.ScreenUtil.jsonObject
import com.vinibank.backend.sdui.flow.ScreenUtil.multipleActions
import com.vinibank.backend.sdui.flow.ScreenUtil.property
import com.vinibank.backend.sdui.flow.ScreenUtil.screen
import com.vinibank.backend.sdui.flow.ScreenUtil.validator
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import org.springframework.http.ResponseEntity

object LoginController {
    const val IDENTIFIER = "Login"

    val userDb = userDatabaseInstance

    fun getSdUiScreen(
        request: SdUiRequest,
    ) = getRule(request)

    private fun getRule(request: SdUiRequest) = when (request.fromScreen) {
        "InformationInput" -> loginRule(request)
        else -> noRule(request)
    }

    private inline fun <reified T> createModel(model: JsonObject?): T {
        return Json.decodeFromJsonElement<T>(model ?: JsonObject(emptyMap()))
    }

    private fun loginRule(request: SdUiRequest): Pair<JsonObject, ResponseEntity<String>?> {
        val model = createModel<User>(request.screenData)

        val prefetchUser = userDb.users[model.email]
        val user = prefetchUser?.takeIf { it.password == model.password }

        if (user == null) {
            TODO()
        } else {
            return getScreen(request)
        }
    }

    private fun noRule(request: SdUiRequest): Pair<JsonObject, ResponseEntity<String>?> {
        return getScreen(request)
    }


    private fun getScreen(request: SdUiRequest): Pair<JsonObject, ResponseEntity<String>?> =
        when (request.toScreen == "") {
            true -> Pair(loginScreen.getScreenModel(request.screenData), null)
            false -> Pair(successScreen.getScreenModel(request.screenData), null)
        }

    val loginScreen = object : SdUiScreen {
        override fun getScreenModel(screenData: JsonObject?): JsonObject {
            return screen(
                flow = "Login",
                stage = "InformationInput",
                version = "1",
                template = "",
                shouldCache = false,
                components = listOf(
                    component(
                        type = "lazyColumn",
                        properties = listOf(
                            property("horizontalFillType", "Max"),
                            property("verticalArrangement", "SpaceBetween"),
                            property("paddingHorizontal", "30"),
                            property("weight", "1")
                        ),
                        components = listOf(
                            component(
                                type = "column",
                                properties = listOf(),
                                components = listOf(
                                    component(
                                        type = "topAppBar",
                                        properties = listOf(),
                                        components = listOf(
                                            component(
                                                type = "text",
                                                properties = listOf(
                                                    property("text", "Login")
                                                ),
                                            ),
                                        )
                                    ),
                                    component(
                                        type = "spacer",
                                        properties = listOf(
                                            property("height", 20),
                                        ),
                                    ),
                                    component(
                                        type = "outlinedTextInput",
                                        properties = listOf(
                                            property("horizontalFillType", "Max"),
                                            property("label", "Email"),
                                            property(
                                                "text",
                                                "123@123.com",
                                                "Login.InformationInput.Email"
                                            ),
                                        ),
                                        validators = listOf(
                                            validator(
                                                type = "emailValid",
                                                id = "Login.InformationInput.Email.EmailValid",
                                                required = listOf("Login.InformationInput.Email")
                                            )
                                        )
                                    ),
                                    component(
                                        type = "outlinedTextInput",
                                        properties = listOf(
                                            property("horizontalFillType", "Max"),
                                            property("label", "Senha"),
                                            property("keyboardOptions", "Password"),
                                            property(
                                                "text",
                                                "123@123A",
                                                "Login.InformationInput.Password"
                                            ),
                                            property(
                                                "visualTransformation",
                                                "Password",
                                                "Login.InformationInput.Password.VisualTransformation"
                                            ),
                                        ),
                                        validators = listOf(
                                            validator(
                                                type = "minLength",
                                                id = "Login.InformationInput.Password.MinLength",
                                                required = listOf("Login.InformationInput.Password"),
                                                data = jsonObject(
                                                    "length" to 8
                                                )
                                            )
                                        ),
                                        customComponents = arrayOf(
                                            "trailingIcon" to listOf(
                                                component(
                                                    type = "iconButton",
                                                    properties = listOf(
                                                        property(
                                                            "isVisible",
                                                            true,
                                                            "Login.InformationInput.PasswordIsVisible"
                                                        ),
                                                    ),
                                                    components = listOf(
                                                        component(
                                                            type = "icon",
                                                            properties = listOf(
                                                                property("icon", "Visibility"),
                                                            ),
                                                        ),
                                                    ),
                                                    actions = listOf(
                                                        multipleActions(
                                                            listOf(
                                                                action(
                                                                    type = "toString",
                                                                    data = jsonObject(
                                                                        "id" to "Login.InformationInput.Password.VisualTransformation",
                                                                        "value" to "None"
                                                                    ),
                                                                ),
                                                                action(
                                                                    type = "toBoolean",
                                                                    data = jsonObject(
                                                                        "id" to "Login.InformationInput.PasswordIsNotVisible",
                                                                        "value" to true
                                                                    ),
                                                                ),
                                                                action(
                                                                    type = "toBoolean",
                                                                    data = jsonObject(
                                                                        "id" to "Login.InformationInput.PasswordIsVisible",
                                                                        "value" to false
                                                                    ),
                                                                ),
                                                            )
                                                        )
                                                    )
                                                ),
                                                component(
                                                    type = "iconButton",
                                                    properties = listOf(
                                                        property(
                                                            "isVisible",
                                                            false,
                                                            "Login.InformationInput.PasswordIsNotVisible"
                                                        ),
                                                    ),
                                                    components = listOf(
                                                        component(
                                                            type = "icon",
                                                            properties = listOf(
                                                                property("icon", "VisibilityOff"),
                                                            ),
                                                        ),
                                                    ),
                                                    actions = listOf(
                                                        multipleActions(
                                                            listOf(
                                                                action(
                                                                    type = "toString",
                                                                    data = jsonObject(
                                                                        "id" to "Login.InformationInput.Password.VisualTransformation",
                                                                        "value" to "Password"
                                                                    ),
                                                                ),

                                                                action(
                                                                    type = "toBoolean",
                                                                    data = jsonObject(
                                                                        "id" to "Login.InformationInput.PasswordIsVisible",
                                                                        "value" to true
                                                                    ),
                                                                ),
                                                                action(
                                                                    type = "toBoolean",
                                                                    data = jsonObject(
                                                                        "id" to "Login.InformationInput.PasswordIsNotVisible",
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
                            component(
                                type = "column",
                                properties = listOf(),
                                components = listOf(
                                    component(
                                        type = "button",
                                        properties = listOf(
                                            property("text", "Fazer Login"),
                                            property("horizontalFillType", "Max"),
                                            property(
                                                "enabled",
                                                "false",
                                                id = "Login.InformationInput.LoginButton.Enabled"
                                            )
                                        ),
                                        validators = listOf(
                                            validator(
                                                type = "allTrue",
                                                id = "Login.InformationInput.LoginButton.Enabled",
                                                required = listOf(
                                                    "Login.InformationInput.Email.EmailValid",
                                                    "Login.InformationInput.Password.MinLength"
                                                )
                                            )
                                        ),
                                        actions = listOf(
                                            action(
                                                type = "continue",
                                                name = "OnClick",
                                                id = "Login.InformationInput.LoginButton",
                                                data = jsonObject(
                                                    "flowId" to "Login",
                                                    "nextScreenId" to "AuthLogin",
                                                    "currentScreenId" to "InformationInput",
                                                    "screenRequestData" to jsonObject(
                                                        "Login.InformationInput.Email" to "email",
                                                        "Login.InformationInput.Password" to "password"
                                                    ),
                                                    "screenData" to screenData,
                                                )
                                            )
                                        )
                                    ),
                                    component(
                                        type = "spacer",
                                        properties = listOf(
                                            property("height", 10),
                                        ),
                                    ),
                                    component(
                                        type = "elevatedButton",
                                        properties = listOf(
                                            property("text", "Fazer Cadastro"),
                                            property("horizontalFillType", "Max"),
                                        ),
                                        actions = listOf(
                                            action(
                                                type = "navigate",
                                                data = jsonObject(
                                                    "flow" to "SignUp",
                                                    "screenData" to screenData,
                                                    "screenRequestData" to jsonObject(
                                                        "Login.InformationInput.Email" to "email",
                                                        "Login.InformationInput.Password" to "password"
                                                    ),
                                                    "actionId" to "Login.InformationInput.LoginButton",
                                                )
                                            )
                                        )
                                    ),
                                    component(
                                        type = "spacer",
                                        properties = listOf(
                                            property("height", 20),
                                        ),
                                    ),
                                )
                            )
                        )
                    )
                )
            )
        }
    }
    val successScreen = object : SdUiScreen {
        override fun getScreenModel(screenData: JsonObject?): JsonObject {
            return screen(
                flow = "Login",
                stage = "Success",
                version = "1",
                template = "",
                shouldCache = false,
                components = listOf(
                    component(
                        type = "blank",
                        properties = listOf(),
                        actions = listOf(
                            action(
                                type = "businessSuccess",
                                data = jsonObject(
                                    "screenData" to screenData
                                )
                            )
                        )
                    )
                )
            )
        }
    }
}