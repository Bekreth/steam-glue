package com.rainbowpunch.steamGlue


import org.gradle.api.tasks.Delete
import org.gradle.api.tasks.TaskAction

import java.util.regex.Pattern
import java.util.stream.Collectors

class SteamClean extends Delete {

    List<String> exclude

    @TaskAction
    def steamClean() {
        SteamConfiguration configuration = project.extensions.getByType(SteamConfiguration.class)

        List<Pattern> patterns = exclude.stream()
                .map({exclusion -> Pattern.compile(exclusion)})
                .collect(Collectors.toList())

        Set<String> filesToDelete = configuration.getDepots().stream()
                .flatMap({path -> Arrays.stream(path.toFile().listFiles())})
                .filter({file -> excludedPatterns(patterns, file.name)})
                .map({file -> file.toString()})
                .collect(Collectors.toSet())

        super.setDelete(filesToDelete)
        super.clean()
    }

    private static boolean excludedPatterns(List<Pattern> patterns, String filename) {
        patterns.stream()
                .map({pattern -> pattern.matcher(filename)})
                .map({matcher -> !matcher.find()})
                .any {t -> t}
    }

}
