package com.vinibank.backend.sdui.flow.home.screens

import com.vinibank.backend.sdui.SdUiScreen
import com.vinibank.backend.sdui.components.topBarWithBackAction
import com.vinibank.backend.sdui.flow.ScreenUtil.action
import com.vinibank.backend.sdui.flow.ScreenUtil.component
import com.vinibank.backend.sdui.flow.ScreenUtil.jsonObject
import com.vinibank.backend.sdui.flow.ScreenUtil.property
import com.vinibank.backend.sdui.flow.ScreenUtil.screen
import kotlinx.serialization.json.JsonObject

object UserDetailScreen : SdUiScreen {
    override fun getScreenModel(screenData: JsonObject?): JsonObject {
        fun menuItem(name: String, icon: String? = null) = component(
            type = "column",
            properties = listOf(
                property("horizontalFillType", "Max"),
                property("paddingHorizontal", "20"),
                property("verticalArrangement", "Center"),
            ),
            components = listOf(
                component(
                    type = "row",
                    properties = listOf(
                        property("horizontalFillType", "Max"),
                        property("paddingVertical", "10"),
                        property("horizontalArrangement", "SpaceBetween"),
                        property("verticalAlignment", "Center"),
                    ),
                    components = listOf(
                        component(
                            type = "row",
                            properties = listOf(
                                property("verticalAlignment", "Center"),
                            ),
                            components = listOfNotNull(
                                icon?.let {
                                    component(
                                        type = "icon",
                                        properties = listOf(
                                            property("icon", it),
                                            property(
                                                "paddingHorizontal",
                                                "10"
                                            ),
                                            property("size", "48"),
                                        )
                                    )
                                },
                                component(
                                    type = "text",
                                    properties = listOf(
                                        property("text", name),
                                    )
                                ),
                            )
                        ),
                        component(
                            type = "icon",
                            properties = listOf(
                                property("icon", "RightArrow"),
                                property("paddingHorizontal", "10"),
                            )
                        )
                    )
                ),
                component(
                    type = "horizontalDivider",
                    properties = listOf()
                )
            ),
            action = action(
                "continue",
                jsonObject(
                    "flowId" to "TODO",
                    "nextScreenId" to "TODO",
                    "currentScreenId" to "UserDetail"
                )
            ),
        )

        val content = component(
            type = "lazyColumn",
            properties = listOf(
                property("horizontalFillType", "Max"),
                property("weight", "1"),
            ),
            components = listOf(
                component(
                    type = "card",
                    properties = listOf(
                        property("paddingHorizontal", "20"),
                        property("paddingVertical", "10"),
                        property("horizontalFillType", "Max"),
                    ),
                    components = listOf(
                        component(
                            type = "column",
                            properties = listOf(
                                property("paddingVertical", "20"),
                                property("horizontalAlignment", "Center"),
                                property("horizontalFillType", "Max"),
                            ),
                            components = listOf(
                                component(
                                    type = "icon",
                                    properties = listOf(
                                        property("icon", "User"),
                                        property(
                                            "paddingVertical",
                                            "20"
                                        ),
                                        property("size", "96"),
                                    )
                                ),
                                component(
                                    type = "text",
                                    properties = listOf(
                                        property("text", "Vinicius Oliveira"),
                                        property("paddingHorizontal", "20"),
                                    )
                                ),
                                component(
                                    type = "text",
                                    properties = listOf(
                                        property("text", "vinioliveirasilva@hotmail.com"),
                                        property("paddingHorizontal", "20"),
                                    )
                                ),
                                component(
                                    type = "text",
                                    properties = listOf(
                                        property("text", "+55 11 9 77801285"),
                                        property("paddingHorizontal", "20"),
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

        val screen = screen(
            flow = "Home",
            stage = "UserDetail",
            version = "1",
            template = "",
            shouldCache = false,
            components = listOf(
                topBarWithBackAction("User Detail"),
                content,
            )
        )
        return screen
    }
}