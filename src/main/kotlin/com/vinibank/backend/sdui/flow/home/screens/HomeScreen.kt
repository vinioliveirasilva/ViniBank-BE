package com.vinibank.backend.sdui.flow.home.screens

import com.vinibank.backend.sdui.SdUiScreen
import org.json.JSONObject

object HomeScreen : SdUiScreen {
    override fun getScreenModel(screenData: String): JSONObject {
        val bottomNavigation = JSONObject(
            mapOf(
                "type" to "bottomNavigation",
                "properties" to listOf(
                    mapOf(
                        "name" to "selectedDestination",
                        "value" to "0",
                        "id" to "bottomNavigation.selectedDestination"
                    ),
                ),
                "components" to listOf(
                    mapOf(
                        "type" to "navigationBarItem",
                        "properties" to listOf(
                            mapOf("name" to "index", "value" to "0"),
                            mapOf(
                                "name" to "selectedDestination",
                                "value" to "0",
                                "id" to "bottomNavigation.selectedDestination"
                            ),
                        ),
                        "components" to listOf(
                            mapOf(
                                "type" to "text",
                                "properties" to listOf(
                                    mapOf("name" to "text", "value" to "Home")
                                )
                            ),
                        ),
                        "selectedIcon" to listOf(
                            mapOf(
                                "type" to "icon",
                                "properties" to listOf(
                                    mapOf("name" to "icon", "value" to "Home"),
                                )
                            )
                        ),
                        "unselectedIcon" to listOf(
                            mapOf(
                                "type" to "icon",
                                "properties" to listOf(
                                    mapOf("name" to "icon", "value" to "HomeOutline"),
                                )
                            )
                        )
                    ),
                    mapOf(
                        "type" to "navigationBarItem",
                        "properties" to listOf(
                            mapOf("name" to "index", "value" to "1"),
                            mapOf(
                                "name" to "selectedDestination",
                                "value" to "1",
                                "id" to "bottomNavigation.selectedDestination"
                            ),
                        ),
                        "components" to listOf(
                            mapOf(
                                "type" to "text",
                                "properties" to listOf(
                                    mapOf("name" to "text", "value" to "Card")
                                )
                            )
                        ),
                        "selectedIcon" to listOf(
                            mapOf(
                                "type" to "icon",
                                "properties" to listOf(
                                    mapOf("name" to "icon", "value" to "Payment")
                                )
                            )
                        ),
                        "unselectedIcon" to listOf(
                            mapOf(
                                "type" to "icon",
                                "properties" to listOf(
                                    mapOf("name" to "icon", "value" to "PaymentOutline")
                                )
                            )
                        )
                    ),
                    mapOf(
                        "type" to "navigationBarItem",
                        "properties" to listOf(
                            mapOf("name" to "index", "value" to "2"),
                            mapOf(
                                "name" to "selectedDestination",
                                "value" to "2",
                                "id" to "bottomNavigation.selectedDestination"
                            ),
                        ),
                        "components" to listOf(
                            mapOf(
                                "type" to "text",
                                "properties" to listOf(
                                    mapOf("name" to "text", "value" to "Investment")
                                )
                            )
                        ),
                        "selectedIcon" to listOf(
                            mapOf(
                                "type" to "icon",
                                "properties" to listOf(
                                    mapOf("name" to "icon", "value" to "Investment")
                                )
                            )
                        ),
                        "unselectedIcon" to listOf(
                            mapOf(
                                "type" to "icon",
                                "properties" to listOf(
                                    mapOf("name" to "icon", "value" to "InvestmentOutline")
                                )
                            )
                        )
                    )
                )
            )
        )

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
                                "value" to "Home",
                                "id" to "bottomNavigation.selectedDestinationTitle"
                            )
                        )
                    )
                )
            )
        )

        val content = JSONObject(
            mapOf(
                "type" to "sdui",
                "properties" to listOf(
                    mapOf("name" to "flow", "value" to "Home"),
                    mapOf(
                        "name" to "stage",
                        "value" to "ContaCorrente",
                        "id" to "bottomNavigation.selectedDestinationContent"
                    ),
                    mapOf("name" to "horizontalFillType", "value" to "Max"),
                    mapOf("name" to "horizontalAlignment", "value" to "Center"),
                    mapOf("name" to "weight", "value" to "1"),
                ),
                "components" to listOf(
                    mapOf(
                        "type" to "text",
                        "properties" to listOf(
                            mapOf(
                                "name" to "text",
                                "value" to "Salve",
                                "id" to "bottomNavigation.selectedDestinationString"
                            )
                        )
                    )
                ),
                "validators" to listOf(
                    mapOf(
                        "type" to "intToString",
                        "id" to "bottomNavigation.selectedDestinationContent",
                        "data" to mapOf(
                            "0" to "ContaCorrente",
                            "1" to "Cartoes",
                            "2" to "Investimentos",
                        ),
                        "required" to listOf("bottomNavigation.selectedDestination")
                    ),
                    mapOf(
                        "type" to "intToString",
                        "id" to "bottomNavigation.selectedDestinationTitle",
                        "data" to mapOf(
                            "0" to "Home",
                            "1" to "Card",
                            "2" to "Investment",
                        ),
                        "required" to listOf("bottomNavigation.selectedDestination")
                    )
                )
            )
        )

        val screen = JSONObject(
            mapOf(
                "type" to "column",
                "properties" to listOf(
                    mapOf("name" to "horizontalFillType", "value" to "Max"),
                    mapOf("name" to "verticalFillType", "value" to "Max"),
                    mapOf("name" to "verticalArrangement", "value" to "SpaceBetween")
                ),
                "components" to listOf(
                    topBar,
                    content,
                    bottomNavigation
                )
            )
        )
        return screen
    }
}