package com.vinibank.backend.sdui.flow.home.screen

import com.vini.designsystemsdui.action.backAction
import com.vini.designsystemsdui.action.continueAction
import com.vini.designsystemsdui.component.card
import com.vini.designsystemsdui.component.column
import com.vini.designsystemsdui.component.horizontalDivider
import com.vini.designsystemsdui.component.icon
import com.vini.designsystemsdui.component.iconButton
import com.vini.designsystemsdui.component.lazyColumn
import com.vini.designsystemsdui.component.row
import com.vini.designsystemsdui.component.text
import com.vini.designsystemsdui.component.topAppBar
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.SizeProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalAlignmentProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.VerticalAlignmentOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen
import kotlinx.serialization.json.JsonObject
import org.springframework.stereotype.Component

@Component
class UserDetailScreen : HomeScreen {
    override val screenId: String
        get() = "UserDetail"

    override fun getScreen(request: SdUiRequest): JsonObject? {
        fun menuItem(name: String, icon: String? = null) = column(
            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
            paddingHorizontalProperty = PaddingHorizontalProperty(20),
            verticalArrangementProperty = VerticalArrangementProperty(VerticalArrangementOption.Center),
            content =  listOf(
                row(
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    paddingVerticalProperty = PaddingVerticalProperty(10),
                    horizontalArrangementProperty = HorizontalArrangementProperty(
                        HorizontalArrangementOption.SpaceBetween
                    ),
                    verticalAlignmentProperty = VerticalAlignmentProperty(VerticalAlignmentOption.Center),
                    content =  listOf(
                        row(
                            verticalAlignmentProperty = VerticalAlignmentProperty(VerticalAlignmentOption.Center),
                            content =  listOfNotNull(
                                icon?.let {
                                    icon(
                                        iconNameProperty = IconNameProperty(it),
                                        paddingHorizontalProperty = PaddingHorizontalProperty(10),
                                        sizeProperty = SizeProperty(48),
                                    )
                                },
                                text(textProperty = TextProperty(name))
                            )
                        ),
                        icon(
                            iconNameProperty = IconNameProperty("RightArrow"),
                            paddingHorizontalProperty = PaddingHorizontalProperty(10),
                        ),
                    )
                ),
                horizontalDivider(),
            ),
            onClick = continueAction(
                flowId = "TODO",
                currentScreenId = "UserDetail",
                nextScreenId = "TODO",
                screenData = request.screenData
            ),
        )

        val content = lazyColumn(
            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
            weightProperty = WeightProperty(1f),
            content =  listOf(
                card(
                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                    paddingVerticalProperty = PaddingVerticalProperty(10),
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    content =  listOf(
                        column(
                            paddingVerticalProperty = PaddingVerticalProperty(20),
                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                HorizontalAlignmentOption.Center
                            ),
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                HorizontalFillTypeOption.Max
                            ),
                            content =  listOf(
                                icon(
                                    iconNameProperty = IconNameProperty("User"),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                    sizeProperty = SizeProperty(96),
                                ),
                                text(
                                    textProperty = TextProperty("Vinicius Oliveira"),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                ),
                                text(
                                    textProperty = TextProperty("vinioliveirasilva@hotmail.com"),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                ),
                                text(
                                    textProperty = TextProperty("+55 11 9 77801285"),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                ),
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

        val screen = screen(
            flow = "Home",
            stage = "UserDetail",
            version = "1",
            template = "",
            shouldCache = false,
            content =  listOf(
                topAppBar(
                    title =  listOf(
                        text(textProperty = TextProperty("User Detail"))
                    ),
                    navigationIcon = listOf(
                        iconButton(
                            content =  listOf(
                                icon(iconNameProperty = IconNameProperty("LeftArrow"))
                            ),
                            onClick = backAction()
                        )
                    )
                ),
                content,
            )
        )
        return screen
    }
}