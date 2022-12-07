package com.fastspeed.five5gbrowser;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.fastspeed.five5gbrowser.browser_bholu.BholuWebActivity;
import com.fastspeed.five5gbrowser.rld_bholu.interfaces_bholu.BholuNativeAdListener;
import com.fastspeed.five5gbrowser.rld_bholu.interfaces_bholu.BholuadmobCloseEvent;
import com.fastspeed.five5gbrowser.rld_bholu.main_bholu.BholuEdgeLight;
import com.fastspeed.five5gbrowser.rld_bholu.utils_bholu.BholuMyPreferenceManager;


public class BholuStartActivity extends AppCompatActivity {

    AppCompatTextView tvStartBholu;
    BholuVerticalTextView tvRateBholu, tvShareBholu;
    BholuEdgeLight spyAdsGlobalClassEveryTime;
    BholuMyPreferenceManager werMyPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_bholu);

        werMyPreferenceManager = new BholuMyPreferenceManager(this);
        spyAdsGlobalClassEveryTime = new BholuEdgeLight();

        spyAdsGlobalClassEveryTime.showAndLoadGoogleNativeBholu(this,
                werMyPreferenceManager.nadIdBholu(),
                findViewById(R.id.adsContainerBholu),
                true,
                1,
                new BholuNativeAdListener() {

                    @Override
                    public void setNativeFailedwer() {
                        findViewById(R.id.loadContainerBholu).setVisibility(View.GONE);
                    }

                    @Override
                    public void setNativeSuccesswer() {

                    }
                });

        tvStartBholu = findViewById(R.id.tvStartBholu);
        tvShareBholu = findViewById(R.id.tvShareBholu);
        tvRateBholu = findViewById(R.id.tvRateBholu);

        tvStartBholu.setOnClickListener(view -> {

            spyAdsGlobalClassEveryTime.showIntrestrialAdsBholu(BholuStartActivity.this, new BholuadmobCloseEvent() {
                @Override
                public void setFailedwer() {

                }

                @Override
                public void setSuccesswer() {

                }

                @Override
                public void setAdmobCloseEventwer() {
                    Intent intentBholu = new Intent(BholuStartActivity.this, BholuWebActivity.class);
                    startActivity(intentBholu);
                }
            }, werMyPreferenceManager.fIdBholu());
        });

        tvShareBholu.setOnClickListener(v -> {
            Intent sendIntentBholu = new Intent();
            sendIntentBholu.setAction(Intent.ACTION_SEND);
            sendIntentBholu.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
            sendIntentBholu.setType("text/plain");
            startActivity(sendIntentBholu);
        });

        tvRateBholu.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()))));
    }

    @Override
    protected void onResume() {
        super.onResume();
        BholuEdgeLight.setAdShowDialogBholu(BholuStartActivity.this);
    }
}