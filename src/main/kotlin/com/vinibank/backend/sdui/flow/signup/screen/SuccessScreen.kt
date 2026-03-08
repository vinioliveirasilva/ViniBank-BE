package com.vinibank.backend.sdui.flow.signup.screen

import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.BusinessSuccessAction
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.component.TopAppBar
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.fillMaxHeight
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.sdui.flow.signup.SignUpScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component

@Component
class SuccessScreen : SignUpScreen {
    override val screenId: String = "Success"

    override fun getScreen(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? = DefaultTemplate(
        flow = request.flow,
        stage = screenId,
        version = "1",
        content = {
            TopAppBar(
                modifier = SdUiModifier().fillMaxWidth().padding(horizontal = 20),
                title = {
                    Text(text = "Conta Criada com Sucesso")
                }
            )
            Column(
                modifier = SdUiModifier().padding(horizontal = 20).fillMaxWidth().fillMaxHeight(),
                content = {
                    Text(
                        modifier = SdUiModifier().padding(vertical = 24),
                        text = "Seu cadastro foi concluído."
                    )
                    Button(
                        modifier = SdUiModifier().fillMaxWidth(),
                        content = {
                            Text(text = "Continuar")
                        },
                        onClickAction = BusinessSuccessAction(screenData = request.screenData)
                    )
                }
            )
        }
    )
}
