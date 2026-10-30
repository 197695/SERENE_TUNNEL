package com.serenetunnel.app.account

data class AuthenticatedAccessContext(
    val accountId: String,
    val token: String,
    val deviceId: String
)
