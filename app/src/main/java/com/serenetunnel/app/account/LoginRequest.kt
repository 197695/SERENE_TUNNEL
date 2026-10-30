package com.serenetunnel.app.account

data class LoginRequest(
    val username: String,
    val password: String,
    val deviceId: String
)
