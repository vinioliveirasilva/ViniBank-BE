package com.vinibank.backend

import com.google.gson.Gson
import com.vinibank.backend.db.sessionDatabaseInstance
import com.vinibank.backend.db.userDatabaseInstance
import com.vinibank.backend.sdui.flow.UndefinedController
import com.vinibank.backend.sdui.flow.home.HomeController
import com.vinibank.backend.sdui.flow.signup.SignUpController
import com.vinibank.backend.sdui.model.SdUiRequest
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
import java.util.*

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
    val keyAgreementGenerator = DHExchangePartner()
    val userDb = userDatabaseInstance
    val sessionDb = sessionDatabaseInstance
    val signUpController = SignUpController()
    val gson = Gson()

    private inline fun <reified T> facade(
        data: String,
        iv: String,
        sessionId: String,
        action: (T) -> Pair<String, ResponseEntity<String>?>
    ): ResponseEntity<String> {
        val decodedIv = Base64.getDecoder().decode(iv)
        val decoded = Base64.getDecoder().decode(data)

        CryptoManager().run {
            sessionDb.sessions[sessionId].run {
                if (this == null) return ResponseEntity.badRequest()
                    .body(createError("Session not found", 400))

                val secret = this
                val input = decrypt(decoded, secret, decodedIv, sessionId).decodeToString()

                val decodedObject = try {
                    gson.fromJson(input, T::class.java)
                } catch (_: Exception) {
                    input as T
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
            val prefetchUser = userDb.users[output.email]
            val user = prefetchUser?.takeIf { it.password == output.password }

            if (user == null) {
                Pair("", ResponseEntity.badRequest().body(createError("Invalid credentials", 401)))
            } else {
                val successBody = UserAuthenticated(
                    email = user.email,
                    name = user.name,
                    docNumber = "???"
                )

                val serializer = serializer(UserAuthenticated::class.java)
                val toEncrypt = Json.encodeToString(serializer, successBody)

                Pair(toEncrypt, null)
            }
        }
    }

    @RequestMapping("/initialize")
    fun startHandShake(@RequestHeader(value = "publicKey") publicKey: String): ResponseEntity<String> {
        val newSessionId = (sessionDb.sessions.keys.lastOrNull() ?: "0").toInt().inc()
        val result = keyAgreementGenerator.let {
            val alicePubKeyForBob =
                it.createPublicKeyFromEncodedMaterial(Base64.getDecoder().decode(publicKey))
            val bobKpair = it.createPersonalDHKeypairAndInitAgreement(alicePubKeyForBob)
            it.phaseOne(alicePubKeyForBob)

            sessionDb.addSession(newSessionId.toString(), it.generateSharedSecret().asHex())

            Base64.getEncoder().encodeToString(bobKpair.public.encoded)
        }

        return ResponseEntity
            .ok()
            .header("Content-Type", "text/plain")
            .header("sessionId", newSessionId.toString())
            .body(result)
    }

    @RequestMapping("/sdui_screens")
    fun getSdUiScreen(
        @RequestHeader(value = "iv") iv: String,
        @RequestHeader(value = "sessionId") sessionId: String,
        @RequestBody request: String
    ): ResponseEntity<String> {
        return facade<SdUiRequest>(request, iv, sessionId) { output ->

            val response = when (output.currentFlow) {
                SignUpController.IDENTIFIER -> signUpController.getSdUiScreen(output)
                HomeController.IDENTIFIER -> HomeController.getSdUiScreen(output)
                else -> UndefinedController.getSdUiScreen(output)
            }

            if (response.second != null) {
                Pair("", response.second)
            } else {
                Pair(
                    response.first.toString(),
                    null
                )
            }
        }
    }

    fun createError(errorMessage: String, code: Int): String {
        val serializer = serializer(Error::class.java)
        return Json.encodeToString(serializer, Error(errorMessage, code))
    }

}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
