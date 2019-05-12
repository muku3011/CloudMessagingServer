package com.notifire.server.service

import com.notifire.server.dao.UserRepository
import com.notifire.server.model.User
import com.notifire.server.secure.EncryptDecrypt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    fun getAllUsers(): MutableIterable<User> {
        return userRepository.findAll()
    }

    fun addUser(user: User) {
        userRepository.save(user)
    }

    fun getUserToken(userName: String): Optional<User> {
        return userRepository.findById(userName)
    }

    fun deleteUser(users: List<String>) {
        users.forEach {
            userRepository.deleteById(it)
        }
    }
}