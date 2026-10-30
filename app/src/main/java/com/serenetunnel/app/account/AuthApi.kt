package com.serenetunnel.app.account

import com.serenetunnel.app.network.ApiResult

interface AuthApi {

    fun login(
        request: LoginRequest
    ): ApiResult<LoginResult>

    fun logout(
        token: String
    ): ApiResult<Boolean>
}
