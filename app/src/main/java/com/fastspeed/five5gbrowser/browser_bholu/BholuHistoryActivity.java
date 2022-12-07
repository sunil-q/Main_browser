package com.fastspeed.five5gbrowser.browser_bholu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.fastspeed.five5gbrowser.BholuSessionManager;
import com.fastspeed.five5gbrowser.R;
import com.fastspeed.five5gbrowser.databinding.ActivityHistoryBholuBinding;
import com.fastspeed.five5gbrowser.rld_bholu.interfaces_bholu.BholuadmobCloseEvent;
import com.fastspeed.five5gbrowser.rld_bholu.main_bholu.BholuEdgeLight;
import com.fastspeed.five5gbrowser.rld_bholu.utils_bholu.BholuMyPreferenceManager;

import java.util.ArrayList;

public class BholuHistoryActivity extends AppCompatActivity implements BholuHistoryAdapter.OnHistoryClickListnearBholu {

    private static final String TAGBholu = "historyact";
    public static final int HISTORY_ACTIVITY_CODEBholu = 1001;
    ActivityHistoryBholuBinding bindingBholu;
    private BholuSessionManager sessonManagerBholu;
    private ArrayList<String> listBholu = new ArrayList<>();
    BholuEdgeLight spyAdsGlobalClassEveryTime;
    BholuMyPreferenceManager werMyPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingBholu = DataBindingUtil.setContentView(this, R.layout.activity_history_bholu);

        werMyPreferenceManager = new BholuMyPreferenceManager(this);
        spyAdsGlobalClassEveryTime = new BholuEdgeLight();

        spyAdsGlobalClassEveryTime.showBannerAdBholu(this, new BholuadmobCloseEvent() {
            @Override
            public void setFailedwer() {
                findViewById(R.id.adViewkaka).setVisibility(View.GONE);
            }

            @Override
            public void setSuccesswer() {

            }

            @Override
            public void setAdmobCloseEventwer() {

            }
        }, werMyPreferenceManager.getGBannerIDkaka(), findViewById(R.id.adViewkaka));

        ImageView iv_backBholu = findViewById(R.id.iv_backBholu);
        iv_backBholu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        sessonManagerBholu = new BholuSessionManager(this);
        initViewBholu();
        initListnearBholu();

    }

    private void initListnearBholu() {

        bindingBholu.tvClearBholu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spyAdsGlobalClassEveryTime.showIntrestrialAdsBholu(BholuHistoryActivity.this, new BholuadmobCloseEvent() {
                    @Override
                    public void setFailedwer() {

                    }

                    @Override
                    public void setSuccesswer() {

                    }

                    @Override
                    public void setAdmobCloseEventwer() {
                        clearBookmarksBholu();
                    }
                }, werMyPreferenceManager.fIdBholu());
            }
        });
    }

    private void clearBookmarksBholu() {
        ArrayList<String> b = sessonManagerBholu.getHistoryBholu();
        Log.d(TAGBholu, "clearBookmarks: " + b.size());
        for (int i = 0; i < b.size(); i++) {
            sessonManagerBholu.removefromHistoryBholu(b.get(i));
        }
        Log.d(TAGBholu, "clearBookmarks:size2  " + sessonManagerBholu.getHistoryBholu().size());
        initViewBholu();
    }

    private void initViewBholu() {
        listBholu.clear();
        listBholu = sessonManagerBholu.getHistoryBholu();
        Log.d(TAGBholu, "clearBookmarks:size3  " + sessonManagerBholu.getHistoryBholu().size());
        Log.d(TAGBholu, "clearBookmarks:size34  " + listBholu.size());

        BholuHistoryAdapter historyAdapter = new BholuHistoryAdapter(listBholu, this);
        bindingBholu.rvHistoryBholu.setAdapter(historyAdapter);
    }

    @Override
    public void onHistoryClick(int position, String url, String work) {
        if (work.equals("DELETE")) {

            if (position % 3 == 0) {

                spyAdsGlobalClassEveryTime.showIntrestrialAdsBholu(BholuHistoryActivity.this, new BholuadmobCloseEvent() {
                    @Override
                    public void setFailedwer() {

                    }

                    @Override
                    public void setSuccesswer() {

                    }

                    @Override
                    public void setAdmobCloseEventwer() {
                        sessonManagerBholu.removefromHistoryBholu(url);
                        initViewBholu();
                    }
                }, werMyPreferenceManager.fIdBholu());
            } else {
                sessonManagerBholu.removefromHistoryBholu(url);
                initViewBholu();
            }
        }
        if (work.equals("OPEN")) {
            Intent intentBholu = new Intent();
            intentBholu.putExtra("url", url);

            setResult(RESULT_OK, intentBholu);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        BholuEdgeLight.setAdShowDialogBholu(BholuHistoryActivity.this);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}