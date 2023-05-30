plugins {
    id("katana.android.library")
    id("katana.android.hilt")
}

android {
    namespace = "dev.nmgalo.core.config"
}

dependencies {
    implementation(libs.firebase.config)
}
