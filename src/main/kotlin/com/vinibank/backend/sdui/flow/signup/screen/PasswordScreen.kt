package com.vinibank.backend.sdui.flow.signup.screen

import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.BackAction
import com.vini.designsystemsdui.action.ContinueAction
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.CreatePassword
import com.vini.designsystemsdui.component.OutlinedButton
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.component.TopAppBar
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.fillMaxHeight
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.option.HorizontalAlignmentOption
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.size
import com.vini.designsystemsdui.modifier.option.VerticalArrangementOption
import com.vini.designsystemsdui.InteractionId
import com.vini.designsystemsdui.component.ButtonInteractionModel
import com.vini.designsystemsdui.component.CreatePasswordInteractionModel
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.db.UserDatabase
import com.vinibank.backend.sdui.flow.signup.SignUpScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import org.springframework.stereotype.Component

@Component
class PasswordScreen(
    private val userDb: UserDatabase,
) : SignUpScreen {
    override val screenId: String = "Password"

    private val passwordInputId = InteractionId<String>("SignUp.${screenId}.passwordInput")
    private val isPasswordValid = InteractionId<Boolean>("SignUp.${screenId}.isPasswordValid")

    override fun getRule(request: SdUiRequest) {
        val model = Json.decodeFromJsonElement<PasswordScreenState>(
            request.screenData ?: JsonObject(emptyMap())
        )
        userDb.addUser(model.name, model.email, model.password, model.phone)
    }

    override fun getScreen(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? {
        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            content = {
                TopAppBar(
                    modifier = SdUiModifier().fillMaxWidth().padding(horizontal = 20),
                    title = {
                        Text(text = "Criar Senha")
                    }
                )
                Spacer(modifier = SdUiModifier().size(20))
                CreatePassword(
                    interactionModel = CreatePasswordInteractionModel(
                        text = passwordInputId,
                        isPasswordValid = isPasswordValid
                    ),
                    modifier = SdUiModifier().fillMaxWidth().padding(horizontal = 20),
                    text = "",
                    isPasswordValid = false
                )
                Column(
                    modifier = SdUiModifier().padding(horizontal = 20).fillMaxWidth().fillMaxHeight(),
                    horizontalAlignment = (
                        HorizontalAlignmentOption.Center()
                    ),
                    verticalArrangement = (
                        VerticalArrangementOption.Bottom()
                    ),
                    content = {
                        Button(
                            interactionModel = ButtonInteractionModel(
                                enabled = isPasswordValid
                            ),
                            modifier = SdUiModifier().fillMaxWidth(),
                            content = {
                                Text(text = "Continuar")
                            },
                            enabled = false,
                            onClickAction = ContinueAction(
                                flowId = request.flow,
                                currentScreenId = screenId,
                                nextScreenId = "Success",
                                screenData = request.screenData,
                                screenRequestData = listOf(
                                    passwordInputId.id to "password"
                                )
                            ),
                        )
                        OutlinedButton(
                            modifier = SdUiModifier().fillMaxWidth(),
                            content = {
                                Text(text = "Voltar")
                            },
                            onClickAction = BackAction()
                        )
                    }
                )
                Spacer(modifier = SdUiModifier().size(20))
            }
        )
    }
}
