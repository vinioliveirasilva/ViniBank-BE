package com.vinibank.backend.sdui.model

import com.google.gson.annotations.SerializedName

data class SdUiRequest(
    @SerializedName("currentFlow")
    val currentFlow: String,
    @SerializedName("currentStage")
    val currentStage: String,
    @SerializedName("nextStage")
    val nextStage: String,
    @SerializedName("flowData")
    val flowData: String,
)