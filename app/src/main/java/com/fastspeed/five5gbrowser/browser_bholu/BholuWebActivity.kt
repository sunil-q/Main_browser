package com.fastspeed.five5gbrowser.browser_bholu

import android.Manifest
import com.fastspeed.five5gbrowser.rld_bholu.main_bholu.BholuEdgeLight.Companion.setAdShowDialogBholu
import androidx.appcompat.app.AppCompatActivity
import android.webkit.WebView
import android.widget.FrameLayout
import com.fastspeed.five5gbrowser.rld_bholu.main_bholu.BholuEdgeLight
import com.fastspeed.five5gbrowser.rld_bholu.utils_bholu.BholuMyPreferenceManager
import androidx.annotation.RequiresApi
import android.os.Build
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.fastspeed.five5gbrowser.R
import com.downloader.PRDownloader
import com.fastspeed.five5gbrowser.rld_bholu.interfaces_bholu.BholuadmobCloseEvent
import androidx.lifecycle.LifecycleRegistry
import com.fastspeed.five5gbrowser.browser_bholu.BholuViewPager2Adapter.ViewPagerListenerBholu
import android.content.Intent
import android.graphics.Bitmap
import com.fastspeed.five5gbrowser.browser_bholu.BholuTabListFragment.ListFragmentListnerBholu
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.fastspeed.five5gbrowser.browser_bholu.BholuBrowserFragment.BrowserFragmentListnerBholu
import android.widget.TextView
import android.view.inputmethod.EditorInfo
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import android.graphics.drawable.ColorDrawable
import com.fastspeed.five5gbrowser.rld_bholu.interfaces_bholu.BholuNativeAdListener
import android.speech.RecognizerIntent
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.fastspeed.five5gbrowser.databinding.ActivityWebBholuBinding
import com.karumi.dexter.listener.PermissionRequest
import com.fastspeed.five5gbrowser.mynotification_bholu.BholuApp
import com.fastspeed.five5gbrowser.mynotification_bholu.GoaAllNotificationDialog
import kotlinx.android.synthetic.main.banner_ad_common_kaka.*
import kotlinx.android.synthetic.main.native_ad_common_bholu.*

class BholuWebActivity : AppCompatActivity() {
    var bindingBholu: ActivityWebBholuBinding? = null
    var baseGoogleSearchBholu = "https://www.google.com/search?q="
    private var viewPager2AdapterBholu: BholuViewPager2Adapter? = null
    private var fragmentUrlBholu: String? = null
    private var fragmentWebviewBholu: WebView? = null
    private var fragmentBholu: BholuBrowserFragment? = null
    var framelytBholu: FrameLayout? = null
    var spyAdsGlobalClassEveryTime: BholuEdgeLight? = null
    var werMyPreferenceManager: BholuMyPreferenceManager? = null
    var dialogcatgoa : Dialog? = null
    lateinit var preferencesgoa : SharedPreferences

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingBholu = DataBindingUtil.setContentView(this, R.layout.activity_web_bholu)
        framelytBholu = findViewById(R.id.framelytBholu)

        val csc = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        csc.hideSoftInputFromWindow(bindingBholu?.etSearchBholu?.windowToken, 0)

        requestStoragePermissionBholu()
        PRDownloader.initialize(applicationContext)
        werMyPreferenceManager = BholuMyPreferenceManager(this)
        spyAdsGlobalClassEveryTime = BholuEdgeLight()

        spyAdsGlobalClassEveryTime!!.showBannerAdBholu(this, object : BholuadmobCloseEvent {
            override fun setFailedwer() {
                findViewById<View>(R.id.adViewkaka).visibility = View.GONE
            }

            override fun setSuccesswer() {}
            override fun setAdmobCloseEventwer() {}
        }, werMyPreferenceManager!!.getGBannerIDkaka(), adViewkaka)

