package com.vinibank.backend.sdui.flow.investments

import com.vinibank.backend.sdui.flow.BaseFlowController
import com.vinibank.backend.sdui.flow.SdUiScreen
import com.vinibank.backend.sdui.flow.investments.screen.ConsolidatedPositionScreen
import org.springframework.stereotype.Component

interface InvestmentsScreen : SdUiScreen

@Component
class InvestmentsController(
    screens: List<InvestmentsScreen>,
    defaultScreen: ConsolidatedPositionScreen,
) : BaseFlowController<InvestmentsScreen>(screens, defaultScreen, "Investments")


fun Double.toBrl(): String {
    val formattedValue = "%.2f".format(this).replace(".", ",")
    val parts = formattedValue.split(",")
    val integerPart = parts[0].reversed().chunked(3).joinToString(".").reversed()
    return "R$ $integerPart,${parts[1]}"
}