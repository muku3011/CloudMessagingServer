package com.notifire.server.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "application.scheduler.message")
class SchedulerConfig {
    lateinit var title: String
    lateinit var body: String
}
