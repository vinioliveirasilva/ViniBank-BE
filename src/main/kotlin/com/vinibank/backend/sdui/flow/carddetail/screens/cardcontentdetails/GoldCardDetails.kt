package com.vinibank.backend.sdui.flow.carddetail.screens.cardcontentdetails

import com.vinibank.backend.sdui.SdUiScreen
import com.vinibank.backend.sdui.flow.ScreenUtil.action
import com.vinibank.backend.sdui.flow.ScreenUtil.component
import com.vinibank.backend.sdui.flow.ScreenUtil.jsonObject
import com.vinibank.backend.sdui.flow.ScreenUtil.property
import com.vinibank.backend.sdui.flow.ScreenUtil.screen
import kotlinx.serialization.json.JsonObject

object GoldCardDetails : SdUiScreen {
    fun horizontalDivider() = component(
        "horizontalDivider",
        listOf(
            property("paddingVertical", "8")
        )
    )

    fun billing() = component(
        "row",
        listOf(
            property("horizontalFillType", "Max"),
            property("verticalAlignment", "Center"),
            property("horizontalArrangement", "SpaceBetween"),
        ),
        listOf(
            component(
                "column",
                listOf(),
                listOf(
                    component(
                        "text",
                        listOf(
                            property("text", "Fatura Fechada"),
                        )
                    ),
                    component(
                        "text",
                        listOf(
                            property("text", "R$ 1.115,90"),
                        )
                    ),
                    component(
                        "text",
                        listOf(
                            property("text", "vencimento: 06 jul"),
                        )
                    ),
                )
            ),
            component(
                "row",
                listOf(
                    property("verticalAlignment", "Center"),
                ),
                listOf(
                    component(
                        "text",
                        listOf(
                            property("paddingHorizontal", "8"),
                            property("text", "Acessar fatura"),
                        )
                    ),
                    component(
                        "icon",
                        listOf(
                            property("icon", "RightArrow"),
                            property("size", "16"),
                        )
                    )
                )
            )
        ),
        actions = listOf(
            action(
                "continue",
                jsonObject(
                    "flowId" to "Home",
                    "nextScreenId" to "Cartoes/card2/billing",
                    "currentScreenId" to "Cartoes/card2",
                )
            )
        )
    )

    fun automaticPayment() = component(
        "row",
        listOf(
            property("horizontalFillType", "Max"),
            property("verticalAlignment", "Center"),
            property("horizontalArrangement", "SpaceBetween"),
            property("paddingVertical", "12"),
        ),
        listOf(
            component(
                "text",
                listOf(
                    property("text", "Débito automatico"),
                )
            ),
            component(
                "row",
                listOf(
                    property("verticalAlignment", "Center"),
                ),
                listOf(
                    component(
                        "text",
                        listOf(
                            property("text", "Desativado"),
                            property("paddingHorizontal", "8"),
                        )
                    ),
                    component(
                        "icon",
                        listOf(
                            property("icon", "RightArrow"),
                            property("size", "12"),
                        )
                    )
                )
            )
        )
    )

    fun creditLimit() = component(
        "row",
        listOf(
            property("horizontalFillType", "Max"),
            property("verticalAlignment", "Center"),
            property("horizontalArrangement", "SpaceBetween"),
            property("paddingVertical", "12"),
        ),
        listOf(
            component(
                "text",
                listOf(
                    property("text", "Limite Disponivel"),
                )
            ),
            component(
                "row",
                listOf(
                    property("verticalAlignment", "Center"),
                ),
                listOf(
                    component(
                        "text",
                        listOf(
                            property("text", "R$ 26.880,10"),
                            property("paddingHorizontal", "8"),
                        )
                    ),
                    component(
                        "icon",
                        listOf(
                            property("icon", "RightArrow"),
                            property("size", "12"),
                        )
                    )
                )
            )
        )
    )

    fun milhas() = component(
        "row",
        listOf(
            property("horizontalFillType", "Max"),
            property("verticalAlignment", "Center"),
            property("horizontalArrangement", "SpaceBetween"),
            property("paddingVertical", "12"),
        ),
        listOf(
            component(
                "text",
                listOf(
                    property("text", "Pontos e Beneficios"),
                )
            ),
            component(
                "row",
                listOf(
                    property("verticalAlignment", "Center"),
                ),
                listOf(
                    component(
                        "text",
                        listOf(
                            property("text", "2.166"),
                            property("paddingHorizontal", "8"),
                        )
                    ),
                    component(
                        "icon",
                        listOf(
                            property("icon", "RightArrow"),
                            property("size", "12"),
                        )
                    )
                )
            )
        )
    )

    override fun getScreenModel(screenData: JsonObject?): JsonObject = screen(
        "Home",
        "Cartoes/card2",
        "",
        "",
        true,
        components = listOf(
            component(
                "column",
                listOf(
                    property("paddingVertical", "10"),
                    property("paddingHorizontal", "25"),
                    property("horizontalFillType", "Max"),
                ),
                listOf(
                    billing(),
                    horizontalDivider(),
                    automaticPayment(),
                    horizontalDivider(),
                    creditLimit(),
                    horizontalDivider(),
                    milhas(),
                )
            )
        ),
    )
}