package com.vinibank.backend.sdui.flow.home.content

import com.vini.designsystemsdui.action.toBooleanAction
import com.vini.designsystemsdui.component.column
import com.vini.designsystemsdui.component.icon
import com.vini.designsystemsdui.component.iconButton
import com.vini.designsystemsdui.component.row
import com.vini.designsystemsdui.component.spacer
import com.vini.designsystemsdui.component.text
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalAlignmentProperty
import com.vini.designsystemsdui.property.VerticalFillTypeProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.VerticalAlignmentOption
import com.vini.designsystemsdui.property.options.VerticalFillTypeOption
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen
import kotlinx.serialization.json.JsonObject
import org.springframework.stereotype.Component

@Component
class BalanceContent : HomeScreen {
    override val screenId: String = "Balance"

    override fun getScreen(request: SdUiRequest): JsonObject? {
        val screenObj = screen(
            flow = "Home",
            stage = "Balance",
            version = "1",
            template = "",
            shouldCache = false,
            components = listOf(
                row(
                    horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    verticalFillType = VerticalFillTypeProperty(VerticalFillTypeOption.Max),
                    verticalAlignment = VerticalAlignmentProperty(VerticalAlignmentOption.Center),
                    components = listOf(
                        spacer(weight = WeightProperty(1)),
                        column(
                            weight = WeightProperty(10),
                            paddingHorizontal = PaddingHorizontalProperty(10),
                            paddingVertical = PaddingVerticalProperty(10),
                            horizontalAlignment = HorizontalAlignmentProperty(
                                HorizontalAlignmentOption.Center),
                            components = listOf(
                                text(textProperty = TextProperty("Balance")),
                                text(textProperty = TextProperty("R$ 100,00")),
                                text(textProperty = TextProperty("updated 10 min ago")),
                            )
                        ),
                        iconButton(
                            weight = WeightProperty(1),
                            components = listOf(
                                icon(
                                    iconName = IconNameProperty("Autorenew")
                                )
                            ),
                            actions = listOf(
                                toBooleanAction(
                                    idToChange = "requestUpdate1",
                                    newValue = true
                                )
                            )
                        ),
                    )
                ),
            )
        )
        return screenObj
    }
}