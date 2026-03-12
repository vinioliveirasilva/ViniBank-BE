package com.vinibank.backend.sdui.flow.signup.screen

import com.vini.designsystemsdui.core.SdUiNode.Template
import com.vini.designsystemsdui.ui.action.CloseAction
import com.vini.designsystemsdui.ui.action.ContinueAction
import com.vini.designsystemsdui.ui.component.Button
import com.vini.designsystemsdui.ui.component.Column
import com.vini.designsystemsdui.ui.component.LazyColumn
import com.vini.designsystemsdui.ui.component.OutlinedButton
import com.vini.designsystemsdui.ui.component.OutlinedTextInput
import com.vini.designsystemsdui.ui.component.Text
import com.vini.designsystemsdui.ui.component.TopAppBar
import com.vini.designsystemsdui.exception.createSdUiPropertyUpdateException
import com.vini.designsystemsdui.ui.modifier.Modifier
import com.vini.designsystemsdui.ui.modifier.fillMaxHeight
import com.vini.designsystemsdui.ui.modifier.fillMaxWidth
import com.vini.designsystemsdui.ui.modifier.option.HorizontalAlignmentOption
import com.vini.designsystemsdui.ui.modifier.padding
import com.vini.designsystemsdui.ui.modifier.option.VerticalArrangementOption
import com.vini.designsystemsdui.InteractionId
import com.vini.designsystemsdui.ui.data.ButtonInteractionModel
import com.vini.designsystemsdui.ui.data.OutlinedTextInputInteractionModel
import com.vini.designsystemsdui.ui.component.SdUi
import com.vini.designsystemsdui.ui.modifier.fillMaxSize
import com.vini.designsystemsdui.ui.template.ScreenTemplate
import com.vini.designsystemsdui.ui.validator.emailValidator
import com.vinibank.backend.db.UserDatabase
import com.vinibank.backend.sdui.flow.signup.SignUpScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import org.springframework.stereotype.Component

@Component
class EmailScreen(
    private val userDb: UserDatabase,
) : SignUpScreen {
    override val screenId: String = "Start"

    private val emailInputId = InteractionId<String>("SignUp.Email.emailInput")
    private val isEmailValid = InteractionId<Boolean>("SignUp.Email.isEmailValid")
    private val isError = InteractionId<Boolean>("SignUp.Email.emailInput.isError")
    private val errorMessage = InteractionId<String>("SignUp.Email.emailInput.errorMessage")

    override fun getScreen(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? {
        return getInternalScreen(request)
    }

    override fun getRule(request: SdUiRequest) {
        if (request.fromScreen.isBlank()) return

        val model = Json.decodeFromJsonElement<EmailScreenState>(
            request.screenData ?: JsonObject(emptyMap())
        )

        if (userDb.users[model.email] != null) {
            throw createSdUiPropertyUpdateException(
                message = "Email ja cadastrado",
                properties = listOf(
//                    ErrorMessageProperty("Email já cadastrado", errorMessage),TODO()
//                    ErrorProperty(true, isError),
//                    EnabledProperty(false, isEmailValid),
                )
            )
        }
    }

    private fun getInternalScreen(
        request: SdUiRequest,
        state: EmailScreenState = EmailScreenState(),
    ) = ScreenTemplate(
        flow = request.flow,
        stage = screenId,
        version = "1",
        content = {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                TopAppBar(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20),
                    title = {
                        Text(text = "Email")
                    }
                )
                LazyColumn(
                    modifier = Modifier.padding(vertical = 20).fillMaxHeight(),
                    verticalArrangement = VerticalArrangementOption.SpaceBetween(),
                    content = {
                        OutlinedTextInput(
                            interactionModel = OutlinedTextInputInteractionModel(
                                text = emailInputId,
                                isError = isError,
                                errorMessage = errorMessage,
                            ),
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 20),
                            text = state.email,
                            isError = state.isError,
                            errorMessage = "Email já cadastrado",
                            label = {
                                Text(text = "Digite seu email")
                            },
                            validators = listOf(
                                emailValidator(
                                    idWrapper = isEmailValid,
                                    emails = listOf(emailInputId),
                                ),
                            )
                        )
                        Column(
                            modifier = Modifier.padding(horizontal = 20).fillMaxWidth(),
                            horizontalAlignment = HorizontalAlignmentOption.Center(),
                            content = {
                                Button(
                                    interactionModel = ButtonInteractionModel(
                                        enabled = isEmailValid
                                    ),
                                    modifier = Modifier.fillMaxWidth(),
                                    content = {
                                        Text(text = "Continuar")
                                    },
                                    enabled = false,
                                    onClickAction = ContinueAction(
                                        flowId = "SignUp",
                                        nextScreenId = "PersonalInfo",
                                        currentScreenId = screenId,
                                        screenRequestData = listOf(
                                            "SignUp.Email.emailInput" to "email"
                                        ),
                                        screenData = request.screenData,
                                    ),
                                )
                                OutlinedButton(
                                    modifier = Modifier.fillMaxWidth(),
                                    content = {
                                        Text(text = "Fechar")
                                    },
                                    onClickAction = CloseAction()
                                )
                            }
                        )
                    }
                )
            }
        }
    )
}