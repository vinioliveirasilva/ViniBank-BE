package com.vinibank.backend.sdui.oldflow

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray

typealias Component = JsonObject

object ScreenUtil {
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
}