@file:Suppress("unused")

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            pluginManager.apply {
                apply("katana.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.kapt")
            }
            extensions.configure<LibraryExtension> {
                composeOptions {
                    kotlinCompilerExtensionVersion =
                        libs.findVersion("androidxComposeCompiler").get().toString()
                }

                buildFeatures {
                    compose = true
                }
            }

            dependencies {
                add("implementation", fileTree("libs") { include("*.jar") })

                add("implementation", project(":core-model"))
                add("implementation", project(":core-data"))
                add("implementation", project(":core-common"))
                add("implementation", project(":core-ui"))

                add("implementation", libs.findLibrary("coil.kt").get())

                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())

                add(
                    "implementation",
                    libs.findLibrary("androidx.compose.material.iconsExtended").get()
                )
                add("implementation", libs.findLibrary("androidx.compose.material3").get())
                add("implementation", libs.findLibrary("androidx.compose.ui.tooling.preview").get())

                add(
                    "implementation",
                    libs.findLibrary("androidx.lifecycle.viewModelCompose").get()
                )

                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())

                add("implementation", libs.findLibrary("hilt.android").get())
                add("kapt", libs.findLibrary("hilt.compiler").get())
            }
        }
    }
}
