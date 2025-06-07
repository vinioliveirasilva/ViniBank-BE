package com.vinibank.backend

import com.google.gson.Gson
import com.vinibank.backend.model.ScreenModel
import com.vinibank.backend.model.SdUiError
import com.vinibank.backend.model.SdUiRequest
import com.vinibank.backend.signup.emailScreen
import com.vinibank.backend.signup.emailScreenError
import com.vinibank.backend.signup.passwordScreen
import com.vinibank.backend.signup.personalInfoScreen
import com.vinibank.backend.signup.successScreen
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Thread.sleep
import java.util.Base64

@Serializable
data class User(
    val email: String,
    val password: String,
)

@Serializable
data class UserAuthenticated(
    val email: String,
    val name: String,
    val docNumber: String,
)

@Serializable
data class Error(
    val message: String,
    val code: Int,
)

@RestController
@SpringBootApplication
class Application {
    val session = mutableMapOf<String, Pair<String, ByteArray?>>()
    val keyAgreementGenerator = DHExchangePartner()

    val users = mutableListOf(
        User(
            email = "bob@example.com",
            password = "secure456",
        )
    )

    private inline fun <reified T> facade(
        data: String,
        iv: String,
        sessionId: String,
        action: (T) -> Pair<String, ResponseEntity<String>?>
    ): ResponseEntity<String> {
        val decodedIv = Base64.getDecoder().decode(iv)
        val decoded = Base64.getDecoder().decode(data)

        CryptoManager().run {
            session[sessionId].run {
                if (this == null) return ResponseEntity.badRequest()
                    .body(createError("Session not found", 400))

                val (secret, oldIv) = this
                val input = decrypt(decoded, secret, decodedIv, sessionId).decodeToString().also {
                    println(it)
                }

                val decodedObject = try {
                    Gson().fromJson<T>(input, T::class.java)
                } catch (e: Exception) {
                    input as T
                }.also {
                    println(it)
                }

                val (toEncrypt, error) = action(decodedObject)

                error?.run {
                    return error
                }

                val (result, newIv) = encrypt(
                    toEncrypt,
                    secret,
                    sessionId
                )

                session[sessionId] = Pair(secret, newIv)

                return ResponseEntity
                    .ok()
                    .header("Content-Type", "text/plain")
                    .header("iv", Base64.getEncoder().encodeToString(newIv))
                    .body(Base64.getEncoder().encodeToString(result))
            }
        }
    }

    @RequestMapping("/logout")
    fun doLogout(
        @RequestHeader(value = "iv") iv: String,
        @RequestHeader(value = "sessionId") sessionId: String,
    ): ResponseEntity<String> {
        return facade<Any>("", iv, sessionId) { output ->
            val hasSession = true//session.remove(sessionId) != null
            if (hasSession) {
                Pair("{}", null)
            } else {
                Pair("", ResponseEntity.badRequest().body(createError("Invalid credentials", 401)))
            }
        }
    }

    @RequestMapping("/authenticate")
    fun authenticate(
        @RequestHeader(value = "iv") iv: String,
        @RequestHeader(value = "sessionId") sessionId: String,
        @RequestBody data: String
    ): ResponseEntity<String> {
        sleep(2000)
        return facade<User>(data, iv, sessionId) { output ->
            val user =
                users.firstOrNull { it.email == output.email && it.password == output.password }

            val successBody = UserAuthenticated(
                email = output.email,
                name = "Vinicius",
                docNumber = "429.377.828.41"
            )

            val serializer = serializer(UserAuthenticated::class.java)
            val toEncrypt = Json.encodeToString(serializer, successBody)

            if (user == null) {
                Pair("", ResponseEntity.badRequest().body(createError("Invalid credentials", 401)))
            } else {
                Pair(toEncrypt, null)
            }
        }
    }

    @RequestMapping("/initialize")
    fun startHandShake(@RequestHeader(value = "publicKey") publicKey: String): ResponseEntity<String> {
        val newSessionId = (session.keys.lastOrNull() ?: "0").toInt().inc()
        val result = keyAgreementGenerator.let {
            val alicePubKeyForBob =
                it.createPublicKeyFromEncodedMaterial(Base64.getDecoder().decode(publicKey))
            val bobKpair = it.createPersonalDHKeypairAndInitAgreement(alicePubKeyForBob)
            it.phaseOne(alicePubKeyForBob)

            session[newSessionId.toString()] = Pair(it.generateSharedSecret().asHex(), null)

            Base64.getEncoder().encodeToString(bobKpair.public.encoded)
        }

        return ResponseEntity
            .ok()
            .header("Content-Type", "text/plain")
            .header("sessionId", newSessionId.toString())
            .body(result)
    }

