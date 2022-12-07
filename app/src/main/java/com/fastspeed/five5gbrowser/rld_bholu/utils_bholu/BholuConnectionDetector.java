package com.fastspeed.five5gbrowser.rld_bholu.utils_bholu;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class BholuConnectionDetector {

    private Context _contextBholu;

    public BholuConnectionDetector(Context context) {
        this._contextBholu = context;
    }

    public boolean isConnectingToInternet() {
        ConnectivityManager connectivityBholu = (ConnectivityManager) _contextBholu.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo[] networkInfoBholu = connectivityBholu.getAllNetworkInfo();

        for (NetworkInfo netInfoBholu : networkInfoBholu) {

            if (netInfoBholu.getTypeName().equalsIgnoreCase("WIFI"))

                for (int i = 0; i < networkInfoBholu.length; i++) {
                    if (netInfoBholu.isConnected())

                        return true;
                }
            if (netInfoBholu.getTypeName().equalsIgnoreCase("MOBILE"))

                if (netInfoBholu.isConnected())

                    return true;
        }
        return false;
    }

}

