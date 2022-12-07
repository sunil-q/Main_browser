package com.fastspeed.five5gbrowser.rld_bholu.dialogs_bholu

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Html
import com.fastspeed.five5gbrowser.R

class BholuAdShowingDialog(var mContextwer: Context, private var messagewer: String?) : Dialog(mContextwer) {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_loading_dialog_bholu)

        when {
            messagewer != null -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.tvMessageBholu).text = Html.fromHtml(messagewer, Html.FROM_HTML_MODE_LEGACY)
            } else {
                findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.tvMessageBholu).text = Html.fromHtml(messagewer)
            }
        }
    }
}
