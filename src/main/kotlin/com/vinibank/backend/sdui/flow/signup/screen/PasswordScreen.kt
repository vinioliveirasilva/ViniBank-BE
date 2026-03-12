package com.vinibank.backend.sdui.flow.signup.screen

import com.vini.designsystemsdui.core.SdUiNode.Template
import com.vini.designsystemsdui.ui.action.BackAction
import com.vini.designsystemsdui.ui.action.ContinueAction
import com.vini.designsystemsdui.ui.component.Button
import com.vini.designsystemsdui.ui.component.Column
import com.vini.designsystemsdui.ui.component.CreatePassword
import com.vini.designsystemsdui.ui.component.OutlinedButton
import com.vini.designsystemsdui.ui.component.Spacer
import com.vini.designsystemsdui.ui.component.Text
import com.vini.designsystemsdui.ui.component.TopAppBar
import com.vini.designsystemsdui.ui.modifier.Modifier
import com.vini.designsystemsdui.ui.modifier.fillMaxHeight
import com.vini.designsystemsdui.ui.modifier.fillMaxWidth
import com.vini.designsystemsdui.ui.modifier.option.HorizontalAlignmentOption
import com.vini.designsystemsdui.ui.modifier.padding
import com.vini.designsystemsdui.ui.modifier.size
import com.vini.designsystemsdui.ui.modifier.option.VerticalArrangementOption
import com.vini.designsystemsdui.InteractionId
import com.vini.designsystemsdui.ui.data.ButtonInteractionModel
import com.vini.designsystemsdui.ui.data.CreatePasswordInteractionModel
import com.vini.designsystemsdui.ui.modifier.fillMaxSize
import com.vini.designsystemsdui.ui.template.ScreenTemplate
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
        return ScreenTemplate(
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
                            Text(text = "Criar Senha")
                        }
                    )
                    Spacer(modifier = Modifier.size(20))
                    CreatePassword(
                        interactionModel = CreatePasswordInteractionModel(
                            text = passwordInputId,
                            isPasswordValid = isPasswordValid
                        ),
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20),
                        text = "",
                        isPasswordValid = false
                    )
                    Column(
                        modifier = Modifier.padding(horizontal = 20).fillMaxWidth()
                            .fillMaxHeight(),
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
                                modifier = Modifier.fillMaxWidth(),
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
                                modifier = Modifier.fillMaxWidth(),
                                content = {
                                    Text(text = "Voltar")
                                },
                                onClickAction = BackAction()
                            )
                        }
                    )
                    Spacer(modifier = Modifier.size(20))
                }
            }
        )
    }
}
