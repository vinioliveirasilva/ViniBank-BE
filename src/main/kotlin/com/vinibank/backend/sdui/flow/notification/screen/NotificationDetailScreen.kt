package com.vinibank.backend.sdui.flow.notification.screen

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
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.background
import com.vini.designsystemsdui.modifier.clip
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.height
import com.vini.designsystemsdui.modifier.option.ShapeOption
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.size
import com.vini.designsystemsdui.property.ButtonColorsProperty
import com.vini.designsystemsdui.property.CardColorsProperty
import com.vini.designsystemsdui.property.ColorProperty
import com.vini.designsystemsdui.property.ContentAlignmentProperty
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.FontWeightProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.LineHeightProperty
import com.vini.designsystemsdui.property.ShapeProperty
import com.vini.designsystemsdui.property.TextAlignProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.TintProperty
import com.vini.designsystemsdui.property.TopAppBarColorProperty
import com.vini.designsystemsdui.property.VerticalAlignmentProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.AlignmentOptions
import com.vini.designsystemsdui.property.options.ButtonColorsModel
import com.vini.designsystemsdui.property.options.CardColorsModel
import com.vini.designsystemsdui.property.options.FontWeightOption
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
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
    override fun getScreen(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? {
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

            when (notification.categoryLabel) {
                NotificationCategory.ALL -> getNotificationTemplate(request, notification, screenId)
                NotificationCategory.TRANSACTIONS -> getNotificationTemplate(
                    request,
                    notification,
                    screenId
                )

                NotificationCategory.SECURITY -> getNotificationTemplate(
                    request,
                    notification,
                    screenId
                )

                NotificationCategory.OFFER -> getNotificationTemplate(
                    request,
                    notification,
                    screenId
                )

                NotificationCategory.LOGIN -> getLoginNotificationTemplate(notification, screenId)
                NotificationCategory.STATEMENT -> getNotificationTemplate(
                    request,
                    notification,
                    screenId
                )
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
        screenId: String,
    ): Template {
        val background = ColorOption.DeepBlue()

        val separator = Column(
            modifier = SdUiModifier().fillMaxWidth().height(1)
                .background(ColorOption.CustomColor(0xff233648))
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
            modifier = SdUiModifier().size(128).clip(ShapeOption.Circle())
                .background(getNotificationBackground(notification.categoryLabel)),
            content = listOf(
                Icon(
                    modifier = SdUiModifier().padding(32).size(64),
                    iconNameProperty = IconNameProperty(getNotificationIcon(notification.categoryLabel)),
                    tintProperty = TintProperty(getNotificationTint(notification.categoryLabel)),
                )
            )
        )

        val body = Column(
            modifier = SdUiModifier().padding(horizontal = 24),
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
                            modifier = SdUiModifier().padding(horizontal = 8, vertical = 4),
                            textProperty = TextProperty(notification.categoryLabel.name),
                            fontSizeProperty = FontSizeProperty(12f),
                            fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                        )
                    )
                ),
                Spacer(modifier = SdUiModifier().height(8)),
                Text(
                    textProperty = TextProperty(notification.title),
                    fontSizeProperty = FontSizeProperty(30f),
                    fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                    colorProperty = ColorProperty(ColorOption.White())
                ),
                Spacer(modifier = SdUiModifier().height(8)),
                Text(
                    textProperty = TextProperty(notification.highlightedText.orEmpty()),
                    fontSizeProperty = FontSizeProperty(20f),
                    fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                    colorProperty = ColorProperty(ColorOption.LightBlue())
                ),
                Spacer(modifier = SdUiModifier().height(8)),
                Text(
                    textProperty = TextProperty(notification.dateTimeText.orEmpty()),
                    fontSizeProperty = FontSizeProperty(14f),
                    fontWeightProperty = FontWeightProperty(FontWeightOption.Normal),
                    colorProperty = ColorProperty(ColorOption.CustomColor(0xBA2B8CEE))
                ),
                Spacer(modifier = SdUiModifier().height(32)),
                Card(
                    cardColorsProperty = CardColorsProperty(
                        value = CardColorsModel(
                            containerColor = ColorOption.CustomColor(0xff1E293B),
                            contentColor = ColorOption.LightBlue(),
                        )
                    ),
                    content = listOf(
                        Text(
                            modifier = SdUiModifier().padding(24),
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
                    modifier = SdUiModifier().fillMaxWidth().padding(horizontal = 24).clip(
                        ShapeOption.RoundedCorner(8)
                    ),
                    buttonColorsProperty = ButtonColorsProperty(
                        value = ButtonColorsModel(
                            containerColor = ColorOption.CustomColor(0xff2B8CEE),
                            contentColor = ColorOption.White()
                        )
                    ),
                    content = listOf(
                        Text(
                            modifier = SdUiModifier().padding(vertical = 8),
                            textProperty = TextProperty("View Transaction Details"),
                            fontSizeProperty = FontSizeProperty(16f),
                            fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                        )
                    )
                ),
                Button(
                    modifier = SdUiModifier().fillMaxWidth().padding(horizontal = 24).clip(
                        ShapeOption.RoundedCorner(8)
                    ),
                    buttonColorsProperty = ButtonColorsProperty(
                        value = ButtonColorsModel(
                            containerColor = background,
                            contentColor = ColorOption.CustomColor(0xA02B8CEE)
                        )
                    ),
                    content = listOf(
                        Text(
                            modifier = SdUiModifier().padding(vertical = 8),
                            textProperty = TextProperty("Dismiss Notification"),
                            fontSizeProperty = FontSizeProperty(16f),
                            fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                        )
                    )
                ),
                Spacer(modifier = SdUiModifier().height(32)),
            ),
        )

        val screen = DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            //cacheStrategy = CacheStrategy.TimeCache(System.currentTimeMillis().plus(300000)),
            content = listOf(
                Column(
                    modifier = SdUiModifier().background(color = background),
                    weightProperty = WeightProperty(1f),
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
                                Spacer(modifier = SdUiModifier().height(48)),
                                centerIcon,
                                Spacer(modifier = SdUiModifier().height(32)),
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


    private fun getLoginNotificationTemplate(
        notification: NotificationDetail,
        screenId: String,
    ): Template {
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
                modifier = SdUiModifier().fillMaxWidth(),
                horizontalArrangementProperty = HorizontalArrangementProperty(
                    HorizontalArrangementOption.SpaceBetween
                ),
                verticalAlignmentProperty = VerticalAlignmentProperty(VerticalAlignmentOption.Center),
                content = listOf(
                    Text(
                        modifier = SdUiModifier().padding(vertical = 16),
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
                    modifier = SdUiModifier().fillMaxWidth().background(separatorColor).height(1)
                )
            } else null
        )

        val screen = DefaultTemplate(
            flow = "Notification",
            stage = screenId,
            version = "1",
            content = listOf(
                Column(
                    modifier = SdUiModifier().fillMaxWidth().background(background),
                    weightProperty = WeightProperty(1f),
                    content = listOf(
                        topBar,
                        LazyColumn(
                            modifier = SdUiModifier().fillMaxWidth().padding(horizontal = 24),
                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                HorizontalAlignmentOption.Center
                            ),
                            content = listOf(
                                Spacer(modifier = SdUiModifier().height(24)),
                                Box(
                                    contentAlignmentProperty = ContentAlignmentProperty(
                                        AlignmentOptions.TopEnd
                                    ),
                                    content = listOf(
                                        Card(
                                            shapeProperty = ShapeProperty(ShapeOptions.Circle),
                                            cardColorsProperty = CardColorsProperty(
                                                value = CardColorsModel(
                                                    containerColor = ColorOption.CustomColor(
                                                        0xff153354
                                                    ),
                                                    contentColor = ColorOption.CustomColor(
                                                        0xff2B8CEE
                                                    ),
                                                )
                                            ),
                                            content = listOf(
                                                Icon(
                                                    modifier = SdUiModifier().padding(32).size(64),
                                                    iconNameProperty = IconNameProperty(IconOption.Devices),
                                                    tintProperty = TintProperty(
                                                        ColorOption.LightBlue()
                                                    ),
                                                )
                                            )
                                        ),
                                        Card(
                                            modifier = SdUiModifier().size(44),
                                            shapeProperty = ShapeProperty(ShapeOptions.Circle),
                                            cardColorsProperty = CardColorsProperty(
                                                value = CardColorsModel(
                                                    containerColor = ColorOption.CustomColor(
                                                        0xffEF4444
                                                    ),
                                                    contentColor = ColorOption.White(),
                                                )
                                            ),
                                            content = listOf(
                                                Icon(
                                                    modifier = SdUiModifier().padding(12).size(20),
                                                    iconNameProperty = IconNameProperty(IconOption.WarningAmber),
                                                    tintProperty = TintProperty(ColorOption.White()),
                                                )
                                            )
                                        ),
                                    )
                                ),
                                Spacer(modifier = SdUiModifier().height(28)),
                                Text(
                                    modifier = SdUiModifier().fillMaxWidth(),
                                    textProperty = TextProperty(notification.title),
                                    fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                                    fontSizeProperty = FontSizeProperty(30f),
                                    lineHeightProperty = LineHeightProperty(38),
                                    textAlignProperty = TextAlignProperty(TextAlignOption.Center),
                                    colorProperty = ColorProperty(ColorOption.White()),
                                ),
                                Spacer(modifier = SdUiModifier().height(10)),
                                Text(
                                    modifier = SdUiModifier().fillMaxWidth(),
                                    textProperty = TextProperty(
                                        notification.subtitle
                                    ),
                                    colorProperty = ColorProperty(ColorOption.CustomColor(0xff8EA2BD)),
                                    textAlignProperty = TextAlignProperty(TextAlignOption.Center),
                                    fontSizeProperty = FontSizeProperty(18f),
                                    lineHeightProperty = LineHeightProperty(26),
                                ),
                                Spacer(modifier = SdUiModifier().height(24)),
                                Card(
                                    modifier = SdUiModifier().fillMaxWidth(),
                                    shapeProperty = ShapeProperty(ShapeOptions.Large),
                                    cardColorsProperty = CardColorsProperty(
                                        value = CardColorsModel(
                                            containerColor = cardBackground,
                                            contentColor = ColorOption.White(),
                                        )
                                    ),
                                    content = listOf(
                                        Column(
                                            modifier = SdUiModifier().padding(
                                                horizontal = 16,
                                                vertical = 4
                                            ),
                                            content = detailRow("Device", "iPhone 15 Pro")
                                                .plus(detailRow("Location", "Brooklyn, NY"))
                                                .plus(detailRow("Date", "October 24, 2024"))
                                                .plus(
                                                    detailRow(
                                                        "Time",
                                                        "02:15 PM",
                                                        showSeparator = false
                                                    )
                                                )
                                        ),
                                        Column(
                                            modifier = SdUiModifier().height(130).fillMaxWidth()
                                                .background(ColorOption.CustomColor(0xff1B324A)),
                                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                                HorizontalAlignmentOption.Center
                                            ),
                                            verticalArrangementProperty = VerticalArrangementProperty(
                                                VerticalArrangementOption.Center
                                            ),
                                            content = listOf(
                                                Icon(
                                                    modifier = SdUiModifier().size(26),
                                                    iconNameProperty = IconNameProperty(IconOption.Notification),
                                                    tintProperty = TintProperty(
                                                        ColorOption.CustomColor(
                                                            0xff5FA8FF
                                                        )
                                                    ),
                                                ),
                                                Spacer(modifier = SdUiModifier().height(8)),
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
                                Spacer(modifier = SdUiModifier().height(40)),
                                Button(
                                    modifier = SdUiModifier().fillMaxWidth()
                                        .clip(ShapeOption.RoundedCorner(16)),
                                    buttonColorsProperty = ButtonColorsProperty(
                                        value = ButtonColorsModel(
                                            containerColor = ColorOption.CustomColor(0xff2B8CEE),
                                            contentColor = ColorOption.White()
                                        )
                                    ),
                                    content = listOf(
                                        Text(
                                            modifier = SdUiModifier().padding(vertical = 8),
                                            textProperty = TextProperty("Yes, it was me"),
                                            fontSizeProperty = FontSizeProperty(18f),
                                            fontWeightProperty = FontWeightProperty(FontWeightOption.Bold)
                                        )
                                    )
                                ),
                                Spacer(modifier = SdUiModifier().height(12)),
                                OutlinedButton(
                                    modifier = SdUiModifier().fillMaxWidth()
                                        .clip(ShapeOption.RoundedCorner(16)),
                                    buttonColorsProperty = ButtonColorsProperty(
                                        value = ButtonColorsModel()
                                    ),
                                    content = listOf(
                                        Text(
                                            modifier = SdUiModifier().padding(vertical = 8),
                                            textProperty = TextProperty("No, secure my account"),
                                            fontSizeProperty = FontSizeProperty(16f),
                                            fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                                            colorProperty = ColorProperty(ColorOption.White())
                                        )
                                    )
                                ),
                                Spacer(modifier = SdUiModifier().height(24)),
                                Text(
                                    modifier = SdUiModifier().fillMaxWidth(),
                                    textProperty = TextProperty(
                                        "If you don't recognize this activity, please secure your account immediately to prevent unauthorized access."
                                    ),
                                    colorProperty = ColorProperty(ColorOption.CustomColor(0xff7E94AF)),
                                    textAlignProperty = TextAlignProperty(TextAlignOption.Center),
                                    fontSizeProperty = FontSizeProperty(16f),
                                    lineHeightProperty = LineHeightProperty(24),
                                ),
                                Spacer(modifier = SdUiModifier().height(24)),
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