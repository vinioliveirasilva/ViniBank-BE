package com.vinibank.backend.sdui.model


import com.google.gson.annotations.SerializedName

data class ComponentModel(
    @SerializedName("staticProperty")
    val staticProperty: Map<String, String> = mapOf(),
    @SerializedName("dynamicProperty")
    val dynamicProperty: List<PropertyModel> = emptyList(),
    @SerializedName("type")
    val type: String,
    @SerializedName("components")
    val components: List<ComponentModel> = emptyList(),
    @SerializedName("action")
    val action: ActionModel? = null,
    @SerializedName("validator")
    val validators: List<ValidatorModel> = emptyList()
)