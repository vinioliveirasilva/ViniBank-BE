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
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.background
import com.vini.designsystemsdui.modifier.clip
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.height
import com.vini.designsystemsdui.modifier.option.ShapeOption
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.size
import com.vini.designsystemsdui.modifier.width
import com.vini.designsystemsdui.property.ButtonColorsProperty
import com.vini.designsystemsdui.property.CardColorsProperty
import com.vini.designsystemsdui.property.ColorProperty
import com.vini.designsystemsdui.property.FlowIdentifierProperty
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.FontWeightProperty
import com.vini.designsystemsdui.property.FromScreenIdentifierProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.LineHeightProperty
import com.vini.designsystemsdui.property.ShapeProperty
import com.vini.designsystemsdui.property.StageIdentifierProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.TintProperty
import com.vini.designsystemsdui.property.VerticalAlignmentProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.ButtonColorsModel
import com.vini.designsystemsdui.property.options.CardColorsModel
import com.vini.designsystemsdui.property.options.FontWeightOption
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.IconOption
import com.vini.designsystemsdui.property.options.ShapeOptions
import com.vini.designsystemsdui.property.options.VerticalAlignmentOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
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

        fun cardVisual(
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
            shapeProperty = ShapeProperty(ShapeOptions.Large),
            cardColorsProperty = CardColorsProperty(
                value = CardColorsModel(
                    containerColor = cardColor,
                    contentColor = textColor,
                )
            ),
            content = listOf(
                Column(
                    modifier = SdUiModifier().padding(horizontal = 16).padding(vertical = 16)
                        .height(170),
                    verticalArrangementProperty = VerticalArrangementProperty(
                        VerticalArrangementOption.SpaceBetween
                    ),
                    content = listOf(
                        Row(
                            modifier = SdUiModifier().fillMaxWidth(),
                            horizontalArrangementProperty = HorizontalArrangementProperty(
                                HorizontalArrangementOption.SpaceBetween
                            ),
                            verticalAlignmentProperty = VerticalAlignmentProperty(
                                VerticalAlignmentOption.Center
                            ),
                            content = listOf(
                                Icon(
                                    modifier = SdUiModifier().size(20),
                                    iconNameProperty = IconNameProperty(IconOption.Card),
                                    tintProperty = TintProperty(textColor),
                                ),
                                Text(
                                    textProperty = TextProperty(name),
                                    fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                                    fontSizeProperty = FontSizeProperty(26f),
                                    lineHeightProperty = LineHeightProperty(26),
                                    colorProperty = ColorProperty(textColor),
                                ),
                            )
                        ),
                        Row(
                            content = listOf(
                                Column(
                                    modifier = SdUiModifier().size(16)
                                        .clip(shape = ShapeOption.Circle())
                                        .background(accentOne),
                                ),
                                Spacer(modifier = SdUiModifier().width(6)),
                                Column(
                                    modifier = SdUiModifier().size(16)
                                        .clip(shape = ShapeOption.Circle())
                                        .background(accentTwo),
                                ),
                            )
                        ),
                        Column(
                            content = listOf(
                                Text(
                                    textProperty = TextProperty(number),
                                    colorProperty = ColorProperty(textColor),
                                    fontSizeProperty = FontSizeProperty(14f),
                                ),
                                Spacer(modifier = SdUiModifier().size(8)),
                                Row(
                                    modifier = SdUiModifier().fillMaxWidth(),
                                    horizontalArrangementProperty = HorizontalArrangementProperty(
                                        HorizontalArrangementOption.SpaceBetween
                                    ),
                                    content = listOf(
                                        Text(
                                            textProperty = TextProperty(holder),
                                            colorProperty = ColorProperty(textColor),
                                            fontSizeProperty = FontSizeProperty(14f),
                                            fontWeightProperty = FontWeightProperty(FontWeightOption.SemiBold)
                                        ),
                                        Text(
                                            textProperty = TextProperty(date),
                                            colorProperty = ColorProperty(textColor),
                                            fontSizeProperty = FontSizeProperty(12f),
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            )
        )

        fun benefitItem(text: String, iconColor: ColorOption) = Row(
            verticalAlignmentProperty = VerticalAlignmentProperty(VerticalAlignmentOption.Center),
            content = listOf(
                Icon(
                    modifier = SdUiModifier().size(16),
                    iconNameProperty = IconNameProperty(IconOption.Check),
                    tintProperty = TintProperty(iconColor),
                ),
                Spacer(modifier = SdUiModifier().width(10)),
                Text(
                    textProperty = TextProperty(text),
                    colorProperty = ColorProperty(ColorOption.White()),
                    fontSizeProperty = FontSizeProperty(14f),
                    lineHeightProperty = LineHeightProperty(20)
                )
            )
        )

        fun benefitsList(items: List<String>, iconColor: ColorOption) = Column(
            verticalArrangementProperty = VerticalArrangementProperty(
                VerticalArrangementOption.SpacedBy(
                    10
                )
            ),
            content = items.map { benefitItem(it, iconColor) }
        )

        fun infoRow(label: String, value: String) = Column(
            content = listOf(
                Text(
                    textProperty = TextProperty(label),
                    colorProperty = ColorProperty(subtitleColor),
                    fontSizeProperty = FontSizeProperty(12f)
                ),
                Spacer(modifier = SdUiModifier().size(2)),
                Text(
                    textProperty = TextProperty(value),
                    colorProperty = ColorProperty(ColorOption.White()),
                    fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                    fontSizeProperty = FontSizeProperty(16f)
                )
            )
        )

        fun cardPlanSection(
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
            shapeProperty = ShapeProperty(ShapeOptions.Large),
            cardColorsProperty = CardColorsProperty(
                value = CardColorsModel(
                    containerColor = surface,
                    contentColor = ColorOption.White(),
                )
            ),
            content = listOf(
                Column(
                    modifier = SdUiModifier().padding(horizontal = 16).padding(vertical = 16),
                    verticalArrangementProperty = VerticalArrangementProperty(
                        VerticalArrangementOption.SpacedBy(14)
                    ),
                    content = listOf(
                        cardVisual(
                            name = cardName,
                            holder = holder,
                            number = number,
                            date = date,
                            cardColor = visualColor,
                            textColor = visualTextColor,
                            accentOne = badgeOne,
                            accentTwo = badgeTwo,
                        ),
                        Text(
                            textProperty = TextProperty(benefitsTitle),
                            colorProperty = ColorProperty(ColorOption.CustomColor(0xffCBD5E1)),
                            fontWeightProperty = FontWeightProperty(FontWeightOption.SemiBold),
                            fontSizeProperty = FontSizeProperty(14f)
                        ),
                        benefitsList(benefits, benefitIconColor),
                        Card(
                            modifier = SdUiModifier().fillMaxWidth(),
                            shapeProperty = ShapeProperty(ShapeOptions.Medium),
                            cardColorsProperty = CardColorsProperty(
                                value = CardColorsModel(
                                    containerColor = ColorOption.CustomColor(0xff0F172A),
                                    contentColor = ColorOption.White(),
                                )
                            ),
                            content = listOf(
                                Row(
                                    modifier = SdUiModifier().padding(horizontal = 14)
                                        .padding(vertical = 12).fillMaxWidth(),
                                    horizontalArrangementProperty = HorizontalArrangementProperty(
                                        HorizontalArrangementOption.SpaceBetween
                                    ),
                                    content = listOf(
                                        infoRow("Annual Fee", fee),
                                        infoRow("Pre-approved Limit", limit),
                                    )
                                )
                            )
                        ),
                        Button(
                            modifier = SdUiModifier().fillMaxWidth(),
                            shapeProperty = ShapeProperty(ShapeOptions.Medium),
                            buttonColorsProperty = ButtonColorsProperty(
                                value = ButtonColorsModel(
                                    containerColor = ColorOption.White(),
                                    contentColor = ColorOption.CustomColor(0xff111827),
                                )
                            ),
                            content = listOf(
                                Text(
                                    modifier = SdUiModifier().padding(vertical = 6),
                                    textProperty = TextProperty("Choose this Card"),
                                    fontSizeProperty = FontSizeProperty(16f),
                                    fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                                )
                            )
                        )
                    )
                )
            )
        )

        val screen = DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            content = listOf(
                Column(
                    modifier = SdUiModifier().fillMaxWidth().background(background),
                    weightProperty = WeightProperty(1f),
                    content = listOf(
                        LazyColumn(
                            modifier = SdUiModifier().padding(horizontal = 12, vertical = 16)
                                .fillMaxWidth(),
                            weightProperty = WeightProperty(1f),
                            verticalArrangementProperty = VerticalArrangementProperty(
                                VerticalArrangementOption.SpacedBy(14)
                            ),
                            content = listOf(
                                Column(
                                    modifier = SdUiModifier().padding(horizontal = 8),
                                    content = listOf(
                                        Text(
                                            textProperty = TextProperty("Choose your plan"),
                                            colorProperty = ColorProperty(ColorOption.White()),
                                            fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                                            fontSizeProperty = FontSizeProperty(36f),
                                            lineHeightProperty = LineHeightProperty(42)
                                        ),
                                        Spacer(modifier = SdUiModifier().size(4)),
                                        Text(
                                            textProperty = TextProperty(
                                                "Select the credit card that best fits your financial goals."
                                            ),
                                            colorProperty = ColorProperty(subtitleColor),
                                            fontSizeProperty = FontSizeProperty(15f),
                                            lineHeightProperty = LineHeightProperty(22)
                                        )
                                    )
                                ),
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
                                ),
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
                                ),
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
                                ),
                            )
                        )
                    )
                )
            )
        )

        return screen
    }

    fun getScreen1(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? = DefaultTemplate(
        flow = request.flow,
        stage = screenId,
        version = "1",
        content = listOf(
            SdUi(
                modifier = SdUiModifier().fillMaxWidth(),
                flowIdentifierProperty = FlowIdentifierProperty("Card"),
                stageIdentifierProperty = StageIdentifierProperty("Start"),
                fromScreenIdentifierProperty = FromScreenIdentifierProperty(screenId),
                template = routingController.getTemplate(
                    SdUiRequest(
                        flow = "Card",
                        fromScreen = screenId,
                        toScreen = "Start",
                        screenData = request.screenData
                    )
                )
            ),
        )
    )
}