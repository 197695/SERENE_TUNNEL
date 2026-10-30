package com.serenetunnel.app.vpnc6

import android.content.Context
import java.io.File

object NativeCoreLocator {
    fun xray(context: Context): File = nativeFile(context, "libserene_xray.so")
    fun dnstt(context: Context): File = nativeFile(context, "libserene_dnstt.so")

    private fun nativeFile(context: Context, name: String): File {
        val file = File(context.applicationInfo.nativeLibraryDir, name)
        if (!file.isFile) {
            throw IllegalStateException("Moteur natif C6 absent : $name")
        }
        return file
    }
}
