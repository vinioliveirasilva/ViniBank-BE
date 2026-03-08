package com.vinibank.backend.sdui.flow.notification.screen

import com.vini.designsystemsdui.CacheStrategy
import com.vini.designsystemsdui.InteractionId
import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.CloseAction
import com.vini.designsystemsdui.action.ContinueAction
import com.vini.designsystemsdui.action.MultipleActions
import com.vini.designsystemsdui.action.ToModifierAction
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.IconButton
import com.vini.designsystemsdui.component.Row
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.component.TopAppBar
import com.vini.designsystemsdui.modifier.BaseModifier
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.background
import com.vini.designsystemsdui.modifier.clickable
import com.vini.designsystemsdui.modifier.clip
import com.vini.designsystemsdui.modifier.fillMaxHeight
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.height
import com.vini.designsystemsdui.modifier.option.FontWeightOption
import com.vini.designsystemsdui.modifier.option.HorizontalArrangementOption
import com.vini.designsystemsdui.modifier.option.IconOption
import com.vini.designsystemsdui.modifier.option.ShapeOption
import com.vini.designsystemsdui.modifier.option.TextAlignOption
import com.vini.designsystemsdui.modifier.option.VerticalAlignmentOption
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.size
import com.vini.designsystemsdui.modifier.visible
import com.vini.designsystemsdui.property.options.TopAppBarColorsModel
import com.vini.designsystemsdui.property.options.color.ColorOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.db.NotificationCategory
import com.vinibank.backend.db.NotificationsDatabase
import com.vinibank.backend.db.UserLoginDb
import com.vinibank.backend.sdui.flow.notification.NotificationScreen
import com.vinibank.backend.sdui.model.SdUiRequest
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
                minutes < 60 -> "${minutes}m ago"
                hours < 24 -> "${hours}h ago"
                days == 1L -> "Yesterday"
                days < 7 -> "${days} days ago"
                else -> {
                    val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                    sdf.format(Date(epochMillis))
                }
            }
        }
    }

    override fun getScreen(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? {
        val background = ColorOption.CustomColor(0xff101922)
        val notifications = notificationsDatabase.list(userLoginDb.getUserEmail(request.sessionId))

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

        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            //cacheStrategy = CacheStrategy.TimeCache(System.currentTimeMillis().plus(300000)),
            content = {
                Column(
                    modifier = SdUiModifier().fillMaxWidth().fillMaxHeight().background(background),
                    content = {
                        TopAppBar(
                            colors = TopAppBarColorsModel(
                                containerColor = background,
                                titleContentColor = ColorOption.White(),
                                navigationIconContentColor = ColorOption.White(),
                            ),
                            title = {
                                Text(
                                    text = "Notifications",
                                    fontSize = 20f,
                                    lineHeight = 28f,
                                    textAlign = TextAlignOption.Center,
                                    fontWeight = FontWeightOption.Bold
                                )
                            },
                            navigationIcon = {
                                IconButton(
                                    onClickAction = CloseAction(),
                                    content = {
                                        Icon(icon = IconOption.LeftArrow)
                                    }
                                )
                            }
                        )
                        Column(
                            modifier = SdUiModifier().fillMaxWidth().height(1)
                                .background(ColorOption.CustomColor(0xff233648))
                        )
                        if (notifications.isEmpty()) {
                            Column(
                                modifier = SdUiModifier().fillMaxWidth().padding(horizontal = 24)
                                    .padding(vertical = 32),
                                content = {
                                    Text(
                                        text = "No notifications found.",
                                        color = ColorOption.LightGray()
                                    )
                                }
                            )
                        } else {
                            notifications.forEach { notification ->
                                fun readDotId(notificationId: String) =
                                    InteractionId<BaseModifier>(id = "notification_${notificationId}")
                                Column(
                                    modifier = SdUiModifier().clickable(
                                        action = MultipleActions(
                                            actions = listOf(
                                                ContinueAction(
                                                    flowId = request.flow,
                                                    currentScreenId = screenId,
                                                    nextScreenId = "Detail?notificationId=${notification.id}",
                                                ),
                                                ToModifierAction(
                                                    SdUiModifier().visible(
                                                        visible = false,
                                                        id = readDotId(notification.id)
                                                    )
                                                )
                                            )
                                        )
                                    ),
                                    content = {
                                        Row(
                                            modifier = SdUiModifier().padding(all = 16)
                                                .fillMaxWidth(),
                                            verticalAlignment = VerticalAlignmentOption.Center(),
                                            content = {
                                                Column(
                                                    modifier = SdUiModifier().clip(
                                                        ShapeOption.RoundedCorner(8)
                                                    )
                                                        .background(
                                                            getNotificationBackground(
                                                                notification.category
                                                            )
                                                        ).padding(horizontal = 12)
                                                        .padding(vertical = 12),
                                                    content = {
                                                        Icon(
                                                            modifier = SdUiModifier().size(
                                                                20
                                                            ),
                                                            tint = getNotificationTint(
                                                                notification.category
                                                            ),
                                                            icon = getNotificationIcon(
                                                                notification.category
                                                            )
                                                        )
                                                    }
                                                )
                                                Spacer(modifier = SdUiModifier().size(16))
                                                Column(
                                                    modifier = SdUiModifier().fillMaxWidth(),
                                                    content = {
                                                        Row(
                                                            modifier = SdUiModifier().fillMaxWidth(),
                                                            horizontalArrangement = HorizontalArrangementOption.SpaceBetween(),
                                                            content = {
                                                                Text(
                                                                    color = ColorOption.White(),
                                                                    text = notification.title,
                                                                    fontWeight = FontWeightOption.Bold,
                                                                    fontSize = 14f,
                                                                )
                                                                Row(
                                                                    verticalAlignment = VerticalAlignmentOption.Center()
                                                                ) {
                                                                    Text(
                                                                        color = ColorOption.LightGray(),
                                                                        text = RelativeDateFormatter.format(
                                                                            notification.createdAtEpochMillis
                                                                        ),
                                                                        fontSize = 11f,
                                                                        fontWeight = FontWeightOption.Light,
                                                                    )
                                                                    Column(
                                                                        modifier = SdUiModifier().visible(
                                                                            visible = !notification.isRead,
                                                                            id = readDotId(
                                                                                notification.id
                                                                            )
                                                                        )
                                                                            .padding(horizontal = 4)
                                                                            .size(8)
                                                                            .clip(ShapeOption.Circle())
                                                                            .background(
                                                                                ColorOption.LightBlue()
                                                                            ),
                                                                    )
                                                                }
                                                            }
                                                        )
                                                        Spacer(
                                                            modifier = SdUiModifier().height(4)
                                                        )
                                                        Text(
                                                            color = ColorOption.LightGray(),
                                                            text = notification.message,
                                                            fontWeight = FontWeightOption.Normal,
                                                            fontSize = 14f,
                                                        )
                                                    }
                                                )
                                            }
                                        )
                                        Column(
                                            modifier = SdUiModifier().fillMaxWidth()
                                                .height(1)
                                                .background(
                                                    ColorOption.CustomColor(
                                                        0xff233648
                                                    )
                                                )
                                        )
                                    }
                                )
                            }
                        }
                    }
                )
            }
        )
    }
}
