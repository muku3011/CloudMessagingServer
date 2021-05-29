package com.notifire.server.model

import io.swagger.annotations.ApiModelProperty

data class UserMessage(
    @ApiModelProperty(
        position = 1,
        required = true,
        value = "User to which message need to be sent",
        example = "Mukesh"
    )
    val userName: String,
    @ApiModelProperty(
        position = 2,
        required = true,
        value = "Url of the server which will be used for sending the message",
        example = "www.google.com"
    )
    val serverUrl: String,
    @ApiModelProperty(position = 3, required = true, value = "Message title/subject", example = "Cloud message")
    val title: String,
    @ApiModelProperty(position = 4, required = true, value = "Message content", example = "My first cloud message")
    val body: String
)
