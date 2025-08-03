package com.vinibank.backend.sdui.flow.signup.screen

import com.vini.designsystemsdui.action.backAction
import com.vini.designsystemsdui.action.continueAction
import com.vini.designsystemsdui.component.button
import com.vini.designsystemsdui.component.column
import com.vini.designsystemsdui.component.createPassword
import com.vini.designsystemsdui.component.outlinedButton
import com.vini.designsystemsdui.component.spacer
import com.vini.designsystemsdui.component.text
import com.vini.designsystemsdui.component.topBar
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.IsEnabledProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.SizeProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.ValidPasswordProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.VerticalFillTypeProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.property.options.VerticalFillTypeOption
import com.vinibank.backend.db.UserDatabase
import com.vinibank.backend.sdui.flow.signup.SignUpScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import org.springframework.stereotype.Component

@Component
class PasswordScreen(
    private val userDb: UserDatabase,
) : SignUpScreen {
    override val screenId: String = "Password"

    override fun getRule(request: SdUiRequest): JsonObject? {
        val model = Json.decodeFromJsonElement<PasswordScreenState>(
            request.screenData ?: JsonObject(emptyMap())
        )
        userDb.addUser(model.name, model.email, model.password)
        return null
    }

    override fun getScreen(request: SdUiRequest): JsonObject? {
        val screenFlowId = "${request.flow}.${screenId}"

        return screen(
            flow = request.flow,
            stage = screenId,
            version = "1",
            template = "",
            shouldCache = false,
            components = listOf(
                topBar(
                    horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    paddingHorizontal = PaddingHorizontalProperty(20),
                    components = listOf(
                        text(textProperty = TextProperty("Criar Senha"))
                    )
                ),
                spacer(
                    size = SizeProperty(20)
                ),
                createPassword(
                    text = TextProperty("", "$screenFlowId.passwordInput"),
                    horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    paddingHorizontal = PaddingHorizontalProperty(20),
                    validPassword = ValidPasswordProperty(false, "$screenFlowId.isPasswordValid")
                ),
                column(
                    horizontalAlignment = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
                    paddingHorizontal = PaddingHorizontalProperty(20),
                    horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    verticalFillType = VerticalFillTypeProperty(VerticalFillTypeOption.Max),
                    weight = WeightProperty(1),
                    verticalArrangement = VerticalArrangementProperty(VerticalArrangementOption.Bottom),
                    components = listOf(
                        button(
                            text = TextProperty("Continuar"),
                            isEnabled = IsEnabledProperty(false, "$screenFlowId.isPasswordValid"),
                            horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                            actions = listOf(
                                continueAction(
                                    flowId = request.flow,
                                    currentScreenId = screenId,
                                    nextScreenId = "Success",
                                    screenData = request.screenData,
                                    screenRequestData = listOf(
                                        "$screenFlowId.passwordInput" to "password"
                                    )
                                ),
                            )
                        ),
                        outlinedButton(
                            text = TextProperty("Voltar"),
                            horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                            actions = listOf(
                                backAction()
                            )
                        ),
                    )
                ),
                spacer(size = SizeProperty(20)),
            )
        )
    }
}