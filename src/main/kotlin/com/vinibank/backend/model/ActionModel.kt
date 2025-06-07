package com.vinibank.backend.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class ActionModel(
    @SerializedName("type")
    val type: String,
    @SerializedName("data")
    val data: Map<String, String> = mapOf()
)