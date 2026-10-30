package com.serenetunnel.app.access

data class AccessState(
    val allowed: Boolean,
    val type: AccessType,
    val serverTime: Long,
    val expiresAt: Long?,
    val remainingSeconds: Long
)
