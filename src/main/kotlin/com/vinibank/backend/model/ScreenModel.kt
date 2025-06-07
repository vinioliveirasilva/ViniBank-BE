package com.vinibank.backend.model


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class ScreenModel(
    @SerializedName("shouldCache")
    val shouldCache: Boolean = true,
    @SerializedName("flow")
    val flow: String,
    @SerializedName("stage")
    val stage: String,
    @SerializedName("template")
    val template: String,
    @SerializedName("version")
    val version: String,
    @SerializedName("components")
    val components: List<ComponentModel>,
)