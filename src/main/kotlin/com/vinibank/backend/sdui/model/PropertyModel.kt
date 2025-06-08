package com.vinibank.backend.sdui.model


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class PropertyModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: String,
    @SerializedName("id")
    val id: String = "",
)