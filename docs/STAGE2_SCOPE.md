# Stage 2 — System UI prototype scope

## Implemented in this package
- Four-screen navigation: Home, Lock, Control Center and Settings.
- Navy/gold visual language.
- Home screen clock/date panel, quick-action cards and app grid placeholders.
- Lock screen visual preview with clock and notification cards.
- Control Center tiles and brightness slider (prototype-only).
- Settings categories for connectivity, display, privacy, security and system updates.

## Not implemented
- Real keyguard replacement or biometric authentication.
- Real notification shade or Quick Settings integration.
- Actual Wi-Fi, Bluetooth, mobile-data, airplane-mode or brightness system changes.
- Real system settings writes, VPN, firewall, app scanning or secure vault.
- AOSP framework/SystemUI patches, platform signing, device tree or firmware integration.

## Acceptance tests for the prototype
1. App launches without requesting sensitive permissions.
2. Bottom navigation switches between all four screens.
3. Control Center visual toggles change their selected appearance only.
4. Brightness slider updates its displayed percentage only.
5. UI remains usable on a narrow phone display.
