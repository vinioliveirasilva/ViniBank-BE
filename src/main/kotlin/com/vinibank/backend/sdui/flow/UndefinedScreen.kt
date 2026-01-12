package com.vinibank.backend.sdui.flow

import com.vini.designsystemsdui.action.BackAction
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.IconButton
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.component.TopAppBar
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.TextAlignProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.TextAlignOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.sdui.model.SdUiRequest

fun getUndefinedScreen(request: SdUiRequest) = DefaultTemplate(
    flow = request.flow,
    stage = request.toScreen,
    version = "1",
    content =  listOf(
        TopAppBar(
            title =  listOf(
                Text(textProperty = TextProperty("TODO"))
            ),
            navigationIcon = listOf(
                IconButton(
                    content =  listOf(
                        Icon(
                            iconNameProperty = IconNameProperty("LeftArrow"),
                        )
                    ),
                    onClick = BackAction(),
                ),
            )
        ),
        Text(
            textProperty = TextProperty("Tela não cadastrada"),
            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
            paddingVerticalProperty = PaddingVerticalProperty(30),
            textAlignProperty = TextAlignProperty(TextAlignOption.Center)
        ),
    )
)