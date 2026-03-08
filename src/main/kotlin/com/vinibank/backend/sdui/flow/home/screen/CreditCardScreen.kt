package com.vinibank.backend.sdui.flow.home.screen

import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Card
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.LazyColumn
import com.vini.designsystemsdui.component.Row
import com.vini.designsystemsdui.component.SdUi
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.core.SdUiComposer
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.background
import com.vini.designsystemsdui.modifier.clip
import com.vini.designsystemsdui.modifier.fillMaxHeight
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.height
import com.vini.designsystemsdui.modifier.option.FontWeightOption
import com.vini.designsystemsdui.modifier.option.HorizontalArrangementOption
import com.vini.designsystemsdui.modifier.option.IconOption
import com.vini.designsystemsdui.modifier.option.ShapeOption
import com.vini.designsystemsdui.modifier.option.VerticalAlignmentOption
import com.vini.designsystemsdui.modifier.option.VerticalArrangementOption
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.size
import com.vini.designsystemsdui.modifier.width
import com.vini.designsystemsdui.property.options.ButtonColorsModel
import com.vini.designsystemsdui.property.options.CardColorsModel
import com.vini.designsystemsdui.property.options.color.ColorOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.sdui.flow.RoutingController
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class CreditCardScreen(
    @Lazy private val routingController: RoutingController,
) : HomeScreen {
    override val screenId: String = "Cartoes"

    override fun getScreen(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? {
        val background = ColorOption.CustomColor(0xff101922)
        val surface = ColorOption.CustomColor(0xf202B8CEE)
        val subtitleColor = ColorOption.CustomColor(0xff94A3B8)

        fun SdUiComposer.cardVisual(
            name: String,
            holder: String,
            number: String,
            date: String,
            cardColor: ColorOption,
            textColor: ColorOption,
            accentOne: ColorOption,
            accentTwo: ColorOption,
        ) = Card(
            modifier = SdUiModifier().fillMaxWidth(),
            shape = ShapeOption.RoundedCorner(16),
            colors = CardColorsModel(
                containerColor = cardColor,
                contentColor = textColor,
            ),
            content = {
                Column(
                    modifier = SdUiModifier().padding(16).height(170),
                    verticalArrangement = VerticalArrangementOption.SpaceBetween(),
                    content = {
                        Row(
                            modifier = SdUiModifier().fillMaxWidth(),
                            horizontalArrangement = HorizontalArrangementOption.SpaceBetween(),
                            verticalAlignment = VerticalAlignmentOption.Center(),
                            content = {
                                Icon(
                                    modifier = SdUiModifier().size(20),
                                    icon = IconOption.Card,
                                    tint = textColor,
                                )
                                Text(
                                    text = name,
                                    fontWeight = FontWeightOption.Bold,
                                    fontSize = 26f,
                                    lineHeight = 26f,
                                    color = textColor,
                                )
                            }
                        )
                        Row(
                            content = {
                                Column(
                                    modifier = SdUiModifier().size(16)
                                        .clip(shape = ShapeOption.Circle())
                                        .background(accentOne),
                                )
                                Spacer(modifier = SdUiModifier().width(6))
                                Column(
                                    modifier = SdUiModifier().size(16)
                                        .clip(shape = ShapeOption.Circle())
                                        .background(accentTwo),
                                )
                            }
                        )
                        Column(
                            content = {
                                Text(
                                    text = number,
                                    color = textColor,
                                    fontSize = 14f,
                                )
                                Spacer(modifier = SdUiModifier().size(8))
                                Row(
                                    modifier = SdUiModifier().fillMaxWidth(),
                                    horizontalArrangement = (
                                            HorizontalArrangementOption.SpaceBetween()
                                            ),
                                    content = {
                                        Text(
                                            text = holder,
                                            color = textColor,
                                            fontSize = 14f,
                                            fontWeight = FontWeightOption.SemiBold
                                        )
                                        Text(
                                            text = date,
                                            color = textColor,
                                            fontSize = 12f,
                                        )
                                    }
                                )
                            }
                        )
                    }
                )
            }
        )

        fun SdUiComposer.benefitItem(text: String, iconColor: ColorOption) = Row(
            verticalAlignment = VerticalAlignmentOption.Center(),
            content = {
                Icon(
                    modifier = SdUiModifier().size(16),
                    icon = IconOption.Check,
                    tint = iconColor,
                )
                Spacer(modifier = SdUiModifier().width(10))
                Text(
                    text = text,
                    color = ColorOption.White(),
                    fontSize = 14f,
                    lineHeight = 20f
                )
            }
        )

        fun SdUiComposer.benefitsList(items: List<String>, iconColor: ColorOption) = Column(
            verticalArrangement = VerticalArrangementOption.SpacedBy(10),
            content = {
                items.forEach { benefitItem(it, iconColor) }
            }
        )

        fun SdUiComposer.infoRow(label: String, value: String) = Column(
            content = {
                Text(
                    text = label,
                    color = subtitleColor,
                    fontSize = 12f
                )
                Spacer(modifier = SdUiModifier().size(2))
                Text(
                    text = value,
                    color = ColorOption.White(),
                    fontWeight = FontWeightOption.Bold,
                    fontSize = 16f
                )
            }
        )

        fun SdUiComposer.cardPlanSection(
            cardName: String,
            holder: String,
            number: String,
            date: String,
            benefitsTitle: String,
            benefits: List<String>,
            fee: String,
            limit: String,
            visualColor: ColorOption,
            visualTextColor: ColorOption,
            badgeOne: ColorOption,
            badgeTwo: ColorOption,
            benefitIconColor: ColorOption,
        ) = Card(
            modifier = SdUiModifier().fillMaxWidth(),
            shape = ShapeOption.RoundedCorner(16),
            colors = CardColorsModel(
                containerColor = surface,
                contentColor = ColorOption.White(),
            ),
            content = {
                Column(
                    modifier = SdUiModifier().padding(horizontal = 16).padding(vertical = 16),
                    verticalArrangement = VerticalArrangementOption.SpacedBy(14),
                    content = {
                        cardVisual(
                            name = cardName,
                            holder = holder,
                            number = number,
                            date = date,
                            cardColor = visualColor,
                            textColor = visualTextColor,
                            accentOne = badgeOne,
                            accentTwo = badgeTwo,
                        )
                        Text(
                            text = benefitsTitle,
                            color = ColorOption.CustomColor(0xffCBD5E1),
                            fontWeight = FontWeightOption.SemiBold,
                            fontSize = 14f
                        )
                        benefitsList(benefits, benefitIconColor)
                        Card(
                            modifier = SdUiModifier().fillMaxWidth(),
                            shape = ShapeOption.RoundedCorner(8),
                            colors = CardColorsModel(
                                containerColor = ColorOption.CustomColor(0xff0F172A),
                                contentColor = ColorOption.White(),
                            ),
                            content = {
                                Row(
                                    modifier = SdUiModifier().padding(horizontal = 14)
                                        .padding(vertical = 12).fillMaxWidth(),
                                    horizontalArrangement = HorizontalArrangementOption.SpaceBetween(),
                                    content = {
                                        infoRow("Annual Fee", fee)
                                        infoRow("Pre-approved Limit", limit)
                                    }
                                )
                            }
                        )
                        Button(
                            modifier = SdUiModifier().fillMaxWidth(),
                            shape = ShapeOption.RoundedCorner(8),
                            colors = ButtonColorsModel(
                                containerColor = ColorOption.White(),
                                contentColor = ColorOption.CustomColor(0xff111827),
                            ),
                            content = {
                                Text(
                                    modifier = SdUiModifier().padding(vertical = 6),
                                    text = "Choose this Card",
                                    fontSize = 16f,
                                    fontWeight = FontWeightOption.Bold,
                                )
                            }
                        )
                    }
                )
            }
        )

        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            content = {
                Column(
                    modifier = SdUiModifier().fillMaxWidth().background(background).fillMaxHeight(),
                    content = {
                        LazyColumn(
                            modifier = SdUiModifier().padding(horizontal = 12, vertical = 16)
                                .fillMaxWidth().fillMaxHeight(),
                            verticalArrangement = VerticalArrangementOption.SpacedBy(14),
                            content = {
                                Column(
                                    modifier = SdUiModifier().padding(horizontal = 8),
                                    content = {
                                        Text(
                                            text = "Choose your plan",
                                            color = ColorOption.White(),
                                            fontWeight = FontWeightOption.Bold,
                                            fontSize = 36f,
                                            lineHeight = 42f
                                        )
                                        Spacer(modifier = SdUiModifier().size(4))
                                        Text(
                                            text = (
                                                    "Select the credit card that best fits your financial goals."
                                                    ),
                                            color = subtitleColor,
                                            fontSize = 15f,
                                            lineHeight = 22f
                                        )
                                    }
                                )
                                cardPlanSection(
                                    cardName = "GOLD",
                                    holder = "RICARDO SILVA",
                                    number = "•••• •••• •••• 4211",
                                    date = "09/28",
                                    benefitsTitle = "EXCLUSIVE BENEFITS",
                                    benefits = listOf(
                                        "2% Cashback on all dining & grocery purchases",
                                        "Extended warranty protection",
                                        "No foreign transaction fees",
                                    ),
                                    fee = "R$ 30,00/mo",
                                    limit = "R$ 5.000,00",
                                    visualColor = ColorOption.CustomColor(0xffCE9C33),
                                    visualTextColor = ColorOption.CustomColor(0xffFDF4D6),
                                    badgeOne = ColorOption.CustomColor(0xffEF4444),
                                    badgeTwo = ColorOption.CustomColor(0xffFBBF24),
                                    benefitIconColor = ColorOption.CustomColor(0xffFDB931),
                                )
                                cardPlanSection(
                                    cardName = "PLATINUM",
                                    holder = "RICARDO SILVA",
                                    number = "•••• •••• •••• 8892",
                                    date = "11/29",
                                    benefitsTitle = "PREMIUM BENEFITS",
                                    benefits = listOf(
                                        "Global airport lounge access (2x/year)",
                                        "Travel & Rental car insurance",
                                        "Concierge service 24/7",
                                    ),
                                    fee = "R$ 85,00/mo",
                                    limit = "R$ 15.000,00",
                                    visualColor = ColorOption.CustomColor(0xffD4D4D8),
                                    visualTextColor = ColorOption.CustomColor(0xff334155),
                                    badgeOne = ColorOption.CustomColor(0xff9CA3AF),
                                    badgeTwo = ColorOption.CustomColor(0xffE5E7EB),
                                    benefitIconColor = ColorOption.CustomColor(0xffE2E8F0),
                                )
                                cardPlanSection(
                                    cardName = "INFINITE",
                                    holder = "RICARDO SILVA",
                                    number = "•••• •••• •••• 1001",
                                    date = "01/30",
                                    benefitsTitle = "ELITE BENEFITS",
                                    benefits = listOf(
                                        "Unlimited VIP Lounge access worldwide",
                                        "Personal banker & priority support",
                                        "3.5 points per dollar spent",
                                    ),
                                    fee = "R$ 190,00/mo",
                                    limit = "R$ 50.000,00+",
                                    visualColor = ColorOption.CustomColor(0xff111111),
                                    visualTextColor = ColorOption.CustomColor(0xffA1A1AA),
                                    badgeOne = ColorOption.CustomColor(0xff71717A),
                                    badgeTwo = ColorOption.CustomColor(0xff52525B),
                                    benefitIconColor = ColorOption.CustomColor(0xff94A3B8),
                                )
                            }
                        )
                    }
                )
            }
        )
    }

    fun getScreen1(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? = DefaultTemplate(
        flow = request.flow,
        stage = screenId,
        version = "1",
        content = {
            SdUi(
                modifier = SdUiModifier().fillMaxWidth(),
                flow = "Card",
                stage = "Start",
                currentScreen = screenId,
                template = routingController.getTemplate(
                    SdUiRequest(
                        flow = "Card",
                        fromScreen = screenId,
                        toScreen = "Start",
                        screenData = request.screenData
                    )
                )
            )
        }
    )
}
