package com.fastspeed.five5gbrowser.rld_bholu.core_bholu

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.fastspeed.five5gbrowser.databinding.ActivityAppUpdateBholuBinding
import com.fastspeed.five5gbrowser.rld_bholu.interfaces_bholu.BholuNativeAdListener
import com.fastspeed.five5gbrowser.rld_bholu.main_bholu.BholuEdgeLight
import com.fastspeed.five5gbrowser.rld_bholu.utils_bholu.BholuMyPreferenceManager

class BholuWerAppUpdateActivity : AppCompatActivity() {

    private lateinit var bindingBholu: ActivityAppUpdateBholuBinding
    var admobObjEveryBholu: BholuEdgeLight? = null
    lateinit var prefenrencMyPreferenceManagerBholu: BholuMyPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingBholu = ActivityAppUpdateBholuBinding.inflate(layoutInflater)
        setContentView(bindingBholu.root)

        val message = intent.getStringExtra("versionMessage")

        if (!message.isNullOrEmpty()) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                bindingBholu.updateDescriptionBholu.text =
                    Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY)
            } else
                bindingBholu.updateDescriptionBholu.text = message
        }

        bindingBholu.btnUpdateBholu.setOnClickListener {
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                    )
                )
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=$packageName")
                    )
                )
            }
        }

        prefenrencMyPreferenceManagerBholu = BholuMyPreferenceManager(this@BholuWerAppUpdateActivity)
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