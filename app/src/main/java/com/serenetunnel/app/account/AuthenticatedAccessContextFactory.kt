package com.serenetunnel.app.account

import android.content.Context
import com.serenetunnel.app.device.DeviceIdentity

class AuthenticatedAccessContextFactory(
    private val context: Context,
    private val sessionStore: AccountSessionStore
) {

    fun create(): AuthenticatedAccessContext? {
        val session = sessionStore.get() ?: return null

        return AuthenticatedAccessContext(
            accountId = session.accountId,
            token = session.token,
            deviceId = DeviceIdentity.getDeviceId(context)
        )
    }
}
