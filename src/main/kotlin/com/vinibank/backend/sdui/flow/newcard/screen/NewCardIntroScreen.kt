package com.vinibank.backend.sdui.flow.newcard.screen

import com.vini.designsystemsdui.core.SdUiNode.Template
import com.vini.designsystemsdui.ui.action.CloseAction
import com.vini.designsystemsdui.ui.action.ToNumberAction
import com.vini.designsystemsdui.ui.component.Card
import com.vini.designsystemsdui.ui.component.Column
import com.vini.designsystemsdui.ui.component.Icon
import com.vini.designsystemsdui.ui.component.IconButton
import com.vini.designsystemsdui.ui.component.Image
import com.vini.designsystemsdui.ui.component.LazyColumn
import com.vini.designsystemsdui.ui.component.Row
import com.vini.designsystemsdui.ui.component.Text
import com.vini.designsystemsdui.ui.component.TopAppBar
import com.vini.designsystemsdui.core.SdUiComposer
import com.vini.designsystemsdui.ui.modifier.Modifier
import com.vini.designsystemsdui.ui.modifier.clickable
import com.vini.designsystemsdui.ui.modifier.fillMaxHeight
import com.vini.designsystemsdui.ui.modifier.fillMaxWidth
import com.vini.designsystemsdui.ui.modifier.height
import com.vini.designsystemsdui.ui.modifier.option.HorizontalAlignmentOption
import com.vini.designsystemsdui.ui.modifier.padding
import com.vini.designsystemsdui.ui.modifier.size
import com.vini.designsystemsdui.ui.modifier.option.HorizontalArrangementOption
import com.vini.designsystemsdui.ui.modifier.option.IconOption
import com.vini.designsystemsdui.ui.modifier.option.VerticalArrangementOption
import com.vini.designsystemsdui.InteractionId
import com.vini.designsystemsdui.ui.modifier.option.DrawableOption
import com.vini.designsystemsdui.ui.template.ScreenTemplate
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
            modifier = Modifier.padding(horizontal = 30).padding(vertical = 10).fillMaxWidth()
                .height(180).clickable(action = ToNumberAction(selectedCardIndex, index)),
            content = {
                Column(
                    modifier = Modifier.padding(horizontal = 20).padding(vertical = 20)
                        .fillMaxHeight(),
                    content = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
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
                            modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                            horizontalAlignment = (
                                HorizontalAlignmentOption.End()
                            ),
                            verticalArrangement = (
                                VerticalArrangementOption.Bottom()
                            ),
                            content = {
                                Image(
                                    modifier = Modifier.size(30),
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
        return ScreenTemplate(
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
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
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
