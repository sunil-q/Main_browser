package com.fastspeed.five5gbrowser.rld_bholu.main_bholu

import android.app.Activity
import android.content.Context
import android.graphics.Color

import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.util.DisplayMetrics

import android.view.Display
import android.view.View
import com.fastspeed.five5gbrowser.R

import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.google.android.gms.ads.*
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.orhanobut.logger.Logger
import com.fastspeed.five5gbrowser.rld_bholu.dialogs_bholu.BholuAdShowingDialog
import com.fastspeed.five5gbrowser.rld_bholu.interfaces_bholu.BholuNativeAdListener
import com.fastspeed.five5gbrowser.rld_bholu.interfaces_bholu.BholuNativeAdLoadListener
import com.fastspeed.five5gbrowser.rld_bholu.interfaces_bholu.BholuadmobCloseEvent
import com.fastspeed.five5gbrowser.rld_bholu.utils_bholu.BholuMyPreferenceManager
import com.fastspeed.five5gbrowser.rld_bholu.utils_bholu.beVisiblewer
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class BholuEdgeLight {
    fun showAdAfter(context: Activity, closeEventSTED: BholuadmobCloseEvent, adId: String?, dc: Int) {
        count += 1
        try {
            if (count > dc) {
                count = 0
                Logger.e("showI->")
                showAdDailog(context)
                if (!isNetworkAvailable(context)) {
                    Logger.e("return")
                    dismmisEverything(context, closeEventSTED)
                    return
                }
                if (mInterstitialAd == null) {
                    Logger.e("return null")
                    dismmisEverythingWithLoad(context, closeEventSTED, adId)
                    return
                }
                if (!context.isFinishing) {
                    Logger.e("isFinishing->")
                    if (mInterstitialAd != null ) {
                        Logger.e("show->")
                        GlobalScope.launch {
                            delay(500)
                            context.runOnUiThread {
                                BholuMyPreferenceManager(context).setLastTimeShowkaka()
                                dismisAdDialog(context)
                                if (mInterstitialAd != null) {

                                    mInterstitialAd?.show(context)
                                    mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                                        override fun onAdDismissedFullScreenContent() {
                                            // show it a second time.
                                            Logger.e("onAdClosed->")
                                            dismmisEverythingWithLoad(context, closeEventSTED, adId)
                                        }

                                        override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                                            // Called when fullscreen content failed to show.
                                            // Make sure to set your reference to null so you don't
                                            // show it a second time.
                                            Logger.e("onAdFailedToLoad->")
                                            dismmisEverything(context, closeEventSTED)
                                        }

                                        override fun onAdShowedFullScreenContent() {
                                            // Called when fullscreen content is shown.
                                            Logger.e( "The ad was shown.")
                                        }
                                    }

                                } else {
                                    dismmisEverythingWithLoad(context, closeEventSTED, adId)
                                }

                            }
                        }

                    } else {
//                        Logger.e("loadednot->")
                        dismmisEverythingWithLoad(context, closeEventSTED, adId)
                    }
                } else {
                    dismmisEverything(context, closeEventSTED)
                }
            } else {
//                Logger.e("finisf->")
                dismmisEverything(context, closeEventSTED)
            }
        } catch (e: Exception) {
            Logger.e("Exception->")
            dismmisEverythingWithLoad(context, closeEventSTED, adId)
        }

    }

    fun showIntrestrialAdsBholu(context: Activity, closeEventSTED: BholuadmobCloseEvent, adId: String?) {
        val millisInDay = (1000 * 60 * 60 * 24).toLong()

        val dcFinal = BholuMyPreferenceManager(context).showCountBholu
        val dc = 0
        val dynamicDays = BholuMyPreferenceManager(context).dynamicDaysgoa //2
        val dynamicShows = BholuMyPreferenceManager(context).dynamicShowsgoa//true
        val firstTime = BholuMyPreferenceManager(context).installTimegoa//install time

        val currentTime = System.currentTimeMillis()
        val diffrance = currentTime - firstTime
        val diffranceFinal = diffrance / millisInDay

        if (dynamicShows) {

            if (diffranceFinal >= dynamicDays) {
                // normal
                showAdAfter(context, closeEventSTED, adId, dcFinal)
            } else {
                // everytime
                showAdAfter(context, closeEventSTED, adId, dc)
            }
        } else {
            if (diffranceFinal >= dynamicDays) {
                // everytime
                showAdAfter(context, closeEventSTED, adId, dc)
            } else {
                // normal
                showAdAfter(context, closeEventSTED, adId, dcFinal)
            }
        }


    }

    fun showAdInHomeScreen(context: Activity, closeEventSTED: BholuadmobCloseEvent, adId: String?) {
        try {
            if(!BholuMyPreferenceManager(context).homeAdShowgoa){
                closeEventSTED.setAdmobCloseEventwer()
                return
            }

            if(adId == null){
                closeEventSTED.setAdmobCloseEventwer()
                return
            }

            val adShowDiloagH = BholuAdShowingDialog(context, context.getString(R.string.showingad))
            adShowDiloagH!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            adShowDiloagH!!.setCanceledOnTouchOutside(false)
            adShowDiloagH.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            if (!context.isFinishing && adShowDiloagH != null) {
                adShowDiloagH.show()
            }
            if (!isNetworkAvailable(context)) {
                if ( !context.isFinishing && adShowDiloagH.isShowing) {
                    adShowDiloagH.cancel()
                }
                closeEventSTED.setAdmobCloseEventwer()
                return
            }
            if (mInterstitialAd == null) {
                GlobalScope.launch {
                    delay(5000)
                    showHaD(context, adShowDiloagH, closeEventSTED, adId)
                }
            } else {
                if (!context.isFinishing) {
                    GlobalScope.launch {
                        delay(500)
                        context.runOnUiThread {
                            if (mInterstitialAd != null) {
                                showHaD(context, adShowDiloagH, closeEventSTED, adId)
                            } else {
                                GlobalScope.launch {
                                    delay(4500)
                                    showHaD(context, adShowDiloagH, closeEventSTED, adId)
                                }
                            }
                        }
                    }
                } else {
                    if (!context.isFinishing && adShowDiloagH.isShowing) {
                        adShowDiloagH.cancel()
                    }
                    closeEventSTED.setAdmobCloseEventwer()
                }
            }

        } catch (e: Exception) {
            closeEventSTED.setAdmobCloseEventwer()
        }
    }

    fun showHaD(
        context: Activity,
        adShowDiloagH: BholuAdShowingDialog,
        closeEventSTED: BholuadmobCloseEvent,
        adId: String?
    ) {
        context.runOnUiThread {
            if (!context.isFinishing && adShowDiloagH.isShowing) {
                adShowDiloagH.cancel()
            }
            if (mInterstitialAd != null) {

                mInterstitialAd?.show(context)
                mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        // show it a second time.
                        if (!context.isFinishing && adShowDiloagH.isShowing) {
                            adShowDiloagH.cancel()
                        }
                        closeEventSTED.setAdmobCloseEventwer()
                        loadInterstitialAds(context, adId)
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        // Called when fullscreen content failed to show.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                        if (!context.isFinishing && adShowDiloagH.isShowing) {
                            adShowDiloagH.cancel()
                        }
                        closeEventSTED.setAdmobCloseEventwer()
                    }

                    override fun onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        Logger.e( "The ad was shown.")
                    }
                }
            } else {
                if (!context.isFinishing && adShowDiloagH.isShowing) {
                    adShowDiloagH.cancel()
                }
                loadInterstitialAds(context, adId)
                closeEventSTED.setAdmobCloseEventwer()
            }
        }
    }


    fun dismmisEverything(context: Activity, closeEventSTED: BholuadmobCloseEvent) {
        dismisAdDialog(context)
        closeEventSTED.setAdmobCloseEventwer()
    }

    fun dismmisEverythingWithLoad(
        context: Activity,
        closeEventSTED: BholuadmobCloseEvent,
        adId: String?
    ) {
        dismisAdDialog(context)
        closeEventSTED.setAdmobCloseEventwer()
        loadInterstitialAds(context, adId)
    }

    fun showAdDailog(context: Activity) {
        if (!context.isFinishing && adShowDiloagSTED != null) {
            adShowDiloagSTED.show()
        }
    }

    fun dismisAdDialog(context: Activity) {
        if (!context.isFinishing && adShowDiloagSTED != null && adShowDiloagSTED.isShowing) {
            adShowDiloagSTED.cancel()
        }
    }

    /** Which layout
     * @author Hp
     * @param which = 1 - R.layout.native_single_start
     * @param which = 2 - R.layout.native_g_dialog
     * @param which = 3 - R.layout.native_single_gad_unified
     * @param which = 4 - R.layout.native_single_gad_vd
     */
    fun showAndLoadGoogleNativeBholu(
        activity: Activity?,
        nadId: String?,
        nativeAdContainerG: FrameLayout?,
        wantToShow: Boolean = true,
        which: Int,
        nativeEventSTED: BholuNativeAdListener

    ) {
        nativeAdContainer = nativeAdContainerG


        if (gnativeAd != null) {
            if (nativeAdContainerG != null) {
                if (wantToShow) {
                    try {
                        if (activity != null) {
                            showNativeGoogle(activity, nativeAdContainerG, which, nativeEventSTED)
                            loadGoogleNative(activity, nadId, null)
                        } else {
                            nativeEventSTED.setNativeFailedwer()
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                        nativeEventSTED.setNativeFailedwer()
                    }
                } else {
                    loadGoogleNative(activity, nadId, object : BholuNativeAdLoadListener {
                        override fun setNativeSuccesswer() {
                        }

                        override fun setNativeFailedwer() {
                        }
                    })
                }
            } else {
                nativeEventSTED.setNativeFailedwer()
            }

        } else {
            loadGoogleNative(activity, nadId, object : BholuNativeAdLoadListener {
                override fun setNativeSuccesswer() {
                    if (activity != null && nativeAdContainerG != null) {
                        showNativeGoogle(activity, nativeAdContainerG, which, nativeEventSTED)
                        loadGoogleNative(activity, nadId, null)
                    } else {
                        nativeEventSTED.setNativeFailedwer()
                    }
                }

                override fun setNativeFailedwer() {
                    nativeEventSTED.setNativeFailedwer()
                }
            })
        }

    }

    fun showNativeGoogle(
        activity: Activity,
        nativeAdContainerG: FrameLayout,
        which: Int,
        nativeEventSTED: BholuNativeAdListener
    ) {
        when (which) {
            1 -> {
                nativeAdContainerG.beVisiblewer()
                Logger.e("native Show-> 1")
                val adView = activity.layoutInflater.inflate(
                    R.layout.native_single_start_bholu,
                    null
                ) as NativeAdView
//                    adView.isHardwareAccelerated
                BholuNativeGoogle().populateUnifiedNativeAdViewStartGoa(gnativeAd, adView)
                nativeAdContainerG.removeAllViews()
                nativeAdContainerG.addView(adView)
                nativeEventSTED.setNativeSuccesswer()
            }
            2 -> {
                nativeAdContainerG.beVisiblewer()
                Logger.e("native Show-> 2")
                val adView = activity.layoutInflater.inflate(
                    R.layout.native_g_dialog_bholu,
                    null
                ) as NativeAdView
//                    adView.isHardwareAccelerated
                BholuNativeGoogle().populateUnifiedNativeAdViewDGoa(gnativeAd, adView)
                nativeAdContainerG.removeAllViews()
                nativeAdContainerG.addView(adView)
                nativeEventSTED.setNativeSuccesswer()
            }
            3 -> {
                nativeAdContainerG.beVisiblewer()
                Logger.e("native Show-> 3")
                val adView = activity.layoutInflater.inflate(
                    R.layout.native_single_gad_unified_bholu,
                    null
                ) as NativeAdView
//                    adView.isHardwareAccelerated
                BholuNativeGoogle().populateUnifiedNativeAdViewUnifiGoa(gnativeAd, adView)
                nativeAdContainerG.removeAllViews()
                nativeAdContainerG.addView(adView)
                nativeEventSTED.setNativeSuccesswer()
            }
            4 -> {
                nativeAdContainerG.beVisiblewer()
                Logger.e("native Show-> 4")
                val adView = activity.layoutInflater.inflate(
                    R.layout.native_single_gad_vd_bholu,
                    null
                ) as NativeAdView
//                    adView.isHardwareAccelerated
                BholuNativeGoogle().populateUnifiedNativeAdViewUnifiGoa(gnativeAd, adView)
                nativeAdContainerG.removeAllViews()
                nativeAdContainerG.addView(adView)
                nativeEventSTED.setNativeSuccesswer()

            }
        }
    }


    fun showBannerAdBholu(
        activity: Activity,
        closeEventSTED: BholuadmobCloseEvent,
        BannerID: String?,
        adContainer: LinearLayout,
    ) {
        try {
            if (mAdView != null) {
                adContainer.visibility = View.VISIBLE
//                Logger.e("adContainer ---")
                if (adContainer.childCount > 0)
                    adContainer.removeAllViews()

                adContainer.addView(mAdView)

                mAdView?.adListener = object : AdListener() {
                    override fun onAdFailedToLoad(i: LoadAdError) {
                        super.onAdFailedToLoad(i)
                        closeEventSTED.setFailedwer()
                        onLoadagainBanner(activity, closeEventSTED, BannerID)
                    }

                    override fun onAdClosed() {
                        super.onAdClosed()
                        Logger.e("onAdClosed ---")
                    }

                    override fun onAdLoaded() {
                        super.onAdLoaded()
                        closeEventSTED.setSuccesswer()
                        Logger.e("onAdLoaded --- Baner")
                    }

                }
                onLoadagainBanner(activity, closeEventSTED, BannerID)


            } else {
                Logger.e("no adContainer ---")
                onLoadagainBanner(activity, closeEventSTED, BannerID)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            onLoadagainBanner(activity, closeEventSTED, BannerID)
        }
    }


    fun onLoadagainBanner(activity: Activity, closeEventSTED: BholuadmobCloseEvent, BannerID: String?) {
        closeEventSTED.setAdmobCloseEventwer()
        loadbanner(activity, BannerID, closeEventSTED)
    }


    companion object {
        var count = 0
        lateinit var adShowDiloagSTED: BholuAdShowingDialog
        var mInterstitialAd: InterstitialAd? = null
        var gnativeAd: NativeAd? = null
        var mAdView: AdView? = null
        var nativeAdContainer: ViewGroup? = null

        @JvmStatic
        fun loadInterstitialAds(activity: Activity, fId: String?) {
            if (fId != null && isNetworkAvailable(activity)) {
                Logger.e("why ?")
                nowLoadFull(activity, fId)
            } else {
                Logger.e("why not?")
                mInterstitialAd = null
            }
        }

        fun nowLoadFull(activity: Activity, fId: String) {
            try {
                Logger.e("fId->")
                val adRequest = AdRequest.Builder().build()
                InterstitialAd.load(activity, fId, adRequest,
                    object : InterstitialAdLoadCallback() {
                        override fun onAdLoaded(ad: InterstitialAd) {
                            // The mInterstitialAd reference will be null until
                            // an ad is loaded.
                            Logger.e("load->")

                            mInterstitialAd = ad
                            ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                                override fun onAdDismissedFullScreenContent() {
                                }

                                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                                    mInterstitialAd = null
                                }

                                override fun onAdShowedFullScreenContent() {
                                }
                            }
                        }

                        override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                            // Handle the error
                            val error: String = String.format(
                                Locale.ENGLISH,
                                "domain: %s, code: %d, message: %s",
                                loadAdError.domain,
                                loadAdError.code,
                                loadAdError.message
                            )
                            Logger.e("Fload->${error}")
                            mInterstitialAd = null

                        }
                    })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @JvmStatic
        fun setAdShowDialogBholu(context: Activity) {
            adShowDiloagSTED = BholuAdShowingDialog(context, context.getString(R.string.showingad))
            adShowDiloagSTED.requestWindowFeature(Window.FEATURE_NO_TITLE)
            adShowDiloagSTED.setCanceledOnTouchOutside(false)
            adShowDiloagSTED.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        fun loadGoogleNative(
            activity: Activity?,
            nadId: String?,
            nativeAdLoadListenerSTED: BholuNativeAdLoadListener?
        ) {
            if (activity != null && nadId != null) {
                val videoOptions =
                    VideoOptions.Builder().setStartMuted(true).build()

                val adLoader = AdLoader.Builder(activity, nadId)
                    .forNativeAd { ad : NativeAd ->
                        gnativeAd?.destroy()
                        gnativeAd = ad
                    }
                    .withAdListener(object : AdListener() {
                        override fun onAdFailedToLoad(errorCode: LoadAdError) {
                            Logger.e("native roror->${errorCode.message}")
                            nativeAdLoadListenerSTED?.setNativeFailedwer()
                        }

                        override fun onAdLoaded() {
                            super.onAdLoaded()
                            Logger.e("native success")
                            nativeAdLoadListenerSTED?.setNativeSuccesswer()
                        }

                        override fun onAdClicked() {
                            super.onAdClicked()
                            Logger.e("native clickeds")

                        }
                    })
                    .withNativeAdOptions(NativeAdOptions.Builder().setVideoOptions(videoOptions).build())
                    .build()

                adLoader.loadAd(AdRequest.Builder().build())
            } else {
                nativeAdLoadListenerSTED?.setNativeFailedwer()
            }
        }

        fun loadbanner(activity: Activity, BannerID: String?, closeEventSTED: BholuadmobCloseEvent?) {
            if (!activity.isFinishing && BannerID != null) {
                mAdView = AdView(activity)

                mAdView?.setAdSize(getAdSize(activity))
                mAdView?.adUnitId = BannerID

                val adRequest = AdRequest.Builder().build()

                mAdView?.loadAd(adRequest)
                mAdView?.adListener = object : AdListener() {
                    override fun onAdFailedToLoad(i: LoadAdError) {
                        super.onAdFailedToLoad(i)
                        Logger.e("onAdFailedToLoad onAdFailedToLoad-${i.message}")
                        mAdView = null
                    }

                    override fun onAdClosed() {
                        super.onAdClosed()
                        Logger.e("onAdClosed ---")
                    }

                    override fun onAdLoaded() {
                        super.onAdLoaded()
                        closeEventSTED?.setSuccesswer()
//                        Logger.e("onAdLoaded ---")
                    }
                }
            } else {
                mAdView = null
            }
        }

        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
        }

        private fun getAdSize(activityNow: Activity): AdSize {
            // Step 2 - Determine the screen width (less decorations) to use for the ad width.
            val display: Display = activityNow.windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)

            val widthPixels = outMetrics.widthPixels.toFloat()
            val density = outMetrics.density

            val adWidth = (widthPixels / density).toInt()

            // Step 3 - Get adaptive ad size and return for setting on the ad view.
            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activityNow, adWidth)
        }

    }


}