package com.vinibank.backend.sdui.flow

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray

typealias Property = JsonObject
typealias Component = JsonObject
typealias Validator = JsonObject
typealias Action = JsonObject

object ScreenUtil {
    fun property(name: String, value: String, id: String? = null): Property = buildJsonObject {
        put("name", name)
        put("value", value)
        id?.run { put("id", this) }
    }

    fun action(
        type: String,
        data: JsonObject? = null,
    ): Action = buildJsonObject {
        put("type", type)
        data?.run { put("data", this) }
    }

    fun validator(
        type: String,
        id: String,
        required: List<String>? = null,
        data: JsonObject? = null
    ): Action = buildJsonObject {
        put("type", type)
        put("id", id)
        required?.run { putJsonArray("required") { forEach { add(JsonPrimitive(it)) } } }
        data?.run { put("data", this) }
    }

    fun component(
        type: String,
        properties: List<Property>? = null,
        components: List<Component>? = null,
        action: Action? = null,
        validators: List<Validator>? = null,
        vararg customComponents: Pair<String, List<Component>>
    ): Component = buildJsonObject {
        put("type", type)
        properties?.run { putJsonArray("properties") { forEach { add(it) } } }
        components?.run { putJsonArray("components") { forEach { add(it) } } }
        validators?.run { putJsonArray("validators") { forEach { add(it) } } }
        action?.run { put("action", this) }
        customComponents.forEach { (name, component) ->
            putJsonArray(name) {
                component.forEach {
                    add(
                        it
                    )
                }
            }
        }
    }

    fun screen(
        flow: String,
        stage: String,
        version: String,
        template: String,
        shouldCache: Boolean,
        components: List<Component>? = null,
    ) = buildJsonObject {
        put("flow", flow)
        put("stage", stage)
        put("version", version)
        put("template", template)
        put("shouldCache", shouldCache)
        components?.run { putJsonArray("components") { forEach { add(it) } } }
    }

    fun jsonObject(vararg pairs: Pair<String, Any?>) = buildJsonObject {
        pairs.forEach { (name, value) ->
            when (value) {
                is String -> put(name, value)
                is JsonElement -> put(name, value)
            }
        }
    }
}