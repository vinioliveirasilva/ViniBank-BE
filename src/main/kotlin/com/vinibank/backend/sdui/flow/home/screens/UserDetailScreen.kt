package com.vinibank.backend.sdui.flow.home.screens

import com.vinibank.backend.sdui.SdUiScreen
import org.json.JSONObject

object UserDetailScreen : SdUiScreen {
    override fun getScreenModel(screenData: String): JSONObject {
        val topBar = JSONObject(
            mapOf(
                "type" to "topAppBar",
                "properties" to listOf<String>(),
                "components" to listOf(
                    mapOf(
                        "type" to "text",
                        "properties" to listOf(
                            mapOf(
                                "name" to "text",
                                "value" to "User Detail"
                            )
                        )
                    )
                ),
                "navigationIcons" to listOf(
                    mapOf(
                        "type" to "iconButton",
                        "properties" to listOf<String>(),
                        "action" to mapOf("type" to "back"),
                        "components" to listOf(
                            mapOf(
                                "type" to "icon",
                                "properties" to listOf(
                                    mapOf("name" to "icon", "value" to "LeftArrow"),
                                )
                            )
                        )
                    )
                )
            )
        )

        fun menuItem(name: String, icon: String? = null) = JSONObject(
            mapOf(
                "type" to "column",
                "properties" to listOf(
                    mapOf("name" to "horizontalFillType", "value" to "Max"),
                    mapOf("name" to "paddingHorizontal", "value" to "20"),
                ),
                "components" to listOf(
                    mapOf(
                        "type" to "row",
                        "properties" to listOf(
                            mapOf("name" to "horizontalFillType", "value" to "Max"),
                            mapOf("name" to "paddingVertical", "value" to "10"),
                            mapOf("name" to "horizontalArrangement", "value" to "SpaceBetween"),
                        ),
                        "components" to listOf(
                            mapOf(
                                "type" to "row",
                                "properties" to listOf<String>(),
                                "components" to listOf(
                                    icon?.let {
                                        mapOf(
                                            "type" to "icon",
                                            "properties" to listOf(
                                                mapOf("name" to "icon", "value" to it),
                                                mapOf("name" to "paddingHorizontal", "value" to "10"),
                                            )
                                        )
                                    },
                                    mapOf(
                                        "type" to "text",
                                        "properties" to listOf(
                                            mapOf("name" to "text", "value" to name),
                                        )
                                    ),
                                    mapOf(
                                        "type" to "spacer",
                                        "properties" to listOf(
                                            mapOf("name" to "size", "value" to "50")
                                        ),
                                    ),
                                )
                            ),
                            mapOf(
                                "type" to "icon",
                                "properties" to listOf(
                                    mapOf("name" to "icon", "value" to "RightArrow"),
                                    mapOf("name" to "paddingHorizontal", "value" to "10"),
                                )
                            )
                        )
                    ),
                    mapOf(
                        "type" to "horizontalDivider",
                        "properties" to listOf<String>()
                    )
                ),
                "action" to mapOf(
                    "type" to "continue",
                    "data" to mapOf(
                        "flowId" to "Home",
                        "nextScreenId" to "Home",
                        "currentScreenId" to "UserDetail"
                    )
                ),
            )
        )

        val content = JSONObject(
            mapOf(
                "type" to "lazyColumn",
                "properties" to listOf(
                    mapOf("name" to "horizontalFillType", "value" to "Max"),
                    mapOf("name" to "weight", "value" to "1"),
                ),
                "components" to listOf(
                    menuItem("teste1", "User"),
                    menuItem("teste2", "Logout"),
                )
            )
        )

        val screen = JSONObject(
            mapOf(
                "flow" to "Home",
                "stage" to "UserDetail",
                "version" to "1",
                "template" to "",
                "shouldCache" to false,
                "components" to listOf(
                    topBar,
                    content,
                )
            )
        )
        return screen
    }
}