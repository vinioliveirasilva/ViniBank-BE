package com.vinibank.backend

import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec

class IvGenerator(
    private val cipher: Cipher,
) {

    private fun generateIv(): IvParameterSpec {
        val blockSize = cipher.blockSize
        val iv = ByteArray(blockSize)
        SecureRandom.getInstanceStrong().nextBytes(iv)
        return IvParameterSpec(iv)
    }

    fun getIv(): ByteArray =
        try {
            generateIv().iv
        } catch (e: Exception) {
            e.printStackTrace()
            ByteArray(16)
        }
}