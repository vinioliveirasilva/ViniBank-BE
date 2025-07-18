package com.vinibank.backend.sdui.flow.carddetail.screens.cardcontentdetails

import com.vinibank.backend.sdui.SdUiScreen
import com.vinibank.backend.sdui.flow.ScreenUtil.component
import com.vinibank.backend.sdui.flow.ScreenUtil.property
import com.vinibank.backend.sdui.flow.ScreenUtil.screen
import kotlinx.serialization.json.JsonObject

object CardDetails : SdUiScreen {
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
                            property("text", "R$ 177,42"),
                        )
                    ),
                    component(
                        "text",
                        listOf(
                            property("text", "vencimento: 07 jul"),
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
                            property("text", "Ativado"),
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
                            property("text", "R$ 980,58"),
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
                            property("text", "Indisponivel"),
                            property("paddingHorizontal", "8"),
                        )
                    ),
                    component(
                        "icon",
                        listOf(
                            property("icon", ""),
                            property("size", "12"),
                        )
                    )
                )
            )
        )
    )

    override fun getScreenModel(screenData: JsonObject?): JsonObject = screen(
        "Home",
        "Cartoes/card3",
        "",
        "",
        true,
        listOf(
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