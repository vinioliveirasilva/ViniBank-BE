package com.vinibank.backend.sdui.flow.card.content

import com.vini.designsystemsdui.action.toIntAction
import com.vini.designsystemsdui.component.card
import com.vini.designsystemsdui.component.column
import com.vini.designsystemsdui.component.horizontalPager
import com.vini.designsystemsdui.component.image
import com.vini.designsystemsdui.component.lazyColumn
import com.vini.designsystemsdui.component.row
import com.vini.designsystemsdui.component.sdUi
import com.vini.designsystemsdui.component.text
import com.vini.designsystemsdui.property.ContentPaddingProperty
import com.vini.designsystemsdui.property.CurrentPageProperty
import com.vini.designsystemsdui.property.CurrentScreenProperty
import com.vini.designsystemsdui.property.DrawableNameProperty
import com.vini.designsystemsdui.property.FlowIdentifierProperty
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.SizeProperty
import com.vini.designsystemsdui.property.StageIdentifierProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.VerticalFillTypeProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.property.options.VerticalFillTypeOption
import com.vini.designsystemsdui.validator.intToStringValidator
import com.vinibank.backend.db.Card
import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen

class CardDetailContent {
    private fun getCard(card: Card, index: Int) = card(
        paddingHorizontalProperty = PaddingHorizontalProperty(10),
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
                        horizontalArrangement = HorizontalArrangementProperty(
                            HorizontalArrangementOption.SpaceBetween
                        ),
                        components = listOf(
                            text(
                                textProperty = TextProperty(card.name),
                            ),
                            text(
                                textProperty = TextProperty(
                                    "final ".plus(
                                        card.number.split(" ").last()
                                    )
                                ),
                            ),
                        )
                    ),
                    text(
                        textProperty = TextProperty(card.type),
                    ),
                    column(
                        verticalFillType = VerticalFillTypeProperty(VerticalFillTypeOption.Max),
                        horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        horizontalAlignment = HorizontalAlignmentProperty(HorizontalAlignmentOption.End),
                        verticalArrangement = VerticalArrangementProperty(VerticalArrangementOption.Bottom),
                        components = listOf(
                            image(
                                drawableName = DrawableNameProperty("Visa"),
                                size = SizeProperty(30),
                            ),
                        )
                    )
                ),
            )
        ),
        actions = listOf(
            toIntAction(
                idToChange = "CardsContent.SelectedCardIndex",
                newValue = index
            ),
        )
    )

    fun screen(cards: List<Card>) = screen(
        flow = "Card",
        stage = "Detail",
        version = "1",
        template = "",
        shouldCache = false,
        components = listOf(
            lazyColumn(
                paddingHorizontal = PaddingHorizontalProperty(10),
                weight = WeightProperty(1),
                horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                horizontalAlignment = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
                components = listOf(
                    horizontalPager(
                        contentPadding = ContentPaddingProperty(20),
                        currentPage = CurrentPageProperty(0, "CardsContent.SelectedCardIndex"),
                        components = cards.mapIndexed { index, card -> getCard(card, index) }
                    ),
                    sdUi(
                        flow = FlowIdentifierProperty("Card"),
                        currentScreen = CurrentScreenProperty("Detail"),
                        stage = StageIdentifierProperty("Detail", "CardsContent.SelectedCardId"),
                        horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        verticalFillType = VerticalFillTypeProperty(VerticalFillTypeOption.Max),
                        horizontalAlignment = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
                        weight = WeightProperty(1),
                        validators = listOf(
                            intToStringValidator(
                                id = "CardsContent.SelectedCardIndex",
                                intToString = cards.mapIndexed { index, card -> index to "Cartoes/${card.identifier}" },
                                required = listOf("CardsContent.SelectedCardIndex")
                            ),
                        )
                    ),
                    column(
                        paddingVertical = PaddingVerticalProperty(10),
                        paddingHorizontal = PaddingHorizontalProperty(25),
                        horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    ),
                )
            )
        )
    )
}