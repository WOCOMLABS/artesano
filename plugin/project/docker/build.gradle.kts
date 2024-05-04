@file:Suppress("UnstableApiUsage")

import com.vanniktech.maven.publish.GradlePublishPlugin

plugins {
    id(libs.plugins.kotlin.dsl.get().pluginId)
    id(libs.plugins.vanniktech.maven.publish.base.get().pluginId)
    id(libs.plugins.gradle.plugin.publish.get().pluginId)
}

dependencies {
    implementation(project(":plugin:task"))
    implementation(project(":stencil"))
    implementation(project(":builder"))
    implementation(gradleKotlinDsl())
    implementation(libs.openapi.generator.gradle.plugin)
    implementation(libs.gradle.kotlin.dsl.plugins)
    implementation(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    val WEBSITE : String by project
    val VCS_URL : String by project

    website = WEBSITE
    vcsUrl = VCS_URL

    plugins {
        create("dockerArtesano") {
            id = "$group.gradle.project.docker-artesano-plugin"
            implementationClass = "$group.gradle.project.DockerArtesanoPlugin"
            displayName = " Docker Artesano"
            description = """
            Docker Artesano Plugin
           
            • adds dockerComposeUp and dockerComposeDown tasks for the defined docker-compose at the root of the project
        """.trimIndent()
            tags = listOf("docker compose", "surrealdb", "nexus")
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

            name = "GitHubPackagesProjectDocker"

            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }

        }
    }

}