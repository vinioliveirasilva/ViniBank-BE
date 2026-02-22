package com.vinibank.backend.sdui.flow.notification.screen

import com.vini.designsystemsdui.CacheStrategy
import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.BackAction
import com.vini.designsystemsdui.component.Box
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Card
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.IconButton
import com.vini.designsystemsdui.component.LazyColumn
import com.vini.designsystemsdui.component.OutlinedButton
import com.vini.designsystemsdui.component.Row
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.component.TopAppBar
import com.vini.designsystemsdui.property.BackgroundColorProperty
import com.vini.designsystemsdui.property.ButtonColorsProperty
import com.vini.designsystemsdui.property.CardColorsProperty
import com.vini.designsystemsdui.property.ColorProperty
import com.vini.designsystemsdui.property.ContentAlignmentProperty
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.FontWeightProperty
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.LineHeightProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.ShapeProperty
import com.vini.designsystemsdui.property.SizeProperty
import com.vini.designsystemsdui.property.TextAlignProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.TintProperty
import com.vini.designsystemsdui.property.TopAppBarColorProperty
import com.vini.designsystemsdui.property.VerticalAlignmentProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.WidthProperty
import com.vini.designsystemsdui.property.options.AlignmentOptions
import com.vini.designsystemsdui.property.options.ButtonColorsModel
import com.vini.designsystemsdui.property.options.CardColorsModel
import com.vini.designsystemsdui.property.options.FontWeightOption
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.IconOption
import com.vini.designsystemsdui.property.options.ShapeOptions
import com.vini.designsystemsdui.property.options.TextAlignOption
import com.vini.designsystemsdui.property.options.TopAppBarColorsModel
import com.vini.designsystemsdui.property.options.VerticalAlignmentOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.property.options.color.ColorOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.db.NotificationCategory
import com.vinibank.backend.db.NotificationDetail
import com.vinibank.backend.db.NotificationDetailsDatabase
import com.vinibank.backend.db.NotificationsDatabase
import com.vinibank.backend.db.UserLoginDb
import com.vinibank.backend.sdui.flow.getUndefinedScreen
import com.vinibank.backend.sdui.flow.notification.NotificationScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component

