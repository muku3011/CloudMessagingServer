package com.notifire.server.model

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.UniqueElements
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "USER")
data class User(

    @Id
    @ApiModelProperty(
        position = 1,
        required = true,
        value = "User to whom message need to be delivered",
        example = "Mukesh"
    )
    val userName: String,

    @UniqueElements
    @ApiModelProperty(position = 2, required = true, value = "User token (secure)", example = "")
    //@Convert(converter = EncryptDecrypt::class)
    val userToken: String
)
