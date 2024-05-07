import com.vanniktech.maven.publish.MavenPublishBaseExtension

plugins {
    alias(libs.plugins.kotlin.dsl) apply false
    alias(libs.plugins.jvm) apply false
    alias(libs.plugins.vanniktech.maven.publish) apply false
    alias(libs.plugins.gradle.plugin.publish) apply false
}

val vanniktechPluginId : String = libs.plugins.vanniktech.maven.publish.get().pluginId
val jvmPluginId : String = libs.plugins.jvm.get().pluginId
val kotlinDslPluginId : String = libs.plugins.kotlin.dsl.get().pluginId
val gradlePluginPublishPluginId : String = libs.plugins.gradle.plugin.publish.get().pluginId

enum class TypedProject(
    val projectName : String ,
    val vanniktech : Boolean = true ,
    val jvm : Boolean = false ,
    val versionCatalog : Boolean = false ,
    val kotlinDsl : Boolean = false ,
    val gradlePublish : Boolean = false ,
) {

    Builder(projectName = ":builder" , jvm = true) ,
    Catalog(projectName = "catalog" , versionCatalog = true) ,
    Dsl(projectName = ":dsl" , jvm = true) ,
    Docker(projectName = ":plugin:project:docker" , kotlinDsl = true , gradlePublish = true) ,
    Openapi(projectName = ":plugin:project:openapi" , kotlinDsl = true , gradlePublish = true) ,
    Settings(projectName = ":plugin:settings" , kotlinDsl = true , gradlePublish = true) ,
    Stencil(projectName = ":stencil" , jvm = true) ,
    Task(projectName = ":plugin:task" , kotlinDsl = true);

}

val projects : Map<Project, TypedProject> = TypedProject.values()
    .associateBy { typedProject -> project(typedProject.projectName) }


configure(projects.keys.toList()) {

    projects[this]?.apply {

        group = properties["GROUP"] as String
        version = properties["VERSION_NAME"] as String

        repositories {
            gradlePluginPortal()
            mavenCentral()
            mavenLocal()
        }

        if (vanniktech) {
            apply(plugin = vanniktechPluginId)
            extensions.configureVanniktech()
            extensions.configureForGithub()
        }

        if (jvm) {
            apply(plugin = jvmPluginId)
        }

        if (versionCatalog) {
            apply<VersionCatalogPlugin>()
        }

        if (kotlinDsl) {
            apply(plugin = kotlinDslPluginId)
        }

        if (gradlePublish) {
            apply(plugin = gradlePluginPublishPluginId)
        }
    }
}

@Suppress("UnstableApiUsage")
//https://vanniktech.github.io/gradle-maven-publish-plugin/what/#kotlin-jvm-library
fun ExtensionContainer.configureVanniktech() {
    configure(MavenPublishBaseExtension::class) {
        configureBasedOnAppliedPlugins()
    }
}


fun ExtensionContainer.configureForGithub() {
    configure(PublishingExtension::class) {

        repositories {

            val REPOSITORY_URL : String by project

            maven(REPOSITORY_URL) {

                name = "GitHubPackages"

                credentials {
                    username = System.getenv("GITHUB_ACTOR")
                    password = System.getenv("GITHUB_TOKEN")
                }

            }
        }
    }
}