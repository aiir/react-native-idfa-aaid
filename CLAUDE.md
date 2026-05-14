# CLAUDE.md

This file provides guidance to Claude Code when working in this repository.

## What this repo is

This is the **Aiir fork** of [`@sparkfabrik/react-native-idfa-aaid`](https://github.com/sparkfabrik/sparkfabrik-react-native-idfa-aaid).
The upstream repo was archived by SparkFabrik in November 2025. We forked it to add
React Native New Architecture (TurboModule) support, which is required to enable
`RCT_NEW_ARCH_ENABLED=1` in the [aiirmobile](https://github.com/aiir/aiirmobile) app.

The package is consumed by aiirmobile as a GitHub dependency:

```json
"@sparkfabrik/react-native-idfa-aaid": "github:aiir/react-native-idfa-aaid#v1.2.0-aiir.1"
```

## What was changed from upstream

**iOS** — The original module used `RCT_EXTERN_MODULE` + `RCT_EXTERN_METHOD` in a plain `.m`
file to bridge a Swift implementation class (`ReactNativeIdfaAaid.swift`) into React Native.
This approach does not support TurboModules because the `getTurboModule:` method (required by
New Architecture) returns a C++ type that cannot be implemented in plain ObjC or Swift.

The fix (following the `@react-native-async-storage/async-storage` pattern) is a thin ObjC++
wrapper (`ios/ReactNativeIdfaAaid.mm`) that:
- Registers as the RN module via `RCT_EXPORT_MODULE(ReactNativeIdfaAaid)`
- Holds a `ReactNativeIdfaAaid` Swift instance and delegates all method calls to it
- Implements `getTurboModule:` behind `#ifdef RCT_NEW_ARCH_ENABLED`

The Swift file (`ios/ReactNativeIdfaAaid.swift`) is **unchanged**.

**Android** — Split the module into two source sets selected at build time:
- `android/src/newarch/` — module extends codegen-generated `NativeReactNativeIdfaAaidSpec`
- `android/src/oldarch/` — module extends `ReactContextBaseJavaModule` (original approach)

Package updated from `ReactPackage` to `TurboReactPackage`. Build.gradle modernised.

**JS** — Added `src/NativeReactNativeIdfaAaid.ts` (the codegen spec) and `codegenConfig` in
`package.json`. The `src/index.tsx` entry point is unchanged so `lib/` does not need rebuilding.

## Releasing a new version

1. Make changes on `main`
2. Bump the version in `package.json` following the `1.2.0-aiir.N` convention
3. Commit, tag, and push:

```bash
git commit -m "..."
git tag v1.2.0-aiir.N
git push origin main
git push origin v1.2.0-aiir.N
```

4. In aiirmobile, update `package.json` to reference the new tag and run `npm install`

## Key files

| File | Purpose |
|------|---------|
| `ios/ReactNativeIdfaAaid.mm` | ObjC++ wrapper — the RN module, delegates to Swift |
| `ios/ReactNativeIdfaAaid.swift` | Swift implementation — all tracking logic lives here |
| `src/NativeReactNativeIdfaAaid.ts` | Codegen spec — defines the TurboModule interface |
| `android/src/newarch/` | New Architecture Android module |
| `android/src/oldarch/` | Old Architecture Android module |
| `sparkfabrik-react-native-idfa-aaid.podspec` | iOS pod spec |
