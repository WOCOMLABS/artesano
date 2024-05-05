import io.ight.gradle.builder.DockerCompose
import io.ight.gradle.settings.artesano

pluginManagement {

    repositories {
        // until the plugin is approved in the gradle plugin portal, the artifact is published in
        // github packages which means you need to authenticate to download it

        // https://github.com/settings/tokens/new

        maven("https://maven.pkg.github.com/WOCOMLABS/artesano") {
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
    id("io.ight.gradle.settings.settings-artesano-plugin") version "0.0.1-RC6"
}

rootProject.name = "dockercompose"

artesano {

    dockerCompose {
        type { DockerCompose.Type.Surrealdb }
        containerName { "surreal-db-artesano" }
        containerPort { "3001" }
        containerVolume { "surreal-db-artesano-volume" }
    }

    dockerCompose {
        type { DockerCompose.Type.Nexus }
        containerName { "nexus-artesano" }
        containerPort { "3002" }
        containerVolume { "nexus-artesano-volume" }
    }
}