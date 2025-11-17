package com.vinibank.backend.sdui.flow.home.content

import com.vini.designsystemsdui.action.toBooleanAction
import com.vini.designsystemsdui.component.BottomSheet
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Card
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.Dialog
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.LazyColumn
import com.vini.designsystemsdui.component.Row
import com.vini.designsystemsdui.component.SdUi
import com.vini.designsystemsdui.component.SnackBar
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.property.DrawableNameProperty
import com.vini.designsystemsdui.property.FlowIdentifierProperty
import com.vini.designsystemsdui.property.FromScreenIdentifierProperty
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.RequestUpdateProperty
import com.vini.designsystemsdui.property.SizeProperty
import com.vini.designsystemsdui.property.StageIdentifierProperty
import com.vini.designsystemsdui.property.TextAlignProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalAlignmentProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.VerticalFillTypeProperty
import com.vini.designsystemsdui.property.VisibilityProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.TextAlignOption
import com.vini.designsystemsdui.property.options.VerticalAlignmentOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.property.options.VerticalFillTypeOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vini.designsystemsdui.template.Template
import com.vinibank.backend.sdui.flow.RoutingController
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.model.SdUiRequest

import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class CheckingAccountContent(
    @Lazy private val routingController: RoutingController,
) : HomeScreen {
    fun actionIcon(name: String, icon: String) = Column(
        horizontalAlignmentProperty = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
        content = listOf(
            Card(
                content = listOf(
                    Icon(
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
            Text(
                textProperty = TextProperty(name),
                paddingVerticalProperty = PaddingVerticalProperty(10)
            ),
        ),
    )

    fun transactionItem() = Card(
        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
        content = listOf(
            Row(
                paddingVerticalProperty = PaddingVerticalProperty(10),
                paddingHorizontalProperty = PaddingHorizontalProperty(10),
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                horizontalArrangementProperty = HorizontalArrangementProperty(
                    HorizontalArrangementOption.SpaceBetween
                ),
                content = listOf(
                    Row(
                        paddingVerticalProperty = PaddingVerticalProperty(10),
                        content = listOf(
                            Icon(
                                iconNameProperty = IconNameProperty("Money"),
                                paddingHorizontalProperty = PaddingHorizontalProperty(10),
                            ),
                            Text(
                                textProperty = TextProperty("Gastou pra caralho"),
                                textAlignProperty = TextAlignProperty(
                                    TextAlignOption.Center
                                ),
                            )
                        ),
                    ),
                    Row(
                        paddingVerticalProperty = PaddingVerticalProperty(10),
                        verticalAlignmentProperty = VerticalAlignmentProperty(
                            VerticalAlignmentOption.Center
                        ),
                        content = listOf(
                            Text(
                                textProperty = TextProperty("-R$ 500.00"),
                                textAlignProperty = TextAlignProperty(
                                    TextAlignOption.End
                                ),
                            ),
                            Icon(
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

    override fun getScreen(request: SdUiRequest): Template? = DefaultTemplate(
        "Home",
        "ContaCorrente",
        "1",
        "",
        content = listOf(
            Dialog(
                visibilityProperty = VisibilityProperty(false, "123abc")
            ),
            BottomSheet(
                visibilityProperty = VisibilityProperty(false, "123abc1"),
                content = listOf(
                    Button(
                        content = listOf(
                            Text(textProperty = TextProperty("Balance"))
                        ),
                        onClick = toBooleanAction(
                            idToChange = "123abc",
                            newValue = true
                        )
                    ),
                    Text(
                        textProperty = TextProperty("R$ 100,00"),
                    ),
                    Text(
                        textProperty = TextProperty("updated 10 min ago"),
                    )
                )
            ),
            LazyColumn(
                weightProperty = WeightProperty(1f),
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                horizontalAlignmentProperty = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
                content = listOf(
                    Column(
                        paddingVerticalProperty = PaddingVerticalProperty(10),
                        heightProperty = HeightProperty(110),
                        paddingHorizontalProperty = PaddingHorizontalProperty(10),
                        content = listOf(
                            Card(
                                heightProperty = HeightProperty(110),
                                content = listOf(
                                    Column(
                                        horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                            HorizontalAlignmentOption.Center
                                        ),
                                        verticalArrangementProperty = VerticalArrangementProperty(
                                            VerticalArrangementOption.Center
                                        ),
                                        verticalFillTypeProperty = VerticalFillTypeProperty(
                                            VerticalFillTypeOption.Max
                                        ),
                                        content = listOf(
                                            SdUi(
                                                flowIdentifierProperty = FlowIdentifierProperty("Home"),
                                                stageIdentifierProperty = StageIdentifierProperty("Balance"),
                                                fromScreenIdentifierProperty = FromScreenIdentifierProperty(
                                                    "ContaCorrente"
                                                ),
                                                requestUpdateProperty = RequestUpdateProperty(
                                                    false,
                                                    "requestUpdate1"
                                                ),
                                                horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                                    HorizontalFillTypeOption.Max
                                                ),
                                                components = routingController.getSdUiComponents(
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
                            Spacer(sizeProperty = SizeProperty(10))
                        )
                    ),
                    Row(
                        paddingHorizontalProperty = PaddingHorizontalProperty(10),
                        paddingVerticalProperty = PaddingVerticalProperty(10),
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(
                            HorizontalFillTypeOption.Max
                        ),
                        horizontalArrangementProperty = HorizontalArrangementProperty(
                            HorizontalArrangementOption.SpaceBetween
                        ),
                        content = listOf(
                            Column(
                                horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                    HorizontalAlignmentOption.Center
                                ),
                                content = listOf(
                                    Card(
                                        content = listOf(
                                            Icon(
                                                drawableNameProperty = DrawableNameProperty("Pix"),
                                                paddingVerticalProperty = PaddingVerticalProperty(20),
                                                paddingHorizontalProperty = PaddingHorizontalProperty(
                                                    25
                                                ),
                                                sizeProperty = SizeProperty(36),
                                            )
                                        ),
                                        onClick = toBooleanAction(
                                            idToChange = "123abc",
                                            newValue = true
                                        )
                                    ),
                                    Text(
                                        textProperty = TextProperty("Pix"),
                                        paddingVerticalProperty = PaddingVerticalProperty(10)

                                    )
                                )
                            ),
                            actionIcon("Transfer", "Payment"),
                            actionIcon("Pay", "ReceiptLong"),
                            Column(
                                horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                    HorizontalAlignmentOption.Center
                                ),
                                content = listOf(
                                    Card(
                                        content = listOf(
                                            Icon(
                                                iconNameProperty = IconNameProperty("MoreVert"),
                                                paddingVerticalProperty = PaddingVerticalProperty(20),
                                                paddingHorizontalProperty = PaddingHorizontalProperty(
                                                    25
                                                ),
                                                sizeProperty = SizeProperty(36),
                                            ),
                                        ),
                                        onClick = toBooleanAction(
                                            idToChange = "123abc1",
                                            newValue = true
                                        )
                                    ),
                                    Text(
                                        textProperty = TextProperty("More"),
                                        paddingVerticalProperty = PaddingVerticalProperty(10),
                                    )
                                )
                            ),
                        )
                    ),
                    Column(
                        paddingHorizontalProperty = PaddingHorizontalProperty(10),
                        horizontalAlignmentProperty = HorizontalAlignmentProperty(
                            HorizontalAlignmentOption.Center
                        ),
                        paddingVerticalProperty = PaddingVerticalProperty(10),
                        verticalArrangementProperty = VerticalArrangementProperty(
                            VerticalArrangementOption.SpacedBy(10)
                        ),
                        weightProperty = WeightProperty(1f),
                        content = listOf(
                            Text(textProperty = TextProperty("Last Transactions")),
                            transactionItem(),
                            transactionItem(),
                            transactionItem(),
                        )
                    ),
                )
            ),
            SnackBar(
                textProperty = TextProperty("SnackBar"),
                visibilityProperty = VisibilityProperty(false, "123123")
            ),
        )
    )
}