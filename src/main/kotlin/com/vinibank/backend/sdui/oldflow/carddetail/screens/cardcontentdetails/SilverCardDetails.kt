package com.vinibank.backend.sdui.oldflow.carddetail.screens.cardcontentdetails
//
//import com.vinibank.backend.sdui.oldflow.SdUiScreenOLD
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen
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
//            horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//            property("verticalAlignment", "Center"),
//            property("horizontalArrangement", "SpaceBetween"),
//        ),
//        components = listOf(
//            component(
//                type = "column",
//                properties = listOf(),
//                components = listOf(
//                    component(
//                        type = "text",
//                        properties = listOf(
//                            text = TextProperty("Fatura Fechada"),
//                        )
//                    ),
//                    component(
//                        type = "text",
//                        properties = listOf(
//                            text = TextProperty("R$ 177,42"),
//                        )
//                    ),
//                    component(
//                        type = "text",
//                        properties = listOf(
//                            text = TextProperty("vencimento: 07 jul"),
//                        )
//                    ),
//                )
//            ),
//            component(
//                type = "row",
//                properties = listOf(
//                    property("verticalAlignment", "Center"),
//                ),
//                components = listOf(
//                    component(
//                        type = "text",
//                        properties = listOf(
//                            paddingHorizontal = PaddingHorizontalProperty(8"),
//                            text = TextProperty("Acessar fatura"),
//                        )
//                    ),
//                    component(
//                        type = "icon",
//                        properties = listOf(
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
//        type = "row",
//        properties = listOf(
//            horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//            property("verticalAlignment", "Center"),
//            property("horizontalArrangement", "SpaceBetween"),
//            property("paddingVertical", "12"),
//        ),
//        components = listOf(
//            component(
//                type = "text",
//                properties = listOf(
//                    text = TextProperty("Débito automatico"),
//                )
//            ),
//            component(
//                type = "row",
//                properties = listOf(
//                    property("verticalAlignment", "Center"),
//                ),
//                components = listOf(
//                    component(
//                        type = "text",
//                        properties = listOf(
//                            text = TextProperty("Ativado"),
//                            paddingHorizontal = PaddingHorizontalProperty(8"),
//                        )
//                    ),
//                    component(
//                        type = "icon",
//                        properties = listOf(
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
//        type = "row",
//        properties = listOf(
//            horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//            property("verticalAlignment", "Center"),
//            property("horizontalArrangement", "SpaceBetween"),
//            property("paddingVertical", "12"),
//        ),
//        components = listOf(
//            component(
//                type = "text",
//                properties = listOf(
//                    text = TextProperty("Limite Disponivel"),
//                )
//            ),
//            component(
//                type = "row",
//                properties = listOf(
//                    property("verticalAlignment", "Center"),
//                ),
//                components = listOf(
//                    component(
//                        type = "text",
//                        properties = listOf(
//                            text = TextProperty("R$ 980,58"),
//                            paddingHorizontal = PaddingHorizontalProperty(8"),
//                        )
//                    ),
//                    component(
//                        type = "icon",
//                        properties = listOf(
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
//        type = "row",
//        properties = listOf(
//            horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//            property("verticalAlignment", "Center"),
//            property("horizontalArrangement", "SpaceBetween"),
//            property("paddingVertical", "12"),
//        ),
//        components = listOf(
//            component(
//                type = "text",
//                properties = listOf(
//                    text = TextProperty("Pontos e Beneficios"),
//                )
//            ),
//            component(
//                type = "row",
//                properties = listOf(
//                    property("verticalAlignment", "Center"),
//                ),
//                components = listOf(
//                    component(
//                        type = "text",
//                        properties = listOf(
//                            text = TextProperty("Indisponivel"),
//                            paddingHorizontal = PaddingHorizontalProperty(8"),
//                        )
//                    ),
//                    component(
//                        type = "icon",
//                        properties = listOf(
//                            property("icon", ""),
//                            size = SizeProperty(12"),
//                        )
//                    )
//                )
//            )
//        )
//    )
//
//    override fun getScreenModel(screenData: JsonObject?): JsonObject =
//        screen(
//            "Home",
//            "Cartoes/card3",
//             "",
//            "",
//             true,
//            components = listOf(
//                component(
//                    type = "column",
//                    properties = listOf(
//                        property("paddingVertical", "10"),
//                        paddingHorizontal = PaddingHorizontalProperty(25"),
//                        horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//                    ),
//                    components = listOf(
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