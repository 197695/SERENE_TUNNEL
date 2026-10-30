package com.serenetunnel.app.access

data class AccessCheckRequest(
    val accountId: String,
    val deviceId: String
)
