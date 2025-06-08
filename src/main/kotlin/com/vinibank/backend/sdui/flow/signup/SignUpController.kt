package com.vinibank.backend.sdui.flow.signup

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.vinibank.backend.db.userDatabaseInstance
import com.vinibank.backend.sdui.flow.signup.model.CreateAccountModel
import com.vinibank.backend.sdui.flow.signup.screens.Email
import com.vinibank.backend.sdui.flow.signup.screens.Password
import com.vinibank.backend.sdui.flow.signup.screens.PersonalInfo
import com.vinibank.backend.sdui.flow.signup.screens.Success
import com.vinibank.backend.sdui.model.ScreenModel
import com.vinibank.backend.sdui.model.SdUiError
import com.vinibank.backend.sdui.model.SdUiRequest
import org.springframework.http.ResponseEntity

class SignUpController {

    companion object {
        const val IDENTIFIER = "SignUp"
    }

    val db = userDatabaseInstance
    private val gson = Gson()

    fun getSdUiScreen(
        request: SdUiRequest
    ) = getScreen(request)


    private fun getScreen(request: SdUiRequest): Pair<ScreenModel, ResponseEntity<String>?> {
        if (request.currentStage.isBlank())
            return Pair(signUpScreens(request), null)

        return getRule(request)
    }

    private fun getRule(request: SdUiRequest) = when (request.currentStage) {
        "Email" -> emailRule(request)
        "PersonalInfo" -> noRule(request)
        "Password" -> passwordRule(request)
        "Success" -> noRule(request)
        else -> noRule(request)
    }

    private fun emailRule(request: SdUiRequest): Pair<ScreenModel, ResponseEntity<String>?> {

        data class EmailModel(
            @SerializedName("email") val email: String,
        )

        val model = createModel<EmailModel>(request.flowData)

        if (db.users.any { it.key == model.email }) {
            val response = Email(isError = true).getScreenModel(request.flowData)
            return Pair(
                response,
                ResponseEntity.badRequest().body(
                    gson.toJson(
                        SdUiError("Email ja cadastrado", 400, response),
                        SdUiError::class.java
                    )
                )
            )
        }

        return Pair(signUpScreens(request), null)
    }

    private fun passwordRule(request: SdUiRequest): Pair<ScreenModel, ResponseEntity<String>?> {
        val model = createModel<CreateAccountModel>(request.flowData)
        db.addUser(model.name, model.email, model.password)
        return Pair(signUpScreens(request), null)
    }

    private fun noRule(request: SdUiRequest): Pair<ScreenModel, ResponseEntity<String>?> {
        return Pair(signUpScreens(request), null)
    }

    private fun signUpScreens(request: SdUiRequest) = when (request.nextStage) {
        "Email" -> Email().getScreenModel(request.flowData)
        "PersonalInfo" -> PersonalInfo().getScreenModel(request.flowData)
        "Password" -> Password().getScreenModel(request.flowData)
        "Success" -> Success().getScreenModel(request.flowData)
        else -> Email().getScreenModel(request.flowData)
    }


    private inline fun <reified T> createModel(model: String): T {
        return gson.fromJson<T>(model, T::class.java)
    }
}