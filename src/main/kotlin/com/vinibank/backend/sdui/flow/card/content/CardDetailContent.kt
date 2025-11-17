package com.vinibank.backend.sdui.flow.card.content

import com.vini.designsystemsdui.action.toIntAction
import com.vini.designsystemsdui.component.Card
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.HorizontalPager
import com.vini.designsystemsdui.component.Image
import com.vini.designsystemsdui.component.LazyColumn
import com.vini.designsystemsdui.component.Row
import com.vini.designsystemsdui.component.SdUi
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.property.ContentPaddingProperty
import com.vini.designsystemsdui.property.CurrentPageProperty
import com.vini.designsystemsdui.property.DrawableNameProperty
import com.vini.designsystemsdui.property.FlowIdentifierProperty
import com.vini.designsystemsdui.property.FromScreenIdentifierProperty
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
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vini.designsystemsdui.validator.intToStringValidator
import com.vinibank.backend.db.Card as CardDbModel


class CardDetailContent {
    private fun getCard(card: CardDbModel, index: Int) = Card(
        paddingHorizontalProperty = PaddingHorizontalProperty(10),
        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
        heightProperty = HeightProperty(180),
        content =  listOf(
            Column(
                paddingHorizontalProperty = PaddingHorizontalProperty(20),
                paddingVerticalProperty = PaddingVerticalProperty(20),
                verticalFillTypeProperty = VerticalFillTypeProperty(VerticalFillTypeOption.Max),
                content =  listOf(
                    Row(
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        horizontalArrangementProperty = HorizontalArrangementProperty(
                            HorizontalArrangementOption.SpaceBetween
                        ),
                        content =  listOf(
                            Text(
                                textProperty = TextProperty(card.name),
                            ),
                            Text(
                                textProperty = TextProperty(
                                    "final ".plus(
                                        card.number.split(" ").last()
                                    )
                                ),
                            ),
                        )
                    ),
                    Text(
                        textProperty = TextProperty(card.type),
                    ),
                    Column(
                        verticalFillTypeProperty = VerticalFillTypeProperty(VerticalFillTypeOption.Max),
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        horizontalAlignmentProperty = HorizontalAlignmentProperty(HorizontalAlignmentOption.End),
                        verticalArrangementProperty = VerticalArrangementProperty(VerticalArrangementOption.Bottom),
                        content =  listOf(
                            Image(
                                drawableNameProperty = DrawableNameProperty("Visa"),
                                sizeProperty = SizeProperty(30),
                            ),
                        )
                    )
                ),
            )
        ),
        onClick = toIntAction(
            idToChange = "CardsContent.SelectedCardIndex",
            newValue = index
        ),
    )

    fun defaultScreen(cards: List<CardDbModel>) = DefaultTemplate(
        flow = "Card",
        stage = "Detail",
        version = "1",
        template = "",
        content =  listOf(
            LazyColumn(
                paddingHorizontalProperty = PaddingHorizontalProperty(10),
                weightProperty = WeightProperty(1f),
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                horizontalAlignmentProperty = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
                content =  listOf(
                    HorizontalPager(
                        contentPaddingProperty = ContentPaddingProperty(20),
                        currentPageProperty = CurrentPageProperty(0, "CardsContent.SelectedCardIndex"),
                        pageContent =  cards.mapIndexed { index, card -> getCard(card, index) }
                    ),
                    SdUi(
                        flowIdentifierProperty = FlowIdentifierProperty("Card"),
                        fromScreenIdentifierProperty = FromScreenIdentifierProperty("Detail"),
                        stageIdentifierProperty = StageIdentifierProperty("Detail", "CardsContent.SelectedCardId"),
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        verticalFillTypeProperty = VerticalFillTypeProperty(VerticalFillTypeOption.Max),
                        weightProperty = WeightProperty(1f),
                        validators = listOf(
                            intToStringValidator(
                                id = "CardsContent.SelectedCardIndex",
                                intToString = cards.mapIndexed { index, card -> index to "Cartoes/${card.identifier}" },
                                required = listOf("CardsContent.SelectedCardIndex")
                            ),
                        )
                    ),
                    Column(
                        paddingVerticalProperty = PaddingVerticalProperty(10),
                        paddingHorizontalProperty = PaddingHorizontalProperty(25),
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    ),
                )
            )
        )
    )
}