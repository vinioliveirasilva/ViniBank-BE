package com.vinibank.backend.sdui.flow.home.content

import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.oldflow.ScreenUtil
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.JsonObject
import org.springframework.stereotype.Component

@Component
class BalanceContent : HomeScreen {
    override val screenId: String = "Balance"

    override fun getScreen(request: SdUiRequest): JsonObject? {
        val screenObj = ScreenUtil.screen(
            flow = "Home",
            stage = "Balance",
            version = "1",
            template = "",
            shouldCache = false,
            components = listOf(
                ScreenUtil.component(
                    "row",
                    listOf(
                        ScreenUtil.property("horizontalFillType", "Max"),
                        ScreenUtil.property("verticalFillType", "Max"),
                        ScreenUtil.property("verticalAlignment", "Center"),
                    ),
                    listOf(
                        ScreenUtil.component(
                            "spacer",
                            listOf(
                                ScreenUtil.property("weight", "1"),
                            )
                        ),
                        ScreenUtil.component(
                            "column",
                            listOf(
                                ScreenUtil.property("paddingHorizontal", "10"),
                                ScreenUtil.property("paddingVertical", "10"),
                                ScreenUtil.property("horizontalAlignment", "Center"),
                                ScreenUtil.property("weight", "10"),
                            ),
                            listOf(
                                ScreenUtil.component(
                                    "text",
                                    listOf(
                                        ScreenUtil.property("text", "Balance"),
                                    )
                                ),
                                ScreenUtil.component(
                                    "text",
                                    listOf(
                                        ScreenUtil.property("text", "R$ 100,00"),
                                    )
                                ),
                                ScreenUtil.component(
                                    "text",
                                    listOf(
                                        ScreenUtil.property("text", "updated 10 min ago"),
                                    )
                                )
                            )
                        ),
                        ScreenUtil.component(
                            "iconButton",
                            listOf(
                                ScreenUtil.property("weight", "1"),
                            ),
                            listOf(
                                ScreenUtil.component(
                                    "icon",
                                    listOf(
                                        ScreenUtil.property("icon", "Autorenew"),
                                    )
                                ),
                            ),
                            actions = listOf(
                                ScreenUtil.action(
                                    type = "toBoolean",
                                    data = ScreenUtil.jsonObject(
                                        "id" to "requestUpdate1",
                                        "value" to true
                                    )
                                )
                            )
                        ),
                    )
                )
            )
        )
        return screenObj
    }
}