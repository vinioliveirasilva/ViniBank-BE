package com.vinibank.backend.sdui.flow.home.screen

import com.vini.designsystemsdui.InteractionId
import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.BaseAction
import com.vini.designsystemsdui.action.CloseApplicationAction
import com.vini.designsystemsdui.action.NavigateAction
import com.vini.designsystemsdui.action.ToBooleanAction
import com.vini.designsystemsdui.component.BackHandler
import com.vini.designsystemsdui.component.BottomSheet
import com.vini.designsystemsdui.component.BottomSheetInteractionModel
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Card
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.Dialog
import com.vini.designsystemsdui.component.DialogInteractionModel
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.IconButton
import com.vini.designsystemsdui.component.Image
import com.vini.designsystemsdui.component.LazyColumn
import com.vini.designsystemsdui.component.Row
import com.vini.designsystemsdui.component.SdUi
import com.vini.designsystemsdui.component.SdUiInteractionModel
import com.vini.designsystemsdui.component.SnackBar
import com.vini.designsystemsdui.component.SnackBarInteractionModel
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.core.SdUiComposer
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.background
import com.vini.designsystemsdui.modifier.clickable
import com.vini.designsystemsdui.modifier.clip
import com.vini.designsystemsdui.modifier.fillMaxHeight
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.height
import com.vini.designsystemsdui.modifier.option.FontWeightOption
import com.vini.designsystemsdui.modifier.option.HorizontalAlignmentOption
import com.vini.designsystemsdui.modifier.option.HorizontalArrangementOption
import com.vini.designsystemsdui.modifier.option.IconOption
import com.vini.designsystemsdui.modifier.option.ShapeOption
import com.vini.designsystemsdui.modifier.option.TextAlignOption
import com.vini.designsystemsdui.modifier.option.VerticalAlignmentOption
import com.vini.designsystemsdui.modifier.option.VerticalArrangementOption
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.size
import com.vini.designsystemsdui.modifier.width
import com.vini.designsystemsdui.modifier.wrapContentSize
import com.vini.designsystemsdui.property.options.CardColorsModel
import com.vini.designsystemsdui.property.options.color.ColorOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.db.CheckingAccountDatabase
import com.vinibank.backend.db.CheckingAccountTransaction
import com.vinibank.backend.db.NotificationsDatabase
import com.vinibank.backend.db.TransactionCategory
import com.vinibank.backend.db.UserLoginDb
import com.vinibank.backend.sdui.flow.RoutingController
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.flow.investments.toBrl
import com.vinibank.backend.sdui.model.SdUiRequest
import com.vinibank.backend.util.DateParserPtBr
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class CheckingAccountScreen(
    @Lazy private val routingController: RoutingController,
    @Lazy private val notificationsDatabase: NotificationsDatabase,
    @Lazy private val userLoginDb: UserLoginDb,
    @Lazy private val transactionHistoryDatabase: CheckingAccountDatabase,
) : HomeScreen {

    companion object {
        val checkingAccountTopSdUiRequestUpdate = InteractionId<Boolean>("requestUpdate1")
    }

    override val screenId: String
        get() = "ContaCorrente"

    override fun getScreen(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? {
        val textColor = ColorOption.White()
        val showDialogId = InteractionId<Boolean>("123abc")
        val showBottomSheetId = InteractionId<Boolean>("123abc1")
        val showSnackBarId = InteractionId<Boolean>("123123")

        val userEmail = userLoginDb.getUserEmail(request.sessionId)
        val hasUnreadNotification = notificationsDatabase.hasUnreadNotification(userEmail)
        val transactions = transactionHistoryDatabase.get(userEmail)

        fun parseTransactionDateAndTime(transaction: CheckingAccountTransaction): String {
            return "${DateParserPtBr.parse(transaction.transactionDate)} • ${transaction.transactionTime}"
        }

        fun getTransactionIcon(transaction: CheckingAccountTransaction): IconOption {
            return when (transaction.category) {
                TransactionCategory.MARKET -> IconOption.ShoppingBag
                TransactionCategory.PHARMACY -> IconOption.MedicalServices
                TransactionCategory.SALARY -> IconOption.Money
                TransactionCategory.PIX -> IconOption.Money
                TransactionCategory.STREAMING -> IconOption.VideoLibrary
                TransactionCategory.TRANSPORT -> IconOption.DirectionsCar
                TransactionCategory.FOOD -> IconOption.Fastfood
                TransactionCategory.UTILITIES -> IconOption.Handyman
                TransactionCategory.OTHER -> IconOption.Receipt
            }
        }

        fun SdUiComposer.notificationIcon() {
            Icon(
                tint = ColorOption.White(),
                icon = IconOption.Notification
            )
            if (hasUnreadNotification) {
                Column(
                    modifier = SdUiModifier().fillMaxWidth().padding(horizontal = 10),
                    horizontalAlignment = HorizontalAlignmentOption.End(),
                    content = {
                        Column(
                            modifier = SdUiModifier().height(8).width(8)
                                .clip(ShapeOption.Circle())
                                .background(ColorOption.Red()),
                        )
                        Spacer(modifier = SdUiModifier().size(10))
                    }
                )
            }
        }

        fun SdUiComposer.transactionItem(transaction: CheckingAccountTransaction) = Card(
            modifier = SdUiModifier().fillMaxWidth().clickable(
                enabled = true, action = NavigateAction(flow = "Notification")
            ),
            colors = CardColorsModel(
                containerColor = ColorOption.CustomColor(0x881E293B),
                contentColor = ColorOption.White(),
            ),
            content = {
                Row(
                    modifier = SdUiModifier().padding(10).fillMaxWidth(),
                    verticalAlignment = VerticalAlignmentOption.Center(),
                    horizontalArrangement = HorizontalArrangementOption.SpaceBetween(),
                    content = {
                        Row(
                            modifier = SdUiModifier().weight(5f),
                            verticalAlignment = VerticalAlignmentOption.Center(),
                            content = {
                                Card(
                                    modifier = SdUiModifier().size(40),
                                    colors = CardColorsModel(
                                        containerColor = ColorOption.CustomColor(0x1A2B8CEE),
                                        contentColor = ColorOption.CustomColor(0xff2B8CEE),
                                    ),
                                    content = {
                                        Icon(
                                            modifier = SdUiModifier().padding(10),
                                            icon = getTransactionIcon(transaction),
                                        )
                                    }
                                )
                                Spacer(modifier = SdUiModifier().size(12))
                                Column(
                                    content = {
                                        Text(
                                            fontWeight = FontWeightOption.Bold,
                                            text = transaction.establishmentName,
                                            textAlign = TextAlignOption.Start,
                                        )
                                        Text(
                                            fontSize = 11f,
                                            color = ColorOption.LightGray(),
                                            text = parseTransactionDateAndTime(transaction),
                                            textAlign = TextAlignOption.Center,
                                        )
                                    }
                                )
                            },
                        )
                        Row(
                            modifier = SdUiModifier().padding(vertical = 10).padding(start = 10).wrapContentSize(),
                            verticalAlignment = VerticalAlignmentOption.Center(),
                            content = {
                                Text(
                                    fontWeight = FontWeightOption.Bold,
                                    color = if (transaction.amount > 0) {
                                        ColorOption.CustomColor(0xff10B981)
                                    } else {
                                        ColorOption.CustomColor(0xffEF4444)
                                    },
                                    text = transaction.amount.toBrl(),
                                    textAlign = TextAlignOption.End,
                                )
                                Spacer(modifier = SdUiModifier().size(8))
                            }
                        )
                    }
                )
            }
        )

        fun SdUiComposer.actionIcon(
            name: String,
            icon: IconOption,
            onClickAction: BaseAction = ToBooleanAction(
                idToChange = showSnackBarId,
                newValue = true
            ),
        ) = Column(
            horizontalAlignment = HorizontalAlignmentOption.Center(),
            content = {
                Card(
                    modifier = SdUiModifier().size(56).clickable(action = onClickAction),
                    colors = CardColorsModel(
                        containerColor = ColorOption.CustomColor(0x1A2B8CEE),
                        contentColor = ColorOption.CustomColor(0xff2B8CEE),
                    ),
                    shape = ShapeOption.RoundedCorner(16),
                    content = {
                        Row(
                            modifier = SdUiModifier().fillMaxWidth(),
                            horizontalArrangement = HorizontalArrangementOption.Center(),
                            content = {
                                Column(
                                    modifier = SdUiModifier().fillMaxHeight(),
                                    verticalArrangement = VerticalArrangementOption.Center(),
                                    content = {
                                        Icon(
                                            icon = icon,
                                            modifier = SdUiModifier().size(20),
                                        )
                                    }
                                )
                            }
                        )
                    },
                )
                Text(
                    modifier = SdUiModifier().padding(vertical = 10),
                    color = textColor,
                    text = name,
                    fontSize = 11f
                )
            },
        )

        fun SdUiComposer.profileSection() = Row(
            modifier = SdUiModifier().fillMaxWidth().padding(20),
            horizontalArrangement = HorizontalArrangementOption.SpaceBetween(),
            content = {
                Row(
                    verticalAlignment = VerticalAlignmentOption.Center(),
                    content = {
                        Image(
                            icon = IconOption.User,
                            modifier = SdUiModifier().size(48),
                            tint = ColorOption.White()
                        )
                        Spacer(modifier = SdUiModifier().width(5))
                        Column(
                            verticalArrangement = VerticalArrangementOption.Center(),
                            content = {
                                Text(
                                    fontWeight = FontWeightOption.Bold,
                                    text = "Nome do cliente",
                                    color = textColor,
                                )
                                Text(
                                    fontSize = 11f,
                                    color = textColor,
                                    text = "Nivel do cliente",
                                    lineHeight = 12f
                                )
                            }
                        )
                    }
                )
                Column(
                    modifier = SdUiModifier().clip(ShapeOption.Circle())
                        .background(ColorOption.CustomColor(0xff1E293B)),
                    content = {
                        IconButton(
                            content = {
                                notificationIcon()
                            },
                            onClickAction = NavigateAction(flow = "Notification")
                        )
                    }
                )
            }
        )

        fun SdUiComposer.balanceSection() = SdUi(
            modifier = SdUiModifier().fillMaxWidth(),
            flow = "Home",
            stage = "Balance",
            currentScreen = screenId,
            interactionModel = SdUiInteractionModel(
                requestUpdate = checkingAccountTopSdUiRequestUpdate
            ),
            template = routingController.getTemplate(request.copy(toScreen = "Balance"))
        )

        fun SdUiComposer.servicesSection() {
            Row(
                modifier = SdUiModifier().fillMaxWidth(),
                horizontalArrangement = HorizontalArrangementOption.SpaceBetween(),
                content = {
                    Text(
                        color = ColorOption.LightGray(),
                        text = "SERVIÇOS",
                        fontSize = 14f,
                        fontWeight = FontWeightOption.SemiBold
                    )
                    Text(
                        color = ColorOption.LightBlue(),
                        text = "Mostrar todos",
                        fontSize = 12f,
                        fontWeight = FontWeightOption.SemiBold
                    )
                }
            )
            Spacer(modifier = SdUiModifier().size(16))
            Row(
                modifier = SdUiModifier().fillMaxWidth(),
                horizontalArrangement = HorizontalArrangementOption.SpaceBetween(),
                content = {
                    actionIcon(
                        "Cards",
                        IconOption.Money,
                        ToBooleanAction(
                            idToChange = showDialogId,
                            newValue = true
                        )
                    )
                    actionIcon("Savings", IconOption.Wallet)
                    actionIcon("Loans", IconOption.Money)
                    actionIcon(
                        "Insurance",
                        IconOption.Shield,
                        ToBooleanAction(
                            idToChange = showBottomSheetId,
                            newValue = true
                        )
                    )
                }
            )
        }

        fun SdUiComposer.lastTransactionsSection() {
            Row(
                modifier = SdUiModifier().fillMaxWidth(),
                horizontalArrangement = HorizontalArrangementOption.SpaceBetween(),
                content = {
                    Text(
                        color = ColorOption.LightGray(),
                        text = "RECENT TRANSACTIONS",
                        fontSize = 14f,
                        fontWeight = FontWeightOption.SemiBold
                    )
                }
            )
            Column(
                verticalArrangement = VerticalArrangementOption.SpacedBy(16),
                content = {
                    transactions.forEach { transactionItem(it) }
                }
            )
            Spacer(modifier = SdUiModifier().size(8))
        }

        return DefaultTemplate(
            flow = request.flow,
            stage = request.toScreen,
            version = "1",
            content = {
                BackHandler(onBackAction = CloseApplicationAction())
                Column(
                    modifier = SdUiModifier().fillMaxWidth()
                        .background(ColorOption.CustomColor(0xff101922))
                        .fillMaxHeight(),
                    horizontalAlignment = HorizontalAlignmentOption.Center(),
                    content = {
                        profileSection()
                        Spacer(modifier = SdUiModifier().size(10))
                        Column(
                            modifier = SdUiModifier().fillMaxWidth().fillMaxHeight(),
                            horizontalAlignment = HorizontalAlignmentOption.Center(),
                            content = {
                                Dialog(
                                    isVisible = false,
                                    interactionModel = DialogInteractionModel(
                                        isVisible = showDialogId
                                    )
                                )
                                BottomSheet(
                                    isVisible = false,
                                    interactionModel = BottomSheetInteractionModel(
                                        isVisible = showBottomSheetId
                                    ),
                                    content = {
                                        Button(
                                            content = {
                                                Text(text = "Balance")
                                            },
                                            onClickAction = ToBooleanAction(
                                                idToChange = showDialogId,
                                                newValue = true
                                            )
                                        )
                                        Text(text = "R$ 100,00")
                                        Text(text = "updated 10 min ago")
                                    }
                                )
                                LazyColumn(
                                    modifier = SdUiModifier().fillMaxWidth().fillMaxHeight(),
                                    horizontalAlignment = HorizontalAlignmentOption.Center(),
                                    content = {
                                        Column(
                                            modifier = SdUiModifier().padding(horizontal = 24),
                                            content = {
                                                Spacer(modifier = SdUiModifier().size(20))
                                                balanceSection()
                                                Spacer(modifier = SdUiModifier().size(10))
                                            }
                                        )
                                        Column(
                                            modifier = SdUiModifier().padding(horizontal = 24)
                                                .padding(vertical = 10).fillMaxWidth(),
                                            content = {
                                                servicesSection()
                                            }
                                        )
                                        Column(
                                            modifier = SdUiModifier().padding(horizontal = 24)
                                                .fillMaxWidth(),
                                            horizontalAlignment = HorizontalAlignmentOption.Center(),
                                            verticalArrangement = VerticalArrangementOption.SpacedBy(
                                                16
                                            ),
                                            content = {
                                                lastTransactionsSection()
                                            }
                                        )
                                    }
                                )
                                SnackBar(
                                    text = "SnackBar",
                                    isVisible = false,
                                    interactionModel = SnackBarInteractionModel(
                                        isVisible = showSnackBarId
                                    )
                                )
                            }
                        )
                    }
                )
            }
        )
    }
}
