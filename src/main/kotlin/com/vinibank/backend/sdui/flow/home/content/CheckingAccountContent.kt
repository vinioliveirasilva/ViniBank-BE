package com.vinibank.backend.sdui.flow.home.content

import com.vini.designsystemsdui.action.toBooleanAction
import com.vini.designsystemsdui.component.bottomSheet
import com.vini.designsystemsdui.component.button
import com.vini.designsystemsdui.component.card
import com.vini.designsystemsdui.component.column
import com.vini.designsystemsdui.component.dialog
import com.vini.designsystemsdui.component.icon
import com.vini.designsystemsdui.component.lazyColumn
import com.vini.designsystemsdui.component.row
import com.vini.designsystemsdui.component.sdUi
import com.vini.designsystemsdui.component.snackBar
import com.vini.designsystemsdui.component.spacer
import com.vini.designsystemsdui.component.text
import com.vini.designsystemsdui.property.FromScreenIdentifierProperty
import com.vini.designsystemsdui.property.DrawableNameProperty
import com.vini.designsystemsdui.property.FlowIdentifierProperty
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.RequestUpdateProperty
import com.vini.designsystemsdui.property.VisibilityProperty
import com.vini.designsystemsdui.property.SizeProperty
import com.vini.designsystemsdui.property.StageIdentifierProperty
import com.vini.designsystemsdui.property.TextAlignProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalAlignmentProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.VerticalFillTypeProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.TextAlignOption
import com.vini.designsystemsdui.property.options.VerticalAlignmentOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.property.options.VerticalFillTypeOption
import com.vinibank.backend.sdui.flow.RoutingController
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen
import kotlinx.serialization.json.JsonObject
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class CheckingAccountContent(
    @Lazy private val routingController: RoutingController,
) : HomeScreen {
    fun actionIcon(name: String, icon: String) = column(
        horizontalAlignmentProperty = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
        content =  listOf(
            card(
                content =  listOf(
                    icon(
                        iconNameProperty = IconNameProperty(icon),
                        paddingVerticalProperty = PaddingVerticalProperty(20),
                        paddingHorizontalProperty = PaddingHorizontalProperty(25),
                        sizeProperty = SizeProperty(36),
                    )
                ),
                onClick = toBooleanAction(
                    idToChange = "123123",
                    newValue = true
                )
            ),
            text(
                textProperty = TextProperty(name),
                paddingVerticalProperty = PaddingVerticalProperty(10)
            ),
        ),
    )

    fun transactionItem() = card(
        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
        content =  listOf(
            row(
                paddingVerticalProperty = PaddingVerticalProperty(10),
                paddingHorizontalProperty = PaddingHorizontalProperty(10),
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                horizontalArrangementProperty = HorizontalArrangementProperty(HorizontalArrangementOption.SpaceBetween),
                content =  listOf(
                    row(
                        paddingVerticalProperty = PaddingVerticalProperty(10),
                        content =  listOf(
                            icon(
                                iconNameProperty = IconNameProperty("Money"),
                                paddingHorizontalProperty = PaddingHorizontalProperty(10),
                            ),
                            text(
                                textProperty = TextProperty("Gastou pra caralho"),
                                textAlignProperty = TextAlignProperty(
                                    TextAlignOption.Center
                                ),
                            )
                        ),
                    ),
                    row(
                        paddingVerticalProperty = PaddingVerticalProperty(10),
                        verticalAlignmentProperty = VerticalAlignmentProperty(VerticalAlignmentOption.Center),
                        content =  listOf(
                            text(
                                textProperty = TextProperty("-R$ 500.00"),
                                textAlignProperty = TextAlignProperty(
                                    TextAlignOption.End
                                ),
                            ),
                            icon(
                                iconNameProperty = IconNameProperty("RightArrow"),
                                sizeProperty = SizeProperty(18),
                            )
                        )
                    ),
                )
            )
        )
    )

    override val screenId: String
        get() = "ContaCorrente"

    override fun getScreen(request: SdUiRequest): JsonObject? = screen(
        "Home",
        "ContaCorrente",
        "1",
        "",
        false,
        content =  listOf(
            dialog(
                visibilityProperty = VisibilityProperty(false, "123abc")
            ),
            bottomSheet(
                visibilityProperty = VisibilityProperty(false, "123abc1"),
                content =  listOf(
                    button(
                        textProperty = TextProperty("Balance"),
                        onClick = toBooleanAction(
                            idToChange = "123abc",
                            newValue = true
                        )
                    ),
                    text(
                        textProperty = TextProperty("R$ 100,00"),
                    ),
                    text(
                        textProperty = TextProperty("updated 10 min ago"),
                    )
                )
            ),
            lazyColumn(
                weightProperty = WeightProperty(1f),
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                paddingHorizontalProperty = PaddingHorizontalProperty(10),
                horizontalAlignmentProperty = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
                content =  listOf(
                    column(
                        paddingVerticalProperty = PaddingVerticalProperty(10),
                        heightProperty = HeightProperty(110),
                        content =  listOf(
                            card(
                                heightProperty = HeightProperty(110),
                                content =  listOf(
                                    column(
                                        horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                            HorizontalAlignmentOption.Center
                                        ),
                                        verticalArrangementProperty = VerticalArrangementProperty(
                                            VerticalArrangementOption.Center
                                        ),
                                        verticalFillTypeProperty = VerticalFillTypeProperty(
                                            VerticalFillTypeOption.Max
                                        ),
                                        content =  listOf(
                                            sdUi(
                                                flowIdentifierProperty = FlowIdentifierProperty("Home"),
                                                stageIdentifierProperty = StageIdentifierProperty("Balance"),
                                                fromScreenIdentifierProperty = FromScreenIdentifierProperty("ContaCorrente"),
                                                requestUpdateProperty = RequestUpdateProperty(
                                                    false,
                                                    "requestUpdate1"
                                                ),
                                                horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                                    HorizontalFillTypeOption.Max
                                                ),
                                                components =  routingController.getSdUiComponents(
                                                    SdUiRequest(
                                                        request.flow,
                                                        screenId,
                                                        "Balance",
                                                        request.screenData
                                                    )
                                                ),
                                            ),
                                        )
                                    ),
                                )
                            ),
                            spacer(sizeProperty = SizeProperty(10))
                        )
                    ),
                    row(
                        paddingVerticalProperty = PaddingVerticalProperty(10),
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(
                            HorizontalFillTypeOption.Max
                        ),
                        horizontalArrangementProperty = HorizontalArrangementProperty(
                            HorizontalArrangementOption.SpaceBetween
                        ),
                        content =  listOf(
                            column(
                                horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                    HorizontalAlignmentOption.Center
                                ),
                                content =  listOf(
                                    card(
                                        content =  listOf(
                                            icon(
                                                drawableNameProperty = DrawableNameProperty("Pix"),
                                                paddingVerticalProperty = PaddingVerticalProperty(20),
                                                paddingHorizontalProperty = PaddingHorizontalProperty(25),
                                                sizeProperty = SizeProperty(36),
                                            )
                                        ),
                                        onClick = toBooleanAction(
                                            idToChange = "123abc",
                                            newValue = true
                                        )
                                    ),
                                    text(
                                        textProperty = TextProperty("Pix"),
                                        paddingVerticalProperty = PaddingVerticalProperty(10)

                                    )
                                )
                            ),
                            actionIcon("Transfer", "Payment"),
                            actionIcon("Pay", "ReceiptLong"),
                            column(
                                horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                    HorizontalAlignmentOption.Center
                                ),
                                content =  listOf(
                                    card(
                                        content =  listOf(
                                            icon(
                                                iconNameProperty = IconNameProperty("MoreVert"),
                                                paddingVerticalProperty = PaddingVerticalProperty(20),
                                                paddingHorizontalProperty = PaddingHorizontalProperty(25),
                                                sizeProperty = SizeProperty(36),
                                            ),
                                        ),
                                        onClick = toBooleanAction(
                                            idToChange = "123abc1",
                                            newValue = true
                                        )
                                    ),
                                    text(
                                        textProperty = TextProperty("More"),
                                        paddingVerticalProperty = PaddingVerticalProperty(10),
                                    )
                                )
                            ),
                        )
                    ),
                    column(
                        horizontalAlignmentProperty = HorizontalAlignmentProperty(
                            HorizontalAlignmentOption.Center
                        ),
                        paddingVerticalProperty = PaddingVerticalProperty(10),
                        verticalArrangementProperty = VerticalArrangementProperty(
                            VerticalArrangementOption.SpacedBy(10)
                        ),
                        weightProperty = WeightProperty(1f),
                        content =  listOf(
                            text(TextProperty("Last Transactions")),
                            transactionItem(),
                            transactionItem(),
                            transactionItem(),
                        )
                    ),
                )
            ),
            snackBar(
                textProperty = TextProperty("SnackBar"),
                visibilityProperty = VisibilityProperty(false, "123123")
            ),
        )
    )
}