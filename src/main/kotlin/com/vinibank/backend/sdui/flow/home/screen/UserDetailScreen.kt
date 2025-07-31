package com.vinibank.backend.sdui.flow.home.screen

import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.components.topBarWithBackAction
import com.vinibank.backend.sdui.oldflow.ScreenUtil
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.JsonObject
import org.springframework.stereotype.Component

@Component
class UserDetailScreen : HomeScreen {
    override val screenId: String
        get() = "UserDetail"

    override fun getScreen(request: SdUiRequest): JsonObject? {
        fun menuItem(name: String, icon: String? = null) = ScreenUtil.component(
            type = "column",
            properties = listOf(
                ScreenUtil.property("horizontalFillType", "Max"),
                ScreenUtil.property("paddingHorizontal", "20"),
                ScreenUtil.property("verticalArrangement", "Center"),
            ),
            components = listOf(
                ScreenUtil.component(
                    type = "row",
                    properties = listOf(
                        ScreenUtil.property("horizontalFillType", "Max"),
                        ScreenUtil.property("paddingVertical", "10"),
                        ScreenUtil.property("horizontalArrangement", "SpaceBetween"),
                        ScreenUtil.property("verticalAlignment", "Center"),
                    ),
                    components = listOf(
                        ScreenUtil.component(
                            type = "row",
                            properties = listOf(
                                ScreenUtil.property("verticalAlignment", "Center"),
                            ),
                            components = listOfNotNull(
                                icon?.let {
                                    ScreenUtil.component(
                                        type = "icon",
                                        properties = listOf(
                                            ScreenUtil.property("icon", it),
                                            ScreenUtil.property(
                                                "paddingHorizontal",
                                                "10"
                                            ),
                                            ScreenUtil.property("size", "48"),
                                        )
                                    )
                                },
                                ScreenUtil.component(
                                    type = "text",
                                    properties = listOf(
                                        ScreenUtil.property("text", name),
                                    )
                                ),
                            )
                        ),
                        ScreenUtil.component(
                            type = "icon",
                            properties = listOf(
                                ScreenUtil.property("icon", "RightArrow"),
                                ScreenUtil.property("paddingHorizontal", "10"),
                            )
                        )
                    )
                ),
                ScreenUtil.component(
                    type = "horizontalDivider",
                    properties = listOf()
                )
            ),
            actions = listOf(
                ScreenUtil.action(
                    "continue",
                    ScreenUtil.jsonObject(
                        "flowId" to "TODO",
                        "nextScreenId" to "TODO",
                        "currentScreenId" to "UserDetail"
                    )
                )
            ),
        )

        val content = ScreenUtil.component(
            type = "lazyColumn",
            properties = listOf(
                ScreenUtil.property("horizontalFillType", "Max"),
                ScreenUtil.property("weight", "1"),
            ),
            components = listOf(
                ScreenUtil.component(
                    type = "card",
                    properties = listOf(
                        ScreenUtil.property("paddingHorizontal", "20"),
                        ScreenUtil.property("paddingVertical", "10"),
                        ScreenUtil.property("horizontalFillType", "Max"),
                    ),
                    components = listOf(
                        ScreenUtil.component(
                            type = "column",
                            properties = listOf(
                                ScreenUtil.property("paddingVertical", "20"),
                                ScreenUtil.property("horizontalAlignment", "Center"),
                                ScreenUtil.property("horizontalFillType", "Max"),
                            ),
                            components = listOf(
                                ScreenUtil.component(
                                    type = "icon",
                                    properties = listOf(
                                        ScreenUtil.property("icon", "User"),
                                        ScreenUtil.property(
                                            "paddingVertical",
                                            "20"
                                        ),
                                        ScreenUtil.property("size", "96"),
                                    )
                                ),
                                ScreenUtil.component(
                                    type = "text",
                                    properties = listOf(
                                        ScreenUtil.property("text", "Vinicius Oliveira"),
                                        ScreenUtil.property("paddingHorizontal", "20"),
                                    )
                                ),
                                ScreenUtil.component(
                                    type = "text",
                                    properties = listOf(
                                        ScreenUtil.property(
                                            "text",
                                            "vinioliveirasilva@hotmail.com"
                                        ),
                                        ScreenUtil.property("paddingHorizontal", "20"),
                                    )
                                ),
                                ScreenUtil.component(
                                    type = "text",
                                    properties = listOf(
                                        ScreenUtil.property("text", "+55 11 9 77801285"),
                                        ScreenUtil.property("paddingHorizontal", "20"),
                                    )
                                )
                            )
                        )
                    )
                ),
                menuItem("Dados Pessoais", "PersonSearch"),
                menuItem("Privacidade de dados", "Lock"),
                menuItem("Tema", "Theme"),
                menuItem("Sair do App", "Logout"),
            )
        )

        val screen = ScreenUtil.screen(
            flow = "Home",
            stage = "UserDetail",
            version = "1",
            template = "",
            shouldCache = false,
            components = listOf(
                topBarWithBackAction("User Detail"),
                content,
            )
        )
        return screen
    }
}