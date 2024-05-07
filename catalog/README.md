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
[libraries]
# https://plugins.gradle.org/plugin/org.gradle.kotlin.kotlin-dsl
gradle-kotlin-dsl-plugins = { module = "org.gradle.kotlin:gradle-kotlin-dsl-plugins" ,version  = "4.4.0"}
# https://plugins.gradle.org/plugin/org.jetbrains.kotlin.android
kotlin-gradle-plugin = { module = "org.jetbrains.kotlin:kotlin-gradle-plugin",version  = "1.9.23"}
# https://plugins.gradle.org/plugin/org.openapi.generator
openapi-generator-gradle-plugin = { module = "org.openapitools:openapi-generator-gradle-plugin",version  = "7.5.0"}



[plugins]
# https://plugins.gradle.org/plugin/org.jetbrains.kotlin.jvm
jvm = { id = "org.jetbrains.kotlin.jvm", version = "1.9.23" }
# https://plugins.gradle.org/plugin/org.gradle.kotlin.kotlin-dsl
kotlin-dsl = { id = "org.gradle.kotlin.kotlin-dsl", version = "4.4.0" }
# https://mvnrepository.com/artifact/com.vanniktech.maven.publish/com.vanniktech.maven.publish.gradle.plugin
vanniktech-maven-publish = { id = "com.vanniktech.maven.publish", version = "0.28.0" }
# https://mvnrepository.com/artifact/com.vanniktech.maven.publish.base/com.vanniktech.maven.publish.base.gradle.plugin
vanniktech-maven-publish-base = { id = "com.vanniktech.maven.publish.base", version = "0.28.0" }
gradle-plugin-publish = { id = "com.gradle.plugin-publish", version = "1.2.1" }

```
