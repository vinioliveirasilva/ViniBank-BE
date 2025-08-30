package com.vinibank.backend.sdui.flow

import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import org.springframework.stereotype.Component


@Component
class RoutingController(
    private val sdUiFlowControllers: List<SdUiFlowController>,
) {

    fun getSdUiScreen(sdUiRequest: SdUiRequest) = sdUiFlowControllers.firstOrNull {
        it.flowId == sdUiRequest.flow
    }?.getScreen(sdUiRequest) ?: getUndefinedScreen(sdUiRequest)

    fun getSdUiComponents(sdUiRequest: SdUiRequest) = getSdUiScreen(sdUiRequest)["components"]?.jsonArray?.map { it.jsonObject } ?: emptyList()
}