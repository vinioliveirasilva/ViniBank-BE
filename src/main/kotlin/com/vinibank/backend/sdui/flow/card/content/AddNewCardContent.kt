package com.vinibank.backend.sdui.flow.card.content

import com.vinibank.backend.sdui.oldflow.ScreenUtil.action
import com.vinibank.backend.sdui.oldflow.ScreenUtil.component
import com.vinibank.backend.sdui.oldflow.ScreenUtil.jsonObject
import com.vinibank.backend.sdui.oldflow.ScreenUtil.property
import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen

class AddNewCardContent {
    private fun horizontalDivider() = component(
        "horizontalDivider",
        listOf(
            property("paddingVertical", "8")
        )
    )

    private fun item(title: String, description: String, icon: String) = component(
        "row",
        listOf(
            property("horizontalFillType", "Max"),
            property("verticalAlignment", "Center"),
            property("paddingVertical", "16"),
        ),
        listOf(
            component(
                "icon",
                listOf(
                    property("icon", icon),
                    property("size", "24"),
                )
            ),
            component(
                "column",
                listOf(
                    property("paddingHorizontal", "16"),
                ),
                listOf(
                    component(
                        "text",
                        listOf(
                            property("text", title),
                        )
                    ),
                    component(
                        "text",
                        listOf(
                            property("text", description),
                        )
                    )
                )
            )
        )
    )

    fun screen() = screen(
        flow = "Card",
        stage = "newCard",
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
                        components = listOf(
                            component(
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
                        )
                    ),
                    component(
                        "column",
                        listOf(
                            property("paddingVertical", "10"),
                            property("paddingHorizontal", "25"),
                            property("horizontalFillType", "Max"),
                        ),
                        listOf(
                            item(
                                "Até 3 adicionais",
                                "Conte com até 3 cartoes adicionais gratuitos com os mesmos beneficios do titular.",
                                "Payment"
                            ),
                            horizontalDivider(),
                            item(
                                "ViniBank Shop",
                                "Faça compras no ViniBank Shop com o seu cartão e tenha vantagens como cashback  e parcelamentos sem juros.",
                                "ShoppingBag"
                            ),
                            horizontalDivider(),
                            item(
                                "Concierge",
                                "Conte com assistencia pessoal para te ajudar na organização de viagens, procura de eeventos e incidação dos melhores restaurantes e servicos onde quer que voce esteja.",
                                "SupervisorAccount"
                            ),
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