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
                    mapOf("name" to "verticalArrangement", "value" to "Center"),
                ),
                "components" to listOf(
                    mapOf(
                        "type" to "row",
                        "properties" to listOf(
                            mapOf("name" to "horizontalFillType", "value" to "Max"),
                            mapOf("name" to "paddingVertical", "value" to "10"),
                            mapOf("name" to "horizontalArrangement", "value" to "SpaceBetween"),
                            mapOf("name" to "verticalAlignment", "value" to "Center"),
                        ),
                        "components" to listOf(
                            mapOf(
                                "type" to "row",
                                "properties" to listOf(
                                    mapOf("name" to "verticalAlignment", "value" to "Center"),
                                ),
                                "components" to listOfNotNull(
                                    icon?.let {
                                        mapOf(
                                            "type" to "icon",
                                            "properties" to listOf(
                                                mapOf("name" to "icon", "value" to it),
                                                mapOf(
                                                    "name" to "paddingHorizontal",
                                                    "value" to "10"
                                                ),
                                                mapOf("name" to "size", "value" to "48"),
                                            )
                                        )
                                    },
                                    mapOf(
                                        "type" to "text",
                                        "properties" to listOf(
                                            mapOf("name" to "text", "value" to name),
                                        )
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
                        "flowId" to "TODO",
                        "nextScreenId" to "TODO",
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
                    mapOf(
                        "type" to "card",
                        "properties" to listOf(
                            mapOf("name" to "paddingHorizontal", "value" to "20"),
                            mapOf("name" to "paddingVertical", "value" to "10"),
                            mapOf("name" to "horizontalFillType", "value" to "Max"),
                        ),
                        "components" to listOf(
                            mapOf(
                                "type" to "column",
                                "properties" to listOf(
                                    mapOf("name" to "paddingVertical", "value" to "20"),
                                    mapOf("name" to "horizontalAlignment", "value" to "Center"),
                                    mapOf("name" to "horizontalFillType", "value" to "Max"),
                                ),
                                "components" to listOf(
                                    mapOf(
                                        "type" to "icon",
                                        "properties" to listOf(
                                            mapOf("name" to "icon", "value" to "User"),
                                            mapOf(
                                                "name" to "paddingVertical",
                                                "value" to "20"
                                            ),
                                            mapOf("name" to "size", "value" to "96"),
                                        )
                                    ),
                                    mapOf(
                                        "type" to "text",
                                        "properties" to listOf(
                                            mapOf("name" to "text", "value" to "Vinicius Oliveira"),
                                            mapOf("name" to "paddingHorizontal", "value" to "20"),
                                        )
                                    ),
                                    mapOf(
                                        "type" to "text",
                                        "properties" to listOf(
                                            mapOf("name" to "text", "value" to "vinioliveirasilva@hotmail.com"),
                                            mapOf("name" to "paddingHorizontal", "value" to "20"),
                                        )
                                    ),
                                    mapOf(
                                        "type" to "text",
                                        "properties" to listOf(
                                            mapOf("name" to "text", "value" to "+55 11 9 77801285"),
                                            mapOf("name" to "paddingHorizontal", "value" to "20"),
                                        )
                                    )
                                )
                            )
                        )
                    ),
                    menuItem("Dados Pessoais", "PersonSearch"),
                    menuItem("Privacidade de dados", "Lock"),
                    menuItem("Tema", "Theme"),
                    menuItem("Sair do App", "Logout"),
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