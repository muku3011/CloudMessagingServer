package com.notifire.server.secure

import com.notifire.server.model.Key
import com.notifire.server.service.KeyService
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class EncryptDecryptTest {

    private var encryptDecrypt: EncryptDecrypt = EncryptDecrypt()

    @Before
    fun initMocks() {
        val key = Key("Bar12345Bar12345", "RandomInitVector", "TestKey")
        encryptDecrypt.keyService = KeyService()
        encryptDecrypt.keyService.addKey(key)
    }

    @Test
    fun encryptTest() {
        val planeData = "It's working"
        val encryptedData: String = encryptDecrypt.convertToDatabaseColumn(planeData)!!
        val decryptedData = encryptDecrypt.convertToEntityAttribute(encryptedData)
        Assert.assertEquals(planeData, decryptedData)
    }
}

