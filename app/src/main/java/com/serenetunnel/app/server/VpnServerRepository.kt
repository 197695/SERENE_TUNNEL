package com.serenetunnel.app.server

import com.serenetunnel.app.account.AccountSessionStore
import com.serenetunnel.app.network.ApiResult

class VpnServerRepository(
    private val api: VpnServerApi,
    private val sessionStore: AccountSessionStore
) {

    fun getServers(): ApiResult<List<VpnServer>> {
        val session = sessionStore.get()
            ?: return ApiResult.Error("Utilisateur non connecté")

        return api.getServers(session.token)
    }

    fun getConfig(
        serverId: String,
        deviceId: String
    ): ApiResult<VpnConfigResponse> {

        val session = sessionStore.get()
            ?: return ApiResult.Error("Utilisateur non connecté")

        return api.getConfig(
            token = session.token,
            serverId = serverId,
            deviceId = deviceId
        )
    }
}
