plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "in.meerugroup.thanviui"
    compileSdk = 35
    defaultConfig {
        applicationId = "in.meerugroup.thanviui"
        minSdk = 26
        targetSdk = 35
        versionCode = 2
        versionName = "0.2.0"
    }
}
