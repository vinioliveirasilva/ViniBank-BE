package com.vinibank.backend.sdui.oldflow.carddetail.screens.cardcontentdetails
//
//import com.vinibank.backend.sdui.oldflow.SdUiScreenOLD
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.action
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.component
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.jsonObject
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.property
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen
//import kotlinx.serialization.json.JsonObject
//
//object GoldCardDetails : SdUiScreenOLD {
//    fun horizontalDivider() = component(
//        "horizontalDivider",
//        listOf(
//            property("paddingVertical", "8")
//        )
//    )
//
//    fun billing() = component(
//        "row",
//        listOf(
//            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//            property("verticalAlignment", "Center"),
//            property("horizontalArrangement", "SpaceBetween"),
//        ),
//        listOf(
//            component(
//                "column",
//                listOf(),
//                listOf(
//                    component(
//                        "text",
//                        listOf(
//                            textProperty = TextProperty("Fatura Fechada"),
//                        )
//                    ),
//                    component(
//                        "text",
//                        listOf(
//                            textProperty = TextProperty("R$ 1.115,90"),
//                        )
//                    ),
//                    component(
//                        "text",
//                        listOf(
//                            textProperty = TextProperty("vencimento: 06 jul"),
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
//                            textProperty = TextProperty("Acessar fatura"),
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
//                "continue",
//                jsonObject(
//                    "flowId" to "Home",
//                    "nextScreenId" to "Cartoes/card2/billing",
//                    "currentScreenId" to "Cartoes/card2",
//                )
//            )
//        )
//    )
//
//    fun automaticPayment() = component(
//        "row",
//        listOf(
//            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//            property("verticalAlignment", "Center"),
//            property("horizontalArrangement", "SpaceBetween"),
//            property("paddingVertical", "12"),
//        ),
//        listOf(
//            component(
//                "text",
//                listOf(
//                    textProperty = TextProperty("Débito automatico"),
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
//                            textProperty = TextProperty("Desativado"),
//                            paddingHorizontalProperty = PaddingHorizontalProperty(8"),
//                        )
//                    ),
//                    component(
//                        "icon",
//                        listOf(
//                            property("icon", "RightArrow"),
//                            sizeProperty = SizeProperty(12"),
//                        )
//                    )
//                )
//            )
//        )
//    )
//
//    fun creditLimit() = component(
//        "row",
//        listOf(
//            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//            property("verticalAlignment", "Center"),
//            property("horizontalArrangement", "SpaceBetween"),
//            property("paddingVertical", "12"),
//        ),
//        listOf(
//            component(
//                "text",
//                listOf(
//                    textProperty = TextProperty("Limite Disponivel"),
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
//                            textProperty = TextProperty("R$ 26.880,10"),
//                            paddingHorizontalProperty = PaddingHorizontalProperty(8"),
//                        )
//                    ),
//                    component(
//                        "icon",
//                        listOf(
//                            property("icon", "RightArrow"),
//                            sizeProperty = SizeProperty(12"),
//                        )
//                    )
//                )
//            )
//        )
//    )
//
//    fun milhas() = component(
//        "row",
//        listOf(
//            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//            property("verticalAlignment", "Center"),
//            property("horizontalArrangement", "SpaceBetween"),
//            property("paddingVertical", "12"),
//        ),
//        listOf(
//            component(
//                "text",
//                listOf(
//                    textProperty = TextProperty("Pontos e Beneficios"),
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
//                            textProperty = TextProperty("2.166"),
//                            paddingHorizontalProperty = PaddingHorizontalProperty(8"),
//                        )
//                    ),
//                    component(
//                        "icon",
//                        listOf(
//                            property("icon", "RightArrow"),
//                            sizeProperty = SizeProperty(12"),
//                        )
//                    )
//                )
//            )
//        )
//    )
//
//    override fun getScreenModel(screenData: JsonObject?): JsonObject = screen(
//        "Home",
//        "Cartoes/card2",
//        "",
//        "",
//        true,
//        content =  listOf(
//            component(
//                "column",
//                listOf(
//                    property("paddingVertical", "10"),
//                    paddingHorizontalProperty = PaddingHorizontalProperty(25"),
//                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//                ),
//                listOf(
//                    billing(),
//                    horizontalDivider(),
//                    automaticPayment(),
//                    horizontalDivider(),
//                    creditLimit(),
//                    horizontalDivider(),
//                    milhas(),
//                )
//            )
//        ),
//    )
//}