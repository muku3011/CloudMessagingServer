package com.notifire.server.model

import com.notifire.server.secure.EncryptDecrypt
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.URL
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity(name = "SERVER")
data class Server(

        @Id
        @NotNull(message = "Null value not acceptable for server url")
        @NotBlank(message = "Empty value not acceptable for server url")
        @URL(message = "Invalid server url")
        @ApiModelProperty(position = 1, required = true, value = "Url of the server which will be used to send message", example = "www.google.com")
        val serverUrl: String,

        @ApiModelProperty(position = 2, required = true, value = "Server key (secure)", example = "")
        @Convert(converter = EncryptDecrypt::class)
        val serverKey: String
)