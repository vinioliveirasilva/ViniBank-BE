package com.vinibank.backend

import com.vinibank.backend.db.SessionDatabase
import com.vinibank.backend.sdui.model.SdUiError
import com.vinibank.backend.sdui.model.SdUiRequest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.lang.Thread.sleep

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

                var hasError = false

                val toEncrypt = runCatching {
                    action(decodedObject)
                }.onFailure {
                    hasError = true
                    (it as SdUiError).screen
                }

                val (result, newIv) = encrypt(
                    toEncrypt.getOrNull().orEmpty(),
                    decryptIv,
                    this
                )

                sleep(1000)
                return if (hasError) {
                    ResponseEntity.badRequest()
                } else {
                    ResponseEntity.ok()
                }.header("Content-Type", "text/plain")
                    .header("iv", encoderProvider.encode(newIv))
                    .body(encoderProvider.encode(result))
            }
        }
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