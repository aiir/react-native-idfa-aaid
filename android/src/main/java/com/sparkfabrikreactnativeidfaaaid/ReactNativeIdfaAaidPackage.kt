package com.sparkfabrik.rnidfaaaid

import com.facebook.react.TurboReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider

class ReactNativeIdfaAaidPackage : TurboReactPackage() {
    override fun getModule(name: String, reactContext: ReactApplicationContext): NativeModule? {
        return if (name == ReactNativeIdfaAaidModule.NAME) {
            ReactNativeIdfaAaidModule(reactContext)
        } else {
            null
        }
    }

    override fun getReactModuleInfoProvider(): ReactModuleInfoProvider {
        return ReactModuleInfoProvider {
            val isTurboModule = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED
            mapOf(
                ReactNativeIdfaAaidModule.NAME to ReactModuleInfo(
                    ReactNativeIdfaAaidModule.NAME,
                    ReactNativeIdfaAaidModule.NAME,
                    false,  // canOverrideExistingModule
                    false,  // needsEagerInit
                    false,  // isCxxModule
                    isTurboModule
                )
            )
        }
    }
}
