package com.notifire.server.controller

import com.notifire.server.model.Server
import com.notifire.server.service.ServerService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@Api(value = "message", description = "Endpoint for server management", tags = ["Server management"])
class ServerController (var serverService: ServerService) {

    @RequestMapping("/server", method = [RequestMethod.GET])
    @ApiOperation(value = "Get all servers", notes = "Get list of all server")
    fun getAllServer() : List<Server> {
        return serverService.getAllServers()
    }

    @RequestMapping("/server", method = [RequestMethod.POST])
    @ApiOperation(value = "Add server", notes = "Add a new server")
    fun addServer(@Valid @RequestBody server: Server) {
        return serverService.addServer(server)
    }

    @RequestMapping("/server", method = [RequestMethod.DELETE])
    @ApiOperation(value = "Delete server", notes = "Delete a server")
    fun deleteServer(@RequestBody servers: List<String>) {
        return serverService.deleteServer(servers)
    }
}