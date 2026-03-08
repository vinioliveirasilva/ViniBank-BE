package com.vinibank.backend.sdui.flow.notification.screen

import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.BackAction
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
import com.vini.designsystemsdui.core.SdUiComposer
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.background
import com.vini.designsystemsdui.modifier.clip
import com.vini.designsystemsdui.modifier.fillMaxHeight
import com.vini.designsystemsdui.modifier.fillMaxSize
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.height
import com.vini.designsystemsdui.modifier.option.FontWeightOption
import com.vini.designsystemsdui.modifier.option.HorizontalAlignmentOption
import com.vini.designsystemsdui.modifier.option.HorizontalArrangementOption
import com.vini.designsystemsdui.modifier.option.IconOption
import com.vini.designsystemsdui.modifier.option.ShapeOption
import com.vini.designsystemsdui.modifier.option.TextAlignOption
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.size
import com.vini.designsystemsdui.property.options.ButtonColorsModel
import com.vini.designsystemsdui.property.options.CardColorsModel
import com.vini.designsystemsdui.property.options.TopAppBarColorsModel
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
        val userEmail = userLoginDb.getUserEmail(request.sessionId)
        val notification = notificationDetailDatabase.get(userEmail, notificationId)

        if (notification == null) {
            return getUndefinedScreen(request)
        }

        notificationsDatabase.markAsRead(userEmail, notification.notificationId)
        return getNotificationTemplate(request, notification, screenId)
    }

    private fun getNotificationIcon(type: NotificationCategory) = when (type) {
        NotificationCategory.TRANSACTIONS -> IconOption.WalletOutlined
        NotificationCategory.SECURITY -> IconOption.ShieldOutlined
        NotificationCategory.OFFER -> IconOption.Campaign
        NotificationCategory.LOGIN -> IconOption.Devices
        NotificationCategory.STATEMENT -> IconOption.NotificationsNone
        else -> IconOption.Notification
    }

    private fun getNotificationTint(type: NotificationCategory) = when (type) {
        NotificationCategory.TRANSACTIONS -> ColorOption.CustomColor(0xff2B8CEE)
        NotificationCategory.SECURITY -> ColorOption.CustomColor(0xffF59E0B)
        NotificationCategory.OFFER -> ColorOption.CustomColor(0xff2B8CEE)
        NotificationCategory.LOGIN -> ColorOption.CustomColor(0xff92ADC9)
        NotificationCategory.STATEMENT -> ColorOption.CustomColor(0xff92ADC9)
        else -> ColorOption.CustomColor(0xff2B8CEE)
    }

    private fun getNotificationBackground(type: NotificationCategory) = when (type) {
        NotificationCategory.TRANSACTIONS -> ColorOption.CustomColor(0x302B8CEE)
        NotificationCategory.SECURITY -> ColorOption.CustomColor(0x30F59E0B)
        NotificationCategory.OFFER -> ColorOption.CustomColor(0x302B8CEE)
        NotificationCategory.LOGIN -> ColorOption.CustomColor(0x3092ADC9)
        NotificationCategory.STATEMENT -> ColorOption.CustomColor(0x3092ADC9)
        else -> ColorOption.CustomColor(0x302B8CEE)
    }

    private fun SdUiComposer.detailRow(label: String, value: String) {
        Row(
            modifier = SdUiModifier().fillMaxWidth(),
            horizontalArrangement = HorizontalArrangementOption.SpaceBetween(),
            content = {
                Text(
                    text = label,
                    color = ColorOption.CustomColor(0xff8EA2BD),
                    fontSize = 14f,
                    lineHeight = 20f,
                )
                Text(
                    text = value,
                    color = ColorOption.White(),
                    fontWeight = FontWeightOption.SemiBold,
                    fontSize = 14f,
                    lineHeight = 20f,
                )
            }
        )
    }

    private fun getNotificationTemplate(
        request: SdUiRequest,
        notification: NotificationDetail,
        screenId: String,
    ): Template {
        val background = ColorOption.CustomColor(0xff081523)

        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            content = {
                Column(
                    modifier = SdUiModifier().fillMaxSize().background(background),
                    content = {
                        TopAppBar(
                            colors = TopAppBarColorsModel(
                                containerColor = background,
                                titleContentColor = ColorOption.White(),
                                navigationIconContentColor = ColorOption.White(),
                            ),
                            title = {
                                Text(
                                    text = notification.screenTitle,
                                    fontSize = 18f,
                                    lineHeight = 28f,
                                    textAlign = TextAlignOption.Center,
                                    fontWeight = FontWeightOption.Bold
                                )
                            },
                            navigationIcon = {
                                IconButton(
                                    onClickAction = BackAction(),
                                    content = {
                                        Icon(icon = IconOption.LeftArrow)
                                    }
                                )
                            }
                        )
                        LazyColumn(
                            horizontalAlignment = HorizontalAlignmentOption.Center(),
                            modifier = SdUiModifier().fillMaxWidth().padding(horizontal = 24),
                            content = {
                                Spacer(modifier = SdUiModifier().height(24))
                                Column(
                                    modifier = SdUiModifier().size(110).clip(ShapeOption.Circle())
                                        .background(getNotificationBackground(notification.categoryLabel)),
                                    content = {
                                        Icon(
                                            modifier = SdUiModifier().padding(26).size(58),
                                            icon = getNotificationIcon(notification.categoryLabel),
                                            tint = getNotificationTint(notification.categoryLabel),
                                        )
                                    }
                                )
                                Spacer(modifier = SdUiModifier().height(18))
                                Text(
                                    modifier = SdUiModifier().fillMaxWidth(),
                                    text = notification.title,
                                    fontWeight = FontWeightOption.Bold,
                                    fontSize = 28f,
                                    lineHeight = 36f,
                                    textAlign = TextAlignOption.Center,
                                    color = ColorOption.White(),
                                )
                                Spacer(modifier = SdUiModifier().height(8))
                                Text(
                                    modifier = SdUiModifier().fillMaxWidth(),
                                    text = notification.subtitle,
                                    color = ColorOption.CustomColor(0xff8EA2BD),
                                    textAlign = TextAlignOption.Center,
                                    fontSize = 16f,
                                    lineHeight = 24f,
                                )
                                if (!notification.highlightedText.isNullOrBlank()) {
                                    Spacer(modifier = SdUiModifier().height(10))
                                    Text(
                                        modifier = SdUiModifier().fillMaxWidth(),
                                        text = notification.highlightedText,
                                        color = ColorOption.LightBlue(),
                                        textAlign = TextAlignOption.Center,
                                        fontWeight = FontWeightOption.Bold,
                                        fontSize = 18f,
                                    )
                                }
                                if (!notification.dateTimeText.isNullOrBlank()) {
                                    Spacer(modifier = SdUiModifier().height(8))
                                    Text(
                                        modifier = SdUiModifier().fillMaxWidth(),
                                        text = notification.dateTimeText,
                                        color = ColorOption.CustomColor(0xBA2B8CEE),
                                        textAlign = TextAlignOption.Center,
                                        fontSize = 14f,
                                    )
                                }
                                Spacer(modifier = SdUiModifier().height(22))
                                Card(
                                    modifier = SdUiModifier().fillMaxWidth(),
                                    shape = ShapeOption.RoundedCorner(16),
                                    colors = CardColorsModel(
                                        containerColor = ColorOption.CustomColor(0xff0D1D30),
                                        contentColor = ColorOption.White(),
                                    ),
                                    content = {
                                        Column(
                                            modifier = SdUiModifier().padding(16),
                                            content = {
                                                if (!notification.detailMessage.isNullOrBlank()) {
                                                    Text(
                                                        text = notification.detailMessage,
                                                        fontSize = 15f,
                                                        lineHeight = 22f,
                                                        color = ColorOption.CustomColor(0xffC2D4E6)
                                                    )
                                                }
                                                if (notification.detailItems.isNotEmpty()) {
                                                    Spacer(modifier = SdUiModifier().height(12))
                                                    notification.detailItems.forEach { (key, value) ->
                                                        detailRow(key, value)
                                                        Spacer(modifier = SdUiModifier().height(8))
                                                    }
                                                }
                                            }
                                        )
                                    }
                                )
                                Spacer(modifier = SdUiModifier().height(26))
                                Button(
                                    modifier = SdUiModifier().fillMaxWidth()
                                        .clip(ShapeOption.RoundedCorner(12)),
                                    colors = ButtonColorsModel(
                                        containerColor = ColorOption.CustomColor(0xff2B8CEE),
                                        contentColor = ColorOption.White()
                                    ),
                                    content = {
                                        Text(
                                            modifier = SdUiModifier().padding(vertical = 6),
                                            text = notification.primaryActionLabel ?: "Continuar",
                                            fontSize = 16f,
                                            fontWeight = FontWeightOption.Bold
                                        )
                                    }
                                )
                                if (!notification.secondaryActionLabel.isNullOrBlank()) {
                                    Spacer(modifier = SdUiModifier().height(10))
                                    OutlinedButton(
                                        modifier = SdUiModifier().fillMaxWidth()
                                            .clip(ShapeOption.RoundedCorner(12)),
                                        content = {
                                            Text(
                                                modifier = SdUiModifier().padding(vertical = 6),
                                                text = notification.secondaryActionLabel,
                                                color = ColorOption.White(),
                                                fontWeight = FontWeightOption.Bold
                                            )
                                        }
                                    )
                                }
                                if (!notification.footerMessage.isNullOrBlank()) {
                                    Spacer(modifier = SdUiModifier().height(18))
                                    Text(
                                        modifier = SdUiModifier().fillMaxWidth(),
                                        text = notification.footerMessage,
                                        color = ColorOption.CustomColor(0xff7E94AF),
                                        textAlign = TextAlignOption.Center,
                                        fontSize = 14f,
                                        lineHeight = 22f,
                                    )
                                }
                                Spacer(modifier = SdUiModifier().height(24))
                            }
                        )
                    }
                )
            }
        )
    }
}
