#import <React/RCTBridgeModule.h>

#ifdef RCT_NEW_ARCH_ENABLED
#import <React/RCTTurboModule.h>
#import <ReactNativeIdfaAaidSpec/ReactNativeIdfaAaidSpec.h>
#endif

// Import the Swift-generated header. CocoaPods replaces dashes with underscores
// in the target name, producing sparkfabrik_react_native_idfa_aaid-Swift.h.
#if __has_include("sparkfabrik_react_native_idfa_aaid-Swift.h")
#import "sparkfabrik_react_native_idfa_aaid-Swift.h"
#elif __has_include(<sparkfabrik_react_native_idfa_aaid/sparkfabrik_react_native_idfa_aaid-Swift.h>)
#import <sparkfabrik_react_native_idfa_aaid/sparkfabrik_react_native_idfa_aaid-Swift.h>
#endif

@interface ReactNativeIdfaAaidModule : NSObject <
#ifdef RCT_NEW_ARCH_ENABLED
  NativeReactNativeIdfaAaidSpec
#else
  RCTBridgeModule
#endif
>
@end

@implementation ReactNativeIdfaAaidModule {
    ReactNativeIdfaAaid *_impl;
}

RCT_EXPORT_MODULE(ReactNativeIdfaAaid)

+ (BOOL)requiresMainQueueSetup
{
    return NO;
}

- (instancetype)init
{
    if (self = [super init]) {
        _impl = [ReactNativeIdfaAaid new];
    }
    return self;
}

// Method selector uses `resolve:reject:` to match the codegen-generated spec protocol.
// Delegates to the Swift class using its own @objc selector labels.

RCT_EXPORT_METHOD(getAdvertisingInfo:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject)
{
    [_impl getAdvertisingInfo:resolve withRejecter:reject];
}

RCT_EXPORT_METHOD(getAdvertisingInfoAndCheckAuthorization:(BOOL)checkAuthorization
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject)
{
    [_impl getAdvertisingInfoAndCheckAuthorization:checkAuthorization
                                      withResolver:resolve
                                      withRejecter:reject];
}

#ifdef RCT_NEW_ARCH_ENABLED
- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativeReactNativeIdfaAaidSpecJSI>(params);
}
#endif

@end
