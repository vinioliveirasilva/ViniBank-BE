package com.vinibank.backend.sdui.flow.signup.screen

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PasswordScreenState(
    @SerialName("password") val password: String,
    @SerialName("email") val email: String,
    @SerialName("name") val name: String,
    @SerialName("document") val document: String,
    @SerialName("phone") val phone: String,
)