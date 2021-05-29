package com.notifire.server.schedule

import com.notifire.server.configuration.SchedulerConfig
import com.notifire.server.model.UserMessage
import com.notifire.server.service.MessageService
import com.notifire.server.service.UserService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ScheduledJob {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var messageService: MessageService

    @Autowired
    lateinit var schedulerConfig: SchedulerConfig

    private val logger = KotlinLogging.logger {}

    @Scheduled(cron = "\${application.scheduler.cron-regex}")
    fun scheduleFixedDelayTask() {
        logger.info { "Scheduled Job: executing ..." }

        userService.getAllUsers()
        for (user in userService.getAllUsers()) {
            logger.info { "Executing for user: ${user.userName}" }
            val userMessage =
                UserMessage(user.userName, "", schedulerConfig.title, schedulerConfig.body)
            messageService.sendMessage(userMessage)
        }
    }
}
