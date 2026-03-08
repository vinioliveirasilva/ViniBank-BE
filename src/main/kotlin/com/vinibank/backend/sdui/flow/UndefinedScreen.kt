package com.vinibank.backend.sdui.flow

import com.vini.designsystemsdui.action.BackAction
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.IconButton
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.component.TopAppBar
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.option.IconOption
import com.vini.designsystemsdui.modifier.option.TextAlignOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.sdui.model.SdUiRequest

fun getUndefinedScreen(request: SdUiRequest) = DefaultTemplate(
    flow = request.flow,
    stage = request.toScreen,
    version = "1",
    content = {
        TopAppBar(
            title = {
                Text(text = "TODO")
            },
            navigationIcon = {
                IconButton(
                    content = {
                        Icon(icon = IconOption.LeftArrow)
                    },
                    onClickAction = BackAction(),
                )
            }
        )
        Text(
            modifier = SdUiModifier().fillMaxWidth().padding(vertical = 30),
            text = "Tela não cadastrada",
            textAlign = TextAlignOption.Center
        )
    }
)
