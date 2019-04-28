package com.notifire.server.controller

import com.notifire.server.schema.User
import com.notifire.server.service.UserService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController (val userService: UserService) {

    @RequestMapping("/user", method = [RequestMethod.GET])
    fun getAllUser() : Set<String> {
        return userService.getAllUsers()
    }

    @RequestMapping("/user", method = [RequestMethod.POST])
    fun addUser(@RequestBody user: User) {
        return userService.addUser(user)
    }

}