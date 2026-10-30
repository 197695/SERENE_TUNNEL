package com.serenetunnel.app.account

sealed class LoginState {

    data object LoggedOut : LoginState()

    data class LoggedIn(
        val accountId: String
    ) : LoginState()

    data class Error(
        val message: String
    ) : LoginState()
}
