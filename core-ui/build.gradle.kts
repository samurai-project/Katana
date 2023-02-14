plugins {
    id("katana.android.library")
}

android {
    namespace = "dev.nmgalo.core.ui"
}

dependencies {
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling.preview)
}
