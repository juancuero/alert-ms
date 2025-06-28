
plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":application"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
   testImplementation("org.mockito:mockito-core:5.11.0")
    implementation(project(":domain"))
}
