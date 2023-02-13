plugins {
    id("katana.android.library")
    kotlin("kapt")
}

dependencies {
    implementation(project(":core-model"))

    implementation(libs.androidx.room)
    kapt(libs.androidx.room.compiler)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
