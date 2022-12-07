package com.fastspeed.five5gbrowser.rld_bholu.core_bholu

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.fastspeed.five5gbrowser.databinding.ActivityMaintenanceBholuBinding
import com.fastspeed.five5gbrowser.rld_bholu.interfaces_bholu.BholuNativeAdListener
import com.fastspeed.five5gbrowser.rld_bholu.main_bholu.BholuEdgeLight
import com.fastspeed.five5gbrowser.rld_bholu.utils_bholu.BholuMyPreferenceManager

class BholuMaintenanceActivity : AppCompatActivity() {

    private lateinit var bindingBholu: ActivityMaintenanceBholuBinding
    var admobObjEveryBholu: BholuEdgeLight? = null
    lateinit var prefenrencMyPreferenceManagerBholu: BholuMyPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingBholu = ActivityMaintenanceBholuBinding.inflate(layoutInflater)
        setContentView(bindingBholu.root)

        val messagewer = intent.getStringExtra("maintainceMessage")
        if (!messagewer.isNullOrEmpty()) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                bindingBholu.descriptionBholu.text =
                    Html.fromHtml(messagewer, Html.FROM_HTML_MODE_LEGACY)
            } else {
                bindingBholu.descriptionBholu.text = messagewer
            }
        }

        bindingBholu.btnContactBholu.setOnClickListener {
            finish()
        }

        prefenrencMyPreferenceManagerBholu = BholuMyPreferenceManager(this@BholuMaintenanceActivity)
        admobObjEveryBholu = BholuEdgeLight()
        observerViewModelBholu()
    }

    fun observerViewModelBholu() {
        admobObjEveryBholu?.showAndLoadGoogleNativeBholu(activity = this,
            nadId = prefenrencMyPreferenceManagerBholu.nadIdBholu(),
            nativeAdContainerG = bindingBholu.nadViewBholu.adsContainerBholu,
            which = 1,
            nativeEventSTED = object : BholuNativeAdListener {
                override fun setNativeSuccesswer() {
                }

                override fun setNativeFailedwer() {
                }
            })
    }
}