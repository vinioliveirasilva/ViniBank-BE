package com.vinibank.backend.sdui.flow

import com.vini.designsystemsdui.action.backAction
import com.vini.designsystemsdui.component.icon
import com.vini.designsystemsdui.component.iconButton
import com.vini.designsystemsdui.component.text
import com.vini.designsystemsdui.component.topBar
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.TextAlignProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.TextAlignOption
import com.vinibank.backend.sdui.model.SdUiRequest
import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen
import org.springframework.stereotype.Component

@Component
class UndefinedController {
    fun getSdUiScreen(
        request: SdUiRequest
    ) = screen(
        flow = request.flow,
        stage = request.toScreen,
        version = "1",
        template = "",
        shouldCache = false,
        components = listOf(
            topBar(
                components = listOf(
                    text(textProperty = TextProperty("TODO"))
                ),
                navigationIcons = listOf(
                    iconButton(
                        components = listOf(
                            icon(
                                iconName = IconNameProperty("LeftArrow"),
                            )
                        ),
                        actions = listOf(
                            backAction()
                        )
                    ),
                )
            ),
            text(
                textProperty = TextProperty("Tela não cadastrada"),
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                paddingVerticalProperty = PaddingVerticalProperty(30),
                textAlignProperty = TextAlignProperty(TextAlignOption.Center)
            ),
        )
    )
}