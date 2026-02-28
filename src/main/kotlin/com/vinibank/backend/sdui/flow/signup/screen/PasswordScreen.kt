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
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.size
import com.vini.designsystemsdui.property.EnabledProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.ValidPasswordProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.property.util.PropertyIdWrapper
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

    private val passwordInputId = PropertyIdWrapper<String>("SignUp.${screenId}.passwordInput")
    private val isPasswordValid = PropertyIdWrapper<Boolean>("SignUp.${screenId}.isPasswordValid")

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
        val screenFlowId = "${request.flow}.${screenId}"

        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            content = listOf(
                TopAppBar(
                    modifier = SdUiModifier().fillMaxWidth().padding(horizontal = 20),
                    title = listOf(
                        Text(textProperty = TextProperty("Criar Senha"))
                    )
                ),
                Spacer(
                    modifier = SdUiModifier().size(20)
                ),
                CreatePassword(
                    modifier = SdUiModifier().fillMaxWidth().padding(horizontal = 20),
                    textProperty = TextProperty("", passwordInputId),
                    validPasswordProperty = ValidPasswordProperty(false, isPasswordValid)
                ),
                Column(
                    modifier = SdUiModifier().padding(horizontal = 20).fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignmentProperty = HorizontalAlignmentProperty(
                        HorizontalAlignmentOption.Center
                    ),
                    weightProperty = WeightProperty(1f),
                    verticalArrangementProperty = VerticalArrangementProperty(
                        VerticalArrangementOption.Bottom
                    ),
                    content = listOf(
                        Button(
                            modifier = SdUiModifier().fillMaxWidth(),
                            content = listOf(
                                Text(textProperty = TextProperty(value = "Continuar"))
                            ),
                            enabledProperty = EnabledProperty(false, isPasswordValid),
                            onClick = ContinueAction(
                                flowId = request.flow,
                                currentScreenId = screenId,
                                nextScreenId = "Success",
                                screenData = request.screenData,
                                screenRequestData = listOf(
                                    passwordInputId.id to "password"
                                )
                            ),
                        ),
                        OutlinedButton(
                            modifier = SdUiModifier().fillMaxWidth(),
                            content = listOf(
                                Text(textProperty = TextProperty(value = "Voltar"))
                            ),
                            onClick = BackAction()
                        ),
                    )
                ),
                Spacer(modifier = SdUiModifier().size(20)),
            )
        )
    }
}