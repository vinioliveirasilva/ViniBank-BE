package com.vinibank.backend.sdui.flow.investments.screen

import com.vini.designsystemsdui.component.text
import com.vini.designsystemsdui.property.TextProperty
import com.vinibank.backend.sdui.flow.investments.InvestmentsScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import com.vinibank.backend.sdui.oldflow.ScreenUtil
import kotlinx.serialization.json.JsonObject
import org.springframework.stereotype.Component

@Component
class FundsWalletScreen : InvestmentsScreen {
    override val screenId: String = "Fundos"

    override fun getScreen(request: SdUiRequest): JsonObject? {
        return ScreenUtil.screen(
            flow = request.flow,
            stage = screenId,
            version = "1",
            template = "",
            shouldCache = false,
            content =  listOf(
                text(
                    textProperty = TextProperty("Fundos")
                )
            )
        )
    }
}