package com.vinibank.backend.sdui.flow.investments.screen

import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vini.designsystemsdui.template.Template
import com.vinibank.backend.sdui.flow.investments.InvestmentsScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component

@Component
class FundsWalletScreen : InvestmentsScreen {
    override val screenId: String = "Fundos"

    override fun getScreen(request: SdUiRequest): Template? {
        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            template = "",
            content =  listOf(
                Text(
                    textProperty = TextProperty("Fundos")
                )
            )
        )
    }
}