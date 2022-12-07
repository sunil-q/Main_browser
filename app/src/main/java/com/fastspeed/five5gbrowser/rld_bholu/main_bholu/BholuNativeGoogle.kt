package com.fastspeed.five5gbrowser.rld_bholu.main_bholu

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.fastspeed.five5gbrowser.R
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView

class BholuNativeGoogle {

    fun populateUnifiedNativeAdViewDGoa(nativeAd: NativeAd?, nativeAdView: NativeAdView) {
        if (nativeAd!=null) {
            // Set the media view.
            nativeAdView.mediaView = nativeAdView.findViewById(R.id.ad_media)

            // Set other ad assets.
            nativeAdView.headlineView = nativeAdView.findViewById(R.id.ad_headline)
            nativeAdView.bodyView = nativeAdView.findViewById(R.id.ad_body)
            nativeAdView.iconView = nativeAdView.findViewById(R.id.ad_app_icon)
            nativeAdView.starRatingView = nativeAdView.findViewById(R.id.ad_stars)
            nativeAdView.advertiserView = nativeAdView.findViewById(R.id.ad_advertiser)

            // The headline and media content are guaranteed to be in every NativeAd.
            (nativeAdView.headlineView as TextView).text = nativeAd!!.headline
            nativeAdView.mediaView?.setMediaContent(nativeAd!!.mediaContent)

            // These assets aren't guaranteed to be in every NativeAd, so it's important to
            // check before trying to display them.
            if (nativeAd!!.body == null) {
                nativeAdView.bodyView?.visibility = View.INVISIBLE
            } else {
                nativeAdView.bodyView?.visibility = View.VISIBLE
                (nativeAdView.bodyView as TextView).text = nativeAd!!.body
            }

            if (nativeAd!!.icon == null) {
                nativeAdView.iconView?.visibility = View.GONE
            } else {
                (nativeAdView.iconView as ImageView).setImageDrawable(
                    nativeAd!!.icon?.drawable)
                nativeAdView.iconView?.visibility = View.VISIBLE
            }

            if (nativeAd!!.starRating == null) {
                nativeAdView.starRatingView?.visibility = View.INVISIBLE
            } else {
                (nativeAdView.starRatingView as RatingBar).rating = nativeAd!!.starRating!!.toFloat()
                nativeAdView.starRatingView?.visibility = View.VISIBLE
            }

            if (nativeAd!!.advertiser == null) {
                nativeAdView.advertiserView?.visibility = View.INVISIBLE
            } else {
                (nativeAdView.advertiserView as TextView).text = nativeAd!!.advertiser
                nativeAdView.advertiserView?.visibility = View.VISIBLE
            }

            // This method tells the Google Mobile Ads SDK that you have finished populating your
            // native ad view with this native ad.
            nativeAdView.setNativeAd(nativeAd)

        }
    }

