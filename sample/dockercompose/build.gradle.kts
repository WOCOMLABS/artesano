plugins {
    id("io.ight.gradle.project.docker-artesano-plugin") version "0.0.1-RC4" apply false
    id("io.ight.gradle.project.open-api-artesano-plugin") version "0.0.1-RC4" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.23" apply false
}

allprojects {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.github.com/WOCOMLABS/artesano") {
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}