@Component
class NotificationDetailScreen(
    private val notificationDetailDatabase: NotificationDetailsDatabase,
    private val notificationsDatabase: NotificationsDatabase,
    private val userLoginDb: UserLoginDb,
) : NotificationScreen {
    override val screenId: String = "Detail"
    override fun getScreen(request: SdUiRequest, parameters: Map<String, String>, screenId: String): Template? {
        val notificationId = parameters["notificationId"]
        val notification = notificationDetailDatabase.get(
            userLoginDb.getUserEmail(request.sessionId),
            notificationId
        )

        return if (notification == null) {
            getErrorScreen(request)
        } else {
            notificationsDatabase.markAsRead(
                userLoginDb.getUserEmail(request.sessionId),
                notification.notificationId
            )

            when(notification.categoryLabel) {
                NotificationCategory.ALL -> getNotificationTemplate(request, notification, screenId)
                NotificationCategory.TRANSACTIONS -> getNotificationTemplate(request, notification, screenId)
                NotificationCategory.SECURITY -> getNotificationTemplate(request, notification, screenId)
                NotificationCategory.OFFER -> getNotificationTemplate(request, notification, screenId)
                NotificationCategory.LOGIN -> getLoginNotificationTemplate(notification, screenId)
                NotificationCategory.STATEMENT -> getNotificationTemplate(request, notification, screenId)
            }
        }
    }

    fun getNotificationIcon(type: NotificationCategory) = when (type) {
        NotificationCategory.TRANSACTIONS -> IconOption.WalletOutlined
        NotificationCategory.SECURITY -> IconOption.ShieldOutlined
        NotificationCategory.OFFER -> IconOption.Campaign
        NotificationCategory.LOGIN -> IconOption.Devices
        NotificationCategory.STATEMENT -> IconOption.NotificationsNone
        else -> IconOption.Notification
    }

    fun getNotificationTint(type: NotificationCategory) = when (type) {
        NotificationCategory.TRANSACTIONS -> ColorOption.CustomColor(0xff2B8CEE)
        NotificationCategory.SECURITY -> ColorOption.CustomColor(0xffF59E0B)
        NotificationCategory.OFFER -> ColorOption.CustomColor(0xff2B8CEE)
        NotificationCategory.LOGIN -> ColorOption.CustomColor(0xff92ADC9)
        NotificationCategory.STATEMENT -> ColorOption.CustomColor(0xff92ADC9)
        else -> ColorOption.CustomColor(0xff2B8CEE)
    }

    fun getNotificationBackground(type: NotificationCategory) = when (type) {
        NotificationCategory.TRANSACTIONS -> ColorOption.CustomColor(0x302B8CEE)
        NotificationCategory.SECURITY -> ColorOption.CustomColor(0x30F59E0B)
        NotificationCategory.OFFER -> ColorOption.CustomColor(0x302B8CEE)
        NotificationCategory.LOGIN -> ColorOption.CustomColor(0x3092ADC9)
        NotificationCategory.STATEMENT -> ColorOption.CustomColor(0x3092ADC9)
        else -> ColorOption.CustomColor(0x302B8CEE)
    }

    private fun getNotificationTemplate(
        request: SdUiRequest,
        notification: NotificationDetail,
        screenId: String
    ): Template {
        val background = ColorOption.CustomColor(0xff101922)


        val separator = Column(
            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                HorizontalFillTypeOption.Max
            ),
            heightProperty = HeightProperty(1),
            backgroundColorProperty = BackgroundColorProperty(ColorOption.CustomColor(0xff233648))
        )

        val topBar = TopAppBar(
            topAppBarColorProperty = TopAppBarColorProperty(
                value = TopAppBarColorsModel(
                    containerColor = background,
                    titleContentColor = ColorOption.White(),
                    navigationIconContentColor = ColorOption.White(),
                )
            ),
            title = listOf(
                Text(
                    textProperty = TextProperty("Notification"),
                    fontSizeProperty = FontSizeProperty(18f),
                    lineHeightProperty = LineHeightProperty(28),
                    textAlignProperty = TextAlignProperty(TextAlignOption.Center),
                    fontWeightProperty = FontWeightProperty(FontWeightOption.Bold)
                )
            ),
            navigationIcon = listOf(
                IconButton(
                    onClick = BackAction(),
                    content = listOf(
                        Icon(
                            iconNameProperty = IconNameProperty(IconOption.LeftArrow)
                        )
                    )
                )
            ),
        )

        val centerIcon = Column(
            shapeProperty = ShapeProperty(ShapeOptions.Circle),
            heightProperty = HeightProperty(128),
            widthProperty = WidthProperty(128),
            backgroundColorProperty = BackgroundColorProperty(getNotificationBackground(notification.categoryLabel)),
            content = listOf(
                Icon(
                    paddingHorizontalProperty = PaddingHorizontalProperty(32),
                    paddingVerticalProperty = PaddingVerticalProperty(32),
                    sizeProperty = SizeProperty(64),
                    iconNameProperty = IconNameProperty(getNotificationIcon(notification.categoryLabel)),
                    tintProperty = TintProperty(getNotificationTint(notification.categoryLabel)),
                )
            )
        )

        val body = Column(
            paddingHorizontalProperty = PaddingHorizontalProperty(24),
            horizontalAlignmentProperty = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
            content = listOf(
                Card(
                    shapeProperty = ShapeProperty(ShapeOptions.Circle),
                    cardColorsProperty = CardColorsProperty(
                        value = CardColorsModel(
                            containerColor = ColorOption.CustomColor(0xff1E293B),
                            contentColor = ColorOption.LightBlue(),
                        )
                    ),
                    content = listOf(
                        Text(
                            paddingHorizontalProperty = PaddingHorizontalProperty(8),
                            paddingVerticalProperty = PaddingVerticalProperty(4),
                            textProperty = TextProperty(notification.categoryLabel.name),
                            fontSizeProperty = FontSizeProperty(12f),
                            fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                        )
                    )
                ),
                Spacer(heightProperty = HeightProperty(8)),
                Text(
                    textProperty = TextProperty(notification.title),
                    fontSizeProperty = FontSizeProperty(30f),
                    fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                    colorProperty = ColorProperty(ColorOption.White())
                ),
                Spacer(sizeProperty = SizeProperty(8)),
                Text(
                    textProperty = TextProperty(notification.highlightedText.orEmpty()),
                    fontSizeProperty = FontSizeProperty(20f),
                    fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                    colorProperty = ColorProperty(ColorOption.LightBlue())
                ),
                Spacer(sizeProperty = SizeProperty(8)),
                Text(
                    textProperty = TextProperty(notification.dateTimeText.orEmpty()),
                    fontSizeProperty = FontSizeProperty(14f),
                    fontWeightProperty = FontWeightProperty(FontWeightOption.Normal),
                    colorProperty = ColorProperty(ColorOption.CustomColor(0xBA2B8CEE))
                ),
                Spacer(sizeProperty = SizeProperty(32)),
                Card(
                    cardColorsProperty = CardColorsProperty(
                        value = CardColorsModel(
                            containerColor = ColorOption.CustomColor(0xff1E293B),
                            contentColor = ColorOption.LightBlue(),
                        )
                    ),
                    content = listOf(
                        Text(
                            paddingHorizontalProperty = PaddingHorizontalProperty(24),
                            paddingVerticalProperty = PaddingVerticalProperty(24),
                            textProperty = TextProperty(notification.detailMessage.orEmpty()),
                            fontSizeProperty = FontSizeProperty(16f),
                            fontWeightProperty = FontWeightProperty(FontWeightOption.Normal),
                            colorProperty = ColorProperty(ColorOption.LightBlue())
                        )
                    )
                )
            )
        )

        val buttons = Column(
            horizontalAlignmentProperty = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
            content = listOf(
                Button(
                    onClick = BackAction(),
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    paddingHorizontalProperty = PaddingHorizontalProperty(24),
                    shapeProperty = ShapeProperty(ShapeOptions.Medium),
                    buttonColorsProperty = ButtonColorsProperty(
                        value = ButtonColorsModel(
                            containerColor = ColorOption.CustomColor(0xff2B8CEE),
                            contentColor = ColorOption.White()
                        )
                    ),
                    content = listOf(
                        Text(
                            paddingVerticalProperty = PaddingVerticalProperty(8),
                            textProperty = TextProperty("View Transaction Details"),
                            fontSizeProperty = FontSizeProperty(16f),
                            fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                        )
                    )
                ),
                Button(
                    onClick = BackAction(),
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    paddingHorizontalProperty = PaddingHorizontalProperty(24),
                    shapeProperty = ShapeProperty(ShapeOptions.Medium),
                    buttonColorsProperty = ButtonColorsProperty(
                        value = ButtonColorsModel(
                            containerColor = background,
                            contentColor = ColorOption.CustomColor(0xA02B8CEE)
                        )
                    ),
                    content = listOf(
                        Text(
                            paddingVerticalProperty = PaddingVerticalProperty(8),
                            textProperty = TextProperty("Dismiss Notification"),
                            fontSizeProperty = FontSizeProperty(16f),
                            fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                        )
                    )
                ),
                Spacer(sizeProperty = SizeProperty(32))
            ),
        )

        val screen = DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            cacheStrategy = CacheStrategy.TimeCache(System.currentTimeMillis().plus(300000)),
            content = listOf(
                Column(
                    weightProperty = WeightProperty(1f),
                    backgroundColorProperty = BackgroundColorProperty(background),
                    verticalArrangementProperty = VerticalArrangementProperty(
                        VerticalArrangementOption.SpaceBetween
                    ),
                    horizontalAlignmentProperty = HorizontalAlignmentProperty(
                        HorizontalAlignmentOption.Center
                    ),
                    content = listOf(
                        Column(
                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                HorizontalAlignmentOption.Center
                            ),
                            content = listOf(
                                topBar,
                                separator,
                                Spacer(sizeProperty = SizeProperty(48)),
                                centerIcon,
                                Spacer(sizeProperty = SizeProperty(32)),
                                body,
                            )
                        ),
                        buttons
                    )
                )
            )
        )

        return screen
    }


    private fun getLoginNotificationTemplate(notification: NotificationDetail, screenId: String): Template {
            val background = ColorOption.CustomColor(0xff081523)
            val cardBackground = ColorOption.CustomColor(0xff0D1D30)
            val separatorColor = ColorOption.CustomColor(0xff1C2F45)

            val topBar = TopAppBar(
                topAppBarColorProperty = TopAppBarColorProperty(
                    value = TopAppBarColorsModel(
                        containerColor = background,
                        titleContentColor = ColorOption.White(),
                        navigationIconContentColor = ColorOption.White(),
                    )
                ),
                title = listOf(
                    Text(
                        textProperty = TextProperty(notification.screenTitle),
                        fontSizeProperty = FontSizeProperty(18f),
                        lineHeightProperty = LineHeightProperty(28),
                        textAlignProperty = TextAlignProperty(TextAlignOption.Center),
                        fontWeightProperty = FontWeightProperty(FontWeightOption.Bold)
                    )
                ),
                navigationIcon = listOf(
                    IconButton(
                        onClick = BackAction(),
                        content = listOf(
                            Icon(iconNameProperty = IconNameProperty(IconOption.LeftArrow))
                        )
                    )
                ),
            )

            fun detailRow(
                label: String,
                value: String,
                showSeparator: Boolean = true,
            ) = listOfNotNull(
                Row(
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    horizontalArrangementProperty = HorizontalArrangementProperty(
                        HorizontalArrangementOption.SpaceBetween
                    ),
                    verticalAlignmentProperty = VerticalAlignmentProperty(VerticalAlignmentOption.Center),
                    content = listOf(
                        Text(
                            paddingVerticalProperty = PaddingVerticalProperty(16),
                            textProperty = TextProperty(label),
                            colorProperty = ColorProperty(ColorOption.CustomColor(0xff8EA2BD)),
                            fontSizeProperty = FontSizeProperty(14f),
                            lineHeightProperty = LineHeightProperty(20),
                        ),
                        Text(
                            textProperty = TextProperty(value),
                            colorProperty = ColorProperty(ColorOption.White()),
                            fontWeightProperty = FontWeightProperty(FontWeightOption.SemiBold),
                            fontSizeProperty = FontSizeProperty(14f),
                            lineHeightProperty = LineHeightProperty(20),
                        )
                    )
                ),
                if (showSeparator) {
                    Column(
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(
                            HorizontalFillTypeOption.Max
                        ),
                        heightProperty = HeightProperty(1),
                        backgroundColorProperty = BackgroundColorProperty(separatorColor)
                    )
                } else null
            )

            val screen = DefaultTemplate(
                flow = "Notification",
                stage = screenId,
                version = "1",
                content = listOf(
                    Column(
                        backgroundColorProperty = BackgroundColorProperty(background),
                        weightProperty = WeightProperty(1f),
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                        content = listOf(
                            topBar,
                            LazyColumn(
                                horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                    HorizontalAlignmentOption.Center
                                ),
                                paddingHorizontalProperty = PaddingHorizontalProperty(24),
                                horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                    HorizontalFillTypeOption.Max
                                ),
                                content = listOf(
                                    Spacer(sizeProperty = SizeProperty(24)),
                                    Box(
                                        contentAlignmentProperty = ContentAlignmentProperty(AlignmentOptions.TopEnd),
                                        content = listOf(
                                            Card(
                                                shapeProperty = ShapeProperty(ShapeOptions.Circle),
                                                cardColorsProperty = CardColorsProperty(
                                                    value = CardColorsModel(
                                                        containerColor = ColorOption.CustomColor(0xff153354),
                                                        contentColor = ColorOption.CustomColor(0xff2B8CEE),
                                                    )
                                                ),
                                                content = listOf(
                                                    Icon(
                                                        iconNameProperty = IconNameProperty(IconOption.Devices),
                                                        tintProperty = TintProperty(
                                                            ColorOption.CustomColor(
                                                                0xff2B8CEE
                                                            )
                                                        ),
                                                        sizeProperty = SizeProperty(64),
                                                        paddingVerticalProperty = PaddingVerticalProperty(32),
                                                        paddingHorizontalProperty = PaddingHorizontalProperty(
                                                            32
                                                        ),
                                                    )
                                                )
                                            ),
                                            Card(
                                                shapeProperty = ShapeProperty(ShapeOptions.Circle),
                                                cardColorsProperty = CardColorsProperty(
                                                    value = CardColorsModel(
                                                        containerColor = ColorOption.CustomColor(0xffEF4444),
                                                        contentColor = ColorOption.White(),
                                                    )
                                                ),
                                                heightProperty = HeightProperty(44),
                                                widthProperty = WidthProperty(44),
                                                content = listOf(
                                                    Icon(
                                                        iconNameProperty = IconNameProperty(IconOption.WarningAmber),
                                                        tintProperty = TintProperty(ColorOption.White()),
                                                        sizeProperty = SizeProperty(20),
                                                        paddingVerticalProperty = PaddingVerticalProperty(12),
                                                        paddingHorizontalProperty = PaddingHorizontalProperty(
                                                            12
                                                        ),
                                                    )
                                                )
                                            ),
                                        )
                                    ),
                                    Spacer(sizeProperty = SizeProperty(28)),
                                    Text(
                                        textProperty = TextProperty(notification.title),
                                        fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                                        fontSizeProperty = FontSizeProperty(30f),
                                        lineHeightProperty = LineHeightProperty(38),
                                        textAlignProperty = TextAlignProperty(TextAlignOption.Center),
                                        colorProperty = ColorProperty(ColorOption.White()),
                                        horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                            HorizontalFillTypeOption.Max
                                        )
                                    ),
                                    Spacer(sizeProperty = SizeProperty(10)),
                                    Text(
                                        textProperty = TextProperty(
                                            notification.subtitle
                                        ),
                                        colorProperty = ColorProperty(ColorOption.CustomColor(0xff8EA2BD)),
                                        textAlignProperty = TextAlignProperty(TextAlignOption.Center),
                                        fontSizeProperty = FontSizeProperty(18f),
                                        lineHeightProperty = LineHeightProperty(26),
                                        horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                            HorizontalFillTypeOption.Max
                                        )
                                    ),
                                    Spacer(sizeProperty = SizeProperty(24)),
                                    Card(
                                        shapeProperty = ShapeProperty(ShapeOptions.Large),
                                        cardColorsProperty = CardColorsProperty(
                                            value = CardColorsModel(
                                                containerColor = cardBackground,
                                                contentColor = ColorOption.White(),
                                            )
                                        ),
                                        horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                            HorizontalFillTypeOption.Max
                                        ),
                                        content = listOf(
                                            Column(
                                                paddingHorizontalProperty = PaddingHorizontalProperty(16),
                                                paddingVerticalProperty = PaddingVerticalProperty(4),
                                                content = detailRow("Device", "iPhone 15 Pro")
                                                    .plus(detailRow("Location", "Brooklyn, NY"))
                                                    .plus(detailRow("Date", "October 24, 2024"))
                                                    .plus(detailRow("Time", "02:15 PM", showSeparator = false))
                                            ),
                                            Column(
                                                horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                                    HorizontalFillTypeOption.Max
                                                ),
                                                heightProperty = HeightProperty(130),
                                                backgroundColorProperty = BackgroundColorProperty(
                                                    ColorOption.CustomColor(0xff1B324A)
                                                ),
                                                horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                                    HorizontalAlignmentOption.Center
                                                ),
                                                verticalArrangementProperty = VerticalArrangementProperty(
                                                    VerticalArrangementOption.Center
                                                ),
                                                content = listOf(
                                                    Icon(
                                                        iconNameProperty = IconNameProperty(IconOption.Notification),
                                                        tintProperty = TintProperty(ColorOption.CustomColor(0xff5FA8FF)),
                                                        sizeProperty = SizeProperty(26),
                                                    ),
                                                    Spacer(sizeProperty = SizeProperty(8)),
                                                    Text(
                                                        textProperty = TextProperty("Brooklyn map preview"),
                                                        colorProperty = ColorProperty(
                                                            ColorOption.CustomColor(0xffA8BFDA)
                                                        ),
                                                        fontSizeProperty = FontSizeProperty(12f),
                                                    )
                                                )
                                            )
                                        )
                                    ),
                                    Spacer(sizeProperty = SizeProperty(40)),
                                    Button(
                                        horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                            HorizontalFillTypeOption.Max
                                        ),
                                        shapeProperty = ShapeProperty(ShapeOptions.Large),
                                        buttonColorsProperty = ButtonColorsProperty(
                                            value = ButtonColorsModel(
                                                containerColor = ColorOption.CustomColor(0xff2B8CEE),
                                                contentColor = ColorOption.White()
                                            )
                                        ),
                                        content = listOf(
                                            Text(
                                                paddingVerticalProperty = PaddingVerticalProperty(8),
                                                textProperty = TextProperty("Yes, it was me"),
                                                fontSizeProperty = FontSizeProperty(18f),
                                                fontWeightProperty = FontWeightProperty(FontWeightOption.Bold)
                                            )
                                        )
                                    ),
                                    Spacer(sizeProperty = SizeProperty(12)),
                                    OutlinedButton(
                                        buttonColorsProperty = ButtonColorsProperty(
                                            value = ButtonColorsModel()
                                        ),
                                        horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                            HorizontalFillTypeOption.Max
                                        ),
                                        shapeProperty = ShapeProperty(ShapeOptions.Large),
                                        content = listOf(
                                            Text(
                                                paddingVerticalProperty = PaddingVerticalProperty(8),
                                                textProperty = TextProperty("No, secure my account"),
                                                fontSizeProperty = FontSizeProperty(16f),
                                                fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                                                colorProperty = ColorProperty(ColorOption.White())
                                            )
                                        )
                                    ),
                                    Spacer(sizeProperty = SizeProperty(24)),
                                    Text(
                                        textProperty = TextProperty(
                                            "If you don't recognize this activity, please secure your account immediately to prevent unauthorized access."
                                        ),
                                        colorProperty = ColorProperty(ColorOption.CustomColor(0xff7E94AF)),
                                        textAlignProperty = TextAlignProperty(TextAlignOption.Center),
                                        fontSizeProperty = FontSizeProperty(16f),
                                        lineHeightProperty = LineHeightProperty(24),
                                        horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                            HorizontalFillTypeOption.Max
                                        )
                                    ),
                                    Spacer(sizeProperty = SizeProperty(24)),
                                )
                            )
                        )
                    )
                )
            )

        return screen

    }

    private fun getErrorScreen(request: SdUiRequest) = getUndefinedScreen(request)
}