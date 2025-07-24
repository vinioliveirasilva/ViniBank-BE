package com.vinibank.backend.sdui.flow.carddetail.screens

import com.vinibank.backend.db.Card
import com.vinibank.backend.sdui.SdUiScreen
import com.vinibank.backend.sdui.flow.ScreenUtil.action
import com.vinibank.backend.sdui.flow.ScreenUtil.component
import com.vinibank.backend.sdui.flow.ScreenUtil.jsonObject
import com.vinibank.backend.sdui.flow.ScreenUtil.property
import com.vinibank.backend.sdui.flow.ScreenUtil.screen
import com.vinibank.backend.sdui.flow.ScreenUtil.validator
import kotlinx.serialization.json.JsonObject

class CardsContent(private val cards: List<Card>) : SdUiScreen {

    private val cardsLayout = cards.mapIndexed { index, card ->
        getCard(card, index)
    }.takeIf { it.isNotEmpty() } ?: listOf(addCard())

    private val cardsAction = jsonObject(
        *(cards.mapIndexed { index, card -> index.toString() to "Cartoes/${card.identifier}" }
            .takeIf { it.isNotEmpty() } ?: listOf("0" to "Cartoes/newCard")).toTypedArray()
    )

    private fun addCard() = component(
        type = "card",
        properties = listOf(
            property("paddingHorizontal", "10"),
            property("horizontalFillType", "Max"),
            property("height", "180"),
        ),
        components = listOf(
            component(
                type = "column",
                properties = listOf(
                    property("paddingHorizontal", "20"),
                    property("paddingVertical", "20"),
                    property("verticalFillType", "Max"),
                    property("horizontalFillType", "Max"),
                    property("horizontalAlignment", "Center"),
                    property("verticalArrangement", "Center"),
                ),
                components = listOf(
                    component(
                        type = "icon",
                        properties = listOf(
                            property("icon", "Add"),
                            property("size", "30"),
                        )
                    ),
                ),
            )
        ),
        actions = listOf(
            action(
                "navigate",
                jsonObject(
                    "flow" to "NewCard"
                )
            )
        )
    )

    private fun getCard(card: Card, index: Int) = component(
        type = "card",
        properties = listOf(
            property("paddingHorizontal", "10"),
            property("horizontalFillType", "Max"),
            property("height", "180"),
        ),
        components = listOf(
            component(
                type = "column",
                properties = listOf(
                    property("paddingHorizontal", "20"),
                    property("paddingVertical", "20"),
                    property("verticalFillType", "Max"),
                ),
                components = listOf(
                    component(
                        type = "row",
                        properties = listOf(
                            property("horizontalFillType", "Max"),
                            property(
                                "horizontalArrangement",
                                "SpaceBetween"
                            )
                        ),
                        components = listOf(
                            component(
                                type = "text",
                                properties = listOf(
                                    property("text", card.name),
                                )
                            ),
                            component(
                                type = "text",
                                properties = listOf(
                                    property(
                                        "text",
                                        "final ".plus(card.number.split(" ").last())
                                    ),
                                )
                            ),
                        )
                    ),
                    component(
                        type = "text",
                        properties = listOf(
                            property("text", card.type),
                        )
                    ),
                    component(
                        type = "column",
                        properties = listOf(
                            property("verticalFillType", "Max"),
                            property("horizontalFillType", "Max"),
                            property("horizontalAlignment", "End"),
                            property("verticalArrangement", "Bottom"),
                        ),
                        components = listOf(
                            component(
                                type = "image",
                                properties = listOf(
                                    property("iconDrawable", "Visa"),
                                    property("size", "30"),
                                )
                            ),
                        )
                    )
                ),
            )
        ),
        actions = listOf(
            action(
                "toInt",
                data = jsonObject(
                    "id" to "CardsContent.SelectedCardIndex",
                    "value" to index.toString(),
                )
            )
        )
    )

    override fun getScreenModel(screenData: JsonObject?) = screen(
        flow = "Home",
        stage = "Cartoes",
        version = "1",
        template = "",
        shouldCache = false,
        components = listOf(
            component(
                type = "lazyColumn",
                properties = listOf(
                    property("paddingHorizontal", "10"),
                    property("weight", "1"),
                    property("horizontalFillType", "Max"),
                    property("horizontalAlignment", "Center"),
                ),
                components = listOf(
                    component(
                        type = "horizontalPager",
                        properties = listOf(
                            property("contentPadding", "20"),
                            property(
                                "currentPage",
                                "0",
                                "CardsContent.SelectedCardIndex"
                            ),
                        ),
                        components = cardsLayout
                    ),
                    component(
                        type = "sdui",
                        properties = listOf(
                            property("flow", "Home"),
                            property(
                                "stage",
                                "Detail",
                                "CardsContent.SelectedCardId"
                            ),
                            property("horizontalFillType", "Max"),
                            property("verticalFillType", "Max"),
                            property("horizontalAlignment", "Center"),
                            property("weight", "1"),
                        ),
                        validators = listOf(
                            validator(
                                type = "intToString",
                                id = "CardsContent.SelectedCardId",
                                data = cardsAction,
                                required = listOf("CardsContent.SelectedCardIndex")
                            )
                        )
                    ),
                    component(
                        type = "column",
                        properties = listOf(
                            property("paddingVertical", "10"),
                            property("paddingHorizontal", "25"),
                            property("horizontalFillType", "Max"),
                        ),
                    ),
                )
            )
        )
    )
}