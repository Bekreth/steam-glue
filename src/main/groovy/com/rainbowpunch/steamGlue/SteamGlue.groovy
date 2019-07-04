package com.rainbowpunch.steamGlue

import org.gradle.api.Plugin
import org.gradle.api.Project

class SteamGlue implements Plugin<Project> {

    void apply(Project project) {
        def extension = project.extensions.create('steam', SteamConfiguration)
        project.task('hello') {
            doLast {
                println(extension.sdk)
                println(extension.contentRoot)
                println(extension.buildEnvironment)
                println(extension.username)
                println(extension.depotScript)
            }
        }
    }

}
