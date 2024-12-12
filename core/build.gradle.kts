plugins {
  id(libs.plugins.javaConvention.get().pluginId)
  alias(spring.plugins.boot)
  alias(spring.plugins.dependency)
}

group = "com.todayant.joint"

version = "0.0.1-SNAPSHOT"

repositories { mavenCentral() }

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.springframework.data:spring-data-envers")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")

  api("org.springframework.boot:spring-boot-starter-data-redis")
  api(spring.queryDslJpa) { artifact { classifier = "jakarta" } }

  compileOnly("org.projectlombok:lombok:1.18.34")
  runtimeOnly("com.mysql:mysql-connector-j")

  annotationProcessor(libs.bundles.jakarataAnnotationProcessor)
  annotationProcessor(spring.queryDslApt) { artifact { classifier = "jakarta" } }
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.boot:spring-boot-testcontainers")
  testImplementation("org.springframework.cloud:spring-cloud-contract-wiremock:4.1.4")
  testImplementation("com.h2database:h2")
  testImplementation("io.projectreactor:reactor-test")
  testImplementation("org.testcontainers:junit-jupiter")
  testImplementation(platform(libs.testcontainersBom))
  testImplementation(libs.testcontainersJunitRedis)
}

tasks.bootJar { enabled = false }

tasks.test { useJUnitPlatform() }
