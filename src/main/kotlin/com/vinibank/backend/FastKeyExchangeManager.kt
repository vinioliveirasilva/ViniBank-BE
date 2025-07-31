package com.vinibank.backend

import org.springframework.stereotype.Component
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Component
class FastKeyExchangeManager(
    private val encoderProvider: EncoderProvider,
    private val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"),
    private val ivGenerator: IvGenerator = IvGenerator(cipher),
) {
    private var privateKey: PrivateKey

    private val encodedPrivateKey =
        "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAiz4f68r5qtDCXw5b5HuTYw5t2tatHkjFQoYE5u3OPWq1tdBidqg4bELVdmD0+BWkvzpUzmF7JFJLS0W8FWX08QIDAQABAkAuNkeU05AJ42/m6VaRHXw8b2EBNKdO+jrRibYdyEXe9sqNfIWd2qZKkKPcddV1Jp0igRibYXs3IQosslEJTkHzAiEAwVcbVw1Xx79/oA6+umVlQ3LE7MvszjX+bAyQ8fgsK9sCIQC4XrHOgP92Ps84eb7FiiwzjEKeO8kffc+QS8JSdGDCIwIhAIYsVjvmdZmYqxA/y1r9zPWu6zzOFIvS6rq/ScOyj6zrAiEAnm0ohtVML1R7hgD/kAlSQB1HZWbkRT47rLeczHOabVcCIDcEh2pkE7XsJgC6W0qgb82Iitzacq+HmrRiXpNm6yvr"

    init {
        val decodedPrivateKey = encoderProvider.decode(encodedPrivateKey)
        val privateKeySpec = PKCS8EncodedKeySpec(decodedPrivateKey)
        val keyFactory = KeyFactory.getInstance("RSA")
        privateKey = keyFactory.generatePrivate(privateKeySpec)
    }


    private fun decrypt(data: ByteArray, privateKey: PrivateKey): ByteArray {
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        val decodedData = cipher.doFinal(data)
        return decodedData
    }

    fun createPublicKeyAndRunPhaseOne(encoded: ByteArray): ByteArray {
        val decryptedAes = decrypt(encoded, privateKey)
        return encoderProvider.decode(decryptedAes)
    }

    fun generateSecret(): String = encoderProvider.encode(ivGenerator.getIv())

    fun encrypt(data: String, iv: ByteArray, encodedKey: ByteArray): Pair<ByteArray, ByteArray> {
        val plainText = data.toByteArray(Charsets.UTF_8)
        cipher.init(Cipher.ENCRYPT_MODE, SecretKeySpec(encodedKey, "AES"), IvParameterSpec(iv))
        return cipher.doFinal(plainText) to cipher.iv
    }

    fun decrypt(
        encryptedData: ByteArray,
        iv: ByteArray,
        encodedKey: ByteArray,
    ): Pair<ByteArray, ByteArray> {
        cipher.init(Cipher.DECRYPT_MODE, SecretKeySpec(encodedKey, "AES"), IvParameterSpec(iv))
        return cipher.doFinal(encryptedData) to cipher.iv
    }
}