export type AdvertisingInfoResponse = {
    id: string | null;
    isAdTrackingLimited: boolean;
};
type ReactNativeIdfaAaidType = {
    getAdvertisingInfo(): Promise<AdvertisingInfoResponse>;
    getAdvertisingInfoAndCheckAuthorization(check: boolean): Promise<AdvertisingInfoResponse>;
};
declare const _default: ReactNativeIdfaAaidType;
export default _default;