        viewPager2AdapterBholu = BholuViewPager2Adapter(supportFragmentManager, LifecycleRegistry(this))
        viewPager2AdapterBholu!!.setViewPagerListenerBholu(object : ViewPagerListenerBholu {
            override fun setUrlBholu(url: String) {
                fragmentUrlBholu = url
                bindingBholu?.etSearchBholu?.setText(url)
            }

            override fun openHistoryBholu() {
                spyAdsGlobalClassEveryTime!!.showIntrestrialAdsBholu(
                    this@BholuWebActivity,
                    object : BholuadmobCloseEvent {
                        override fun setFailedwer() {}
                        override fun setSuccesswer() {}
                        override fun setAdmobCloseEventwer() {
                            val intentBholu =
                                Intent(this@BholuWebActivity, BholuHistoryActivity::class.java)
                            startActivityForResult(
                                intentBholu,
                                BholuHistoryActivity.HISTORY_ACTIVITY_CODEBholu
                            )
                        }
                    },
                    werMyPreferenceManager!!.fIdBholu()
                )
            }

            override fun setWebObjBholu(webObj: WebView) {
                fragmentWebviewBholu = webObj
            }

            override fun addTabBholu() {
                viewPager2AdapterBholu!!.addItemBholu(BholuBrowserFragment(), false, "")
                bindingBholu?.viewpager2Bholu?.currentItem = viewPager2AdapterBholu!!.itemCount - 1
            }

            override fun openBookmarksBholu() {
                spyAdsGlobalClassEveryTime!!.showIntrestrialAdsBholu(
                    this@BholuWebActivity,
                    object : BholuadmobCloseEvent {
                        override fun setFailedwer() {}
                        override fun setSuccesswer() {}
                        override fun setAdmobCloseEventwer() {
                            val intentBholu =
                                Intent(this@BholuWebActivity, BholuBookamrksActivity::class.java)
                            startActivityForResult(
                                intentBholu,
                                BholuBookamrksActivity.BOOKMAR_ACTIVITY_REQUEST_CODEBholu
                            )
                        }
                    },
                    werMyPreferenceManager!!.fIdBholu()
                )
            }

            override fun openIntigoBholu() {
                viewPager2AdapterBholu!!.addItemBholu(BholuBrowserFragment(), true, "")
                bindingBholu?.viewpager2Bholu?.currentItem = viewPager2AdapterBholu!!.itemCount - 1
                //viewPager2Adapter.getItemAt(binding.viewpager2.getCurrentItem()),true,"");
            }

            override fun openTabListBholu(bitmap: Bitmap, title: String) {
                Log.d(TAGBholu, "openTabList: $title")
                viewPager2AdapterBholu!!.listBholu[bindingBholu?.viewpager2Bholu?.currentItem!!].setTitleBholu(
                    title
                )
                viewPager2AdapterBholu!!.listBholu[bindingBholu?.viewpager2Bholu?.currentItem!!].setBitmapBholu(
                    bitmap
                )
                val listFragment = BholuTabListFragment(
                    viewPager2AdapterBholu!!.listBholu, object : ListFragmentListnerBholu {
                        override fun onListClickBholu(
                            browserFragment: BholuBrowserFragment,
                            pos: Int
                        ) {
                            bindingBholu?.viewpager2Bholu?.currentItem = pos
                        }

                        override fun onClickRemoveBholu(
                            browserFragment: BholuBrowserFragment,
                            pos: Int
                        ) {
                            viewPager2AdapterBholu!!.removePageBholu(browserFragment, pos)
                            if (viewPager2AdapterBholu!!.listBholu.size == 0) {
                                viewPager2AdapterBholu!!.addItemBholu(
                                    BholuBrowserFragment(),
                                    false,
                                    ""
                                )
                                bindingBholu?.viewpager2Bholu?.currentItem =
                                    viewPager2AdapterBholu!!.itemCount - 1
                                return
                            }
                        }

                        override fun onClickBookmarkBholu() {
                            onBackPressed()
                            spyAdsGlobalClassEveryTime!!.showIntrestrialAdsBholu(
                                this@BholuWebActivity,
                                object : BholuadmobCloseEvent {
                                    override fun setFailedwer() {}
                                    override fun setSuccesswer() {}
                                    override fun setAdmobCloseEventwer() {
                                        val intentBholu = Intent(
                                            this@BholuWebActivity,
                                            BholuBookamrksActivity::class.java
                                        )
                                        startActivityForResult(
                                            intentBholu,
                                            BholuBookamrksActivity.BOOKMAR_ACTIVITY_REQUEST_CODEBholu
                                        )
                                    }
                                },
                                werMyPreferenceManager!!.fIdBholu()
                            )
                        }

                        override fun onClickHistoryBholu() {
                            onBackPressed()
                            spyAdsGlobalClassEveryTime!!.showIntrestrialAdsBholu(
                                this@BholuWebActivity,
                                object : BholuadmobCloseEvent {
                                    override fun setFailedwer() {}
                                    override fun setSuccesswer() {}
                                    override fun setAdmobCloseEventwer() {
                                        val intentBholu = Intent(
                                            this@BholuWebActivity,
                                            BholuHistoryActivity::class.java
                                        )
                                        startActivityForResult(
                                            intentBholu,
                                            BholuHistoryActivity.HISTORY_ACTIVITY_CODEBholu
                                        )
                                    }
                                },
                                werMyPreferenceManager!!.fIdBholu()
                            )
                        }

                        override fun onClickAddBholu() {
                            viewPager2AdapterBholu!!.addItemBholu(BholuBrowserFragment(), false, "")
                            bindingBholu?.viewpager2Bholu?.currentItem =
                                viewPager2AdapterBholu!!.itemCount - 1
                        }

                        override fun closeAllBholu() {
                            for (i in viewPager2AdapterBholu!!.listBholu.indices) {
                                if (viewPager2AdapterBholu!!.getItemAtBholu(i) != null) {
                                    viewPager2AdapterBholu!!.removePageBholu(
                                        viewPager2AdapterBholu!!.getItemAtBholu(
                                            i
                                        ), i
                                    )
                                }
                            }
                            viewPager2AdapterBholu!!.addItemBholu(BholuBrowserFragment(), false, "")
                            bindingBholu?.viewpager2Bholu?.currentItem =
                                viewPager2AdapterBholu!!.itemCount - 1
                        }
                    })
                framelytBholu?.setVisibility(View.VISIBLE)
                supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.framelytBholu, listFragment).commit()
            }
        })
        bindingBholu?.viewpager2Bholu?.registerOnPageChangeCallback(object :
            OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d(TAGBholu, "onPageSelected: $position")
                if (viewPager2AdapterBholu!!.listBholu.size == 0) {
                    viewPager2AdapterBholu!!.addItemBholu(BholuBrowserFragment(), false, "")
                    bindingBholu?.viewpager2Bholu?.currentItem =
                        viewPager2AdapterBholu!!.itemCount - 1
                    return
                }
                if (viewPager2AdapterBholu!!.getItemAtBholu(position) != null) {
                    fragmentBholu = viewPager2AdapterBholu!!.getItemAtBholu(position)
                    viewPager2AdapterBholu!!.getItemAtBholu(position)
                        .setBrowserFragmentListnerBholu(object : BrowserFragmentListnerBholu {
                            override fun setUrlBholu(url: String) {
                                setUrlintoTextViewBholu(url)
                            }

                            override fun setWebObjBholu(webObj: WebView) {
                                fragmentWebviewBholu = webObj
                            }

                            override fun addTabBholu() {
                                viewPager2AdapterBholu!!.addItemBholu(
                                    BholuBrowserFragment(),
                                    false,
                                    ""
                                )
                                bindingBholu?.viewpager2Bholu?.currentItem =
                                    viewPager2AdapterBholu!!.itemCount - 1
                            }

                            override fun openIncognitoBholu() {
                                viewPager2AdapterBholu!!.addItemBholu(
                                    BholuBrowserFragment(),
                                    true,
                                    ""
                                )
                                bindingBholu?.viewpager2Bholu?.currentItem =
                                    viewPager2AdapterBholu!!.itemCount - 1
                            }

                            override fun openHistoryBholu() {
                                val intentBholu =
                                    Intent(this@BholuWebActivity, BholuHistoryActivity::class.java)
                                startActivityForResult(
                                    intentBholu,
                                    BholuHistoryActivity.HISTORY_ACTIVITY_CODEBholu
                                )
                            }

                            override fun openBookmarksBholu() {
                                val intentBholu = Intent(
                                    this@BholuWebActivity,
                                    BholuBookamrksActivity::class.java
                                )
                                startActivityForResult(
                                    intentBholu,
                                    BholuBookamrksActivity.BOOKMAR_ACTIVITY_REQUEST_CODEBholu
                                )
                            }

                            override fun openTabListBholu(bitmap: Bitmap, title: String) {
                                viewPager2AdapterBholu!!.listBholu[position].setTitleBholu(title)
                                viewPager2AdapterBholu!!.listBholu[position].setBitmapBholu(bitmap)
                                val listFragmentBholu = BholuTabListFragment(
                                    viewPager2AdapterBholu!!.listBholu,
                                    object : ListFragmentListnerBholu {
                                        override fun onListClickBholu(
                                            browserFragment: BholuBrowserFragment,
                                            pos: Int
                                        ) {
                                            bindingBholu?.viewpager2Bholu?.currentItem = pos
                                        }

                                        override fun onClickBookmarkBholu() {
                                            val intentBholu = Intent(
                                                this@BholuWebActivity,
                                                BholuBookamrksActivity::class.java
                                            )
                                            startActivityForResult(
                                                intentBholu,
                                                BholuBookamrksActivity.BOOKMAR_ACTIVITY_REQUEST_CODEBholu
                                            )
                                        }

                                        override fun onClickHistoryBholu() {
                                            val intentBholu = Intent(
                                                this@BholuWebActivity,
                                                BholuHistoryActivity::class.java
                                            )
                                            startActivityForResult(
                                                intentBholu,
                                                BholuHistoryActivity.HISTORY_ACTIVITY_CODEBholu
                                            )
                                        }

                                        override fun onClickRemoveBholu(
                                            browserFragment: BholuBrowserFragment,
                                            pos: Int
                                        ) {
                                            viewPager2AdapterBholu!!.removePageBholu(
                                                browserFragment,
                                                pos
                                            )
                                            if (viewPager2AdapterBholu!!.listBholu.size == 0) {
                                                viewPager2AdapterBholu!!.addItemBholu(
                                                    BholuBrowserFragment(),
                                                    false,
                                                    ""
                                                )
                                                bindingBholu?.viewpager2Bholu?.currentItem =
                                                    viewPager2AdapterBholu!!.itemCount - 1
                                                return
                                            }
                                        }

                                        override fun onClickAddBholu() {
                                            viewPager2AdapterBholu!!.addItemBholu(
                                                BholuBrowserFragment(),
                                                false,
                                                ""
                                            )
                                            bindingBholu?.viewpager2Bholu?.currentItem =
                                                viewPager2AdapterBholu!!.itemCount - 1
                                        }

                                        override fun closeAllBholu() {
                                            for (i in viewPager2AdapterBholu!!.listBholu.indices) {
                                                viewPager2AdapterBholu!!.removePageBholu(
                                                    viewPager2AdapterBholu!!.getItemAtBholu(i), i
                                                )
                                            }
                                            viewPager2AdapterBholu!!.addItemBholu(
                                                BholuBrowserFragment(),
                                                false,
                                                ""
                                            )
                                            bindingBholu?.viewpager2Bholu?.currentItem =
                                                viewPager2AdapterBholu!!.itemCount - 1
                                        }
                                    })
                                framelytBholu?.setVisibility(View.VISIBLE)
                                supportFragmentManager.beginTransaction()
                                    .addToBackStack("null")
                                    .add(R.id.framelytBholu, listFragmentBholu).commit()
                            }
                        })
                }
            }
        })
        bindingBholu?.viewpager2Bholu?.adapter = viewPager2AdapterBholu
        bindingBholu?.viewpager2Bholu?.isUserInputEnabled = false
        viewPager2AdapterBholu!!.addItemBholu(BholuBrowserFragment(), false, "")
        bindingBholu?.viewpager2Bholu?.currentItem = viewPager2AdapterBholu!!.itemCount - 1
        intentDataBholu
        bindingBholu?.etSearchBholu?.setOnEditorActionListener { v: TextView?, actionId: Int, event: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val textBholu = bindingBholu?.etSearchBholu?.text.toString()
                if (textBholu.contains("https://") || textBholu.contains("http://") || textBholu.contains(
                        ".com"
                    )
                ) {
                    loadUrliNFragmentBholu(bindingBholu?.etSearchBholu?.text.toString())
                } else {
                    loadUrliNFragmentBholu(baseGoogleSearchBholu + textBholu)
                }
                csc.hideSoftInputFromWindow(bindingBholu?.etSearchBholu?.windowToken, 0)
                bindingBholu?.etSearchBholu?.clearFocus()
                return@setOnEditorActionListener true
            }
            false
        }
        initListenerBholu()

        dialogcatgoa = Dialog(this@BholuWebActivity)
        preferencesgoa = getSharedPreferences(getString(R.string.app_name)+"FastTrans", Context.MODE_PRIVATE)

        if(preferencesgoa.getBoolean(BholuApp.isInAppMessagegoa,false)){
            openNotificationDialoggoa()
        }

    }

    private fun requestStoragePermissionBholu() {
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {}
                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                }
            }).check()
    }

    private fun setUrlintoTextViewBholu(url: String) {
        bindingBholu!!.etSearchBholu?.setText(url)
    }

    private fun loadUrliNFragmentBholu(toString: String?) {
        viewPager2AdapterBholu!!.addItemBholu(BholuBrowserFragment(), IS_PRIVATEBholu, toString)
        bindingBholu!!.viewpager2Bholu?.currentItem = viewPager2AdapterBholu!!.itemCount - 1
    }

    private val intentDataBholu: Unit
        private get() {
            val intentBholu = intent
            if (intentBholu != null) {
                val str = intentBholu.getStringExtra("words")
                if (str != null && str != "") {
                    if (str == "terms") {
                        loadUrliNFragmentBholu("https://www.termsandconditionsgenerator.com/live.php?token=KtQKy1KH7rmbxZx7kk8UBFoyYIAtgld0")
                    } else {
                        bindingBholu!!.etSearchBholu.setText(baseGoogleSearchBholu + str)
                        loadUrliNFragmentBholu(baseGoogleSearchBholu + str)
                    }
                }
            }
        }

    override fun onBackPressed() {
        if (BholuBrowserFragment.webViewBholu != null && BholuBrowserFragment.webViewBholu.canGoBack()) {
            BholuBrowserFragment.webViewBholu.goBack()
        } else if (framelytBholu!!.visibility == View.VISIBLE) {
            framelytBholu!!.visibility = View.GONE
        } else {
            val dialogBholu = Dialog(this)
            dialogBholu.setContentView(R.layout.dialog_fullexit_bholu)
            dialogBholu.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialogBholu.window!!.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            lp.gravity = Gravity.CENTER
            dialogBholu.window!!.attributes = lp
            spyAdsGlobalClassEveryTime!!.showAndLoadGoogleNativeBholu(this,
                werMyPreferenceManager!!.nadIdBholu(),
                dialogBholu.findViewById(R.id.adsContainerBholu),
                true,
                1,
                object : BholuNativeAdListener {
                    override fun setNativeFailedwer() {
                        dialogBholu.findViewById<View>(R.id.loadContainerBholu).visibility =
                            View.GONE
                    }

                    override fun setNativeSuccesswer() {}
                })
            val txt_yesBholu = dialogBholu.findViewById<TextView>(R.id.txt_yesBholu)
            txt_yesBholu.setOnClickListener { v: View? ->
                dialogBholu.dismiss()
                finishAffinity()
            }
            val txt_noBholu = dialogBholu.findViewById<TextView>(R.id.txt_noBholu)
            txt_noBholu.setOnClickListener { v: View? -> dialogBholu.dismiss() }
            dialogBholu.show()
        }
    }

    private fun initListenerBholu() {
        bindingBholu!!.googleMicBholu.setOnClickListener { v: View? ->
            val intentBholu = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intentBholu.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            startActivityForResult(intentBholu, SPEECH_REQUEST_CODEBholu)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAGBholu, "onActivityResult: code$requestCode")
        Log.d(TAGBholu, "onActivityResult: code$resultCode")
        if (requestCode == SPEECH_REQUEST_CODEBholu && resultCode == RESULT_OK) {
            var results: List<String?>? = null
            if (data != null) {
                results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS
                )
            }
            var spokenText: String? = null
            if (results != null) {
                spokenText = results[0]
            }
            // Do something with spokenText
            bindingBholu!!.etSearchBholu.setText(baseGoogleSearchBholu + spokenText)
            loadUrliNFragmentBholu(baseGoogleSearchBholu + spokenText)
        }
        if (requestCode == BholuBookamrksActivity.BOOKMAR_ACTIVITY_REQUEST_CODEBholu) {
            if (resultCode == RESULT_OK) {
                Log.d(TAGBholu, "onActivityResult: requestcode $requestCode")
                Log.d(TAGBholu, "onActivityResult: result  $resultCode")
                if (data != null) {
                    Log.d(TAGBholu, "onActivityResult: datawork " + data.getStringExtra("url"))
                    loadUrliNFragmentBholu(data.getStringExtra("url"))
                }
            }
        }
        if (requestCode == BholuHistoryActivity.HISTORY_ACTIVITY_CODEBholu) {
            if (resultCode == RESULT_OK) {
                Log.d(TAGBholu, "onActivityResult: requestcode $requestCode")
                Log.d(TAGBholu, "onActivityResult: result  $resultCode")
                if (data != null) {
                    Log.d(TAGBholu, "onActivityResult: datawork " + data.getStringExtra("url"))
                    loadUrliNFragmentBholu(data.getStringExtra("url"))
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setAdShowDialogBholu(this@BholuWebActivity)
    }

    companion object {
        private const val TAGBholu = "web:activity"
        private const val SPEECH_REQUEST_CODEBholu = 1
        private const val IS_PRIVATEBholu = false
    }


    private fun openNotificationDialoggoa() {

        val imageUrlgoa=preferencesgoa.getString(BholuApp.imageUrlgoa,null)
        val text1goa=preferencesgoa.getString(BholuApp.text1goa,null)
        val text2goa=preferencesgoa.getString(BholuApp.text2goa,null)
        val buttonTextgoa=preferencesgoa.getString(BholuApp.buttonTextgoa,null)
        val buttonActionUrlgoa=preferencesgoa.getString(BholuApp.buttonActionUrlgoa,null)

        val notificationDialoggoa = GoaAllNotificationDialog(this@BholuWebActivity, imageUrlgoa, text1goa,text2goa,buttonTextgoa,buttonActionUrlgoa)
        notificationDialoggoa.requestWindowFeature(Window.FEATURE_NO_TITLE)
        notificationDialoggoa.setCanceledOnTouchOutside(false)
        notificationDialoggoa.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        notificationDialoggoa.setListenergoa(object : GoaAllNotificationDialog.OnButtonClick {
            override fun onPositiveButtonClickgoa(linkrrb: String?) {
                if(linkrrb.isNullOrEmpty()){
                    notificationDialoggoa.dismiss()
                }
                else{
                    try {
                        val urlgoa = linkrrb
                        val igoa = Intent(Intent.ACTION_VIEW)
                        igoa.data = Uri.parse(urlgoa)
                        startActivity(igoa)
                    } catch (egoa: Exception) {
                        egoa.printStackTrace()
                        Toast.makeText(this@BholuWebActivity,"", Toast.LENGTH_SHORT).show()
                    } finally {
                        notificationDialoggoa.dismiss()
                    }

                }

            }

            override fun onNegativeButtonClickgoa() {
                notificationDialoggoa.dismiss()
            }
        })

        if (!isFinishing) {
            preferencesgoa.edit().clear().apply()
            notificationDialoggoa.show()
        }
    }
}