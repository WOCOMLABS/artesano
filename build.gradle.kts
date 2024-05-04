plugins {
    alias(libs.plugins.kotlin.dsl) apply false
    alias(libs.plugins.jvm) apply false
    alias(libs.plugins.vanniktech.maven.publish.base) apply false
    alias(libs.plugins.gradle.plugin.publish) apply false
}

subprojects {

    group = properties["GROUP"] as String
    version = properties["VERSION_NAME"] as String

    repositories {
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
    }

}
