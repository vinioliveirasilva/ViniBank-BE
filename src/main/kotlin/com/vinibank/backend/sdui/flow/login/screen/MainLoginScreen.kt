package com.vinibank.backend.sdui.flow.login.screen

import com.vini.designsystemsdui.action.continueAction
import com.vini.designsystemsdui.action.multipleActions
import com.vini.designsystemsdui.action.navigateAction
import com.vini.designsystemsdui.action.toBooleanAction
import com.vini.designsystemsdui.action.toStringAction
import com.vini.designsystemsdui.component.button
import com.vini.designsystemsdui.component.column
import com.vini.designsystemsdui.component.elevatedButton
import com.vini.designsystemsdui.component.icon
import com.vini.designsystemsdui.component.iconButton
import com.vini.designsystemsdui.component.lazyColumn
import com.vini.designsystemsdui.component.outlinedTextInput
import com.vini.designsystemsdui.component.spacer
import com.vini.designsystemsdui.component.text
import com.vini.designsystemsdui.component.topAppBar
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.EnabledProperty
import com.vini.designsystemsdui.property.KeyboardOptionsProperty
import com.vini.designsystemsdui.property.LabelProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.VisibilityProperty
import com.vini.designsystemsdui.property.VisualTransformationProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.KeyboardOptionsOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.property.options.VisualTransformationOption
import com.vini.designsystemsdui.validator.allTrueValidator
import com.vini.designsystemsdui.validator.emailValidator
import com.vini.designsystemsdui.validator.minLengthValidator
import com.vinibank.backend.db.UserDatabase
import com.vinibank.backend.sdui.flow.login.LoginScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import org.springframework.stereotype.Component

@Component()
class MainLoginScreen(
    private val userDb: UserDatabase,
) : LoginScreen {
    override val screenId: String
        get() = "InformationInput"

    override fun getScreen(request: SdUiRequest): JsonObject? {
        val screenFlowId = "${request.flow}.${screenId}"
        return screen(
            flow = request.flow,
            stage = screenId,
            version = "1",
            template = "",
            shouldCache = false,
            content =  listOf(
                lazyColumn(
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    verticalArrangementProperty = VerticalArrangementProperty(VerticalArrangementOption.SpaceBetween),
                    weightProperty = WeightProperty(1f),
                    content =  listOf(
                        column(
                            content =  listOf(
                                topAppBar(title = listOf(text(TextProperty("Login")))),
                                spacer(heightProperty = HeightProperty(20)),
                                outlinedTextInput(
                                    paddingHorizontalProperty = PaddingHorizontalProperty(30),
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    labelProperty = LabelProperty("Email"),
                                    textProperty = TextProperty("123@123.com", "$screenFlowId.Email"),
                                    validators = listOf(
                                        emailValidator(
                                            id = "$screenFlowId.Email.EmailValid",
                                            emails = listOf("$screenFlowId.Email")
                                        ),
                                    )
                                ),
                                outlinedTextInput(
                                    paddingHorizontalProperty = PaddingHorizontalProperty(30),
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    labelProperty = LabelProperty("Senha"),
                                    textProperty = TextProperty("123@123A", "$screenFlowId.Password"),
                                    keyboardOptionsProperty = KeyboardOptionsProperty(KeyboardOptionsOption.Password),
                                    visualTransformationProperty = VisualTransformationProperty(
                                        VisualTransformationOption.Password,
                                        "$screenFlowId.Password.VisualTransformation"
                                    ),
                                    validators = listOf(
                                        minLengthValidator(
                                            id = "$screenFlowId.Password.MinLength",
                                            idsToValidate = listOf("$screenFlowId.Password"),
                                            length = 8
                                        ),
                                    ),
                                    trailingIcon = listOf(
                                        iconButton(
                                            visibilityProperty = VisibilityProperty(
                                                true,
                                                "$screenFlowId.PasswordIsVisible"
                                            ),
                                            content =  listOf(
                                                icon(iconNameProperty = IconNameProperty("Visibility"))
                                            ),
                                            onClick = multipleActions(
                                                listOf(
                                                    toStringAction(
                                                        "$screenFlowId.Password.VisualTransformation",
                                                        "None"
                                                    ),
                                                    toBooleanAction(
                                                        "$screenFlowId.PasswordIsNotVisible",
                                                        true
                                                    ),
                                                    toBooleanAction(
                                                        "$screenFlowId.PasswordIsVisible",
                                                        false
                                                    ),
                                                )
                                            )
                                        ),
                                        iconButton(
                                            visibilityProperty = VisibilityProperty(
                                                false,
                                                "$screenFlowId.PasswordIsNotVisible"
                                            ),
                                            content =  listOf(icon(iconNameProperty = IconNameProperty("VisibilityOff"))),
                                            onClick = multipleActions(
                                                listOf(
                                                    toStringAction(
                                                        "$screenFlowId.Password.VisualTransformation",
                                                        "Password"
                                                    ),
                                                    toBooleanAction(
                                                        "$screenFlowId.PasswordIsVisible",
                                                        true
                                                    ),
                                                    toBooleanAction(
                                                        "$screenFlowId.PasswordIsNotVisible",
                                                        false
                                                    ),
                                                )
                                            ),
                                        ),
                                    )
                                ),
                            )
                        ),
                        column(
                            paddingHorizontalProperty = PaddingHorizontalProperty(30),
                            content =  listOf(
                                button(
                                    textProperty = TextProperty("Fazer Login"),
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    enabledProperty = EnabledProperty(
                                        false,
                                        "$screenFlowId.LoginButton.Enabled"
                                    ),
                                    validators = listOf(
                                        allTrueValidator(
                                            id = "$screenFlowId.LoginButton.Enabled",
                                            toValidate = listOf(
                                                "$screenFlowId.Email.EmailValid",
                                                "$screenFlowId.Password.MinLength"
                                            )
                                        ),
                                    ),
                                    onClick = continueAction(
                                        id = "$screenFlowId.LoginButton",
                                        flowId = request.flow,
                                        nextScreenId = "Success",
                                        currentScreenId = screenId,
                                        screenRequestData = listOf(
                                            "$screenFlowId.Email" to "email",
                                            "$screenFlowId.Password" to "password"
                                        ),
                                        screenData = request.screenData
                                    ),

                                ),
                                spacer(heightProperty = HeightProperty(10)),
                                elevatedButton(
                                    textProperty = TextProperty("Fazer Cadastro"),
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    onClick = navigateAction(
                                        flow = "SignUp",
                                        actionId = "$screenFlowId.LoginButton",
                                        screenData = request.screenData,
                                        screenRequestData = listOf(
                                            "$screenFlowId.Email" to "email",
                                            "$screenFlowId.Password" to "password"
                                        )
                                    ),
                                ),
                                spacer(heightProperty = HeightProperty(20))
                            )
                        )
                    )
                ),
            )
        )
    }

    override fun getRule(request: SdUiRequest): JsonObject? {
        val model = Json.Default.decodeFromJsonElement<LoginScreenState>(
            request.screenData ?: throw IllegalArgumentException("LoginScreen Model is null")
        )

        val prefetchUser = userDb.users[model.email]
        val user = prefetchUser?.takeIf { it.password == model.password }

        if (user == null) {
            TODO()
        } else {
            return null
        }
    }
}