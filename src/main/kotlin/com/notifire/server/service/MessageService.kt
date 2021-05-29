package com.notifire.server.service

import com.google.api.core.ApiFuture
import com.google.api.core.ApiFutureCallback
import com.google.api.core.ApiFutures
import com.google.auth.oauth2.GoogleCredentials
import com.google.common.util.concurrent.MoreExecutors
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import com.notifire.server.model.UserMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class MessageService {

    @Autowired
    lateinit var userService: UserService

    @PostConstruct
    fun init() {
        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.getApplicationDefault())
            .build()

        FirebaseApp.initializeApp(options)
    }

    fun sendMessage(userMessage: UserMessage): ResponseEntity<String> {
        val registrationToken = userService.getUserToken(userMessage.userName).get().userToken
        println("Registration Token: $registrationToken")

        val message = Message.builder()
            .setToken(registrationToken)
            .setNotification(
                Notification.builder()
                    .setTitle(userMessage.title)
                    .setBody(userMessage.body)
                    .build()
            )
            .build()

        val response: ApiFuture<String> = FirebaseMessaging.getInstance().sendAsync(message)
        println("Successfully sent message: $response")

        var responseMessage = "Successfully sent message"
        var responseCode = HttpStatus.OK

        ApiFutures.addCallback(response, object : ApiFutureCallback<String> {
                override fun onFailure(t: Throwable) {
                    responseMessage = "Message not sent, Failed!"
                    responseCode = HttpStatus.INTERNAL_SERVER_ERROR
                    println("Error: $t")
                }

                override fun onSuccess(messageId: String?) {
                    println("Published changes to Pubsub")
                }
            },
            MoreExecutors.directExecutor()
        )
        response.get()
        return ResponseEntity<String>(responseMessage, responseCode)
    }
}