    val text = """ {
    "name" : "teste",
    "version" : "1",
    "components" : [
        {
            "type" : "text",
            "data" : { 
                "fillType" : "Max",
                "textAlign" : "Center",
                "text": "salve quebrada"
             }
        },
        {
            "type" : "text",
            "data" : { "text": "salve quebrada2" }
        },
        {
            "type" : "unknown",
            "data" : { "text": "salve quebrada2" }
        },
        {
            "type" : "text",
            "data" : { "text": "salve quebrada25" }
        },
        {
            "type" : "spacer",
            "data" : { "size": 100 }
        },
        {
            "type" : "button",
            "data" : { "text": "Iniciar fluxo" }
        },
        {
            "type" : "spacer",
            "data" : { "size": 100 }
        },
        {
            "type" : "column",
            "data" : {
                "horizontalAlignment" : "Center",
                "paddingHorizontal" : 10,
                "fillType" : "Max",
                "components" : [
                    {
                        "type" : "button",
                        "data" : { 
                            "text": "Continuar",
                            "fillType" : "Max"
                         }
                    },
                    {
                        "type" : "button",
                        "data" : {
                            "text": "Fechar",
                            "fillType" : "Max"
                         }
                    }
                ]
            }
        }
    ]
}
"""

    @RequestMapping("/sdui_screens")
    fun getSdUiScreen(
        @RequestHeader(value = "iv") iv: String,
        @RequestHeader(value = "sessionId") sessionId: String,
        @RequestBody request: String
    ): ResponseEntity<String> {
        return facade<SdUiRequest>(request, iv, sessionId) { output ->

            val response = getScreen(output)
            println(response)

            if (response.second) {
                Pair(
                    "",
                    ResponseEntity.badRequest().body(
                        Gson().toJson(
                            SdUiError("Email ja cadastrado", 400, response.first),
                            SdUiError::class.java
                        )
                    )
                )
            } else {

                Pair(
                    Gson().toJson(response.first, ScreenModel::class.java),
                    null
                )
            }
        }
    }

    fun createError(errorMessage: String, code: Int): String {
        val serializer = serializer(Error::class.java)
        return Json.encodeToString(serializer, Error(errorMessage, code))
    }

    private inline fun <reified T> createModel(model: String): T {
        return Json.decodeFromString<T>(model)
    }

    private fun getScreen(request: SdUiRequest): Pair<ScreenModel, Boolean> {
        if (request.currentStage.isBlank())
            return Pair(signUpScreens(request), false)

        return getRule(request)
    }

    private fun getRule(request: SdUiRequest) = when (request.currentStage) {
        "Email" -> emailRule(request)
        "PersonalInfo" -> noRule(request)
        "Password" -> passwordRule(request)
        "Success" -> noRule(request)
        else -> noRule(request)
    }

    private fun emailRule(request: SdUiRequest): Pair<ScreenModel, Boolean> {
        @Serializable
        data class EmailModel(
            val email: String,
        )

        val model = createModel<EmailModel>(request.flowData)

        if (users.any { it.email == model.email }) {
            val response = emailScreenError(request.flowData)
            return Pair(response, true)//ResponseEntity.badRequest().body(TODO(), 400)))
        }

        return Pair(signUpScreens(request), false)
    }

    private fun passwordRule(request: SdUiRequest): Pair<ScreenModel, Boolean> {
        @Serializable
        data class PasswordModel(
            val password: String,
            val email: String,
            val name: String,
            val document: String,
            val phone: String,
        )

        val model = createModel<PasswordModel>(request.flowData)

        users.add(User(model.email, model.password))

        return Pair(signUpScreens(request), false)
    }

    private fun noRule(request: SdUiRequest): Pair<ScreenModel, Boolean> {
        return Pair(signUpScreens(request), false)
    }

    private fun signUpScreens(request: SdUiRequest) = when (request.nextStage) {
        "Email" -> emailScreen(request.flowData)
        "PersonalInfo" -> personalInfoScreen(request.flowData)
        "Password" -> passwordScreen(request.flowData)
        "Success" -> successScreen(request.flowData)
        else -> emailScreen(request.flowData)
    }

}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
