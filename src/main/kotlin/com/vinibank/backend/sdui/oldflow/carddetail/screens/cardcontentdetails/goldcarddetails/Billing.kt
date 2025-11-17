package com.vinibank.backend.sdui.oldflow.carddetail.screens.cardcontentdetails.goldcarddetails
//
//import com.vinibank.backend.sdui.oldflow.SdUiScreenOLD
//import com.vinibank.backend.sdui.components.TopAppBarWithBackAction
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.action
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.component
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.jsonObject
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.property
//
//import kotlinx.serialization.json.JsonObject
//
//object Billing : SdUiScreenOLD {
//    fun horizontalDivider() = component(
//        "horizontalDivider",
//        listOf(
//            paddingHorizontalProperty = PaddingHorizontalProperty(25"),
//        )
//    )
//
//    fun billingItem(whereUsedName: String, paymentType: String, paymentValue: String) = component(
//        "row",
//        listOf(
//            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//            property("verticalAlignment", "Center"),
//            property("horizontalArrangement", "SpaceBetween"),
//            paddingHorizontalProperty = PaddingHorizontalProperty(25"),
//            property("paddingVertical", "12"),
//        ),
//        listOf(
//            component(
//                "Column",
//                listOf(),
//                listOf(
//                    component(
//                        "text",
//                        listOf(
//                            textProperty = TextProperty(whereUsedName),
//                        )
//                    ),
//                    component(
//                        "text",
//                        listOf(
//                            textProperty = TextProperty(paymentType),
//                        )
//                    ),
//                )
//            ),
//            component(
//                "row",
//                listOf(
//                    property("verticalAlignment", "Center"),
//                ),
//                listOf(
//                    component(
//                        "text",
//                        listOf(
//                            paddingHorizontalProperty = PaddingHorizontalProperty(8"),
//                            textProperty = TextProperty(paymentValue),
//                        )
//                    ),
//                    component(
//                        "icon",
//                        listOf(
//                            property("icon", "RightArrow"),
//                            sizeProperty = SizeProperty(16"),
//                        )
//                    )
//                )
//            )
//        ),
//        actions = listOf(
//            action(
//                type = "continue",
//                jsonObject(
//                    "flowId" to "Home",
//                    "nextScreenId" to "Cartoes/card2/billing/1",
//                    "currentScreenId" to "Cartoes/card2/billing"
//                )
//            )
//        )
//    )
//
//    override fun getScreenModel(screenData: JsonObject?): JsonObject = DefaultTemplate(
//        "Home",
//        "Cartoes/card2/billing",
//        "",
//        "",
//        false,
//        content =  listOf(
//            component(
//                "LazyColumn",
//                listOf(
//                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//                ),
//                listOf(
//                    TopAppBarWithBackAction("Billing Description"),
//                    billingItem("Loja do Zé", "Crédito", "R$ 120,00"),
//                    horizontalDivider(),
//                    billingItem("Supermercado Bom Preço", "Débito", "R$ 89,99"),
//                    horizontalDivider(),
//                    billingItem("Posto Shell", "Crédito", "R$ 200,50"),
//                    horizontalDivider(),
//                    billingItem("Netflix", "Débito automático", "R$ 39,90"),
//                    horizontalDivider(),
//                    billingItem("Spotify", "Débito automático", "R$ 19,90"),
//                    horizontalDivider(),
//                    billingItem("iFood", "Crédito", "R$ 45,00"),
//                    horizontalDivider(),
//                    billingItem("Uber", "Crédito", "R$ 27,80"),
//                    horizontalDivider(),
//                    billingItem("Farmácia São João", "Débito", "R$ 58,40"),
//                    horizontalDivider(),
//                    billingItem("Amazon", "Crédito", "R$ 110,00"),
//                    horizontalDivider(),
//                    billingItem("Mercado Central", "Débito", "R$ 72,10"),
//                    horizontalDivider(),
//                    billingItem("Padaria Pão Doce", "Crédito", "R$ 15,00"),
//                    horizontalDivider(),
//                    billingItem("Apple Store", "Crédito", "R$ 499,00"),
//                    horizontalDivider(),
//                    billingItem("Google Drive", "Débito automático", "R$ 9,90"),
//                    horizontalDivider(),
//                    billingItem("Academia Vida Saudável", "Débito", "R$ 120,00"),
//                    horizontalDivider(),
//                    billingItem("Cinema Lux", "Crédito", "R$ 35,00"),
//                    horizontalDivider(),
//                    billingItem("Livraria Cultura", "Crédito", "R$ 85,50"),
//                    horizontalDivider(),
//                    billingItem("Restaurante Bella", "Débito", "R$ 60,00"),
//                    horizontalDivider(),
//                    billingItem("Farmácia Popular", "Débito", "R$ 23,75"),
//                    horizontalDivider(),
//                    billingItem("Posto Ipiranga", "Crédito", "R$ 120,00"),
//                    horizontalDivider(),
//                    billingItem("Uber Eats", "Crédito", "R$ 40,00"),
//                    horizontalDivider(),
//                    billingItem("Magazine Online", "Débito", "R$ 150,00"),
//                    horizontalDivider(),
//                    billingItem("Spotify", "Débito automático", "R$ 19,90"),
//                    horizontalDivider(),
//                    billingItem("Netflix", "Débito automático", "R$ 39,90"),
//                    horizontalDivider(),
//                    billingItem("App Store", "Crédito", "R$ 29,99"),
//                    horizontalDivider(),
//                    billingItem("Google Play", "Crédito", "R$ 25,00"),
//                    horizontalDivider(),
//                    billingItem("Supermercado Bom Preço", "Débito", "R$ 89,99"),
//                    horizontalDivider(),
//                    billingItem("Taxi City", "Crédito", "R$ 18,50"),
//                    horizontalDivider(),
//                    billingItem("Padaria Central", "Débito", "R$ 10,00"),
//                    horizontalDivider(),
//                    billingItem("Cinema Lux", "Crédito", "R$ 35,00"),
//                    horizontalDivider(),
//                    billingItem("Mercado Verde", "Débito", "R$ 45,00"),
//                    horizontalDivider(),
//                    billingItem("Restaurante Italiano", "Crédito", "R$ 75,00"),
//                    horizontalDivider(),
//                    billingItem("Farmácia São João", "Débito", "R$ 58,40"),
//                    horizontalDivider(),
//                    billingItem("Amazon", "Crédito", "R$ 110,00"),
//                    horizontalDivider()
//                )
//            )
//        ),
//    )
//}