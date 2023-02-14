plugins {
    id("katana.android.library")
    kotlin("kapt")
}

android {
    namespace = "dev.nmgalo.core.database"
}

dependencies {
    implementation(project(":core-model"))

    implementation(libs.androidx.room)
    kapt(libs.androidx.room.compiler)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
