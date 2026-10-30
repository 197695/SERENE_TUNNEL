package com.serenetunnel.app.ui.home

import android.content.Context
import android.content.Intent
import com.serenetunnel.app.account.LoginActivity
import com.serenetunnel.app.ipfinder.IpFinderActivity
import com.serenetunnel.app.journal.JournalActivity
import com.serenetunnel.app.settings.SettingsActivity
import com.serenetunnel.app.subscription.ActivationActivity
import com.serenetunnel.app.subscription.SubscriptionActivity

class HomeNavigator(
    private val context: Context
) {

    fun openLogin() {
        context.startActivity(
            Intent(context, LoginActivity::class.java)
        )
    }

    fun openIpFinder() {
        context.startActivity(
            Intent(context, IpFinderActivity::class.java)
        )
    }

    fun openJournal() {
        context.startActivity(
            Intent(context, JournalActivity::class.java)
        )
    }

    fun openSettings() {
        context.startActivity(
            Intent(context, SettingsActivity::class.java)
        )
    }

    fun openSubscription() {
        context.startActivity(
            Intent(context, SubscriptionActivity::class.java)
        )
    }

    fun openActivation() {
        context.startActivity(
            Intent(context, ActivationActivity::class.java)
        )
    }
}
