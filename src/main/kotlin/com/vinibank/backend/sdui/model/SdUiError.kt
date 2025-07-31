package com.vinibank.backend.sdui.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class SdUiError(
    @SerialName("message")
    override val message: String,
    @SerialName("code")
    val code: Int,
    @SerialName("screen")
    val screen: JsonObject,
) : Exception(message)