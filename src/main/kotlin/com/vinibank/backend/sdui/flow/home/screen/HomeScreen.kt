package com.vinibank.backend.sdui.flow.home.screen

import com.vini.designsystemsdui.action.continueAction
import com.vini.designsystemsdui.component.icon
import com.vini.designsystemsdui.component.iconButton
import com.vini.designsystemsdui.component.navigationBar
import com.vini.designsystemsdui.component.navigationBarItem
import com.vini.designsystemsdui.component.sdUi
import com.vini.designsystemsdui.component.text
import com.vini.designsystemsdui.component.topBar
import com.vini.designsystemsdui.property.CurrentScreenProperty
import com.vini.designsystemsdui.property.DestinationIndexProperty
import com.vini.designsystemsdui.property.FlowIdentifierProperty
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.RequestUpdateProperty
import com.vini.designsystemsdui.property.SelectedNavigationDestinationIndexProperty
import com.vini.designsystemsdui.property.StageIdentifierProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.validator.intToStringValidator
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen
import kotlinx.serialization.json.JsonObject
import org.springframework.stereotype.Component

@Component
class HomeScreen : HomeScreen {
    override val screenId: String
        get() = "Home"

    override fun getScreen(request: SdUiRequest): JsonObject? {
        val bottomNavigation = navigationBar(
            selectedDestinationIndex = SelectedNavigationDestinationIndexProperty(0, "bottomNavigation.selectedDestination"),
            components = listOf(
                navigationBarItem(
                    destinationIndex = DestinationIndexProperty(0),
                    selectedDestinationIndex = SelectedNavigationDestinationIndexProperty(0, "bottomNavigation.selectedDestination"),
                    components = listOf(
                        text(textProperty = TextProperty("Home"))
                    ),
                    selectedIcon = listOf(
                        icon(iconName = IconNameProperty("Home"))
                    ),
                    unselectedIcon = listOf(
                        icon(iconName = IconNameProperty("HomeOutline"))
                    )
                ),
                navigationBarItem(
                    destinationIndex = DestinationIndexProperty(1),
                    selectedDestinationIndex = SelectedNavigationDestinationIndexProperty(1, "bottomNavigation.selectedDestination"),
                    components = listOf(
                        text(textProperty = TextProperty("Card"))
                    ),
                    selectedIcon = listOf(
                        icon(iconName = IconNameProperty("Payment"))
                    ),
                    unselectedIcon = listOf(
                        icon(iconName = IconNameProperty("PaymentOutline"))
                    )
                ),
                navigationBarItem(
                    destinationIndex = DestinationIndexProperty(2),
                    selectedDestinationIndex = SelectedNavigationDestinationIndexProperty(2, "bottomNavigation.selectedDestination"),
                    components = listOf(
                        text(textProperty = TextProperty("Investimentos"))
                    ),
                    selectedIcon = listOf(
                        icon(iconName = IconNameProperty("Investment"))
                    ),
                    unselectedIcon = listOf(
                        icon(iconName = IconNameProperty("InvestmentOutline"))
                    )
                ),
            )
        )

        val topBar = topBar(
            components = listOf(
                text(
                    textProperty = TextProperty("Home", id = "bottomNavigation.selectedDestinationTitle"),
                    fontSizeProperty = FontSizeProperty(18)
                ),
            ),
            actionIcons = listOf(
                iconButton(
                    actions = listOf(
                        continueAction(
                            flowId = request.flow,
                            currentScreenId = screenId,
                            nextScreenId = "UserDetail",
                            screenData = request.screenData
                        ),
                    ),
                    components = listOf(
                        icon(
                            iconName = IconNameProperty("User")
                        )
                    )
                ),
            )
        )

        val content = sdUi(
            flow = FlowIdentifierProperty("Home"),
            stage = StageIdentifierProperty(
                "ContaCorrente",
                "bottomNavigation.selectedDestinationContent"
            ),
            currentScreen = CurrentScreenProperty("Home"),
            requestUpdate = RequestUpdateProperty(false, "requestUpdate"),
            horizontalFillType = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
            horizontalAlignment = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
            weight = WeightProperty(1),
            validators = listOf(
                intToStringValidator(
                    id = "bottomNavigation.selectedDestinationContent",
                    intToString = listOf(
                        0 to "ContaCorrente",
                        1 to "Cartoes",
                        2 to "Investimentos"
                    ),
                    required = listOf("bottomNavigation.selectedDestination")
                ),
                intToStringValidator(
                    id = "bottomNavigation.selectedDestinationTitle",
                    intToString = listOf(
                        0 to "Home",
                        1 to "Card",
                        2 to "Investment"
                    ),
                    required = listOf("bottomNavigation.selectedDestination")
                ),
            )
        )

        val screenObj = screen(
            flow = "Home",
            stage = "Home",
            version = "1",
            template = "",
            shouldCache = true,
            components = listOf(
                topBar,
                content,
                bottomNavigation
            )
        )
        return screenObj
    }

}