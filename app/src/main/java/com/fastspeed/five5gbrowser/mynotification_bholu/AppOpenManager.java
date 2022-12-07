package com.fastspeed.five5gbrowser.mynotification_bholu;

import static androidx.lifecycle.Lifecycle.Event.ON_START;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.fastspeed.five5gbrowser.rld_bholu.utils_bholu.BholuMyPreferenceManager;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

import java.util.Date;
import java.util.Objects;

public class AppOpenManager implements LifecycleObserver, Application.ActivityLifecycleCallbacks {
    private static final String LOG_TAGaaa = "AppOpenManager";
    private AppOpenAd appOpenAdaaa = null;
    private AppOpenAd.AppOpenAdLoadCallback loadCallbackaaa;
    private final BholuApp myapplicationaaa;
    private Activity currentActivityaaa;
    private static boolean isShowingAdaaa = false;
    private long loadTimeaaa = 0;

    public AppOpenManager(BholuApp myApplication) {
        this.myapplicationaaa = myApplication;
        this.myapplicationaaa.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(ON_START)
    public void onStart() {
            showAdIfAvailableaaa();
        Log.d(LOG_TAGaaa, "onStart");
    }

    public void fetchAdaaa() {
        // We will implement this below.
        if (isAdAvailableaaa()) {
            return;
        }

        loadCallbackaaa = new AppOpenAd.AppOpenAdLoadCallback() {

            @Override
            public void onAdLoaded(AppOpenAd ad) {
                AppOpenManager.this.appOpenAdaaa = ad;
                AppOpenManager.this.loadTimeaaa = (new Date()).getTime();
            }


            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                Log.e("onAdFailedToLoad", "====>>>>" + loadAdError.getMessage());
                // Handle the error.
            }

        };
        AdRequest requestaaa = getAdRequestaaa();

        BholuMyPreferenceManager myPreferenceManageraaa = new BholuMyPreferenceManager(currentActivityaaa);

        if(myPreferenceManageraaa.openIdwer()!=null){
            AppOpenAd.load(currentActivityaaa, Objects.requireNonNull(myPreferenceManageraaa.openIdwer()), requestaaa, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallbackaaa);
        }
    }

    private AdRequest getAdRequestaaa() {
        return new AdRequest.Builder().build();
    }

    private boolean wasLoadTimeLessThanNHoursAgoaaa(long numHours) {
        long dateDifferenceaaa = (new Date()).getTime() - this.loadTimeaaa;
        long numMilliSecondsPerHouraaa = 3600000;
        return (dateDifferenceaaa < (numMilliSecondsPerHouraaa * numHours));
    }

    public boolean isAdAvailableaaa() {
        return appOpenAdaaa != null && wasLoadTimeLessThanNHoursAgoaaa(4);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        currentActivityaaa = activity;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        currentActivityaaa = activity;
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        currentActivityaaa = null;
    }

    public void showAdIfAvailableaaa() {
        // Only show ad if there is not already an app open ad currently showing
        // and an ad is available.
        if (!isShowingAdaaa && isAdAvailableaaa()) {
            Log.d(LOG_TAGaaa, "Will show ad.");

            FullScreenContentCallback fullScreenContentCallback =
                    new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Set the reference to null so isAdAvailable() returns false.
                            AppOpenManager.this.appOpenAdaaa = null;
                            isShowingAdaaa = false;
                            fetchAdaaa();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            isShowingAdaaa = true;
                        }
                    };

            appOpenAdaaa.setFullScreenContentCallback(fullScreenContentCallback);
            appOpenAdaaa.show(currentActivityaaa);



        } else {
            Log.d(LOG_TAGaaa, "Can not show ad.");
            fetchAdaaa();
        }
    }
}
