# Privacy

## Stance
Bare Scroll works on the device. There is no account and no analytics that leave the phone.

## Data stored locally
- Blocklists (apps, channels, domains), schedules, session settings.
- Event counts and durations for blocked surfaces, used only for the stats screens.

## Data never collected
Screen content, messages, browsing history, contacts, location, or identifiers.

## Permissions
| Permission | Why | Shown to user |
|---|---|---|
| Accessibility service | Recognise blocked surfaces and the open channel or domain | Plain-language explanation before the system prompt |
| Usage access | Foreground app and time used | Same |
| Display over other apps | Block screen | Same |
| Notifications | Session and status notices | Optional |

## Handling
Content read by the accessibility service is evaluated in memory and discarded. Only the decision and a counter are saved. Users can clear all data in settings.

## Transparency
Each permission screen states what is read and what is not. Projections are labelled as projections.
