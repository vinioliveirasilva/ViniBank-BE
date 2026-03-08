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
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.fillMaxHeight
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.option.FontWeightOption
import com.vini.designsystemsdui.modifier.option.HorizontalAlignmentOption
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
import com.vinibank.backend.sdui.flow.UpdateSdUiTemplateRequest
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.flow.home.screen.CheckingAccountScreen.Companion.checkingAccountTopSdUiRequestUpdate
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.json.JsonObject
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class BalanceContent : HomeScreen {
    override val screenId: String = "Balance"

    override fun getScreenUpdate(request: UpdateSdUiTemplateRequest) = listOf<JsonObject>(
//        Text(TODO()
//            fontSize = 26f,
//            fontWeight = (
//                FontWeightOption.Bold
//            ),
//            id = "HomeBalance",
//            text = (
//                "R$ ${Random.nextInt(10000)},00"
//            ),
//            cacheStrategy = CacheStrategy.NoCache(),
//        ),
    )

    override fun getScreen(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? {
        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            content = {
                Card(
                    colors = CardColorsModel(
                        containerColor = ColorOption.CustomColor(hex = 0xff2B8CEE),
                        contentColor = ColorOption.White(),
                        disabledContainerColor = ColorOption.CustomColor(hex = 0xff2B8CEE),
                        disabledContentColor = ColorOption.White(),
                    ),
                    shape = ShapeOption.RoundedCorner(16),
                    content = {
                        Column(
                            modifier = SdUiModifier().padding(24).fillMaxHeight(),
                            horizontalAlignment = HorizontalAlignmentOption.Center(),
                            verticalArrangement = VerticalArrangementOption.Center(),
                            content = {
                                Row(
                                    modifier = SdUiModifier().fillMaxWidth(),
                                    verticalAlignment = VerticalAlignmentOption.Center(),
                                    content = {
                                        Column(
                                            modifier = SdUiModifier().weight(10f),
                                            content = {
                                                Text(
                                                    text = "Saldo atual",
                                                    color = ColorOption.LightGray(),
                                                )
                                                Text(
                                                    fontSize = 26f,
                                                    fontWeight = FontWeightOption.Bold,
                                                    id = "HomeBalance",
                                                    text = "R$ ${Random.nextInt(10000)},00",
                                                    cacheStrategy = CacheStrategy.NoCache(),
                                                )
                                                Text(
                                                    text = "Atualizado em: Agora",
                                                    color = ColorOption.LightGray()
                                                )
                                            }
                                        )
                                        IconButton(
                                            modifier = SdUiModifier().weight(1f),
                                            content = {
                                                IconButton(
                                                    modifier = SdUiModifier().weight(1f),
                                                    content = {
                                                        Icon(icon = IconOption.Autorenew)
                                                    },
                                                    onClickAction = ToBooleanAction(
                                                        idToChange = checkingAccountTopSdUiRequestUpdate,
                                                        newValue = true
                                                    )
                                                )
                                            },
                                        )
                                    }
                                )
                                Spacer(modifier = SdUiModifier().size(24))
                                Row(
                                    modifier = SdUiModifier().fillMaxWidth(),
                                    horizontalArrangement = HorizontalArrangementOption.SpaceBetween(),
                                    content = {
                                        Button(
                                            modifier = SdUiModifier().weight(1f),
                                            colors = ButtonColorsModel(
                                                containerColor = ColorOption.White(),
                                                contentColor = ColorOption.CustomColor(
                                                    0xff2B8CEE
                                                ),
                                                disabledContainerColor = ColorOption.White(),
                                                disabledContentColor = ColorOption.White()
                                            ),
                                            shape = ShapeOption.Rectangle(),
                                            content = {
                                                Icon(
                                                    modifier = SdUiModifier().size(16),
                                                    icon = IconOption.Money
                                                )
                                                Spacer(modifier = SdUiModifier().size(8))
                                                Text(
                                                    text = "Transferir",
                                                    fontSize = 14f,
                                                    fontWeight = FontWeightOption.SemiBold
                                                )
                                            }
                                        )
                                        Spacer(modifier = SdUiModifier().width(12))
                                        Button(
                                            modifier = SdUiModifier().weight(1f),
                                            colors = ButtonColorsModel(
                                                containerColor = ColorOption.White(),
                                                contentColor = ColorOption.CustomColor(
                                                    0xff2B8CEE
                                                ),
                                                disabledContainerColor = ColorOption.White(),
                                                disabledContentColor = ColorOption.White()
                                            ),
                                            shape = ShapeOption.Rectangle(),
                                            content = {
                                                Icon(
                                                    modifier = SdUiModifier().size(16),
                                                    icon = IconOption.Add
                                                )
                                                Spacer(modifier = SdUiModifier().size(8))
                                                Text(
                                                    text = "Investir",
                                                    fontSize = 14f,
                                                    fontWeight = FontWeightOption.SemiBold
                                                )
                                            }
                                        )
                                    }
                                )
                            }
                        )
                    }
                )
            }
        )
    }
}