## Adding nexus to your dev environment


---


Apply the settings plugin to your settings.gradle.kts file.


> [!CAUTION]
> This plugin is extremely intrusive and will modify your project scaffold.

<details>
<summary>settings.gradle.kts</summary>

```kotlin
import io.ight.gradle.settings.artesano
import io.ight.gradle.settings.builder.DockerCompose

pluginManagement {

    repositories {
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
    }

}

plugins {
    id("io.ight.artesano.settings") version "<<latest>>"
}

artesano {

    dockerCompose {
        type { DockerCompose.Type.Nexus }
        containerName { "nexus" }
        containerVolume { "nexusData" }
        containerPort { "8001" }
    }

}


```
</details>

---

<details>
<summary>artesano.properties</summary>

```properties
io.ight.docker.compose.name=nexus
io.ight.docker.compose.volume=nexusData
io.ight.docker.compose.port=8001

```
</details>

---


<details>
<summary>build.gradle.kts</summary>

```kotlin
plugins {
   id("io.ight.gradle.project.docker-artesano-plugin")
}
 
dockerArtesano {
   environment.putAll(mapOf())

}
```
</details>

---


<details>
<summary>docker-compose.yml</summary>

```yml
version: "3"

services:
  nexus:
    container_name: nexus
    image: sonatype/nexus3
    restart: always
    volumes:
      - "nexusData:/sonatype-work"
    ports:
      - "8001:8081"


volumes:
  nexusData: {}

```
</details>

---

