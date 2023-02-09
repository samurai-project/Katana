@file:Suppress("DSL_SCOPE_VIOLATION", "UNUSED_VARIABLE")

import com.google.protobuf.gradle.builtins
import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    id("katana.android.library")
    kotlin("kapt")
    alias(libs.plugins.protobuf)
}

android {
    namespace = "dev.nmgalo.core.datastore"
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                val java by registering {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    implementation(project(":core-common"))
    implementation(project(":core-model"))

    implementation(libs.androidx.datastore)
    implementation(libs.protobuf.kotlin.lite)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
