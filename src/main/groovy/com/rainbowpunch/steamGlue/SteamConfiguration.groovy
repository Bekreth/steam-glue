package com.rainbowpunch.steamGlue

import java.nio.file.Path
import java.nio.file.Paths

class SteamConfiguration {

    String sdk
    String contentRoot
    BuildEnvironment buildEnvironment

    String username
    String appBuildScript

    List<List<Object>> depots

    protected Path sdkPath() { Paths.get(sdk) }
    protected Path contentRootPath() {
        if (contentRoot.startsWith(File.pathSeparator)) Paths.get(contentRoot)
        else sdkPath().resolve(contentRoot)
    }

    protected List<Path> getDepots() {
        TargetOS.targetOSPaths(contentRootPath(), depots)
    }
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

