package com.vinibank.backend

import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class CryptoManager {
    private val keyPool = mutableMapOf<String, SecretKeySpec>()

    fun encrypt(strToEncrypt: String, secret: String, sessionId: String): Pair<ByteArray, ByteArray> {
        val plainText = strToEncrypt.toByteArray(Charsets.UTF_8)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        cipher.init(Cipher.ENCRYPT_MODE, getKey(sessionId, secret))
        val cipherText = cipher.doFinal(plainText)
        return cipherText to cipher.iv
    }

    fun decrypt(dataToDecrypt: ByteArray, secret: String, iv: ByteArray, sessionId: String): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        cipher.init(Cipher.DECRYPT_MODE, getKey(sessionId, secret), IvParameterSpec(iv))
        val cipherText = cipher.doFinal(dataToDecrypt)
        return cipherText
    }

    private fun getKey(sessionId: String, secret: String) : SecretKeySpec {
        return keyPool[sessionId] ?: generateKey(secret).also { keyPool[sessionId] = it }
    }

    private fun generateKey(password: String): SecretKeySpec {
        val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
        val bytes = password.toByteArray()
        digest.update(bytes, 0, bytes.size)
        val key = digest.digest()
        val secretKeySpec = SecretKeySpec(key, "AES")
        return secretKeySpec
    }
}