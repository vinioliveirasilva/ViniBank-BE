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
        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
        verticalAlignmentProperty = VerticalAlignmentProperty(VerticalAlignmentOption.Center),
        paddingVerticalProperty = PaddingVerticalProperty(16),
        content =  listOf(
            icon(
                iconNameProperty = IconNameProperty(icon),
                sizeProperty = SizeProperty(24),
            ),
            column(
                paddingHorizontalProperty = PaddingHorizontalProperty(16),
                content =  listOf(
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
        content =  listOf(
            lazyColumn(
                paddingHorizontalProperty = PaddingHorizontalProperty(10),
                weightProperty = WeightProperty(1f),
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                horizontalAlignmentProperty = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
                content =  listOf(
                    horizontalPager(
                        contentPaddingProperty = ContentPaddingProperty(20),
                        currentPageProperty = CurrentPageProperty(0, "CardsContent.SelectedCardIndex"),
                        pageContent =  listOf(
                            card(
                                paddingHorizontalProperty = PaddingHorizontalProperty(10),
                                horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                    HorizontalFillTypeOption.Max
                                ),
                                heightProperty = HeightProperty(180),
                                content =  listOf(
                                    column(
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
                                        content =  listOf(
                                            icon(
                                                iconNameProperty = IconNameProperty("Add"),
                                                sizeProperty = SizeProperty(30),
                                            ),
                                        ),
                                    )
                                ),
                                onClick = navigateAction(flow = "NewCard"),
                            )
                        )
                    ),
                    column(
                        paddingVerticalProperty = PaddingVerticalProperty(10),
                        paddingHorizontalProperty = PaddingHorizontalProperty(25),
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        content =  listOf(
                            item(
                                "Até 3 adicionais",
                                "Conte com até 3 cartoes adicionais gratuitos com os mesmos beneficios do titular.",
                                "Payment"
                            ),
                            horizontalDivider(
                                paddingHorizontalProperty = PaddingHorizontalProperty(8),
                            ),
                            item(
                                "ViniBank Shop",
                                "Faça compras no ViniBank Shop com o seu cartão e tenha vantagens como cashback  e parcelamentos sem juros.",
                                "ShoppingBag"
                            ),
                            horizontalDivider(
                                paddingHorizontalProperty = PaddingHorizontalProperty(8),
                            ),
                            item(
                                "Concierge",
                                "Conte com assistencia pessoal para te ajudar na organização de viagens, procura de eeventos e incidação dos melhores restaurantes e servicos onde quer que voce esteja.",
                                "SupervisorAccount"
                            ),
                        )
                    ),
                    column(
                        paddingVerticalProperty = PaddingVerticalProperty(10),
                        paddingHorizontalProperty = PaddingHorizontalProperty(25),
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    ),
                )
            )
        )
    )
}