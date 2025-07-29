package com.vinibank.backend.sdui.flow.home.screens

import com.vinibank.backend.sdui.SdUiScreen
import com.vinibank.backend.sdui.flow.ScreenUtil.action
import com.vinibank.backend.sdui.flow.ScreenUtil.component
import com.vinibank.backend.sdui.flow.ScreenUtil.jsonObject
import com.vinibank.backend.sdui.flow.ScreenUtil.property
import com.vinibank.backend.sdui.flow.ScreenUtil.screen
import kotlinx.serialization.json.JsonObject

object BalanceScreen : SdUiScreen {
    override fun getScreenModel(screenData: JsonObject?): JsonObject {
        val screenObj = screen(
            flow = "Home",
            stage = "Balance",
            version = "1",
            template = "",
            shouldCache = false,
            components = listOf(
                component(
                    "row",
                    listOf(
                        property("horizontalFillType", "Max"),
                        property("verticalFillType", "Max"),
                        property("verticalAlignment", "Center"),
                    ),
                    listOf(
                        component(
                            "spacer",
                            listOf(
                                property("weight", "1"),
                            )
                        ),
                        component(
                            "column",
                            listOf(
                                property("paddingHorizontal", "10"),
                                property("paddingVertical", "10"),
                                property("horizontalAlignment", "Center"),
                                property("weight", "10"),
                            ),
                            listOf(
                                component(
                                    "text",
                                    listOf(
                                        property("text", "Balance"),
                                    )
                                ),
                                component(
                                    "text",
                                    listOf(
                                        property("text", "R$ 100,00"),
                                    )
                                ),
                                component(
                                    "text",
                                    listOf(
                                        property("text", "updated 10 min ago"),
                                    )
                                )
                            )
                        ),
                        component(
                            "iconButton",
                            listOf(
                                property("weight", "1"),
                            ),
                            listOf(
                                component(
                                    "icon",
                                    listOf(
                                        property("icon", "Autorenew"),
                                    )
                                ),
                            ),
                            actions = listOf(
                                action(
                                    type = "toBoolean",
                                    data = jsonObject(
                                        "id" to "requestUpdate1",
                                        "value" to true
                                    )
                                )
                            )
                        ),
                    )
                )
            )
        )
        return screenObj
    }
}