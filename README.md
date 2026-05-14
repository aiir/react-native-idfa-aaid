# react-native-idfa-aaid

React Native module to get the Advertising Identifier (IDFA on iOS, AAID on Android).

> **This is the [Aiir](https://www.aiir.com) fork** of the archived
> [`@sparkfabrik/react-native-idfa-aaid`](https://github.com/sparkfabrik/sparkfabrik-react-native-idfa-aaid)
> package. The upstream repo was archived by SparkFabrik in November 2025. We forked it to add
> React Native New Architecture (TurboModule) support. See [CLAUDE.md](./CLAUDE.md) for
> technical details of the changes made.

## What it does

The [Advertising Identifier](https://developer.apple.com/documentation/adsupport/asidentifiermanager)
(IDFA on iOS, [AAID](https://developer.android.com/training/articles/ad-id) on Android) is a
device-specific, resettable ID used for advertising attribution. This module exposes it to React
Native, respecting OS-level user permission.

Both methods return:

```ts
interface AdvertisingInfoResponse {
  id: string | null;       // null if tracking is not permitted
  isAdTrackingLimited: boolean;
}
```

## Installation

This package is consumed as a GitHub dependency. Add it to your `package.json`:

```json
"@sparkfabrik/react-native-idfa-aaid": "github:aiir/react-native-idfa-aaid#v1.2.0-aiir.1"
```

Then run `npm install` and `pod install` in your `ios` folder.

## iOS setup

Add the tracking usage description to `Info.plist`:

```xml
<key>NSUserTrackingUsageDescription</key>
<string>Your description here</string>
```

## Usage

```js
import ReactNativeIdfaAaid, { AdvertisingInfoResponse } from '@sparkfabrik/react-native-idfa-aaid';

useEffect(() => {
  ReactNativeIdfaAaid.getAdvertisingInfo()
    .then((res: AdvertisingInfoResponse) => {
      const id = res.isAdTrackingLimited ? null : res.id;
    })
    .catch((err) => console.log(err));
}, []);
```

### iOS 17.4 tracking status bug

iOS 17.4 has a bug where `ATTrackingManager` can return `denied` before the permission
dialog has been shown. Use `getAdvertisingInfoAndCheckAuthorization(true)` to apply a
workaround that re-checks when the app becomes active:

```js
ReactNativeIdfaAaid.getAdvertisingInfoAndCheckAuthorization(true)
  .then((res: AdvertisingInfoResponse) => {
    const id = res.isAdTrackingLimited ? null : res.id;
  })
  .catch((err) => console.log(err));
```

Pass `false` (or use `getAdvertisingInfo()`) if you do not need the workaround.

## License

MIT — see [LICENSE](./LICENSE).
