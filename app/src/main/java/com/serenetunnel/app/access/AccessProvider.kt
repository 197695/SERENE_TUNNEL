package com.serenetunnel.app.access

interface AccessProvider {
    fun getAccessState(): AccessState
}
