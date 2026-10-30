package com.serenetunnel.app.core

import android.content.Context
import com.serenetunnel.app.account.RealAuthApi
import com.serenetunnel.app.account.SecureAccountSessionStore
import com.serenetunnel.app.network.HttpJsonClient
import com.serenetunnel.app.network.RealSereneApi
import com.serenetunnel.app.server.RealVpnServerApi
import com.serenetunnel.app.server.VpnServerManager
import com.serenetunnel.app.server.VpnServerRepository

class AppContainer(
    context: Context
) {

    val httpClient = HttpJsonClient()

    val sessionStore =
        SecureAccountSessionStore(context)

    val authApi =
        RealAuthApi(httpClient)

    val accessApi =
        RealSereneApi(httpClient)

    val vpnServerApi =
        RealVpnServerApi(httpClient)

    val vpnServerRepository =
        VpnServerRepository(
            api = vpnServerApi,
            sessionStore = sessionStore
        )

    val vpnServerManager =
        VpnServerManager(
            context = context,
            repository = vpnServerRepository
        )
}
