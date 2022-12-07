package com.fastspeed.five5gbrowser.mynotification_bholu

import android.app.Activity
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fastspeed.five5gbrowser.R


class GoaAllNotificationDialog(
        var activitygoa: Activity,
        private var imageUrlgoa: String?,
        private var text1Sgoa: String?,
        private var text2Sgoa: String?,
        private var buttonTextgoa: String?,
        private var buttonActionUrlgoa: String?) : Dialog(activitygoa) {

    private var onButtonClickgoa : OnButtonClick? = null

    interface OnButtonClick {
        fun onPositiveButtonClickgoa(linkgoa:String?)
        fun onNegativeButtonClickgoa()
    }

    fun setListenergoa(onButtonClickgoa: OnButtonClick?) {
        this.onButtonClickgoa = onButtonClickgoa
    }


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_notification_goa)

        val textview1goa:AppCompatTextView=findViewById(R.id.text1Tgoa)
        val textview2goa:AppCompatTextView=findViewById(R.id.text2Tgoa)
        val positiveButtongoa: Button =findViewById(R.id.positive_buttongoa)
        val closeImagegoa:AppCompatImageView=findViewById(R.id.closeImagegoa)
        val mainImagegoa:AppCompatImageView=findViewById(R.id.notificationImagegoa)
        when {
            text1Sgoa != null ->
            {
                textview1goa.visibility=View.VISIBLE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    textview1goa.text = Html.fromHtml(text1Sgoa, Html.FROM_HTML_MODE_LEGACY)
                } else {
                    textview1goa.text = Html.fromHtml(text1Sgoa)
                }
            }
            else->{
                textview1goa.visibility=View.GONE
            }
        }

        when {
            text2Sgoa != null ->
            {
                textview2goa.visibility=View.VISIBLE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    textview2goa.text = Html.fromHtml(text2Sgoa, Html.FROM_HTML_MODE_LEGACY)
                } else {
                    textview2goa.text = Html.fromHtml(text2Sgoa)
                }
            }
            else->{
                textview2goa.visibility=View.GONE
            }
        }

        when {
            imageUrlgoa != null ->
            {
                mainImagegoa.visibility=View.VISIBLE
                Glide.with(activitygoa)
                    .load(imageUrlgoa)
                    .placeholder(R.color.colorGray)
                    .error(R.color.black)
                    .priority(Priority.IMMEDIATE)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mainImagegoa)
            }
            else->{
                mainImagegoa.visibility=View.GONE
            }
        }

        closeImagegoa.setOnClickListener { dismiss() }


        positiveButtongoa.text=buttonTextgoa
        positiveButtongoa.setOnClickListener { onButtonClickgoa!!.onPositiveButtonClickgoa(buttonActionUrlgoa) }

    }

}
