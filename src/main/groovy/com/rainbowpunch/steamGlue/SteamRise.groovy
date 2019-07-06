package com.rainbowpunch.steamGlue

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class SteamRise extends DefaultTask {

    String commitMessage

    @TaskAction
    def rise() {
        println(commitMessage)

        SteamConfiguration configuration = project.extensions.getByType(SteamConfiguration)

        String command = configuration.buildEnvironment.steamShellFile(configuration.sdkPath()).path
        command += " +login ${configuration.username} PASSWORD"
        command += " +run_app_build ${configuration.appBuildScript}"
        command += " +exit"

        println(command)
    }


}
