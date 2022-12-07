package com.fastspeed.five5gbrowser.browser_bholu;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;

import com.fastspeed.five5gbrowser.BholuSessionManager;
import com.fastspeed.five5gbrowser.R;
import com.fastspeed.five5gbrowser.databinding.BottomsheetMenuBholuBinding;
import com.fastspeed.five5gbrowser.databinding.FragmentBrowserBholuBinding;
import com.fastspeed.five5gbrowser.rld_bholu.core_bholu.BholuSplashActivity;
import com.fastspeed.five5gbrowser.rld_bholu.utils_bholu.BholuMyPreferenceManager;
import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class BholuBrowserFragment extends Fragment {
    private static final String GOOGLE_URLBholu = "https://www.google.com";
    private static final String TAGBholu = "web:browserfrag";
    FragmentBrowserBholuBinding bindingBholu;
    WebViewClient webViewClientBholu;
    boolean loadingFinishedBholu = true;
    boolean redirectBholu = false;
    int position = 0;
    BrowserFragmentListnerBholu browserFragmentListnerBholu;
    Bitmap bitmapBholu;
    String titleBholu;
    public static WebView webViewBholu;
    private MaterialProgressBar progressBarBholu;
    private BottomSheetDialog bottomSheetDialogBholu;
    private BholuSessionManager sessionManagerBholu;
    private boolean isPrivateBholu = false;
    private String urlBholu;

    public BrowserFragmentListnerBholu getBrowserFragmentListnerBholu() {
        return browserFragmentListnerBholu;
    }

    public void setBrowserFragmentListnerBholu(BrowserFragmentListnerBholu browserFragmentListnerBholu) {
        this.browserFragmentListnerBholu = browserFragmentListnerBholu;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bindingBholu = DataBindingUtil.inflate(inflater, R.layout.fragment_browser_bholu, container, false);

        sessionManagerBholu = new BholuSessionManager(getActivity());
        return bindingBholu.getRoot();
    }

    public void back() {
        if (webViewBholu.canGoBack()) {
            bindingBholu.webviewBholu.goBack();
        }
    }

    public void load(String s) {
        Log.d(TAGBholu, "load: ");
        loadUrlBholu(s);
    }

    public String getTitleBholu() {
        return titleBholu;
    }

    public void setTitleBholu(String titleBholu) {
        this.titleBholu = titleBholu;
    }

    public Bitmap getBitmapBholu() {
        return bitmapBholu;
    }

    public void setBitmapBholu(Bitmap bitmapBholu) {
        this.bitmapBholu = bitmapBholu;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        webViewBholu = bindingBholu.webviewBholu;
        progressBarBholu = bindingBholu.progressbarBholu;
        progressBarBholu.setIndeterminate(true);
        progressBarBholu.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            progressBarBholu.setIndeterminateTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.colorPrimary)));
        }

        if (browserFragmentListnerBholu == null) {
            requireActivity().startActivity(new Intent(requireActivity(), BholuSplashActivity.class));
            requireActivity().finishAffinity();
            return;
        }

        if (isPrivateBholu) {
            if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
                WebSettingsCompat.setSafeBrowsingEnabled(bindingBholu.webviewBholu.getSettings(), true);
            }
            CookieManager.getInstance().setAcceptCookie(false);

