plugins {
    id("java")
    id("java-library")
    id("com.diffplug.spotless")
    id("io.freefair.lombok")
}

version = "0.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

tasks.test { useJUnitPlatform() }

repositories {
    mavenCentral()
}

configurations {
    compileOnly {
        extendsFrom(annotationProcessor.get())
    }
    all {
        exclude(group = "junit", module = "junit")
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}


dependencies {
    compileOnly("org.projectlombok:lombok") // java 는 어차피 lombok 과 물아일치일꺼같아 그냥 java convention에 넣음(..)
    annotationProcessor("org.projectlombok:lombok")
    runtimeOnly("io.netty:netty-resolver-dns-native-macos:4.1.76.Final:osx-aarch_64") // for m1 mac
}

val generated = "$projectDir/build/generated"

sourceSets { main { java { srcDirs(arrayOf("$projectDir/src/main/java", generated)) } } }

tasks.compileJava { options.generatedSourceOutputDirectory.set(file(generated)) }

tasks.clean { delete(file(generated)) }

spotless {
    val excludeFiles = arrayOf(".idea/**/*.*", ".vscode/**/*.*", ".github/**/*.*", "build/**/*.*")
    val prettierFile = "$rootDir/.prettierrc"

    java {
        targetExclude("/build/**/*.java")
        removeUnusedImports()
        replaceRegex("Remove wildcard imports", "import\\s+[^\\*\\s]+\\*;(\\r\\n|\\r|\\n)", "$1")
        googleJavaFormat()
        importOrder(
            "java",
            "jakarta",
            "javax",
            "lombok",
            "org.springframework",
            "",
            "org.junit",
            "com.todayant",
            "\\#",
            "\\#org.junit",
            "\\#com.todayant",
        )
        indentWithTabs(2)
        indentWithSpaces(2)
        trimTrailingWhitespace()
        endWithNewline()
    }
    kotlinGradle {
        target("*.gradle.kts") // default target for kotlinGradle
        ktfmt().googleStyle() // or ktfmt() or prettier()
        indentWithTabs(2)
        indentWithSpaces(2)
    }
    format("yaml") {
        target("**/*.yaml", "**/*.yml")
        targetExclude(*excludeFiles)
        prettier().configFile(prettierFile)
    }
    format("xml") {
        target("**/*.xml")
        targetExclude(*excludeFiles + "**/logback-spring.xml")
        prettier().config(mapOf("parser" to "html", "printWidth" to 160)).configFile(prettierFile)
    }
    format("md") {
        target("**/*.md")
        targetExclude(*excludeFiles)
        prettier().config(mapOf("printWidth" to 160)).configFile(prettierFile)
    }
}

repositories {
    mavenCentral()
    maven("https://repo.spring.io/milestone")
    maven("https://repo.spring.io/snapshot")
}
