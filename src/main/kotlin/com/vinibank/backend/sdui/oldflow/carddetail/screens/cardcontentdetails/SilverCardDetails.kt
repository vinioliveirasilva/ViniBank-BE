package com.vinibank.backend.sdui.oldflow.carddetail.screens.cardcontentdetails
//
//import com.vinibank.backend.sdui.oldflow.SdUiScreenOLD
//
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.component
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.property
//import kotlinx.serialization.json.JsonObject
//
//object SilverCardDetails : SdUiScreenOLD {
//    fun horizontalDivider() = component(
//        type = "horizontalDivider",
//        properties = listOf(
//            property("paddingVertical", "8")
//        )
//    )
//
//    fun billing() = component(
//        type = "row",
//        properties = listOf(
//            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//            property("verticalAlignment", "Center"),
//            property("horizontalArrangement", "SpaceBetween"),
//        ),
//        content =  listOf(
//            component(
//                type = "Column",
//                properties = listOf(),
//                content =  listOf(
//                    component(
//                        type = "text",
//                        properties = listOf(
//                            textProperty = TextProperty("Fatura Fechada"),
//                        )
//                    ),
//                    component(
//                        type = "text",
//                        properties = listOf(
//                            textProperty = TextProperty("R$ 177,42"),
//                        )
//                    ),
//                    component(
//                        type = "text",
//                        properties = listOf(
//                            textProperty = TextProperty("vencimento: 07 jul"),
//                        )
//                    ),
//                )
//            ),
//            component(
//                type = "row",
//                properties = listOf(
//                    property("verticalAlignment", "Center"),
//                ),
//                content =  listOf(
//                    component(
//                        type = "text",
//                        properties = listOf(
//                            paddingHorizontalProperty = PaddingHorizontalProperty(8"),
//                            textProperty = TextProperty("Acessar fatura"),
//                        )
//                    ),
//                    component(
//                        type = "icon",
//                        properties = listOf(
//                            property("icon", "RightArrow"),
//                            sizeProperty = SizeProperty(16"),
//                        )
//                    )
//                )
//            )
//        ),
//    )
//
//    fun automaticPayment() = component(
//        type = "row",
//        properties = listOf(
//            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//            property("verticalAlignment", "Center"),
//            property("horizontalArrangement", "SpaceBetween"),
//            property("paddingVertical", "12"),
//        ),
//        content =  listOf(
//            component(
//                type = "text",
//                properties = listOf(
//                    textProperty = TextProperty("Débito automatico"),
//                )
//            ),
//            component(
//                type = "row",
//                properties = listOf(
//                    property("verticalAlignment", "Center"),
//                ),
//                content =  listOf(
//                    component(
//                        type = "text",
//                        properties = listOf(
//                            textProperty = TextProperty("Ativado"),
//                            paddingHorizontalProperty = PaddingHorizontalProperty(8"),
//                        )
//                    ),
//                    component(
//                        type = "icon",
//                        properties = listOf(
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
//        type = "row",
//        properties = listOf(
//            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//            property("verticalAlignment", "Center"),
//            property("horizontalArrangement", "SpaceBetween"),
//            property("paddingVertical", "12"),
//        ),
//        content =  listOf(
//            component(
//                type = "text",
//                properties = listOf(
//                    textProperty = TextProperty("Limite Disponivel"),
//                )
//            ),
//            component(
//                type = "row",
//                properties = listOf(
//                    property("verticalAlignment", "Center"),
//                ),
//                content =  listOf(
//                    component(
//                        type = "text",
//                        properties = listOf(
//                            textProperty = TextProperty("R$ 980,58"),
//                            paddingHorizontalProperty = PaddingHorizontalProperty(8"),
//                        )
//                    ),
//                    component(
//                        type = "icon",
//                        properties = listOf(
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
//        type = "row",
//        properties = listOf(
//            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//            property("verticalAlignment", "Center"),
//            property("horizontalArrangement", "SpaceBetween"),
//            property("paddingVertical", "12"),
//        ),
//        content =  listOf(
//            component(
//                type = "text",
//                properties = listOf(
//                    textProperty = TextProperty("Pontos e Beneficios"),
//                )
//            ),
//            component(
//                type = "row",
//                properties = listOf(
//                    property("verticalAlignment", "Center"),
//                ),
//                content =  listOf(
//                    component(
//                        type = "text",
//                        properties = listOf(
//                            textProperty = TextProperty("Indisponivel"),
//                            paddingHorizontalProperty = PaddingHorizontalProperty(8"),
//                        )
//                    ),
//                    component(
//                        type = "icon",
//                        properties = listOf(
//                            property("icon", ""),
//                            sizeProperty = SizeProperty(12"),
//                        )
//                    )
//                )
//            )
//        )
//    )
//
//    override fun getScreenModel(screenData: JsonObject?): JsonObject =
//        DefaultTemplate(
//            "Home",
//            "Cartoes/card3",
//             "",
//            "",
//             true,
//            content =  listOf(
//                component(
//                    type = "Column",
//                    properties = listOf(
//                        property("paddingVertical", "10"),
//                        paddingHorizontalProperty = PaddingHorizontalProperty(25"),
//                        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//                    ),
//                    content =  listOf(
//                        billing(),
//                        horizontalDivider(),
//                        automaticPayment(),
//                        horizontalDivider(),
//                        creditLimit(),
//                        horizontalDivider(),
//                        milhas(),
//                    )
//                )
//            ),
//        )
//
//}