package com.notifire.server.filter

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(ctx: CorsRegistry) {
        ctx.addMapping("/***")
                .allowedMethods("OPTIONS", "GET", "PUT", "POST", "DELETE")
                .allowedOrigins("http://localhost:4200", "https://cloud-messaging-ui.herokuapp.com")
                .allowedHeaders("*")
    }
}