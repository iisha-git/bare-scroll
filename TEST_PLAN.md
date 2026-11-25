# Test Plan

## Unit tests
Rules engine decisions, schedule evaluation, session timer and lock state, projection maths, data retention.

## Instrumented tests
Detector rules against recorded view hierarchies of each target app version. Room migrations. Foreground service restart after boot and update.

## Device tests (real phones)
| Case | Expected |
|---|---|
| Open Reels from the home tab | Closed-feed screen, no feed flash |
| Open a Short from a search result | Closed |
| Open a blocked channel via autoplay | Closed after first confident match |
| Open a blocked channel via shared link | Closed |
| Allowed channel | Plays normally |
| Messages in a blocked app | Open normally |
| Blocked website in two browsers | Closed |
| Locked session, swipe app away | Blocking continues |
| Emergency unlock | Opens only after the wait |
| Permission revoked | Banner shown, no false claim of protection |

## Regression
Re-run detector tests whenever a target app updates. Keep a table of tested app versions.

## Quality bars
No crash in a 24 hour run. Battery use stays low. UI checked at small and large font sizes.
