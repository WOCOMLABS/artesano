plugins {
    id("io.ight.gradle.project.docker-artesano-plugin") version "0.0.1-RC6" apply false
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