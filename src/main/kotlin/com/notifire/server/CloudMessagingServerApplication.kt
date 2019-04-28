package com.notifire.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CloudMessagingServerApplication

fun main(args: Array<String>) {
	runApplication<CloudMessagingServerApplication>(*args)
}
