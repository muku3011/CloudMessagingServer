package com.notifire.server.controller

import com.notifire.server.schema.Message
import com.notifire.server.service.MessageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController (var messageService: MessageService) {

    @RequestMapping("/message", method = [RequestMethod.POST])
    fun sendMessage(@RequestBody message: Message): ResponseEntity<String> {
        return messageService.sendMessage(message)
    }

}