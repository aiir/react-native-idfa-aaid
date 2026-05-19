import type { TurboModule } from 'react-native/Libraries/TurboModule/RCTExport';
export type AdvertisingInfoResponse = {
    id: string | null;
    isAdTrackingLimited: boolean;
};
export interface Spec extends TurboModule {
    getAdvertisingInfo(): Promise<AdvertisingInfoResponse>;
    getAdvertisingInfoAndCheckAuthorization(check: boolean): Promise<AdvertisingInfoResponse>;
}
declare const _default: Spec | null;
export default _default;
