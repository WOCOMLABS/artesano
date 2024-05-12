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

[libraries]
# https://plugins.gradle.org/plugin/org.gradle.kotlin.kotlin-dsl
gradle-kotlin-dsl-plugins = { module = "org.gradle.kotlin:gradle-kotlin-dsl-plugins" ,version  = "4.4.0"}
# https://plugins.gradle.org/plugin/org.jetbrains.kotlin.android
kotlin-gradle-plugin = { module = "org.jetbrains.kotlin:kotlin-gradle-plugin",version  = "1.9.23"}
# https://plugins.gradle.org/plugin/org.openapi.generator
openapi-generator-gradle-plugin = { module = "org.openapitools:openapi-generator-gradle-plugin",version  = "7.5.0"}
# https://plugins.gradle.org/plugin/de.undercouch.download
de-undercouch-gradle-download-task = { module = "de.undercouch:gradle-download-task",version  = "5.6.0"}
# https://mvnrepository.com/artifact/com.github.ajalt.clikt/clikt
ajalt-clikt = { module = "com.github.ajalt.clikt:clikt",version  = "4.4.0"}

artesano-catalog = { module = "io.ight:artesano-catalog", version.ref = "artesano" }
artesano-plugin-dsl = { module = "io.ight:artesano-plugin-dsl", version.ref = "artesano" }
artesano-settings-builder = { module = "io.ight:artesano-settings-builder", version.ref = "artesano" }

artesano-plugin-project-docker = { module = "io.ight:artesano-project-docker-plugin", version.ref = "artesano" }
artesano-plugin-project-open-api = { module = "io.ight:artesano-project-open-api-plugin", version.ref = "artesano" }
artesano-plugin-project-ijhttp = { module = "io.ight:artesano-project-ijhttp-plugin", version.ref = "artesano" }
artesano-plugin-root-project = { module = "io.ight:artesano-root-project", version.ref = "artesano" }

artesano-plugin-settings = { module = "io.ight:artesano-settings-plugin", version.ref = "artesano" }
artesano-plugin-settings-stencil = { module = "io.ight:artesano-settings-stencil", version.ref = "artesano" }


[plugins]
# https://plugins.gradle.org/plugin/org.jetbrains.kotlin.jvm
jvm = { id = "org.jetbrains.kotlin.jvm", version = "1.9.23" }
# https://plugins.gradle.org/plugin/org.gradle.kotlin.kotlin-dsl
kotlin-dsl = { id = "org.gradle.kotlin.kotlin-dsl", version = "4.4.0" }
# https://mvnrepository.com/artifact/com.vanniktech.maven.publish/com.vanniktech.maven.publish.gradle.plugin
vanniktech-maven-publish = { id = "com.vanniktech.maven.publish", version = "0.28.0" }
# https://plugins.gradle.org/plugin/com.gradle.plugin-publish
gradle-plugin-publish = { id = "com.gradle.plugin-publish", version = "1.2.1" }
# https://plugins.gradle.org/plugin/org.jetbrains.dokka
org-jetbrains-dokka = { id = "org.jetbrains.dokka", version = "1.9.20" }
# https://plugins.gradle.org/plugin/de.undercouch.download
de-undercouch-download = { id = "de.undercouch.download", version = "5.6.0" }

artesano-settings = { id = "io.ight.gradle.settings.settings-artesano-plugin", version.ref = "artesano" }
artesano-project-docker = { id = "io.ight.gradle.project.docker-artesano-plugin", version.ref = "artesano" }
artesano-project-open-api = { id = "io.ight.gradle.project.open-api-artesano-plugin", version.ref = "artesano" }
artesano-project-ijhttp = { id = "io.ight.gradle.project.ijhttp-artesano-plugin", version.ref = "artesano" }
artesano-project-root = { id = "io.ight.gradle.project.root-artesano-plugin", version.ref = "artesano" }


```
