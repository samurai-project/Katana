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
        google()
        mavenCentral()
    }
}

rootProject.name = "Katana"
include(":app")
include(":benchmark")
include(":core-data")
include(":core-network")
include(":core-model")
include(":core-datastore")
include(":core-database")
include(":core-common")
include(":core-ui")
include(":feature-wall")
include(":feature-messenger")
include(":feature-profile")
include(":feature-settings")
