package com.serenetunnel.app.access

import com.serenetunnel.app.network.ApiResult
import com.serenetunnel.app.network.SereneApi
import com.serenetunnel.app.trial.TrialSession
import com.serenetunnel.app.trial.TrialStartRequest

class AccessCoordinator(
    private val api: SereneApi,
    private val accountId: String,
    private val deviceId: String
) {

    fun checkAccess(): ApiResult<AccessState> {
        return api.checkAccess(
            AccessCheckRequest(
                accountId = accountId,
                deviceId = deviceId
            )
        )
    }

    fun startTrial(): ApiResult<TrialSession> {
        return api.startTrial(
            TrialStartRequest(
                accountId = accountId,
                deviceId = deviceId
            )
        )
    }

    fun activateCode(code: String): ApiResult<ActivationResult> {
        return api.activateCode(
            ActivationRequest(
                accountId = accountId,
                deviceId = deviceId,
                code = code.trim()
            )
        )
    }
}
