package com.vinibank.backend.sdui.flow.home.content

import com.vini.designsystemsdui.CacheStrategy
import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.ToBooleanAction
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.IconButton
import com.vini.designsystemsdui.component.Row
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
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
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.sdui.flow.UpdateSdUiTemplateRequest
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.flow.home.content.CheckingAccountContent.Companion.checkingAccountTopSdUiRequestUpdate
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class BalanceContent : HomeScreen {
    override val screenId: String = "Balance"

    override fun getScreenUpdate(request: UpdateSdUiTemplateRequest): List<com.vini.designsystemsdui.Component> {
        return listOf(
            Text(
                id = "HomeBalance",
                textProperty = TextProperty("R$ ${Random.nextInt(10000)},00"),
                cacheStrategy = CacheStrategy.NoCache(),
            )
        )
    }

    override fun getScreen(request: SdUiRequest): Template? {
        return DefaultTemplate(
            flow = "Home",
            stage = screenId,
            version = "1",
            content = listOf(
                Row(
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    verticalFillTypeProperty = VerticalFillTypeProperty(VerticalFillTypeOption.Max),
                    verticalAlignmentProperty = VerticalAlignmentProperty(VerticalAlignmentOption.Center),
                    content = listOf(
                        Spacer(weightProperty = WeightProperty(1f)),
                        Column(
                            weightProperty = WeightProperty(10f),
                            paddingHorizontalProperty = PaddingHorizontalProperty(10),
                            paddingVerticalProperty = PaddingVerticalProperty(10),
                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                HorizontalAlignmentOption.Center
                            ),
                            content = listOf(
                                Text(textProperty = TextProperty("Balance")),
                                Text(
                                    id = "HomeBalance",
                                    textProperty = TextProperty("R$ ${Random.nextInt(10000)},00"),
                                    cacheStrategy = CacheStrategy.NoCache(),
                                ),
                                Text(textProperty = TextProperty("updated 10 min ago")),
                            )
                        ),
                        IconButton(
                            weightProperty = WeightProperty(1f),
                            content = listOf(
                                Icon(
                                    iconNameProperty = IconNameProperty("Autorenew")
                                )
                            ),
                            onClick = ToBooleanAction(
                                idToChange = checkingAccountTopSdUiRequestUpdate,
                                newValue = true
                            )
                        ),
                    )
                ),
            )
        )
    }
}