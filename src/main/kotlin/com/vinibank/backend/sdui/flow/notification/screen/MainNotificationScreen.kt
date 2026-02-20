package com.vinibank.backend.sdui.flow.notification.screen

import com.vini.designsystemsdui.CacheStrategy
import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.CloseAction
import com.vini.designsystemsdui.action.ContinueAction
import com.vini.designsystemsdui.action.MultipleActions
import com.vini.designsystemsdui.action.ToBooleanAction
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.IconButton
import com.vini.designsystemsdui.component.LazyRow
import com.vini.designsystemsdui.component.Row
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.component.TopAppBar
import com.vini.designsystemsdui.property.BackgroundColorProperty
import com.vini.designsystemsdui.property.ButtonColorsProperty
import com.vini.designsystemsdui.property.ColorProperty
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.FontWeightProperty
import com.vini.designsystemsdui.property.HeightProperty
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
import com.vini.designsystemsdui.property.TopAppBarColorsProperty
import com.vini.designsystemsdui.property.VerticalAlignmentProperty
import com.vini.designsystemsdui.property.VisibilityProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.WidthProperty
import com.vini.designsystemsdui.property.options.ButtonColorsModel
import com.vini.designsystemsdui.property.options.FontWeightOption
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.IconOption
import com.vini.designsystemsdui.property.options.ShapeOptions
import com.vini.designsystemsdui.property.options.TextAlignOption
import com.vini.designsystemsdui.property.options.TopAppBarColorsModel
import com.vini.designsystemsdui.property.options.VerticalAlignmentOption
import com.vini.designsystemsdui.property.options.color.ColorOption
import com.vini.designsystemsdui.property.util.PropertyIdWrapper
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.db.NotificationCategory
import com.vinibank.backend.db.NotificationsDatabase
import com.vinibank.backend.db.UserLoginDb
import com.vinibank.backend.sdui.flow.notification.NotificationScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

