package com.notifire.server.service

import com.notifire.server.model.Key
import org.springframework.stereotype.Service

@Service
class KeyService {

    //FIXME Update this part with some key management mechanise
    fun addKey(key: Key): String {
        keyStore = key
        return keyStore!!.keyName
    }

    fun getKey(): Key? {
        return keyStore
    }

    companion object {
        var keyStore : Key? = null
    }
}