    fun populateUnifiedNativeAdViewStartGoa(nativeAd: NativeAd?, adView: NativeAdView) {

        if (nativeAd!=null)  {// Set the media view.
            adView.mediaView = adView.findViewById<com.google.android.gms.ads.nativead.MediaView>(R.id.ad_media)

            // Set other ad assets.
            adView.headlineView = adView.findViewById(R.id.ad_headline)
            adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)
            adView.iconView = adView.findViewById(R.id.ad_app_icon)
            adView.priceView = adView.findViewById(R.id.ad_price)
            adView.starRatingView = adView.findViewById(R.id.ad_stars)
            adView.storeView = adView.findViewById(R.id.ad_store)
            adView.advertiserView = adView.findViewById(R.id.ad_advertiser)

            // The headline and media content are guaranteed to be in every NativeAd.
            (adView.headlineView as TextView).text = nativeAd!!.headline
            adView.mediaView?.setMediaContent(nativeAd!!.mediaContent)

            if (nativeAd!!.callToAction == null) {
                adView.callToActionView?.visibility = View.GONE
            } else {
                adView.callToActionView?.visibility = View.VISIBLE
                (adView.callToActionView as Button).text = nativeAd!!.callToAction
            }

            if (nativeAd!!.icon == null) {
                adView.iconView?.visibility = View.GONE
            } else {
                (adView.iconView as ImageView).setImageDrawable(
                    nativeAd!!.icon?.drawable)
                adView.iconView?.visibility = View.VISIBLE
            }

            if (nativeAd!!.price == null) {
                adView.priceView?.visibility = View.GONE
            } else {
                adView.priceView?.visibility = View.VISIBLE
                (adView.priceView as TextView).text = nativeAd!!.price
            }

            if (nativeAd!!.store == null) {
                adView.storeView?.visibility = View.GONE
            } else {
                adView.storeView?.visibility = View.VISIBLE
                (adView.storeView as TextView).text = nativeAd!!.store
            }

            if (nativeAd!!.starRating == null) {
                adView.starRatingView?.visibility = View.INVISIBLE
            } else {
                (adView.starRatingView as RatingBar).rating = nativeAd!!.starRating!!.toFloat()
                adView.starRatingView?.visibility = View.VISIBLE
            }

            if (nativeAd!!.advertiser == null) {
                adView.advertiserView?.visibility = View.GONE
            } else {
                (adView.advertiserView as TextView).text = nativeAd!!.advertiser
                adView.advertiserView?.visibility = View.VISIBLE
            }

            // This method tells the Google Mobile Ads SDK that you have finished populating your
            // native ad view with this native ad.
            adView.setNativeAd(nativeAd)
        }

    }

    fun populateUnifiedNativeAdViewUnifiGoa(nativeAd: NativeAd?, adView: NativeAdView) {

        if (nativeAd!=null) {
            adView.mediaView = adView.findViewById<com.google.android.gms.ads.nativead.MediaView>(R.id.ad_media)

            // Set other ad assets.
            adView.headlineView = adView.findViewById(R.id.ad_headline)
            adView.bodyView = adView.findViewById(R.id.ad_body)
            adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)
            adView.iconView = adView.findViewById(R.id.ad_app_icon)
            adView.priceView = adView.findViewById(R.id.ad_price)
            adView.starRatingView = adView.findViewById(R.id.ad_stars)
            adView.storeView = adView.findViewById(R.id.ad_store)
            adView.advertiserView = adView.findViewById(R.id.ad_advertiser)

            // The headline and media content are guaranteed to be in every NativeAd.
            (adView.headlineView as TextView).text = nativeAd.headline
            adView.mediaView?.setMediaContent(nativeAd.mediaContent)

            // These assets aren't guaranteed to be in every NativeAd, so it's important to
            // check before trying to display them.
            if (nativeAd.body == null) {
                adView.bodyView?.visibility = View.GONE
            } else {
                adView.bodyView?.visibility = View.GONE
                (adView.bodyView as TextView).text = nativeAd.body
            }

            if (nativeAd.callToAction == null) {
                adView.callToActionView?.visibility = View.INVISIBLE
            } else {
                adView.callToActionView?.visibility = View.VISIBLE
                (adView.callToActionView as Button).text = nativeAd.callToAction
            }

            if (nativeAd.icon == null) {
                adView.iconView?.visibility = View.GONE
            } else {
                (adView.iconView as ImageView).setImageDrawable(
                    nativeAd.icon?.drawable)
                adView.iconView?.visibility = View.VISIBLE
            }

            if (nativeAd.price == null) {
                adView.priceView?.visibility = View.INVISIBLE
            } else {
                adView.priceView?.visibility = View.VISIBLE
                (adView.priceView as TextView).text = nativeAd.price
            }

            if (nativeAd.store == null) {
                adView.storeView?.visibility = View.INVISIBLE
            } else {
                adView.storeView?.visibility = View.VISIBLE
                (adView.storeView as TextView).text = nativeAd.store
            }

            if (nativeAd.starRating == null) {
                adView.starRatingView?.visibility = View.INVISIBLE
            } else {
                (adView.starRatingView as RatingBar).rating = nativeAd.starRating!!.toFloat()
                adView.starRatingView?.visibility = View.VISIBLE
            }

            if (nativeAd.advertiser == null) {
                adView.advertiserView?.visibility = View.INVISIBLE
            } else {
                (adView.advertiserView as TextView).text = nativeAd.advertiser
                adView.advertiserView?.visibility = View.VISIBLE
            }

            // This method tells the Google Mobile Ads SDK that you have finished populating your
            // native ad view with this native ad.
            adView.setNativeAd(nativeAd)
        }

    }
}