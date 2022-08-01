import java.util.Locale

fun isLinuxOrMacOs() =
    System.getProperty("os.name").toLowerCase(Locale.ROOT) in listOf("linux", "mac os", "macos")

tasks {
    register<Copy>("copyGitHooks") {
        description = "Copies the git hooks from git-hooks to the .git folder."
        group = "git hooks"
        from("$rootDir/config/") {
            include("**/*.sh")
            rename("(.*).sh", "$1")
        }
        into("$rootDir/.git/hooks")
    }

    register<Exec>("installGitHooks") {
        description = "Installs the pre-commit git hooks from git-hooks."
        group = "git hooks"
        workingDir(rootDir)
        commandLine("chmod")
        args("-R", "+x", ".git/hooks/")
        dependsOn(named("copyGitHooks"))
        onlyIf {
            isLinuxOrMacOs()
        }
        doLast {
            logger.info("Git hooks installed successfully.")
        }
    }

    register<Delete>("deleteGitHooks") {
        description = "Delete the pre-commit git hooks."
        group = "git hooks"
        delete(fileTree(".git/hooks/"))
    }
}
