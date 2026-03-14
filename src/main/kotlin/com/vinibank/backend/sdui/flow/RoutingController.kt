package com.vinibank.backend.sdui.flow

import com.vini.designsystemsdui.core.SdUiNode
import com.vinibank.backend.sdui.flow.login.LoginController
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component

@Component
class RoutingController(
    private val sdUiFlowControllers: List<SdUiFlowController>,
    private val mainFlow: LoginController,
) {

    fun getSdUiScreen(sdUiRequest: SdUiRequest) = getInternalScreen(sdUiRequest)

    fun getSdUiScreenUpdate(sdUiRequest: UpdateSdUiTemplateRequest) =
        getInternalScreenUpdate(sdUiRequest)

    fun getTemplate(sdUiRequest: SdUiRequest) = getInternalScreen(sdUiRequest)


    private fun getInternalScreenUpdate(sdUiRequest: UpdateSdUiTemplateRequest) =
        sdUiFlowControllers.firstOrNull {
            it.flowId == sdUiRequest.flow
        }?.getScreenUpdate(sdUiRequest).orEmpty()

    private fun getInternalScreen(sdUiRequest: SdUiRequest) : SdUiNode.Template {
        if(sdUiRequest.flow.isEmpty()) {
            return mainFlow.getScreen(sdUiRequest) ?: getUndefinedScreen(sdUiRequest)
        }

        return sdUiFlowControllers.firstOrNull {
            it.flowId == sdUiRequest.flow
        }?.getScreen(sdUiRequest) ?: getUndefinedScreen(sdUiRequest)
    }
}