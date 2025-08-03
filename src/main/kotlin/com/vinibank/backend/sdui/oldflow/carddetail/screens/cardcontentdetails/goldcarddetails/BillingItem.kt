package com.vinibank.backend.sdui.oldflow.carddetail.screens.cardcontentdetails.goldcarddetails
//
//import com.vinibank.backend.sdui.oldflow.SdUiScreenOLD
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.action
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.component
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.property
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen
//import kotlinx.serialization.json.JsonObject
//
//object BillingItem : SdUiScreenOLD {
//
//    val topBar = component(
//        "topAppBar",
//        listOf(),
//        listOf(),
//        validators = listOf(),
//        customComponents = arrayOf(
//            "navigationIcons" to listOf(
//                component(
//                    "iconButton",
//                    listOf(),
//                    listOf(
//                        component(
//                            "icon",
//                            listOf(
//                                property("icon", "LeftArrow"),
//                            )
//                        )
//                    ),
//                    actions = listOf(
//                        action("back")
//                    ),
//                ),
//            )
//        )
//    )
//
//    fun horizontalDivider() = component(
//        "horizontalDivider",
//        listOf(
//            property("paddingVertical", "8"),
//            paddingHorizontal = PaddingHorizontalProperty(16"),
//        )
//    )
//
//    fun item(title: String, description: String, icon: String) = component(
//        "row",
//        listOf(
//            horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//            property("verticalAlignment", "Center"),
//            property("paddingVertical", "16"),
//            paddingHorizontal = PaddingHorizontalProperty(16"),
//        ),
//        listOf(
//            component(
//                "icon",
//                listOf(
//                    property("icon", icon),
//                    size = SizeProperty(24"),
//                )
//            ),
//            component(
//                "column",
//                listOf(
//                    paddingHorizontal = PaddingHorizontalProperty(16"),
//                ),
//                listOf(
//                    component(
//                        "text",
//                        listOf(
//                            text = TextProperty(title),
//                        )
//                    ),
//                    component(
//                        "text",
//                        listOf(
//                            text = TextProperty(description),
//                        )
//                    )
//                )
//            )
//        )
//    )
//
//    override fun getScreenModel(screenData: JsonObject?): JsonObject = screen(
//        "Home",
//        "Cartoes",
//        "1",
//        "",
//        false,
//        components = listOf(
//            component(
//                "lazyColumn",
//                listOf(
//                    weight = WeightProperty(1),
//                    horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//                    horizontalAlignment = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
//                ),
//                listOf(
//                    topBar,
//                    item("Valor Total", "R$ 1.000,00", "Money"),
//                    horizontalDivider(),
//                    item("Android Pay", "Gold Card, final 4869", ""),
//                    horizontalDivider(),
//                    item("Local de compra", "Carrefour Hipermercado", "Location"),
//                    component(
//                        "googleMaps"
//                    )
//                )
//            )
//        )
//    )
//}