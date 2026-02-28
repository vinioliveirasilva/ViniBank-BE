package com.vinibank.backend.sdui.flow.login.screen

import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.CloseApplicationAction
import com.vini.designsystemsdui.action.ContinueAction
import com.vini.designsystemsdui.action.MultipleActions
import com.vini.designsystemsdui.action.NavigateAction
import com.vini.designsystemsdui.action.ToModifierAction
import com.vini.designsystemsdui.action.ToTypeAction
import com.vini.designsystemsdui.component.BackHandler
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.IconButton
import com.vini.designsystemsdui.component.OutlinedTextInput
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.modifier.BaseModifier
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.background
import com.vini.designsystemsdui.modifier.clip
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.height
import com.vini.designsystemsdui.modifier.option.ShapeOption
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.size
import com.vini.designsystemsdui.modifier.visible
import com.vini.designsystemsdui.modifier.width
import com.vini.designsystemsdui.property.ButtonColorsProperty
import com.vini.designsystemsdui.property.EnabledProperty
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.FontWeightProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.KeyboardOptionsProperty
import com.vini.designsystemsdui.property.OutlinedTextFieldColorsProperty
import com.vini.designsystemsdui.property.ShapeProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.VerticalScrollProperty
import com.vini.designsystemsdui.property.VisualTransformationProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.ButtonColorsModel
import com.vini.designsystemsdui.property.options.FontWeightOption
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.IconOption
import com.vini.designsystemsdui.property.options.KeyboardOptionsOption
import com.vini.designsystemsdui.property.options.OutlinedTextFieldColorsModel
import com.vini.designsystemsdui.property.options.ShapeOptions
import com.vini.designsystemsdui.property.options.TextSelectionColorsModel
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.property.options.VisualTransformationOption
import com.vini.designsystemsdui.property.options.color.ColorOption
import com.vini.designsystemsdui.property.util.PropertyIdWrapper
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vini.designsystemsdui.validator.allTrueValidator
import com.vini.designsystemsdui.validator.emailValidator
import com.vini.designsystemsdui.validator.minLengthValidator
import com.vinibank.backend.db.UserDatabase
import com.vinibank.backend.db.UserLoginDb
import com.vinibank.backend.sdui.flow.login.LoginScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component()
class MainLoginScreen(
    @Lazy private val userDb: UserDatabase,
    @Lazy private val userLoginDb: UserLoginDb,
) : LoginScreen {
    override val screenId: String
        get() = "Start"

    override fun getScreen(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? {
        return greenLoginScreen(request)
    }

    private fun greenLoginScreen(request: SdUiRequest): Template {
        val outlinedTextFieldsPadding = 30
        val outlinedTextFieldLabelPadding = outlinedTextFieldsPadding + 20
        val screenFlowId = "${request.flow}.${screenId}"

        val emailInputId = PropertyIdWrapper<String>("$screenFlowId.Email")
        val isEmailValid = PropertyIdWrapper<Boolean>("$screenFlowId.Email.EmailValid")
        val passwordInputId = PropertyIdWrapper<String>("$screenFlowId.Password")
        val passwordVisualTransformation =
            PropertyIdWrapper<VisualTransformationOption>("$screenFlowId.Password.VisualTransformation")
        val isPasswordMinLength = PropertyIdWrapper<Boolean>("$screenFlowId.Email.MinLength")

        val isContinueEnabled = PropertyIdWrapper<Boolean>("$screenFlowId.LoginButton.Enabled")
        val isPasswordVisible = PropertyIdWrapper<BaseModifier>("$screenFlowId.PasswordIsVisible")
        val isPasswordInvisible =
            PropertyIdWrapper<BaseModifier>("$screenFlowId.PasswordIsNotVisible")

        val loginActionId = PropertyIdWrapper<String>("$screenFlowId.LoginAction")

        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            content = listOf(
                BackHandler(onBackAction = CloseApplicationAction()),
                Column(
                    modifier = SdUiModifier().fillMaxWidth()
                        .background(ColorOption.CaribbeanGreen()),
                    verticalScrollProperty = VerticalScrollProperty(true),
                    horizontalAlignmentProperty = HorizontalAlignmentProperty(
                        HorizontalAlignmentOption.Center
                    ),
                    weightProperty = WeightProperty(1f),
                    content = listOf(
                        Spacer(modifier = SdUiModifier().height(20)),
                        Text(
                            modifier = SdUiModifier().padding(vertical = 60),
                            textProperty = TextProperty("Welcome"),
                            fontSizeProperty = FontSizeProperty(30f),
                            fontWeightProperty = FontWeightProperty(FontWeightOption.SemiBold)
                        ),
                        Column(
                            modifier = SdUiModifier().fillMaxWidth()
                                .clip(ShapeOption.RoundedCornerEdges(40, 40))
                                .background(ColorOption.HoneyDew()),
                            weightProperty = WeightProperty(1f),
                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                HorizontalAlignmentOption.Center
                            ),
                            content = listOf(
                                Spacer(modifier = SdUiModifier().height(70)),
                                Text(
                                    modifier = SdUiModifier().fillMaxWidth().padding(
                                        horizontal = outlinedTextFieldLabelPadding
                                    ),
                                    textProperty = TextProperty("Email"),
                                    fontSizeProperty = FontSizeProperty(18f),
                                ),
                                OutlinedTextInput(
                                    modifier = SdUiModifier().fillMaxWidth().padding(
                                        horizontal = outlinedTextFieldsPadding
                                    ),
                                    outlinedTextFieldColorsProperty = OutlinedTextFieldColorsProperty(
                                        OutlinedTextFieldColorsModel(
                                            focusedBorderColor = ColorOption.Transparent(),
                                            unfocusedBorderColor = ColorOption.Transparent(),
                                            focusedContainerColor = ColorOption.LightGreen(),
                                            unfocusedContainerColor = ColorOption.LightGreen(),
                                            focusedPlaceholderColor = ColorOption.Black(),
                                            unfocusedPlaceholderColor = ColorOption.Black(),
                                            cursorColor = ColorOption.CaribbeanGreen(),
                                            textSelectionColors = TextSelectionColorsModel(
                                                backgroundColor = ColorOption.CaribbeanGreen(),
                                                handleColor = ColorOption.CaribbeanGreen()
                                            )
                                        )
                                    ),
                                    shapeProperty = ShapeProperty(ShapeOptions.Circle),
                                    textProperty = TextProperty(
                                        idWrapper = emailInputId,
                                        value = "vinioliveirasilva@outlook.com"
                                    ),
                                    prefix = listOf(
                                        Spacer(modifier = SdUiModifier().width(10))
                                    ),
                                    validators = listOf(
                                        emailValidator(
                                            idWrapper = isEmailValid,
                                            emails = listOf(emailInputId)
                                        ),
                                    ),
                                ),
                                Spacer(modifier = SdUiModifier().height(30)),
                                Text(
                                    modifier = SdUiModifier().fillMaxWidth().padding(
                                        horizontal = outlinedTextFieldLabelPadding
                                    ),
                                    textProperty = TextProperty("Senha"),
                                    fontSizeProperty = FontSizeProperty(18f),
                                ),
                                OutlinedTextInput(
                                    modifier = SdUiModifier().fillMaxWidth().padding(
                                        horizontal = outlinedTextFieldsPadding
                                    ),
                                    outlinedTextFieldColorsProperty = OutlinedTextFieldColorsProperty(
                                        OutlinedTextFieldColorsModel(
                                            focusedBorderColor = ColorOption.Transparent(),
                                            unfocusedBorderColor = ColorOption.Transparent(),
                                            focusedContainerColor = ColorOption.LightGreen(),
                                            unfocusedContainerColor = ColorOption.LightGreen(),
                                            focusedPlaceholderColor = ColorOption.Black(),
                                            unfocusedPlaceholderColor = ColorOption.Black(),
                                            cursorColor = ColorOption.CaribbeanGreen(),
                                            textSelectionColors = TextSelectionColorsModel(
                                                backgroundColor = ColorOption.CaribbeanGreen(),
                                                handleColor = ColorOption.CaribbeanGreen()
                                            )
                                        )
                                    ),
                                    shapeProperty = ShapeProperty(ShapeOptions.Circle),
                                    keyboardOptionsProperty = KeyboardOptionsProperty(
                                        KeyboardOptionsOption.Password
                                    ),
                                    visualTransformationProperty = VisualTransformationProperty(
                                        VisualTransformationOption.Password,
                                        passwordVisualTransformation
                                    ),
                                    textProperty = TextProperty(
                                        idWrapper = passwordInputId,
                                        value = "Vini@123"
                                    ),
                                    validators = listOf(
                                        minLengthValidator(
                                            idWrapper = isPasswordMinLength,
                                            idsToValidate = listOf(passwordInputId),
                                            length = 8
                                        ),
                                    ),
                                    trailingIcon = listOf(
                                        IconButton(
                                            modifier = SdUiModifier().visible(
                                                true,
                                                isPasswordVisible
                                            ),
                                            content = listOf(
                                                Icon(iconNameProperty = IconNameProperty(IconOption.Visibility))
                                            ),
                                            onClick = MultipleActions(
                                                listOf(
                                                    ToTypeAction(
                                                        passwordVisualTransformation,
                                                        VisualTransformationOption.None
                                                    ),
                                                    ToModifierAction(
                                                        newValue = SdUiModifier().visible(
                                                            true,
                                                            isPasswordInvisible
                                                        )
                                                    ),
                                                    ToModifierAction(
                                                        newValue = SdUiModifier().visible(
                                                            false,
                                                            isPasswordVisible
                                                        )
                                                    )
                                                )
                                            )
                                        ),
                                        IconButton(
                                            modifier = SdUiModifier().visible(
                                                false,
                                                isPasswordInvisible
                                            ),
                                            content = listOf(
                                                Icon(
                                                    iconNameProperty = IconNameProperty(
                                                        IconOption.VisibilityOff
                                                    )
                                                )
                                            ),
                                            onClick = MultipleActions(
                                                listOf(
                                                    ToTypeAction(
                                                        passwordVisualTransformation,
                                                        VisualTransformationOption.Password
                                                    ),
                                                    ToModifierAction(
                                                        newValue = SdUiModifier().visible(
                                                            false,
                                                            isPasswordInvisible
                                                        )
                                                    ),
                                                    ToModifierAction(
                                                        newValue = SdUiModifier().visible(
                                                            true,
                                                            isPasswordVisible
                                                        )
                                                    )
                                                )
                                            ),
                                        ),
                                    ),
                                    prefix = listOf(
                                        Spacer(modifier = SdUiModifier().width(10))
                                    )
                                ),
                                Column(
                                    modifier = SdUiModifier().padding(horizontal = 30),
                                    weightProperty = WeightProperty(1f),
                                    verticalArrangementProperty = VerticalArrangementProperty(
                                        VerticalArrangementOption.Bottom
                                    ),
                                    horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                        HorizontalAlignmentOption.Center
                                    ),
                                    content = listOf(
                                        Button(
                                            modifier = SdUiModifier().padding(
                                                horizontal = outlinedTextFieldLabelPadding
                                            ).height(50).fillMaxWidth(),
                                            buttonColorsProperty = ButtonColorsProperty(
                                                ButtonColorsModel(
                                                    contentColor = ColorOption.Black(),
                                                    containerColor = ColorOption.CaribbeanGreen(),
                                                )
                                            ),
                                            content = listOf(
                                                Text(
                                                    textProperty = TextProperty("Fazer Login"),
                                                    fontSizeProperty = FontSizeProperty(16f),
                                                    fontWeightProperty = FontWeightProperty(
                                                        FontWeightOption.SemiBold
                                                    )
                                                )
                                            ),
                                            enabledProperty = EnabledProperty(
                                                false,
                                                isContinueEnabled
                                            ),
                                            validators = listOf(
                                                allTrueValidator(
                                                    idWrapper = isContinueEnabled,
                                                    toValidate = listOf(
                                                        isEmailValid,
                                                        isPasswordMinLength
                                                    )
                                                ),
                                            ),
                                            onClick = ContinueAction(
                                                idWrapper = loginActionId,
                                                flowId = request.flow,
                                                nextScreenId = "Success",
                                                currentScreenId = screenId,
                                                screenRequestData = listOf(
                                                    emailInputId.id to "email",
                                                    passwordInputId.id to "password"
                                                ),
                                                screenData = request.screenData
                                            ),
                                        ),
                                        Spacer(modifier = SdUiModifier().height(10)),
                                        Button(
                                            modifier = SdUiModifier().padding(
                                                horizontal = outlinedTextFieldLabelPadding
                                            ).height(50).fillMaxWidth(),
                                            buttonColorsProperty = ButtonColorsProperty(
                                                ButtonColorsModel(
                                                    containerColor = ColorOption.LightGreen(),
                                                    contentColor = ColorOption.Black(),
                                                )
                                            ),
                                            content = listOf(
                                                Text(
                                                    textProperty = TextProperty("Fazer Cadastro"),
                                                    fontSizeProperty = FontSizeProperty(16f),
                                                    fontWeightProperty = FontWeightProperty(
                                                        FontWeightOption.SemiBold
                                                    )
                                                )
                                            ),
                                            onClick = NavigateAction(
                                                flow = "SignUp",
                                                actionId = loginActionId,
                                                screenData = request.screenData,
                                                screenRequestData = listOf(
                                                    emailInputId.id to "email",
                                                    passwordInputId.id to "password"
                                                )
                                            ),
                                        ),
                                        Spacer(modifier = SdUiModifier().height(20))
                                    )
                                ),
                                Spacer(modifier = SdUiModifier().size(10))
                            )
                        ),
                    )
                )
            )
        )
    }

    override fun getRule(request: SdUiRequest) {
        val model = Json.decodeFromJsonElement<LoginScreenState>(
            request.screenData ?: throw IllegalArgumentException("LoginScreen Model is null")
        )

        val prefetchUser = userDb.users[model.email]
        val user = prefetchUser?.takeIf { it.password == model.password }

        if (user == null) {
            println(model)
        } else {
            userLoginDb.bind(request.sessionId, model.email)
        }
    }
}
