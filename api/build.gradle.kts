plugins {
  id(libs.plugins.javaConvention.get().pluginId)
  id("java")
  id("org.springframework.boot") version "3.3.5"
  id("io.spring.dependency-management") version "1.1.6"
}

group = "com.todayant.joint"

version = "0.0.1-SNAPSHOT"

springBoot { buildInfo() }

repositories { mavenCentral() }

dependencies {
  implementation(project(":core"))
  implementation("org.springframework.boot:spring-boot-starter-web")
  testImplementation(platform("org.junit:junit-bom:5.10.0"))
  testImplementation("org.junit.jupiter:junit-jupiter")
  testImplementation("org.springframework.cloud:spring-cloud-contract-wiremock:4.1.4")
}

tasks.bootJar { enabled = true }

tasks.test {
  useJUnitPlatform()
  systemProperties["spring.profiles.active"] = "test"
}
