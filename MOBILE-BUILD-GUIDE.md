# Build THANVI UI using only an Android phone

No PC or Android Studio is required for this cloud-build route.

1. Download and extract this ZIP using your Android file manager.
2. In a mobile browser, create a new repository on https://github.com/new .
3. Upload the extracted project files and folders to the repository. Ensure `.github/workflows/android-build.yml` is included. GitHub's web uploader may not show hidden `.github` folders; if so, create `.github/workflows/android-build.yml` using the GitHub website's Add file > Create new file flow and paste the workflow contents from this package.
4. Open the repository's Actions tab. If prompted, enable GitHub Actions.
5. Select **Build THANVI UI APK** and tap **Run workflow** (or push to main to trigger it).
6. Wait for the workflow to finish successfully.
7. Open the completed run, find **Artifacts**, and download `THANVI-UI-debug-apk`.
8. Extract the artifact ZIP and install `app-debug.apk`. Android may ask you to allow installation from that browser/file manager.

This produces a debug APK of the THANVI UI prototype. It does not install a custom operating system or replace OPPO's real SystemUI / lock screen.

If GitHub blocks uploading the whole folder tree from mobile, use GitHub's web interface to create files individually, or use Termux + git after installing git.
