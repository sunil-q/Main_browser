package com.fastspeed.five5gbrowser.rld_bholu

import retrofit2.http.GET
import retrofit2.http.Path

interface BholuApiUtils {
    @GET("{fullUrl}")
    fun getPowerswer(@Path("fullUrl") fullUrl:String): retrofit2.Call<BholuSettingsModel>
}