plugins {
  java
  id("org.springframework.boot") version "3.3.5"
  id("io.spring.dependency-management") version "1.1.6"
  id("com.diffplug.spotless") version "6.23.3"
}

group = "com.todayant"

version = "0.0.1-SNAPSHOT"

java {
  sourceCompatibility = JavaVersion.VERSION_21
  toolchain { languageVersion = JavaLanguageVersion.of(21) }
}

repositories { mavenCentral() }

subprojects {
  apply(plugin = "java")
  apply(plugin = "io.spring.dependency-management")
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
}

val generated = "$projectDir/build/generated"

sourceSets { main { java { srcDirs(arrayOf("$projectDir/src/main/java", generated)) } } }

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
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
      "com.wesang",
      "\\#",
      "\\#org.junit",
      "\\#com.wesang",
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

// 컴파일 전 spotless 적용
tasks.named("compileJava") { dependsOn("spotlessApply") }

tasks.withType<Test> { useJUnitPlatform() }
