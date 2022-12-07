package com.fastspeed.five5gbrowser.browser_bholu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.fastspeed.five5gbrowser.BholuSessionManager;
import com.fastspeed.five5gbrowser.R;
import com.fastspeed.five5gbrowser.databinding.ActivityBookamrksBholuBinding;
import com.fastspeed.five5gbrowser.rld_bholu.interfaces_bholu.BholuadmobCloseEvent;
import com.fastspeed.five5gbrowser.rld_bholu.main_bholu.BholuEdgeLight;
import com.fastspeed.five5gbrowser.rld_bholu.utils_bholu.BholuMyPreferenceManager;

import java.util.ArrayList;

public class BholuBookamrksActivity extends AppCompatActivity implements BholuBookamrksAdapter.OnBookmarkClickListnerBholu {

    public static final int BOOKMAR_ACTIVITY_REQUEST_CODEBholu = 1002;
    ActivityBookamrksBholuBinding bindingBholu;
    private BholuSessionManager sessonManagerBholu;
    private ArrayList<String> listBholu = new ArrayList<>();
    BholuEdgeLight spyAdsGlobalClassEveryTime;
    BholuMyPreferenceManager werMyPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingBholu = DataBindingUtil.setContentView(this, R.layout.activity_bookamrks_bholu);
        sessonManagerBholu = new BholuSessionManager(this);

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

        initViewBholu();
        initListnearBholu();

    }

    private void initListnearBholu() {
        bindingBholu.tvClearBholu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spyAdsGlobalClassEveryTime.showIntrestrialAdsBholu(BholuBookamrksActivity.this, new BholuadmobCloseEvent() {
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
        ArrayList<String> b = sessonManagerBholu.getBookamrksBholu();
        for (int i = 0; i <= b.size() - 1; i++) {
            sessonManagerBholu.toggleBookmarkBholu(b.get(i));
        }
        if (bookamrksAdapter != null) {
            bookamrksAdapter.notifyDataSetChanged();
        }
        initViewBholu();
    }

    BholuBookamrksAdapter bookamrksAdapter;

    private void initViewBholu() {
        listBholu.clear();
        listBholu = sessonManagerBholu.getBookamrksBholu();
        if (!listBholu.isEmpty()) {
            bookamrksAdapter = new BholuBookamrksAdapter(listBholu, this);
            bindingBholu.rvBookamrkBholu.setAdapter(bookamrksAdapter);
        } else {
            Toast.makeText(this, "Bookmarks not found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBookmarkClick(int position, String url, String work) {
        if (work.equals("DELETE")) {
            int i = sessonManagerBholu.toggleBookmarkBholu(url);
            if (i == 0) {
                Toast.makeText(this, "Bookmark Removed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Bookmarked", Toast.LENGTH_SHORT).show();
            }

            spyAdsGlobalClassEveryTime.showIntrestrialAdsBholu(BholuBookamrksActivity.this, new BholuadmobCloseEvent() {
                @Override
                public void setFailedwer() {

                }

                @Override
                public void setSuccesswer() {

                }

                @Override
                public void setAdmobCloseEventwer() {
                    initViewBholu();
                }
            }, werMyPreferenceManager.fIdBholu());
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
        BholuEdgeLight.setAdShowDialogBholu(BholuBookamrksActivity.this);
    }

    public void onclickBackBholu(View view) {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}