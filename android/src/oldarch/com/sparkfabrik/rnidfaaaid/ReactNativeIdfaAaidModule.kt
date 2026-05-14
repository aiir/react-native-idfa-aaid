package com.sparkfabrik.rnidfaaaid

import android.util.Log
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.WritableNativeMap
import com.facebook.react.module.annotations.ReactModule
import com.google.android.gms.ads.identifier.AdvertisingIdClient

@ReactModule(name = ReactNativeIdfaAaidModule.NAME)
class ReactNativeIdfaAaidModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    companion object {
        const val NAME = "ReactNativeIdfaAaid"
    }

    override fun getName() = NAME

    @ReactMethod
    fun getAdvertisingInfo(promise: Promise) {
        val ret = WritableNativeMap()
        try {
            val adInfo = AdvertisingIdClient.getAdvertisingIdInfo(reactApplicationContext)
            ret.putString("id", adInfo?.id)
            ret.putBoolean("isAdTrackingLimited", false)
        } catch (e: Exception) {
            Log.e(NAME, "Failed to connect to Advertising ID provider.")
            promise.reject("Error getting aaid.", e)
            return
        }
        promise.resolve(ret)
    }

    @ReactMethod
    fun getAdvertisingInfoAndCheckAuthorization(check: Boolean, promise: Promise) {
        // Check not needed on Android
        getAdvertisingInfo(promise)
    }
}
