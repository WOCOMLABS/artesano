plugins {
    id(libs.plugins.kotlin.dsl.get().pluginId)
    id(libs.plugins.vanniktech.maven.publish.base.get().pluginId)
}

dependencies {
    implementation(project(":dsl"))
}

@Suppress("UnstableApiUsage")
mavenPublishing {
    pomFromGradleProperties()
}

publishing {

    repositories {

        val REPOSITORY_URL : String by project

        maven(REPOSITORY_URL) {

            name = "GitHubPackagesTask"

            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }

        }
    }

}