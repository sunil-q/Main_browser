package com.fastspeed.five5gbrowser.browser_bholu;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class BholuViewPager2Adapter extends FragmentStateAdapter {
    private static final String TAGBholu = "web:viewpager";
    static List<BholuBrowserFragment> listBholu = new ArrayList<>();
    ViewPagerListenerBholu viewPagerListenerBholu;
    private boolean isPrivateBholu;
    private String urlBholu;

    public BholuViewPager2Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public List<BholuBrowserFragment> getListBholu() {
        return listBholu;
    }

    public void setList(List<BholuBrowserFragment> list) {
        this.listBholu = list;
    }

    public ViewPagerListenerBholu getViewPagerListnear() {
        return viewPagerListenerBholu;
    }

    public void setViewPagerListenerBholu(ViewPagerListenerBholu viewPagerListenerBholu) {
        this.viewPagerListenerBholu = viewPagerListenerBholu;
    }

    public BholuBrowserFragment getItemAtBholu(int position) {
        return (listBholu.get(position));
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d(TAGBholu, "createFragment: " + isPrivateBholu + urlBholu);

        BholuBrowserFragment browserFragmentBholu = new BholuBrowserFragment();

        browserFragmentBholu.setArgs(isPrivateBholu, urlBholu);
        browserFragmentBholu.setBrowserFragmentListnerBholu(new BholuBrowserFragment.BrowserFragmentListnerBholu() {
            @Override
            public void setUrlBholu(String url) {
                viewPagerListenerBholu.setUrlBholu(url);
            }

            @Override
            public void setWebObjBholu(WebView webObj) {
                viewPagerListenerBholu.setWebObjBholu(webObj);
            }

            @Override
            public void addTabBholu() {
                viewPagerListenerBholu.addTabBholu();
            }

            @Override
            public void openTabListBholu(Bitmap bitmap, String title) {
                Log.d(TAGBholu, "openTabList: " + title);
                browserFragmentBholu.setTitleBholu(title);
                browserFragmentBholu.setBitmapBholu(bitmap);
                viewPagerListenerBholu.openTabListBholu(bitmap, title);
            }

            @Override
            public void openBookmarksBholu() {
                viewPagerListenerBholu.openBookmarksBholu();
            }

            @Override
            public void openIncognitoBholu() {
                viewPagerListenerBholu.openIntigoBholu();
            }

            @Override
            public void openHistoryBholu() {
                viewPagerListenerBholu.openHistoryBholu();
            }
        });
        return browserFragmentBholu;
    }

    @Override
    public int getItemCount() {
        return listBholu.size();
    }

    public void addItemBholu(BholuBrowserFragment webFragment, boolean isprivate, String url) {
        this.isPrivateBholu = isprivate;
        this.urlBholu = url;
        Log.d(TAGBholu, "addItem: ");
        listBholu.add(webFragment);
        notifyItemInserted(listBholu.size() - 1);
    }

    public void removePageBholu(BholuBrowserFragment newFragment, int pos) {
        listBholu.remove(newFragment);
        notifyItemRemoved(pos);
    }

    public interface ViewPagerListenerBholu {
        void setUrlBholu(String url);

        void setWebObjBholu(WebView webObj);

        void addTabBholu();

        void openTabListBholu(Bitmap bitmap, String title);

        void openIntigoBholu();

        void openBookmarksBholu();

        void openHistoryBholu();
    }
}