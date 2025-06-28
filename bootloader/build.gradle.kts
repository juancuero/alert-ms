
plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":application"))
    implementation(project(":domain"))
    implementation(project(":infrastructure"))
    implementation(project(":entrypoints"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
}
