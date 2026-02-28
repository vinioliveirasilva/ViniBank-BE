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
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.size
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalArrangementProperty
import com.vini.designsystemsdui.property.IconNameProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalAlignmentProperty
import com.vini.designsystemsdui.property.VerticalArrangementProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption
import com.vini.designsystemsdui.property.options.IconOption
import com.vini.designsystemsdui.property.options.VerticalAlignmentOption
import com.vini.designsystemsdui.property.options.VerticalArrangementOption
import com.vini.designsystemsdui.template.DefaultTemplate
import com.vinibank.backend.db.User
import com.vinibank.backend.db.UserDatabase
import com.vinibank.backend.sdui.flow.home.HomeScreen
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.stereotype.Component

@Component
class UserDetailScreen(
    private val userDetailRepository: UserDatabase,
) : HomeScreen {
    override val screenId: String
        get() = "UserDetail"

    private fun String.formatPhoneNumber(): String {
        val digitsOnly = replace(Regex("[^\\d]"), "")
        val regex = Regex("(\\d{2})(\\d)(\\d{4})(\\d{4})")
        return regex.replace(digitsOnly, "$1 $2 $3-$4")
    }

    override fun getScreen(
        request: SdUiRequest,
        parameters: Map<String, String>,
        screenId: String,
    ): Template? {
        val user =
            userDetailRepository.users["vinioliveirasilva@outlook.com"] ?: User("", "", "", "")

        fun menuItem(name: String, icon: IconOption? = null) = Column(
            modifier = SdUiModifier().fillMaxWidth().padding(horizontal = 20),
            verticalArrangementProperty = VerticalArrangementProperty(VerticalArrangementOption.Center),
            content = listOf(
                Row(
                    modifier = SdUiModifier().fillMaxWidth().padding(vertical = 10),
                    horizontalArrangementProperty = HorizontalArrangementProperty(
                        HorizontalArrangementOption.SpaceBetween
                    ),
                    verticalAlignmentProperty = VerticalAlignmentProperty(VerticalAlignmentOption.Center),
                    content = listOf(
                        Row(
                            verticalAlignmentProperty = VerticalAlignmentProperty(
                                VerticalAlignmentOption.Center
                            ),
                            content = listOfNotNull(
                                icon?.let {
                                    Icon(
                                        modifier = SdUiModifier().padding(horizontal = 10).size(48),
                                        iconNameProperty = IconNameProperty(it),
                                    )
                                },
                                Text(textProperty = TextProperty(name))
                            )
                        ),
                        Icon(
                            modifier = SdUiModifier().padding(horizontal = 10),
                            iconNameProperty = IconNameProperty(IconOption.RightArrow),
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
            modifier = SdUiModifier().fillMaxWidth(),
            weightProperty = WeightProperty(1f),
            content = listOf(
                Card(
                    modifier = SdUiModifier().padding(horizontal = 20).padding(vertical = 10)
                        .fillMaxWidth(),
                    content = listOf(
                        Column(
                            modifier = SdUiModifier().padding(vertical = 20).fillMaxWidth(),
                            horizontalAlignmentProperty = HorizontalAlignmentProperty(
                                HorizontalAlignmentOption.Center
                            ),
                            content = listOf(
                                Icon(
                                    modifier = SdUiModifier().padding(horizontal = 20).size(96),
                                    iconNameProperty = IconNameProperty(IconOption.User),
                                ),
                                Text(
                                    modifier = SdUiModifier().padding(horizontal = 20),
                                    textProperty = TextProperty(user.name),
                                ),
                                Text(
                                    modifier = SdUiModifier().padding(horizontal = 20),
                                    textProperty = TextProperty(user.email),
                                ),
                                Text(
                                    modifier = SdUiModifier().padding(horizontal = 20),
                                    textProperty = TextProperty(user.phone.formatPhoneNumber()),
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
            content = listOf(
                TopAppBar(
                    title = listOf(
                        Text(textProperty = TextProperty("User Detail"))
                    ),
                    navigationIcon = listOf(
                        IconButton(
                            content = listOf(
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