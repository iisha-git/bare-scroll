# Blocking Approach

Blocking uses standard Android capabilities that the user grants explicitly. This document describes the techniques and their limits.

## Techniques
1. **Accessibility service.** Reads the view tree of the foreground app. Used to recognise a Reels, Shorts or Spotlight surface by its view identifiers and structure, and to read the channel name on a YouTube watch screen. When a blocked surface is found, the service performs a back action and shows the closed-feed screen.
2. **Overlay window.** A full-screen block screen shown over the app while the surface is closed, so the feed never flashes visible content.
3. **Usage access.** Detects which app is in the foreground and how long it has been used. Drives focus sessions, whole-app blocks and the usage numbers.
4. **Website blocking.** For browsers, the accessibility service reads the address bar. For a stronger fallback, a local VPN-style filter on the device can drop requests to blocked domains. Nothing leaves the device.

## Surface detection
| Surface | Signal |
|---|---|
| Instagram Reels | Reels tab selected or reel player view ids present |
| YouTube Shorts | Shorts shelf or Shorts player view ids present |
| Spotlight (Snapchat) | Spotlight tab selected |
| YouTube channel | Channel name read on the watch screen, matched to the blocklist |

## Known risks
- **Identifier drift.** Target apps change their view ids with updates. Mitigation: store detection rules as data, keep fallbacks (tab labels, content descriptions), and surface detector health.
- **Channel identification.** A video opened by search, autoplay or a shared link may not show the channel at once. Mitigation: re-check after the watch screen settles and block on first confident match.
- **Permission friction.** Accessibility and usage access need clear explanation and a guided setup.
- **Battery.** Limit event handling to supported packages and throttle checks.
- **OEM battery management.** Xiaomi, Oppo, Vivo, Samsung and others stop background work aggressively, which can kill the foreground service or the accessibility service even after the user granted every permission. Most students in the target audience use these phones. Mitigation: persistent foreground-service notification, restart on boot, a setup step that asks the user to exempt Bare Scroll from battery optimisation, detection when a service has been stopped with guidance to re-enable it, and service health shown next to detector health in settings.
- **Distribution.** Accessibility apps get extra review on public stores. A free distribution route needs to be chosen (see ROADMAP).

## Principles
- Never block when unsure.
- Never read or store content beyond what a decision needs.
- Never transmit screen content.

## First technical test
Before any interface work, a minimal prototype checks each surface and the channel read on a real phone across two versions of each target app.
