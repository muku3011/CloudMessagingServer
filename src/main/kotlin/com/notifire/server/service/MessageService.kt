package com.notifire.server.service

import com.notifire.server.model.Message
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.http.HttpEntity
import org.springframework.http.HttpRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.http.client.support.HttpRequestWrapper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.concurrent.CompletableFuture
import kotlin.collections.ArrayList

@Service
class MessageService {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var serverService: ServerService

    fun sendMessage(message: Message): ResponseEntity<String> {
        val body = JSONObject()
        body.put("to", userService.getUserToken(message.userName))
        body.put("priority", "high")

        val notification = JSONObject()
        notification.put("title", message.title)
        notification.put("body", message.body)

        body.put("notification", notification)

        val response = HttpEntity(body.toString())
        val pushNotification: CompletableFuture<String> = send(response, message.serverUrl)
        CompletableFuture.allOf(pushNotification)

        val firebaseResponse = pushNotification.get()
        return ResponseEntity(firebaseResponse, HttpStatus.OK)
    }

    @Async
    fun send(entity: HttpEntity<String>, serverUrl: String): CompletableFuture<String> {
        val restTemplate = RestTemplate()

        val interceptor = ArrayList<ClientHttpRequestInterceptor>()
        interceptor.add(HeaderRequestInterceptor("Authorization", "key=" + serverService.getServerKey(serverUrl)))
        interceptor.add(HeaderRequestInterceptor("Content-Type", "application/json"))
        restTemplate.interceptors = interceptor

        val firebaseResponse = restTemplate.postForObject(serverUrl, entity, String::class.java)
        return CompletableFuture.completedFuture(firebaseResponse)
    }

    private class HeaderRequestInterceptor(private var headerName: String, private var headerValue: String) : ClientHttpRequestInterceptor {

        override fun intercept(request: HttpRequest, body: ByteArray, execution: ClientHttpRequestExecution): ClientHttpResponse {
            val wrapper = HttpRequestWrapper(request)
            wrapper.headers.set(headerName, headerValue)
            return execution.execute(wrapper, body)
        }
    }
}

