package com.vinibank.backend.sdui.flow.newcard.screens

import com.vinibank.backend.db.Card
import com.vinibank.backend.sdui.SdUiScreen
import com.vinibank.backend.sdui.components.topBarWithCloseAction
import com.vinibank.backend.sdui.flow.ScreenUtil.action
import com.vinibank.backend.sdui.flow.ScreenUtil.component
import com.vinibank.backend.sdui.flow.ScreenUtil.jsonObject
import com.vinibank.backend.sdui.flow.ScreenUtil.property
import com.vinibank.backend.sdui.flow.ScreenUtil.screen
import kotlinx.serialization.json.JsonObject

object IntroScreen : SdUiScreen {

    private fun getCard(card: Card, index: Int) = component(
        "card",
        listOf(
            property("paddingHorizontal", "30"),
            property("paddingVertical", "10"),
            property("horizontalFillType", "Max"),
            property("height", "180"),
        ),
        listOf(
            component(
                "column",
                listOf(
                    property("paddingHorizontal", "20"),
                    property("paddingVertical", "20"),
                    property("verticalFillType", "Max"),
                ),
                listOf(
                    component(
                        "row",
                        listOf(
                            property("horizontalFillType", "Max"),
                            property(
                                "horizontalArrangement",
                                "SpaceBetween"
                            )
                        ),
                        listOf(
                            component(
                                "text",
                                listOf(
                                    property("text", card.name),
                                )
                            ),
                            component(
                                "text",
                                listOf(
                                    property(
                                        "text",
                                        "final ".plus(card.number.split(" ").last())
                                    ),
                                )
                            ),
                        )
                    ),
                    component(
                        "text",
                        listOf(
                            property("text", card.type),
                        )
                    ),
                    component(
                        "column",
                        listOf(
                            property("verticalFillType", "Max"),
                            property("horizontalFillType", "Max"),
                            property("horizontalAlignment", "End"),
                            property("verticalArrangement", "Bottom"),
                        ),
                        listOf(
                            component(
                                "image",
                                listOf(
                                    property("iconDrawable", "Visa"),
                                    property("size", "30"),
                                )
                            ),
                        )
                    )
                ),
            )
        ),
        action = action(
            "toInt",
            data = jsonObject(
                "id" to "CardsContent.SelectedCardIndex",
                "value" to index.toString(),
            )
        )
    )

    override fun getScreenModel(screenData: JsonObject?): JsonObject {
        return screen(
            flow = "Home",
            stage = "UserDetail",
            version = "1",
            template = "",
            shouldCache = false,
            components = listOf(
                topBarWithCloseAction("Select your card"),
                component(
                    type = "lazyColumn",
                    properties = listOf(
                        property("horizontalFillType", "Max"),
                        property("weight", "1")
                    ),
                    components = listOf(
                        getCard(
                            card = Card(
                                identifier = "",
                                name = "Platinum card",
                                type = "International",
                                number = "",
                                validUntil = "",
                                cvv = "",
                            ),
                            index = 0
                        ),
                        getCard(
                            card = Card(
                                identifier = "",
                                name = "Gold card",
                                type = "International",
                                number = "",
                                validUntil = "",
                                cvv = "",
                            ),
                            index = 0
                        ),
                        getCard(
                            card = Card(
                                identifier = "",
                                name = "Silver Card",
                                type = "National",
                                number = "",
                                validUntil = "",
                                cvv = "",
                            ),
                            index = 0
                        ),
                    )
                )
            )
        )
    }
}