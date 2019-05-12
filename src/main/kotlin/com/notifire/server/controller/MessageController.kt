package com.notifire.server.controller

import com.notifire.server.model.Message
import com.notifire.server.service.MessageService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@Api(value = "message", description = "Endpoint for message management", tags = ["Message management"])
class MessageController (var messageService: MessageService) {

    @RequestMapping("/message", method = [RequestMethod.POST])
    @ApiOperation(value = "Send message", notes = "Send a message to requested user", tags = ["Message management"])
    fun sendMessage(@RequestBody message: Message): ResponseEntity<String> {
        return messageService.sendMessage(message)
    }
}