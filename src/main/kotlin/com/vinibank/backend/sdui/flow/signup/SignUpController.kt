package com.vinibank.backend.sdui.flow.signup

import com.vini.designsystemsdui.InteractionId
import com.vini.designsystemsdui.ui.action.NavigationContract
import com.vini.designsystemsdui.ui.action.NavigationResult
import com.vinibank.backend.sdui.flow.BaseFlowController
import com.vinibank.backend.sdui.flow.SdUiScreen
import com.vinibank.backend.sdui.flow.signup.screen.EmailScreen
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.springframework.stereotype.Component

interface SignUpScreen : SdUiScreen

@Serializable
data class SignUpResult(
    val email: String,
    val password: String,
    val name: String,
    val phone: String,
) : NavigationResult {
    override fun get() = buildJsonObject {
        put(SignUpController.Contract.EMAIL, email)
        put(SignUpController.Contract.PASS, password)
        put(SignUpController.Contract.NAME, name)
        put(SignUpController.Contract.PHONE, phone)
    }
}

data class SignUpContract(
    val email: InteractionId<String>? = null,
    val password: InteractionId<String>? = null,
    val name: InteractionId<String>? = null,
    val phone: InteractionId<String>? = null,
) : NavigationContract {
    override fun get() = buildJsonObject {
        email?.id?.let { put(it, SignUpController.Contract.EMAIL) }
        password?.id?.let { put(it, SignUpController.Contract.PASS) }
        name?.id?.let { put(it, SignUpController.Contract.NAME) }
        phone?.id?.let { put(it, SignUpController.Contract.PHONE) }
    }
}

@Component()
class SignUpController(
    screens: List<SignUpScreen>,
    defaultScreen: EmailScreen,
) : BaseFlowController<SignUpScreen>(screens, defaultScreen, "SignUp") {

    object Contract {
        const val EMAIL = "SignUp.Contract.email"
        const val PASS = "SignUp.Contract.password"
        const val NAME = "SignUp.Contract.name"
        const val PHONE = "SignUp.Contract.phone"
    }
}