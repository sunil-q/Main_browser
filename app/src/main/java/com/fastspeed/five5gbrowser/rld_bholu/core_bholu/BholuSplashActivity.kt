package com.fastspeed.five5gbrowser.rld_bholu.core_bholu

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fastspeed.five5gbrowser.BuildConfig
import com.fastspeed.five5gbrowser.browser_bholu.BholuWebActivity
import com.fastspeed.five5gbrowser.rld_bholu.BholuApiUtils
import com.fastspeed.five5gbrowser.rld_bholu.BholuConnectionManager
import com.fastspeed.five5gbrowser.rld_bholu.BholuSettingsModel
import com.fastspeed.five5gbrowser.rld_bholu.main_bholu.BholuEdgeLight
import com.fastspeed.five5gbrowser.rld_bholu.utils_bholu.BholuMyPreferenceManager
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.fastspeed.five5gbrowser.databinding.ActivitySplashBholuBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class BholuSplashActivity : AppCompatActivity() {

    var isStartScreenBholu = false;
    private lateinit var bindingBholu: ActivitySplashBholuBinding
    lateinit var prefenrencMyPreferenceManagerBholu: BholuMyPreferenceManager

    external fun stringFromJNI(): String

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isTaskRoot
            && intent.hasCategory(Intent.CATEGORY_LAUNCHER)
            && intent.action != null
            && intent.action.equals(Intent.ACTION_MAIN)
        ) {
            finish()
            return
        }
        bindingBholu = ActivitySplashBholuBinding.inflate(layoutInflater)
        setContentView(bindingBholu.root)

        prefenrencMyPreferenceManagerBholu = BholuMyPreferenceManager(this@BholuSplashActivity)
        val cd = BholuConnectionManager(this@BholuSplashActivity)
        if (cd.isConnectedBholu()) {
            getFromRestAPIBholu()
        } else {
            setNullIdBholu()
            goHomeBholu()
        }

        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    private fun getFromRestAPIBholu() {
        val BASE_URLBholu = stringFromJNI()

        val clientBholu: OkHttpClient = if (BuildConfig.DEBUG) {
            val interceptorBholu = HttpLoggingInterceptor()
            interceptorBholu.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(interceptorBholu) // remove when live it's for log
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES).build()
        } else {
            OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES).build()
        }
        val retrofitBholu: Retrofit = Retrofit.Builder().baseUrl(BASE_URLBholu)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBholu)
            .build()

        val myApiBholu = retrofitBholu.create(BholuApiUtils::class.java)

        val pkgBholu = packageName.replace(".", "_") + ".json"
        val callBholu: Call<BholuSettingsModel> = myApiBholu.getPowerswer(pkgBholu)
        callBholu.enqueue(object : Callback<BholuSettingsModel> {
            override fun onResponse(
                call: Call<BholuSettingsModel>?,
                response: retrofit2.Response<BholuSettingsModel>
            ) {
                val settingModelWer: BholuSettingsModel? = response.body()
                if (settingModelWer != null) {
                    prefenrencMyPreferenceManagerBholu.setGBannerIDBholu(settingModelWer?.googleBannerwer)
                    prefenrencMyPreferenceManagerBholu.setfIdBholu(settingModelWer?.googleInterstitialwer)
                    prefenrencMyPreferenceManagerBholu.setnadIdBholu(settingModelWer?.googleNativewer)
                    prefenrencMyPreferenceManagerBholu.setrIdBholu(settingModelWer?.googleRewardedwer)
                    prefenrencMyPreferenceManagerBholu.setopenIdBholu(settingModelWer?.googleOpenAdIdwer)

                    //region new dynamic show 2
                    prefenrencMyPreferenceManagerBholu.dynamicShowsgoa=settingModelWer.dynamicShows
                    prefenrencMyPreferenceManagerBholu.dynamicDaysgoa=settingModelWer.dynamicDays
                    if(prefenrencMyPreferenceManagerBholu.installTimegoa<=0){
                        prefenrencMyPreferenceManagerBholu.installTimegoa=System.currentTimeMillis()
                    }

                    prefenrencMyPreferenceManagerBholu.homeAdShowgoa=settingModelWer.homeAdShow

                    //endregion
                    prefenrencMyPreferenceManagerBholu.privacyPolicyBholu = settingModelWer?.privacyPolicy
                    prefenrencMyPreferenceManagerBholu.termsandConditionBholu = settingModelWer?.termsandcondition
                    prefenrencMyPreferenceManagerBholu.moreAppsBholu = settingModelWer?.moreApps
                    prefenrencMyPreferenceManagerBholu.showCountBholu = settingModelWer.showCount
                    isStartScreenBholu = settingModelWer?.isStartScreenwer == true
                }

                val appCurrentVersion: Long =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
                        packageManager.getPackageInfo(packageName, 0).longVersionCode
                    else
                        packageManager.getPackageInfo(packageName, 0).versionCode.toLong()

                val appWebVersionwer = settingModelWer?.versionwer ?: 1

                BholuEdgeLight.loadGoogleNative(this@BholuSplashActivity, prefenrencMyPreferenceManagerBholu.nadIdBholu(), null)
                BholuEdgeLight.loadbanner(this@BholuSplashActivity, prefenrencMyPreferenceManagerBholu.getGBannerIDkaka(), null)
                BholuEdgeLight.loadInterstitialAds(this@BholuSplashActivity, prefenrencMyPreferenceManagerBholu.fIdBholu())

                if (settingModelWer != null) {
                    when {
                        appWebVersionwer > appCurrentVersion -> {
                            val updateAppwer =
                                Intent(
                                    this@BholuSplashActivity,
                                    BholuWerAppUpdateActivity::class.java
                                )
                            settingModelWer.versionMessagewer?.let {
                                updateAppwer.putExtra("versionMessage", it)
                            }
                            startActivity(updateAppwer)
                            finish()
                        }
                        settingModelWer.isMaintaincewer -> {
                            val maintenancewer =
                                Intent(
                                    this@BholuSplashActivity,
                                    BholuMaintenanceActivity::class.java
                                )
                            settingModelWer.maintainceMessagewer?.let {
                                maintenancewer.putExtra("maintainceMessage", it)
                            }
                            startActivity(maintenancewer)
                            finish()
                        }
                        settingModelWer.isMovedwer -> {
                            val movedwer =
                                Intent(this@BholuSplashActivity, BholuWeMovedActivity::class.java)
                            settingModelWer.movedURLwer?.let {
                                movedwer.putExtra("movedURL", it)
                                movedwer.putExtra(
                                    "movedDescription",
                                    settingModelWer.movedDescriptionwer
                                )
                            }
                            startActivity(movedwer)
                            finish()
                        }
                        else -> {
                            goHomeBholu()
                        }

                    }
                } else {
                    goHomeBholu()
                }
            }

            override fun onFailure(call: Call<BholuSettingsModel>?, t: Throwable?) {
                setNullIdBholu()
                goHomeBholu()
            }
        })
    }

    fun setNullIdBholu() {
        prefenrencMyPreferenceManagerBholu.setGBannerIDBholu(null)
        prefenrencMyPreferenceManagerBholu.setfIdBholu(null)
        prefenrencMyPreferenceManagerBholu.setnadIdBholu(null)
        prefenrencMyPreferenceManagerBholu.setrIdBholu(null)
        prefenrencMyPreferenceManagerBholu.setopenIdBholu(null)
    }

    private fun goHomeBholu() {
//        if (isStartScreenBholu) {
//            val iBholu = Intent(this@BholuSplashActivity, BholuStartActivity::class.java)
//            startActivity(iBholu)
//            finish()
//        } else {
        startActivity(Intent(this@BholuSplashActivity, BholuWebActivity::class.java))
        finish()
//        }
    }
}