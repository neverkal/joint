dependencyResolutionManagement {
    versionCatalogs {
        create("spring") {
            from(files("gradle/spring.versions.toml"))
        }
    }
}

pluginManagement {
    repositories {
        mavenCentral()
        maven("https://repo.spring.io/milestone")
        maven("https://repo.spring.io/snapshot")
        gradlePluginPortal()
    }
}

rootProject.name = "joint"
include("core")
include("api")
