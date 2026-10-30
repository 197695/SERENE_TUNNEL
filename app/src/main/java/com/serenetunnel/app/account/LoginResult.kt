package com.serenetunnel.app.account

data class LoginResult(
    val success: Boolean,
    val accountId: String?,
    val token: String?,
    val message: String
)
