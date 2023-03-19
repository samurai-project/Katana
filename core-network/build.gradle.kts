plugins {
    id("katana.android.library")
    id("katana.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "dev.nmgalo.core.network"
}

dependencies {
    implementation(project(":core-common"))
    implementation(project(":core-model"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material3)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
}
