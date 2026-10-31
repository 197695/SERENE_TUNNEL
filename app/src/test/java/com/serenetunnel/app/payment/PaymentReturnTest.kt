package com.serenetunnel.app.payment

import android.app.Application
import android.content.Intent
import android.net.Uri
import com.serenetunnel.app.MainActivity
import com.serenetunnel.app.reseller.ResellerPurchaseActivity
import com.serenetunnel.app.reseller.ResellerPurchaseStore
import com.serenetunnel.app.subscription.PendingPaymentStore
import com.serenetunnel.app.subscription.SubscriptionActivity
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
class PaymentReturnTest {
    private val context get() = RuntimeEnvironment.getApplication()
    private fun route(reference: String): Intent {
        val controller = Robolectric.buildActivity(PaymentReturnActivity::class.java,
            Intent(Intent.ACTION_VIEW, Uri.parse("serenetunnel://payment-return?reference=$reference"))).create()
        return shadowOf(controller.get()).nextStartedActivity.also { controller.destroy() }
    }
    @Test fun ownedSubscriptionReturnsToReceiptWithoutTrustingTheLinkAsPayment() {
        PendingPaymentStore.save(context, "SERENE-owned-ref", "24h")
        assertEquals(SubscriptionActivity::class.java.name, route("SERENE-owned-ref").component?.className)
        assertNull(PendingPaymentStore.confirmedCode(context))
    }
    @Test fun ownedResellerReturnsToItsAccountAndPreservesOwnerKey() {
        val state = ResellerPurchaseStore.read(context).put("reference", "BTR-owned-ref")
        ResellerPurchaseStore.write(context, state)
        val result = route("BTR-owned-ref")
        assertEquals(ResellerPurchaseActivity::class.java.name, result.component?.className)
        assertTrue(result.getBooleanExtra("payment_return", false))
        assertEquals(state.getString("owner_key"), ResellerPurchaseStore.read(context).getString("owner_key"))
    }
    @Test fun foreignReferenceCannotReplaceTheStoredPurchase() {
        PendingPaymentStore.save(context, "SERENE-owned-ref", "24h")
        assertEquals(MainActivity::class.java.name, route("SERENE-foreign-ref").component?.className)
        assertEquals("SERENE-owned-ref", PendingPaymentStore.reference(context))
    }
}
