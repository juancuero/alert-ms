plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":domain"))
    implementation("org.springframework:spring-context:6.1.6")
    implementation("org.slf4j:slf4j-api:2.0.13")
    implementation(kotlin("reflect"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation("org.mockito:mockito-core:5.11.0")

}
