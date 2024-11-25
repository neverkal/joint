dependencyResolutionManagement {
    versionCatalogs {
        create("plibs") {
            from(files("../gradle/plibs.versions.toml"))
        }
    }
}
