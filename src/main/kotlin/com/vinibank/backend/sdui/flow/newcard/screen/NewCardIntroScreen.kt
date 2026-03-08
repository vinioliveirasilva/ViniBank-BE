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
import com.vini.designsystemsdui.core.SdUiComposer
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.clickable
import com.vini.designsystemsdui.modifier.fillMaxHeight
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.height
import com.vini.designsystemsdui.modifier.option.HorizontalAlignmentOption
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.size
import com.vini.designsystemsdui.modifier.option.HorizontalArrangementOption
import com.vini.designsystemsdui.modifier.option.IconOption
import com.vini.designsystemsdui.modifier.option.VerticalArrangementOption
import com.vini.designsystemsdui.InteractionId
import com.vini.designsystemsdui.modifier.option.DrawableOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.sdui.flow.newcard.NewCardScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component
import com.vinibank.backend.db.Card as CardDbModel

@Component
class NewCardIntroScreen : NewCardScreen {

    override val screenId: String = "Start"

    private val selectedCardIndex = InteractionId<Int>("CardsContent.SelectedCardIndex")

    private fun SdUiComposer.cardItem(card: CardDbModel, index: Int) {
        Card(
            modifier = SdUiModifier().padding(horizontal = 30).padding(vertical = 10).fillMaxWidth()
                .height(180).clickable(action = ToNumberAction(selectedCardIndex, index)),
            content = {
                Column(
                    modifier = SdUiModifier().padding(horizontal = 20).padding(vertical = 20)
                        .fillMaxHeight(),
                    content = {
                        Row(
                            modifier = SdUiModifier().fillMaxWidth(),
                            horizontalArrangement = (
                                HorizontalArrangementOption.SpaceBetween()
                            ),
                            content = {
                                Text(text = card.name)
                                Text(
                                    text = (
                                        "final ".plus(card.number.split(" ").last())
                                    )
                                )
                            }
                        )
                        Text(text = card.type)
                        Column(
                            modifier = SdUiModifier().fillMaxHeight().fillMaxWidth(),
                            horizontalAlignment = (
                                HorizontalAlignmentOption.End()
                            ),
                            verticalArrangement = (
                                VerticalArrangementOption.Bottom()
                            ),
                            content = {
                                Image(
                                    modifier = SdUiModifier().size(30),
                                    iconDrawable = DrawableOption.Visa,
                                )
                            }
                        )
                    }
                )
            },
        )
    }

    override fun getScreen(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? {
        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            content = {
                TopAppBar(
                    title = {
                        Text(text = "Select your card")
                    },
                    navigationIcon = {
                        IconButton(
                            content = {
                                Icon(icon = IconOption.LeftArrow)
                            },
                            onClickAction = CloseAction()
                        )
                    }
                )
                LazyColumn(
                    modifier = SdUiModifier().fillMaxWidth().fillMaxHeight(),
                    content = {
                        cardItem(
                            card = CardDbModel(
                                identifier = "",
                                name = "Platinum card",
                                type = "International",
                                number = "",
                                validUntil = "",
                                cvv = "",
                            ),
                            index = 0
                        )
                        cardItem(
                            card = CardDbModel(
                                identifier = "",
                                name = "Gold card",
                                type = "International",
                                number = "",
                                validUntil = "",
                                cvv = "",
                            ),
                            index = 1
                        )
                        cardItem(
                            card = CardDbModel(
                                identifier = "",
                                name = "Silver Card",
                                type = "National",
                                number = "",
                                validUntil = "",
                                cvv = "",
                            ),
                            index = 2
                        )
                    }
                )
            }
        )
    }
}
