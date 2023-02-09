plugins {
    id("katana.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

dependencies {
    implementation(project(":core-common"))
    implementation(project(":core-model"))
    implementation(project(":core-network"))
    implementation(project(":core-datastore"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material3)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
