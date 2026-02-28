package com.vinibank.backend.sdui.flow

import com.vini.designsystemsdui.Template
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component

@Component
interface SdUiScreen {
    val screenId: String
    fun getScreen(
        request: SdUiRequest,
        parameters: Map<String, String> = emptyMap(),
        screenId: String,
    ): Template?

    fun getRule(request: SdUiRequest) = Unit

    fun getScreenUpdate(request: UpdateSdUiTemplateRequest): List<com.vini.designsystemsdui.Component> =
        emptyList()
}