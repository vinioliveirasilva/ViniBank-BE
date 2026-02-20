package com.vinibank.backend.sdui.flow.notification

import com.vinibank.backend.sdui.flow.BaseFlowController
import com.vinibank.backend.sdui.flow.SdUiScreen
import com.vinibank.backend.sdui.flow.notification.screen.MainNotificationScreen
import org.springframework.stereotype.Component

interface NotificationScreen : SdUiScreen

@Component()
class NotificationController(
    screens: List<NotificationScreen>,
    defaultScreen: MainNotificationScreen,
) : BaseFlowController<NotificationScreen>(screens, defaultScreen, "Notification")