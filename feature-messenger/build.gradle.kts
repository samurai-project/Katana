plugins {
    id("katana.android.feature")
}

android {

    namespace = "dev.nmgalo.feature.messenger"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.constraintlayout)
}
