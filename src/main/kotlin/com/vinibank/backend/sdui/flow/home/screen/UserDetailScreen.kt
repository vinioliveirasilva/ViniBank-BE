package com.vinibank.backend.sdui.flow.home.screen

import com.vini.designsystemsdui.Template
import com.vini.designsystemsdui.action.BackAction
import com.vini.designsystemsdui.action.ContinueAction
import com.vini.designsystemsdui.component.Card
import com.vini.designsystemsdui.component.Column
import com.vini.designsystemsdui.component.HorizontalDivider
import com.vini.designsystemsdui.component.Icon
import com.vini.designsystemsdui.component.IconButton
import com.vini.designsystemsdui.component.LazyColumn
import com.vini.designsystemsdui.component.Row
import com.vini.designsystemsdui.component.Text
import com.vini.designsystemsdui.component.TopAppBar
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.SizeProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalAlignmentProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.options.IconOption
import com.vini.designsystemsdui.property.options.VerticalAlignmentOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.db.User
import com.vinibank.backend.db.UserDatabase
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
class UserDetailScreen(
    private val userDetailRepository: UserDatabase
) : HomeScreen {
    override val screenId: String
        get() = "UserDetail"

    private fun String.formatPhoneNumber(): String {
        val digitsOnly = replace(Regex("[^\\d]"), "")
        val regex = Regex("(\\d{2})(\\d)(\\d{4})(\\d{4})")
        return regex.replace(digitsOnly, "$1 $2 $3-$4")
    }

    override fun getScreen(request: SdUiRequest): Template? {
        val user = userDetailRepository.users["vinioliveirasilva@outlook.com"] ?: User("", "", "", "")

        fun menuItem(name: String, icon: IconOption? = null) = Column(
            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
            paddingHorizontalProperty = PaddingHorizontalProperty(20),
            verticalArrangementProperty = VerticalArrangementProperty(VerticalArrangementOption.Center),
            content =  listOf(
                Row(
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    paddingVerticalProperty = PaddingVerticalProperty(10),
                    horizontalArrangementProperty = HorizontalArrangementProperty(
                        HorizontalArrangementOption.SpaceBetween
                    ),
                    verticalAlignmentProperty = VerticalAlignmentProperty(VerticalAlignmentOption.Center),
                    content =  listOf(
                        Row(
                            verticalAlignmentProperty = VerticalAlignmentProperty(VerticalAlignmentOption.Center),
                            content =  listOfNotNull(
                                icon?.let {
                                    Icon(
                                        iconNameProperty = IconNameProperty(it),
                                        paddingHorizontalProperty = PaddingHorizontalProperty(10),
                                        sizeProperty = SizeProperty(48),
                                    )
                                },
                                Text(textProperty = TextProperty(name))
                            )
                        ),
                        Icon(
                            iconNameProperty = IconNameProperty(IconOption.RightArrow),
                            paddingHorizontalProperty = PaddingHorizontalProperty(10),
                        ),
                    )
                ),
                HorizontalDivider(),
            ),
            onClick = ContinueAction(
                flowId = "TODO",
                currentScreenId = "UserDetail",
                nextScreenId = "TODO",
                screenData = request.screenData
            ),
        )

        val content = LazyColumn(
            horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
            weightProperty = WeightProperty(1f),
            content =  listOf(
                Card(
                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                    paddingVerticalProperty = PaddingVerticalProperty(10),
                    horizontalFillTypeProperty = HorizontalFillTypeProperty(HorizontalFillTypeOption.Max),
                    content =  listOf(
                        Column(
                            paddingVerticalProperty = PaddingVerticalProperty(20),
                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                HorizontalAlignmentOption.Center
                            ),
                            horizontalFillTypeProperty = HorizontalFillTypeProperty(
                                HorizontalFillTypeOption.Max
                            ),
                            content =  listOf(
                                Icon(
                                    iconNameProperty = IconNameProperty(IconOption.User),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                    sizeProperty = SizeProperty(96),
                                ),
                                Text(
                                    textProperty = TextProperty(user.name),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                ),
                                Text(
                                    textProperty = TextProperty(user.email),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                ),
                                Text(
                                    textProperty = TextProperty(user.phone.formatPhoneNumber()),
                                    paddingHorizontalProperty = PaddingHorizontalProperty(20),
                                ),
                            )
                        )
                    )
                ),
                menuItem("Dados Pessoais", IconOption.PersonSearch),
                menuItem("Privacidade de dados", IconOption.Lock),
                menuItem("Tema", IconOption.Theme),
                menuItem("Sair do App", IconOption.Logout),
            )
        )

        val screen = DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            content =  listOf(
                TopAppBar(
                    title =  listOf(
                        Text(textProperty = TextProperty("User Detail"))
                    ),
                    navigationIcon = listOf(
                        IconButton(
                            content =  listOf(
                                Icon(iconNameProperty = IconNameProperty(IconOption.LeftArrow))
                            ),
                            onClick = BackAction()
                        )
                    )
                ),
                content,
            )
        )
        return screen
    }
}