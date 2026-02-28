package com.vinibank.backend.sdui.flow.card.content

import com.vini.designsystemsdui.action.NavigateAction
import com.vini.designsystemsdui.component.Card
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.HorizontalDivider
import com.vini.designsystemsdui.component.HorizontalPager
import com.vini.designsystemsdui.component.Icon
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
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalAlignmentProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.IconOption
import com.vini.designsystemsdui.property.options.VerticalAlignmentOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.template.DefaultTemplate


class AddNewCardContent {
    private fun item(title: String, description: String, icon: IconOption) = Row(
        modifier = SdUiModifier().fillMaxWidth().padding(vertical = 16),
        verticalAlignmentProperty = VerticalAlignmentProperty(VerticalAlignmentOption.Center),
        content = listOf(
            Icon(
                modifier = SdUiModifier().size(24),
                iconNameProperty = IconNameProperty(icon),
            ),
            Column(
                modifier = SdUiModifier().padding(horizontal = 16),
                content = listOf(
                    Text(
                        textProperty = TextProperty(title),
                    ),
                    Text(
                        textProperty = TextProperty(description),
                    ),
                )
            )
        )
    )

    fun defaultScreen() = DefaultTemplate(
        flow = "Card",
        stage = "newCard",
        version = "1",
        content = listOf(
            LazyColumn(
                modifier = SdUiModifier().padding(horizontal = 10).fillMaxWidth(),
                weightProperty = WeightProperty(1f),
                horizontalAlignmentProperty = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
                content = listOf(
                    HorizontalPager(
                        contentPaddingProperty = ContentPaddingProperty(20),
                        currentPageProperty = CurrentPageProperty(0),
                        pageContent = listOf(
                            Card(
                                modifier = SdUiModifier().padding(horizontal = 10).fillMaxWidth()
                                    .height(180),
                                content = listOf(
                                    Column(
                                        modifier = SdUiModifier().padding(horizontal = 20)
                                            .padding(vertical = 20).fillMaxHeight().fillMaxWidth(),
                                        horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                            HorizontalAlignmentOption.Center
                                        ),
                                        verticalArrangementProperty = VerticalArrangementProperty(
                                            VerticalArrangementOption.Center
                                        ),
                                        content = listOf(
                                            Icon(
                                                modifier = SdUiModifier().size(30),
                                                iconNameProperty = IconNameProperty(IconOption.Add),
                                            ),
                                        ),
                                    )
                                ),
                                onClick = NavigateAction(flow = "NewCard"),
                            )
                        )
                    ),
                    Column(
                        modifier = SdUiModifier().padding(vertical = 10).padding(horizontal = 25)
                            .fillMaxWidth(),
                        content = listOf(
                            item(
                                "Até 3 adicionais",
                                "Conte com até 3 cartoes adicionais gratuitos com os mesmos beneficios do titular.",
                                IconOption.Payment
                            ),
                            HorizontalDivider(
                                modifier = SdUiModifier().padding(horizontal = 8),
                            ),
                            item(
                                "ViniBank Shop",
                                "Faça compras no ViniBank Shop com o seu cartão e tenha vantagens como cashback  e parcelamentos sem juros.",
                                IconOption.ShoppingBag
                            ),
                            HorizontalDivider(
                                modifier = SdUiModifier().padding(horizontal = 8),
                            ),
                            item(
                                "Concierge",
                                "Conte com assistencia pessoal para te ajudar na organização de viagens, procura de eeventos e incidação dos melhores restaurantes e servicos onde quer que voce esteja.",
                                IconOption.SupervisorAccount
                            ),
                        )
                    ),
                    Column(
                        modifier = SdUiModifier().padding(vertical = 10).padding(horizontal = 25)
                            .fillMaxWidth(),
                    ),
                )
            )
        )
    )
}