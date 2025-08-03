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
import com.vini.designsystemsdui.component.text
import com.vini.designsystemsdui.property.CurrentScreenProperty
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
import com.vini.designsystemsdui.property.ShouldShowProperty
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
        horizontalAlignment = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
        components = listOf(
            card(
                components = listOf(
                    icon(
                        iconName = IconNameProperty(icon),
                        paddingVertical = PaddingVerticalProperty(20),
                        paddingHorizontal = PaddingHorizontalProperty(25),
                        size = SizeProperty(36),
                    )
                ),
                actions = listOf(
                    toBooleanAction(
                        idToChange = "123123",
                        newValue = true
                    )
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
        components = listOf(
            row(
                paddingVertical = PaddingVerticalProperty(10),
                paddingHorizontal = PaddingHorizontalProperty(10),
                horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                horizontalArrangement = HorizontalArrangementProperty(HorizontalArrangementOption.SpaceBetween),
                components = listOf(
                    row(
                        paddingVertical = PaddingVerticalProperty(10),
                        components = listOf(
                            icon(
                                iconName = IconNameProperty("Money"),
                                paddingHorizontal = PaddingHorizontalProperty(10),
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
                        paddingVertical = PaddingVerticalProperty(10),
                        verticalAlignment = VerticalAlignmentProperty(VerticalAlignmentOption.Center),
                        components = listOf(
                            text(
                                textProperty = TextProperty("-R$ 500.00"),
                                textAlignProperty = TextAlignProperty(
                                    TextAlignOption.End
                                ),
                            ),
                            icon(
                                iconName = IconNameProperty("RightArrow"),
                                size = SizeProperty(18),
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
        components = listOf(
            dialog(
                shouldShow = ShouldShowProperty(false, "123abc")
            ),
            bottomSheet(
                shouldShow = ShouldShowProperty(false, "123abc1"),
                components = listOf(
                    button(
                        text = TextProperty("Balance"),
                        actions = listOf(
                            toBooleanAction(
                                idToChange = "123abc",
                                newValue = true
                            )
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
                weight = WeightProperty(1),
                horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                paddingVertical = PaddingVerticalProperty(10),
                paddingHorizontal = PaddingHorizontalProperty(10),
                horizontalAlignment = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
                components = listOf(
                    column(
                        paddingVertical = PaddingVerticalProperty(10),
                        height = HeightProperty(110),
                        components = listOf(
                            card(
                                paddingVerticalProperty = PaddingVerticalProperty(10),
                                heightProperty = HeightProperty(110),
                                components = listOf(
                                    column(
                                        horizontalAlignment = HorizontalAlignmentProperty(
                                            HorizontalAlignmentOption.Center
                                        ),
                                        verticalArrangement = VerticalArrangementProperty(
                                            VerticalArrangementOption.Center
                                        ),
                                        verticalFillType = VerticalFillTypeProperty(
                                            VerticalFillTypeOption.Max
                                        ),
                                        components = listOf(
                                            sdUi(
                                                flow = FlowIdentifierProperty("Home"),
                                                stage = StageIdentifierProperty("Balance"),
                                                currentScreen = CurrentScreenProperty("ContaCorrente"),
                                                requestUpdate = RequestUpdateProperty(
                                                    false,
                                                    "requestUpdate1"
                                                ),
                                                horizontalFillType = HorizontalFillTypeProperty(
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
                            )
                        )
                    ),
                    row(
                        paddingVertical = PaddingVerticalProperty(10),
                        horizontalFillType = HorizontalFillTypeProperty(
                            HorizontalFillTypeOption.Max
                        ),
                        horizontalArrangement = HorizontalArrangementProperty(
                            HorizontalArrangementOption.SpaceBetween
                        ),
                        components = listOf(
                            column(
                                horizontalAlignment = HorizontalAlignmentProperty(
                                    HorizontalAlignmentOption.Center
                                ),
                                components = listOf(
                                    card(
                                        components = listOf(
                                            icon(
                                                drawableName = DrawableNameProperty("Pix"),
                                                paddingVertical = PaddingVerticalProperty(20),
                                                paddingHorizontal = PaddingHorizontalProperty(25),
                                                size = SizeProperty(36),
                                            )
                                        ),
                                        actions = listOf(
                                            toBooleanAction(
                                                idToChange = "123abc",
                                                newValue = true
                                            )
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
                                horizontalAlignment = HorizontalAlignmentProperty(
                                    HorizontalAlignmentOption.Center
                                ),
                                components = listOf(
                                    card(
                                        components = listOf(
                                            icon(
                                                iconName = IconNameProperty("MoreVert"),
                                                paddingVertical = PaddingVerticalProperty(20),
                                                paddingHorizontal = PaddingHorizontalProperty(25),
                                                size = SizeProperty(36),
                                            ),
                                        ),
                                        actions = listOf(
                                            toBooleanAction(
                                                idToChange = "123abc1",
                                                newValue = true
                                            )
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
                        horizontalAlignment = HorizontalAlignmentProperty(
                            HorizontalAlignmentOption.Center
                        ),
                        paddingVertical = PaddingVerticalProperty(10),
                        verticalArrangement = VerticalArrangementProperty(
                            VerticalArrangementOption.SpacedBy(10)
                        ),
                        weight = WeightProperty(1),
                        components = listOf(
                            text(TextProperty("Last Transactions")),
                            transactionItem(),
                            transactionItem(),
                            transactionItem(),
                        )
                    ),
                )
            ),
            snackBar(
                text = TextProperty("SnackBar"),
                shouldShow = ShouldShowProperty(false, "123123")
            ),
        )
    )
}