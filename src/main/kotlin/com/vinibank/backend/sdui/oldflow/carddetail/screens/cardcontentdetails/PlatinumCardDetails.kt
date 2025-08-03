package com.vinibank.backend.sdui.oldflow.carddetail.screens.cardcontentdetails
//
//import com.vinibank.backend.sdui.oldflow.SdUiScreenOLD
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.component
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.property
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen
//import kotlinx.serialization.json.JsonObject
//
//object PlatinumCardDetails : SdUiScreenOLD {
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
//            horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
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
//                            text = TextProperty("Fatura Fechada"),
//                        )
//                    ),
//                    component(
//                        "text",
//                        listOf(
//                            text = TextProperty("R$ 7.740,02"),
//                        )
//                    ),
//                    component(
//                        "text",
//                        listOf(
//                            text = TextProperty("vencimento: 06 jul", "vini2"),
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
//                            paddingHorizontal = PaddingHorizontalProperty(8"),
//                            text = TextProperty("Acessar fatura", "vini1"),
//                        )
//                    ),
//                    component(
//                        "icon",
//                        listOf(
//                            property("icon", "RightArrow"),
//                            size = SizeProperty(16"),
//                        )
//                    )
//                )
//            )
//        ),
//    )
//
//    fun automaticPayment() = component(
//        "row",
//        listOf(
//            horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//            property("verticalAlignment", "Center"),
//            property("horizontalArrangement", "SpaceBetween"),
//            property("paddingVertical", "12"),
//        ),
//        listOf(
//            component(
//                "text",
//                listOf(
//                    text = TextProperty("Débito automatico"),
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
//                            text = TextProperty("Ativado"),
//                            paddingHorizontal = PaddingHorizontalProperty(8"),
//                        )
//                    ),
//                    component(
//                        "icon",
//                        listOf(
//                            property("icon", "RightArrow"),
//                            size = SizeProperty(12"),
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
//            horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//            property("verticalAlignment", "Center"),
//            property("horizontalArrangement", "SpaceBetween"),
//            property("paddingVertical", "12"),
//        ),
//        listOf(
//            component(
//                "text",
//                listOf(
//                    text = TextProperty("Limite Disponivel"),
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
//                            text = TextProperty("R$ 106.011,49"),
//                            paddingHorizontal = PaddingHorizontalProperty(8"),
//                        )
//                    ),
//                    component(
//                        "icon",
//                        listOf(
//                            property("icon", "RightArrow"),
//                            size = SizeProperty(12"),
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
//            horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//            property("verticalAlignment", "Center"),
//            property("horizontalArrangement", "SpaceBetween"),
//            property("paddingVertical", "12"),
//        ),
//        listOf(
//            component(
//                "text",
//                listOf(
//                    text = TextProperty("Pontos e Beneficios"),
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
//                            text = TextProperty("7.754"),
//                            paddingHorizontal = PaddingHorizontalProperty(8"),
//                        )
//                    ),
//                    component(
//                        "icon",
//                        listOf(
//                            property("icon", "RightArrow"),
//                            size = SizeProperty(12"),
//                        )
//                    )
//                )
//            )
//        )
//    )
//
//    override fun getScreenModel(screenData: JsonObject?): JsonObject = screen(
//        "Home",
//        "Cartoes/card1",
//        "",
//        "",
//        true,
//        components = listOf(
//            component(
//                "column",
//                listOf(
//                    property("paddingVertical", "10"),
//                    paddingHorizontal = PaddingHorizontalProperty(25"),
//                    horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
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