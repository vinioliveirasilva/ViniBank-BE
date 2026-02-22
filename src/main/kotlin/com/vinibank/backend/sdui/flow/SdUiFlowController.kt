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
            defaultScreen.getScreen(request = request, screenId = request.toScreen)
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

    private fun executeScreen(request: SdUiRequest) : Template {
        val parameters = getQueryParameters(request.toScreen)
        val parsedScreenId = request.toScreen.stripQueryParameters()

        return screens.firstOrNull { it.screenId == parsedScreenId }?.getScreen(
            request = request,
            parameters = parameters,
            screenId = request.toScreen
        ) ?: getUndefinedScreen(request)
    }

    private fun getQueryParameters(screenId: String) : Map<String, String> {
        val parameters = screenId.split("?").last()
        if(parameters == screenId) return emptyMap()
        return parameters.split("&").associate {
            val (key, value) = it.split("=")
            key to value
        }.toMap()
    }

    private fun String.stripQueryParameters() = split("?").first()
}