## Adding surrealdb to your dev environment


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
        type { DockerCompose.Type.Surrealdb }
        containerName { "surrealDb" }
        containerVolume { "surrealDbData" }
        containerPort { "8000" }
    }

}


```
</details>

---

<details>
<summary>artesano.properties</summary>

```properties
io.ight.docker.compose.name=surrealDb
io.ight.docker.compose.volume=surrealDbData
io.ight.docker.compose.port=8000


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
    environment.putAll(
        mutableMapOf<String , String>(
            "DB_USER" to properties["io.ight.surrealdb.root.user"] as String ,
            "DB_PASSWORD" to properties["io.ight.surrealdb.root.secret"] as String ,
        )
    )

}
```
</details>

---


<details>
<summary>docker-compose.yml</summary>

```yml
version: "3"

services:
  surrealDb:
    container_name: surrealDb
    image: surrealdb/surrealdb:latest
    user: root:root
    restart: always
    command:
      - start
      - --log
      - trace
      - --user
      - ${DB_USER}
      - --pass
      - ${DB_PASSWORD}
      - file:///var/lib/surrealdb/database.db
    ports:
      - "8000:8000"
    volumes:
      - surrealDbData:/var/lib/surrealdb

volumes:
  surrealDbData:

```
</details>

---
