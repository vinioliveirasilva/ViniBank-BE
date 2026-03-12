package com.vinibank.backend.sdui.flow

import com.vini.designsystemsdui.ui.action.BackAction
import com.vini.designsystemsdui.ui.component.Column
import com.vini.designsystemsdui.ui.component.Icon
import com.vini.designsystemsdui.ui.component.IconButton
import com.vini.designsystemsdui.ui.component.Text
import com.vini.designsystemsdui.ui.component.TopAppBar
import com.vini.designsystemsdui.ui.modifier.Modifier
import com.vini.designsystemsdui.ui.modifier.background
import com.vini.designsystemsdui.ui.modifier.fillMaxSize
import com.vini.designsystemsdui.ui.modifier.fillMaxWidth
import com.vini.designsystemsdui.ui.modifier.padding
import com.vini.designsystemsdui.ui.modifier.option.IconOption
import com.vini.designsystemsdui.ui.modifier.option.TextAlignOption
import com.vini.designsystemsdui.ui.modifier.option.ColorOption
import com.vini.designsystemsdui.ui.template.ScreenTemplate
import com.vinibank.backend.sdui.model.SdUiRequest

fun getUndefinedScreen(request: SdUiRequest) = ScreenTemplate(
    flow = request.flow,
    stage = request.toScreen,
    version = "1",
    content = {
        Column(
            modifier = Modifier.fillMaxSize().background(ColorOption.White())
        ) {
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
                modifier = Modifier.fillMaxWidth().padding(vertical = 30),
                text = "Tela não cadastrada",
                textAlign = TextAlignOption.Center
            )
        }
    }
)
