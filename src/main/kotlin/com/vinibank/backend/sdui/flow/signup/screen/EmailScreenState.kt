package com.vinibank.backend.sdui.flow.signup.screen

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class EmailScreenState(
    @SerialName("email") val email: String = "",
    @Transient val isError: Boolean = false,
)