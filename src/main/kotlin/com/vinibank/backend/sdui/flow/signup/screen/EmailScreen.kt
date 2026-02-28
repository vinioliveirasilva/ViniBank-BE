package com.vinibank.backend.sdui.flow.signup.screen

import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.CloseAction
import com.vini.designsystemsdui.action.ContinueAction
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.LazyColumn
import com.vini.designsystemsdui.component.OutlinedButton
import com.vini.designsystemsdui.component.OutlinedTextInput
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.component.TopAppBar
import com.vini.designsystemsdui.exception.createSdUiPropertyUpdateException
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.property.EnabledProperty
import com.vini.designsystemsdui.property.ErrorMessageProperty
import com.vini.designsystemsdui.property.ErrorProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.property.util.PropertyIdWrapper
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vini.designsystemsdui.validator.emailValidator
import com.vinibank.backend.db.UserDatabase
import com.vinibank.backend.sdui.flow.signup.SignUpScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import org.springframework.stereotype.Component

@Component()
class EmailScreen(
    private val userDb: UserDatabase,
) : SignUpScreen {
    override val screenId: String = "Start"

    private val emailInputId = PropertyIdWrapper<String>("SignUp.Email.emailInput")
    private val isEmailValid = PropertyIdWrapper<Boolean>("SignUp.Email.isEmailValid")
    private val isError = PropertyIdWrapper<Boolean>("SignUp.Email.emailInput.isError")
    private val errorMessage = PropertyIdWrapper<String>("SignUp.Email.emailInput.errorMessage")

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
                    ErrorMessageProperty("Email já cadastrado", errorMessage),
                    ErrorProperty(true, isError),
                    EnabledProperty(false, isEmailValid),
                )
            )
        }
    }

    private fun getInternalScreen(
        request: SdUiRequest,
        state: EmailScreenState = EmailScreenState(),
    ) = DefaultTemplate(
        flow = request.flow,
        stage = screenId,
        version = "1",
        content = listOf(
            TopAppBar(
                modifier = SdUiModifier().fillMaxWidth().padding(horizontal = 20),
                title = listOf(
                    Text(
                        textProperty = TextProperty("Email"),
                    )
                )
            ),
            LazyColumn(
                modifier = SdUiModifier().padding(vertical = 20),
                verticalArrangementProperty = VerticalArrangementProperty(VerticalArrangementOption.SpaceBetween),
                weightProperty = WeightProperty(1f),
                content = listOf(
                    OutlinedTextInput(
                        modifier = SdUiModifier().fillMaxWidth().padding(horizontal = 20),
                        textProperty = TextProperty(state.email, emailInputId),
                        errorProperty = ErrorProperty(
                            state.isError,
                            isError
                        ),
                        errorMessageProperty = ErrorMessageProperty(
                            "Email já cadastrado",
                            idWrapper = errorMessage
                        ),
                        label = listOf(
                            Text(textProperty = TextProperty(value = "Digite seu email"))
                        ),
                        validators = listOf(
                            emailValidator(
                                idWrapper = isEmailValid,
                                emails = listOf(emailInputId),
                            ),
                        )
                    ),
                    Column(
                        modifier = SdUiModifier().padding(horizontal = 20).fillMaxWidth(),
                        horizontalAlignmentProperty = HorizontalAlignmentProperty(
                            HorizontalAlignmentOption.Center
                        ),
                        content = listOf(
                            Button(
                                modifier = SdUiModifier().fillMaxWidth(),
                                content = listOf(
                                    Text(textProperty = TextProperty(value = "Continuar"))
                                ),
                                enabledProperty = EnabledProperty(
                                    false,
                                    isEmailValid
                                ),
                                onClick = ContinueAction(
                                    flowId = "SignUp",
                                    nextScreenId = "PersonalInfo",
                                    currentScreenId = screenId,
                                    screenRequestData = listOf(
                                        "SignUp.Email.emailInput" to "email"
                                    ),
                                    screenData = request.screenData,
                                ),
                            ),
                            OutlinedButton(
                                modifier = SdUiModifier().fillMaxWidth(),
                                content = listOf(
                                    Text(textProperty = TextProperty(value = "Fechar"))
                                ),
                                onClick = CloseAction()
                            )
                        )
                    )
                )
            )
        )
    )
}