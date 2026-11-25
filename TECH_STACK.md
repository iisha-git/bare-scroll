# Tech Stack

> Status: Proposed, pending confirmation. Android only, Kotlin, Jetpack Compose, AccessibilityService plus usage access, Room and DataStore, no backend, free tools only.

| Area | Choice | Reason |
|---|---|---|
| Platform | Android, minSdk 26 | Required for accessibility and usage APIs |
| Language | Kotlin | Standard for Android |
| UI | Jetpack Compose, Material 3 base with custom black theme | Fast iteration, flat dark design |
| Type | Noto Sans | Clean app-style sans |
| Storage | Room for events, DataStore for settings | Local, no network |
| Concurrency | Kotlin coroutines and Flow | Event streams from detectors |
| DI | Hilt | Standard, testable |
| Background | Foreground service, WorkManager for housekeeping | Reliable lifecycle |
| Icons | Simple Icons (CC0) for app logos in previews and UI; check each brand's guidelines before release | Consistent monochrome set |
| Testing | JUnit, Compose UI tests, instrumented tests on real devices | See TEST_PLAN |
| Build | Gradle with version catalog, GitHub Actions for CI | Reproducible builds |

## Not used
No backend, no ad SDKs, no third-party analytics.
