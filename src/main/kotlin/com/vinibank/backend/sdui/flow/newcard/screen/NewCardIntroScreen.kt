package com.vinibank.backend.sdui.flow.newcard.screen

import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.CloseAction
import com.vini.designsystemsdui.action.ToNumberAction
import com.vini.designsystemsdui.component.Card
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.IconButton
import com.vini.designsystemsdui.component.Image
import com.vini.designsystemsdui.component.LazyColumn
import com.vini.designsystemsdui.component.Row
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.component.TopAppBar
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
import com.vini.designsystemsdui.property.options.IconOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.property.options.VerticalFillTypeOption
import com.vini.designsystemsdui.property.util.PropertyIdWrapper
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.sdui.flow.newcard.NewCardScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component
import com.vinibank.backend.db.Card as CardDbModel

@Component
class NewCardIntroScreen : NewCardScreen {

    override val screenId: String = "Start"

    private val selectedCardIndex = PropertyIdWrapper<Int>("CardsContent.SelectedCardIndex")

    private fun getCard(card: CardDbModel, index: Int) = Card(
        paddingHorizontalProperty = PaddingHorizontalProperty(30),
        paddingVerticalProperty = PaddingVerticalProperty(10),
        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
        heightProperty = HeightProperty(180),
        content = listOf(
            Column(
                paddingHorizontalProperty = PaddingHorizontalProperty(20),
                paddingVerticalProperty = PaddingVerticalProperty(20),
                verticalFillTypeProperty = VerticalFillTypeProperty(VerticalFillTypeOption.Max),
                content = listOf(
                    Row(
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        horizontalArrangementProperty = HorizontalArrangementProperty(HorizontalArrangementOption.SpaceBetween),
                        content = listOf(
                            Text(textProperty = TextProperty(card.name)),
                            Text(textProperty = TextProperty("final ".plus(card.number.split(" ").last())))
                        )
                    ),
                    Text(textProperty = TextProperty(card.type)),
                    Column(
                        verticalFillTypeProperty = VerticalFillTypeProperty(VerticalFillTypeOption.Max),
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        horizontalAlignmentProperty = HorizontalAlignmentProperty(HorizontalAlignmentOption.End),
                        verticalArrangementProperty = VerticalArrangementProperty(VerticalArrangementOption.Bottom),
                        content = listOf(
                            Image(
                                drawableNameProperty = DrawableNameProperty("Visa"),
                                sizeProperty = SizeProperty(30)
                            )
                        )
                    ),
                )
            ),
        ),
        onClick = ToNumberAction(selectedCardIndex, index)
    )

    override fun getScreen(request: SdUiRequest): Template? {
        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            content = listOf(
                TopAppBar(
                    title = listOf(Text(textProperty = TextProperty("Select your card"))),
                    navigationIcon = listOf(
                        IconButton(
                            content = listOf(
                                Icon(
                                    iconNameProperty = IconNameProperty(IconOption.LeftArrow),
                                )
                            ),
                            onClick = CloseAction()
                        )
                    )
                ),
                LazyColumn(
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    weightProperty = WeightProperty(1f),
                    content = listOf(
                        getCard(
                            card = CardDbModel(
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
                            card = CardDbModel(
                                identifier = "",
                                name = "Gold card",
                                type = "International",
                                number = "",
                                validUntil = "",
                                cvv = "",
                            ),
                            index = 1
                        ),
                        getCard(
                            card = CardDbModel(
                                identifier = "",
                                name = "Silver Card",
                                type = "National",
                                number = "",
                                validUntil = "",
                                cvv = "",
                            ),
                            index = 2
                        ),
                    )
                ),
            )
        )
    }
}