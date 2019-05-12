package com.notifire.server.controller

import com.notifire.server.model.Key
import com.notifire.server.service.KeyService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@Api(value = "Key", description = "Endpoint for key management", tags = ["Key management"])
class KeyController (var keyService: KeyService) {

    @RequestMapping("/key", method = [RequestMethod.POST])
    @ApiOperation(value = "Add key", notes = "Add key for encrypting and decrypting data into database", tags = ["Key management"])
    fun addKey(@RequestBody key: Key): String {
        return keyService.addKey(key)
    }
}