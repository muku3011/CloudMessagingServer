package com.notifire.server.service

import com.notifire.server.schema.Server
import org.springframework.stereotype.Service

@Service
class ServerService {

    val serverMap = mutableMapOf<String, String>()

    fun getAllServers(): Set<String> {
        return serverMap.keys
    }

    fun addServer(server: Server) {
        serverMap[server.serverUrl] = server.serverKey
    }

    fun getServerKey(serverUrl: String): String {
        return serverMap.getValue(serverUrl)
    }

}