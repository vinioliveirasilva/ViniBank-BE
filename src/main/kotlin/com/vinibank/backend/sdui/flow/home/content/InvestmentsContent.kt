package com.vinibank.backend.sdui.flow.home.content

import com.vini.designsystemsdui.InteractionId
import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.component.Card
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.DotIndicator
import com.vini.designsystemsdui.component.DotIndicatorInteractionModel
import com.vini.designsystemsdui.component.HorizontalPager
import com.vini.designsystemsdui.component.HorizontalPagerInteractionModel
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.LazyColumn
import com.vini.designsystemsdui.component.LazyRow
import com.vini.designsystemsdui.component.OutlinedButton
import com.vini.designsystemsdui.component.Row
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.core.SdUiComposer
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.background
import com.vini.designsystemsdui.modifier.fillMaxHeight
import com.vini.designsystemsdui.modifier.fillMaxSize
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.height
import com.vini.designsystemsdui.modifier.option.FontWeightOption
import com.vini.designsystemsdui.modifier.option.HorizontalAlignmentOption
import com.vini.designsystemsdui.modifier.option.HorizontalArrangementOption
import com.vini.designsystemsdui.modifier.option.IconOption
import com.vini.designsystemsdui.modifier.option.PaddingValuesOption
import com.vini.designsystemsdui.modifier.option.ShapeOption
import com.vini.designsystemsdui.modifier.option.TextAlignOption
import com.vini.designsystemsdui.modifier.option.VerticalAlignmentOption
import com.vini.designsystemsdui.modifier.option.VerticalArrangementOption
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.size
import com.vini.designsystemsdui.modifier.width
import com.vini.designsystemsdui.modifier.wrapContentHeight
import com.vini.designsystemsdui.property.options.ButtonColorsModel
import com.vini.designsystemsdui.property.options.CardColorsModel
import com.vini.designsystemsdui.property.options.color.ColorOption
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
        val carouselPageId = InteractionId<Int>("investment.carousel.page")

        fun SdUiComposer.productItem(icon: IconOption, tint: ColorOption, title: String) = Column(
            horizontalAlignment = HorizontalAlignmentOption.Center(),
            content = {
                OutlinedButton(
                    modifier = SdUiModifier().size(64),
                    shape = ShapeOption.RoundedCorner(16),
                    colors = ButtonColorsModel(
                        containerColor = cardDark,
                        contentColor = tint,
                    ),
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
                                            modifier = SdUiModifier().size(20),
                                            icon = icon,
                                            tint = tint,
                                        )
                                    }
                                )
                            }
                        )
                    }
                )
                Spacer(modifier = SdUiModifier().size(10))
                Text(
                    text = title,
                    color = ColorOption.White(),
                    fontSize = 12f,
                    textAlign = TextAlignOption.Center,
                    lineHeight = 16f,
                )
            }
        )

        fun SdUiComposer.promoCard(
            title: String,
            body: String,
            highlight: String,
        ) = Column(
            modifier = SdUiModifier().padding(horizontal = 5),
            content = {
                Card(
                    modifier = SdUiModifier().fillMaxWidth().wrapContentHeight(),
                    shape = ShapeOption.RoundedCorner(16),
                    colors = CardColorsModel(
                        containerColor = ColorOption.CustomColor(0xff2F3A92),
                        contentColor = ColorOption.White(),
                    ),
                    content = {
                        Column(
                            modifier = SdUiModifier().padding(horizontal = 18)
                                .padding(vertical = 18),
                            content = {
                                Card(
                                    shape = ShapeOption.RoundedCorner(8),
                                    colors = CardColorsModel(
                                        containerColor = ColorOption.CustomColor(0x3345B7AA),
                                        contentColor = ColorOption.White(),
                                    ),
                                    content = {
                                        Text(
                                            modifier = SdUiModifier().padding(horizontal = 10)
                                                .padding(vertical = 6),
                                            text = "NEW OPPORTUNITY",
                                            fontWeight = FontWeightOption.Bold,
                                            fontSize = 12f,
                                        )
                                    }
                                )
                                Spacer(modifier = SdUiModifier().size(14))
                                Text(
                                    text = title,
                                    fontSize = 24f,
                                    lineHeight = 30f,
                                    fontWeight = FontWeightOption.Bold,
                                    color = ColorOption.White(),
                                )
                                Spacer(modifier = SdUiModifier().size(8))
                                Text(
                                    text = "Earn up to $highlight with $body",
                                    fontSize = 16f,
                                    lineHeight = 24f,
                                    color = ColorOption.CustomColor(0xffD0DCFF),
                                )
                            }
                        )
                    }
                )
            }
        )

        val screen = DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            content = {
                Column(
                    modifier = SdUiModifier().fillMaxWidth().background(background).fillMaxSize(),
                    content = {
                        LazyColumn(
                            modifier = SdUiModifier().padding(horizontal = 24).fillMaxWidth()
                                .weight(1f),
                            content = {
                                Spacer(modifier = SdUiModifier().size(8))
                                Text(
                                    text = "Total Invested",
                                    color = textSecondary,
                                    fontSize = 14f,
                                    fontWeight = FontWeightOption.Medium
                                )
                                Spacer(modifier = SdUiModifier().size(4))
                                Row(
                                    verticalAlignment = VerticalAlignmentOption.Center(),
                                    content = {
                                        Text(
                                            text = "\$0.00",
                                            color = ColorOption.White(),
                                            fontWeight = FontWeightOption.Bold,
                                            fontSize = 30f,
                                        )
                                        Spacer(modifier = SdUiModifier().width(10))
                                        Card(
                                            shape = ShapeOption.RoundedCorner(8),
                                            colors = CardColorsModel(
                                                containerColor = ColorOption.CustomColor(
                                                    0xff253449
                                                ),
                                                contentColor = textSecondary,
                                            ),
                                            content = {
                                                Text(
                                                    modifier = SdUiModifier().padding(horizontal = 8)
                                                        .padding(vertical = 4),
                                                    text = "- 0.0%",
                                                    color = textSecondary,
                                                    fontSize = 12f,
                                                    fontWeight = FontWeightOption.Bold
                                                )
                                            }
                                        )
                                    }
                                )
                                Spacer(modifier = SdUiModifier().size(32))
                                Row(
                                    modifier = SdUiModifier().fillMaxWidth(),
                                    horizontalArrangement = HorizontalArrangementOption.Center(),
                                    content = {
                                        Card(
                                            shape = ShapeOption.Circle(),
                                            colors = CardColorsModel(
                                                containerColor = cardDark,
                                                contentColor = primary
                                            ),
                                            content = {
                                                Icon(
                                                    modifier = SdUiModifier().padding(30).size(68),
                                                    icon = IconOption.Investment,
                                                    tint = primary,
                                                )
                                            }
                                        )
                                    }
                                )
                                Spacer(modifier = SdUiModifier().size(20))
                                Text(
                                    modifier = SdUiModifier().fillMaxWidth(),
                                    text = "No investments yet",
                                    fontSize = 18f,
                                    lineHeight = 28f,
                                    fontWeight = FontWeightOption.Bold,
                                    color = ColorOption.White(),
                                    textAlign = TextAlignOption.Center,
                                )
                                Spacer(modifier = SdUiModifier().size(8))
                                Text(
                                    modifier = SdUiModifier().fillMaxWidth(),
                                    text = "Start building your wealth today by exploring our investment products.",
                                    fontSize = 14f,
                                    lineHeight = 22f,
                                    color = textSecondary,
                                    textAlign = TextAlignOption.Center,
                                )
                                Spacer(modifier = SdUiModifier().size(28))
                                Text(
                                    text = "INVESTMENT PRODUCTS",
                                    color = textSecondary,
                                    fontWeight = FontWeightOption.SemiBold,
                                    fontSize = 14f,
                                )
                                Spacer(modifier = SdUiModifier().size(14))
                                LazyRow(
                                    content = {
                                        productItem(
                                            icon = IconOption.InvestmentOutline,
                                            tint = ColorOption.CustomColor(0xffA855F7),
                                            title = "Stocks"
                                        )
                                        Spacer(modifier = SdUiModifier().width(12))
                                        productItem(
                                            icon = IconOption.WalletOutlined,
                                            tint = ColorOption.CustomColor(0xff3B82F6),
                                            title = "Fixed\nIncome"
                                        )
                                        Spacer(modifier = SdUiModifier().width(12))
                                        productItem(
                                            icon = IconOption.Money,
                                            tint = ColorOption.CustomColor(0xffF97316),
                                            title = "Crypto"
                                        )
                                        Spacer(modifier = SdUiModifier().width(12))
                                        productItem(
                                            icon = IconOption.Card,
                                            tint = ColorOption.CustomColor(0xff10B981),
                                            title = "ETFs"
                                        )
                                        Spacer(modifier = SdUiModifier().width(12))
                                        productItem(
                                            icon = IconOption.Favorite,
                                            tint = ColorOption.CustomColor(0xffF59E0B),
                                            title = "Gold"
                                        )
                                    }
                                )
                                Spacer(modifier = SdUiModifier().size(26))
                                HorizontalPager(
                                    contentPadding = PaddingValuesOption.PaddingValueAll(10),
                                    interactionModel = HorizontalPagerInteractionModel(
                                        currentPage = carouselPageId
                                    ),
                                    currentPage = 1,
                                    content = {
                                        promoCard(
                                            title = "High-Yield Growth Fund",
                                            highlight = "12% p.a.",
                                            body = "our diversified global income fund."
                                        )
                                        promoCard(
                                            title = "Stable Income Plus",
                                            highlight = "10% p.a.",
                                            body = "our low-volatility bond strategy."
                                        )
                                        promoCard(
                                            title = "Global Equity Select",
                                            highlight = "15% p.a.",
                                            body = "our international growth portfolio."
                                        )
                                    }
                                )
                                Spacer(modifier = SdUiModifier().size(10))
                                Row(
                                    modifier = SdUiModifier().fillMaxWidth(),
                                    horizontalArrangement = HorizontalArrangementOption.Center(),
                                    content = {
                                        DotIndicator(
                                            interactionModel = DotIndicatorInteractionModel(
                                                selectedIndex = carouselPageId
                                            ),
                                            indicatorCount = 3,
                                            selectedColor = primary,
                                            unselectedColor = ColorOption.CustomColor(0xff3A4A5F),
                                        )
                                    }
                                )
                                Spacer(modifier = SdUiModifier().size(20))
                            }
                        )
                    }
                )
            }
        )

        return screen
    }
}