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
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.kapt")
            }
            extensions.configure<LibraryExtension> {
                compileSdk = 32

                defaultConfig {
                    minSdk = 24
                    targetSdk = 32
                }

                composeOptions {
                    kotlinCompilerExtensionVersion =
                        libs.findVersion("androidxComposeCompiler").get().toString()
                }

                buildFeatures {
                    compose = true
                }
            }

            dependencies {
                add("implementation", project(":core-model"))
                add("implementation", project(":core-data"))
                add("implementation", project(":core-common"))

                add("implementation", libs.findDependency("coil.kt").get())

                add("implementation", libs.findDependency("androidx.hilt.navigation.compose").get())

                add(
                    "implementation",
                    libs.findDependency("androidx.compose.material.iconsExtended").get()
                )
                add("implementation", libs.findDependency("androidx.compose.material3").get())

                add(
                    "implementation",
                    libs.findDependency("androidx.lifecycle.viewModelCompose").get()
                )

                add("implementation", libs.findDependency("kotlinx.coroutines.android").get())

                add("implementation", libs.findDependency("hilt.android").get())
                add("kapt", libs.findDependency("hilt.compiler").get())
            }
        }
    }
}