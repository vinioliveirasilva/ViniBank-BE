package com.vinibank.backend.sdui.flow.home.screens

import com.vinibank.backend.sdui.SdUiScreen
import com.vinibank.backend.sdui.flow.ScreenUtil.action
import com.vinibank.backend.sdui.flow.ScreenUtil.component
import com.vinibank.backend.sdui.flow.ScreenUtil.jsonObject
import com.vinibank.backend.sdui.flow.ScreenUtil.property
import com.vinibank.backend.sdui.flow.ScreenUtil.screen
import com.vinibank.backend.sdui.flow.ScreenUtil.validator
import kotlinx.serialization.json.JsonObject

object HomeScreen : SdUiScreen {
    override fun getScreenModel(screenData: JsonObject?): JsonObject {
        val bottomNavigation = component(
            "navigationBar",
            properties = listOf(
                property("selectedDestination", "0", id = "bottomNavigation.selectedDestination"),
            ),
            components = listOf(
                component(
                    "navigationBarItem",
                    properties = listOf(
                        property("index", "0"),
                        property(
                            "selectedDestination",
                            "0",
                            id = "bottomNavigation.selectedDestination"
                        ),
                    ),
                    components = listOf(
                        component(
                            "text",
                            properties = listOf(
                                property("text", "Home")
                            )
                        )
                    ),
                    customComponents = arrayOf(
                        "selectedIcon" to listOf(
                            component(
                                "icon",
                                properties = listOf(
                                    property("icon", "Home")
                                )
                            )
                        ),
                        "unselectedIcon" to listOf(
                            component(
                                "icon",
                                properties = listOf(
                                    property("icon", "HomeOutline")
                                )
                            )
                        )
                    )
                ),
                component(
                    "navigationBarItem",
                    properties = listOf(
                        property("index", "1"),
                        property(
                            "selectedDestination",
                            "1",
                            id = "bottomNavigation.selectedDestination"
                        ),
                    ),
                    components = listOf(
                        component(
                            "text",
                            properties = listOf(
                                property("text", "Card")
                            )
                        )
                    ),
                    customComponents = arrayOf(
                        "selectedIcon" to listOf(
                            component(
                                "icon",
                                properties = listOf(
                                    property("icon", "Payment")
                                )
                            )
                        ),
                        "unselectedIcon" to listOf(
                            component(
                                "icon",
                                properties = listOf(
                                    property("icon", "PaymentOutline")
                                )
                            )
                        )
                    )
                ),
                component(
                    "navigationBarItem",
                    properties = listOf(
                        property("index", "2"),
                        property(
                            "selectedDestination",
                            "2",
                            id = "bottomNavigation.selectedDestination"
                        ),
                    ),
                    components = listOf(
                        component(
                            "text",
                            properties = listOf(
                                property("text", "Investment")
                            )
                        )
                    ),
                    customComponents = arrayOf(
                        "selectedIcon" to listOf(
                            component(
                                "icon",
                                properties = listOf(
                                    property("icon", "Investment")
                                )
                            )
                        ),
                        "unselectedIcon" to listOf(
                            component(
                                "icon",
                                properties = listOf(
                                    property("icon", "InvestmentOutline")
                                )
                            )
                        )
                    )
                )
            )
        )

        val topBar = component(
            "topAppBar",
            properties = emptyList(),
            components = listOf(
                component(
                    "text",
                    properties = listOf(
                        property("text", "Home", id = "bottomNavigation.selectedDestinationTitle")
                    )
                )
            ),
            customComponents = arrayOf(
                "actionIcons" to listOf(
                    component(
                        "iconButton",
                        properties = emptyList(),
                        actions = listOf(
                            action(
                                "continue",
                                jsonObject(
                                    "flowId" to "Home",
                                    "nextScreenId" to "UserDetail",
                                    "currentScreenId" to "Home"
                                )
                            )
                        ),
                        components = listOf(
                            component(
                                "icon",
                                properties = listOf(
                                    property("icon", "User")
                                )
                            )
                        )
                    ),
                )
            )
        )

        val content = component(
            "sdui",
            properties = listOf(
                property("flow", "Home"),
                property(
                    "stage",
                    "ContaCorrente",
                    id = "bottomNavigation.selectedDestinationContent"
                ),
                property("horizontalFillType", "Max"),
                property("horizontalAlignment", "Center"),
                property("weight", "1"),
            ),
            components = listOf(
                component(
                    "text",
                    properties = listOf(
                        property("text", "Salve", id = "bottomNavigation.selectedDestinationString")
                    )
                )
            ),
            validators = listOf(
                validator(
                    type = "intToString",
                    id = "bottomNavigation.selectedDestinationContent",
                    data = jsonObject(
                        "0" to "ContaCorrente",
                        "1" to "Cartoes",
                        "2" to "Investimentos"
                    ),
                    required = listOf("bottomNavigation.selectedDestination")
                ),
                validator(
                    type = "intToString",
                    id = "bottomNavigation.selectedDestinationTitle",
                    data = jsonObject(
                        "0" to "Home",
                        "1" to "Card",
                        "2" to "Investment"
                    ),
                    required = listOf("bottomNavigation.selectedDestination")
                )
            )
        )

        val screenObj = screen(
            flow = "Home",
            stage = "Home",
            version = "1",
            template = "",
            shouldCache = false,
            components = listOf(
                topBar,
                content,
                bottomNavigation
            )
        )
        return screenObj
    }
}