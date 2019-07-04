package com.rainbowpunch.steamGlue

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
