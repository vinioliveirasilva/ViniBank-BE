package com.vinibank.backend.sdui.flow.home.screen

import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.BaseAction
import com.vini.designsystemsdui.action.CloseApplicationAction
import com.vini.designsystemsdui.action.NavigateAction
import com.vini.designsystemsdui.action.ToBooleanAction
import com.vini.designsystemsdui.component.BackHandler
import com.vini.designsystemsdui.component.BottomSheet
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Card
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.Dialog
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.IconButton
import com.vini.designsystemsdui.component.Image
import com.vini.designsystemsdui.component.LazyColumn
import com.vini.designsystemsdui.component.Row
import com.vini.designsystemsdui.component.SdUi
import com.vini.designsystemsdui.component.SnackBar
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.property.BackgroundColorProperty
import com.vini.designsystemsdui.property.CardColorsProperty
import com.vini.designsystemsdui.property.ColorProperty
import com.vini.designsystemsdui.property.FlowIdentifierProperty
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.FontWeightProperty
import com.vini.designsystemsdui.property.FromScreenIdentifierProperty
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.LineHeightProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.RequestUpdateProperty
import com.vini.designsystemsdui.property.ShapeProperty
import com.vini.designsystemsdui.property.SizeProperty
import com.vini.designsystemsdui.property.StageIdentifierProperty
import com.vini.designsystemsdui.property.TextAlignProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.TintProperty
import com.vini.designsystemsdui.property.VerticalAlignmentProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.VerticalFillTypeProperty
import com.vini.designsystemsdui.property.VisibilityProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.WidthProperty
import com.vini.designsystemsdui.property.options.CardColorsModel
import com.vini.designsystemsdui.property.options.FontWeightOption
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.IconOption
import com.vini.designsystemsdui.property.options.ShapeOptions
import com.vini.designsystemsdui.property.options.TextAlignOption
import com.vini.designsystemsdui.property.options.VerticalAlignmentOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.property.options.VerticalFillTypeOption
import com.vini.designsystemsdui.property.options.color.BackgroundOption
import com.vini.designsystemsdui.property.options.color.ColorOption
import com.vini.designsystemsdui.property.util.PropertyIdWrapper
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.db.NotificationsDatabase
import com.vinibank.backend.db.SessionDatabase
import com.vinibank.backend.db.UserLoginDb
import com.vinibank.backend.sdui.flow.RoutingController
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class CheckingAccountScreen(
    @Lazy private val routingController: RoutingController,
    @Lazy private val notificationsDatabase: NotificationsDatabase,
    @Lazy private val userLoginDb: UserLoginDb,
) : HomeScreen {

    companion object {
        val checkingAccountTopSdUiRequestUpdate = PropertyIdWrapper<Boolean>("requestUpdate1")
    }

    private fun notificationIcon(request: SdUiRequest) = listOfNotNull(
        Icon(
            tintProperty = TintProperty(ColorOption.White()),
            iconNameProperty = IconNameProperty(IconOption.Notification)
        ),
        Column(
            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                HorizontalFillTypeOption.Max
            ),
            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                HorizontalAlignmentOption.End
            ),
            paddingHorizontalProperty = PaddingHorizontalProperty(10),
            content = listOf(
                Column(
                    heightProperty = HeightProperty(8),
                    widthProperty = WidthProperty(8),
                    shapeProperty = ShapeProperty(ShapeOptions.Circle),
                    backgroundColorProperty = BackgroundColorProperty(
                        ColorOption.Red()
                    )
                ),
                Spacer(
                    sizeProperty = SizeProperty(10)
                )
            )
        ).takeIf {
            notificationsDatabase.hasUnreadNotification(userLoginDb.getUserEmail(request.sessionId))
        }
    )

    override val screenId: String
        get() = "ContaCorrente"

    override fun getScreen(request: SdUiRequest): Template? {

        val textColor = ColorOption.White()

        val showDialogId = PropertyIdWrapper<Boolean>("123abc")
        val showBottomSheetId = PropertyIdWrapper<Boolean>("123abc1")
        val showSnackBarId = PropertyIdWrapper<Boolean>("123123")

        fun transactionItem(
            title: String,
            subtitle: String,
            icon: IconOption,
            color: ColorOption,
            value: String,
        ) = Card(
            colors = CardColorsProperty(
                CardColorsModel(
                    containerColor = ColorOption.CustomColor(0x881E293B),
                    contentColor = ColorOption.White(),
                )
            ),
            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
            content = listOf(
                Row(
                    verticalAlignmentProperty = VerticalAlignmentProperty(VerticalAlignmentOption.Center),
                    paddingVerticalProperty = PaddingVerticalProperty(10),
                    paddingHorizontalProperty = PaddingHorizontalProperty(10),
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    horizontalArrangementProperty = HorizontalArrangementProperty(
                        HorizontalArrangementOption.SpaceBetween
                    ),
                    content = listOf(
                        Row(
                            verticalAlignmentProperty = VerticalAlignmentProperty(
                                VerticalAlignmentOption.Center
                            ),
                            content = listOf(
                                Card(
                                    colors = CardColorsProperty(
                                        CardColorsModel(
                                            containerColor = ColorOption.CustomColor(0x1A2B8CEE),
                                            contentColor = ColorOption.CustomColor(0xff2B8CEE),
                                        )
                                    ),
                                    widthProperty = WidthProperty(40),
                                    heightProperty = HeightProperty(40),
                                    content = listOf(
                                        Icon(
                                            iconNameProperty = IconNameProperty(icon),
                                            paddingHorizontalProperty = PaddingHorizontalProperty(10),
                                            paddingVerticalProperty = PaddingVerticalProperty(10),
                                        ),
                                    )
                                ),
                                Spacer(sizeProperty = SizeProperty(12)),
                                Column(
                                    content = listOf(
                                        Text(
                                            fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                                            textProperty = TextProperty(title),
                                            textAlignProperty = TextAlignProperty(
                                                TextAlignOption.Center
                                            ),
                                        ),
                                        Text(
                                            fontSizeProperty = FontSizeProperty(11f),
                                            colorProperty = ColorProperty(ColorOption.LightGray()),
                                            textProperty = TextProperty(subtitle),
                                            textAlignProperty = TextAlignProperty(
                                                TextAlignOption.Center
                                            ),
                                        )
                                    )
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
                                    fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                                    colorProperty = ColorProperty(color),
                                    textProperty = TextProperty(value),
                                    textAlignProperty = TextAlignProperty(
                                        TextAlignOption.End
                                    ),
                                ),
                                Spacer(
                                    sizeProperty = SizeProperty(8)
                                )
                            )
                        ),
                    )
                )
            )
        )

        fun actionIcon(
            name: String, icon: IconOption,
            onClickAction: BaseAction = ToBooleanAction(
                idToChange = showSnackBarId,
                newValue = true
            ),
        ) = Column(
            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                HorizontalAlignmentOption.Center
            ),
            content = listOf(
                Card(
                    colors = CardColorsProperty(
                        CardColorsModel(
                            containerColor = ColorOption.CustomColor(0x1A2B8CEE),
                            contentColor = ColorOption.CustomColor(0xff2B8CEE),
                        )
                    ),
                    heightProperty = HeightProperty(56),
                    widthProperty = WidthProperty(56),
                    shapeProperty = ShapeProperty(ShapeOptions.Large),
                    content = listOf(
                        Row(
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                HorizontalFillTypeOption.Max
                            ),
                            horizontalArrangementProperty = HorizontalArrangementProperty(
                                HorizontalArrangementOption.Center
                            ),
                            content = listOf(
                                Column(
                                    verticalArrangementProperty = VerticalArrangementProperty(
                                        VerticalArrangementOption.Center
                                    ),
                                    verticalFillTypeProperty = VerticalFillTypeProperty(
                                        VerticalFillTypeOption.Max
                                    ),
                                    content = listOf(
                                        Icon(
                                            iconNameProperty = IconNameProperty(icon),
                                            sizeProperty = SizeProperty(20),
                                        )
                                    )
                                )
                            )
                        ),
                    ),
                    onClick = onClickAction
                ),
                Text(
                    colorProperty = ColorProperty(textColor),
                    textProperty = TextProperty(name),
                    fontSizeProperty = FontSizeProperty(11f),
                    paddingVerticalProperty = PaddingVerticalProperty(10)
                ),
            ),
        )

        fun PerfilDoUsuario() = Row(
            paddingHorizontalProperty = PaddingHorizontalProperty(20),
            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
            horizontalArrangementProperty = HorizontalArrangementProperty(
                HorizontalArrangementOption.SpaceBetween
            ),
            content = listOf(
                Row(
                    verticalAlignmentProperty = VerticalAlignmentProperty(VerticalAlignmentOption.Center),
                    content = listOf(
                        Image(
                            iconNameProperty = IconNameProperty(IconOption.User),
                            sizeProperty = SizeProperty(48),
                            tintProperty = TintProperty(ColorOption.White())
                        ),
                        Spacer(widthProperty = WidthProperty(5)),
                        Column(
                            verticalArrangementProperty = VerticalArrangementProperty(
                                VerticalArrangementOption.Center
                            ),
                            content = listOf(
                                Text(
                                    fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                                    textProperty = TextProperty("Nome do cliente"),
                                    colorProperty = ColorProperty(textColor),
                                ),
                                Text(
                                    fontSizeProperty = FontSizeProperty(11f),
                                    colorProperty = ColorProperty(textColor),
                                    textProperty = TextProperty("Nivel do cliente"),
                                    lineHeightProperty = LineHeightProperty(12)
                                ),
                            )
                        )
                    )
                ),
                Column(
                    backgroundColorProperty = BackgroundColorProperty(
                        BackgroundOption.from(
                            ColorOption.CustomColor(
                                hex = 0xff1E293B
                            )
                        )
                    ),
                    shapeProperty = ShapeProperty(ShapeOptions.Circle),
                    content = listOf(
                        IconButton(
                            content = notificationIcon(request),
                            onClick = NavigateAction(
                                flow = "Notification"
                            )
                        )
                    )
                ),
            )
        )

        fun SaldoDaConta() = SdUi(
            flowIdentifierProperty = FlowIdentifierProperty("Home"),
            stageIdentifierProperty = StageIdentifierProperty("Balance"),
            fromScreenIdentifierProperty = FromScreenIdentifierProperty(
                screenId
            ),
            requestUpdateProperty = RequestUpdateProperty(
                idWrapper = checkingAccountTopSdUiRequestUpdate
            ),
            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                HorizontalFillTypeOption.Max
            ),
            template = routingController.getTemplate(request.copy(toScreen = "Balance"))
        )

        fun Servicos() = listOf(
            Row(
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                horizontalArrangementProperty = HorizontalArrangementProperty(
                    HorizontalArrangementOption.SpaceBetween
                ),
                content = listOf(
                    Text(
                        colorProperty = ColorProperty(ColorOption.LightGray()),
                        textProperty = TextProperty("SERVIÇOS"),
                        fontSizeProperty = FontSizeProperty(14f),
                        fontWeightProperty = FontWeightProperty(FontWeightOption.SemiBold)
                    ),
                    Text(
                        colorProperty = ColorProperty(ColorOption.LightBlue()),
                        textProperty = TextProperty("Mostrar todos"),
                        fontSizeProperty = FontSizeProperty(12f),
                        fontWeightProperty = FontWeightProperty(FontWeightOption.SemiBold)
                    ),
                )
            ),
            Spacer(sizeProperty = SizeProperty(16)),
            Row(
                horizontalArrangementProperty = HorizontalArrangementProperty(
                    HorizontalArrangementOption.SpaceBetween
                ),
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                content = listOf(
                    actionIcon(
                        "Cards",
                        IconOption.Money,
                        ToBooleanAction(
                            idToChange = showDialogId,
                            newValue = true
                        )
                    ),
                    actionIcon("Savings", IconOption.Wallet),
                    actionIcon("Loans", IconOption.Money),
                    actionIcon(
                        "Insurance",
                        IconOption.Shield,
                        ToBooleanAction(
                            idToChange = showBottomSheetId,
                            newValue = true
                        )
                    )
                )
            ),
        )

        fun LastTransactions() = listOf(
            Row(
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                horizontalArrangementProperty = HorizontalArrangementProperty(
                    HorizontalArrangementOption.SpaceBetween
                ),
                content = listOf(
                    Text(
                        colorProperty = ColorProperty(ColorOption.LightGray()),
                        textProperty = TextProperty("RECENT TRANSACTIONS"),
                        fontSizeProperty = FontSizeProperty(14f),
                        fontWeightProperty = FontWeightProperty(FontWeightOption.SemiBold)
                    ),
                )
            ),
            Column(
                verticalArrangementProperty = VerticalArrangementProperty(
                    VerticalArrangementOption.SpacedBy(
                        16
                    )
                ),
                content = listOf(
                    transactionItem(
                        "Starbucks Coffee",
                        "May 24, 2024 • 08:32 AM",
                        icon = IconOption.Check,
                        color = ColorOption.CustomColor(0xffEF4444),
                        value = "-R$ 10,00"
                    ),
                    transactionItem(
                        "Salary Deposit",
                        "May 23, 2024 • 09:00 AM",
                        icon = IconOption.Money,
                        color = ColorOption.CustomColor(0xff10B981),
                        value = "+R$ 3200,00"
                    )
                )
            )
        )


        return DefaultTemplate(
            flow = request.flow,
            stage = request.toScreen,
            version = "1",
            content = listOf(
                BackHandler(onBackAction = CloseApplicationAction()),
                Column(
                    horizontalAlignmentProperty = HorizontalAlignmentProperty(
                        HorizontalAlignmentOption.Center
                    ),
                    backgroundColorProperty = BackgroundColorProperty(
                        ColorOption.CustomColor(
                            0xff101922
                        )
                    ),
                    weightProperty = WeightProperty(1f),
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                        HorizontalFillTypeOption.Max
                    ),
                    content = listOf(
                        PerfilDoUsuario(),
                        Column(
                            shapeProperty = ShapeProperty(ShapeOptions.CustomTop(40)),
                            weightProperty = WeightProperty(1f),
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                HorizontalFillTypeOption.Max
                            ),
                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                HorizontalAlignmentOption.Center
                            ),
                            content = listOf(
                                Dialog(
                                    visibilityProperty = VisibilityProperty(
                                        false,
                                        showDialogId
                                    )
                                ),
                                BottomSheet(
                                    visibilityProperty = VisibilityProperty(
                                        false,
                                        showBottomSheetId
                                    ),
                                    content = listOf(
                                        Button(
                                            content = listOf(
                                                Text(textProperty = TextProperty("Balance"))
                                            ),
                                            onClick = ToBooleanAction(
                                                idToChange = showDialogId,
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
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                        HorizontalAlignmentOption.Center
                                    ),
                                    content = listOf(
                                        Column(
                                            paddingHorizontalProperty = PaddingHorizontalProperty(
                                                24
                                            ),
                                            content = listOf(
                                                Spacer(sizeProperty = SizeProperty(20)),
                                                SaldoDaConta(),
                                                Spacer(sizeProperty = SizeProperty(10)),
                                            )
                                        ),
                                        Column(
                                            paddingHorizontalProperty = PaddingHorizontalProperty(
                                                24
                                            ),
                                            paddingVerticalProperty = PaddingVerticalProperty(10),
                                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                                HorizontalFillTypeOption.Max
                                            ),
                                            content = Servicos()
                                        ),
                                        Column(
                                            paddingHorizontalProperty = PaddingHorizontalProperty(
                                                24
                                            ),
                                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                                HorizontalAlignmentOption.Center
                                            ),
                                            verticalArrangementProperty = VerticalArrangementProperty(
                                                VerticalArrangementOption.SpacedBy(16)
                                            ),
                                            weightProperty = WeightProperty(1f),
                                            content = LastTransactions()
                                        ),
                                    )
                                ),
                                SnackBar(
                                    textProperty = TextProperty("SnackBar"),
                                    visibilityProperty = VisibilityProperty(
                                        false,
                                        showSnackBarId
                                    )
                                ),
                            )
                        ),
                    )
                ),
            )
        )
    }
}