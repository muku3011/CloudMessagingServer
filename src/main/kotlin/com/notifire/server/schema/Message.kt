package com.notifire.server.schema

data class Message(
        val userName: String,
        val serverUrl: String,
        val title: String,
        val body: String
)


