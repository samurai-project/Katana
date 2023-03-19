plugins {
    id("katana.android.library")
    id("katana.android.hilt")
}

android {
    namespace = "dev.nmgalo.core.database"
}

kapt {
    arguments {
        arg("room.schemaLocation", "$projectDir/schemas")
    }
}

dependencies {
    implementation(project(":core-model"))

    implementation(libs.androidx.room)
    kapt(libs.androidx.room.compiler)
}
