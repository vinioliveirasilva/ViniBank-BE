package com.vinibank.backend.sdui.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ValidatorModel(
    @SerialName("type") val type : String,
    @SerialName("data") val data : Map<String, String> = emptyMap(),
    @SerialName("id") val id : String,
    @SerialName("required") val required : List<String>,
)