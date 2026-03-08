package com.vinibank.backend.sdui.flow.home.screen

import com.vini.designsystemsdui.InteractionId
import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.CloseApplicationAction
import com.vini.designsystemsdui.action.ToBooleanAction
import com.vini.designsystemsdui.component.BackHandler
import com.vini.designsystemsdui.component.BottomSheet
import com.vini.designsystemsdui.component.BottomSheetInteractionModel
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.NavigationBar
import com.vini.designsystemsdui.component.NavigationBarInteractionModel
import com.vini.designsystemsdui.component.NavigationBarItem
import com.vini.designsystemsdui.component.NavigationBarItemInteractionModel
import com.vini.designsystemsdui.component.OutlinedButton
import com.vini.designsystemsdui.component.SdUi
import com.vini.designsystemsdui.component.SdUiInteractionModel
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.core.SdUiComposer
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.background
import com.vini.designsystemsdui.modifier.fillMaxSize
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.height
import com.vini.designsystemsdui.modifier.option.HorizontalAlignmentOption
import com.vini.designsystemsdui.modifier.option.IconOption
import com.vini.designsystemsdui.modifier.option.ShapeOption
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.size
import com.vini.designsystemsdui.property.options.NavigationBarItemColorsModel
import com.vini.designsystemsdui.property.options.color.ColorOption
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
        val bottomNavigationId = InteractionId<Int>(id = "bottomNavigation.selectedDestination")

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
                    selectedDestination = bottomNavigationId
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

        val showExitBottomSheetId = InteractionId<Boolean>("exitAppBackHandler")
        return DefaultTemplate(
            flow = HomeController.FLOW_ID,
            stage = screenId,
            version = "1",
            content = {
                Column(
                    modifier = SdUiModifier().fillMaxSize()
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
                                    modifier = SdUiModifier().fillMaxWidth()
                                        .padding(horizontal = 10),
                                    horizontalAlignment = HorizontalAlignmentOption.Center(),
                                    content = {
                                        Text(text = "Tem certeza que deseja sair?")
                                        Spacer(modifier = SdUiModifier().height(10))
                                        Button(
                                            modifier = SdUiModifier().fillMaxWidth(),
                                            shape = ShapeOption.Rectangle(),
                                            content = {
                                                Text(text = "Sair do App")
                                            },
                                            onClickAction = CloseApplicationAction()
                                        )
                                        Spacer(modifier = SdUiModifier().height(4))
                                        OutlinedButton(
                                            modifier = SdUiModifier().fillMaxWidth(),
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
                        Spacer(modifier = SdUiModifier().size(36))
                        SdUi(
                            modifier = SdUiModifier().weight(1f).fillMaxWidth(),
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
                            template = routingController.getTemplate(
                                request.copy(
                                    toScreen = "ContaCorrente"
                                )
                            )
                        )
                        Column(
                            modifier = SdUiModifier().height(2).fillMaxWidth()
                                .background(ColorOption.CustomColor(0xff1E293B)),
                        )
                        bottomNavigation()
                    }
                )
            }
        )
    }
}
