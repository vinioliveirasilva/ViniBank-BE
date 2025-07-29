package com.vinibank.backend.sdui.flow.home.screens

import com.vinibank.backend.sdui.SdUiScreen2
import com.vinibank.backend.sdui.flow.ScreenUtil.action
import com.vinibank.backend.sdui.flow.ScreenUtil.component
import com.vinibank.backend.sdui.flow.ScreenUtil.jsonObject
import com.vinibank.backend.sdui.flow.ScreenUtil.property
import com.vinibank.backend.sdui.flow.ScreenUtil.screen
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.JsonObject

object CheckingAccountContent : SdUiScreen2 {
    fun actionIcon(name: String, icon: String) = component(
        "column",
        listOf(
            property("horizontalAlignment", "Center"),
        ),
        listOf(
            component(
                "card",
                listOf(),
                listOf(
                    component(
                        "icon",
                        listOf(
                            property("icon", icon),
                            property("paddingVertical", "20"),
                            property("paddingHorizontal", "25"),
                            property("size", "36"),
                        )
                    )
                ),
                actions = listOf(
                    action(
                        "toBoolean",
                        jsonObject(
                            "id" to "123123",
                            "value" to "true",
                        )
                    )
                )
            ),
            component(
                "text",
                listOf(
                    property("text", name),
                    property("paddingVertical", "10"),
                )
            )
        ),
    )

    fun transactionItem() = component(
        "card",
        listOf(
            property("horizontalFillType", "Max")
        ),
        listOf(
            component(
                "row", listOf(
                    property("paddingVertical", "10"),
                    property("horizontalFillType", "Max"),
                    property("horizontalArrangement", "SpaceBetween"),
                    property("paddingHorizontal", "10"),
                ), listOf(
                    component(
                        "row",
                        listOf(
                            property("paddingVertical", "10"),
                        ),
                        listOf(
                            component(
                                "icon",
                                listOf(
                                    property("icon", "Money"),
                                    property("paddingHorizontal", "10"),
                                )
                            ),
                            component(
                                "text",
                                listOf(
                                    property("text", "Gastou pra caralho"),
                                    property("textAlign", "Center")
                                )
                            )
                        )
                    ),
                    component(
                        "row",
                        listOf(
                            property("paddingVertical", "10"),
                            property("verticalAlignment", "Center"),
                        ),
                        listOf(
                            component(
                                "text",
                                listOf(
                                    property("paddingHorizontal", "15"),
                                    property("text", "-R$ 500.00"),
                                )
                            ),
                            component(
                                "icon",
                                listOf(
                                    property("icon", "RightArrow"),
                                    property("size", "18"),
                                )
                            )
                        )
                    ),
                )
            ),
        )
    )

    override fun getScreenModel(screenData: JsonObject?, internal: (SdUiRequest) -> List<JsonObject>): JsonObject = screen(
        "Home",
        "ContaCorrente",
        "1",
        "",
        false,
        components = listOf(
            component(
                "dialog",
                listOf(
                    property("isVisible", "false", "123abc")
                ),
            ),
            component(
                "bottomSheet",
                listOf(
                    property("isVisible", "false", "123abc1")
                ),
                listOf(
                    component(
                        "button",
                        listOf(
                            property("text", "Balance"),
                        ),
                        actions = listOf(
                            action(
                                "toBoolean",
                                jsonObject(
                                    "id" to "123abc",
                                    "value" to "true"
                                )
                            )
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
                "lazyColumn",
                listOf(
                    property("weight", "1"),
                    property("horizontalFillType", "Max"),
                    property("paddingVertical", "10"),
                    property("paddingHorizontal", "10"),
                    property("horizontalAlignment", "Center"),
                    property("verticalArrangement", "SpaceBetween"),
                ),
                listOf(
                    component(
                        type = "column",
                        properties = listOf(),
                        components = listOf(
                            component(
                                "card",
                                listOf(
                                    property("paddingVertical", "10"),
                                    property("height", "90"),
                                ),
                                listOf(
                                    component(
                                        "column",
                                        listOf(
                                            property("horizontalAlignment", "Center"),
                                            property("verticalArrangement", "Center"),
                                            property("verticalFillType", "Max"),
                                        ),
                                        listOf(
                                            component(
                                                "sdui",
                                                properties = listOf(
                                                    property("flow", "Home"),
                                                    property("stage", "Balance"),
                                                    property("requestUpdate", false, "requestUpdate1"),
                                                    property("horizontalFillType", "Max"),
                                                ),
                                                components = internal(SdUiRequest("Home", "ContaCorrente", "Balance", screenData)),
                                            ),
                                        )
                                    )
                                )
                            ),
                            component(
                                "row",
                                listOf(
                                    property("paddingVertical", "10"),
                                    property("horizontalFillType", "Max"),
                                    property("horizontalArrangement", "SpaceBetween"),
                                ),
                                listOf(
                                    component(
                                        "column",
                                        listOf(
                                            property("horizontalAlignment", "Center"),
                                        ),
                                        listOf(
                                            component(
                                                "card",
                                                listOf(),
                                                listOf(
                                                    component(
                                                        "icon",
                                                        listOf(
                                                            property("iconDrawable", "Pix"),
                                                            property("paddingVertical", "20"),
                                                            property("paddingHorizontal", "25"),
                                                            property("size", "36"),
                                                        )
                                                    )
                                                ),
                                                listOf(
                                                    action(
                                                        "toBoolean",
                                                        jsonObject(
                                                            "id" to "123abc",
                                                            "value" to "true",
                                                        )
                                                    )
                                                )
                                            ),
                                            component(
                                                "text",
                                                listOf(
                                                    property("text", "Pix"),
                                                    property("paddingVertical", "10"),
                                                )
                                            )
                                        )
                                    ),
                                    actionIcon("Transfer", "Payment"),
                                    actionIcon("Pay", "ReceiptLong"),
                                    component(
                                        "column",
                                        listOf(
                                            property("horizontalAlignment", "Center"),
                                        ),
                                        listOf(
                                            component(
                                                "card",
                                                listOf(),
                                                listOf(
                                                    component(
                                                        "icon",
                                                        listOf(
                                                            property("icon", "MoreVert"),
                                                            property("paddingVertical", "20"),
                                                            property("paddingHorizontal", "25"),
                                                            property("size", "36"),
                                                        )
                                                    )
                                                ),
                                                listOf(
                                                    action(
                                                        "toBoolean",
                                                        jsonObject(
                                                            "id" to "123abc1",
                                                            "value" to "true",
                                                        )
                                                    )
                                                )
                                            ),
                                            component(
                                                "text",
                                                listOf(
                                                    property("text", "More"),
                                                    property("paddingVertical", "10"),
                                                )
                                            )
                                        )
                                    ),
                                )
                            ),
                            component(
                                "column",
                                listOf(
                                    property("horizontalAlignment", "Center"),
                                    property("paddingVertical", "10"),
                                    property("verticalArrangement", "SpacedBy10"),
                                ),
                                listOf(
                                    component(
                                        "text",
                                        listOf(
                                            property("text", "Last Transactions")
                                        )
                                    ),
                                    transactionItem(),
                                    transactionItem(),
                                    transactionItem(),
                                )
                            ),
                        )
                    ),
                    component(
                        type = "snackBar",
                        properties = listOf(
                            property("text", "SnackBar"),
                            property("isVisible", "false", "123123")
                        ),
                    ),
                )
            ),
        )
    )
}