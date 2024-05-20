___ 
<p align="right">
  <a href="https://github.com/WOCOMLABS/artesano/actions/workflows/publish.yml">
    <img src="https://github.com/WOCOMLABS/artesano/actions/workflows/publish.yml/badge.svg" alt="Release CI">
  </a>
  <a href="https://github.com/WOCOMLABS/artesano/releases">
    <img src="https://img.shields.io/github/v/release/WOCOMLABS/artesano?logo=github&style=flat" alt="GitHub release (latest by date)">
  </a>
  <a href="https://opensource.org/licenses/MIT">
    <img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="GitHub license: MIT">
  </a>
</p>

___
# Version catalog 

all ight.io jvm projects should rely on this artesano if something is out of date please create an issue or a pull request.

## catalog shared

```toml
[versions]
artesano = "0.0.1"
#kotlin = "1.9.24"
kotlin = "1.9.22"
#kotlinDsl = "4.4.0"
kotlinDsl = "4.3.0"
kotlinx-coroutines = "1.8.1"
kotlinx-serialization = "1.6.3"
kotlinx-datetime = "0.6.0"
kermit = "2.0.3"
ktor = "2.3.11"
koin = "3.5.6"
#agp = "8.4.0"
agp = "8.2.2"
osDetector = "1.7.3"

[libraries]
# https://plugins.gradle.org/plugin/org.gradle.kotlin.kotlin-dsl
gradle-kotlin-dsl-plugins = { module = "org.gradle.kotlin:gradle-kotlin-dsl-plugins" ,version.ref  = "kotlinDsl"}
# https://plugins.gradle.org/plugin/org.jetbrains.kotlin.android
kotlin-gradle-plugin = { module = "org.jetbrains.kotlin:kotlin-gradle-plugin",version.ref  = "kotlin"}
# https://plugins.gradle.org/plugin/org.openapi.generator
openapi-generator-gradle-plugin = { module = "org.openapitools:openapi-generator-gradle-plugin",version  = "7.5.0"}
# https://plugins.gradle.org/plugin/de.undercouch.download
de-undercouch-gradle-download-task = { module = "de.undercouch:gradle-download-task",version  = "5.6.0"}
# https://mvnrepository.com/artifact/com.github.ajalt.clikt/clikt
ajalt-clikt = { module = "com.github.ajalt.clikt:clikt",version  = "4.4.0"}
# From terrakok's <3  generated project
# https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
kotlinx-coroutines-core = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-core", version.ref = "kotlinx-coroutines" }
# https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-android
kotlinx-coroutines-android = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-android", version.ref = "kotlinx-coroutines" }
# https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-swing
kotlinx-coroutines-swing = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-swing", version.ref = "kotlinx-coroutines" }
# https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-serialization-json
kotlinx-serialization-json = { module = "org.jetbrains.kotlinx:kotlinx-serialization-json", version.ref = "kotlinx-serialization" }
# https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-datetime
kotlinx-datetime = { module = "org.jetbrains.kotlinx:kotlinx-datetime", version.ref = "kotlinx-datetime" }
# https://mvnrepository.com/artifact/co.touchlab/kermit
kermit = { module = "co.touchlab:kermit", version.ref = "kermit" }
# https://mvnrepository.com/artifact/io.ktor/ktor-client-core
ktor-core = { module = "io.ktor:ktor-client-core", version.ref = "ktor" }
# https://mvnrepository.com/artifact/io.ktor/ktor-client-darwin
ktor-client-darwin = { module = "io.ktor:ktor-client-darwin", version.ref = "ktor" }
# https://mvnrepository.com/artifact/io.ktor/ktor-client-okhttp
ktor-client-okhttp = { module = "io.ktor:ktor-client-okhttp", version.ref = "ktor" }
# https://mvnrepository.com/artifact/io.ktor/ktor-client-js-js
ktor-client-js = { module = "io.ktor:ktor-client-js", version.ref = "ktor" }
# https://mvnrepository.com/artifact/io.ktor/ktor-client-curl-linuxx64
ktor-client-curl = { module = "io.ktor:ktor-client-curl", version.ref = "ktor" }
# https://mvnrepository.com/artifact/io.ktor/ktor-client-winhttp-mingwx64
ktor-client-winhttp = { module = "io.ktor:ktor-client-winhttp", version.ref = "ktor" }
# https://mvnrepository.com/artifact/io.insert-koin/koin-core
koin-core = { module = "io.insert-koin:koin-core", version.ref = "koin" }
# https://mvnrepository.com/artifact/io.insert-koin/koin-core
os-detector = { module = "gradle.plugin.com.google.gradle:osdetector-gradle-plugin", version.ref = "osDetector" }


artesano-catalog = { module = "io.ight:artesano-catalog", version.ref = "artesano" }
artesano-annotation = { module = "io.ight:artesano-annotation", version.ref = "artesano" }

artesano-plugin-project-docker = { module = "io.ight:artesano-project-docker-plugin", version.ref = "artesano" }
artesano-plugin-project-open-api = { module = "io.ight:artesano-project-open-api-plugin", version.ref = "artesano" }
artesano-plugin-project-ijhttp = { module = "io.ight:artesano-project-ijhttp-plugin", version.ref = "artesano" }
artesano-plugin-root-project = { module = "io.ight:artesano-root-project", version.ref = "artesano" }

artesano-plugin-settings = { module = "io.ight:artesano-settings-plugin", version.ref = "artesano" }
artesano-stencil = { module = "io.ight:artesano-stencil", version.ref = "artesano" }


[plugins]
# https://plugins.gradle.org/plugin/org.jetbrains.kotlin.multiplatform
multiplatform = { id = "org.jetbrains.kotlin.multiplatform", version.ref = "kotlin" }
# https://plugins.gradle.org/plugin/org.gradle.kotlin.kotlin-dsl
kotlin-dsl = { id = "org.gradle.kotlin.kotlin-dsl", version.ref = "kotlinDsl" }
# https://mvnrepository.com/artifact/com.vanniktech.maven.publish/com.vanniktech.maven.publish.gradle.plugin
vanniktech-maven-publish = { id = "com.vanniktech.maven.publish", version = "0.28.0" }
# https://plugins.gradle.org/plugin/com.gradle.plugin-publish
gradle-plugin-publish = { id = "com.gradle.plugin-publish", version = "1.2.1" }
# https://plugins.gradle.org/plugin/org.jetbrains.dokka
org-jetbrains-dokka = { id = "org.jetbrains.dokka", version = "1.9.20" }
# https://plugins.gradle.org/plugin/de.undercouch.download
de-undercouch-download = { id = "de.undercouch.download", version = "5.6.0" }
# https://plugins.gradle.org/plugin/io.github.terrakok.kmp-hierarchy
kmp-hierarchy = { id = "io.github.terrakok.kmp-hierarchy", version = "1.1" }
#
android-library = { id = "com.android.library", version.ref = "agp" }
android-application = { id = "com.android.application", version.ref = "agp" }
# https://plugins.gradle.org/plugin/org.jetbrains.kotlin.plugin.serialization
kotlinx-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlin" }
# https://plugins.gradle.org/plugin/com.google.osdetector
os-detector = { id = "com.google.osdetector", version.ref = "osDetector" }

artesano-settings = { id = "io.ight.gradle.settings.settings-artesano-plugin", version.ref = "artesano" }
artesano-project-docker = { id = "io.ight.gradle.project.docker-artesano-plugin", version.ref = "artesano" }
artesano-project-open-api = { id = "io.ight.gradle.project.open-api-artesano-plugin", version.ref = "artesano" }
artesano-project-ijhttp = { id = "io.ight.gradle.project.ijhttp-artesano-plugin", version.ref = "artesano" }
artesano-project-root = { id = "io.ight.gradle.project.root-artesano-plugin", version.ref = "artesano" }

```
