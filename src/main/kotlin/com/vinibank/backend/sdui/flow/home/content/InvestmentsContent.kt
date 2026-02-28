package com.vinibank.backend.sdui.flow.home.content

import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.component.Card
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.DotIndicator
import com.vini.designsystemsdui.component.HorizontalPager
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.LazyColumn
import com.vini.designsystemsdui.component.LazyRow
import com.vini.designsystemsdui.component.OutlinedButton
import com.vini.designsystemsdui.component.Row
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.background
import com.vini.designsystemsdui.modifier.fillMaxHeight
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.height
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.size
import com.vini.designsystemsdui.modifier.width
import com.vini.designsystemsdui.property.ButtonColorsProperty
import com.vini.designsystemsdui.property.CardColorsProperty
import com.vini.designsystemsdui.property.ColorProperty
import com.vini.designsystemsdui.property.ContentPaddingProperty
import com.vini.designsystemsdui.property.CurrentPageProperty
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.FontWeightProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.IndicatorCountProperty
import com.vini.designsystemsdui.property.LineHeightProperty
import com.vini.designsystemsdui.property.SelectedColorProperty
import com.vini.designsystemsdui.property.SelectedIndexProperty
import com.vini.designsystemsdui.property.ShapeProperty
import com.vini.designsystemsdui.property.TextAlignProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.TintProperty
import com.vini.designsystemsdui.property.UnselectedColorProperty
import com.vini.designsystemsdui.property.VerticalAlignmentProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.ButtonColorsModel
import com.vini.designsystemsdui.property.options.CardColorsModel
import com.vini.designsystemsdui.property.options.FontWeightOption
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.IconOption
import com.vini.designsystemsdui.property.options.ShapeOptions
import com.vini.designsystemsdui.property.options.TextAlignOption
import com.vini.designsystemsdui.property.options.VerticalAlignmentOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.property.options.color.ColorOption
import com.vini.designsystemsdui.property.util.PropertyIdWrapper
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component

@Component
class InvestmentsContent : HomeScreen {
    override val screenId: String
        get() = "Investimentos"

