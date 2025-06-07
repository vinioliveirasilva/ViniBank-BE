package com.vinibank.backend

import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.spec.X509EncodedKeySpec
import javax.crypto.KeyAgreement
import javax.crypto.interfaces.DHPublicKey
import javax.crypto.spec.DHParameterSpec

class DHExchangePartner() {

    private lateinit var keyAgreement: KeyAgreement

    private fun createPersonalDHKeypairAndInitAgreement(initKeyPairGenerator: (KeyPairGenerator) -> Unit): KeyPair {
        val keyPair = KeyPairGenerator.getInstance(ALGORITHM).apply {
            initKeyPairGenerator(this)
        }.generateKeyPair()
        initializeKeyAgreement(keyPair)
        return keyPair
    }

    fun createPersonalDHKeypairAndInitAgreement(publicKey: DHPublicKey): KeyPair =
        createPersonalDHKeypairAndInitAgreement { it.initialize(publicKey.params) }

    fun createPublicKeyFromEncodedMaterial(encoded: ByteArray): DHPublicKey {
        val keyFactory = KeyFactory.getInstance(ALGORITHM)
        return keyFactory.generatePublic(X509EncodedKeySpec(encoded)) as DHPublicKey
    }

    private fun initializeKeyAgreement(keyPair: KeyPair) {
        keyAgreement = KeyAgreement.getInstance(ALGORITHM).apply {
            init(keyPair.private)
        }
    }

    fun phaseOne(partnerPublicKey: DHPublicKey) {
        keyAgreement.doPhase(partnerPublicKey, true)
    }

    fun generateSharedSecret(): ByteArray = keyAgreement.generateSecret()

    companion object {
        const val ALGORITHM = "DH"
    }
}


fun ByteArray.asHex() = joinToString("") { "%02x".format(it) }