package com.vinibank.backend.sdui.flow.signup.screen

import com.vini.designsystemsdui.action.closeAction
import com.vini.designsystemsdui.action.continueAction
import com.vini.designsystemsdui.component.button
import com.vini.designsystemsdui.component.column
import com.vini.designsystemsdui.component.lazyColumn
import com.vini.designsystemsdui.component.outlinedButton
import com.vini.designsystemsdui.component.outlinedTextInput
import com.vini.designsystemsdui.component.text
import com.vini.designsystemsdui.component.topBar
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
        components = listOf(
            topBar(
                horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                paddingHorizontal = PaddingHorizontalProperty(20),
                components = listOf(
                    text(
                        textProperty = TextProperty("Email"),
                    )
                )
            ),
            lazyColumn(
                verticalArrangement = VerticalArrangementProperty(VerticalArrangementOption.SpaceBetween),
                weight = WeightProperty(1f),
                paddingVertical = PaddingVerticalProperty(20),
                components = listOf(
                    outlinedTextInput(
                        text = TextProperty(state.email, "SignUp.Email.emailInput"),
                        error = ErrorProperty(state.isError, "SignUp.Email.emailInput.isError"),
                        errorMessage = ErrorMessageProperty("Email já cadastrado"),
                        horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        paddingHorizontal = PaddingHorizontalProperty(20),
                        label = LabelProperty("Digite seu email"),
                        validators = listOf(
                            emailValidator(
                                id = "SignUp.Email.isEmailValid",
                                emails = listOf("SignUp.Email.emailInput"),
                            ),
                        )
                    ),
                    column(
                        horizontalAlignment = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
                        paddingHorizontal = PaddingHorizontalProperty(20),
                        horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        components = listOf(
                            button(
                                text = TextProperty("Continuar"),
                                isEnabled = EnabledProperty(false, "SignUp.Email.isEmailValid"),
                                horizontalFillType = HorizontalFillTypeProperty(
                                    HorizontalFillTypeOption.Max
                                ),
                                actions = listOf(
                                    continueAction(
                                        flowId = "SignUp",
                                        nextScreenId = "PersonalInfo",
                                        currentScreenId = "Email",
                                        screenRequestData = listOf(
                                            "SignUp.Email.emailInput" to "email"
                                        ),
                                        screenData = request.screenData,
                                    ),
                                )
                            ),
                            outlinedButton(
                                text = TextProperty("Fechar"),
                                horizontalFillType = HorizontalFillTypeProperty(
                                    HorizontalFillTypeOption.Max
                                ),
                                actions = listOf(
                                    closeAction()
                                )
                            )
                        )
                    )
                )
            )
        )
    )
}