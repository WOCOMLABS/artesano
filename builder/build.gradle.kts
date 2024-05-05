import com.vanniktech.maven.publish.KotlinJvm

plugins {
    id(libs.plugins.jvm.get().pluginId)
    id(libs.plugins.vanniktech.maven.publish.base.get().pluginId)
}

dependencies {
    implementation(project(":dsl"))
}

@Suppress("UnstableApiUsage")
//https://vanniktech.github.io/gradle-maven-publish-plugin/what/#kotlin-jvm-library
mavenPublishing {
    pomFromGradleProperties()
    configure(KotlinJvm())
}

publishing {

    repositories {

        val REPOSITORY_URL : String by project

        maven(REPOSITORY_URL) {

            name = "GitHubPackagesBuilder"

            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }

        }
    }

}