@Component
class MainNotificationScreen(
    private val notificationsDatabase: NotificationsDatabase,
    private val userLoginDb: UserLoginDb,
) : NotificationScreen {
    override val screenId: String = "Start"

    object RelativeDateFormatter {

        fun format(epochMillis: Long): String {
            val now = System.currentTimeMillis()
            val diff = now - epochMillis

            val minutes = TimeUnit.MILLISECONDS.toMinutes(diff)
            val hours = TimeUnit.MILLISECONDS.toHours(diff)
            val days = TimeUnit.MILLISECONDS.toDays(diff)

            return when {
                minutes < 1 -> "now"

                minutes < 60 ->
                    "${minutes}m ago"

                hours < 24 ->
                    "${hours}h ago"

                days == 1L ->
                    "Yesterday"

                days < 7 ->
                    "${days} days ago"

                else ->
                    formatDate(epochMillis)
            }
        }

        private fun formatDate(epochMillis: Long): String {
            val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            return sdf.format(Date(epochMillis))
        }
    }

    override fun getScreen(request: SdUiRequest): Template? {

        val background = ColorOption.CustomColor(0xff101922)

        val topBar = TopAppBar(
            colors = TopAppBarColorsProperty(
                value = TopAppBarColorsModel(
                    containerColor = background,
                    titleContentColor = ColorOption.White(),
                    navigationIconContentColor = ColorOption.White(),
                )
            ),
            title = listOf(
                Text(
                    textProperty = TextProperty("Notifications"),
                    fontSizeProperty = FontSizeProperty(20f),
                    lineHeightProperty = LineHeightProperty(28),
                    textAlignProperty = TextAlignProperty(TextAlignOption.Center),
                    fontWeightProperty = FontWeightProperty(FontWeightOption.Bold)
                )
            ),
            navigationIcon = listOf(
                IconButton(
                    onClick = CloseAction(),
                    content = listOf(
                        Icon(
                            iconNameProperty = IconNameProperty(IconOption.LeftArrow)
                        )
                    )
                )
            ),
            actions = listOf(
                Text(
                    colorProperty = ColorProperty(ColorOption.LightBlue()),
                    textProperty = TextProperty("Mark all as read"),
                    fontSizeProperty = FontSizeProperty(12f),
                    fontWeightProperty = FontWeightProperty(FontWeightOption.SemiBold)
                )
            )
        )

        val selectedButtonColor = ButtonColorsModel(
            containerColor = ColorOption.CustomColor(0xff2B8CEE),
            contentColor = ColorOption.White()
        )

        val unselectedButtonColor = ButtonColorsModel(
            containerColor = ColorOption.CustomColor(0xff192633),
            contentColor = ColorOption.LightGray()
        )

        fun filterButton(name: String, isSelected: Boolean = false) = Button(
            shapeProperty = ShapeProperty(ShapeOptions.Medium),
            buttonColorsProperty = ButtonColorsProperty(
                value = if (isSelected) selectedButtonColor else unselectedButtonColor
            ),
            content = listOf(
                Text(
                    textProperty = TextProperty(name),
                    fontSizeProperty = FontSizeProperty(12f),
                )
            )
        )

        val filters = LazyRow(
            paddingHorizontalProperty = PaddingHorizontalProperty(16),
            content = listOf(
                filterButton("All", true),
                Spacer(widthProperty = WidthProperty(8)),
                filterButton("Transactions"),
                Spacer(widthProperty = WidthProperty(8)),
                filterButton("Security"),
                Spacer(widthProperty = WidthProperty(8)),
                filterButton("Offers"),
            )
        )

        val separator = Column(
            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                HorizontalFillTypeOption.Max
            ),
            heightProperty = HeightProperty(1),
            backgroundColorProperty = BackgroundColorProperty(ColorOption.CustomColor(0xff233648))
        )

        fun getNotifications() =
            notificationsDatabase.list(userLoginDb.getUserEmail(request.sessionId))

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

        val notifications = getNotifications().map {
            Column(
                onClick = MultipleActions(
                    actions = listOf(
                        ContinueAction(
                            flowId = request.flow,
                            currentScreenId = screenId,
                            nextScreenId = "Detail",
                            screenData = buildJsonObject {
                                put("notificationId", it.id)
                            }
                        ),
                        ToBooleanAction(
                            idToChange = PropertyIdWrapper(
                                id = "notification_${it.id}"
                            ),
                            newValue = false
                        )
                    )
                ),
                content = listOf(
                    Row(
                        paddingVerticalProperty = PaddingVerticalProperty(16),
                        paddingHorizontalProperty = PaddingHorizontalProperty(16),
                        horizontalFillTypeProperty = HorizontalFillTypeProperty(
                            HorizontalFillTypeOption.Max
                        ),
                        content = listOf(
                            Column(
                                backgroundColorProperty = BackgroundColorProperty(
                                    getNotificationBackground(it.category)
                                ),
                                shapeProperty = ShapeProperty(ShapeOptions.Medium),
                                content = listOf(
                                    IconButton(
                                        content = listOf(
                                            Icon(
                                                sizeProperty = SizeProperty(24),
                                                tintProperty = TintProperty(
                                                    getNotificationTint(it.category)
                                                ),
                                                iconNameProperty = IconNameProperty(
                                                    getNotificationIcon(it.category)
                                                )
                                            )
                                        )
                                    )
                                )
                            ),
                            Spacer(sizeProperty = SizeProperty(16)),
                            Column(
                                content = listOf(
                                    Row(
                                        horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                            HorizontalFillTypeOption.Max
                                        ),
                                        horizontalArrangementProperty = HorizontalArrangementProperty(
                                            HorizontalArrangementOption.SpaceBetween
                                        ),
                                        content = listOf(
                                            Text(
                                                colorProperty = ColorProperty(ColorOption.White()),
                                                textProperty = TextProperty(it.title),
                                                fontWeightProperty = FontWeightProperty(
                                                    FontWeightOption.Bold
                                                ),
                                                fontSizeProperty = FontSizeProperty(14f),
                                            ),
                                            Row(
                                                verticalAlignmentProperty = VerticalAlignmentProperty(
                                                    VerticalAlignmentOption.Center
                                                ),
                                                content = listOfNotNull(
                                                    Text(
                                                        colorProperty = ColorProperty(ColorOption.LightGray()),
                                                        textProperty = TextProperty(
                                                            RelativeDateFormatter.format(it.createdAtEpochMillis)
                                                        ),
                                                        fontSizeProperty = FontSizeProperty(11f),
                                                        fontWeightProperty = FontWeightProperty(
                                                            FontWeightOption.Light
                                                        ),
                                                    ),
                                                    Column(
                                                        visibilityProperty = VisibilityProperty(
                                                            value = !it.isRead,
                                                            idWrapper = PropertyIdWrapper(
                                                                id = "notification_${it.id}"
                                                            )
                                                        ),
                                                        paddingHorizontalProperty = PaddingHorizontalProperty(
                                                            4
                                                        ),
                                                        heightProperty = HeightProperty(8),
                                                        widthProperty = WidthProperty(16),
                                                        shapeProperty = ShapeProperty(
                                                            ShapeOptions.Circle
                                                        ),
                                                        backgroundColorProperty = BackgroundColorProperty(
                                                            ColorOption.LightBlue()
                                                        )
                                                    )
                                                )
                                            )
                                        )
                                    ),
                                    Text(
                                        colorProperty = ColorProperty(ColorOption.LightGray()),
                                        textProperty = TextProperty(it.message),
                                        fontWeightProperty = FontWeightProperty(FontWeightOption.Normal),
                                        fontSizeProperty = FontSizeProperty(14f),
                                    ),
                                )
                            ),
                        )
                    ),
                    separator
                )
            )
        }

        val screen = DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            cacheStrategy = CacheStrategy.NoCache(),//CacheStrategy.TimeCache(System.currentTimeMillis().plus(300000)),
            content = listOf(
                Column(
                    backgroundColorProperty = BackgroundColorProperty(background),
                    weightProperty = WeightProperty(1f),
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    content = listOf(
                        topBar,
                        filters,
                        Spacer(heightProperty = HeightProperty(16)),
                        separator,
                    ).plus(notifications)
                )
            )
        )

        return screen
    }
}