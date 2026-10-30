package com.serenetunnel.app.vpn

sealed class VpnAccessResult {

    data object Connected : VpnAccessResult()

    data object Disconnected : VpnAccessResult()

    data object AccessDenied : VpnAccessResult()

    data class Error(
        val message: String
    ) : VpnAccessResult()
}
