package com.vinibank.backend.sdui.components

import com.vinibank.backend.sdui.flow.ScreenUtil.action
import com.vinibank.backend.sdui.flow.ScreenUtil.component
import com.vinibank.backend.sdui.flow.ScreenUtil.property

fun topBarWithBackAction(title: String) = topBarWithAction(title, "back", "LeftArrow")

fun topBarWithCloseAction(title: String) = topBarWithAction(title, "close", "Close")

private fun topBarWithAction(title: String, action: String, actionIcon: String) = component(
    "topAppBar",
    listOf(),
    listOf(
        component(
            "text",
            listOf(
                property("text", title)
            )
        )
    ),
    validators = listOf(),
    customComponents = arrayOf(
        "navigationIcons" to listOf(
            component(
                "iconButton",
                action = action(action),
                components = listOf(
                    component(
                        "icon",
                        listOf(
                            property("icon", actionIcon),
                        )
                    )
                )
            )
        )
    )
)