package com.vinibank.backend.sdui.flow.card.content

import com.vini.designsystemsdui.action.navigateAction
import com.vini.designsystemsdui.component.card
import com.vini.designsystemsdui.component.column
import com.vini.designsystemsdui.component.horizontalDivider
import com.vini.designsystemsdui.component.horizontalPager
import com.vini.designsystemsdui.component.icon
import com.vini.designsystemsdui.component.lazyColumn
import com.vini.designsystemsdui.component.row
import com.vini.designsystemsdui.component.text
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
import com.vini.designsystemsdui.property.options.VerticalAlignmentOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.property.options.VerticalFillTypeOption
import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen

class AddNewCardContent {
    private fun item(title: String, description: String, icon: String) = row(
        horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
        verticalAlignment = VerticalAlignmentProperty(VerticalAlignmentOption.Center),
        paddingVertical = PaddingVerticalProperty(16),
        components = listOf(
            icon(
                iconName = IconNameProperty(icon),
                size = SizeProperty(24),
            ),
            column(
                paddingHorizontal = PaddingHorizontalProperty(16),
                components = listOf(
                    text(
                        textProperty = TextProperty(title),
                    ),
                    text(
                        textProperty = TextProperty(description),
                    ),
                )
            )
        )
    )

    fun screen() = screen(
        flow = "Card",
        stage = "newCard",
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
                        components = listOf(
                            card(
                                paddingHorizontalProperty = PaddingHorizontalProperty(10),
                                horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                    HorizontalFillTypeOption.Max
                                ),
                                heightProperty = HeightProperty(180),
                                components = listOf(
                                    column(
                                        paddingHorizontal = PaddingHorizontalProperty(20),
                                        paddingVertical = PaddingVerticalProperty(20),
                                        verticalFillType = VerticalFillTypeProperty(
                                            VerticalFillTypeOption.Max
                                        ),
                                        horizontalFillType = HorizontalFillTypeProperty(
                                            HorizontalFillTypeOption.Max
                                        ),
                                        horizontalAlignment = HorizontalAlignmentProperty(
                                            HorizontalAlignmentOption.Center
                                        ),
                                        verticalArrangement = VerticalArrangementProperty(
                                            VerticalArrangementOption.Center
                                        ),
                                        components = listOf(
                                            icon(
                                                iconName = IconNameProperty("Add"),
                                                size = SizeProperty(30),
                                            ),
                                        ),
                                    )
                                ),
                                actions = listOf(
                                    navigateAction(
                                        flow = "NewCard"
                                    ),
                                )
                            )
                        )
                    ),
                    column(
                        paddingVertical = PaddingVerticalProperty(10),
                        paddingHorizontal = PaddingHorizontalProperty(25),
                        horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        components = listOf(
                            item(
                                "Até 3 adicionais",
                                "Conte com até 3 cartoes adicionais gratuitos com os mesmos beneficios do titular.",
                                "Payment"
                            ),
                            horizontalDivider(
                                paddingHorizontal = PaddingHorizontalProperty(8),
                            ),
                            item(
                                "ViniBank Shop",
                                "Faça compras no ViniBank Shop com o seu cartão e tenha vantagens como cashback  e parcelamentos sem juros.",
                                "ShoppingBag"
                            ),
                            horizontalDivider(
                                paddingHorizontal = PaddingHorizontalProperty(8),
                            ),
                            item(
                                "Concierge",
                                "Conte com assistencia pessoal para te ajudar na organização de viagens, procura de eeventos e incidação dos melhores restaurantes e servicos onde quer que voce esteja.",
                                "SupervisorAccount"
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