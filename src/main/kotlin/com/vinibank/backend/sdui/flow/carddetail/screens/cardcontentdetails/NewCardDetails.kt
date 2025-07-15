package com.vinibank.backend.sdui.flow.carddetail.screens.cardcontentdetails

import com.vinibank.backend.sdui.SdUiScreen
import com.vinibank.backend.sdui.flow.ScreenUtil.component
import com.vinibank.backend.sdui.flow.ScreenUtil.property
import com.vinibank.backend.sdui.flow.ScreenUtil.screen
import kotlinx.serialization.json.JsonObject

object NewCardDetails : SdUiScreen {
    fun horizontalDivider() = component(
        "horizontalDivider",
        listOf(
            property("paddingVertical", "8")
        )
    )

    fun item(title: String, description: String, icon: String) = component(
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

    override fun getScreenModel(screenData: JsonObject?): JsonObject = screen(
        "Home",
        "Cartoes/newCard",
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
            )
        ),
    )
}