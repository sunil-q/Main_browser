package com.fastspeed.five5gbrowser.rld_bholu

import android.content.Context
import android.net.ConnectivityManager

class BholuConnectionManager(mContext: Context) {
    private var mContextBholu: Context = mContext

    fun isConnectedBholu(): Boolean {
        val connectivityBholu = mContextBholu.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfoBholu = connectivityBholu.allNetworkInfo

        for (netInfo in networkInfoBholu) {
            if (netInfo.typeName.equals("WIFI", ignoreCase = true)) for (i in networkInfoBholu.indices) {
                if (netInfo.isConnected) return true
            }
            if (netInfo.typeName.equals("MOBILE", ignoreCase = true)) if (netInfo.isConnected) return true
        }
        return false
    }
}