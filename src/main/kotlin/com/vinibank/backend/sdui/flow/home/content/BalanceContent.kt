package com.vinibank.backend.sdui.flow.home.content

import com.vini.designsystemsdui.CacheStrategy
import com.vini.designsystemsdui.core.SdUiNode.Template
import com.vini.designsystemsdui.ui.action.ToBooleanAction
import com.vini.designsystemsdui.ui.component.Button
import com.vini.designsystemsdui.ui.component.Card
import com.vini.designsystemsdui.ui.component.Column
import com.vini.designsystemsdui.ui.component.Icon
import com.vini.designsystemsdui.ui.component.IconButton
import com.vini.designsystemsdui.ui.component.Row
import com.vini.designsystemsdui.ui.component.Spacer
import com.vini.designsystemsdui.ui.component.Text
import com.vini.designsystemsdui.ui.modifier.Modifier
import com.vini.designsystemsdui.ui.modifier.fillMaxHeight
import com.vini.designsystemsdui.ui.modifier.fillMaxWidth
import com.vini.designsystemsdui.ui.modifier.option.FontWeightOption
import com.vini.designsystemsdui.ui.modifier.option.HorizontalAlignmentOption
import com.vini.designsystemsdui.ui.modifier.option.HorizontalArrangementOption
import com.vini.designsystemsdui.ui.modifier.option.IconOption
import com.vini.designsystemsdui.ui.modifier.option.ShapeOption
import com.vini.designsystemsdui.ui.modifier.option.VerticalAlignmentOption
import com.vini.designsystemsdui.ui.modifier.option.VerticalArrangementOption
import com.vini.designsystemsdui.ui.modifier.padding
import com.vini.designsystemsdui.ui.modifier.size
import com.vini.designsystemsdui.ui.modifier.width
import com.vini.designsystemsdui.ui.modifier.style.ButtonColorsModel
import com.vini.designsystemsdui.ui.modifier.style.CardColorsModel
import com.vini.designsystemsdui.ui.modifier.option.ColorOption
import com.vini.designsystemsdui.ui.template.ScreenTemplate
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
        return ScreenTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            content = {
                Card(
                    colors = CardColorsModel(
                        containerColor = ColorOption.CustomColor(hexValue = 0xff2B8CEE),
                        contentColor = ColorOption.White(),
                        disabledContainerColor = ColorOption.CustomColor(hexValue = 0xff2B8CEE),
                        disabledContentColor = ColorOption.White(),
                    ),
                    shape = ShapeOption.RoundedCorner(16),
                    content = {
                        Column(
                            modifier = Modifier.padding(24).fillMaxHeight(),
                            horizontalAlignment = HorizontalAlignmentOption.Center(),
                            verticalArrangement = VerticalArrangementOption.Center(),
                            content = {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = VerticalAlignmentOption.Center(),
                                    content = {
                                        Column(
                                            modifier = Modifier.weight(10f),
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
                                            modifier = Modifier.weight(1f),
                                            content = {
                                                IconButton(
                                                    modifier = Modifier.weight(1f),
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
                                Spacer(modifier = Modifier.size(24))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = HorizontalArrangementOption.SpaceBetween(),
                                    content = {
                                        Button(
                                            modifier = Modifier.weight(1f),
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
                                                    modifier = Modifier.size(16),
                                                    icon = IconOption.Money
                                                )
                                                Spacer(modifier = Modifier.size(8))
                                                Text(
                                                    text = "Transferir",
                                                    fontSize = 14f,
                                                    fontWeight = FontWeightOption.SemiBold
                                                )
                                            }
                                        )
                                        Spacer(modifier = Modifier.width(12))
                                        Button(
                                            modifier = Modifier.weight(1f),
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
                                                    modifier = Modifier.size(16),
                                                    icon = IconOption.Add
                                                )
                                                Spacer(modifier = Modifier.size(8))
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