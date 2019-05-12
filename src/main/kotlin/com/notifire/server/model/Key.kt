package com.notifire.server.model

import io.swagger.annotations.ApiModelProperty

data class Key(
        @ApiModelProperty(position = 1, required = true, value = "Key value (Must not be shown)", example = "OurMostSecureKey")
        val key: String,
        @ApiModelProperty(position = 2, required = true, value = "Initial vector value (Must not be shown)", example = "OurInitialVector")
        val initialVector: String,
        @ApiModelProperty(position = 3, required = true, value = "Key name", example = "EncryptKey")
        val keyName: String
)


