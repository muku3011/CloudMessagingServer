package com.notifire.server.service

import com.notifire.server.dao.ServerRepository
import com.notifire.server.model.Server
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ServerService {

    @Autowired
    lateinit var serverRepository: ServerRepository

    fun getAllServers(): List<Server> {
        return serverRepository.findAll().toMutableList()
    }

    fun addServer(server: Server) {
        serverRepository.save(server)
    }

    fun getServerKey(serverUrl: String): Optional<Server> {
        return serverRepository.findById(serverUrl)
    }

    fun deleteServer(servers: List<String>) {
        servers.forEach {
            serverRepository.deleteById(it)
        }
    }

}