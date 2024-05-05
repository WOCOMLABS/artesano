___ 
<p align="right">
  <a href="https://github.com/WOCOMLABS/artesano/actions/workflows/gradle-publish.yml">
    <img src="https://github.com/WOCOMLABS/artesano/actions/workflows/gradle-publish.yml/badge.svg?branch=main" alt="Release CI">
  </a>
  <a href="https://github.com/WOCOMLABS/artesano/releases">
    <img src="https://img.shields.io/github/v/release/WOCOMLABS/artesano?logo=github&style=flat" alt="GitHub release (latest by date)">
  </a>
  <a href="https://opensource.org/licenses/MIT">
    <img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="GitHub license: MIT">
  </a>
</p>

# Artesano 
___
## Introduction
This project includes a Gradle plugin named `settings-artesano-plugin` which facilitates the configuration and generation of Docker Compose files for services like SurrealDB and Nexus. It utilizes a declarative DSL for specifying service details such as container names, ports, and volumes.

## Installation
To use the `settings-artesano-plugin`, add the following to your `settings.gradle.kts`:

```kotlin
pluginManagement {
    repositories {
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
    id("io.ight.gradle.settings.settings-artesano-plugin") version "0.0.1-RC4"
}
```

## Usage

Configure the plugin within your `settings.gradle.kts` file:

```kotlin
rootProject.name = "sample"

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
```

### Files Generated
Upon applying the configuration, the plugin will generate the following files under the `infrastructure` directory:

- **For SurrealDB**:
  - `surreal-db-artesano/docker-compose.yml`: Docker Compose file for setting up the SurrealDB service.

- **For Nexus**:
  - `nexus-artesano/docker-compose.yml`: Docker Compose file for setting up the Nexus service.

## Contributing
Contribute to the plugin by cloning the repository and submitting pull requests. Please ensure your commits follow the existing code style and all tests are passing.
