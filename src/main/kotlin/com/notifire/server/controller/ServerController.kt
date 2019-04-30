package com.notifire.server.controller

import com.notifire.server.schema.Server
import com.notifire.server.service.ServerService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class ServerController (var serverService: ServerService) {

    @RequestMapping("/server", method = [RequestMethod.GET])
    fun getAllServer() : Set<String> {
        return serverService.getAllServers()
    }

    @RequestMapping("/server", method = [RequestMethod.POST])
    fun addServer(@RequestBody server: Server) {
        return serverService.addServer(server)
    }

    @RequestMapping("/server/delete", method = [RequestMethod.POST])
    fun deleteServer(@RequestBody servers: List<String>) {
        return serverService.deleteServer(servers)
    }
}