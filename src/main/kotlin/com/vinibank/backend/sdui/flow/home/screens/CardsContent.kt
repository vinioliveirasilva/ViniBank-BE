package com.vinibank.backend.sdui.flow.home.screens

import com.vinibank.backend.sdui.SdUiScreen
import org.json.JSONObject

object CardsContent : SdUiScreen {
    override fun getScreenModel(screenData: String) = JSONObject(
        mapOf(
            "flow" to "Home",
            "stage" to "Cartoes",
            "version" to "1",
            "template" to "",
            "shouldCache" to false,
            "components" to listOf(
                mapOf(
                    "type" to "lazyColumn",
                    "properties" to listOf(
                        mapOf("name" to "weight", "value" to "1"),
                        mapOf("name" to "horizontalFillType", "value" to "Max"),
                        mapOf("name" to "verticalArrangement", "value" to "Center"),
                        mapOf("name" to "horizontalAlignment", "value" to "Center"),
                    ),
                    "components" to listOf(
                        mapOf(
                            "type" to "text",
                            "properties" to listOf(
                                mapOf("name" to "text", "value" to "Conteudo de cartoes")
                            )
                        )
                    )
                )
            )
        )
    )
}