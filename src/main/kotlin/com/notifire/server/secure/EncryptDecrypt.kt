package com.notifire.server.secure

import com.notifire.server.service.KeyService
import org.apache.tomcat.util.codec.binary.Base64
import javax.crypto.spec.SecretKeySpec
import javax.crypto.spec.IvParameterSpec
import org.apache.tomcat.util.codec.binary.Base64.encodeBase64String
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.crypto.Cipher
import javax.persistence.AttributeConverter

@Service
class EncryptDecrypt : AttributeConverter<String, String> {

    @Autowired
    lateinit var keyService: KeyService

    override fun convertToDatabaseColumn(/*key: String, initVector: String,*/ value: String): String? {
        try {
            val key = keyService.getKey()!!.key
            val initVector = keyService.getKey()!!.initialVector

            val iv = IvParameterSpec(initVector.toByteArray(charset("UTF-8")))
            val skeySpec = SecretKeySpec(key.toByteArray(charset("UTF-8")), "AES")

            val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv)

            val encrypted = cipher.doFinal(value.toByteArray())

            return encodeBase64String(encrypted)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    override fun convertToEntityAttribute(/*key: String, initVector: String,*/ encrypted: String): String? {
        try {
            val key = keyService.getKey()!!.key
            val initVector = keyService.getKey()!!.initialVector

            val iv = IvParameterSpec(initVector.toByteArray(charset("UTF-8")))
            val skeySpec = SecretKeySpec(key.toByteArray(charset("UTF-8")), "AES")

            val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv)

            val original = cipher.doFinal(Base64.decodeBase64(encrypted))

            return String(original)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

}