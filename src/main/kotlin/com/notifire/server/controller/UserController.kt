package com.notifire.server.controller

import com.notifire.server.model.User
import com.notifire.server.service.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@Api(value = "message", description = "Endpoint for user management", tags = ["User management"])
class UserController (val userService: UserService) {

    @RequestMapping("/user", method = [RequestMethod.GET])
    @ApiOperation(value = "Get all users", notes = "Get list of all users")
    fun getAllUser() : MutableIterable<User> {
        return userService.getAllUsers()
    }

    @RequestMapping("/user", method = [RequestMethod.POST])
    @ApiOperation(value = "Add user", notes = "Add a new user")
    fun addUser(@RequestBody user: User) {
        return userService.addUser(user)
    }

    @RequestMapping("/user/delete", method = [RequestMethod.DELETE])
    @ApiOperation(value = "Delete user", notes = "Delete a user")
    fun deleteServer(@RequestBody users: List<String>) {
        return userService.deleteUser(users)
    }
}