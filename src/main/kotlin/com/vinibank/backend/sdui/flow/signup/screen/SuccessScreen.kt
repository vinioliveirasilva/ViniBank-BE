package com.vinibank.backend.sdui.flow.signup.screen

import com.vini.designsystemsdui.core.SdUiNode.Template
import com.vini.designsystemsdui.ui.action.BusinessSuccessAction
import com.vini.designsystemsdui.ui.component.Button
import com.vini.designsystemsdui.ui.component.Column
import com.vini.designsystemsdui.ui.component.Text
import com.vini.designsystemsdui.ui.component.TopAppBar
import com.vini.designsystemsdui.ui.modifier.Modifier
import com.vini.designsystemsdui.ui.modifier.fillMaxHeight
import com.vini.designsystemsdui.ui.modifier.fillMaxSize
import com.vini.designsystemsdui.ui.modifier.fillMaxWidth
import com.vini.designsystemsdui.ui.modifier.padding
import com.vini.designsystemsdui.ui.template.ScreenTemplate
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
    ): Template? = ScreenTemplate(
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
                        Text(text = "Conta Criada com Sucesso")
                    }
                )
                Column(
                    modifier = Modifier.padding(horizontal = 20).fillMaxWidth()
                        .fillMaxHeight(),
                    content = {
                        Text(
                            modifier = Modifier.padding(vertical = 24),
                            text = "Seu cadastro foi concluído."
                        )
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            content = {
                                Text(text = "Continuar")
                            },
                            onClickAction = BusinessSuccessAction(screenData = request.screenData)
                        )
                    }
                )
            }
        }
    )
}
