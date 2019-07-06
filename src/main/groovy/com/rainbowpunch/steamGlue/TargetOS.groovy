package com.rainbowpunch.steamGlue


import java.nio.file.Path
import java.util.stream.Collectors

class TargetOS {
    private TargetOS() {

    }

    static List<Path> targetOSPaths(Path contentRoot, List<List<Object>> depots) {
        return depots.stream()
                .peek({depot -> validate(depot)})
                .map({depot -> ((OperatingSystem) depot.get(0)).osName + ((Architecture) depot.get(1)).architecture})
                .map({depotString -> contentRoot.resolve(depotString)})
                .collect(Collectors.toList())
    }

    private static def validate(List<Object> depot) {
        if (depot.size() != 2 ||
                !(depot.get(0) instanceof OperatingSystem) ||
                !(depot.get(1) instanceof Architecture)) {
            throw new RuntimeException("Depots must be paired by OS and Arch")
        }
    }

}

enum OperatingSystem {
    LINUX("linux"),
    OSX("osx"),
    WINDOWS("windows")

    String osName

    OperatingSystem(String osName) {
        this.osName = osName
    }
}

enum Architecture {
    ALL(""),
    ARCH_32("32"),
    ARCH_64("64")

    String architecture

    Architecture(String architecture) {
        this.architecture = architecture
    }
}
