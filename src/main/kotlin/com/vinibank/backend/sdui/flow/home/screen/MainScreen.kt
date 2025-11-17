package com.vinibank.backend.sdui.flow.home.screen

import com.vini.designsystemsdui.action.closeApplicationAction
import com.vini.designsystemsdui.action.continueAction
import com.vini.designsystemsdui.action.toBooleanAction
import com.vini.designsystemsdui.component.BackHandler
import com.vini.designsystemsdui.component.BottomSheet
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.IconButton
import com.vini.designsystemsdui.component.NavigationBar
import com.vini.designsystemsdui.component.NavigationBarItem
import com.vini.designsystemsdui.component.OutlinedButton
import com.vini.designsystemsdui.component.SdUi
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.component.TopAppBar
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
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vini.designsystemsdui.template.Template
import com.vini.designsystemsdui.validator.intToStringValidator
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component

@Component
class MainScreen : HomeScreen {
    override val screenId: String
        get() = "Home"

    override fun getScreen(request: SdUiRequest): Template? {
        val bottomNavigation = NavigationBar(
            selectedDestinationIndexProperty = SelectedDestinationIndexProperty(
                value = 0,
                id = "bottomNavigation.selectedDestination"
            ),
            content = listOf(
                NavigationBarItem(
                    destinationIndexProperty = DestinationIndexProperty(0),
                    selectedDestinationIndexProperty = SelectedDestinationIndexProperty(
                        id = "bottomNavigation.selectedDestination"
                    ),
                    label = listOf(
                        Text(textProperty = TextProperty("Home"))
                    ),
                    selectedIcon = listOf(
                        Icon(iconNameProperty = IconNameProperty("Home"))
                    ),
                    unselectedIcon = listOf(
                        Icon(iconNameProperty = IconNameProperty("HomeOutline"))
                    )
                ),
                NavigationBarItem(
                    destinationIndexProperty = DestinationIndexProperty(1),
                    selectedDestinationIndexProperty = SelectedDestinationIndexProperty(
                        id = "bottomNavigation.selectedDestination"
                    ),
                    label = listOf(
                        Text(textProperty = TextProperty("Card"))
                    ),
                    selectedIcon = listOf(
                        Icon(iconNameProperty = IconNameProperty("Payment"))
                    ),
                    unselectedIcon = listOf(
                        Icon(iconNameProperty = IconNameProperty("PaymentOutline"))
                    )
                ),
                NavigationBarItem(
                    destinationIndexProperty = DestinationIndexProperty(2),
                    selectedDestinationIndexProperty = SelectedDestinationIndexProperty(
                        id = "bottomNavigation.selectedDestination"
                    ),
                    label = listOf(
                        Text(textProperty = TextProperty("Investimentos"))
                    ),
                    selectedIcon = listOf(
                        Icon(iconNameProperty = IconNameProperty("Investment"))
                    ),
                    unselectedIcon = listOf(
                        Icon(iconNameProperty = IconNameProperty("InvestmentOutline"))
                    )
                ),
            )
        )

        val TopAppBar = TopAppBar(
            title = listOf(
                Text(
                    textProperty = TextProperty(
                        "Home",
                        id = "bottomNavigation.selectedDestinationTitle"
                    ),
                    fontSizeProperty = FontSizeProperty(18f),
                ),
            ),
            actions = listOf(
                IconButton(
                    onClick = continueAction(
                        flowId = request.flow,
                        currentScreenId = screenId,
                        nextScreenId = "UserDetail",
                        screenData = request.screenData
                    ),
                    content = listOf(
                        Icon(
                            iconNameProperty = IconNameProperty("User")
                        )
                    )
                ),
            )
        )

        val content = SdUi(
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

        val screenObj = DefaultTemplate(
            flow = "Home",
            stage = "Home",
            version = "1",
            template = "",
            content = listOf(
                BackHandler(
                    enabledProperty = EnabledProperty(true),
                    onBackAction = toBooleanAction("exitAppBackHandler", true)
                ),
                BottomSheet(
                    visibilityProperty = VisibilityProperty(false, "exitAppBackHandler"),
                    content = listOf(
                        Column(
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                HorizontalFillTypeOption.Max
                            ),
                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                HorizontalAlignmentOption.Center
                            ),
                            paddingHorizontalProperty = PaddingHorizontalProperty(10),
                            content = listOf(
                                Text(textProperty = TextProperty("Tem certeza que deseja sair?")),
                                Spacer(heightProperty = HeightProperty(10)),
                                Button(
                                    shapeProperty = ShapeProperty(ShapeOptions.Large),
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    content = listOf(
                                        Text(textProperty = TextProperty("Sair do App"))
                                    ),
                                    onClick = closeApplicationAction()
                                ),
                                Spacer(heightProperty = HeightProperty(4)),
                                OutlinedButton(
                                    shapeProperty = ShapeProperty(ShapeOptions.Large),
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    content = listOf(
                                        Text(textProperty = TextProperty("Cancelar"))
                                    ),
                                    onClick = toBooleanAction("exitAppBackHandler", false)
                                )
                            )
                        )
                    )
                ),
                TopAppBar,
                content,
                bottomNavigation
            )
        )
        return screenObj
    }

}