plugins {
    id("katana.android.library")
    kotlin("kapt")
}

android {
    namespace = "dev.nmgalo.core.common"
}

dependencies {
    implementation(libs.androidx.core.ktx)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
