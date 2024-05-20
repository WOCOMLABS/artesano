package io.ight.stencil.yml.nexus

import io.ight.stencil.properties.dockercompose.DockerCompose
import io.ight.stencil.Stencil


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
fun Stencil.Yml.dockerComposeNexus(
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
