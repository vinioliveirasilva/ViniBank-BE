package com.vinibank.backend.sdui.flow.home.screen

import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.oldflow.ScreenUtil
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.JsonObject
import org.springframework.stereotype.Component

@Component
class HomeScreen : HomeScreen {
    override val screenId: String
        get() = "Home"

    override fun getScreen(request: SdUiRequest): JsonObject? {
        val bottomNavigation = ScreenUtil.component(
            "navigationBar",
            properties = listOf(
                ScreenUtil.property(
                    "selectedDestination",
                    0,
                    id = "bottomNavigation.selectedDestination"
                ),
            ),
            components = listOf(
                ScreenUtil.component(
                    "navigationBarItem",
                    properties = listOf(
                        ScreenUtil.property("index", 0),
                        ScreenUtil.property(
                            "selectedDestination",
                            0,
                            id = "bottomNavigation.selectedDestination"
                        ),
                    ),
                    components = listOf(
                        ScreenUtil.component(
                            "text",
                            properties = listOf(
                                ScreenUtil.property("text", "Home")
                            )
                        )
                    ),
                    customComponents = arrayOf(
                        "selectedIcon" to listOf(
                            ScreenUtil.component(
                                "icon",
                                properties = listOf(
                                    ScreenUtil.property("icon", "Home")
                                )
                            )
                        ),
                        "unselectedIcon" to listOf(
                            ScreenUtil.component(
                                "icon",
                                properties = listOf(
                                    ScreenUtil.property("icon", "HomeOutline")
                                )
                            )
                        )
                    )
                ),
                ScreenUtil.component(
                    "navigationBarItem",
                    properties = listOf(
                        ScreenUtil.property("index", 1),
                        ScreenUtil.property(
                            "selectedDestination",
                            1,
                            id = "bottomNavigation.selectedDestination"
                        ),
                    ),
                    components = listOf(
                        ScreenUtil.component(
                            "text",
                            properties = listOf(
                                ScreenUtil.property("text", "Card")
                            )
                        )
                    ),
                    customComponents = arrayOf(
                        "selectedIcon" to listOf(
                            ScreenUtil.component(
                                "icon",
                                properties = listOf(
                                    ScreenUtil.property("icon", "Payment")
                                )
                            )
                        ),
                        "unselectedIcon" to listOf(
                            ScreenUtil.component(
                                "icon",
                                properties = listOf(
                                    ScreenUtil.property("icon", "PaymentOutline")
                                )
                            )
                        )
                    )
                ),
                ScreenUtil.component(
                    "navigationBarItem",
                    properties = listOf(
                        ScreenUtil.property("index", 2),
                        ScreenUtil.property(
                            "selectedDestination",
                            2,
                            id = "bottomNavigation.selectedDestination"
                        ),
                    ),
                    components = listOf(
                        ScreenUtil.component(
                            "text",
                            properties = listOf(
                                ScreenUtil.property("text", "Investment")
                            )
                        )
                    ),
                    customComponents = arrayOf(
                        "selectedIcon" to listOf(
                            ScreenUtil.component(
                                "icon",
                                properties = listOf(
                                    ScreenUtil.property("icon", "Investment")
                                )
                            )
                        ),
                        "unselectedIcon" to listOf(
                            ScreenUtil.component(
                                "icon",
                                properties = listOf(
                                    ScreenUtil.property("icon", "InvestmentOutline")
                                )
                            )
                        )
                    ),
                )
            )
        )

        val topBar = ScreenUtil.component(
            "topAppBar",
            properties = emptyList(),
            components = listOf(
                ScreenUtil.component(
                    "text",
                    properties = listOf(
                        ScreenUtil.property(
                            "text",
                            "Home",
                            id = "bottomNavigation.selectedDestinationTitle"
                        ),
                        ScreenUtil.property("fontSize", 18f),
                    )
                )
            ),
            customComponents = arrayOf(
                "actionIcons" to listOf(
                    ScreenUtil.component(
                        "iconButton",
                        properties = emptyList(),
                        actions = listOf(
                            ScreenUtil.action(
                                "continue",
                                ScreenUtil.jsonObject(
                                    "flowId" to "Home",
                                    "nextScreenId" to "UserDetail",
                                    "currentScreenId" to "Home"
                                )
                            )
                        ),
                        components = listOf(
                            ScreenUtil.component(
                                "icon",
                                properties = listOf(
                                    ScreenUtil.property("icon", "User")
                                )
                            )
                        )
                    ),
                )
            )
        )

        val content = ScreenUtil.component(
            "sdui",
            properties = listOf(
                ScreenUtil.property("flow", "Home"),
                ScreenUtil.property("currentScreen", "Home"),
                ScreenUtil.property(
                    "stage",
                    "ContaCorrente",
                    id = "bottomNavigation.selectedDestinationContent"
                ),
                ScreenUtil.property("horizontalFillType", "Max"),
                ScreenUtil.property("horizontalAlignment", "Center"),
                ScreenUtil.property("weight", 1),
                ScreenUtil.property("requestUpdate", false, "requestUpdate"),
            ),
            validators = listOf(
                ScreenUtil.validator(
                    type = "intToString",
                    id = "bottomNavigation.selectedDestinationContent",
                    data = ScreenUtil.jsonObject(
                        "0" to "ContaCorrente",
                        "1" to "Cartoes",
                        "2" to "Investimentos"
                    ),
                    required = listOf("bottomNavigation.selectedDestination")
                ),
                ScreenUtil.validator(
                    type = "intToString",
                    id = "bottomNavigation.selectedDestinationTitle",
                    data = ScreenUtil.jsonObject(
                        "0" to "Home",
                        "1" to "Card",
                        "2" to "Investment"
                    ),
                    required = listOf("bottomNavigation.selectedDestination")
                )
            )
        )

        val screenObj = ScreenUtil.screen(
            flow = "Home",
            stage = "Home",
            version = "1",
            template = "",
            shouldCache = true,
            components = listOf(
                topBar,
                content,
                bottomNavigation
            )
        )
        return screenObj
    }

}