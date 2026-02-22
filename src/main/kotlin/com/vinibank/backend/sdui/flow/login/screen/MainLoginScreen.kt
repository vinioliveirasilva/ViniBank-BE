package com.vinibank.backend.sdui.flow.login.screen

import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.CloseApplicationAction
import com.vini.designsystemsdui.action.ContinueAction
import com.vini.designsystemsdui.action.MultipleActions
import com.vini.designsystemsdui.action.NavigateAction
import com.vini.designsystemsdui.action.ToBooleanAction
import com.vini.designsystemsdui.action.ToTypeAction
import com.vini.designsystemsdui.component.BackHandler
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.IconButton
import com.vini.designsystemsdui.component.OutlinedTextInput
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.property.BackgroundColorProperty
import com.vini.designsystemsdui.property.ButtonColorsProperty
import com.vini.designsystemsdui.property.EnabledProperty
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.FontWeightProperty
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.KeyboardOptionsProperty
import com.vini.designsystemsdui.property.OutlinedTextFieldColorsProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.ShapeProperty
import com.vini.designsystemsdui.property.SizeProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.VerticalScrollProperty
import com.vini.designsystemsdui.property.VisibilityProperty
import com.vini.designsystemsdui.property.VisualTransformationProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.WidthProperty
import com.vini.designsystemsdui.property.options.ButtonColorsModel
import com.vini.designsystemsdui.property.options.color.ColorOption
import com.vini.designsystemsdui.property.options.FontWeightOption
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.IconOption
import com.vini.designsystemsdui.property.options.KeyboardOptionsOption
import com.vini.designsystemsdui.property.options.OutlinedTextFieldColorsModel
import com.vini.designsystemsdui.property.options.ShapeOptions
import com.vini.designsystemsdui.property.options.TextSelectionColorsModel
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.property.options.VisualTransformationOption
import com.vini.designsystemsdui.property.options.color.ColorOptionLegacy
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
    @Lazy private val userLoginDb: UserLoginDb
) : LoginScreen {
    override val screenId: String
        get() = "Start"

    override fun getScreen(request: SdUiRequest, parameters: Map<String, String>, screenId: String): Template? {
        return greenLoginScreen(request)
    }

    private fun greenLoginScreen(request: SdUiRequest): Template {
        val outlinedTextFieldsPadding = 30
        val outlinedTextFieldLabelPadding = outlinedTextFieldsPadding + 20
        val screenFlowId = "${request.flow}.${screenId}"

        val emailInputId = PropertyIdWrapper<String>("$screenFlowId.Email")
        val isEmailValid = PropertyIdWrapper<Boolean>("$screenFlowId.Email.EmailValid")
        val passwordInputId = PropertyIdWrapper<String>("$screenFlowId.Password")
        val passwordVisualTransformation = PropertyIdWrapper<VisualTransformationOption>("$screenFlowId.Password.VisualTransformation")
        val isPasswordMinLength = PropertyIdWrapper<Boolean>("$screenFlowId.Email.MinLength")

        val isContinueEnabled = PropertyIdWrapper<Boolean>("$screenFlowId.LoginButton.Enabled")
        val isPasswordVisible = PropertyIdWrapper<Boolean>("$screenFlowId.PasswordIsVisible")
        val isPasswordInvisible = PropertyIdWrapper<Boolean>("$screenFlowId.PasswordIsNotVisible")

        val loginActionId = PropertyIdWrapper<String>("$screenFlowId.LoginAction")

        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            content = listOf(
                BackHandler(onBackAction = CloseApplicationAction()),
                Column(
                    verticalScrollProperty = VerticalScrollProperty(true),
                    horizontalAlignmentProperty = HorizontalAlignmentProperty(
                        HorizontalAlignmentOption.Center
                    ),
                    backgroundColorProperty = BackgroundColorProperty(ColorOption.CaribbeanGreen()),
                    weightProperty = WeightProperty(1f),
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                        HorizontalFillTypeOption.Max
                    ),
                    content = listOf(
                        Spacer(heightProperty = HeightProperty(20)),
                        Text(
                            paddingVerticalProperty = PaddingVerticalProperty(60),
                            textProperty = TextProperty("Welcome"),
                            fontSizeProperty = FontSizeProperty(30f),
                            fontWeightProperty = FontWeightProperty(FontWeightOption.SemiBold)
                        ),
                        Column(
                            shapeProperty = ShapeProperty(ShapeOptions.CustomTop(40)),
                            weightProperty = WeightProperty(1f),
                            backgroundColorProperty = BackgroundColorProperty(ColorOption.HoneyDew()),
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                HorizontalFillTypeOption.Max
                            ),
                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                HorizontalAlignmentOption.Center
                            ),
                            content = listOf(
                                Spacer(heightProperty = HeightProperty(70)),
                                Text(
                                    textProperty = TextProperty("Email"),
                                    fontSizeProperty = FontSizeProperty(18f),
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(
                                        outlinedTextFieldLabelPadding
                                    )
                                ),
                                OutlinedTextInput(
                                    outlinedTextFieldColorsProperty = OutlinedTextFieldColorsProperty(
                                        OutlinedTextFieldColorsModel(
                                            focusedBorderColor = ColorOptionLegacy.Transparent,
                                            unfocusedBorderColor = ColorOptionLegacy.Transparent,
                                            focusedContainerColor = ColorOptionLegacy.LightGreen,
                                            unfocusedContainerColor = ColorOptionLegacy.LightGreen,
                                            focusedPlaceholderColor = ColorOptionLegacy.Black,
                                            unfocusedPlaceholderColor = ColorOptionLegacy.Black,
                                            cursorColor = ColorOptionLegacy.CaribbeanGreen,
                                            textSelectionColors = TextSelectionColorsModel(
                                                backgroundColor = ColorOptionLegacy.CaribbeanGreen,
                                                handleColor = ColorOptionLegacy.CaribbeanGreen
                                            )
                                        )
                                    ),
                                    shapeProperty = ShapeProperty(ShapeOptions.Circle),
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(
                                        outlinedTextFieldsPadding
                                    ),
                                    textProperty = TextProperty(
                                        idWrapper = emailInputId,
                                        value = "vinioliveirasilva@outlook.com"
                                    ),
                                    prefix = listOf(
                                        Spacer(widthProperty = WidthProperty(10))
                                    ),
                                    validators = listOf(
                                        emailValidator(
                                            idWrapper = isEmailValid,
                                            emails = listOf(emailInputId)
                                        ),
                                    ),
                                ),
                                Spacer(heightProperty = HeightProperty(30)),
                                Text(
                                    textProperty = TextProperty("Senha"),
                                    fontSizeProperty = FontSizeProperty(18f),
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(
                                        outlinedTextFieldLabelPadding
                                    )
                                ),
                                OutlinedTextInput(
                                    outlinedTextFieldColorsProperty = OutlinedTextFieldColorsProperty(
                                        OutlinedTextFieldColorsModel(
                                            focusedBorderColor = ColorOptionLegacy.Transparent,
                                            unfocusedBorderColor = ColorOptionLegacy.Transparent,
                                            focusedContainerColor = ColorOptionLegacy.LightGreen,
                                            unfocusedContainerColor = ColorOptionLegacy.LightGreen,
                                            focusedPlaceholderColor = ColorOptionLegacy.Black,
                                            unfocusedPlaceholderColor = ColorOptionLegacy.Black,
                                            cursorColor = ColorOptionLegacy.CaribbeanGreen,
                                            textSelectionColors = TextSelectionColorsModel(
                                                backgroundColor = ColorOptionLegacy.CaribbeanGreen,
                                                handleColor = ColorOptionLegacy.CaribbeanGreen
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
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    validators = listOf(
                                        minLengthValidator(
                                            idWrapper = isPasswordMinLength,
                                            idsToValidate = listOf(passwordInputId),
                                            length = 8
                                        ),
                                    ),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(
                                        outlinedTextFieldsPadding
                                    ),
                                    trailingIcon = listOf(
                                        IconButton(
                                            visibilityProperty = VisibilityProperty(
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
                                                    ToBooleanAction(
                                                        isPasswordInvisible,
                                                        true
                                                    ),
                                                    ToBooleanAction(
                                                        isPasswordVisible,
                                                        false
                                                    ),
                                                )
                                            )
                                        ),
                                        IconButton(
                                            visibilityProperty = VisibilityProperty(
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
                                                    ToBooleanAction(
                                                        isPasswordVisible,
                                                        true
                                                    ),
                                                    ToBooleanAction(
                                                        isPasswordInvisible,
                                                        false
                                                    ),
                                                )
                                            ),
                                        ),
                                    ),
                                    prefix = listOf(
                                        Spacer(widthProperty = WidthProperty(10))
                                    )
                                ),
                                Column(
                                    weightProperty = WeightProperty(1f),
                                    verticalArrangementProperty = VerticalArrangementProperty(
                                        VerticalArrangementOption.Bottom
                                    ),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(30),
                                    horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                        HorizontalAlignmentOption.Center
                                    ),
                                    content = listOf(
                                        Button(
                                            paddingHorizontalProperty = PaddingHorizontalProperty(
                                                outlinedTextFieldLabelPadding
                                            ),
                                            heightProperty = HeightProperty(50),
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
                                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                                HorizontalFillTypeOption.Max
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
                                        Spacer(heightProperty = HeightProperty(10)),
                                        Button(
                                            paddingHorizontalProperty = PaddingHorizontalProperty(
                                                outlinedTextFieldLabelPadding
                                            ),
                                            heightProperty = HeightProperty(50),
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
                                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                                HorizontalFillTypeOption.Max
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
                                        Spacer(heightProperty = HeightProperty(20))
                                    )
                                ),
                                Spacer(sizeProperty = SizeProperty(10))
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