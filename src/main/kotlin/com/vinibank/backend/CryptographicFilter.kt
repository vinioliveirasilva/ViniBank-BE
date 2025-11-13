package com.vinibank.backend

import com.vini.designsystemsdui.exception.SdUiPropertyUpdateException
import com.vinibank.backend.db.SessionDatabase
import com.vinibank.backend.sdui.model.SdUiError
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CryptographicFilter(
    private val sessionDatabase: SessionDatabase,
    private val keyAgreementGenerator: FastKeyExchangeManager,
    private val encoderProvider: EncoderProvider
) {

    fun handleRequest(
        data: String,
        iv: String,
        sessionId: String,
        action: (SdUiRequest) -> String,
    ): ResponseEntity<String> {
        val decodedIv = encoderProvider.decode(iv)
        val decoded = encoderProvider.decode(data)

        keyAgreementGenerator.run {
            sessionDatabase.sessions[sessionId].run {
                if (this == null) return ResponseEntity.badRequest()
                    .body(Json.encodeToString<Error>(Error("Session not found", 400)))

                val (input, decryptIv) = decrypt(decoded, decodedIv, this)

                val decodedObject = try {
                    Json.decodeFromString<SdUiRequest>(input.decodeToString())
                } catch (_: Exception) {
                    input as SdUiRequest//TODO
                }

                var errorType = ErrorType.None

                val toEncrypt = try {
                    action(decodedObject)
                } catch (it: SdUiError) {
                    errorType = ErrorType.Screen
                    Json.encodeToString(it)
                } catch (it: SdUiPropertyUpdateException) {
                    errorType = ErrorType.Property
                    Json.encodeToString(it)
                }

                val (result, newIv) = encrypt(
                    toEncrypt,
                    decryptIv,
                    this
                )

                return when(errorType) {
                    ErrorType.None -> ResponseEntity.ok()
                    ErrorType.Screen -> ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    ErrorType.Property -> ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    ErrorType.Unknown -> ResponseEntity.badRequest()
                }.header("Content-Type", "text/plain")
                    .header("iv", encoderProvider.encode(newIv))
                    .body(encoderProvider.encode(result))
            }
        }
    }

    enum class ErrorType {
        None,
        Screen,
        Property,
        Unknown;
    }

    fun startFilter(publicKey: String) : Pair<String, String> {
        val newSessionId = (sessionDatabase.sessions.keys.lastOrNull() ?: "0").toInt().inc().toString()
        val result = keyAgreementGenerator.let {
            sessionDatabase.addSession(
                newSessionId,
                it.createPublicKeyAndRunPhaseOne(
                    encoderProvider.decode(publicKey)
                )
            )
            it.generateSecret()
        }
        return Pair(newSessionId, result)
    }
}