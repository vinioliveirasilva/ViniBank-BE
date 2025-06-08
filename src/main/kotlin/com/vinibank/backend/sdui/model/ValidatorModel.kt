package com.vinibank.backend.sdui.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ValidatorModel(
    @SerializedName("type") val type : String,
    @SerializedName("data") val data : Map<String, String> = emptyMap(),
    @SerializedName("id") val id : String,
    @SerializedName("required") val required : List<String>,
)