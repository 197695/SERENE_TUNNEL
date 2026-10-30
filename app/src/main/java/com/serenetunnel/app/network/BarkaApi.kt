package com.serenetunnel.app.network

import com.serenetunnel.app.access.AccessCheckRequest
import com.serenetunnel.app.access.AccessState
import com.serenetunnel.app.access.ActivationRequest
import com.serenetunnel.app.access.ActivationResult
import com.serenetunnel.app.trial.TrialSession
import com.serenetunnel.app.trial.TrialStartRequest

interface BarkaApi {

    fun checkAccess(
        request: AccessCheckRequest
    ): ApiResult<AccessState>

    fun startTrial(
        request: TrialStartRequest
    ): ApiResult<TrialSession>

    fun activateCode(
        request: ActivationRequest
    ): ApiResult<ActivationResult>
}
