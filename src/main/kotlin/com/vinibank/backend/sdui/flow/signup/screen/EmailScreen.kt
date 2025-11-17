package com.vinibank.backend.sdui.flow.signup.screen

import com.vini.designsystemsdui.action.closeAction
import com.vini.designsystemsdui.action.continueAction
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.LazyColumn
import com.vini.designsystemsdui.component.OutlinedButton
import com.vini.designsystemsdui.component.OutlinedTextInput
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.component.TopAppBar
import com.vini.designsystemsdui.exception.createSdUiPropertyUpdateException
import com.vini.designsystemsdui.property.EnabledProperty
import com.vini.designsystemsdui.property.ErrorMessageProperty
import com.vini.designsystemsdui.property.ErrorProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vini.designsystemsdui.template.Template
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
    override val screenId: String = "Email"

    override fun getScreen(request: SdUiRequest): Template? {
        return getInternalScreen(request)
    }

    override fun getRule(request: SdUiRequest) {
        if (request.fromScreen.isBlank()) return

        val model = Json.decodeFromJsonElement<EmailScreenState>(
            request.screenData ?: JsonObject(emptyMap())
        )

        if (userDb.users.any { it.key == model.email }) {
            //val response = getInternalScreen(request, model.copy(isError = true))
            throw createSdUiPropertyUpdateException(
                message = "Email ja cadastrado",
                properties = listOf(
                    ErrorMessageProperty("Email já cadastrado1","SignUp.Email.emailInput.errorMessage"),
                    ErrorProperty(true, "SignUp.Email.emailInput.isError"),
                    EnabledProperty(false, "SignUp.Email.isEmailValid"),
                )
            )
            //throw SdUiError("Email ja cadastrado", 400, response)
        }
    }

    private fun getInternalScreen(
        request: SdUiRequest,
        state: EmailScreenState = EmailScreenState("vinioliveirasilva@hotmail.co"),
    ) = DefaultTemplate(
        flow = "SignUp",
        stage = "Email",
        version = "1",
        template = "",
        content = listOf(
            TopAppBar(
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                paddingHorizontalProperty = PaddingHorizontalProperty(20),
                title = listOf(
                    Text(
                        textProperty = TextProperty("Email"),
                    )
                )
            ),
            LazyColumn(
                verticalArrangementProperty = VerticalArrangementProperty(VerticalArrangementOption.SpaceBetween),
                weightProperty = WeightProperty(1f),
                paddingVerticalProperty = PaddingVerticalProperty(20),
                content = listOf(
                    OutlinedTextInput(
                        textProperty = TextProperty(state.email, "SignUp.Email.emailInput"),
                        errorProperty = ErrorProperty(
                            state.isError,
                            "SignUp.Email.emailInput.isError"
                        ),
                        errorMessageProperty = ErrorMessageProperty("Email já cadastrado", id = "SignUp.Email.emailInput.errorMessage"),
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(
                            HorizontalFillTypeOption.Max
                        ),
                        paddingHorizontalProperty = PaddingHorizontalProperty(20),
                        label = listOf(
                            Text(textProperty = TextProperty(value = "Digite seu email"))
                        ),
                        validators = listOf(
                            emailValidator(
                                id = "SignUp.Email.isEmailValid",
                                emails = listOf("SignUp.Email.emailInput"),
                            ),
                        )
                    ),
                    Column(
                        horizontalAlignmentProperty = HorizontalAlignmentProperty(
                            HorizontalAlignmentOption.Center
                        ),
                        paddingHorizontalProperty = PaddingHorizontalProperty(20),
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(
                            HorizontalFillTypeOption.Max
                        ),
                        content = listOf(
                            Button(
                                content = listOf(
                                    Text(textProperty = TextProperty(value = "Continuar"))
                                ),
                                enabledProperty = EnabledProperty(
                                    false,
                                    "SignUp.Email.isEmailValid"
                                ),
                                horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                    HorizontalFillTypeOption.Max
                                ),
                                onClick = continueAction(
                                    flowId = "SignUp",
                                    nextScreenId = "PersonalInfo",
                                    currentScreenId = "Email",
                                    screenRequestData = listOf(
                                        "SignUp.Email.emailInput" to "email"
                                    ),
                                    screenData = request.screenData,
                                ),
                            ),
                            OutlinedButton(
                                content = listOf(
                                    Text(textProperty = TextProperty(value = "Fechar"))
                                ),
                                horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                    HorizontalFillTypeOption.Max
                                ),
                                onClick = closeAction()
                            )
                        )
                    )
                )
            )
        )
    )
}