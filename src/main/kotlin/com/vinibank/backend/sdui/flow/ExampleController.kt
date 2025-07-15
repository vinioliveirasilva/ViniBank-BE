package com.vinibank.backend.sdui.flow

import com.vinibank.backend.sdui.SdUiScreen
import com.vinibank.backend.sdui.flow.ScreenUtil.component
import com.vinibank.backend.sdui.flow.ScreenUtil.property
import com.vinibank.backend.sdui.flow.ScreenUtil.screen
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.JsonObject
import org.springframework.http.ResponseEntity

object ExampleController {
    const val IDENTIFIER = "Example"

    fun getSdUiScreen(
        request: SdUiRequest,
    ) = getScreen(request)


    private fun getScreen(request: SdUiRequest): Pair<JsonObject, ResponseEntity<String>?> {
        return Pair(undefinedScreen.getScreenModel(JsonObject(emptyMap())), null)
    }

    val undefinedScreen = object : SdUiScreen {
        override fun getScreenModel(screenData: JsonObject?): JsonObject {
            return screen(
                flow = "",
                stage = "",
                version = "1",
                template = "",
                shouldCache = false,
                components = listOf(
                    component(
                        type = "lazyColumn",
                        properties = listOf(
                            property("horizontalFillType", "Max"),
                        ),
                        components = (1..1000).mapIndexed { index, _ ->
                            component(
                                type = "text",
                                properties = listOf(
                                    property("text", "Tela não cadastrada $index"),
                                    property("paddingVertical", "30"),
                                    property("horizontalFillType", "Max"),
                                    property("textAlign", "Center")
                                )
                            )
                        }
                    )
                )
            )
        }
    }
}