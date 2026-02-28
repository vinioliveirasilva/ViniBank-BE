package com.vinibank.backend.sdui.flow.card.content

import com.vini.designsystemsdui.action.ToNumberAction
import com.vini.designsystemsdui.component.Card
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.HorizontalPager
import com.vini.designsystemsdui.component.Image
import com.vini.designsystemsdui.component.LazyColumn
import com.vini.designsystemsdui.component.Row
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.fillMaxHeight
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.height
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.size
import com.vini.designsystemsdui.property.ContentPaddingProperty
import com.vini.designsystemsdui.property.CurrentPageProperty
import com.vini.designsystemsdui.property.DrawableNameProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.property.util.PropertyIdWrapper
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.sdui.flow.RoutingController
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import com.vinibank.backend.db.Card as CardDbModel


@Component
class CardDetailContent(
    @Lazy private val routingController: RoutingController,
) {

    private val selectedCardIndex = PropertyIdWrapper<Int>("CardsContent.SelectedCardIndex")
    private val selectedCardId = PropertyIdWrapper<String>("CardsContent.SelectedCardId")

    private fun getCard(card: CardDbModel, index: Int) = Card(
        modifier = SdUiModifier().padding(horizontal = 10).fillMaxWidth().height(180),
        content = listOf(
            Column(
                modifier = SdUiModifier().padding(horizontal = 20).padding(vertical = 20)
                    .fillMaxHeight(),
                content = listOf(
                    Row(
                        modifier = SdUiModifier().fillMaxWidth(),
                        horizontalArrangementProperty = HorizontalArrangementProperty(
                            HorizontalArrangementOption.SpaceBetween
                        ),
                        content = listOf(
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
                        modifier = SdUiModifier().fillMaxHeight().fillMaxWidth(),
                        horizontalAlignmentProperty = HorizontalAlignmentProperty(
                            HorizontalAlignmentOption.End
                        ),
                        verticalArrangementProperty = VerticalArrangementProperty(
                            VerticalArrangementOption.Bottom
                        ),
                        content = listOf(
                            Image(
                                modifier = SdUiModifier().size(30),
                                drawableNameProperty = DrawableNameProperty("Visa"),
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
        content = listOf(
            LazyColumn(
                modifier = SdUiModifier().padding(horizontal = 10).fillMaxWidth(),
                weightProperty = WeightProperty(1f),
                horizontalAlignmentProperty = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
                content = listOf(
                    HorizontalPager(
                        contentPaddingProperty = ContentPaddingProperty(20),
                        currentPageProperty = CurrentPageProperty(0, selectedCardIndex),
                        pageContent = cards.mapIndexed { index, card -> getCard(card, index) }
                    ),
//                    SdUi(
//                        flowIdentifierProperty = FlowIdentifierProperty("Card"),
//                        fromScreenIdentifierProperty = FromScreenIdentifierProperty("Detail"),
//                        stageIdentifierProperty = StageIdentifierProperty("Detail", selectedCardId),
//                        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
//                        verticalFillTypeProperty = VerticalFillTypeProperty(VerticalFillTypeOption.Max),
//                        weightProperty = WeightProperty(1f),
//                        validators = listOf(
//                            intToStringValidator(
//                                idWrapper = selectedCardId,
//                                intToString = cards.mapIndexed { index, card -> index to "Cartoes/${card.identifier}" },
//                                required = listOf(selectedCardIndex)
//                            ),
//                        ),
//                        template =
//                    ),
                    Column(
                        modifier = SdUiModifier().padding(vertical = 10).padding(horizontal = 25)
                            .fillMaxWidth(),
                    ),
                )
            )
        )
    )
}