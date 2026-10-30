package com.serenetunnel.app.ui.home

import android.content.Context
import com.serenetunnel.app.backend.BarkaBackendClient
import com.serenetunnel.app.core.AppContainer
import com.serenetunnel.app.vpnc6.C6VpnController
import com.serenetunnel.app.vpnprofile.VpnProfileRepository

object HomeRuntimeFactory {

    fun create(
        context: Context,
        container: AppContainer
    ): HomeRuntime {

        val backendClient = BarkaBackendClient(context)

        val c6VpnController = C6VpnController(context)

        return HomeRuntime(
            accessController = HomeAccessController(
                context = context,
                backendClient = backendClient
            ),
            serverController = HomeServerController(
                serverManager = container.vpnServerManager
            ),
            vpnCoordinator = HomeVpnCoordinator(
                profileRepository = VpnProfileRepository(backendClient, context),
                c6VpnController = c6VpnController
            )
        )
    }
}