    override fun getScreen(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? {
        val background = ColorOption.CustomColor(0xff101922)
        val cardDark = ColorOption.CustomColor(0xff1A2632)
        val textSecondary = ColorOption.CustomColor(0xff94A3B8)
        val primary = ColorOption.CustomColor(0xff2B8CEE)
        val carouselPageId = PropertyIdWrapper<Int>("investment.html.carousel.page")

        fun productItem(icon: IconOption, tint: ColorOption, title: String) = Column(
            horizontalAlignmentProperty = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
            content = listOf(
                OutlinedButton(
                    modifier = SdUiModifier().width(64).height(64),
                    shapeProperty = ShapeProperty(ShapeOptions.Large),
                    buttonColorsProperty = ButtonColorsProperty(
                        value = ButtonColorsModel(
                            containerColor = cardDark,
                            contentColor = tint,
                        )
                    ),
                    content = listOf(
                        Row(
                            modifier = SdUiModifier().fillMaxWidth(),
                            horizontalArrangementProperty = HorizontalArrangementProperty(
                                HorizontalArrangementOption.Center
                            ),
                            content = listOf(
                                Column(
                                    modifier = SdUiModifier().fillMaxHeight(),
                                    verticalArrangementProperty = VerticalArrangementProperty(
                                        VerticalArrangementOption.Center
                                    ),
                                    content = listOf(
                                        Icon(
                                            modifier = SdUiModifier().size(20),
                                            iconNameProperty = IconNameProperty(icon),
                                            tintProperty = TintProperty(tint),
                                        )
                                    )
                                )
                            )
                        )
                    )
                ),
                Spacer(modifier = SdUiModifier().size(10)),
                Text(
                    textProperty = TextProperty(title),
                    colorProperty = ColorProperty(ColorOption.White()),
                    fontSizeProperty = FontSizeProperty(12f),
                    textAlignProperty = TextAlignProperty(TextAlignOption.Center),
                    lineHeightProperty = LineHeightProperty(16),
                )
            )
        )

        fun promoCard(
            title: String,
            body: String,
            highlight: String,
        ) = Column(
            modifier = SdUiModifier().padding(horizontal = 5),
            content = listOf(
                Card(
                    modifier = SdUiModifier().fillMaxWidth(),
                    shapeProperty = ShapeProperty(ShapeOptions.Large),
                    cardColorsProperty = CardColorsProperty(
                        value = CardColorsModel(
                            containerColor = ColorOption.CustomColor(0xff2F3A92),
                            contentColor = ColorOption.White(),
                        )
                    ),
                    content = listOf(
                        Column(
                            modifier = SdUiModifier().padding(horizontal = 18)
                                .padding(vertical = 18),
                            content = listOf(
                                Card(
                                    shapeProperty = ShapeProperty(ShapeOptions.Small),
                                    cardColorsProperty = CardColorsProperty(
                                        value = CardColorsModel(
                                            containerColor = ColorOption.CustomColor(0x3345B7AA),
                                            contentColor = ColorOption.White(),
                                        )
                                    ),
                                    content = listOf(
                                        Text(
                                            modifier = SdUiModifier().padding(horizontal = 10)
                                                .padding(vertical = 6),
                                            textProperty = TextProperty("NEW OPPORTUNITY"),
                                            fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                                            fontSizeProperty = FontSizeProperty(12f),
                                        )
                                    )
                                ),
                                Spacer(modifier = SdUiModifier().size(14)),
                                Text(
                                    textProperty = TextProperty(title),
                                    fontSizeProperty = FontSizeProperty(24f),
                                    lineHeightProperty = LineHeightProperty(30),
                                    fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                                    colorProperty = ColorProperty(ColorOption.White()),
                                ),
                                Spacer(modifier = SdUiModifier().size(8)),
                                Text(
                                    textProperty = TextProperty("Earn up to $highlight with $body"),
                                    fontSizeProperty = FontSizeProperty(16f),
                                    lineHeightProperty = LineHeightProperty(24),
                                    colorProperty = ColorProperty(ColorOption.CustomColor(0xffD0DCFF)),
                                ),
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
//                        TopAppBar(
//                            topAppBarColorProperty = TopAppBarColorProperty(
//                                value = TopAppBarColorsModel(
//                                    containerColor = background,
//                                    titleContentColor = ColorOption.White(),
//                                    navigationIconContentColor = ColorOption.White(),
//                                )
//                            ),
//                            title = listOf(
//                                Text(
//                                    textProperty = TextProperty("Investments"),
//                                    fontSizeProperty = FontSizeProperty(18f),
//                                    fontWeightProperty = FontWeightProperty(FontWeightOption.Bold)
//                                )
//                            ),
//                            actions = listOf(
//                                Box(
//                                    contentAlignmentProperty = ContentAlignmentProperty(AlignmentOptions.TopEnd),
//                                    content = listOf(
//                                        Column(
//                                            backgroundColorProperty = BackgroundColorProperty(
//                                                ColorOption.CustomColor(0xff1F2D3D)
//                                            ),
//                                            shapeProperty = ShapeProperty(ShapeOptions.Circle),
//                                            content = listOf(
//                                                IconButton(
//                                                    content = listOf(
//                                                        Icon(
//                                                            iconNameProperty = IconNameProperty(
//                                                                IconOption.NotificationsNone
//                                                            ),
//                                                            tintProperty = TintProperty(textSecondary)
//                                                        )
//                                                    )
//                                                )
//                                            )
//                                        ),
//                                        Column(
//                                            widthProperty = WidthProperty(8),
//                                            heightProperty = HeightProperty(8),
//                                            shapeProperty = ShapeProperty(ShapeOptions.Circle),
//                                            backgroundColorProperty = BackgroundColorProperty(
//                                                ColorOption.CustomColor(0xffEF4444)
//                                            )
//                                        )
//                                    )
//                                )
//                            )
//                        ),
                        LazyColumn(
                            modifier = SdUiModifier().padding(horizontal = 24).fillMaxWidth(),
                            weightProperty = WeightProperty(1f),
                            content = listOf(
                                Spacer(modifier = SdUiModifier().size(8)),
                                Text(
                                    textProperty = TextProperty("Total Invested"),
                                    colorProperty = ColorProperty(textSecondary),
                                    fontSizeProperty = FontSizeProperty(14f),
                                    fontWeightProperty = FontWeightProperty(FontWeightOption.Medium)
                                ),
                                Spacer(modifier = SdUiModifier().size(4)),
                                Row(
                                    verticalAlignmentProperty = VerticalAlignmentProperty(
                                        VerticalAlignmentOption.Center
                                    ),
                                    content = listOf(
                                        Text(
                                            textProperty = TextProperty("\$0.00"),
                                            colorProperty = ColorProperty(ColorOption.White()),
                                            fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                                            fontSizeProperty = FontSizeProperty(30f),
                                        ),
                                        Spacer(modifier = SdUiModifier().width(10)),
                                        Card(
                                            shapeProperty = ShapeProperty(ShapeOptions.Small),
                                            cardColorsProperty = CardColorsProperty(
                                                value = CardColorsModel(
                                                    containerColor = ColorOption.CustomColor(
                                                        0xff253449
                                                    ),
                                                    contentColor = textSecondary,
                                                )
                                            ),
                                            content = listOf(
                                                Text(
                                                    modifier = SdUiModifier().padding(horizontal = 8)
                                                        .padding(vertical = 4),
                                                    textProperty = TextProperty("- 0.0%"),
                                                    colorProperty = ColorProperty(textSecondary),
                                                    fontSizeProperty = FontSizeProperty(12f),
                                                    fontWeightProperty = FontWeightProperty(
                                                        FontWeightOption.Bold
                                                    )
                                                )
                                            )
                                        )
                                    )
                                ),
                                Spacer(modifier = SdUiModifier().size(32)),
                                Row(
                                    modifier = SdUiModifier().fillMaxWidth(),
                                    horizontalArrangementProperty = HorizontalArrangementProperty(
                                        HorizontalArrangementOption.Center
                                    ),
                                    content = listOf(
                                        Card(
                                            shapeProperty = ShapeProperty(ShapeOptions.Circle),
                                            cardColorsProperty = CardColorsProperty(
                                                value = CardColorsModel(
                                                    containerColor = cardDark,
                                                    contentColor = primary
                                                )
                                            ),
                                            content = listOf(
                                                Icon(
                                                    modifier = SdUiModifier().padding(horizontal = 30)
                                                        .padding(vertical = 30).size(68),
                                                    iconNameProperty = IconNameProperty(IconOption.Investment),
                                                    tintProperty = TintProperty(primary),
                                                )
                                            )
                                        )
                                    )
                                ),
                                Spacer(modifier = SdUiModifier().size(20)),
                                Text(
                                    modifier = SdUiModifier().fillMaxWidth(),
                                    textProperty = TextProperty("No investments yet"),
                                    fontSizeProperty = FontSizeProperty(18f),
                                    lineHeightProperty = LineHeightProperty(28),
                                    fontWeightProperty = FontWeightProperty(FontWeightOption.Bold),
                                    colorProperty = ColorProperty(ColorOption.White()),
                                    textAlignProperty = TextAlignProperty(TextAlignOption.Center),
                                ),
                                Spacer(modifier = SdUiModifier().size(8)),
                                Text(
                                    modifier = SdUiModifier().fillMaxWidth(),
                                    textProperty = TextProperty(
                                        "Start building your wealth today by exploring our investment products."
                                    ),
                                    fontSizeProperty = FontSizeProperty(14f),
                                    lineHeightProperty = LineHeightProperty(22),
                                    colorProperty = ColorProperty(textSecondary),
                                    textAlignProperty = TextAlignProperty(TextAlignOption.Center),
                                ),
                                Spacer(modifier = SdUiModifier().size(28)),
                                Text(
                                    textProperty = TextProperty("INVESTMENT PRODUCTS"),
                                    colorProperty = ColorProperty(textSecondary),
                                    fontWeightProperty = FontWeightProperty(FontWeightOption.SemiBold),
                                    fontSizeProperty = FontSizeProperty(14f),
                                ),
                                Spacer(modifier = SdUiModifier().size(14)),
                                LazyRow(
                                    content = listOf(
                                        productItem(
                                            icon = IconOption.InvestmentOutline,
                                            tint = ColorOption.CustomColor(0xffA855F7),
                                            title = "Stocks"
                                        ),
                                        Spacer(modifier = SdUiModifier().width(12)),
                                        productItem(
                                            icon = IconOption.WalletOutlined,
                                            tint = ColorOption.CustomColor(0xff3B82F6),
                                            title = "Fixed\nIncome"
                                        ),
                                        Spacer(modifier = SdUiModifier().width(12)),
                                        productItem(
                                            icon = IconOption.Money,
                                            tint = ColorOption.CustomColor(0xffF97316),
                                            title = "Crypto"
                                        ),
                                        Spacer(modifier = SdUiModifier().width(12)),
                                        productItem(
                                            icon = IconOption.Card,
                                            tint = ColorOption.CustomColor(0xff10B981),
                                            title = "ETFs"
                                        ),
                                        Spacer(modifier = SdUiModifier().width(12)),
                                        productItem(
                                            icon = IconOption.Favorite,
                                            tint = ColorOption.CustomColor(0xffF59E0B),
                                            title = "Gold"
                                        ),
                                    )
                                ),
                                Spacer(modifier = SdUiModifier().size(26)),
                                HorizontalPager(
                                    contentPaddingProperty = ContentPaddingProperty(10),
                                    currentPageProperty = CurrentPageProperty(
                                        value = 1,
                                        idWrapper = carouselPageId
                                    ),
                                    pageContent = listOf(
                                        promoCard(
                                            title = "High-Yield Growth Fund",
                                            highlight = "12% p.a.",
                                            body = "our diversified global income fund."
                                        ),
                                        promoCard(
                                            title = "Stable Income Plus",
                                            highlight = "10% p.a.",
                                            body = "our low-volatility bond strategy."
                                        ),
                                        promoCard(
                                            title = "Global Equity Select",
                                            highlight = "15% p.a.",
                                            body = "our international growth portfolio."
                                        )
                                    ),
                                    validators = listOf(

                                    )
                                ),
                                Spacer(modifier = SdUiModifier().size(10)),
                                Row(
                                    modifier = SdUiModifier().fillMaxWidth(),
                                    horizontalArrangementProperty = HorizontalArrangementProperty(
                                        HorizontalArrangementOption.Center
                                    ),
                                    content = listOf(
                                        DotIndicator(
                                            selectedIndexProperty = SelectedIndexProperty(idWrapper = carouselPageId),
                                            indicatorCountProperty = IndicatorCountProperty(3),
                                            selectedColorProperty = SelectedColorProperty(primary),
                                            unselectedColorProperty = UnselectedColorProperty(
                                                ColorOption.CustomColor(0xff3A4A5F)
                                            )
                                        )
                                    )
                                ),
                                Spacer(modifier = SdUiModifier().size(20)),
                            )
                        ),
                    )
                )
            )
        )

        return screen
    }
}