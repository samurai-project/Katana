plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 24
        targetSdk = 32
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling.preview)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}