package com.vinibank.backend.sdui.flow.signup.model

import com.google.gson.annotations.SerializedName

data class CreateAccountModel(
    @SerializedName( "password") val password: String,
    @SerializedName( "email") val email: String,
    @SerializedName( "name") val name: String,
    @SerializedName( "document") val document: String,
    @SerializedName( "phone") val phone: String,
)