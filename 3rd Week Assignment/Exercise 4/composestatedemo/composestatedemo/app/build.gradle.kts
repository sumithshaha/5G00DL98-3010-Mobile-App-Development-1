plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    // Enable @Serializable annotation for type-safe navigation
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.example.composestatedemo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.composestatedemo"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Compose BOM — manages versions for all androidx.compose.* libraries
    val composeBom = platform("androidx.compose:compose-bom:2026.01.01")
    implementation(composeBom)

    // Compose core (versions managed by BOM, no version number needed)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.foundation:foundation")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Activity Compose
    implementation("androidx.activity:activity-compose:1.10.1")

    // Navigation Compose — NOT part of the BOM, requires explicit version
    implementation("androidx.navigation:navigation-compose:2.9.7")

    // Kotlin Serialization — required for @Serializable route objects
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
}