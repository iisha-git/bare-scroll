# Data Model

All data is local.

## Tables (Room)
- **blocked_surface** (id, app_package, surface_key, enabled)
- **blocked_channel** (id, platform, channel_name, channel_key, added_at)
- **blocked_domain** (id, domain, added_at)
- **schedule** (id, name, days_mask, start_minute, end_minute, enabled)
- **session** (id, started_at, planned_minutes, locked, ended_at, ended_reason)
- **block_event** (id, ts, app_package, surface_key, action)
- **usage_daily** (date, app_package, foreground_seconds, opens)
- **detection_rule** (id, app_package, surface_key, version, matcher_json)

## Preferences (DataStore)
Onboarding done, distracting app list, unlock wait seconds, theme variant.

## Retention
Events older than 400 days are aggregated into usage_daily and removed. Users can wipe everything from settings.

## Projections
Yearly figures use the average of the last 14 days times 365 and are labelled as projections.
