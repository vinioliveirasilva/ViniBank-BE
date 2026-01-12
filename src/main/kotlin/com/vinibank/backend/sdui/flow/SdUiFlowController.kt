package com.vinibank.backend.sdui.flow

import com.vini.designsystemsdui.Component
import com.vini.designsystemsdui.Template
import com.vinibank.backend.sdui.model.SdUiRequest

interface SdUiFlowController {
    val flowId: String

    fun getScreen(request: SdUiRequest): Template?
    fun getScreenUpdate(request: UpdateSdUiTemplateRequest): List<Component> = emptyList()
}

open class BaseFlowController <T : SdUiScreen>(
    private val screens: List<T>,
    private val defaultScreen: T,
    override val flowId: String,
) : SdUiFlowController {

    override fun getScreen(request: SdUiRequest): Template? {
        return if (request.toScreen == "Start") {
            defaultScreen.getScreen(request)
        } else {
            executeRule(request)
            executeScreen(request)
        }
    }

    override fun getScreenUpdate(request: UpdateSdUiTemplateRequest): List<Component> {
        if(request.screenData.isNotEmpty()) {
            executeRule(request.toSdUiRequest())
        }
        return screens.firstOrNull { it.screenId == request.toScreen }?.getScreenUpdate(request) ?: emptyList()
    }

    private fun executeRule(request: SdUiRequest) =
        screens.firstOrNull { it.screenId == request.fromScreen }?.getRule(request)

    private fun executeScreen(request: SdUiRequest) =
        screens.firstOrNull { it.screenId == request.toScreen }?.getScreen(request) ?: getUndefinedScreen(request)
}