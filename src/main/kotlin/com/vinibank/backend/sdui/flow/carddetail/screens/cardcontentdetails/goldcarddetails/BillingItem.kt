package com.vinibank.backend.sdui.flow.carddetail.screens.cardcontentdetails.goldcarddetails

import com.vinibank.backend.sdui.SdUiScreen
import com.vinibank.backend.sdui.flow.ScreenUtil.action
import com.vinibank.backend.sdui.flow.ScreenUtil.component
import com.vinibank.backend.sdui.flow.ScreenUtil.property
import com.vinibank.backend.sdui.flow.ScreenUtil.screen
import kotlinx.serialization.json.JsonObject

object BillingItem : SdUiScreen {

    val topBar = component(
        "topAppBar",
        listOf(),
        listOf(),
        validators = listOf(),
        customComponents = arrayOf(
            "navigationIcons" to listOf(
                component(
                    "iconButton",
                    listOf(),
                    listOf(
                        component(
                            "icon",
                            listOf(
                                property("icon", "LeftArrow"),
                            )
                        )
                    ),
                    actions = listOf(
                        action("back")
                    ),
                ),
            )
        )
    )

    fun horizontalDivider() = component(
        "horizontalDivider",
        listOf(
            property("paddingVertical", "8"),
            property("paddingHorizontal", "16"),
        )
    )

    fun item(title: String, description: String, icon: String) = component(
        "row",
        listOf(
            property("horizontalFillType", "Max"),
            property("verticalAlignment", "Center"),
            property("paddingVertical", "16"),
            property("paddingHorizontal", "16"),
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
        "Cartoes",
        "1",
        "",
        false,
        components = listOf(
            component(
                "lazyColumn",
                listOf(
                    property("weight", "1"),
                    property("horizontalFillType", "Max"),
                    property("horizontalAlignment", "Center"),
                ),
                listOf(
                    topBar,
                    item("Valor Total", "R$ 1.000,00", "Money"),
                    horizontalDivider(),
                    item("Android Pay", "Gold Card, final 4869", ""),
                    horizontalDivider(),
                    item("Local de compra", "Carrefour Hipermercado", "Location"),
                    component(
                        "googleMaps"
                    )
                )
            )
        )
    )
}