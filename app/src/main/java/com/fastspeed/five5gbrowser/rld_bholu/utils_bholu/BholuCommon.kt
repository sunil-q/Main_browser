package com.fastspeed.five5gbrowser.rld_bholu.utils_bholu

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.telephony.PhoneNumberUtils
import android.widget.Toast
import com.fastspeed.five5gbrowser.rld_bholu.main_bholu.BholuEdgeLight

object BholuCommon {
    const val TOURTIMEBholu = 10000
    val TAGBholu = "Bhavin->"
    const val fixtime5mBholu = 300000L
    const val RESULT_FORCED_CANCELBholu = 24

    @JvmField
    var PREFNAMEBholu = "JAKKAASS"

    fun killMe(activityBholu: Activity) {
        activityBholu.finishAffinity()
    }

    fun setUpadDialogwer(activityBholu: Activity) {
        try {
            BholuEdgeLight.setAdShowDialogBholu(activityBholu)
        } catch (e: Exception) {
        }
    }

    fun getAId(activityBholu: Context): String? {
        return Settings.Secure.getString(activityBholu.getContentResolver(), Settings.Secure.ANDROID_ID)
    }

    fun whatsApp(mobileNumberwtsapBholu: String?, context: Context) {
        val isWhatsappInstalledBholu = wappInstalledOrNotBholu("com.whatsapp", context)
        if (isWhatsappInstalledBholu) {
            val sendIntentBholu = Intent("android.intent.action.MAIN")
            sendIntentBholu.component =
                ComponentName("com.whatsapp", "com.whatsapp.Conversation")
            sendIntentBholu.putExtra(
                "jid", PhoneNumberUtils.stripSeparators(
                    mobileNumberwtsapBholu
                ) + "@s.whatsapp.net"
            ) //phone number without "+" prefix
            context.startActivity(sendIntentBholu)
        } else {
            val uriBholu = Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")
            val goToMarketBholu = Intent(Intent.ACTION_VIEW, uriBholu)
            Toast.makeText(
                context, "WhatsApp not Installed",
                Toast.LENGTH_SHORT
            ).show()
            context.startActivity(goToMarketBholu)
        }
    }

    private fun wappInstalledOrNotBholu(targetPackageBholu: String, context: Context): Boolean {
        val pmBholu = context.packageManager
        try {
            val infower = pmBholu.getPackageInfo(targetPackageBholu, PackageManager.GET_META_DATA)
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
        return true
    }

}