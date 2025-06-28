plugins {
    kotlin("jvm") version "1.9.0" apply false
    kotlin("plugin.spring") version "1.9.0" apply false
    id("org.springframework.boot") version "3.2.0" apply false
    id("io.spring.dependency-management") version "1.1.4" apply false
}

subprojects {
    apply(plugin = "jacoco")

    repositories {
        mavenCentral()
    }



    afterEvaluate {
        extensions.configure<JacocoPluginExtension> {
            toolVersion = "0.8.10"
        }

        if (tasks.findByName("test") != null && tasks.findByName("jacocoTestReport") != null) {
            tasks.named("test").configure {
                if (this is Test) {
                    useJUnitPlatform()
                    finalizedBy("jacocoTestReport")
                }
            }


            tasks.named<JacocoReport>("jacocoTestReport") {
                dependsOn("test")

                reports {
                    xml.required.set(true)
                    html.required.set(true)
                }

                classDirectories.setFrom(
                    fileTree("$buildDir/classes/kotlin/main") {
                        exclude("**/generated/**")
                    }
                )
                sourceDirectories.setFrom(files("src/main/kotlin"))
                executionData.setFrom(fileTree(buildDir).include("jacoco/test.exec"))
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = "17"
    }
}

tasks.register<JacocoReport>("jacocoMergedReport") {
    dependsOn(subprojects.mapNotNull { it.tasks.findByName("jacocoTestReport") })

    val sourceDirs = files(subprojects.mapNotNull {
        val src = it.projectDir.resolve("src/main/kotlin")
        src.takeIf { d -> d.exists() }
    })
    val classDirs = files(subprojects.mapNotNull {
        val cls = it.buildDir.resolve("classes/kotlin/main")
        cls.takeIf { d -> d.exists() }
    })
    val execData = files(subprojects.mapNotNull {
        val exec = it.buildDir.resolve("jacoco/test.exec")
        exec.takeIf { f -> f.exists() }
    })

    additionalSourceDirs.setFrom(sourceDirs)
    sourceDirectories.setFrom(sourceDirs)
    classDirectories.setFrom(classDirs)
    executionData.setFrom(execData)

    reports {
        xml.required.set(true)
        html.required.set(true)
        html.outputLocation.set(layout.buildDirectory.dir("reports/jacoco/jacocoMergedReport/html"))
        xml.outputLocation.set(layout.buildDirectory.file("reports/jacoco/jacocoMergedReport/report.xml"))
    }


}
