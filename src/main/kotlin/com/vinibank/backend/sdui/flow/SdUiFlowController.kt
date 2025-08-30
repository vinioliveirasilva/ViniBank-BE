package com.vinibank.backend.sdui.flow

import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.JsonObject

interface SdUiFlowController {
    val flowId: String

    fun getScreen(request: SdUiRequest): JsonObject?
}

open class BaseFlowController <T : SdUiScreen>(
    private val screens: List<T>,
    private val defaultScreen: T,
    override val flowId: String,
) : SdUiFlowController {

    override fun getScreen(request: SdUiRequest): JsonObject? {
        return if (request.fromScreen.isBlank()) {
            defaultScreen.getScreen(request)
        } else {
            executeRule(request) ?: executeScreen(request)
        }
    }

    private fun executeRule(request: SdUiRequest) =
        screens.firstOrNull { it.screenId == request.fromScreen }?.getRule(request)

    private fun executeScreen(request: SdUiRequest) =
        screens.firstOrNull { it.screenId == request.toScreen }?.getScreen(request) ?: getUndefinedScreen(request)
}