package com.fastspeed.five5gbrowser.rld_bholu

import com.google.gson.annotations.SerializedName

class BholuSettingsModel {
    @SerializedName("v")
    val versionwer: Int = 0

    @SerializedName("vm")
    var versionMessagewer: String? = null

    @SerializedName("iMnc")
    var isMaintaincewer: Boolean = false

    @SerializedName("mM")
    var maintainceMessagewer: String? = null

    @SerializedName("iMo")
    var isMovedwer: Boolean = false

    @SerializedName("mU")
    var movedURLwer: String? = null

    @SerializedName("mD")
    var movedDescriptionwer: String? = null

    @SerializedName("gB")
    var googleBannerwer: String? = null

    @SerializedName("gI")
    var googleInterstitialwer: String? = null

    @SerializedName("gN")
    var googleNativewer: String? = null

    @SerializedName("gR")
    var googleRewardedwer: String? = null

    @SerializedName("gO")
    var googleOpenAdIdwer: String? = null

    @SerializedName("isS")
    val isStartScreenwer: Boolean = true

    @SerializedName("pP")
    var privacyPolicy: String?="http://www.google.com"

    @SerializedName("tC")
    var termsandcondition: String?="http://www.google.com"

    @SerializedName("mA")
    var moreApps: String?="https://play.google.com/store/apps/developer?id="

    @SerializedName("sC")
    val showCount: Int = 0


    @SerializedName("dMSW")
    var dynamicShows:Boolean = false

    @SerializedName("dMDY")
    val dynamicDays: Int = 0

    @SerializedName("haOO")
    var homeAdShow:Boolean = false

}