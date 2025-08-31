package com.vinibank.backend.sdui.flow.signup.screen

import com.vini.designsystemsdui.action.closeAction
import com.vini.designsystemsdui.action.continueAction
import com.vini.designsystemsdui.component.button
import com.vini.designsystemsdui.component.column
import com.vini.designsystemsdui.component.lazyColumn
import com.vini.designsystemsdui.component.outlinedButton
import com.vini.designsystemsdui.component.outlinedTextInput
import com.vini.designsystemsdui.component.text
import com.vini.designsystemsdui.component.topAppBar
import com.vini.designsystemsdui.property.ErrorMessageProperty
import com.vini.designsystemsdui.property.ErrorProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.EnabledProperty
import com.vini.designsystemsdui.property.LabelProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.validator.emailValidator
import com.vinibank.backend.db.UserDatabase
import com.vinibank.backend.sdui.flow.signup.SignUpScreen
import com.vinibank.backend.sdui.model.SdUiError
import com.vinibank.backend.sdui.model.SdUiRequest
import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import org.springframework.stereotype.Component

@Component()
class EmailScreen(
    private val userDb: UserDatabase,
) : SignUpScreen {
    override val screenId: String = "Email"

    override fun getScreen(request: SdUiRequest): JsonObject? {
        return getInternalScreen(request)
    }

    override fun getRule(request: SdUiRequest): JsonObject? {
        if (request.fromScreen.isBlank()) return null

        val model = Json.decodeFromJsonElement<EmailScreenState>(
            request.screenData ?: JsonObject(emptyMap())
        )

        if (userDb.users.any { it.key == model.email }) {
            val response = getInternalScreen(request, model.copy(isError = true))
            throw SdUiError("Email ja cadastrado", 400, response)
        }

        return null
    }

    private fun getInternalScreen(
        request: SdUiRequest,
        state: EmailScreenState = EmailScreenState(),
    ) = screen(
        flow = "SignUp",
        stage = "Email",
        version = "1",
        template = "",
        shouldCache = false,
        content =  listOf(
            topAppBar(
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                paddingHorizontalProperty = PaddingHorizontalProperty(20),
                title = listOf(
                    text(
                        textProperty = TextProperty("Email"),
                    )
                )
            ),
            lazyColumn(
                verticalArrangementProperty = VerticalArrangementProperty(VerticalArrangementOption.SpaceBetween),
                weightProperty = WeightProperty(1f),
                paddingVerticalProperty = PaddingVerticalProperty(20),
                content =  listOf(
                    outlinedTextInput(
                        textProperty = TextProperty(state.email, "SignUp.Email.emailInput"),
                        errorProperty = ErrorProperty(state.isError, "SignUp.Email.emailInput.isError"),
                        errorMessageProperty = ErrorMessageProperty("Email já cadastrado"),
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        paddingHorizontalProperty = PaddingHorizontalProperty(20),
                        labelProperty = LabelProperty("Digite seu email"),
                        validators = listOf(
                            emailValidator(
                                id = "SignUp.Email.isEmailValid",
                                emails = listOf("SignUp.Email.emailInput"),
                            ),
                        )
                    ),
                    column(
                        horizontalAlignmentProperty = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
                        paddingHorizontalProperty = PaddingHorizontalProperty(20),
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        content =  listOf(
                            button(
                                textProperty = TextProperty("Continuar"),
                                enabledProperty = EnabledProperty(false, "SignUp.Email.isEmailValid"),
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
                            outlinedButton(
                                textProperty = TextProperty("Fechar"),
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