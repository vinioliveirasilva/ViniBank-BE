package com.vinibank.backend.sdui.flow

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
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

    fun property(name: String, value: Boolean, id: String? = null): Property = buildJsonObject {
        put("name", name)
        put("value", value)
        id?.run { put("id", this) }
    }

    fun property(name: String, value: Number, id: String? = null): Property = buildJsonObject {
        put("name", name)
        put("value", value)
        id?.run { put("id", this) }
    }

    fun action(
        type: String,
        data: JsonObject? = null,
        id: String? = null,
        name: String = "OnClick",//TODO
    ): Action = buildJsonObject {
        put("type", type)
        put("name", name)
        id?.run { put("id", this) }
        data?.run { put("data", this) }
    }

    fun multipleActions(
        actions: List<Action>,
    ): Action = action(
        type = "multipleActions",
        data = jsonObject(
            "actions" to buildJsonArray {
                actions.mapIndexed { index, action ->
                    add(
                        buildJsonObject {
                            action.jsonObject.entries.forEach { (key, value) ->
                                put(key, value)
                            }
                            put("name", index)
                        }
                    )
                }
            }
        )
    )

    fun validator(
        type: String,
        id: String,
        required: List<String>? = null,
        data: JsonObject? = null,
    ): Action = buildJsonObject {
        put("type", type)
        put("id", id)
        required?.run { putJsonArray("required") { forEach { add(JsonPrimitive(it)) } } }
        put("data", data ?: jsonObject())
    }

    fun component(
        type: String,
        properties: List<Property>? = null,
        components: List<Component>? = null,
        actions: List<Action>? = null,
        validators: List<Validator>? = null,
        vararg customComponents: Pair<String, List<Component>>,
    ): Component = buildJsonObject {
        put("type", type)
        properties?.run { putJsonArray("properties") { forEach { add(it) } } }
        components?.run { putJsonArray("components") { forEach { add(it) } } }
        validators?.run { putJsonArray("validators") { forEach { add(it) } } }
        actions?.run { putJsonArray("actions") { forEach { add(it) } } }
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
        cacheStrategy: JsonObject? = null,
        components: List<Component>? = null,
    ) = buildJsonObject {
        put("flow", flow)
        put("stage", stage)
        put("version", version)
        put("template", template)
        put("shouldCache", shouldCache)
        cacheStrategy?.let { put("cacheStrategy", it) }
        components?.run { putJsonArray("components") { forEach { add(it) } } }
    }

    fun jsonObject(vararg pairs: Pair<String, Any?>) = buildJsonObject {
        pairs.forEach { (name, value) ->
            when (value) {
                is String -> put(name, value)
                is Boolean -> put(name, value)
                is Number -> put(name, value)
                is JsonElement -> put(name, value)
            }
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun jsonArray(vararg actions: Action) = buildJsonArray {
        actions.forEach { add(it) }
    }
}