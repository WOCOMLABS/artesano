package io.ight.stencil.yml.surrealdb

import io.ight.stencil.properties.dockercompose.DockerCompose
import io.ight.stencil.Stencil

/**
 * Docker-compose surreal db
 * Generates a docker-compose.yml file from a template
 * like the following:
 *
 * ```yml
 * version: "3"
 *
 * services:
 *   ${dockerCompose.containerName}:
 *     container_name: ${dockerCompose.containerName}
 *     image: surrealdb/surrealdb:latest
 *     user: root:root
 *     restart: always
 *     command:
 *       - start
 *       - --log
 *       - trace
 *       - --user
 *       - ${'$'}{DB_USER}
 *       - --pass
 *       - ${'$'}{DB_PASSWORD}
 *       - file:///var/lib/surrealdb/database.db
 *     ports:
 *       - "${dockerCompose.containerPort}:8000"
 *     volumes:
 *       - ${dockerCompose.containerVolume}:/var/lib/surrealdb
 *
 * volumes:
 *   ${dockerCompose.containerVolume}:
 *
 * ```
 *
 * @param dockerCompose
 * @return
 */
@Suppress("FunctionName")
fun Stencil.Yml.dockerComposeSurrealdb(
    dockerCompose : DockerCompose ,
) : String = """
version: "3"

services:
  ${dockerCompose.containerName}:
    container_name: ${dockerCompose.containerName}
    image: surrealdb/surrealdb:latest
    user: root:root
    restart: always
    command:
      - start
      - --log
      - trace
      - --user
      - ${'$'}{DB_USER}
      - --pass
      - ${'$'}{DB_PASSWORD}
      - file:///var/lib/surrealdb/database.db
    ports:
      - "${dockerCompose.containerPort}:8000"
    volumes:
      - ${dockerCompose.containerVolume}:/var/lib/surrealdb

volumes:
  ${dockerCompose.containerVolume}:
""".trimIndent()
