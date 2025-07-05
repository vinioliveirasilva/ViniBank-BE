package com.vinibank.backend.sdui.flow

import com.vinibank.backend.sdui.SdUiScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import org.json.JSONObject
import org.springframework.http.ResponseEntity

object UndefinedController {
    fun getSdUiScreen(
        request: SdUiRequest
    ) = getScreen(request)


    private fun getScreen(request: SdUiRequest): Pair<JSONObject, ResponseEntity<String>?> {
        return Pair(undefinedScreen.getScreenModel(""), null)
    }

    val undefinedScreen = object : SdUiScreen {
        override fun getScreenModel(screenData: String): JSONObject {
            return JSONObject(
                mapOf(
                    "flow" to "",
                    "stage" to "",
                    "version" to "1",
                    "template" to "",
                    "shouldCache" to false,
                    "components" to listOf(
                        mapOf(
                            "type" to "topAppBar",
                            "properties" to listOf<String>(),
                            "components" to listOf(
                                mapOf(
                                    "type" to "text",
                                    "properties" to listOf(
                                        mapOf(
                                            "name" to "text",
                                            "value" to "TODO",
                                        )
                                    )
                                )
                            ),
                            "navigationIcons" to listOf(
                                mapOf(
                                    "type" to "iconButton",
                                    "properties" to listOf<String>(),
                                    "action" to mapOf("type" to "back"),
                                    "components" to listOf(
                                        mapOf(
                                            "type" to "icon",
                                            "properties" to listOf(
                                                mapOf("name" to "icon", "value" to "LeftArrow"),
                                            )
                                        )
                                    )
                                )
                            )
                        ),
                        mapOf(
                            "type" to "text",
                            "properties" to listOf(
                                mapOf("name" to "text", "value" to "Tela não cadastrada"),
                                mapOf("name" to "paddingVertical", "value" to "30"),
                                mapOf("name" to "horizontalFillType", "value" to "Max"),
                                mapOf("name" to "textAlign", "value" to "Center"),
                            )
                        )
                    )
                )
            )
        }
    }
}