# Architecture

> Status: Proposed, pending confirmation. Android only, Kotlin, Jetpack Compose, AccessibilityService plus usage access, Room and DataStore, no backend, free tools only.

## Overview
A single Android app with a foreground service that watches the foreground app and screen content, decides whether a blocked surface is open, and shows a block screen.

```
 UI (Compose)  <->  Settings / Stats repository (Room, DataStore)
      ^                         ^
      |                         |
 Session manager  <->  Rules engine  <->  Detectors
                                         - Accessibility detector
                                         - Usage detector
                                         - Web detector
                              |
                        Block overlay / action
```

## Modules
- **ui**: screens, navigation, theme.
- **rules**: evaluates the current signal against the user's blocklists, schedules and session state.
- **detectors**: produce signals (foreground app, visible surface id, current channel, current domain).
- **enforcement**: closes the surface (back/home action) and shows the closed-feed screen.
- **sessions**: focus session timer, lock state, emergency unlock countdown.
- **data**: local database and preferences.
- **insights**: aggregates events into daily and yearly figures.

## Service lifecycle
A foreground service keeps detection running. It restarts on boot and after app update. If a permission is revoked, the app shows a clear banner and stops claiming protection.

## Failure behaviour
If a detector cannot identify a surface, it does not block. A missed block is better than blocking the wrong thing. Detector health is shown in settings.

## Constraints
Everything runs on device. No network is needed for blocking.
