@Suppress("DSL_SCOPE_VIOLATION") // TODO Remove once https://github.com/gradle/gradle/issues/22797 is fixed

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.detekt) apply true
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt) apply false
}

apply {
    from("git-hooks.gradle.kts")
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    exclude("**/build/**")
    reports {
        html.required.set(true)
        xml.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    source = files(rootDir)
    config = files("$rootDir/config/detekt.yml")
    baseline = file("$rootDir/config/baseline.xml")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

subprojects {
    tasks.whenTaskAdded {
        if (name == "preBuild") {
            mustRunAfter(":installGitHooks")
        }
    }
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
}
