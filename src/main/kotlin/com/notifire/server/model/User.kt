package com.notifire.server.model

import com.notifire.server.secure.EncryptDecrypt
import io.swagger.annotations.ApiModelProperty
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "USER")
data class User(

        @Id
        @ApiModelProperty(position = 1, required = true, value = "User to whom message need to be delivered", example = "Mukesh")
        val userName: String,

        @ApiModelProperty(position = 2, required = true, value = "User token (secure)", example = "")
        @Convert(converter = EncryptDecrypt::class)
        val userToken: String
)
