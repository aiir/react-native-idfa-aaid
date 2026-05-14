import type { TurboModule } from 'react-native/Libraries/TurboModule/RCTExport';
import { TurboModuleRegistry } from 'react-native';

export type AdvertisingInfoResponse = {
  id: string | null;
  isAdTrackingLimited: boolean;
};

export interface Spec extends TurboModule {
  getAdvertisingInfo(): Promise<AdvertisingInfoResponse>;
  getAdvertisingInfoAndCheckAuthorization(
    check: boolean
  ): Promise<AdvertisingInfoResponse>;
}

export default TurboModuleRegistry.get<Spec>('ReactNativeIdfaAaid');
