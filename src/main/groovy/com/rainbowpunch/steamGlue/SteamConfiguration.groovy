package com.rainbowpunch.steamGlue

import java.nio.file.Path

class SteamConfiguration {

    String sdk
    String contentRoot
    BuildEnvironment buildEnvironment

    String username
    String depotScript

    protected Path sdkPath() { new File(sdk).toPath() }
    protected Path contentRootPath() { new File(sdk + contentRoot).toPath() }
}

enum BuildEnvironment {
    LINUX(["tools", "ContentBuilder", "builder_linux", "steamcmd.sh"]),
    MAC(["tools", "ContentBuilder", "builder_osx", "steamcmd.sh"]),
    WINDOWS(["tools", "ContentBuilder", "builder"])

    Closure<File> steamShellFile

    BuildEnvironment(List<String> steamShellPath) {
        String combinedPath = ""

        steamShellPath.each { String step ->
            combinedPath += File.pathSeparator + step
        }

        steamShellFile = { Path sdkRoot ->
            sdkRoot.resolve(combinedPath).toFile()
        }
    }


}

