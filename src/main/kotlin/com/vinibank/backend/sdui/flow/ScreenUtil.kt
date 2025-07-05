package com.vinibank.backend.sdui.flow

typealias Property = Map<String, String>
typealias Component = Map<String, Any>
typealias Validator = Map<String, String>
typealias Action = Map<String, Any>

object ScreenUtil {
    fun property(name: String, value: String, id: String = ""): Property = mapOf(
        "name" to name,
        "value" to value,
        "id" to id,
    )

    fun action(type: String, data: Map<String, String>): Action = mapOf(
        "type" to type,
        "data" to data
    )

    fun component(
        type: String,
        properties: List<Property> = emptyList(),
        components: List<Component> = emptyList(),
        action: Action = emptyMap(),
        validators: List<Validator> = emptyList(),
        vararg customComponents: Pair<String, List<Component>>
    ): Component = mutableMapOf(
        "type" to type,
        "properties" to properties,
        "components" to components,
        "action" to action,
        "validators" to validators,
    ).apply {
        customComponents.forEach { (name, component) ->
            put(name, component)
        }
    }.toMap()

    fun screen(
        flow: String,
        stage: String,
        version: String,
        template: String,
        shouldCache: Boolean,
        components: List<Component> = emptyList(),
    ) = mapOf(
        "flow" to flow,
        "stage" to stage,
        "version" to version,
        "template" to template,
        "shouldCache" to shouldCache,
        "components" to components,
    )
}