package com.serenetunnel.app.server

data class SelectedVpnServer(
    val server: VpnServer,
    val config: VpnConfigResponse
)
