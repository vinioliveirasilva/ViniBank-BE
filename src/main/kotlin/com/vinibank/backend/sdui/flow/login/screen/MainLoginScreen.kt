package com.vinibank.backend.sdui.flow.login.screen

import com.vini.designsystemsdui.InteractionId
import com.vini.designsystemsdui.core.SdUiNode.Template
import com.vini.designsystemsdui.ui.action.CloseApplicationAction
import com.vini.designsystemsdui.ui.action.ContinueAction
import com.vini.designsystemsdui.ui.action.MultipleActions
import com.vini.designsystemsdui.ui.action.NavigateAction
import com.vini.designsystemsdui.ui.action.ToModifierAction
import com.vini.designsystemsdui.ui.action.toTypeAction
import com.vini.designsystemsdui.ui.component.BackHandler
import com.vini.designsystemsdui.ui.component.Button
import com.vini.designsystemsdui.ui.data.ButtonInteractionModel
import com.vini.designsystemsdui.ui.component.Column
import com.vini.designsystemsdui.ui.component.Icon
import com.vini.designsystemsdui.ui.component.IconButton
import com.vini.designsystemsdui.ui.component.OutlinedTextInput
import com.vini.designsystemsdui.ui.data.OutlinedTextInputInteractionModel
import com.vini.designsystemsdui.ui.component.Spacer
import com.vini.designsystemsdui.ui.component.Text
import com.vini.designsystemsdui.ui.modifier.Modifier
import com.vini.designsystemsdui.ui.modifier.background
import com.vini.designsystemsdui.ui.modifier.clip
import com.vini.designsystemsdui.ui.modifier.fillMaxWidth
import com.vini.designsystemsdui.ui.modifier.height
import com.vini.designsystemsdui.ui.modifier.option.FontWeightOption
import com.vini.designsystemsdui.ui.modifier.option.HorizontalAlignmentOption
import com.vini.designsystemsdui.ui.modifier.option.IconOption
import com.vini.designsystemsdui.ui.modifier.option.KeyboardOptionsOption
import com.vini.designsystemsdui.ui.modifier.option.ShapeOption
import com.vini.designsystemsdui.ui.modifier.option.VerticalArrangementOption
import com.vini.designsystemsdui.ui.modifier.option.VisualTransformationOption
import com.vini.designsystemsdui.ui.modifier.padding
import com.vini.designsystemsdui.ui.modifier.size
import com.vini.designsystemsdui.ui.modifier.verticalScroll
import com.vini.designsystemsdui.ui.modifier.visible
import com.vini.designsystemsdui.ui.modifier.width
import com.vini.designsystemsdui.ui.modifier.style.ButtonColorsModel
import com.vini.designsystemsdui.ui.modifier.option.OutlinedTextFieldColorsModel
import com.vini.designsystemsdui.ui.modifier.option.TextSelectionColorsModel
import com.vini.designsystemsdui.ui.modifier.option.ColorOption
import com.vini.designsystemsdui.ui.template.ScreenTemplate
import com.vini.designsystemsdui.ui.validator.allTrueValidator
import com.vini.designsystemsdui.ui.validator.emailValidator
import com.vini.designsystemsdui.ui.validator.minLengthValidator
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

        val emailInputId = InteractionId<String>("$screenFlowId.Email")
        val isEmailValid = InteractionId<Boolean>("$screenFlowId.Email.EmailValid")
        val passwordInputId = InteractionId<String>("$screenFlowId.Password")
        val passwordVisualTransformation =
            InteractionId<VisualTransformationOption>("$screenFlowId.Password.VisualTransformation")
        val isPasswordMinLength = InteractionId<Boolean>("$screenFlowId.Email.MinLength")

        val isContinueEnabled = InteractionId<Boolean>("$screenFlowId.LoginButton.Enabled")
        val isPasswordVisible = InteractionId<Modifier>("$screenFlowId.PasswordIsVisible")
        val isPasswordInvisible =
            InteractionId<Modifier>("$screenFlowId.PasswordIsNotVisible")

        val loginActionId = InteractionId<String>("$screenFlowId.LoginAction")

        return ScreenTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            content = {
                Column {
                    BackHandler(onBackAction = CloseApplicationAction())
                    Column(
                        modifier = Modifier.fillMaxWidth().weight(1f)
                            .background(ColorOption.CaribbeanGreen()).verticalScroll(),
                        horizontalAlignment = HorizontalAlignmentOption.Center(),
                        content = {
                            Spacer(modifier = Modifier.height(20))
                            Text(
                                modifier = Modifier.padding(vertical = 60),
                                text = "Welcome",
                                fontSize = 30f,
                                fontWeight = FontWeightOption.SemiBold
                            )
                            Column(
                                modifier = Modifier.fillMaxWidth()
                                    .clip(ShapeOption.RoundedCornerEdges(40, 40))
                                    .background(ColorOption.HoneyDew()).weight(1f),
                                horizontalAlignment = HorizontalAlignmentOption.Center(),
                                content = {
                                    Spacer(modifier = Modifier.height(70))
                                    Text(
                                        modifier = Modifier.fillMaxWidth().padding(
                                            horizontal = outlinedTextFieldLabelPadding
                                        ),
                                        text = "Email",
                                        fontSize = 18f,
                                    )
                                    OutlinedTextInput(
                                        modifier = Modifier.fillMaxWidth().padding(
                                            horizontal = outlinedTextFieldsPadding
                                        ),
                                        colors = OutlinedTextFieldColorsModel(
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
                                                ),
                                        shape = ShapeOption.Circle(),
                                        text = "vinioliveirasilva@outlook.com",
                                        prefix = {
                                            Spacer(modifier = Modifier.width(10))
                                        },
                                        validators = listOf(
                                            emailValidator(
                                                idWrapper = isEmailValid,
                                                emails = listOf(emailInputId)
                                            ),
                                        ),
                                        interactionModel = OutlinedTextInputInteractionModel(
                                            text = emailInputId
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(30))
                                    Text(
                                        modifier = Modifier.fillMaxWidth().padding(
                                            horizontal = outlinedTextFieldLabelPadding
                                        ),
                                        text = "Senha",
                                        fontSize = 18f,
                                    )
                                    OutlinedTextInput(
                                        modifier = Modifier.fillMaxWidth().padding(
                                            horizontal = outlinedTextFieldsPadding
                                        ),
                                        colors = OutlinedTextFieldColorsModel(
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
                                                ),
                                        shape = ShapeOption.Circle(),
                                        keyboardOptions = KeyboardOptionsOption.Password,
                                        interactionModel = OutlinedTextInputInteractionModel(
                                            text = passwordInputId,
                                            visualTransformation = passwordVisualTransformation
                                        ),
                                        visualTransformation = VisualTransformationOption.Password(),
                                        text = "Vini@123",
                                        validators = listOf(
                                            minLengthValidator(
                                                idWrapper = isPasswordMinLength,
                                                idsToValidate = listOf(passwordInputId),
                                                length = 8
                                            ),
                                        ),
                                        trailingIcon = {
                                            IconButton(
                                                modifier = Modifier.visible(
                                                    true,
                                                    isPasswordVisible
                                                ),
                                                content = {
                                                    Icon(
                                                        icon = IconOption.Visibility
                                                    )
                                                },
                                                onClickAction = MultipleActions(
                                                    listOf(
                                                        toTypeAction(
                                                            passwordVisualTransformation,
                                                            VisualTransformationOption.None()
                                                        ),
                                                        ToModifierAction(
                                                            newValue = Modifier.visible(
                                                                true,
                                                                isPasswordInvisible
                                                            )
                                                        ),
                                                        ToModifierAction(
                                                            newValue = Modifier.visible(
                                                                false,
                                                                isPasswordVisible
                                                            )
                                                        )
                                                    )
                                                )
                                            )
                                            IconButton(
                                                modifier = Modifier.visible(
                                                    false,
                                                    isPasswordInvisible
                                                ),
                                                content = {
                                                    Icon(icon = IconOption.VisibilityOff)
                                                },
                                                onClickAction = MultipleActions(
                                                    listOf(
                                                        toTypeAction(
                                                            passwordVisualTransformation,
                                                            VisualTransformationOption.Password()
                                                        ),
                                                        ToModifierAction(
                                                            newValue = Modifier.visible(
                                                                false,
                                                                isPasswordInvisible
                                                            )
                                                        ),
                                                        ToModifierAction(
                                                            newValue = Modifier.visible(
                                                                true,
                                                                isPasswordVisible
                                                            )
                                                        )
                                                    )
                                                ),
                                            )
                                        },
                                        prefix = {
                                            Spacer(modifier = Modifier.width(10))
                                        }
                                    )
                                    Column(
                                        modifier = Modifier.padding(horizontal = 30)
                                            .weight(1f),
                                        verticalArrangement = VerticalArrangementOption.Bottom(),
                                        horizontalAlignment = HorizontalAlignmentOption.Center(),
                                        content = {
                                            Button(
                                                interactionModel = ButtonInteractionModel(
                                                    enabled = isContinueEnabled
                                                ),
                                                modifier = Modifier.padding(
                                                    horizontal = outlinedTextFieldLabelPadding
                                                ).height(50).fillMaxWidth(),
                                                colors = ButtonColorsModel(
                                                    contentColor = ColorOption.Black(),
                                                    containerColor = ColorOption.CaribbeanGreen(),
                                                ),
                                                content = {
                                                    Text(
                                                        text = "Fazer Login",
                                                        fontSize = 16f,
                                                        fontWeight = FontWeightOption.SemiBold
                                                    )
                                                },
                                                enabled = false,
                                                validators = listOf(
                                                    allTrueValidator(
                                                        idWrapper = isContinueEnabled,
                                                        toValidate = listOf(
                                                            isEmailValid,
                                                            isPasswordMinLength
                                                        )
                                                    ),
                                                ),
                                                onClickAction = ContinueAction(
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
                                            )
                                            Spacer(modifier = Modifier.height(10))
                                            Button(
                                                modifier = Modifier.padding(
                                                    horizontal = outlinedTextFieldLabelPadding
                                                ).height(50).fillMaxWidth(),
                                                colors = ButtonColorsModel(
                                                    containerColor = ColorOption.LightGreen(),
                                                    contentColor = ColorOption.Black(),
                                                ),
                                                content = {
                                                    Text(
                                                        text = "Fazer Cadastro",
                                                        fontSize = 16f,
                                                        fontWeight = FontWeightOption.SemiBold
                                                    )
                                                },
                                                onClickAction = NavigateAction(
                                                    flow = "SignUp",
                                                    actionId = loginActionId,
                                                    screenData = request.screenData,
                                                    screenRequestData = listOf(
                                                        emailInputId.id to "email",
                                                        passwordInputId.id to "password"
                                                    )
                                                ),
                                            )
                                            Spacer(modifier = Modifier.height(20))
                                        }
                                    )
                                    Spacer(modifier = Modifier.size(10))
                                }
                            )
                        }
                    )
                }
            }
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
