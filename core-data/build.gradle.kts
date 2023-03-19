plugins {
    id("katana.android.library")
    id("katana.android.hilt")
}

android {
    namespace = "dev.nmgalo.core.data"
}

dependencies {
    implementation(project(":core-common"))
    implementation(project(":core-model"))
    implementation(project(":core-network"))
    implementation(project(":core-datastore"))
    implementation(project(":core-database"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material3)

    implementation(libs.kotlinx.coroutines.android)
}
