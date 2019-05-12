package com.notifire.server.configuration

import com.google.common.base.Predicates
import com.google.common.collect.Lists.newArrayList
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RequestMethod
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.builders.ResponseMessageBuilder
import springfox.documentation.schema.ModelRef
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.service.ResponseMessage
import java.util.Collections.emptyList


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
            .paths(Predicates.not(PathSelectors.regex("/error.*")))
            //.paths(PathSelectors.regex("(?!/error.*).*"))
            //.paths(PathSelectors.ant("/foos/*"))

            // When no path selector filter is needed
            //.paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo())

            //Custom Methods Response Messages
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET, customResponse())
            .globalResponseMessage(RequestMethod.POST, customResponse())
            .globalResponseMessage(RequestMethod.DELETE, customResponse())
            .globalResponseMessage(RequestMethod.PUT, customResponse())

    private fun apiInfo(): ApiInfo {

        return ApiInfo(
                API_TITLE,
                API_DESCRIPTION,
                API_VERSION,
                API_TOS_URL,
                Contact(API_CONTACT_NAME, API_CONTACT_URL, API_CONTACT_EMAIL),
                API_LICENSE, API_LICENSE_URL, emptyList())
    }

    private fun customResponse(): List<ResponseMessage> {
        return newArrayList(
                ResponseMessageBuilder()
                        .code(500)
                        .message("Server internal error")
                        .responseModel(ModelRef("string"))
                        .build(),
                ResponseMessageBuilder()
                        .code(403)
                        .message("Forbidden!")
                        .build())
    }
}