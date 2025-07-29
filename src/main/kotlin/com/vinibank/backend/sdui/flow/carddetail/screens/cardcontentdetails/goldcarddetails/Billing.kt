package com.vinibank.backend.sdui.flow.carddetail.screens.cardcontentdetails.goldcarddetails

import com.vinibank.backend.sdui.SdUiScreen
import com.vinibank.backend.sdui.components.topBarWithBackAction
import com.vinibank.backend.sdui.flow.ScreenUtil.action
import com.vinibank.backend.sdui.flow.ScreenUtil.component
import com.vinibank.backend.sdui.flow.ScreenUtil.jsonObject
import com.vinibank.backend.sdui.flow.ScreenUtil.property
import com.vinibank.backend.sdui.flow.ScreenUtil.screen
import kotlinx.serialization.json.JsonObject

object Billing : SdUiScreen {
    fun horizontalDivider() = component(
        "horizontalDivider",
        listOf(
            property("paddingHorizontal", "25"),
        )
    )

    fun billingItem(whereUsedName: String, paymentType: String, paymentValue: String) = component(
        "row",
        listOf(
            property("horizontalFillType", "Max"),
            property("verticalAlignment", "Center"),
            property("horizontalArrangement", "SpaceBetween"),
            property("paddingHorizontal", "25"),
            property("paddingVertical", "12"),
        ),
        listOf(
            component(
                "column",
                listOf(),
                listOf(
                    component(
                        "text",
                        listOf(
                            property("text", whereUsedName),
                        )
                    ),
                    component(
                        "text",
                        listOf(
                            property("text", paymentType),
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
                            property("text", paymentValue),
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
                type = "continue",
                jsonObject(
                    "flowId" to "Home",
                    "nextScreenId" to "Cartoes/card2/billing/1",
                    "currentScreenId" to "Cartoes/card2/billing"
                )
            )
        )
    )

    override fun getScreenModel(screenData: JsonObject?): JsonObject = screen(
        "Home",
        "Cartoes/card2/billing",
        "",
        "",
        false,
        components = listOf(
            component(
                "lazyColumn",
                listOf(
                    property("horizontalFillType", "Max"),
                ),
                listOf(
                    topBarWithBackAction("Billing Description"),
                    billingItem("Loja do Zé", "Crédito", "R$ 120,00"),
                    horizontalDivider(),
                    billingItem("Supermercado Bom Preço", "Débito", "R$ 89,99"),
                    horizontalDivider(),
                    billingItem("Posto Shell", "Crédito", "R$ 200,50"),
                    horizontalDivider(),
                    billingItem("Netflix", "Débito automático", "R$ 39,90"),
                    horizontalDivider(),
                    billingItem("Spotify", "Débito automático", "R$ 19,90"),
                    horizontalDivider(),
                    billingItem("iFood", "Crédito", "R$ 45,00"),
                    horizontalDivider(),
                    billingItem("Uber", "Crédito", "R$ 27,80"),
                    horizontalDivider(),
                    billingItem("Farmácia São João", "Débito", "R$ 58,40"),
                    horizontalDivider(),
                    billingItem("Amazon", "Crédito", "R$ 110,00"),
                    horizontalDivider(),
                    billingItem("Mercado Central", "Débito", "R$ 72,10"),
                    horizontalDivider(),
                    billingItem("Padaria Pão Doce", "Crédito", "R$ 15,00"),
                    horizontalDivider(),
                    billingItem("Apple Store", "Crédito", "R$ 499,00"),
                    horizontalDivider(),
                    billingItem("Google Drive", "Débito automático", "R$ 9,90"),
                    horizontalDivider(),
                    billingItem("Academia Vida Saudável", "Débito", "R$ 120,00"),
                    horizontalDivider(),
                    billingItem("Cinema Lux", "Crédito", "R$ 35,00"),
                    horizontalDivider(),
                    billingItem("Livraria Cultura", "Crédito", "R$ 85,50"),
                    horizontalDivider(),
                    billingItem("Restaurante Bella", "Débito", "R$ 60,00"),
                    horizontalDivider(),
                    billingItem("Farmácia Popular", "Débito", "R$ 23,75"),
                    horizontalDivider(),
                    billingItem("Posto Ipiranga", "Crédito", "R$ 120,00"),
                    horizontalDivider(),
                    billingItem("Uber Eats", "Crédito", "R$ 40,00"),
                    horizontalDivider(),
                    billingItem("Magazine Online", "Débito", "R$ 150,00"),
                    horizontalDivider(),
                    billingItem("Spotify", "Débito automático", "R$ 19,90"),
                    horizontalDivider(),
                    billingItem("Netflix", "Débito automático", "R$ 39,90"),
                    horizontalDivider(),
                    billingItem("App Store", "Crédito", "R$ 29,99"),
                    horizontalDivider(),
                    billingItem("Google Play", "Crédito", "R$ 25,00"),
                    horizontalDivider(),
                    billingItem("Supermercado Bom Preço", "Débito", "R$ 89,99"),
                    horizontalDivider(),
                    billingItem("Taxi City", "Crédito", "R$ 18,50"),
                    horizontalDivider(),
                    billingItem("Padaria Central", "Débito", "R$ 10,00"),
                    horizontalDivider(),
                    billingItem("Cinema Lux", "Crédito", "R$ 35,00"),
                    horizontalDivider(),
                    billingItem("Mercado Verde", "Débito", "R$ 45,00"),
                    horizontalDivider(),
                    billingItem("Restaurante Italiano", "Crédito", "R$ 75,00"),
                    horizontalDivider(),
                    billingItem("Farmácia São João", "Débito", "R$ 58,40"),
                    horizontalDivider(),
                    billingItem("Amazon", "Crédito", "R$ 110,00"),
                    horizontalDivider()
                )
            )
        ),
    )
}