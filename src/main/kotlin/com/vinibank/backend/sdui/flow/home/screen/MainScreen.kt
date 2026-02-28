package com.vinibank.backend.sdui.flow.home.screen

import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.CloseApplicationAction
import com.vini.designsystemsdui.action.ToBooleanAction
import com.vini.designsystemsdui.component.BackHandler
import com.vini.designsystemsdui.component.BottomSheet
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.NavigationBar
import com.vini.designsystemsdui.component.NavigationBarItem
import com.vini.designsystemsdui.component.OutlinedButton
import com.vini.designsystemsdui.component.SdUi
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.background
import com.vini.designsystemsdui.modifier.fillMaxHeight
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.height
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.size
import com.vini.designsystemsdui.property.ContainerColorProperty
import com.vini.designsystemsdui.property.ContentColorProperty
import com.vini.designsystemsdui.property.DestinationIndexProperty
import com.vini.designsystemsdui.property.EnabledProperty
import com.vini.designsystemsdui.property.FlowIdentifierProperty
import com.vini.designsystemsdui.property.FromScreenIdentifierProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.NavigationBarItemColorProperty
import com.vini.designsystemsdui.property.SelectedDestinationIndexProperty
import com.vini.designsystemsdui.property.ShapeProperty
import com.vini.designsystemsdui.property.StageIdentifierProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VisibilityProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.IconOption
import com.vini.designsystemsdui.property.options.NavigationBarItemColorsModel
import com.vini.designsystemsdui.property.options.ShapeOptions
import com.vini.designsystemsdui.property.options.color.ColorOption
import com.vini.designsystemsdui.property.util.PropertyIdWrapper
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vini.designsystemsdui.validator.intToStringValidator
import com.vinibank.backend.sdui.flow.RoutingController
import com.vinibank.backend.sdui.flow.home.HomeController
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class MainScreen(
    @Lazy private val routingController: RoutingController,
) : HomeScreen {
    override val screenId: String
        get() = "Start"

    override fun getScreen(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? {
        val bottomNavigationId = PropertyIdWrapper<Int>(id = "bottomNavigation.selectedDestination")

        val navItemColors = NavigationBarItemColorProperty(
            value = NavigationBarItemColorsModel(
                selectedIconColor = ColorOption.CustomColor(0xff2B8CEE),
                selectedTextColor = ColorOption.CustomColor(0xff2B8CEE),
                unselectedIconColor = ColorOption.CustomColor(0xff94A3B8),
                unselectedTextColor = ColorOption.CustomColor(0xff94A3B8),
                selectedIndicatorColor = ColorOption.CustomColor(0xff101922)
            )
        )

        val bottomNavigation = NavigationBar(
            containerColorProperty = ContainerColorProperty(ColorOption.CustomColor(0xff101922)),
            contentColorProperty = ContentColorProperty(ColorOption.Blue()),
            selectedDestinationIndexProperty = SelectedDestinationIndexProperty(
                value = 0,
                idWrapper = bottomNavigationId
            ),
            content = listOf(
                NavigationBarItem(
                    navigationBarItemColorProperty = navItemColors,
                    destinationIndexProperty = DestinationIndexProperty(0),
                    selectedDestinationIndexProperty = SelectedDestinationIndexProperty(
                        idWrapper = bottomNavigationId
                    ),
                    label = listOf(
                        Text(textProperty = TextProperty("Home"))
                    ),
                    selectedIcon = listOf(
                        Icon(iconNameProperty = IconNameProperty(IconOption.Home))
                    ),
                    unselectedIcon = listOf(
                        Icon(iconNameProperty = IconNameProperty(IconOption.HomeOutline))
                    ),
                ),
                NavigationBarItem(
                    navigationBarItemColorProperty = navItemColors,
                    destinationIndexProperty = DestinationIndexProperty(1),
                    selectedDestinationIndexProperty = SelectedDestinationIndexProperty(
                        idWrapper = bottomNavigationId
                    ),
                    label = listOf(
                        Text(textProperty = TextProperty("Card"))
                    ),
                    selectedIcon = listOf(
                        Icon(iconNameProperty = IconNameProperty(IconOption.Payment))
                    ),
                    unselectedIcon = listOf(
                        Icon(iconNameProperty = IconNameProperty(IconOption.PaymentOutline))
                    ),
                ),
                NavigationBarItem(
                    navigationBarItemColorProperty = navItemColors,
                    destinationIndexProperty = DestinationIndexProperty(2),
                    selectedDestinationIndexProperty = SelectedDestinationIndexProperty(
                        idWrapper = bottomNavigationId
                    ),
                    label = listOf(
                        Text(textProperty = TextProperty("Investimentos"))
                    ),
                    selectedIcon = listOf(
                        Icon(iconNameProperty = IconNameProperty(IconOption.Investment))
                    ),
                    unselectedIcon = listOf(
                        Icon(iconNameProperty = IconNameProperty(IconOption.InvestmentOutline))
                    ),
                ),
            )
        )

        val sdUiStageId = PropertyIdWrapper<String>("bottomNavigation.selectedDestinationContent")
        val content = SdUi(
            modifier = SdUiModifier().fillMaxWidth(),
            flowIdentifierProperty = FlowIdentifierProperty("Home"),
            stageIdentifierProperty = StageIdentifierProperty(
                "ContaCorrente",
                sdUiStageId
            ),
            fromScreenIdentifierProperty = FromScreenIdentifierProperty(screenId),
            weightProperty = WeightProperty(1f),
            validators = listOf(
                intToStringValidator(
                    idWrapper = sdUiStageId,
                    intToString = listOf(
                        0 to "ContaCorrente",
                        1 to "Cartoes",
                        2 to "Investimentos"
                    ),
                    required = listOf(bottomNavigationId)
                ),
            ),
            template = routingController.getTemplate(
                request.copy(
                    toScreen = "ContaCorrente"
                )
            )
        )

        val showExitBottomSheetId = PropertyIdWrapper<Boolean>("exitAppBackHandler")
        val screenObj = DefaultTemplate(
            flow = HomeController.FLOW_ID,
            stage = screenId,
            version = "1",
            content = listOf(
                Column(
                    modifier = SdUiModifier().fillMaxWidth().fillMaxHeight().background(
                        ColorOption.CustomColor(
                            0xff101922
                        )
                    ),
                    content = listOf(
                        BackHandler(
                            enabledProperty = EnabledProperty(true),
                            onBackAction = ToBooleanAction(showExitBottomSheetId, true)
                        ),
                        BottomSheet(
                            modifier = SdUiModifier(),
                            visibilityProperty = VisibilityProperty(false, showExitBottomSheetId),
                            content = listOf(
                                Column(
                                    modifier = SdUiModifier().fillMaxWidth()
                                        .padding(horizontal = 10),
                                    horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                        HorizontalAlignmentOption.Center
                                    ),
                                    content = listOf(
                                        Text(textProperty = TextProperty("Tem certeza que deseja sair?")),
                                        Spacer(modifier = SdUiModifier().height(10)),
                                        Button(
                                            modifier = SdUiModifier().fillMaxWidth(),
                                            shapeProperty = ShapeProperty(ShapeOptions.Large),
                                            content = listOf(
                                                Text(textProperty = TextProperty("Sair do App"))
                                            ),
                                            onClick = CloseApplicationAction()
                                        ),
                                        Spacer(modifier = SdUiModifier().height(4)),
                                        OutlinedButton(
                                            modifier = SdUiModifier().fillMaxWidth(),
                                            shapeProperty = ShapeProperty(ShapeOptions.Large),
                                            content = listOf(
                                                Text(textProperty = TextProperty("Cancelar"))
                                            ),
                                            onClick = ToBooleanAction(showExitBottomSheetId, false)
                                        )
                                    )
                                )
                            )
                        ),
                        //topAppBar,
                        Spacer(modifier = SdUiModifier().size(36)),
                        content,
                        Column(
                            modifier = SdUiModifier().height(2).fillMaxWidth().background(
                                ColorOption.CustomColor(
                                    0xff1E293B
                                )
                            ),
                        ),
                        bottomNavigation
                    )
                ),
            )
        )
        return screenObj
    }
}
