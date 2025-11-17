package com.vinibank.backend.sdui.flow.home.content

import com.vini.designsystemsdui.action.navigateAction
import com.vini.designsystemsdui.component.Button
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.LazyColumn
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vini.designsystemsdui.template.Template
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.model.SdUiRequest

import org.springframework.stereotype.Component

@Component
class InvestmentsContent : HomeScreen {
    override val screenId: String
        get() = "Investimentos"

    override fun getScreen(request: SdUiRequest): Template? = DefaultTemplate(
        flow = "Home",
        stage = "Investimentos",
        version = "1",
        template = "",
        content =  listOf(
            LazyColumn(
                weightProperty = WeightProperty(1f),
                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                verticalArrangementProperty = VerticalArrangementProperty(VerticalArrangementOption.Center),
                horizontalAlignmentProperty = HorizontalAlignmentProperty(HorizontalAlignmentOption.Center),
                content =  listOf(
                    Column(
                        content =  listOf(
                            Text(textProperty = TextProperty("Conteudo de investimentos"))
                        )
                    ),
                    Column(
                        verticalArrangementProperty = VerticalArrangementProperty(VerticalArrangementOption.Bottom),
                        content =  listOf(
                            Button(
                                horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                                paddingHorizontalProperty = PaddingHorizontalProperty(10),
                                content = listOf(
                                    Text(textProperty = TextProperty("Ver mais"))
                                ),
                                onClick = navigateAction(flow = "Investments")
                            )
                        )
                    ),
                )
            ),
        )
    )
}