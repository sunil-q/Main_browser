package com.fastspeed.five5gbrowser.mynotification_bholu

import android.app.Application
import android.content.Context
import android.content.Intent
import com.onesignal.OSNotificationOpenedResult
import com.onesignal.OneSignal
import com.fastspeed.five5gbrowser.R
import com.fastspeed.five5gbrowser.mynotification_bholu.BholuApp.Companion.buttonActionUrlgoa
import com.fastspeed.five5gbrowser.mynotification_bholu.BholuApp.Companion.buttonTextgoa
import com.fastspeed.five5gbrowser.mynotification_bholu.BholuApp.Companion.imageUrlgoa
import com.fastspeed.five5gbrowser.mynotification_bholu.BholuApp.Companion.isInAppMessagegoa
import com.fastspeed.five5gbrowser.mynotification_bholu.BholuApp.Companion.text1goa
import com.fastspeed.five5gbrowser.mynotification_bholu.BholuApp.Companion.text2goa
import com.fastspeed.five5gbrowser.rld_bholu.core_bholu.BholuSplashActivity


class GoaOneSignalNotificationOpenedHandler(private val contextgoa: Application): OneSignal.OSNotificationOpenedHandler {

    override fun notificationOpened(resultrrb: OSNotificationOpenedResult) {
        val datagoa = resultrrb.notification.additionalData

        if (datagoa != null && datagoa.length()>0) {

           var imageUrlJgoa:String? = null
           var text1Jgoa:String? = null
           var text2Jgoa:String? = null
           var buttonTextJgoa:String? = null
           var buttonActionUrlJgoa:String? = null

            val preferencesgoa=contextgoa.getSharedPreferences(contextgoa.getString(R.string.app_name)+ "FastTrans", Context.MODE_PRIVATE)
            val editorgoa = preferencesgoa.edit()
            editorgoa.putBoolean(isInAppMessagegoa,true).apply()

            if( datagoa.has(imageUrlgoa)){
                imageUrlJgoa=datagoa.getString(imageUrlgoa)
                editorgoa.putString(imageUrlgoa, imageUrlJgoa).apply()
            }
            if (datagoa.has(text1goa)){
                text1Jgoa=datagoa.getString(text1goa)
                editorgoa.putString(text1goa, text1Jgoa).apply()
            }
            if (datagoa.has(text2goa)){
                text2Jgoa=datagoa.getString(text2goa)
                editorgoa.putString(text2goa, text2Jgoa).apply()
            }
            if (datagoa.has(buttonTextgoa)){
                buttonTextJgoa=datagoa.getString(buttonTextgoa)
                editorgoa.putString(buttonTextgoa, buttonTextJgoa).apply()
            }
            if (datagoa.has(buttonActionUrlgoa)){
                buttonActionUrlJgoa=datagoa.getString(buttonActionUrlgoa)
                editorgoa.putString(buttonActionUrlgoa, buttonActionUrlJgoa).apply()
            }

            val intentgoa = Intent(contextgoa, BholuSplashActivity::class.java)
            intentgoa.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK
            contextgoa.startActivity(intentgoa)
        }
        else{

            val intentgoa = Intent(contextgoa, BholuSplashActivity::class.java)
            intentgoa.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK
            contextgoa.startActivity(intentgoa)
        }
    }

}