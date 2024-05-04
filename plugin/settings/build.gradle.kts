@file:Suppress("UnstableApiUsage")

import com.vanniktech.maven.publish.GradlePublishPlugin

plugins {
    id(libs.plugins.kotlin.dsl.get().pluginId)
    id(libs.plugins.vanniktech.maven.publish.base.get().pluginId)
    id(libs.plugins.gradle.plugin.publish.get().pluginId)

}

dependencies {
    implementation(libs.kotlin.gradle.plugin)

    implementation(project(":stencil"))
    implementation(project(":builder"))
    implementation(project(":dsl"))
}

gradlePlugin {
    val WEBSITE : String by project
    val VCS_URL : String by project

    website = WEBSITE
    vcsUrl = VCS_URL

    plugins {

        create("settingsArtesano") {
            id = "$group.gradle.settings.settings-artesano-plugin"
            implementationClass = "$group.gradle.settings.SettingsArtesanoPlugin"
            displayName = "Settings Artesano"
            description = """
            Settings Artesano Plugin
            • creates a dsl called artesano that defines 4 different types of subprojects , in essence is a project generator.
            
        """.trimIndent()
            tags = listOf("open api", "docker compose", "surrealdb", "project template")
        }
    }
}

@Suppress("UnstableApiUsage")
mavenPublishing {
    pomFromGradleProperties()
    configure(GradlePublishPlugin())
}

publishing {

    repositories {

        val REPOSITORY_URL : String by project

        maven(REPOSITORY_URL) {

            name = "GitHubPackagesSettings"

            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }

        }
    }

}