import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2024.03"

project {

    buildType(BuildConfigurationName)
}

object BuildConfigurationName : BuildType({
    name = "Build Configuration Name"
    description = "lorem ipsum"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        powerShell {
            name = "first step"
            id = "first_step"
            scriptMode = file {
                path = "firstStep.ps1"
            }
            param("jetbrains_powershell_script_code", """Write-Host "hello world"""")
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }
})
