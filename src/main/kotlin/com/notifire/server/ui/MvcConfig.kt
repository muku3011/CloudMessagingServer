package com.notifire.server.ui

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class MvcConfig : WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry
                .addResourceHandler("/**")
                .addResourceLocations(*CLASSPATH_RESOURCE_LOCATIONS)
    }

    companion object {
        private val CLASSPATH_RESOURCE_LOCATIONS = arrayOf("classpath:/static/")
    }
}