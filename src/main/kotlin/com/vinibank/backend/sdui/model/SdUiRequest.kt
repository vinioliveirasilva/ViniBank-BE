package com.vinibank.backend.sdui.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class SdUiRequest(
    @SerialName("flow")
    val flow: String,
    @SerialName("fromScreen")
    val fromScreen: String,
    @SerialName("toScreen")
    val toScreen: String,
    @SerialName("screenData")
    val screenData: JsonObject?,
)