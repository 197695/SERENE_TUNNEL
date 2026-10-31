package com.serenetunnel.app.vpnc6
import android.app.Application
import android.app.NotificationManager
import android.os.Looper
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
@RunWith(RobolectricTestRunner::class)
@Config(sdk=[28],application=Application::class)
class VpnNotificationStopTest {
    private fun field(service:SereneVpnService,name:String,value:Any?) {
        SereneVpnService::class.java.getDeclaredField(name).apply { isAccessible=true }.set(service,value)
    }
    private fun method(service:SereneVpnService,name:String,vararg params:Pair<Class<*>,Any?>) {
        SereneVpnService::class.java.getDeclaredMethod(name,*params.map { it.first }.toTypedArray()).apply { isAccessible=true }.invoke(service,*params.map { it.second }.toTypedArray())
    }
    @Test fun completedFailedAttemptRemovesNotificationAfterEarlyIdleCheck() {
        val controller=Robolectric.buildService(SereneVpnService::class.java).create();val service=controller.get()
        try {
            field(service,"runtimeState",SereneVpnService.RuntimeConnectionState.DISCONNECTED)
            field(service,"activeConnectRequestId","attempt")
            SereneVpnService.showConnectingNotification(service)
            val manager=shadowOf(service.getSystemService(NotificationManager::class.java))
            method(service,"finishServiceIfIdle",Long::class.javaPrimitiveType!! to 0L)
            shadowOf(Looper.getMainLooper()).idle()
            assertNotNull(manager.getNotification(6001))
            method(service,"completeConnectRequest",String::class.java to "attempt",C6VpnResult::class.java to C6VpnResult.Disconnected)
            shadowOf(Looper.getMainLooper()).idle()
            assertNull(manager.getNotification(6001))
        } finally { controller.destroy();shadowOf(Looper.getMainLooper()).idle() }
    }
    @Test fun queuedConnectingUpdateCannotReappearAfterStop() {
        val controller=Robolectric.buildService(SereneVpnService::class.java).create();val service=controller.get()
        try {
            field(service,"runtimeState",SereneVpnService.RuntimeConnectionState.CONNECTING)
            method(service,"updateNotification",String::class.java to "Tentative")
            field(service,"stopping",true)
            field(service,"runtimeState",SereneVpnService.RuntimeConnectionState.DISCONNECTED)
            method(service,"stopForegroundCompat")
            shadowOf(Looper.getMainLooper()).idle()
            assertNull(shadowOf(service.getSystemService(NotificationManager::class.java)).getNotification(6001))
        } finally { controller.destroy();shadowOf(Looper.getMainLooper()).idle() }
    }
}
