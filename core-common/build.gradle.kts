plugins {
    id("katana.android.library")
    id("katana.android.hilt")
}

android {
    namespace = "dev.nmgalo.core.common"
}

dependencies {
    implementation(libs.androidx.core.ktx)

    implementation(libs.kotlinx.coroutines.android)

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.junit)
    testImplementation(libs.turbine)
}
