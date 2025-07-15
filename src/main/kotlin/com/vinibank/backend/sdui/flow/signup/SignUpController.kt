package com.vinibank.backend.sdui.flow.signup

import com.vinibank.backend.db.userDatabaseInstance
import com.vinibank.backend.sdui.flow.signup.model.CreateAccountModel
import com.vinibank.backend.sdui.flow.signup.screens.EmailScreen
import com.vinibank.backend.sdui.flow.signup.screens.PasswordScreen
import com.vinibank.backend.sdui.flow.signup.screens.PersonalInfoScreen
import com.vinibank.backend.sdui.flow.signup.screens.SuccessScreen
import com.vinibank.backend.sdui.model.SdUiError
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import org.springframework.http.ResponseEntity

object SignUpController {
    const val IDENTIFIER = "SignUp"

    val db = userDatabaseInstance

    fun getSdUiScreen(
        request: SdUiRequest
    ) = getScreen(request)


    private fun getScreen(request: SdUiRequest): Pair<JsonObject, ResponseEntity<String>?> {
        if (request.fromScreen.isBlank())
            return Pair(signUpScreens(request), null)

        return getRule(request)
    }

    private fun getRule(request: SdUiRequest) = when (request.fromScreen) {
        "Email" -> emailRule(request)
        "PersonalInfo" -> noRule(request)
        "Password" -> passwordRule(request)
        "Success" -> noRule(request)
        else -> noRule(request)
    }

    private fun emailRule(request: SdUiRequest): Pair<JsonObject, ResponseEntity<String>?> {
        val model = createModel<EmailScreen.EmailStateModel>(request.screenData)

        if (db.users.any { it.key == model.email }) {
            val response = EmailScreen(model.copy(isError = true)).getScreenModel(request.screenData)

            return Pair(
                response,
                ResponseEntity.badRequest().body(
                    Json.encodeToString<SdUiError>(
                        SdUiError("Email ja cadastrado", 400, response)
                    )
                )
            )
        }

        return Pair(signUpScreens(request), null)
    }

    private fun passwordRule(request: SdUiRequest): Pair<JsonObject, ResponseEntity<String>?> {
        val model = createModel<CreateAccountModel>(request.screenData)
        db.addUser(model.name, model.email, model.password)
        return Pair(signUpScreens(request), null)
    }

    private fun noRule(request: SdUiRequest): Pair<JsonObject, ResponseEntity<String>?> {
        return Pair(signUpScreens(request), null)
    }

    private fun signUpScreens(request: SdUiRequest) = when (request.toScreen) {
        "Email" -> EmailScreen().getScreenModel(request.screenData)
        "PersonalInfo" -> PersonalInfoScreen().getScreenModel(request.screenData)
        "Password" -> PasswordScreen().getScreenModel(request.screenData)
        "Success" -> SuccessScreen().getScreenModel(request.screenData)
        else -> EmailScreen().getScreenModel(request.screenData)
    }


    private inline fun <reified T> createModel(model: JsonObject?): T {
        return Json.decodeFromJsonElement<T>(model ?: JsonObject(emptyMap()))
    }
}