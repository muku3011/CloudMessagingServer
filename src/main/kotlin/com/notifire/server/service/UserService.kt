package com.notifire.server.service

import com.notifire.server.schema.User
import org.springframework.stereotype.Service

@Service
class UserService {

    val userMap = mutableMapOf<String, String>()

    fun getAllUsers(): Set<String> {
        return userMap.keys
    }

    fun addUser(user: User) {
        userMap[user.userName] = user.userToken
        return
    }

    fun getUserToken(userName: String): String {
        return userMap.getValue(userName)
    }
}