package com.vinibank.backend

import org.springframework.stereotype.Component
import java.util.Base64

@Component
class EncoderProvider {

    fun encode(toEncode: ByteArray) : String {
        return Base64.getEncoder().encodeToString(toEncode)
    }

    fun decode(toDecode: String?) : ByteArray {
        return Base64.getDecoder().decode(toDecode)
    }

    fun decode(toDecode: ByteArray) : ByteArray {
        return Base64.getDecoder().decode(toDecode)
    }
}