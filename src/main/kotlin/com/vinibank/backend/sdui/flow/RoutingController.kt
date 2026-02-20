package com.vinibank.backend.sdui.flow

import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component

@Component
class RoutingController(
    private val sdUiFlowControllers: List<SdUiFlowController>,
) {

    fun getSdUiScreen(sdUiRequest: SdUiRequest) = getInternalScreen(sdUiRequest).build()

    fun getSdUiScreenUpdate(sdUiRequest: UpdateSdUiTemplateRequest) = getInternalScreenUpdate(sdUiRequest).map { it.build(it.hashCode()) }

    fun getSdUiComponents(sdUiRequest: SdUiRequest) = getInternalScreen(sdUiRequest).content
    fun getTemplate(sdUiRequest: SdUiRequest) = getInternalScreen(sdUiRequest)

    fun getSdUiComponentsUpdate(sdUiRequest: UpdateSdUiTemplateRequest) = getInternalScreenUpdate(sdUiRequest)


    private fun getInternalScreenUpdate(sdUiRequest: UpdateSdUiTemplateRequest) = sdUiFlowControllers.firstOrNull {
        it.flowId == sdUiRequest.flow
    }?.getScreenUpdate(sdUiRequest).orEmpty()

    private fun getInternalScreen(sdUiRequest: SdUiRequest) = sdUiFlowControllers.firstOrNull {
        it.flowId == sdUiRequest.flow
    }?.getScreen(sdUiRequest) ?: getUndefinedScreen(sdUiRequest)
}