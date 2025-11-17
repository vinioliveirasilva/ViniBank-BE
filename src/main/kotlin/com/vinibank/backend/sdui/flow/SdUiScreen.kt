package com.vinibank.backend.sdui.flow

import com.vini.designsystemsdui.template.Template
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component

@Component
interface SdUiScreen {
    val screenId: String
    fun getScreen(request: SdUiRequest): Template?
    fun getRule(request: SdUiRequest) = Unit
}