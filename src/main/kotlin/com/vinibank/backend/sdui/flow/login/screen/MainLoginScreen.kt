package com.vinibank.backend.sdui.flow.login.screen

import com.vini.designsystemsdui.InteractionId
import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.CloseApplicationAction
import com.vini.designsystemsdui.action.ContinueAction
import com.vini.designsystemsdui.action.MultipleActions
import com.vini.designsystemsdui.action.NavigateAction
import com.vini.designsystemsdui.action.ToModifierAction
import com.vini.designsystemsdui.action.ToTypeAction
import com.vini.designsystemsdui.component.BackHandler
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.ButtonInteractionModel
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.IconButton
import com.vini.designsystemsdui.component.OutlinedTextInput
import com.vini.designsystemsdui.component.OutlinedTextInputInteractionModel
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.modifier.BaseModifier
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.background
import com.vini.designsystemsdui.modifier.clip
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.height
import com.vini.designsystemsdui.modifier.option.FontWeightOption
import com.vini.designsystemsdui.modifier.option.HorizontalAlignmentOption
import com.vini.designsystemsdui.modifier.option.IconOption
import com.vini.designsystemsdui.modifier.option.KeyboardOptionsOption
import com.vini.designsystemsdui.modifier.option.ShapeOption
import com.vini.designsystemsdui.modifier.option.VerticalArrangementOption
import com.vini.designsystemsdui.modifier.option.VisualTransformationOption
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.size
import com.vini.designsystemsdui.modifier.verticalScroll
import com.vini.designsystemsdui.modifier.visible
import com.vini.designsystemsdui.modifier.width
import com.vini.designsystemsdui.property.options.ButtonColorsModel
import com.vini.designsystemsdui.property.options.OutlinedTextFieldColorsModel
import com.vini.designsystemsdui.property.options.TextSelectionColorsModel
import com.vini.designsystemsdui.property.options.color.ColorOption
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

        val emailInputId = InteractionId<String>("$screenFlowId.Email")
        val isEmailValid = InteractionId<Boolean>("$screenFlowId.Email.EmailValid")
        val passwordInputId = InteractionId<String>("$screenFlowId.Password")
        val passwordVisualTransformation =
            InteractionId<VisualTransformationOption>("$screenFlowId.Password.VisualTransformation")
        val isPasswordMinLength = InteractionId<Boolean>("$screenFlowId.Email.MinLength")

        val isContinueEnabled = InteractionId<Boolean>("$screenFlowId.LoginButton.Enabled")
        val isPasswordVisible = InteractionId<BaseModifier>("$screenFlowId.PasswordIsVisible")
        val isPasswordInvisible =
            InteractionId<BaseModifier>("$screenFlowId.PasswordIsNotVisible")

        val loginActionId = InteractionId<String>("$screenFlowId.LoginAction")

        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            content = {
                Column {
                    BackHandler(onBackAction = CloseApplicationAction())
                    Column(
                        modifier = SdUiModifier().fillMaxWidth().weight(1f)
                            .background(ColorOption.CaribbeanGreen()).verticalScroll(),
                        horizontalAlignment = HorizontalAlignmentOption.Center(),
                        content = {
                            Spacer(modifier = SdUiModifier().height(20))
                            Text(
                                modifier = SdUiModifier().padding(vertical = 60),
                                text = "Welcome",
                                fontSize = 30f,
                                fontWeight = FontWeightOption.SemiBold
                            )
                            Column(
                                modifier = SdUiModifier().fillMaxWidth()
                                    .clip(ShapeOption.RoundedCornerEdges(40, 40))
                                    .background(ColorOption.HoneyDew()).weight(1f),
                                horizontalAlignment = HorizontalAlignmentOption.Center(),
                                content = {
                                    Spacer(modifier = SdUiModifier().height(70))
                                    Text(
                                        modifier = SdUiModifier().fillMaxWidth().padding(
                                            horizontal = outlinedTextFieldLabelPadding
                                        ),
                                        text = "Email",
                                        fontSize = 18f,
                                    )
                                    OutlinedTextInput(
                                        modifier = SdUiModifier().fillMaxWidth().padding(
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
                                            Spacer(modifier = SdUiModifier().width(10))
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
                                    Spacer(modifier = SdUiModifier().height(30))
                                    Text(
                                        modifier = SdUiModifier().fillMaxWidth().padding(
                                            horizontal = outlinedTextFieldLabelPadding
                                        ),
                                        text = "Senha",
                                        fontSize = 18f,
                                    )
                                    OutlinedTextInput(
                                        modifier = SdUiModifier().fillMaxWidth().padding(
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
                                                modifier = SdUiModifier().visible(
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
//                                                        ToTypeAction(TODO()
//                                                            passwordVisualTransformation,
//                                                            VisualTransformationOption.None()
//                                                        ),
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
                                            )
                                            IconButton(
                                                modifier = SdUiModifier().visible(
                                                    false,
                                                    isPasswordInvisible
                                                ),
                                                content = {
                                                    Icon(icon = IconOption.VisibilityOff)
                                                },
                                                onClickAction = MultipleActions(
                                                    listOf(
//                                                        ToTypeAction(TODO()
//                                                            passwordVisualTransformation,
//                                                            VisualTransformationOption.Password()
//                                                        ),
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
                                            )
                                        },
                                        prefix = {
                                            Spacer(modifier = SdUiModifier().width(10))
                                        }
                                    )
                                    Column(
                                        modifier = SdUiModifier().padding(horizontal = 30)
                                            .weight(1f),
                                        verticalArrangement = VerticalArrangementOption.Bottom(),
                                        horizontalAlignment = HorizontalAlignmentOption.Center(),
                                        content = {
                                            Button(
                                                interactionModel = ButtonInteractionModel(
                                                    enabled = isContinueEnabled
                                                ),
                                                modifier = SdUiModifier().padding(
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
                                            Spacer(modifier = SdUiModifier().height(10))
                                            Button(
                                                modifier = SdUiModifier().padding(
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
                                            Spacer(modifier = SdUiModifier().height(20))
                                        }
                                    )
                                    Spacer(modifier = SdUiModifier().size(10))
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
