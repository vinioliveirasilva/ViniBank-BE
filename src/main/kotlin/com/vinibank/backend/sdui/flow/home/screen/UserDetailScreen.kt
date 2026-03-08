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
import com.vini.designsystemsdui.core.SdUiComposer
import com.vini.designsystemsdui.modifier.SdUiModifier
import com.vini.designsystemsdui.modifier.clickable
import com.vini.designsystemsdui.modifier.fillMaxHeight
import com.vini.designsystemsdui.modifier.fillMaxWidth
import com.vini.designsystemsdui.modifier.option.HorizontalAlignmentOption
import com.vini.designsystemsdui.modifier.option.VerticalAlignmentOption
import com.vini.designsystemsdui.modifier.padding
import com.vini.designsystemsdui.modifier.size
import com.vini.designsystemsdui.modifier.option.HorizontalArrangementOption
import com.vini.designsystemsdui.modifier.option.IconOption
import com.vini.designsystemsdui.modifier.option.VerticalArrangementOption
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

        fun SdUiComposer.menuItem(name: String, icon: IconOption? = null) = Column(
            modifier = SdUiModifier().fillMaxWidth().padding(horizontal = 20).clickable(
                action = ContinueAction(
                    flowId = "TODO",
                    currentScreenId = "UserDetail",
                    nextScreenId = "TODO",
                    screenData = request.screenData
                )
            ),
            verticalArrangement = VerticalArrangementOption.Center(),
            content = {
                Row(
                    modifier = SdUiModifier().fillMaxWidth().padding(vertical = 10),
                    horizontalArrangement = HorizontalArrangementOption.SpaceBetween(),
                    verticalAlignment = VerticalAlignmentOption.Center(),
                    content = {
                        Row(
                            verticalAlignment = VerticalAlignmentOption.Center(),
                            content = {
                                if (icon != null) {
                                    Icon(
                                        modifier = SdUiModifier().padding(horizontal = 10).size(48),
                                        icon = icon,
                                    )
                                }
                                Text(text = name)
                            }
                        )
                        Icon(
                            modifier = SdUiModifier().padding(horizontal = 10),
                            icon = IconOption.RightArrow,
                        )
                    }
                )
                HorizontalDivider()
            },
        )

        fun SdUiComposer.screenContent() {
            LazyColumn(
                modifier = SdUiModifier().fillMaxWidth().fillMaxHeight(),
                content = {
                    Card(
                        modifier = SdUiModifier().padding(20).fillMaxWidth(),
                        content = {
                            Column(
                                modifier = SdUiModifier().padding(vertical = 20).fillMaxWidth(),
                                horizontalAlignment = (
                                    HorizontalAlignmentOption.Center()
                                ),
                                content = {
                                    Icon(
                                        modifier = SdUiModifier().padding(horizontal = 20).size(96),
                                        icon = IconOption.User,
                                    )
                                    Text(
                                        modifier = SdUiModifier().padding(horizontal = 20),
                                        text = user.name,
                                    )
                                    Text(
                                        modifier = SdUiModifier().padding(horizontal = 20),
                                        text = user.email,
                                    )
                                    Text(
                                        modifier = SdUiModifier().padding(horizontal = 20),
                                        text = user.phone.formatPhoneNumber(),
                                    )
                                }
                            )
                        }
                    )
                    menuItem("Dados Pessoais", IconOption.PersonSearch)
                    menuItem("Privacidade de dados", IconOption.Lock)
                    menuItem("Tema", IconOption.Theme)
                    menuItem("Sair do App", IconOption.Logout)
                }
            )
        }

        return DefaultTemplate(
            flow = request.flow,
            stage = screenId,
            version = "1",
            content = {
                TopAppBar(
                    title = {
                        Text(text = "User Detail")
                    },
                    navigationIcon = {
                        IconButton(
                            content = {
                                Icon(icon = IconOption.LeftArrow)
                            },
                            onClickAction = BackAction()
                        )
                    }
                )
                screenContent()
            }
        )
    }
}
