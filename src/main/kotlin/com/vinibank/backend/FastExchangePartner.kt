package com.vinibank.backend

import java.security.KeyFactory
import java.security.PrivateKey
import java.security.SecureRandom
import java.security.spec.PKCS8EncodedKeySpec
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class FastKeyExchangeManager(
    private val ivGenerator: IvGenerator = IvGenerator()
) {
    var privateKey: PrivateKey

    private val encodedPrivateKey = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAiz4f68r5qtDCXw5b5HuTYw5t2tatHkjFQoYE5u3OPWq1tdBidqg4bELVdmD0+BWkvzpUzmF7JFJLS0W8FWX08QIDAQABAkAuNkeU05AJ42/m6VaRHXw8b2EBNKdO+jrRibYdyEXe9sqNfIWd2qZKkKPcddV1Jp0igRibYXs3IQosslEJTkHzAiEAwVcbVw1Xx79/oA6+umVlQ3LE7MvszjX+bAyQ8fgsK9sCIQC4XrHOgP92Ps84eb7FiiwzjEKeO8kffc+QS8JSdGDCIwIhAIYsVjvmdZmYqxA/y1r9zPWu6zzOFIvS6rq/ScOyj6zrAiEAnm0ohtVML1R7hgD/kAlSQB1HZWbkRT47rLeczHOabVcCIDcEh2pkE7XsJgC6W0qgb82Iitzacq+HmrRiXpNm6yvr"

    init {
        // Generate a private key
        val decodedPrivateKey = Base64.getDecoder().decode(encodedPrivateKey)
        val privateKeySpec = PKCS8EncodedKeySpec(decodedPrivateKey)
        val keyFactory = KeyFactory.getInstance("RSA")
        privateKey = keyFactory.generatePrivate(privateKeySpec)
    }


    private fun decrypt(data: ByteArray, privateKey: PrivateKey): ByteArray {
        val cipher: Cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        val decodedData = cipher.doFinal(data)
        return decodedData
    }

    fun createPublicKeyAndRunPhaseOne(encoded: ByteArray, sessionId: String): ByteArray {
        val decryptedAes = decrypt(encoded, privateKey)
        return Base64.getDecoder().decode(decryptedAes)
    }

    fun generateSecret() = Base64.getEncoder().encodeToString(ivGenerator.getIv())

    fun encrypt(data: String, iv: ByteArray, encodedKey: ByteArray): Pair<ByteArray, ByteArray> {
        val plainText = data.toByteArray(Charsets.UTF_8)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, SecretKeySpec(encodedKey, "AES"), IvParameterSpec(iv))
        return cipher.doFinal(plainText) to cipher.iv
    }

    fun decrypt(encryptedData: ByteArray, iv: ByteArray, encodedKey: ByteArray): Pair<ByteArray, ByteArray> {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, SecretKeySpec(encodedKey, "AES"), IvParameterSpec(iv))
        return cipher.doFinal(encryptedData) to cipher.iv
    }
}

class IvGenerator {
    private lateinit var iv: ByteArray

    private fun generateIv(algorithm: String): IvParameterSpec {
        val random = SecureRandom.getInstanceStrong()

        val blockSize = Cipher.getInstance(algorithm).blockSize
        val iv = ByteArray(blockSize)
        random.nextBytes(iv)
        return IvParameterSpec(iv)
    }

    fun getIv(): ByteArray =
        try {
            val ivParameterSpec = generateIv("AES/CBC/PKCS5Padding")
            ivParameterSpec.iv!!
        } catch (e: Exception) {
            e.printStackTrace()
            ByteArray(16)
        }.also {
            iv = it
        }
}