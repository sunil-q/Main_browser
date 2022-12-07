package com.fastspeed.five5gbrowser.rld_bholu.core_bholu

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fastspeed.five5gbrowser.databinding.ActivityWeMovedBholuBinding
import com.fastspeed.five5gbrowser.rld_bholu.interfaces_bholu.BholuNativeAdListener
import com.fastspeed.five5gbrowser.rld_bholu.main_bholu.BholuEdgeLight
import com.fastspeed.five5gbrowser.rld_bholu.utils_bholu.BholuMyPreferenceManager

class BholuWeMovedActivity : AppCompatActivity() {

    private lateinit var bindingBholu: ActivityWeMovedBholuBinding
    var admobObjEveryBholu: BholuEdgeLight? = null
    lateinit var prefenrencMyPreferenceManagerBholu: BholuMyPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingBholu = ActivityWeMovedBholuBinding.inflate(layoutInflater)
        setContentView(bindingBholu.root)

        val urlwer = intent.getStringExtra("movedURL")
        val messagewer = intent.getStringExtra("movedDescription")
        if (!messagewer.isNullOrEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                bindingBholu.descriptionBholu.text = Html.fromHtml(messagewer, Html.FROM_HTML_MODE_LEGACY)
            } else
                bindingBholu.descriptionBholu.text = messagewer
        }

        bindingBholu.btnCheckBholu.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlwer)))
            } catch (e: Exception) {
                Toast.makeText(this@BholuWeMovedActivity, "Url not found...", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }

        prefenrencMyPreferenceManagerBholu = BholuMyPreferenceManager(this@BholuWeMovedActivity)
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