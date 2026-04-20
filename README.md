# Sokohub Android (Hotwire Native)

This repository contains the **Android app for Sokohub Web**, built with **Hotwire Native (Turbo Android)**.

The app wraps the Sokohub web experience and adds native Android navigation patterns like:

- Bottom tab navigation (Home, Saved Ads, Messages, My Ads, Profile)
- Modal presentation for auth/form flows
- Native image viewer for image URLs
- Deep-link handling via `Intent.ACTION_VIEW`
- JavaScript bridge support between web content and native code

## Tech stack

- **Kotlin**
- **Android SDK 36** (`minSdk 28`)
- **Turbo Android** (`dev.hotwire:turbo:7.0.+`)
- **Material Components**
- **Glide** (for native image display)

## Project structure

- `app/src/main/java/com/example/sokohub/MainActivity.kt`  
  Main entry point. Configures Turbo delegates and bottom tab switching.
- `app/src/main/java/SessionNavHostFragment.kt`  
  Sets up each Turbo session, user agent, JS interface, and path configuration.
- `app/src/main/assets/json/configuration.json`  
  Turbo path rules that map web routes to native destinations.
- `app/src/main/java/WebFragment.kt` + `ModalWebFragment.kt` + `TabbedWebFragment.kt`  
  Main Hotwire web fragment variants.
- `app/src/main/java/com/example/sokohub/ImageViewerFragment.kt`  
  Native image viewer destination.
- `app/src/main/java/Api.kt`  
  Base URLs and tab URLs for debug/release builds.

## URL configuration

`Api.kt` controls where the app points:

- **Debug**: `http://10.0.2.2:3000` (Android emulator to local machine)
- **Release**: an ngrok URL (replace with your production host when needed)

Update this file to match your backend environment before release.

## Requirements

- Android Studio (latest stable recommended)
- JDK 11
- Android SDK 36
- A running Sokohub web server

## Getting started

1. Clone the repository.
2. Open the project in Android Studio.
3. Start your Sokohub web app.
   - If using emulator + local backend, ensure backend is reachable at `http://10.0.2.2:3000`.
4. Sync Gradle.
5. Run the `app` target on an emulator/device.

Or from terminal:

```bash
./gradlew assembleDebug
```

## Hotwire Native route mapping

Turbo path config (`configuration.json`) currently defines:

- All routes -> default web fragment
- Core tab routes (`/`, `/profile`, `/my_listings`, `/saved_listings`, `/conversations`) -> tab fragment with root replacement
- Auth/form-like routes (`/login`, `/sign_up`, password reset, etc.) -> modal web fragment
- Image file URLs (`.jpg`, `.png`, `.webp`, etc.) -> native `ImageViewerFragment`

## Notes

- `android:usesCleartextTraffic="true"` is enabled to support local HTTP in development.
- A JavaScript bridge script is injected from `app/src/main/assets/js/bridge.js` and forwarded through `messageHandler`.

## Testing

Run unit tests:

```bash
./gradlew test
```

Run instrumentation tests (device/emulator required):

```bash
./gradlew connectedAndroidTest
```

## Pairing with Sokohub Web (Important)

If you are pairing this app with the Sokohub web app, keep route/path updates in sync between the Rails (or web backend) routes and `configuration.json` to ensure native navigation behaves correctly.

When adding or changing web routes, also update `app/src/main/assets/json/configuration.json` in the same PR whenever possible.
