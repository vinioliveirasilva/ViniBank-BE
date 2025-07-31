package com.vinibank.backend.sdui.flow.login.screen

import kotlinx.serialization.Serializable

@Serializable
data class LoginScreenState(
    val email: String,
    val password: String,
)