package com.vinibank.backend.sdui.flow.home.screen

import com.vini.designsystemsdui.InteractionId
import com.vini.designsystemsdui.core.SdUiNode.Template
import com.vini.designsystemsdui.ui.action.CloseApplicationAction
import com.vini.designsystemsdui.ui.action.ToBooleanAction
import com.vini.designsystemsdui.ui.component.BackHandler
import com.vini.designsystemsdui.ui.component.BottomSheet
import com.vini.designsystemsdui.ui.data.BottomSheetInteractionModel
import com.vini.designsystemsdui.ui.component.Button
import com.vini.designsystemsdui.ui.component.Column
import com.vini.designsystemsdui.ui.component.Icon
import com.vini.designsystemsdui.ui.component.NavigationBar
import com.vini.designsystemsdui.ui.data.NavigationBarInteractionModel
import com.vini.designsystemsdui.ui.component.NavigationBarItem
import com.vini.designsystemsdui.ui.data.NavigationBarItemInteractionModel
import com.vini.designsystemsdui.ui.component.OutlinedButton
import com.vini.designsystemsdui.ui.component.SdUi
import com.vini.designsystemsdui.ui.data.SdUiInteractionModel
import com.vini.designsystemsdui.ui.component.Spacer
import com.vini.designsystemsdui.ui.component.Text
import com.vini.designsystemsdui.core.SdUiComposer
import com.vini.designsystemsdui.ui.modifier.Modifier
import com.vini.designsystemsdui.ui.modifier.background
import com.vini.designsystemsdui.ui.modifier.fillMaxSize
import com.vini.designsystemsdui.ui.modifier.fillMaxWidth
import com.vini.designsystemsdui.ui.modifier.height
import com.vini.designsystemsdui.ui.modifier.option.HorizontalAlignmentOption
import com.vini.designsystemsdui.ui.modifier.option.IconOption
import com.vini.designsystemsdui.ui.modifier.option.ShapeOption
import com.vini.designsystemsdui.ui.modifier.padding
import com.vini.designsystemsdui.ui.modifier.size
import com.vini.designsystemsdui.ui.modifier.style.NavigationBarItemColorsModel
import com.vini.designsystemsdui.ui.modifier.option.ColorOption
import com.vini.designsystemsdui.ui.template.ScreenTemplate
import com.vini.designsystemsdui.ui.validator.dynamicValidator
import com.vini.designsystemsdui.ui.validator.intToStringValidator
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
        val bottomNavigationId = InteractionId<Int>(id = "bottomNavigation.selectedDestination")

        val currentColor = InteractionId<ColorOption>(id = "bottomNavigation.salve")

        val showExitBottomSheetId = InteractionId<Boolean>("exitAppBackHandler")


        fun SdUiComposer.navigationItem(
            destinationIndex: Int,
            label: String,
            selectedIcon: IconOption,
            unselectedIcon: IconOption,
        ) {
            NavigationBarItem(
                index = destinationIndex,
                colors = NavigationBarItemColorsModel(
                    selectedIconColor = ColorOption.CustomColor(0xff2B8CEE),
                    selectedTextColor = ColorOption.CustomColor(0xff2B8CEE),
                    unselectedIconColor = ColorOption.CustomColor(0xff94A3B8),
                    unselectedTextColor = ColorOption.CustomColor(0xff94A3B8),
                    selectedIndicatorColor = ColorOption.CustomColor(0xff101922)
                ),
                interactionModel = NavigationBarItemInteractionModel(
                    selectedDestination = bottomNavigationId
                ),
                label = {
                    Text(text = label)
                },
                selectedIcon = {
                    Icon(icon = selectedIcon)
                },
                unselectedIcon = {
                    Icon(icon = unselectedIcon)
                },
            )
        }

        fun SdUiComposer.bottomNavigation() {
            NavigationBar(
                containerColor = ColorOption.CustomColor(0xff101922),
                contentColor = ColorOption.Blue(),
                selectedDestination = 0,
                interactionModel = NavigationBarInteractionModel(
                    selectedDestination = bottomNavigationId,
                    contentColor = currentColor
                ),
                content = {
                    navigationItem(0, "Home", IconOption.Home, IconOption.HomeOutline)
                    navigationItem(1, "Card", IconOption.Payment, IconOption.PaymentOutline)
                    navigationItem(
                        2,
                        "Investimentos",
                        IconOption.Investment,
                        IconOption.InvestmentOutline
                    )
                }
            )
        }

        val sdUiStageId = InteractionId<String>("bottomNavigation.selectedDestinationContent")
        return ScreenTemplate(
            flow = HomeController.FLOW_ID,
            stage = screenId,
            version = "1",
            content = {
                Column(
                    validators = listOf(
                        dynamicValidator(
                            id = showExitBottomSheetId,
                            toValidate = listOf(currentColor),
                            toCompare = ColorOption.Blue()
                        )
                    ),
                    modifier = Modifier.fillMaxSize()
                        .background(ColorOption.CustomColor(0xff101922)),
                    content = {
                        BackHandler(
                            enabled = true,
                            onBackAction = ToBooleanAction(showExitBottomSheetId, true)
                        )
                        BottomSheet(
                            isVisible = false,
                            interactionModel = BottomSheetInteractionModel(
                                isVisible = showExitBottomSheetId
                            ),
                            content = {
                                Column(
                                    modifier = Modifier.fillMaxWidth()
                                        .padding(horizontal = 10),
                                    horizontalAlignment = HorizontalAlignmentOption.Center(),
                                    content = {
                                        Text(text = "Tem certeza que deseja sair?")
                                        Spacer(modifier = Modifier.height(10))
                                        Button(
                                            modifier = Modifier.fillMaxWidth(),
                                            shape = ShapeOption.Rectangle(),
                                            content = {
                                                Text(text = "Sair do App")
                                            },
                                            onClickAction = CloseApplicationAction()
                                        )
                                        Spacer(modifier = Modifier.height(4))
                                        OutlinedButton(
                                            modifier = Modifier.fillMaxWidth(),
                                            shape = ShapeOption.Rectangle(),
                                            content = {
                                                Text(text = "Cancelar")
                                            },
                                            onClickAction = ToBooleanAction(
                                                showExitBottomSheetId,
                                                false
                                            )
                                        )
                                    }
                                )
                            }
                        )
                        Spacer(modifier = Modifier.size(36))
                        SdUi(
                            modifier = Modifier.weight(1f).fillMaxWidth(),
                            interactionModel = SdUiInteractionModel(
                                stage = sdUiStageId
                            ),
                            flow = "Home",
                            stage = "ContaCorrente",
                            currentScreen = screenId,
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
                            content = routingController.getTemplate(
                                request.copy(
                                    toScreen = "ContaCorrente"
                                )
                            )
                        )
                        Column(
                            modifier = Modifier.height(2).fillMaxWidth()
                                .background(ColorOption.CustomColor(0xff1E293B)),
                        )
                        bottomNavigation()
                    }
                )
            }
        )
    }
}