//Make sure no caching is done
            webViewBholu.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            webViewBholu.getSettings().setAppCacheEnabled(false);
            webViewBholu.clearHistory();
            webViewBholu.clearCache(true);
            webViewBholu.clearSslPreferences();

            webViewBholu.clearMatches();

            //Make sure no autofill for Forms/ user-name password happens for the app
            webViewBholu.clearFormData();
            webViewBholu.getSettings().setSavePassword(false);
            webViewBholu.getSettings().setSaveFormData(false);

            registerForContextMenu(webViewBholu);
            loadUrlBholu(GOOGLE_URLBholu);
            if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
                // Turning on the light mode
                WebSettingsCompat.setForceDark(bindingBholu.webviewBholu.getSettings(), WebSettingsCompat.FORCE_DARK_ON);
            }
        } else {
            webViewClientBholu = new WebViewClient();
            if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
                WebSettingsCompat.setSafeBrowsingEnabled(bindingBholu.webviewBholu.getSettings(), false);
            }

            webViewBholu.getSettings().setLoadsImagesAutomatically(true);
            webViewBholu.getSettings().setJavaScriptEnabled(true);
            webViewBholu.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webViewBholu.setWebViewClient(webViewClientBholu);
            webViewBholu.setWebChromeClient(new WebChromeClient());

            Log.d(TAGBholu, "onActivityCreated: ");

            registerForContextMenu(webViewBholu);

            if (urlBholu != null && !urlBholu.equals("")) {
                loadUrlBholu(urlBholu);
            } else {
                loadUrlBholu(GOOGLE_URLBholu);
            }

            if (browserFragmentListnerBholu != null) {
                browserFragmentListnerBholu.setWebObjBholu(webViewBholu);
            } else {
                requireActivity().startActivity(new Intent(requireActivity(), BholuSplashActivity.class));
                requireActivity().finishAffinity();
            }
        }

        bindingBholu.swipeBholu.setOnRefreshListener(() -> loadUrlBholu(bindingBholu.webviewBholu.getUrl()));
        initListnerBholu();
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);

        final WebView.HitTestResult webViewHitTestResult = webViewBholu.getHitTestResult();

        if (webViewHitTestResult.getType() == WebView.HitTestResult.IMAGE_TYPE ||
                webViewHitTestResult.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {

            contextMenu.setHeaderTitle("Download Image From Below");

            contextMenu.add(0, 1, 0, "Save - Download Image")
                    .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {

                            String DownloadImageURL = webViewHitTestResult.getExtra();

                            if (URLUtil.isValidUrl(DownloadImageURL)) {

//                                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(DownloadImageURL));
//                                request.allowScanningByMediaScanner();
//                                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//                                DownloadManager downloadManager = (DownloadManager) requireActivity().getSystemService(DOWNLOAD_SERVICE);
//                                downloadManager.enqueue(request);

                                String imgNameBholu = "Image-" + System.currentTimeMillis() + ".png";

                                File fileBholu = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(),
                                        imgNameBholu);

                                int downloadId = PRDownloader.download(DownloadImageURL, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(),
                                                imgNameBholu)
                                        .build()
                                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                                            @Override
                                            public void onStartOrResume() {

                                            }
                                        })
                                        .setOnPauseListener(new OnPauseListener() {
                                            @Override
                                            public void onPause() {

                                            }
                                        })
                                        .setOnCancelListener(new OnCancelListener() {
                                            @Override
                                            public void onCancel() {

                                            }
                                        })
                                        .setOnProgressListener(new OnProgressListener() {
                                            @Override
                                            public void onProgress(Progress progress) {

                                            }
                                        })
                                        .start(new OnDownloadListener() {
                                            @Override
                                            public void onDownloadComplete() {


                                                MediaScannerConnection.scanFile(requireActivity(), new String[]{
                                                                fileBholu.getAbsolutePath()
                                                        }, (String[]) null,
                                                        new MediaScannerConnection.MediaScannerConnectionClient() {
                                                            public void onMediaScannerConnected() {
                                                            }

                                                            public void onScanCompleted(String path, Uri uri) {
                                                            }
                                                        });

                                                Toast.makeText(requireActivity(), "Image Downloaded Successfully.", Toast.LENGTH_LONG).show();
                                            }

                                            @Override
                                            public void onError(Error error) {
                                                Toast.makeText(requireActivity(), "Image Downloaded Error.", Toast.LENGTH_LONG).show();
                                            }
                                        });

                            } else {
                                Toast.makeText(requireActivity(), "Sorry.. Something Went Wrong.", Toast.LENGTH_LONG).show();
                            }
                            return false;
                        }
                    });
        }
    }

    private void initListnerBholu() {
        bindingBholu.imgbackBholu.setOnClickListener(v -> {
            if (webViewBholu.canGoBack()) {
                webViewBholu.goBack();
            }
        });
        bindingBholu.imgforwardBholu.setOnClickListener(v -> {
            if (webViewBholu.canGoForward()) {
                webViewBholu.goForward();
            }
        });
        bindingBholu.imghomeBholu.setOnClickListener(v -> {
            webViewBholu.loadUrl(GOOGLE_URLBholu);
        });
        bindingBholu.imgmenuBholu.setOnClickListener(v -> openBottomSheetBholu());
        bindingBholu.imgtabsBholu.setOnClickListener(v -> {
            bindingBholu.swipeBholu.setDrawingCacheEnabled(true);
            bitmapBholu = Bitmap.createBitmap(bindingBholu.swipeBholu.getDrawingCache());
            bindingBholu.swipeBholu.setDrawingCacheEnabled(false);
            setBitmapBholu(bitmapBholu);
            setTitleBholu(webViewBholu.getUrl());
            Log.d(TAGBholu, getTitleBholu());
            browserFragmentListnerBholu.openTabListBholu(getBitmapBholu(), getTitleBholu());
        });
    }

    private void loadUrlBholu(String url) {
        if (url != null) {
            bindingBholu.webviewBholu.loadUrl(url);
            bindingBholu.webviewBholu.setWebViewClient(new WebViewClient() {

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String urlNewString) {
                    if (!loadingFinishedBholu) {
                        redirectBholu = true;
                    }
                    loadingFinishedBholu = false;
                    view.loadUrl(urlNewString);
                    setTextOnSearchViewBholu(urlNewString);
                    bindingBholu.progressbarBholu.setVisibility(View.VISIBLE);
                    return true;
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap facIcon) {
                    loadingFinishedBholu = false;
                    //SHOW LOADING IF IT ISN'T ALREADY VISIBLE
                    if (!isPrivateBholu) {
                        addToHistryBholu(url);
                    }
                    if (!bindingBholu.swipeBholu.isRefreshing()) {
                        progressBarBholu.setVisibility(View.VISIBLE);
                    }
                    setTextOnSearchViewBholu(url);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    setTextOnSearchViewBholu(url);
                    if (!redirectBholu) {
                        loadingFinishedBholu = true;
                        bindingBholu.swipeBholu.setRefreshing(false);
                        progressBarBholu.setVisibility(View.GONE);
                    }
                    if (loadingFinishedBholu && !redirectBholu) {
                        //HIDE LOADING IT HAS FINISHED
                    } else {
                        redirectBholu = false;
                    }
                    progressBarBholu.setVisibility(View.GONE);
                    bindingBholu.swipeBholu.setRefreshing(false);
                }
            });
        }
    }

    private void openBottomSheetBholu() {
        bottomSheetDialogBholu = new BottomSheetDialog(getActivity());
        BottomsheetMenuBholuBinding menuBindingBholu = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.bottomsheet_menu_bholu, null, false);

        bottomSheetDialogBholu.setContentView(menuBindingBholu.getRoot());
        bottomSheetDialogBholu.show();

        bottomsheetListnearBholu(menuBindingBholu);
    }

    private void bottomsheetListnearBholu(BottomsheetMenuBholuBinding menuBinding) {
        menuBinding.imgaddtabBholu.setOnClickListener(v -> {
            browserFragmentListnerBholu.addTabBholu();
            bottomSheetDialogBholu.dismiss();
        });
        menuBinding.imgcopyBholu.setOnClickListener(v -> {
            ClipboardManager clipboardManagerBholu = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipDataBholu = ClipData.newPlainText("text", bindingBholu.webviewBholu.getUrl());
            clipboardManagerBholu.setPrimaryClip(clipDataBholu);
            Toast.makeText(getActivity(), "Copied", Toast.LENGTH_SHORT).show();
            bottomSheetDialogBholu.dismiss();
        });
        menuBinding.imgrefreshBholu.setOnClickListener(v -> {
            loadUrliNFragmentBholu(bindingBholu.webviewBholu.getUrl());
            bottomSheetDialogBholu.dismiss();
        });
        menuBinding.imgprivateBholu.setOnClickListener(v -> {
            browserFragmentListnerBholu.openIncognitoBholu();
            bottomSheetDialogBholu.dismiss();
        });

        menuBinding.imgbookmarkBholu.setOnClickListener(v -> {
            toggleBookmarkBholu();
            bottomSheetDialogBholu.dismiss();
        });
        menuBinding.imgbookmarksBholu.setOnClickListener(v -> {
            browserFragmentListnerBholu.openBookmarksBholu();
            bottomSheetDialogBholu.dismiss();
        });
        menuBinding.imghistryBholu.setOnClickListener(v -> {
            browserFragmentListnerBholu.openHistoryBholu();
            bottomSheetDialogBholu.dismiss();
        });
        menuBinding.imgshareBholu.setOnClickListener(v -> {
            Intent shareBholu = new Intent(android.content.Intent.ACTION_SEND);
            shareBholu.setType("text/plain");
            shareBholu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            shareBholu.putExtra(Intent.EXTRA_SUBJECT, bindingBholu.webviewBholu.getTitle());
            shareBholu.putExtra(Intent.EXTRA_TEXT, bindingBholu.webviewBholu.getUrl());
            startActivity(Intent.createChooser(shareBholu, "Share Using.."));
            bottomSheetDialogBholu.dismiss();
        });

        menuBinding.linRateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + requireActivity().getPackageName())));
                bottomSheetDialogBholu.dismiss();
            }
        });
        menuBinding.linShareapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out my app at: https://play.google.com/store/apps/details?id=" + requireActivity().getPackageName());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                bottomSheetDialogBholu.dismiss();
            }
        });
        menuBinding.imgmoreBholu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BholuMyPreferenceManager werMyPreferenceManager = new BholuMyPreferenceManager(requireContext());
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(werMyPreferenceManager.getMoreAppsBholu())));
                }catch (Exception e){}
                bottomSheetDialogBholu.dismiss();
            }
        });
        menuBinding.txtPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BholuMyPreferenceManager werMyPreferenceManager = new BholuMyPreferenceManager(requireContext());
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(werMyPreferenceManager.getPrivacyPolicyBholu())));
                }catch (Exception e){}
                bottomSheetDialogBholu.dismiss();
            }
        });
    }

    private void loadUrliNFragmentBholu(String url) {
        loadUrlBholu(url);
    }

    private void addToHistryBholu(String url) {
        sessionManagerBholu.addToHistoryBholu(url);
    }

    private void toggleBookmarkBholu() {
        String urlBholu = bindingBholu.webviewBholu.getUrl();
        int i = sessionManagerBholu.toggleBookmarkBholu(urlBholu);
        if (i == 0) {
            Toast.makeText(getActivity(), "Bookmark Removed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Bookmarked", Toast.LENGTH_SHORT).show();
        }
    }

    private void setTextOnSearchViewBholu(String urlNewString) {
        browserFragmentListnerBholu.setUrlBholu(urlNewString);
    }

    public BholuBrowserFragment isPrivate() {
        return null;
    }

    public BholuBrowserFragment setArgs(boolean b, String s) {
        Log.d(TAGBholu, "setArgs: " + b + s);
        this.isPrivateBholu = b;
        this.urlBholu = s;
        return null;
    }

    public interface BrowserFragmentListnerBholu {
        void setUrlBholu(String url);

        void setWebObjBholu(WebView webObj);

        void addTabBholu();

        void openTabListBholu(Bitmap bitmap, String title);

        void openIncognitoBholu();

        void openBookmarksBholu();

        void openHistoryBholu();
    }

}