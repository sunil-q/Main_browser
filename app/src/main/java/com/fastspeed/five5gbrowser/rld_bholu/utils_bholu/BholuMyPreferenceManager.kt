package com.fastspeed.five5gbrowser.rld_bholu.utils_bholu

import android.content.Context
import com.fastspeed.five5gbrowser.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BholuMyPreferenceManager @Inject constructor(@ApplicationContext private val mcontextBholu: Context?) {
    val sharedPrefBholu = mcontextBholu!!.getSharedPreferences(BholuCommon.PREFNAMEBholu, Context.MODE_PRIVATE)
    private val editorBholu = sharedPrefBholu.edit()

    private val BANNERBholu = "banner"
    private val INTERSTITALBholu = "interstital"
    private val NADIDBholu = "NADID"
    private val OPENADBholu = "openad"
    private val RIDBholu = "rid"

    private val PRIVACYPOLICYBholu = "privacyPolicy"
    private val TERMSBholu = "termsandcondition"
    private val MOREAPPSBholu = "moreApps"
    private val SHOWCOUNTBholu = "showCount"

    private val INSTALLTIMEgoa = "installtime"
    private val DYNAMICDAYSgoa = "dynamicDays"
    private val DYNAMICSHOWSgoa = "dynamicShows"
    private val HOMEADSHOWgoa = "homeAdShow"



    private fun storeStringBholu(keyBholu: String, value: String?) {
        editorBholu.run {
            putString(keyBholu, value)
            apply()
        }
    }

    private fun storeLong(keyBholu: String, value: Long) {
        editorBholu.run {
            putLong(keyBholu, value)
            apply()
        }
    }

    private fun storeInt(keyBholu: String, value: Int) {
        editorBholu.run {
            putInt(keyBholu, value)
            apply()
        }
    }

    private fun storeBoolean(keyBholu: String, value: Boolean) =
        editorBholu.run {
            putBoolean(keyBholu, value)
            apply()
        }

    private fun getString(keyBholu: String) =
        sharedPrefBholu.getString(keyBholu, "")

    fun verifyInstallerIdBholu(contextBholu: Context): Boolean {
        // A list with valid installers package name
        val validInstallers: List<String> =
            java.util.ArrayList(Arrays.asList("com.android.vending", "com.google.android.feedback"))

        // The package name of the app that has installed your app
        val installer = contextBholu.packageManager.getInstallerPackageName(contextBholu.packageName)

        // true if your app has been downloaded from Play Store
//        return installer != null && validInstallers.contains(installer)
        return true;
    }

    fun openIdwer(): String? {
        return if (BuildConfig.DEBUG) {
//            "ca-app-pub-3940256099942544/3419835294";
            null
        } else {
            if (verifyInstallerIdBholu(mcontextBholu!!)) {
                if (sharedPrefBholu.getString(OPENADBholu, null) != null)
                    sharedPrefBholu.getString(OPENADBholu, null)
                else null
            } else null
        }

    }


    fun fIdBholu(): String? {
        return if (BuildConfig.DEBUG) {
            "ca-app-pub-3940256099942544/1033173712";
//            null
        } else {
            if (verifyInstallerIdBholu(mcontextBholu!!)) {
                if (sharedPrefBholu.getString(INTERSTITALBholu, null) != null) sharedPrefBholu.getString(
                    INTERSTITALBholu,
                    null
                ) else null
            } else {
                return null
            }
        }
    }

    fun getGBannerIDkaka(): String? {
        return if (BuildConfig.DEBUG) {
            "ca-app-pub-3940256099942544/6300978111";
//            null
        } else {
            if (verifyInstallerIdBholu(mcontextBholu!!)) {
                if (sharedPrefBholu.getString(BANNERBholu, null) != null) sharedPrefBholu.getString(
                    BANNERBholu,
                    null
                ) else null
            } else null
        }
    }

    fun nadIdBholu(): String? {
        return if (BuildConfig.DEBUG) {
            "ca-app-pub-3940256099942544/2247696110";
//            null
        } else {
            if (verifyInstallerIdBholu(mcontextBholu!!)) {
                if (sharedPrefBholu.getString(NADIDBholu, null) != null) sharedPrefBholu.getString(
                    NADIDBholu,
                    null
                ) else null
            } else null
        }
    }


    fun rIdwer(): String? {
        return if (BuildConfig.DEBUG) {
            "ca-app-pub-3940256099942544/5224354917";
//            null
        } else {
            if (verifyInstallerIdBholu(mcontextBholu!!)) {
                if (sharedPrefBholu.getString(RIDBholu, null) != null) sharedPrefBholu.getString(
                    RIDBholu,
                    null
                ) else null
            } else null
        }

    }




    fun setfIdBholu(idBholu: String?) {
        storeStringBholu(INTERSTITALBholu, idBholu)
    }



    fun setopenIdBholu(idBholu: String?) {
        storeStringBholu(OPENADBholu, idBholu)
    }


    fun setGBannerIDBholu(idBholu: String?) {
        storeStringBholu(BANNERBholu, idBholu)
    }



    fun setrIdBholu(idBholu: String?) {
        storeStringBholu(RIDBholu, idBholu)
    }


    fun setnadIdBholu(idBholu: String?) {
        storeStringBholu(NADIDBholu, idBholu)
    }




    fun setLastTimeShowkaka() {
        val timekaka = System.currentTimeMillis()
        val editor1kaka = sharedPrefBholu.edit()
        editor1kaka.putLong("last_updateTimeShow", timekaka)
        editor1kaka.commit()
    }

    var showCountBholu: Int
        get() = sharedPrefBholu.getInt(SHOWCOUNTBholu, 0)
        set(setCount) {
            val editor1Bholu = sharedPrefBholu.edit()
            editor1Bholu.putInt(SHOWCOUNTBholu, setCount)
            editor1Bholu.apply()
        }

    var privacyPolicyBholu: String?
        get() = sharedPrefBholu.getString(PRIVACYPOLICYBholu, "http://www.google.com")
        set(setprivacyPolicy) = storeStringBholu(PRIVACYPOLICYBholu, setprivacyPolicy)

    var termsandConditionBholu: String?
        get() = sharedPrefBholu.getString(TERMSBholu, "http://www.google.com")
        set(setTermsandCondition) = storeStringBholu(TERMSBholu, setTermsandCondition)

    var moreAppsBholu: String?
        get() = sharedPrefBholu.getString(
            MOREAPPSBholu,
            "https://play.google.com/store/apps/developer?id="
        )
        set(setMoreApps) = storeStringBholu(MOREAPPSBholu, setMoreApps)

    var installTimegoa: Long
        get() = sharedPrefBholu.getLong(INSTALLTIMEgoa, 0)
        set(setInstallTime) {
            val editor1goa = sharedPrefBholu.edit()
            editor1goa.putLong(INSTALLTIMEgoa, setInstallTime)
            editor1goa.apply()
        }


    var dynamicDaysgoa: Int
        get() = sharedPrefBholu.getInt(DYNAMICDAYSgoa, 0)
        set(setDynamicDays) {
            val editor1goa = sharedPrefBholu.edit()
            editor1goa.putInt(DYNAMICDAYSgoa, setDynamicDays)
            editor1goa.apply()
        }

    var dynamicShowsgoa: Boolean
        get() = sharedPrefBholu.getBoolean(DYNAMICSHOWSgoa, false)
        set(setDynamicShows) {
            val editor1goa = sharedPrefBholu.edit()
            editor1goa.putBoolean(DYNAMICSHOWSgoa, setDynamicShows)
            editor1goa.apply()
        }


    var homeAdShowgoa: Boolean
        get() = sharedPrefBholu.getBoolean(HOMEADSHOWgoa, false)
        set(setHomeAdShow) {
            val editor1goa = sharedPrefBholu.edit()
            editor1goa.putBoolean(HOMEADSHOWgoa, setHomeAdShow)
            editor1goa.apply()
        }


}