package com.notifire.server.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.builders.ResponseBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.service.Response
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.Collections.emptyList
import java.util.function.Predicate

@Configuration
@EnableSwagger2
class SwaggerConf {

    companion object {
        const val API_TITLE = "Simple cloud messaging application"
        const val API_DESCRIPTION = "A simple application to manage user, server and to send messages"
        const val API_VERSION = "1.0"
        const val API_TOS_URL = ""
        const val API_CONTACT_NAME = "Mukesh Joshi"
        const val API_CONTACT_URL = ""
        const val API_CONTACT_EMAIL = "mukesh.bciit@gmail.com"
        const val API_LICENSE = ""
        const val API_LICENSE_URL = ""
    }

    @Bean
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
            .select()

            // No filter on api
            .apis(RequestHandlerSelectors.any())

            // Filter api based on packages
            .apis(RequestHandlerSelectors.basePackage("com.notifire.server"))

            // Filter api based on PathSelectors with regular expressions
            .paths(Predicate.not(PathSelectors.regex("/error.*")))
            //.paths(PathSelectors.regex("(?!/error.*).*"))
            //.paths(PathSelectors.ant("/foos/*"))

            // When no path selector filter is needed
            //.paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo())

            //Custom Methods Response Messages
            .useDefaultResponseMessages(false)
            .globalResponses(HttpMethod.GET, customResponse())
            .globalResponses(HttpMethod.POST, customResponse())
            .globalResponses(HttpMethod.DELETE, customResponse())
            .globalResponses(HttpMethod.PUT, customResponse())

    private fun apiInfo(): ApiInfo {

        return ApiInfo(
                API_TITLE,
                API_DESCRIPTION,
                API_VERSION,
                API_TOS_URL,
                Contact(API_CONTACT_NAME, API_CONTACT_URL, API_CONTACT_EMAIL),
                API_LICENSE, API_LICENSE_URL, emptyList())
    }

    private fun customResponse(): List<Response> {
        return listOf(
                ResponseBuilder()
                        .code("500")
                        .description("Server internal error")
                        .build(),
                ResponseBuilder()
                        .code("403")
                        .description("Forbidden!")
                        .build())
    }
}