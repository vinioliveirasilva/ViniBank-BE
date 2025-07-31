package com.vinibank.backend.sdui.flow.home.content

import com.vinibank.backend.sdui.flow.RoutingController
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.oldflow.ScreenUtil
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.JsonObject
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class CheckingAccountContent(
     @Lazy private val routingController: RoutingController
) : HomeScreen {
    fun actionIcon(name: String, icon: String) = ScreenUtil.component(
        "column",
        listOf(
            ScreenUtil.property("horizontalAlignment", "Center"),
        ),
        listOf(
            ScreenUtil.component(
                "card",
                listOf(),
                listOf(
                    ScreenUtil.component(
                        "icon",
                        listOf(
                            ScreenUtil.property("icon", icon),
                            ScreenUtil.property("paddingVertical", "20"),
                            ScreenUtil.property("paddingHorizontal", "25"),
                            ScreenUtil.property("size", "36"),
                        )
                    )
                ),
                actions = listOf(
                    ScreenUtil.action(
                        "toBoolean",
                        ScreenUtil.jsonObject(
                            "id" to "123123",
                            "value" to "true",
                        )
                    )
                )
            ),
            ScreenUtil.component(
                "text",
                listOf(
                    ScreenUtil.property("text", name),
                    ScreenUtil.property("paddingVertical", "10"),
                )
            )
        ),
    )

    fun transactionItem() = ScreenUtil.component(
        "card",
        listOf(
            ScreenUtil.property("horizontalFillType", "Max")
        ),
        listOf(
            ScreenUtil.component(
                "row", listOf(
                    ScreenUtil.property("paddingVertical", "10"),
                    ScreenUtil.property("horizontalFillType", "Max"),
                    ScreenUtil.property("horizontalArrangement", "SpaceBetween"),
                    ScreenUtil.property("paddingHorizontal", "10"),
                ), listOf(
                    ScreenUtil.component(
                        "row",
                        listOf(
                            ScreenUtil.property("paddingVertical", "10"),
                        ),
                        listOf(
                            ScreenUtil.component(
                                "icon",
                                listOf(
                                    ScreenUtil.property("icon", "Money"),
                                    ScreenUtil.property("paddingHorizontal", "10"),
                                )
                            ),
                            ScreenUtil.component(
                                "text",
                                listOf(
                                    ScreenUtil.property("text", "Gastou pra caralho"),
                                    ScreenUtil.property("textAlign", "Center")
                                )
                            )
                        )
                    ),
                    ScreenUtil.component(
                        "row",
                        listOf(
                            ScreenUtil.property("paddingVertical", "10"),
                            ScreenUtil.property("verticalAlignment", "Center"),
                        ),
                        listOf(
                            ScreenUtil.component(
                                "text",
                                listOf(
                                    ScreenUtil.property("paddingHorizontal", "15"),
                                    ScreenUtil.property("text", "-R$ 500.00"),
                                )
                            ),
                            ScreenUtil.component(
                                "icon",
                                listOf(
                                    ScreenUtil.property("icon", "RightArrow"),
                                    ScreenUtil.property("size", "18"),
                                )
                            )
                        )
                    ),
                )
            ),
        )
    )

    override val screenId: String
        get() = "ContaCorrente"

    override fun getScreen(request: SdUiRequest): JsonObject? = ScreenUtil.screen(
        "Home",
        "ContaCorrente",
        "1",
        "",
        false,
        components = listOf(
            ScreenUtil.component(
                "dialog",
                listOf(
                    ScreenUtil.property("isVisible", "false", "123abc")
                ),
            ),
            ScreenUtil.component(
                "bottomSheet",
                listOf(
                    ScreenUtil.property("isVisible", "false", "123abc1")
                ),
                listOf(
                    ScreenUtil.component(
                        "button",
                        listOf(
                            ScreenUtil.property("text", "Balance"),
                        ),
                        actions = listOf(
                            ScreenUtil.action(
                                "toBoolean",
                                ScreenUtil.jsonObject(
                                    "id" to "123abc",
                                    "value" to "true"
                                )
                            )
                        )
                    ),
                    ScreenUtil.component(
                        "text",
                        listOf(
                            ScreenUtil.property("text", "R$ 100,00"),
                        )
                    ),
                    ScreenUtil.component(
                        "text",
                        listOf(
                            ScreenUtil.property("text", "updated 10 min ago"),
                        )
                    )
                )
            ),
            ScreenUtil.component(
                "lazyColumn",
                listOf(
                    ScreenUtil.property("weight", "1"),
                    ScreenUtil.property("horizontalFillType", "Max"),
                    ScreenUtil.property("paddingVertical", "10"),
                    ScreenUtil.property("paddingHorizontal", "10"),
                    ScreenUtil.property("horizontalAlignment", "Center"),
                    ScreenUtil.property("verticalArrangement", "SpaceBetween"),
                ),
                listOf(
                    ScreenUtil.component(
                        type = "column",
                        properties = listOf(),
                        components = listOf(
                            ScreenUtil.component(
                                "card",
                                listOf(
                                    ScreenUtil.property("paddingVertical", "10"),
                                    ScreenUtil.property("height", "90"),
                                ),
                                listOf(
                                    ScreenUtil.component(
                                        "column",
                                        listOf(
                                            ScreenUtil.property(
                                                "horizontalAlignment",
                                                "Center"
                                            ),
                                            ScreenUtil.property(
                                                "verticalArrangement",
                                                "Center"
                                            ),
                                            ScreenUtil.property("verticalFillType", "Max"),
                                        ),
                                        listOf(
                                            ScreenUtil.component(
                                                "sdui",
                                                properties = listOf(
                                                    ScreenUtil.property("flow", "Home"),
                                                    ScreenUtil.property("stage", "Balance"),
                                                    ScreenUtil.property("currentScreen", "ContaCorrente"),
                                                    ScreenUtil.property(
                                                        "requestUpdate",
                                                        false,
                                                        "requestUpdate1"
                                                    ),
                                                    ScreenUtil.property(
                                                        "horizontalFillType",
                                                        "Max"
                                                    ),
                                                ),
                                                components = routingController.getSdUiComponents(
                                                    SdUiRequest(
                                                        request.flow,
                                                        screenId,
                                                        "Balance",
                                                        request.screenData
                                                    )
                                                ),
                                            ),
                                        )
                                    )
                                )
                            ),
                            ScreenUtil.component(
                                "row",
                                listOf(
                                    ScreenUtil.property("paddingVertical", "10"),
                                    ScreenUtil.property("horizontalFillType", "Max"),
                                    ScreenUtil.property(
                                        "horizontalArrangement",
                                        "SpaceBetween"
                                    ),
                                ),
                                listOf(
                                    ScreenUtil.component(
                                        "column",
                                        listOf(
                                            ScreenUtil.property(
                                                "horizontalAlignment",
                                                "Center"
                                            ),
                                        ),
                                        listOf(
                                            ScreenUtil.component(
                                                "card",
                                                listOf(),
                                                listOf(
                                                    ScreenUtil.component(
                                                        "icon",
                                                        listOf(
                                                            ScreenUtil.property(
                                                                "iconDrawable",
                                                                "Pix"
                                                            ),
                                                            ScreenUtil.property(
                                                                "paddingVertical",
                                                                "20"
                                                            ),
                                                            ScreenUtil.property(
                                                                "paddingHorizontal",
                                                                "25"
                                                            ),
                                                            ScreenUtil.property("size", "36"),
                                                        )
                                                    )
                                                ),
                                                listOf(
                                                    ScreenUtil.action(
                                                        "toBoolean",
                                                        ScreenUtil.jsonObject(
                                                            "id" to "123abc",
                                                            "value" to "true",
                                                        )
                                                    )
                                                )
                                            ),
                                            ScreenUtil.component(
                                                "text",
                                                listOf(
                                                    ScreenUtil.property("text", "Pix"),
                                                    ScreenUtil.property(
                                                        "paddingVertical",
                                                        "10"
                                                    ),
                                                )
                                            )
                                        )
                                    ),
                                    actionIcon("Transfer", "Payment"),
                                    actionIcon("Pay", "ReceiptLong"),
                                    ScreenUtil.component(
                                        "column",
                                        listOf(
                                            ScreenUtil.property(
                                                "horizontalAlignment",
                                                "Center"
                                            ),
                                        ),
                                        listOf(
                                            ScreenUtil.component(
                                                "card",
                                                listOf(),
                                                listOf(
                                                    ScreenUtil.component(
                                                        "icon",
                                                        listOf(
                                                            ScreenUtil.property(
                                                                "icon",
                                                                "MoreVert"
                                                            ),
                                                            ScreenUtil.property(
                                                                "paddingVertical",
                                                                "20"
                                                            ),
                                                            ScreenUtil.property(
                                                                "paddingHorizontal",
                                                                "25"
                                                            ),
                                                            ScreenUtil.property("size", "36"),
                                                        )
                                                    )
                                                ),
                                                listOf(
                                                    ScreenUtil.action(
                                                        "toBoolean",
                                                        ScreenUtil.jsonObject(
                                                            "id" to "123abc1",
                                                            "value" to "true",
                                                        )
                                                    )
                                                )
                                            ),
                                            ScreenUtil.component(
                                                "text",
                                                listOf(
                                                    ScreenUtil.property("text", "More"),
                                                    ScreenUtil.property(
                                                        "paddingVertical",
                                                        "10"
                                                    ),
                                                )
                                            )
                                        )
                                    ),
                                )
                            ),
                            ScreenUtil.component(
                                "column",
                                listOf(
                                    ScreenUtil.property("horizontalAlignment", "Center"),
                                    ScreenUtil.property("paddingVertical", "10"),
                                    ScreenUtil.property("verticalArrangement", "SpacedBy10"),
                                ),
                                listOf(
                                    ScreenUtil.component(
                                        "text",
                                        listOf(
                                            ScreenUtil.property("text", "Last Transactions")
                                        )
                                    ),
                                    transactionItem(),
                                    transactionItem(),
                                    transactionItem(),
                                )
                            ),
                        )
                    ),
                    ScreenUtil.component(
                        type = "snackBar",
                        properties = listOf(
                            ScreenUtil.property("text", "SnackBar"),
                            ScreenUtil.property("isVisible", "false", "123123")
                        ),
                    ),
                )
            ),
        )
    )
}