package com.vinibank.backend.sdui.flow.newcard.screen

import com.vini.designsystemsdui.action.toIntAction
import com.vini.designsystemsdui.component.card
import com.vini.designsystemsdui.component.column
import com.vini.designsystemsdui.component.image
import com.vini.designsystemsdui.component.lazyColumn
import com.vini.designsystemsdui.component.row
import com.vini.designsystemsdui.component.text
import com.vini.designsystemsdui.component.topBar
import com.vini.designsystemsdui.property.DrawableNameProperty
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
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
        components = listOf(
            column(
                paddingHorizontal = PaddingHorizontalProperty(20),
                paddingVertical = PaddingVerticalProperty(20),
                verticalFillType = VerticalFillTypeProperty(VerticalFillTypeOption.Max),
                components = listOf(
                    row(
                        horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        horizontalArrangement = HorizontalArrangementProperty(HorizontalArrangementOption.SpaceBetween),
                        components = listOf(
                            text(TextProperty(card.name)),
                            text(TextProperty("final ".plus(card.number.split(" ").last())))
                        )
                    ),
                    text(TextProperty(card.type)),
                    column(
                        verticalFillType = VerticalFillTypeProperty(VerticalFillTypeOption.Max),
                        horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        horizontalAlignment = HorizontalAlignmentProperty(HorizontalAlignmentOption.End),
                        verticalArrangement = VerticalArrangementProperty(VerticalArrangementOption.Bottom),
                        components = listOf(
                            image(
                                drawableName = DrawableNameProperty("Visa"),
                                size = SizeProperty(30)
                            )
                        )
                    ),
                )
            ),
        ),
        actions = listOf(
            toIntAction("CardsContent.SelectedCardIndex", index)
        )
    )

    override fun getScreen(request: SdUiRequest): JsonObject? {
        return screen(
            flow = "Home",
            stage = "UserDetail",
            version = "1",
            template = "",
            shouldCache = false,
            components = listOf(
                topBar(
                    components = listOf(text(TextProperty("Select your card")))
                ),
                lazyColumn(
                    horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    weight = WeightProperty(1),
                    components = listOf(
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