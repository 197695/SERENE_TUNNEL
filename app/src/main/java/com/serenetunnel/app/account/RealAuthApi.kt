package com.serenetunnel.app.account

import com.serenetunnel.app.network.ApiResult
import com.serenetunnel.app.network.HttpJsonClient
import com.serenetunnel.app.network.JsonUtils
import org.json.JSONObject

class RealAuthApi(
    private val client: HttpJsonClient
) : AuthApi {

    override fun login(
        request: LoginRequest
    ): ApiResult<LoginResult> {
        return try {
            val json = JSONObject()
                .put("username", request.username)
                .put("password", request.password)
                .put("deviceId", request.deviceId)

            val response = client.post(
                endpoint = "auth/login",
                json = json.toString()
            )

            if (!response.isSuccessful) {
                return ApiResult.Error("Identifiants incorrects")
            }

            val body = JSONObject(response.body)

            ApiResult.Success(
                LoginResult(
                    success = body.getBoolean("success"),
                    accountId = JsonUtils.nullableString(body, "accountId"),
                    token = JsonUtils.nullableString(body, "token"),
                    message = body.optString("message", "")
                )
            )
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Erreur de connexion")
        }
    }

    override fun logout(
        token: String
    ): ApiResult<Boolean> {
        return try {
            val response = client.post(
                endpoint = "auth/logout",
                json = "{}",
                token = token
            )

            ApiResult.Success(response.isSuccessful)
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Erreur de déconnexion")
        }
    }
}
