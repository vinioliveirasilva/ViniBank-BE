package com.vinibank.backend.sdui.flow.home.screen

import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.BackAction
import com.vini.designsystemsdui.component.Box
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Card
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.IconButton
import com.vini.designsystemsdui.component.LazyColumn
import com.vini.designsystemsdui.component.Row
import com.vini.designsystemsdui.component.SdUi
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.component.TopAppBar
import com.vini.designsystemsdui.property.BackgroundColorProperty
import com.vini.designsystemsdui.property.ButtonColorsProperty
import com.vini.designsystemsdui.property.CardColorsProperty
import com.vini.designsystemsdui.property.ColorProperty
import com.vini.designsystemsdui.property.ContentAlignmentProperty
import com.vini.designsystemsdui.property.FlowIdentifierProperty
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.FontWeightProperty
import com.vini.designsystemsdui.property.FromScreenIdentifierProperty
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.LineHeightProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.ShapeProperty
import com.vini.designsystemsdui.property.SizeProperty
import com.vini.designsystemsdui.property.StageIdentifierProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.TintProperty
import com.vini.designsystemsdui.property.TopAppBarColorProperty
import com.vini.designsystemsdui.property.VerticalAlignmentProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.WidthProperty
import com.vini.designsystemsdui.property.options.AlignmentOptions
import com.vini.designsystemsdui.property.options.ButtonColorsModel
import com.vini.designsystemsdui.property.options.CardColorsModel
import com.vini.designsystemsdui.property.options.FontWeightOption
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.IconOption
import com.vini.designsystemsdui.property.options.ShapeOptions
import com.vini.designsystemsdui.property.options.TopAppBarColorsModel
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
    @Lazy private val routingController: RoutingController
) : HomeScreen {
    override val screenId: String = "Cartoes"

    override fun getScreen(request: SdUiRequest, parameters: Map<String, String>, screenId: String): Template? {
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
            shapeProperty = ShapeProperty(ShapeOptions.Large),
            cardColorsProperty = CardColorsProperty(
                value = CardColorsModel(
                    containerColor = cardColor,
                    contentColor = textColor,
                )
            ),
            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
            content = listOf(
                Column(
                    paddingHorizontalProperty = PaddingHorizontalProperty(16),
                    paddingVerticalProperty = PaddingVerticalProperty(16),
                    heightProperty = HeightProperty(170),
                    verticalArrangementProperty = VerticalArrangementProperty(
                        VerticalArrangementOption.SpaceBetween
                    ),
                    content = listOf(
                        Row(
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                            horizontalArrangementProperty = HorizontalArrangementProperty(
                                HorizontalArrangementOption.SpaceBetween
                            ),
                            verticalAlignmentProperty = VerticalAlignmentProperty(VerticalAlignmentOption.Center),
                            content = listOf(
                                Icon(
                                    iconNameProperty = IconNameProperty(IconOption.Card),
                                    tintProperty = TintProperty(textColor),
                                    sizeProperty = SizeProperty(20)
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
                                    widthProperty = WidthProperty(16),
                                    heightProperty = HeightProperty(16),
                                    shapeProperty = ShapeProperty(ShapeOptions.Circle),
                                    backgroundColorProperty = BackgroundColorProperty(accentOne)
                                ),
                                Spacer(widthProperty = WidthProperty(6)),
                                Column(
                                    widthProperty = WidthProperty(16),
                                    heightProperty = HeightProperty(16),
                                    shapeProperty = ShapeProperty(ShapeOptions.Circle),
                                    backgroundColorProperty = BackgroundColorProperty(accentTwo)
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
                                Spacer(sizeProperty = SizeProperty(8)),
                                Row(
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
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
                    iconNameProperty = IconNameProperty(IconOption.Check),
                    tintProperty = TintProperty(iconColor),
                    sizeProperty = SizeProperty(16),
                ),
                Spacer(widthProperty = WidthProperty(10)),
                Text(
                    textProperty = TextProperty(text),
                    colorProperty = ColorProperty(ColorOption.White()),
                    fontSizeProperty = FontSizeProperty(14f),
                    lineHeightProperty = LineHeightProperty(20)
                )
            )
        )

        fun benefitsList(items: List<String>, iconColor: ColorOption) = Column(
            verticalArrangementProperty = VerticalArrangementProperty(VerticalArrangementOption.SpacedBy(10)),
            content = items.map { benefitItem(it, iconColor) }
        )

        fun infoRow(label: String, value: String) = Column(
            content = listOf(
                Text(
                    textProperty = TextProperty(label),
                    colorProperty = ColorProperty(subtitleColor),
                    fontSizeProperty = FontSizeProperty(12f)
                ),
                Spacer(sizeProperty = SizeProperty(2)),
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
            shapeProperty = ShapeProperty(ShapeOptions.Large),
            cardColorsProperty = CardColorsProperty(
                value = CardColorsModel(
                    containerColor = surface,
                    contentColor = ColorOption.White(),
                )
            ),
            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
            content = listOf(
                Column(
                    paddingHorizontalProperty = PaddingHorizontalProperty(16),
                    paddingVerticalProperty = PaddingVerticalProperty(16),
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
                            shapeProperty = ShapeProperty(ShapeOptions.Medium),
                            cardColorsProperty = CardColorsProperty(
                                value = CardColorsModel(
                                    containerColor = ColorOption.CustomColor(0xff0F172A),
                                    contentColor = ColorOption.White(),
                                )
                            ),
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                HorizontalFillTypeOption.Max
                            ),
                            content = listOf(
                                Row(
                                    paddingHorizontalProperty = PaddingHorizontalProperty(14),
                                    paddingVerticalProperty = PaddingVerticalProperty(12),
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
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
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                            shapeProperty = ShapeProperty(ShapeOptions.Medium),
                            buttonColorsProperty = ButtonColorsProperty(
                                value = ButtonColorsModel(
                                    containerColor = ColorOption.White(),
                                    contentColor = ColorOption.CustomColor(0xff111827),
                                )
                            ),
                            content = listOf(
                                Text(
                                    paddingVerticalProperty = PaddingVerticalProperty(6),
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
                    backgroundColorProperty = BackgroundColorProperty(background),
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    weightProperty = WeightProperty(1f),
                    content = listOf(
                        LazyColumn(
                            weightProperty = WeightProperty(1f),
                            paddingHorizontalProperty = PaddingHorizontalProperty(12),
                            paddingVerticalProperty = PaddingVerticalProperty(16),
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                            verticalArrangementProperty = VerticalArrangementProperty(
                                VerticalArrangementOption.SpacedBy(14)
                            ),
                            content = listOf(
                                Column(
                                    paddingHorizontalProperty = PaddingHorizontalProperty(8),
                                    content = listOf(
                                        Text(
                                            textProperty = TextProperty("Choose your plan"),
                                            colorProperty = ColorProperty(ColorOption.White()),
                                            fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                                            fontSizeProperty = FontSizeProperty(36f),
                                            lineHeightProperty = LineHeightProperty(42)
                                        ),
                                        Spacer(sizeProperty = SizeProperty(4)),
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

    fun getScreen1(request: SdUiRequest, parameters: Map<String, String>, screenId: String): Template? = DefaultTemplate(
        flow = request.flow,
        stage = screenId,
        version = "1",
        content = listOf(
            SdUi(
                flowIdentifierProperty = FlowIdentifierProperty("Card"),
                stageIdentifierProperty = StageIdentifierProperty("Start"),
                fromScreenIdentifierProperty = FromScreenIdentifierProperty(screenId),
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
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