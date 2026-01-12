package com.vinibank.backend.sdui.flow.card.content

import com.vini.designsystemsdui.action.ToNumberAction
import com.vini.designsystemsdui.component.*
import com.vini.designsystemsdui.property.*
import com.vini.designsystemsdui.property.options.*
import com.vini.designsystemsdui.property.util.PropertyIdWrapper
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vini.designsystemsdui.validator.intToStringValidator
import com.vinibank.backend.db.Card as CardDbModel


class CardDetailContent {

    private val selectedCardIndex = PropertyIdWrapper<Int>("CardsContent.SelectedCardIndex")
    private val selectedCardId = PropertyIdWrapper<String>("CardsContent.SelectedCardId")

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
        onClick = ToNumberAction(
            idToChange = selectedCardIndex,
            newValue = index
        ),
    )

    fun defaultScreen(cards: List<CardDbModel>) = DefaultTemplate(
        flow = "Card",
        stage = "Detail",
        version = "1",
        content =  listOf(
            LazyColumn(
                paddingHorizontalProperty = PaddingHorizontalProperty(10),
                weightProperty = WeightProperty(1f),
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                horizontalAlignmentProperty = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
                content =  listOf(
                    HorizontalPager(
                        contentPaddingProperty = ContentPaddingProperty(20),
                        currentPageProperty = CurrentPageProperty(0, selectedCardIndex),
                        pageContent =  cards.mapIndexed { index, card -> getCard(card, index) }
                    ),
                    SdUi(
                        flowIdentifierProperty = FlowIdentifierProperty("Card"),
                        fromScreenIdentifierProperty = FromScreenIdentifierProperty("Detail"),
                        stageIdentifierProperty = StageIdentifierProperty("Detail", selectedCardId),
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        verticalFillTypeProperty = VerticalFillTypeProperty(VerticalFillTypeOption.Max),
                        weightProperty = WeightProperty(1f),
                        validators = listOf(
                            intToStringValidator(
                                idWrapper = selectedCardId,
                                intToString = cards.mapIndexed { index, card -> index to "Cartoes/${card.identifier}" },
                                required = listOf(selectedCardIndex)
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