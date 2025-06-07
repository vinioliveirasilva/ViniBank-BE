package com.vinibank.backend.model

import com.google.gson.annotations.SerializedName

data class SdUiError(
    @SerializedName("message")
    val message: String,
    @SerializedName("code")
    val code: Int,
    @SerializedName("screen")
    val screen: ScreenModel,
)