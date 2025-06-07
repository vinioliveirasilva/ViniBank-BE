package com.vinibank.backend.model

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

data class SdUiResponse(
    @SerializedName("version") val version: String,
    @SerializedName("name") val name: String,
    @SerializedName("components") val components: JsonArray
)