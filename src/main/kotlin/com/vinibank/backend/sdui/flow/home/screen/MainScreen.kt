package com.vinibank.backend.sdui.flow.home.screen

import com.vini.designsystemsdui.action.closeApplicationAction
import com.vini.designsystemsdui.action.continueAction
import com.vini.designsystemsdui.action.toBooleanAction
import com.vini.designsystemsdui.component.backHandler
import com.vini.designsystemsdui.component.bottomSheet
import com.vini.designsystemsdui.component.button
import com.vini.designsystemsdui.component.column
import com.vini.designsystemsdui.component.icon
import com.vini.designsystemsdui.component.iconButton
import com.vini.designsystemsdui.component.navigationBar
import com.vini.designsystemsdui.component.navigationBarItem
import com.vini.designsystemsdui.component.outlinedButton
import com.vini.designsystemsdui.component.sdUi
import com.vini.designsystemsdui.component.spacer
import com.vini.designsystemsdui.component.text
import com.vini.designsystemsdui.component.topAppBar
import com.vini.designsystemsdui.property.DestinationIndexProperty
import com.vini.designsystemsdui.property.EnabledProperty
import com.vini.designsystemsdui.property.FlowIdentifierProperty
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.FromScreenIdentifierProperty
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.RequestUpdateProperty
import com.vini.designsystemsdui.property.SelectedDestinationIndexProperty
import com.vini.designsystemsdui.property.ShapeProperty
import com.vini.designsystemsdui.property.StageIdentifierProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VisibilityProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.ShapeOptions
import com.vini.designsystemsdui.validator.intToStringValidator
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import com.vinibank.backend.sdui.oldflow.ScreenUtil.screen
import kotlinx.serialization.json.JsonObject
import org.springframework.stereotype.Component

@Component
class MainScreen : HomeScreen {
    override val screenId: String
        get() = "Home"

    override fun getScreen(request: SdUiRequest): JsonObject? {
        val bottomNavigation = navigationBar(
            selectedDestinationIndexProperty = SelectedDestinationIndexProperty(
                value = 0,
                id = "bottomNavigation.selectedDestination"
            ),
            content = listOf(
                navigationBarItem(
                    destinationIndexProperty = DestinationIndexProperty(0),
                    selectedDestinationIndexProperty = SelectedDestinationIndexProperty(
                        id = "bottomNavigation.selectedDestination"
                    ),
                    label = listOf(
                        text(textProperty = TextProperty("Home"))
                    ),
                    selectedIcon = listOf(
                        icon(iconNameProperty = IconNameProperty("Home"))
                    ),
                    unselectedIcon = listOf(
                        icon(iconNameProperty = IconNameProperty("HomeOutline"))
                    )
                ),
                navigationBarItem(
                    destinationIndexProperty = DestinationIndexProperty(1),
                    selectedDestinationIndexProperty = SelectedDestinationIndexProperty(
                        id = "bottomNavigation.selectedDestination"
                    ),
                    label = listOf(
                        text(textProperty = TextProperty("Card"))
                    ),
                    selectedIcon = listOf(
                        icon(iconNameProperty = IconNameProperty("Payment"))
                    ),
                    unselectedIcon = listOf(
                        icon(iconNameProperty = IconNameProperty("PaymentOutline"))
                    )
                ),
                navigationBarItem(
                    destinationIndexProperty = DestinationIndexProperty(2),
                    selectedDestinationIndexProperty = SelectedDestinationIndexProperty(
                        id = "bottomNavigation.selectedDestination"
                    ),
                    label = listOf(
                        text(textProperty = TextProperty("Investimentos"))
                    ),
                    selectedIcon = listOf(
                        icon(iconNameProperty = IconNameProperty("Investment"))
                    ),
                    unselectedIcon = listOf(
                        icon(iconNameProperty = IconNameProperty("InvestmentOutline"))
                    )
                ),
            )
        )

        val topAppBar = topAppBar(
            title = listOf(
                text(
                    textProperty = TextProperty(
                        "Home",
                        id = "bottomNavigation.selectedDestinationTitle"
                    ),
                    fontSizeProperty = FontSizeProperty(18f),
                ),
            ),
            actions = listOf(
                iconButton(
                    onClick = continueAction(
                        flowId = request.flow,
                        currentScreenId = screenId,
                        nextScreenId = "UserDetail",
                        screenData = request.screenData
                    ),
                    content = listOf(
                        icon(
                            iconNameProperty = IconNameProperty("User")
                        )
                    )
                ),
            )
        )

        val content = sdUi(
            flowIdentifierProperty = FlowIdentifierProperty("Home"),
            stageIdentifierProperty = StageIdentifierProperty(
                "ContaCorrente",
                "bottomNavigation.selectedDestinationContent"
            ),
            fromScreenIdentifierProperty = FromScreenIdentifierProperty("Home"),
            requestUpdateProperty = RequestUpdateProperty(false, "requestUpdate"),
            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
            weightProperty = WeightProperty(1f),
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
            shouldCache = false,
            content = listOf(
                backHandler(
                    enabledProperty = EnabledProperty(true),
                    onBackAction = toBooleanAction("exitAppBackHandler", true)
                ),
                bottomSheet(
                    visibilityProperty = VisibilityProperty(false, "exitAppBackHandler"),
                    content = listOf(
                        column(
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                HorizontalFillTypeOption.Max
                            ),
                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                HorizontalAlignmentOption.Center
                            ),
                            paddingHorizontalProperty = PaddingHorizontalProperty(10),
                            content = listOf(
                                text(textProperty = TextProperty("Tem certeza que deseja sair?")),
                                spacer(heightProperty = HeightProperty(10)),
                                button(
                                    shapeProperty = ShapeProperty(ShapeOptions.Large),
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    content = listOf(
                                        text(textProperty = TextProperty("Sair do App"))
                                    ),
                                    onClick = closeApplicationAction()
                                ),
                                spacer(heightProperty = HeightProperty(4)),
                                outlinedButton(
                                    shapeProperty = ShapeProperty(ShapeOptions.Large),
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    content = listOf(
                                        text(textProperty = TextProperty("Cancelar"))
                                    ),
                                    onClick = toBooleanAction("exitAppBackHandler", false)
                                )
                            )
                        )
                    )
                ),
                topAppBar,
                content,
                bottomNavigation
            )
        )
        return screenObj
    }

}