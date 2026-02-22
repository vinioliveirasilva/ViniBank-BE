package com.vinibank.backend.sdui.flow.home.content

import com.vini.designsystemsdui.CacheStrategy
import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.ToBooleanAction
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Card
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.IconButton
import com.vini.designsystemsdui.component.Row
import com.vini.designsystemsdui.component.Spacer
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.property.ButtonColorsProperty
import com.vini.designsystemsdui.property.CardColorsProperty
import com.vini.designsystemsdui.property.ColorProperty
import com.vini.designsystemsdui.property.FontSizeProperty
import com.vini.designsystemsdui.property.FontWeightProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.ShapeProperty
import com.vini.designsystemsdui.property.SizeProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalAlignmentProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.VerticalFillTypeProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.WidthProperty
import com.vini.designsystemsdui.property.options.ButtonColorsModel
import com.vini.designsystemsdui.property.options.CardColorsModel
import com.vini.designsystemsdui.property.options.FontWeightOption
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.IconOption
import com.vini.designsystemsdui.property.options.ShapeOptions
import com.vini.designsystemsdui.property.options.VerticalAlignmentOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.property.options.VerticalFillTypeOption
import com.vini.designsystemsdui.property.options.color.ColorOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.sdui.flow.UpdateSdUiTemplateRequest
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.flow.home.screen.CheckingAccountScreen.Companion.checkingAccountTopSdUiRequestUpdate
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class BalanceContent : HomeScreen {
    override val screenId: String = "Balance"

    override fun getScreenUpdate(request: UpdateSdUiTemplateRequest) = listOf(
        Text(
            fontSizeProperty = FontSizeProperty(26f),
            fontWeightProperty = FontWeightProperty(
                FontWeightOption.Bold
            ),
            id = "HomeBalance",
            textProperty = TextProperty(
                "R$ ${Random.nextInt(10000)},00"
            ),
            cacheStrategy = CacheStrategy.NoCache(),
        ),
    )

    override fun getScreen(request: SdUiRequest, parameters: Map<String, String>, screenId: String): Template? {
        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            content = listOf(
                Card(
                    cardColorsProperty = CardColorsProperty(
                        CardColorsModel(
                            containerColor = ColorOption.CustomColor(hex = 0xff2B8CEE),
                            contentColor = ColorOption.White(),
                            disabledContainerColor = ColorOption.CustomColor(hex = 0xff2B8CEE),
                            disabledContentColor = ColorOption.White(),
                        )
                    ),
                    shapeProperty = ShapeProperty(ShapeOptions.Large),
                    content = listOf(
                        Column(
                            paddingHorizontalProperty = PaddingHorizontalProperty(24),
                            paddingVerticalProperty = PaddingVerticalProperty(24),
                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                HorizontalAlignmentOption.Center
                            ),
                            verticalArrangementProperty = VerticalArrangementProperty(
                                VerticalArrangementOption.Center
                            ),
                            verticalFillTypeProperty = VerticalFillTypeProperty(
                                VerticalFillTypeOption.Max
                            ),
                            content = listOf(
                                Row(
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    verticalAlignmentProperty = VerticalAlignmentProperty(
                                        VerticalAlignmentOption.Center
                                    ),
                                    content = listOf(
                                        Column(
                                            weightProperty = WeightProperty(10f),
                                            content = listOf(
                                                Text(
                                                    colorProperty = ColorProperty(ColorOption.LightGray()),
                                                    textProperty = TextProperty("Saldo atual")
                                                ),
                                                Text(
                                                    fontSizeProperty = FontSizeProperty(26f),
                                                    fontWeightProperty = FontWeightProperty(
                                                        FontWeightOption.Bold
                                                    ),
                                                    id = "HomeBalance",
                                                    textProperty = TextProperty(
                                                        "R$ ${Random.nextInt(10000)},00"
                                                    ),
                                                    cacheStrategy = CacheStrategy.NoCache(),
                                                ),
                                                Text(
                                                    colorProperty = ColorProperty(ColorOption.LightGray()),
                                                    textProperty = TextProperty("Atualizado em: Agora")
                                                ),
                                            )
                                        ),
                                        IconButton(
                                            weightProperty = WeightProperty(1f),
                                            content = listOf(
                                                IconButton(
                                                    weightProperty = WeightProperty(1f),
                                                    content = listOf(
                                                        Icon(
                                                            iconNameProperty = IconNameProperty(
                                                                IconOption.Autorenew
                                                            )
                                                        )
                                                    ),
                                                    onClick = ToBooleanAction(
                                                        idToChange = checkingAccountTopSdUiRequestUpdate,
                                                        newValue = true
                                                    )
                                                ),
                                            ),
                                        ),
                                    )
                                ),
                                Spacer(sizeProperty = SizeProperty(24)),
                                Row(
                                    horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                        HorizontalFillTypeOption.Max
                                    ),
                                    horizontalArrangementProperty = HorizontalArrangementProperty(
                                        HorizontalArrangementOption.SpaceBetween
                                    ),
                                    content = listOf(
                                        Button(
                                            buttonColorsProperty = ButtonColorsProperty(
                                                value = ButtonColorsModel(
                                                    containerColor = ColorOption.White(),
                                                    contentColor = ColorOption.CustomColor(
                                                        0xff2B8CEE
                                                    )
                                                )
                                            ),
                                            shapeProperty = ShapeProperty(ShapeOptions.Medium),
                                            weightProperty = WeightProperty(1f),
                                            content = listOf(
                                                Icon(
                                                    sizeProperty = SizeProperty(16),
                                                    iconNameProperty = IconNameProperty(IconOption.Money)
                                                ),
                                                Spacer(sizeProperty = SizeProperty(8)),
                                                Text(
                                                    textProperty = TextProperty("Transferir"),
                                                    fontSizeProperty = FontSizeProperty(14f),
                                                    fontWeightProperty = FontWeightProperty(
                                                        FontWeightOption.SemiBold
                                                    )
                                                )
                                            ),
                                        ),
                                        Spacer(
                                            widthProperty = WidthProperty(12)
                                        ),
                                        Button(
                                            buttonColorsProperty = ButtonColorsProperty(
                                                value = ButtonColorsModel(
                                                    containerColor = ColorOption.White(),
                                                    contentColor = ColorOption.CustomColor(
                                                        0xff2B8CEE
                                                    )
                                                )
                                            ),
                                            shapeProperty = ShapeProperty(ShapeOptions.Medium),
                                            weightProperty = WeightProperty(1f),
                                            content = listOf(
                                                Icon(
                                                    sizeProperty = SizeProperty(16),
                                                    iconNameProperty = IconNameProperty(IconOption.Add)
                                                ),
                                                Spacer(sizeProperty = SizeProperty(8)),
                                                Text(
                                                    textProperty = TextProperty("Investir"),
                                                    fontSizeProperty = FontSizeProperty(14f),
                                                    fontWeightProperty = FontWeightProperty(
                                                        FontWeightOption.SemiBold
                                                    )
                                                )
                                            ),
                                        )
                                    )
                                ),
                            )
                        ),
                    )
                )
            )
        )
    }
}