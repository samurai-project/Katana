plugins {
    id("katana.android.feature")
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.constraintlayout)
}
