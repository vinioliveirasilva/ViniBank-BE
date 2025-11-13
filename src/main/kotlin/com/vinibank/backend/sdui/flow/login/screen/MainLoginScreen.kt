package com.vinibank.backend.sdui.flow.login.screen

import com.vini.designsystemsdui.action.closeApplicationAction
import com.vini.designsystemsdui.action.continueAction
import com.vini.designsystemsdui.action.multipleActions
import com.vini.designsystemsdui.action.navigateAction
import com.vini.designsystemsdui.action.toBooleanAction
import com.vini.designsystemsdui.action.toStringAction
import com.vini.designsystemsdui.component.backHandler
import com.vini.designsystemsdui.component.button
import com.vini.designsystemsdui.component.column
import com.vini.designsystemsdui.component.icon
import com.vini.designsystemsdui.component.iconButton
import com.vini.designsystemsdui.component.lazyColumn
import com.vini.designsystemsdui.component.outlinedButton
import com.vini.designsystemsdui.component.outlinedTextInput
import com.vini.designsystemsdui.component.spacer
import com.vini.designsystemsdui.component.text
import com.vini.designsystemsdui.component.textInput
import com.vini.designsystemsdui.property.BackgroundColorProperty
import com.vini.designsystemsdui.property.ButtonColorsProperty
import com.vini.designsystemsdui.property.ColorProperty
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
import com.vini.designsystemsdui.property.TextFieldColorsProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.VerticalScrollProperty
import com.vini.designsystemsdui.property.VisibilityProperty
import com.vini.designsystemsdui.property.VisualTransformationProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.WidthProperty
import com.vini.designsystemsdui.property.options.ColorOption
import com.vini.designsystemsdui.property.options.FontWeightOption
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.InternalButtonColors
import com.vini.designsystemsdui.property.options.InternalTextFieldColors
import com.vini.designsystemsdui.property.options.KeyboardOptionsOption
import com.vini.designsystemsdui.property.options.OutlinedTextFieldColorsModel
import com.vini.designsystemsdui.property.options.ShapeOptions
import com.vini.designsystemsdui.property.options.TextSelectionColorsModel
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

    companion object {
        var toggleLoginScreens = false
    }

    override val screenId: String
        get() = "InformationInput"

    override fun getScreen(request: SdUiRequest): JsonObject? {
        toggleLoginScreens = toggleLoginScreens.not()
        return if(toggleLoginScreens) {
            greenLoginScreen(request)
        } else {
            blueLoginScreen(request)
        }
    }

    private fun blueLoginScreen(request: SdUiRequest): JsonObject {
        val screenFlowId = "${request.flow}.${screenId}"
        val textFieldTheme = InternalTextFieldColors(
            focusedContainerColor = ColorOption.BlueGray,
            unfocusedContainerColor = ColorOption.BlueGray,
            focusedTextColor = ColorOption.White,
            unfocusedTextColor = ColorOption.White,
            focusedIndicatorColor = ColorOption.White,
            unfocusedIndicatorColor = ColorOption.LightBlueGray,
            focusedLabelColor = ColorOption.White,
            unfocusedLabelColor = ColorOption.White,
        )
        return screen(
            flow = request.flow,
            stage = screenId,
            version = "1",
            template = "",
            shouldCache = false,
            content = listOf(
                backHandler(onBackAction = closeApplicationAction()),
                lazyColumn(
                    backgroundColorProperty = BackgroundColorProperty(ColorOption.BlueGray),
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    verticalArrangementProperty = VerticalArrangementProperty(
                        VerticalArrangementOption.SpaceBetween
                    ),
                    weightProperty = WeightProperty(1f),
                    content = listOf(
                        column(
                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                HorizontalAlignmentOption.Center
                            ),
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                HorizontalFillTypeOption.Max
                            ),
                            content = listOf(
                                //topAppBar(title = listOf(text(textProperty = TextProperty("Login")))),
                                spacer(heightProperty = HeightProperty(60)),
                                column(
                                    horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                        HorizontalAlignmentOption.Center
                                    ),
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    content = listOf(
                                        text(
                                            colorProperty = ColorProperty(ColorOption.White),
                                            textProperty = TextProperty("V i n i"),
                                            fontSizeProperty = FontSizeProperty(40f),
                                            fontWeightProperty = FontWeightProperty(FontWeightOption.ExtraBold)
                                        ),
                                        text(
                                            colorProperty = ColorProperty(ColorOption.White),
                                            textProperty = TextProperty("B a n k"),
                                            fontSizeProperty = FontSizeProperty(40f),
                                            fontWeightProperty = FontWeightProperty(FontWeightOption.ExtraBold)
                                        ),
                                    )
                                ),
                                spacer(heightProperty = HeightProperty(60)),
                                text(
                                    colorProperty = ColorProperty(ColorOption.White),
                                    textProperty = TextProperty("Login"),
                                    fontSizeProperty = FontSizeProperty(30f),
                                    fontWeightProperty = FontWeightProperty(FontWeightOption.Bold)
                                ),
                                spacer(heightProperty = HeightProperty(20)),
                                textInput(
                                    textFieldColorsProperty = TextFieldColorsProperty(textFieldTheme),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(30),
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    label = listOf(
                                        text(textProperty = TextProperty("Email"))
                                    ),
                                    textProperty = TextProperty(
                                        "123@123.com",
                                        "$screenFlowId.Email"
                                    ),
                                    validators = listOf(
                                        emailValidator(
                                            id ="$screenFlowId.Email.EmailValid",
                                            emails = listOf("$screenFlowId.Email")
                                        ),
                                    )
                                ),
                                textInput(
                                    textFieldColorsProperty = TextFieldColorsProperty(textFieldTheme),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(30),
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    label = listOf(
                                        text(textProperty = TextProperty("Senha"))
                                    ),
                                    textProperty = TextProperty(
                                        "123@123A",
                                        "$screenFlowId.Password"
                                    ),
                                    keyboardOptionsProperty = KeyboardOptionsProperty(
                                        KeyboardOptionsOption.Password
                                    ),
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
//                                    trailingIcon = listOf(
//                                        iconButton(
//                                            visibilityProperty = VisibilityProperty(
//                                                true,
//                                                "screenFlowId.PasswordIsVisible"
//                                            ),
//                                            content = listOf(
//                                                icon(iconNameProperty = IconNameProperty("Visibility"))
//                                            ),
//                                            onClick = multipleActions(
//                                                listOf(
//                                                    toStringAction(
//                                                        "screenFlowId.Password.VisualTransformation",
//                                                        "None"
//                                                    ),
//                                                    toBooleanAction(
//                                                        "screenFlowId.PasswordIsNotVisible",
//                                                        true
//                                                    ),
//                                                    toBooleanAction(
//                                                        "screenFlowId.PasswordIsVisible",
//                                                        false
//                                                    ),
//                                                )
//                                            )
//                                        ),
//                                        iconButton(
//                                            visibilityProperty = VisibilityProperty(
//                                                false,
//                                                "screenFlowId.PasswordIsNotVisible"
//                                            ),
//                                            content = listOf(
//                                                icon(
//                                                    iconNameProperty = IconNameProperty(
//                                                        "VisibilityOff"
//                                                    )
//                                                )
//                                            ),
//                                            onClick = multipleActions(
//                                                listOf(
//                                                    toStringAction(
//                                                        "screenFlowId.Password.VisualTransformation",
//                                                        "Password"
//                                                    ),
//                                                    toBooleanAction(
//                                                        "screenFlowId.PasswordIsVisible",
//                                                        true
//                                                    ),
//                                                    toBooleanAction(
//                                                        "screenFlowId.PasswordIsNotVisible",
//                                                        false
//                                                    ),
//                                                )
//                                            ),
//                                        ),
//                                    )
                                ),
                            )
                        ),
                        column(
                            paddingHorizontalProperty = PaddingHorizontalProperty(30),
                            content = listOf(
                                button(
                                    buttonColorsProperty = ButtonColorsProperty(
                                        InternalButtonColors(
                                            contentColor = ColorOption.BlueGray,
                                            containerColor = ColorOption.White
                                        )
                                    ),
                                    content = listOf(
                                        text(textProperty = TextProperty("Fazer Login"))
                                    ),
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
                                outlinedButton(
                                    buttonColorsProperty = ButtonColorsProperty(
                                        InternalButtonColors(
                                            contentColor = ColorOption.White,
                                        )
                                    ),
                                    content = listOf(
                                        text(textProperty = TextProperty("Fazer Cadastro"))
                                    ),
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

    private fun greenLoginScreen(request: SdUiRequest): JsonObject {
        val outlinedTextFieldsPadding = 30
        val outlinedTextFieldLabelPadding = outlinedTextFieldsPadding + 20
        val screenFlowId = "${request.flow}.${screenId}"
        return screen(
            flow = request.flow,
            stage = screenId,
            version = "1",
            template = "",
            shouldCache = false,
            content = listOf(
                backHandler(onBackAction = closeApplicationAction()),
                column(
                    verticalScrollProperty = VerticalScrollProperty(true),
                    horizontalAlignmentProperty = HorizontalAlignmentProperty(
                        HorizontalAlignmentOption.Center
                    ),
                    backgroundColorProperty = BackgroundColorProperty(ColorOption.CaribbeanGreen),
                    weightProperty = WeightProperty(1f),
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                        HorizontalFillTypeOption.Max
                    ),
                    content = listOf(
                        spacer(heightProperty = HeightProperty(20)),
                        text(
                            paddingVerticalProperty = PaddingVerticalProperty(60),
                            textProperty = TextProperty("Welcome"),
                            fontSizeProperty = FontSizeProperty(30f),
                            fontWeightProperty = FontWeightProperty(FontWeightOption.SemiBold)
                        ),
                        column(
                            shapeProperty = ShapeProperty(ShapeOptions.CustomTop(40)),
                            weightProperty = WeightProperty(1f),
                            backgroundColorProperty = BackgroundColorProperty(ColorOption.HoneyDew),
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                HorizontalFillTypeOption.Max
                            ),
                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                HorizontalAlignmentOption.Center
                            ),
                            content = listOf(
                                spacer(heightProperty = HeightProperty(70)),
                                text(
                                    textProperty = TextProperty("Email"),
                                    fontSizeProperty = FontSizeProperty(18f),
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(
                                        outlinedTextFieldLabelPadding
                                    )
                                ),
                                outlinedTextInput(
                                    outlinedTextFieldColorsProperty = OutlinedTextFieldColorsProperty(
                                        OutlinedTextFieldColorsModel(
                                            focusedBorderColor = ColorOption.Transparent,
                                            unfocusedBorderColor = ColorOption.Transparent,
                                            focusedContainerColor = ColorOption.LightGreen,
                                            unfocusedContainerColor = ColorOption.LightGreen,
                                            focusedPlaceholderColor = ColorOption.Black,
                                            unfocusedPlaceholderColor = ColorOption.Black,
                                            cursorColor = ColorOption.CaribbeanGreen,
                                            textSelectionColors = TextSelectionColorsModel(
                                                backgroundColor = ColorOption.CaribbeanGreen,
                                                handleColor = ColorOption.CaribbeanGreen
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
                                        "123@123.com",
                                        "$screenFlowId.Email"
                                    ),
                                    prefix = listOf(
                                        spacer(widthProperty = WidthProperty(10))
                                    ),
                                    validators = listOf(
                                        emailValidator(
                                            id = "$screenFlowId.Email.EmailValid",
                                            emails = listOf("$screenFlowId.Email")
                                        ),
                                    ),
                                ),
                                spacer(heightProperty = HeightProperty(30)),
                                text(
                                    textProperty = TextProperty("Senha"),
                                    fontSizeProperty = FontSizeProperty(18f),
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(
                                        outlinedTextFieldLabelPadding
                                    )
                                ),
                                outlinedTextInput(
                                    outlinedTextFieldColorsProperty = OutlinedTextFieldColorsProperty(
                                        OutlinedTextFieldColorsModel(
                                            focusedBorderColor = ColorOption.Transparent,
                                            unfocusedBorderColor = ColorOption.Transparent,
                                            focusedContainerColor = ColorOption.LightGreen,
                                            unfocusedContainerColor = ColorOption.LightGreen,
                                            focusedPlaceholderColor = ColorOption.Black,
                                            unfocusedPlaceholderColor = ColorOption.Black,
                                            cursorColor = ColorOption.CaribbeanGreen,
                                            textSelectionColors = TextSelectionColorsModel(
                                                backgroundColor = ColorOption.CaribbeanGreen,
                                                handleColor = ColorOption.CaribbeanGreen
                                            )
                                        )
                                    ),
                                    shapeProperty = ShapeProperty(ShapeOptions.Circle),
                                    keyboardOptionsProperty = KeyboardOptionsProperty(
                                        KeyboardOptionsOption.Password
                                    ),
                                    visualTransformationProperty = VisualTransformationProperty(
                                        VisualTransformationOption.Password,
                                        "$screenFlowId.Password.VisualTransformation"
                                    ),
                                    textProperty = TextProperty(
                                        "123@123A",
                                        id = "$screenFlowId.Password"
                                    ),
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    validators = listOf(
                                        minLengthValidator(
                                            id = "$screenFlowId.Password.MinLength",
                                            idsToValidate = listOf("$screenFlowId.Password"),
                                            length = 8
                                        ),
                                    ),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(
                                        outlinedTextFieldsPadding
                                    ),
                                    trailingIcon = listOf(
                                        iconButton(
                                            visibilityProperty = VisibilityProperty(
                                                true,
                                                "$screenFlowId.PasswordIsVisible"
                                            ),
                                            content = listOf(
                                                icon(iconNameProperty = IconNameProperty("Visibility"))
                                            ),
                                            onClick = multipleActions(
                                                listOf(
                                                    toStringAction(
                                                        "$screenFlowId.Password.VisualTransformation",
                                                        VisualTransformationOption.None.name
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
                                            content = listOf(
                                                icon(
                                                    iconNameProperty = IconNameProperty(
                                                        "VisibilityOff"
                                                    )
                                                )
                                            ),
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
                                    ),
                                    prefix = listOf(
                                        spacer(widthProperty = WidthProperty(10))
                                    )
                                ),
                                column(
                                    weightProperty = WeightProperty(1f),
                                    verticalArrangementProperty = VerticalArrangementProperty(
                                        VerticalArrangementOption.Bottom
                                    ),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(30),
                                    horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                        HorizontalAlignmentOption.Center
                                    ),
                                    content = listOf(
                                        button(
                                            paddingHorizontalProperty = PaddingHorizontalProperty(
                                                outlinedTextFieldLabelPadding
                                            ),
                                            heightProperty = HeightProperty(50),
                                            buttonColorsProperty = ButtonColorsProperty(
                                                InternalButtonColors(
                                                    contentColor = ColorOption.Black,
                                                    containerColor = ColorOption.CaribbeanGreen,
                                                )
                                            ),
                                            content = listOf(
                                                text(
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
                                        button(
                                            paddingHorizontalProperty = PaddingHorizontalProperty(
                                                outlinedTextFieldLabelPadding
                                            ),
                                            heightProperty = HeightProperty(50),
                                            buttonColorsProperty = ButtonColorsProperty(
                                                InternalButtonColors(
                                                    containerColor = ColorOption.LightGreen,
                                                    contentColor = ColorOption.Black,
                                                )
                                            ),
                                            content = listOf(
                                                text(
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
                                ),
                                spacer(sizeProperty = SizeProperty(10))
                            )
                        ),
                    )
                )
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