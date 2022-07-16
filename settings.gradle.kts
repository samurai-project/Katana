pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven(url = "https://androidx.dev/snapshots/builds/8455591/artifacts/repository") {
            content {
                includeGroupByRegex("androidx\\..*")
            }
        }
        google()
        mavenCentral()
    }
}


rootProject.name = "Katana"
include(":app")
include(":core-data")
