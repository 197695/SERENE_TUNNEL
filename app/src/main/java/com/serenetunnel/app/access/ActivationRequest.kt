package com.serenetunnel.app.access

data class ActivationRequest(
    val accountId: String,
    val deviceId: String,
    val code: String
)
