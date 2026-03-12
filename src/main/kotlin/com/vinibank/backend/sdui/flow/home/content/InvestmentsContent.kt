package com.vinibank.backend.sdui.flow.home.content

import com.vini.designsystemsdui.InteractionId
import com.vini.designsystemsdui.core.SdUiNode.Template
import com.vini.designsystemsdui.ui.component.Card
import com.vini.designsystemsdui.ui.component.Column
import com.vini.designsystemsdui.ui.component.DotIndicator
import com.vini.designsystemsdui.ui.data.DotIndicatorInteractionModel
import com.vini.designsystemsdui.ui.component.HorizontalPager
import com.vini.designsystemsdui.ui.data.HorizontalPagerInteractionModel
import com.vini.designsystemsdui.ui.component.Icon
import com.vini.designsystemsdui.ui.component.LazyColumn
import com.vini.designsystemsdui.ui.component.LazyRow
import com.vini.designsystemsdui.ui.component.OutlinedButton
import com.vini.designsystemsdui.ui.component.Row
import com.vini.designsystemsdui.ui.component.Spacer
import com.vini.designsystemsdui.ui.component.Text
import com.vini.designsystemsdui.core.SdUiComposer
import com.vini.designsystemsdui.ui.modifier.Modifier
import com.vini.designsystemsdui.ui.modifier.background
import com.vini.designsystemsdui.ui.modifier.fillMaxHeight
import com.vini.designsystemsdui.ui.modifier.fillMaxSize
import com.vini.designsystemsdui.ui.modifier.fillMaxWidth
import com.vini.designsystemsdui.ui.modifier.height
import com.vini.designsystemsdui.ui.modifier.option.FontWeightOption
import com.vini.designsystemsdui.ui.modifier.option.HorizontalAlignmentOption
import com.vini.designsystemsdui.ui.modifier.option.HorizontalArrangementOption
import com.vini.designsystemsdui.ui.modifier.option.IconOption
import com.vini.designsystemsdui.ui.modifier.option.PaddingValuesOption
import com.vini.designsystemsdui.ui.modifier.option.ShapeOption
import com.vini.designsystemsdui.ui.modifier.option.TextAlignOption
import com.vini.designsystemsdui.ui.modifier.option.VerticalAlignmentOption
import com.vini.designsystemsdui.ui.modifier.option.VerticalArrangementOption
import com.vini.designsystemsdui.ui.modifier.padding
import com.vini.designsystemsdui.ui.modifier.size
import com.vini.designsystemsdui.ui.modifier.width
import com.vini.designsystemsdui.ui.modifier.wrapContentHeight
import com.vini.designsystemsdui.ui.modifier.style.ButtonColorsModel
import com.vini.designsystemsdui.ui.modifier.style.CardColorsModel
import com.vini.designsystemsdui.ui.modifier.option.ColorOption
import com.vini.designsystemsdui.ui.template.ScreenTemplate
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
                    modifier = Modifier.size(64),
                    shape = ShapeOption.RoundedCorner(16),
                    colors = ButtonColorsModel(
                        containerColor = cardDark,
                        contentColor = tint,
                    ),
                    content = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = HorizontalArrangementOption.Center(),
                            content = {
                                Column(
                                    modifier = Modifier.fillMaxHeight(),
                                    verticalArrangement = VerticalArrangementOption.Center(),
                                    content = {
                                        Icon(
                                            modifier = Modifier.size(20),
                                            icon = icon,
                                            tint = tint,
                                        )
                                    }
                                )
                            }
                        )
                    }
                )
                Spacer(modifier = Modifier.size(10))
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
            modifier = Modifier.padding(horizontal = 5),
            content = {
                Card(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                    shape = ShapeOption.RoundedCorner(16),
                    colors = CardColorsModel(
                        containerColor = ColorOption.CustomColor(0xff2F3A92),
                        contentColor = ColorOption.White(),
                    ),
                    content = {
                        Column(
                            modifier = Modifier.padding(horizontal = 18)
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
                                            modifier = Modifier.padding(horizontal = 10)
                                                .padding(vertical = 6),
                                            text = "NEW OPPORTUNITY",
                                            fontWeight = FontWeightOption.Bold,
                                            fontSize = 12f,
                                        )
                                    }
                                )
                                Spacer(modifier = Modifier.size(14))
                                Text(
                                    text = title,
                                    fontSize = 24f,
                                    lineHeight = 30f,
                                    fontWeight = FontWeightOption.Bold,
                                    color = ColorOption.White(),
                                )
                                Spacer(modifier = Modifier.size(8))
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

        val screen = ScreenTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            content = {
                Column(
                    modifier = Modifier.fillMaxWidth().background(background).fillMaxSize(),
                    content = {
                        LazyColumn(
                            modifier = Modifier.padding(horizontal = 24).fillMaxWidth()
                                .weight(1f),
                            content = {
                                Spacer(modifier = Modifier.size(8))
                                Text(
                                    text = "Total Invested",
                                    color = textSecondary,
                                    fontSize = 14f,
                                    fontWeight = FontWeightOption.Medium
                                )
                                Spacer(modifier = Modifier.size(4))
                                Row(
                                    verticalAlignment = VerticalAlignmentOption.Center(),
                                    content = {
                                        Text(
                                            text = "\$0.00",
                                            color = ColorOption.White(),
                                            fontWeight = FontWeightOption.Bold,
                                            fontSize = 30f,
                                        )
                                        Spacer(modifier = Modifier.width(10))
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
                                                    modifier = Modifier.padding(horizontal = 8)
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
                                Spacer(modifier = Modifier.size(32))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
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
                                                    modifier = Modifier.padding(30).size(68),
                                                    icon = IconOption.Investment,
                                                    tint = primary,
                                                )
                                            }
                                        )
                                    }
                                )
                                Spacer(modifier = Modifier.size(20))
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "No investments yet",
                                    fontSize = 18f,
                                    lineHeight = 28f,
                                    fontWeight = FontWeightOption.Bold,
                                    color = ColorOption.White(),
                                    textAlign = TextAlignOption.Center,
                                )
                                Spacer(modifier = Modifier.size(8))
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Start building your wealth today by exploring our investment products.",
                                    fontSize = 14f,
                                    lineHeight = 22f,
                                    color = textSecondary,
                                    textAlign = TextAlignOption.Center,
                                )
                                Spacer(modifier = Modifier.size(28))
                                Text(
                                    text = "INVESTMENT PRODUCTS",
                                    color = textSecondary,
                                    fontWeight = FontWeightOption.SemiBold,
                                    fontSize = 14f,
                                )
                                Spacer(modifier = Modifier.size(14))
                                LazyRow(
                                    content = {
                                        productItem(
                                            icon = IconOption.InvestmentOutline,
                                            tint = ColorOption.CustomColor(0xffA855F7),
                                            title = "Stocks"
                                        )
                                        Spacer(modifier = Modifier.width(12))
                                        productItem(
                                            icon = IconOption.WalletOutlined,
                                            tint = ColorOption.CustomColor(0xff3B82F6),
                                            title = "Fixed\nIncome"
                                        )
                                        Spacer(modifier = Modifier.width(12))
                                        productItem(
                                            icon = IconOption.Money,
                                            tint = ColorOption.CustomColor(0xffF97316),
                                            title = "Crypto"
                                        )
                                        Spacer(modifier = Modifier.width(12))
                                        productItem(
                                            icon = IconOption.Card,
                                            tint = ColorOption.CustomColor(0xff10B981),
                                            title = "ETFs"
                                        )
                                        Spacer(modifier = Modifier.width(12))
                                        productItem(
                                            icon = IconOption.Favorite,
                                            tint = ColorOption.CustomColor(0xffF59E0B),
                                            title = "Gold"
                                        )
                                    }
                                )
                                Spacer(modifier = Modifier.size(26))
                                HorizontalPager(
                                    contentPadding = PaddingValuesOption.PaddingValueAll(10),
                                    interactionModel = HorizontalPagerInteractionModel(
                                        currentPage = carouselPageId
                                    ),
                                    currentPage = 0,
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
                                Spacer(modifier = Modifier.size(10))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = HorizontalArrangementOption.Center(),
                                    content = {
                                        DotIndicator(
                                            interactionModel = DotIndicatorInteractionModel(
                                                selectedIndex = carouselPageId
                                            ),
                                            selectedIndex = 0,
                                            indicatorCount = 3,
                                            selectedColor = primary,
                                            unselectedColor = ColorOption.CustomColor(0xff3A4A5F),
                                        )
                                    }
                                )
                                Spacer(modifier = Modifier.size(20))
                            }
                        )
                    }
                )
            }
        )

        return screen
    }
}