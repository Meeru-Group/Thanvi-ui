# THANVI UI — Stage 2 System Interface Prototype

This package implements a navigable Android app prototype for four THANVI UI surfaces:
- Home screen
- Lock screen preview
- Settings
- Control Center

## Honest scope
This is an ordinary Android application prototype. It does **not** replace the real Android lock
screen, notification shade, SystemUI, Settings provider, launcher, or framework. Those require
privileged AOSP/system integration, platform signing, device-specific build configuration and
testing. Do not treat this APK project as a flashable OS.

## Build
Open `apps/ThanviUI` in Android Studio on a compatible Linux/Windows/macOS machine, sync Gradle,
then build the debug APK. This project uses standard Android Views and no external UI libraries.

## Design
- Deep navy background
- Metallic gold accent
- Rounded panels and high-contrast typography
- Bottom navigation between the four prototype screens
- Control Center switches are visual prototype controls; they do not toggle actual radios
- Settings rows are prototype navigation rows; they do not change protected system settings

## Next integration stage
Port approved surfaces into AOSP SystemUI/Settings/Keyguard after CPH2757 kernel, vendor, boot,
partition and signing prerequisites are verified.
