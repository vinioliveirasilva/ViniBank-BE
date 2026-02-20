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
import com.vini.designsystemsdui.property.ContentPaddingProperty
import com.vini.designsystemsdui.property.CurrentPageProperty
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.SizeProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalAlignmentProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.VerticalFillTypeProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.IconOption
import com.vini.designsystemsdui.property.options.VerticalAlignmentOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.property.options.VerticalFillTypeOption
import com.vini.designsystemsdui.template.DefaultTemplate


class AddNewCardContent {
    private fun item(title: String, description: String, icon: IconOption) = Row(
        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
        verticalAlignmentProperty = VerticalAlignmentProperty(VerticalAlignmentOption.Center),
        paddingVerticalProperty = PaddingVerticalProperty(16),
        content = listOf(
            Icon(
                iconNameProperty = IconNameProperty(icon),
                sizeProperty = SizeProperty(24),
            ),
            Column(
                paddingHorizontalProperty = PaddingHorizontalProperty(16),
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
                paddingHorizontalProperty = PaddingHorizontalProperty(10),
                weightProperty = WeightProperty(1f),
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                horizontalAlignmentProperty = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
                content = listOf(
                    HorizontalPager(
                        contentPaddingProperty = ContentPaddingProperty(20),
                        currentPageProperty = CurrentPageProperty(0),
                        pageContent = listOf(
                            Card(
                                paddingHorizontalProperty = PaddingHorizontalProperty(10),
                                horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                    HorizontalFillTypeOption.Max
                                ),
                                heightProperty = HeightProperty(180),
                                content = listOf(
                                    Column(
                                        paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                        paddingVerticalProperty = PaddingVerticalProperty(20),
                                        verticalFillTypeProperty = VerticalFillTypeProperty(
                                            VerticalFillTypeOption.Max
                                        ),
                                        horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                            HorizontalFillTypeOption.Max
                                        ),
                                        horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                            HorizontalAlignmentOption.Center
                                        ),
                                        verticalArrangementProperty = VerticalArrangementProperty(
                                            VerticalArrangementOption.Center
                                        ),
                                        content = listOf(
                                            Icon(
                                                iconNameProperty = IconNameProperty(IconOption.Add),
                                                sizeProperty = SizeProperty(30),
                                            ),
                                        ),
                                    )
                                ),
                                onClick = NavigateAction(flow = "NewCard"),
                            )
                        )
                    ),
                    Column(
                        paddingVerticalProperty = PaddingVerticalProperty(10),
                        paddingHorizontalProperty = PaddingHorizontalProperty(25),
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(
                            HorizontalFillTypeOption.Max
                        ),
                        content = listOf(
                            item(
                                "Até 3 adicionais",
                                "Conte com até 3 cartoes adicionais gratuitos com os mesmos beneficios do titular.",
                                IconOption.Payment
                            ),
                            HorizontalDivider(
                                paddingHorizontalProperty = PaddingHorizontalProperty(8),
                            ),
                            item(
                                "ViniBank Shop",
                                "Faça compras no ViniBank Shop com o seu cartão e tenha vantagens como cashback  e parcelamentos sem juros.",
                                IconOption.ShoppingBag
                            ),
                            HorizontalDivider(
                                paddingHorizontalProperty = PaddingHorizontalProperty(8),
                            ),
                            item(
                                "Concierge",
                                "Conte com assistencia pessoal para te ajudar na organização de viagens, procura de eeventos e incidação dos melhores restaurantes e servicos onde quer que voce esteja.",
                                IconOption.SupervisorAccount
                            ),
                        )
                    ),
                    Column(
                        paddingVerticalProperty = PaddingVerticalProperty(10),
                        paddingHorizontalProperty = PaddingHorizontalProperty(25),
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(
                            HorizontalFillTypeOption.Max
                        ),
                    ),
                )
            )
        )
    )
}