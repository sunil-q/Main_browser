package com.fastspeed.five5gbrowser.mynotification_bholu

import android.app.Activity
import android.content.SharedPreferences
import androidx.multidex.MultiDexApplication
import com.fastspeed.five5gbrowser.BuildConfig
import com.google.android.gms.ads.MobileAds
import com.onesignal.OneSignal

class BholuApp : MultiDexApplication(){
    private var appOpenManagerkaka: AppOpenManager? = null
    private var currentActivity: Activity? = null


    companion object {
        lateinit var instancekaka: BholuApp
            private set
        @JvmField
        var sharedPreferenceskaka: SharedPreferences? = null
        private const val ONESIGNAL_APP_IDgoa = "b86ef07c-cc02-4947-a56a-51a4b74f33df"
        const val buttonTextgoa = "ZC9"
        const val buttonActionUrlgoa = "ZC10"
        const val text1goa = "ZC11"
        const val text2goa = "ZC12"
        const val imageUrlgoa = "ZC13"
        const val isInAppMessagegoa = "isInAppMessage"
    }



    override fun onCreate() {
        super.onCreate()
        appOpenManagerkaka = AppOpenManager(this)
        sharedPreferenceskaka = getSharedPreferences(packageName, MODE_PRIVATE)
        MobileAds.initialize(this) { }

        //region OneSignal
        if (BuildConfig.DEBUG) {
            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        }
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_IDgoa);
        OneSignal.setNotificationOpenedHandler(GoaOneSignalNotificationOpenedHandler(this))
        OneSignal.setNotificationWillShowInForegroundHandler(
            GoaMyNotificationWillShowInForegroundHandler(this)
        )
        //endregion

    }



}