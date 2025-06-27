package com.vinibank.backend.sdui.flow.signup

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.vinibank.backend.db.userDatabaseInstance
import com.vinibank.backend.sdui.flow.signup.model.CreateAccountModel
import com.vinibank.backend.sdui.flow.signup.screens.EmailScreen
import com.vinibank.backend.sdui.flow.signup.screens.PasswordScreen
import com.vinibank.backend.sdui.flow.signup.screens.PersonalInfoScreen
import com.vinibank.backend.sdui.flow.signup.screens.SuccessScreen
import com.vinibank.backend.sdui.model.SdUiError
import com.vinibank.backend.sdui.model.SdUiRequest
import org.json.JSONObject
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


    private fun getScreen(request: SdUiRequest): Pair<JSONObject, ResponseEntity<String>?> {
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

    private fun emailRule(request: SdUiRequest): Pair<JSONObject, ResponseEntity<String>?> {

        data class EmailModel(
            @SerializedName("email") val email: String,
        )

        val model = createModel<EmailModel>(request.flowData)

        if (db.users.any { it.key == model.email }) {
            val response = EmailScreen(isError = true).getScreenModel(request.flowData)
            return Pair(
                response,
                ResponseEntity.badRequest().body(
                    gson.toJson(
                        SdUiError("Email ja cadastrado", 400, response.toString()),
                        SdUiError::class.java
                    )
                )
            )
        }

        return Pair(signUpScreens(request), null)
    }

    private fun passwordRule(request: SdUiRequest): Pair<JSONObject, ResponseEntity<String>?> {
        val model = createModel<CreateAccountModel>(request.flowData)
        db.addUser(model.name, model.email, model.password)
        return Pair(signUpScreens(request), null)
    }

    private fun noRule(request: SdUiRequest): Pair<JSONObject, ResponseEntity<String>?> {
        return Pair(signUpScreens(request), null)
    }

    private fun signUpScreens(request: SdUiRequest) = when (request.nextStage) {
        "Email" -> EmailScreen().getScreenModel(request.flowData)
        "PersonalInfo" -> PersonalInfoScreen().getScreenModel(request.flowData)
        "Password" -> PasswordScreen().getScreenModel(request.flowData)
        "Success" -> SuccessScreen().getScreenModel(request.flowData)
        else -> EmailScreen().getScreenModel(request.flowData)
    }


    private inline fun <reified T> createModel(model: String): T {
        return gson.fromJson<T>(model, T::class.java)
    }
}