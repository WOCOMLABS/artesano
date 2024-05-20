@file:OptIn(ExperimentalWasmDsl::class)

import com.vanniktech.maven.publish.MavenPublishBaseExtension
import org.jetbrains.dokka.DokkaConfiguration
import org.jetbrains.dokka.gradle.DokkaMultiModuleTask
import org.jetbrains.dokka.gradle.DokkaTaskPartial
import org.jetbrains.kotlin.gradle.plugin.KotlinTarget
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import java.net.URL

plugins {
    alias(libs.plugins.org.jetbrains.dokka) apply true
    alias(libs.plugins.kotlin.dsl) apply false
    alias(libs.plugins.multiplatform) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
    alias(libs.plugins.kmp.hierarchy) apply false
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.vanniktech.maven.publish) apply false
    alias(libs.plugins.gradle.plugin.publish) apply false
    alias(libs.plugins.os.detector) apply true
}

val vanniktechPluginId : String = libs.plugins.vanniktech.maven.publish.get().pluginId
val dokkaPluginId : String = libs.plugins.org.jetbrains.dokka.get().pluginId
val multiplatformPluginId : String = libs.plugins.multiplatform.get().pluginId
val androidLibraryPluginId : String = libs.plugins.android.library.get().pluginId
val kmpHierarchyPluginId : String = libs.plugins.kmp.hierarchy.get().pluginId
val kotlinDslPluginId : String = libs.plugins.kotlin.dsl.get().pluginId
val gradlePluginPublishPluginId : String = libs.plugins.gradle.plugin.publish.get().pluginId
val osDetectorPluginId : String = libs.plugins.os.detector.get().pluginId
val downloadTaskLibrary : String = libs.de.undercouch.gradle.download.task.get().toString()

enum class TypedProject(
    val projectName : String ,
    val packageName : String ,
    val vanniktech : Boolean = true ,
    val dokka : Boolean = true ,
    val multiplatform : Boolean = false ,
    val versionCatalog : Boolean = false ,
    val kotlinDsl : Boolean = false ,
    val gradlePublish : Boolean = false ,
) {


    Catalog(
        projectName = ":catalog" ,
        packageName = "catalog" ,
        versionCatalog = true
    ) ,


    // MULTIPLATFORM
    Stencil(
        projectName = ":kmp:stencil" ,
        packageName = "stencil" ,
        multiplatform = true
    ) ,
    Annoatation(
        projectName = ":kmp:annotation" ,
        packageName = "annotation" ,
        multiplatform = true
    ) ,


    // SETTINGS GRADLE PLUGIN
    Settings(
        projectName = ":plugin:settings" ,
        packageName = "settings" ,
        kotlinDsl = true ,
        gradlePublish = true
    ) ,


    // PROJECT GRADLE PLUGIN
    Docker(
        projectName = ":plugin:project:docker" ,
        packageName = "docker" ,
        kotlinDsl = true ,
        gradlePublish = true
    ) ,
    Openapi(
        projectName = ":plugin:project:openapi" ,
        packageName = "openapi" ,
        kotlinDsl = true ,
        gradlePublish = true
    ) ,
    Root(
        projectName = ":plugin:project:root" ,
        packageName = "root" ,
        kotlinDsl = true ,
        gradlePublish = true
    ) ,
    Ijhttp(
        projectName = ":plugin:project:ijhttp" ,
        packageName = "ijhttp" ,
        kotlinDsl = true ,
        gradlePublish = true
    ) ,
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
            google()
            mavenLocal()
        }

        apply(plugin = osDetectorPluginId)

        if (dokka) {
            apply(plugin = dokkaPluginId)

            tasks.withType<DokkaTaskPartial>().configureEach {

                outputDirectory.set(
                    rootProject.layout.projectDirectory.dir(
                        "docs${
                            projectName.replace(
                                ":" ,
                                "/"
                            )
                        }"
                    )
                )
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

        if (multiplatform) {
            apply(plugin = multiplatformPluginId)
            apply(plugin = kmpHierarchyPluginId)
            apply(plugin = androidLibraryPluginId)

            extensions.configureForMultiplatform(packageName)
            extensions.configureForAndroid(packageName)
        }

        if (versionCatalog) {
            apply<VersionCatalogPlugin>()
        }

        if (kotlinDsl) {
            apply(plugin = kotlinDslPluginId)
            dependencies {
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

fun ExtensionContainer.configureForAndroid(name : String) {
    val COMPILE_SDK : String by project
    val MINIMUM_SDK : String by project
    val GROUP : String by project
    configure(com.android.build.gradle.LibraryExtension::class) {
        namespace = "$GROUP.$name"
        compileSdk = COMPILE_SDK.toInt()

        defaultConfig {
            minSdk = MINIMUM_SDK.toInt()
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }
}

fun ExtensionContainer.configureForMultiplatform(name : String) {

    configure(org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension::class) {
        androidTarget {
            publishLibraryVariants("debug" , "release")
            compilations.all {
                kotlinOptions {
                    jvmTarget = "17"
                }
            }
        }

        jvm()

        js {
            browser {
                webpackTask {
                    mainOutputFileName = "${name}.js"
                }
            }
            nodejs()
            binaries.executable()
        }

        wasmJs {
            browser()
            nodejs()
            binaries.executable()
        }

        wasmWasi {
            nodejs()
            binaries.executable()
        }

        val os = osdetector.os

        when {
            os.startsWith("osx") -> {

                listOf(
                    macosX64() ,
                    macosArm64() ,
//                    tvosX64() ,
//                    tvosArm64() ,
//                    tvosSimulatorArm64() ,
//                    watchosArm64() ,
//                    watchosX64() ,
//                    watchosSimulatorArm64() ,
                    iosX64() ,
                    iosArm64() ,
                    iosSimulatorArm64() ,

                    ).forEach { nativeTarget ->

                    nativeTarget.binaries.framework {
                        baseName = name
                        isStatic = true
                    }
                }
            }

            os.startsWith("windows") -> {

            }

            else -> {

            }


        }


        linuxX64 {
            binaries.staticLib {
                baseName = name
            }
        }

//        linuxArm64 {
//            binaries.staticLib {
//                baseName = name
//            }
//        }

        mingwX64 {
            binaries.staticLib {
                baseName = name
            }
        }

        jvmToolchain(17)
    }

    //https://kotlinlang.org/docs/native-objc-interop.html#export-of-kdoc-comments-to-generated-objective-c-headers
    container(KotlinTarget::class.java).withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        compilations["main"].compilerOptions.options.freeCompilerArgs.add("-Xexport-kdoc")
    }
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
    mavenLocal()
}

tasks.withType(DokkaMultiModuleTask::class) {
    moduleName.set("Artesano")
    outputDirectory.set(layout.buildDirectory.dir("docs"))
}

