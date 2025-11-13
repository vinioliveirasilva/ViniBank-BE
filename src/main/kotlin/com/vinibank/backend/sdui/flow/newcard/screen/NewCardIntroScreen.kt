package com.vinibank.backend.sdui.flow.newcard.screen

import com.vini.designsystemsdui.action.closeAction
import com.vini.designsystemsdui.action.toIntAction
import com.vini.designsystemsdui.component.card
import com.vini.designsystemsdui.component.column
import com.vini.designsystemsdui.component.icon
import com.vini.designsystemsdui.component.iconButton
import com.vini.designsystemsdui.component.image
import com.vini.designsystemsdui.component.lazyColumn
import com.vini.designsystemsdui.component.row
import com.vini.designsystemsdui.component.text
import com.vini.designsystemsdui.component.topAppBar
import com.vini.designsystemsdui.property.DrawableNameProperty
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.SizeProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.VerticalFillTypeProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.property.options.VerticalFillTypeOption
import com.vinibank.backend.db.Card
import com.vinibank.backend.sdui.flow.newcard.NewCardScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen
import kotlinx.serialization.json.JsonObject
import org.springframework.stereotype.Component

@Component
class NewCardIntroScreen : NewCardScreen {

    override val screenId: String = "newCard"

    private fun getCard(card: Card, index: Int) = card(
        paddingHorizontalProperty = PaddingHorizontalProperty(30),
        paddingVerticalProperty = PaddingVerticalProperty(10),
        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
        heightProperty = HeightProperty(180),
        content =  listOf(
            column(
                paddingHorizontalProperty = PaddingHorizontalProperty(20),
                paddingVerticalProperty = PaddingVerticalProperty(20),
                verticalFillTypeProperty = VerticalFillTypeProperty(VerticalFillTypeOption.Max),
                content =  listOf(
                    row(
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        horizontalArrangementProperty = HorizontalArrangementProperty(HorizontalArrangementOption.SpaceBetween),
                        content =  listOf(
                            text(textProperty = TextProperty(card.name)),
                            text(textProperty = TextProperty("final ".plus(card.number.split(" ").last())))
                        )
                    ),
                    text(textProperty = TextProperty(card.type)),
                    column(
                        verticalFillTypeProperty = VerticalFillTypeProperty(VerticalFillTypeOption.Max),
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        horizontalAlignmentProperty = HorizontalAlignmentProperty(HorizontalAlignmentOption.End),
                        verticalArrangementProperty = VerticalArrangementProperty(VerticalArrangementOption.Bottom),
                        content =  listOf(
                            image(
                                drawableNameProperty = DrawableNameProperty("Visa"),
                                sizeProperty = SizeProperty(30)
                            )
                        )
                    ),
                )
            ),
        ),
        onClick = toIntAction("CardsContent.SelectedCardIndex", index)
    )

    override fun getScreen(request: SdUiRequest): JsonObject? {
        return screen(
            flow = "Home",
            stage = "UserDetail",
            version = "1",
            template = "",
            shouldCache = false,
            content =  listOf(
                topAppBar(
                    title =  listOf(text(textProperty = TextProperty("Select your card"))),
                    navigationIcon = listOf(
                        iconButton(
                            content =  listOf(
                                icon(
                                    iconNameProperty = IconNameProperty("LeftArrow"),
                                )
                            ),
                            onClick = closeAction()
                        )
                    )
                ),
                lazyColumn(
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    weightProperty = WeightProperty(1f),
                    content =  listOf(
                        getCard(
                            card = Card(
                                identifier = "",
                                name = "Platinum card",
                                type = "International",
                                number = "",
                                validUntil = "",
                                cvv = "",
                            ),
                            index = 0
                        ),
                        getCard(
                            card = Card(
                                identifier = "",
                                name = "Gold card",
                                type = "International",
                                number = "",
                                validUntil = "",
                                cvv = "",
                            ),
                            index = 0
                        ),
                        getCard(
                            card = Card(
                                identifier = "",
                                name = "Silver Card",
                                type = "National",
                                number = "",
                                validUntil = "",
                                cvv = "",
                            ),
                            index = 0
                        ),
                    )
                ),
            )
        )
    }
}