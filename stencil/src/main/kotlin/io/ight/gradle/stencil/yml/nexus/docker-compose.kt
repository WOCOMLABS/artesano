package io.ight.gradle.stencil.yml.nexus

import io.ight.gradle.builder.DockerCompose
import io.ight.gradle.stencil.Stencil


/**
 * Docker-compose nexus
 * * Generates a docker-compose.yml file from a template
 *  * like the following:
 *
 *
 *  ```yml
 * version: "3"
 *
 * services:
 *   ${dockerCompose.containerName}:
 *     container_name: ${dockerCompose.containerName}
 *     image: sonatype/nexus3
 *     restart: always
 *     volumes:
 *       - "${dockerCompose.containerVolume}:/sonatype-work"
 *     ports:
 *       - "${dockerCompose.containerPort}:8081"
 *
 *
 * volumes:
 *   ${dockerCompose.containerVolume}: {}
 *
 * ```
 *
 * @param dockerCompose
 * @return
 */
@Suppress("FunctionName")
fun Stencil.Yml.`docker-compose nexus`(
    dockerCompose : DockerCompose ,
) : String = """
version: "3"

services:
  ${dockerCompose.containerName}:
    container_name: ${dockerCompose.containerName}
    image: sonatype/nexus3
    restart: always
    volumes:
      - "${dockerCompose.containerVolume}:/sonatype-work"
    ports:
      - "${dockerCompose.containerPort}:8081"


volumes:
  ${dockerCompose.containerVolume}: {}

""".trimIndent()
