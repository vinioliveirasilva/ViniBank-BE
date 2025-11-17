package com.vinibank.backend.sdui.oldflow.carddetail.screens.cardcontentdetails.goldcarddetails
//
//import com.vinibank.backend.sdui.oldflow.SdUiScreenOLD
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.action
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.component
//import com.vinibank.backend.sdui.oldflow.ScreenUtil.property
//
//import kotlinx.serialization.json.JsonObject
//
//object BillingItem : SdUiScreenOLD {
//
//    val TopAppBar = component(
//        "TopAppBar",
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
//            paddingHorizontalProperty = PaddingHorizontalProperty(16"),
//        )
//    )
//
//    fun item(title: String, description: String, icon: String) = component(
//        "row",
//        listOf(
//            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//            property("verticalAlignment", "Center"),
//            property("paddingVertical", "16"),
//            paddingHorizontalProperty = PaddingHorizontalProperty(16"),
//        ),
//        listOf(
//            component(
//                "icon",
//                listOf(
//                    property("icon", icon),
//                    sizeProperty = SizeProperty(24"),
//                )
//            ),
//            component(
//                "Column",
//                listOf(
//                    paddingHorizontalProperty = PaddingHorizontalProperty(16"),
//                ),
//                listOf(
//                    component(
//                        "text",
//                        listOf(
//                            textProperty = TextProperty(title),
//                        )
//                    ),
//                    component(
//                        "text",
//                        listOf(
//                            textProperty = TextProperty(description),
//                        )
//                    )
//                )
//            )
//        )
//    )
//
//    override fun getScreenModel(screenData: JsonObject?): JsonObject = DefaultTemplate(
//        "Home",
//        "Cartoes",
//        "1",
//        "",
//        false,
//        content =  listOf(
//            component(
//                "LazyColumn",
//                listOf(
//                    weightProperty = WeightProperty(1),
//                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//                    horizontalAlignmentProperty = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
//                ),
//                listOf(
//                    TopAppBar,
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