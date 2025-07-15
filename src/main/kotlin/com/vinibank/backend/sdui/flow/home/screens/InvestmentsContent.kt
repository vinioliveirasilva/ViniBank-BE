package com.vinibank.backend.sdui.flow.home.screens

import com.vinibank.backend.sdui.SdUiScreen
import kotlinx.serialization.json.JsonObject
import com.vinibank.backend.sdui.flow.ScreenUtil.screen
import com.vinibank.backend.sdui.flow.ScreenUtil.component
import com.vinibank.backend.sdui.flow.ScreenUtil.property

object InvestmentsContent : SdUiScreen {
    override fun getScreenModel(screenData: JsonObject?): JsonObject = screen(
        flow = "Home",
        stage = "Investimentos",
        version = "1",
        template = "",
        shouldCache = false,
        components = listOf(
            component(
                type = "lazyColumn",
                properties = listOf(
                    property(name = "weight", value = "1"),
                    property(name = "horizontalFillType", value = "Max"),
                    property(name = "verticalArrangement", value = "Center"),
                    property(name = "horizontalAlignment", value = "Center"),
                ),
                components = listOf(
                    component(
                        type = "text",
                        properties = listOf(
                            property(name = "text", value = "Conteudo de investimentos")
                        )
                    )
                )
            )
        )
    )
}