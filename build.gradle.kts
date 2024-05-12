import com.vanniktech.maven.publish.MavenPublishBaseExtension
import org.jetbrains.dokka.DokkaConfiguration
import org.jetbrains.dokka.gradle.DokkaMultiModuleTask
import org.jetbrains.dokka.gradle.DokkaTaskPartial
import java.net.URL

plugins {
    alias(libs.plugins.org.jetbrains.dokka) apply true
    alias(libs.plugins.kotlin.dsl) apply false
    alias(libs.plugins.jvm) apply false
    alias(libs.plugins.vanniktech.maven.publish) apply false
    alias(libs.plugins.gradle.plugin.publish) apply false
}

val vanniktechPluginId : String = libs.plugins.vanniktech.maven.publish.get().pluginId
val dokkaPluginId : String = libs.plugins.org.jetbrains.dokka.get().pluginId
val jvmPluginId : String = libs.plugins.jvm.get().pluginId
val kotlinDslPluginId : String = libs.plugins.kotlin.dsl.get().pluginId
val gradlePluginPublishPluginId : String = libs.plugins.gradle.plugin.publish.get().pluginId
val downloadTaskLibrary : String = libs.de.undercouch.gradle.download.task.get().toString()

enum class TypedProject(
    val projectName : String ,
    val vanniktech : Boolean = true ,
    val dokka : Boolean = true ,
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
    Root(projectName = ":plugin:project:root" , kotlinDsl = true , gradlePublish = true) ,
    Ijhttp(projectName = ":plugin:project:ijhttp" , kotlinDsl = true , gradlePublish = true) ,
    Stencil(projectName = ":stencil" , jvm = true) ,
}

val projects : Map<Project , TypedProject> = TypedProject.values()
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

        if (dokka) {
            apply(plugin = dokkaPluginId)

            tasks.withType<DokkaTaskPartial>().configureEach {

                outputDirectory.set(rootProject.layout.projectDirectory.dir("docs${projectName.replace(":", "/")}"))
                dokkaSourceSets.configureEach {
                    documentedVisibilities.set(
                        setOf(
                            DokkaConfiguration.Visibility.PUBLIC ,
                            //DokkaConfiguration.Visibility.PROTECTED
                        )
                    )

                    sourceLink {

                        localDirectory.set(rootProject.projectDir)
                        remoteUrl.set(URL(properties["WEBSITE"] as String))
                        remoteLineSuffix.set("#L")
                    }
                }
            }
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
            dependencies{
                "implementation"(gradleKotlinDsl())
                "implementation"(downloadTaskLibrary)
            }
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

repositories {
    gradlePluginPortal()
    mavenCentral()
    mavenLocal()
}

tasks.withType(DokkaMultiModuleTask::class) {
    moduleName.set("Artesano")
    outputDirectory.set(layout.projectDirectory.dir("docs"))
}

