package com.vinibank.backend.sdui.flow.carddetail.screens.cardcontentdetails

import com.vinibank.backend.sdui.SdUiScreen
import com.vinibank.backend.sdui.flow.ScreenUtil.screen
import com.vinibank.backend.sdui.flow.ScreenUtil.component
import com.vinibank.backend.sdui.flow.ScreenUtil.property
import kotlinx.serialization.json.JsonObject

object SilverCardDetails : SdUiScreen {
    fun horizontalDivider() = component(
        type = "horizontalDivider",
        properties = listOf(
            property("paddingVertical", "8")
        )
    )

    fun billing() = component(
        type = "row",
        properties = listOf(
            property("horizontalFillType", "Max"),
            property("verticalAlignment", "Center"),
            property("horizontalArrangement", "SpaceBetween"),
        ),
        components = listOf(
            component(
                type = "column",
                properties = listOf(),
                components = listOf(
                    component(
                        type = "text",
                        properties = listOf(
                            property("text", "Fatura Fechada"),
                        )
                    ),
                    component(
                        type = "text",
                        properties = listOf(
                            property("text", "R$ 177,42"),
                        )
                    ),
                    component(
                        type = "text",
                        properties = listOf(
                            property("text", "vencimento: 07 jul"),
                        )
                    ),
                )
            ),
            component(
                type = "row",
                properties = listOf(
                    property("verticalAlignment", "Center"),
                ),
                components = listOf(
                    component(
                        type = "text",
                        properties = listOf(
                            property("paddingHorizontal", "8"),
                            property("text", "Acessar fatura"),
                        )
                    ),
                    component(
                        type = "icon",
                        properties = listOf(
                            property("icon", "RightArrow"),
                            property("size", "16"),
                        )
                    )
                )
            )
        ),
    )

    fun automaticPayment() = component(
        type = "row",
        properties = listOf(
            property("horizontalFillType", "Max"),
            property("verticalAlignment", "Center"),
            property("horizontalArrangement", "SpaceBetween"),
            property("paddingVertical", "12"),
        ),
        components = listOf(
            component(
                type = "text",
                properties = listOf(
                    property("text", "Débito automatico"),
                )
            ),
            component(
                type = "row",
                properties = listOf(
                    property("verticalAlignment", "Center"),
                ),
                components = listOf(
                    component(
                        type = "text",
                        properties = listOf(
                            property("text", "Ativado"),
                            property("paddingHorizontal", "8"),
                        )
                    ),
                    component(
                        type = "icon",
                        properties = listOf(
                            property("icon", "RightArrow"),
                            property("size", "12"),
                        )
                    )
                )
            )
        )
    )

    fun creditLimit() = component(
        type = "row",
        properties = listOf(
            property("horizontalFillType", "Max"),
            property("verticalAlignment", "Center"),
            property("horizontalArrangement", "SpaceBetween"),
            property("paddingVertical", "12"),
        ),
        components = listOf(
            component(
                type = "text",
                properties = listOf(
                    property("text", "Limite Disponivel"),
                )
            ),
            component(
                type = "row",
                properties = listOf(
                    property("verticalAlignment", "Center"),
                ),
                components = listOf(
                    component(
                        type = "text",
                        properties = listOf(
                            property("text", "R$ 980,58"),
                            property("paddingHorizontal", "8"),
                        )
                    ),
                    component(
                        type = "icon",
                        properties = listOf(
                            property("icon", "RightArrow"),
                            property("size", "12"),
                        )
                    )
                )
            )
        )
    )

    fun milhas() = component(
        type = "row",
        properties = listOf(
            property("horizontalFillType", "Max"),
            property("verticalAlignment", "Center"),
            property("horizontalArrangement", "SpaceBetween"),
            property("paddingVertical", "12"),
        ),
        components = listOf(
            component(
                type = "text",
                properties = listOf(
                    property("text", "Pontos e Beneficios"),
                )
            ),
            component(
                type = "row",
                properties = listOf(
                    property("verticalAlignment", "Center"),
                ),
                components = listOf(
                    component(
                        type = "text",
                        properties = listOf(
                            property("text", "Indisponivel"),
                            property("paddingHorizontal", "8"),
                        )
                    ),
                    component(
                        type = "icon",
                        properties = listOf(
                            property("icon", ""),
                            property("size", "12"),
                        )
                    )
                )
            )
        )
    )

    override fun getScreenModel(screenData: JsonObject?): JsonObject =
        screen(
            "Home",
            "Cartoes/card3",
             "",
            "",
             true,
            components = listOf(
                component(
                    type = "column",
                    properties = listOf(
                        property("paddingVertical", "10"),
                        property("paddingHorizontal", "25"),
                        property("horizontalFillType", "Max"),
                    ),
                    components = listOf(
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