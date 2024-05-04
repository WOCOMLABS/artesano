package io.ight.gradle.stencil.yml.surrealdb

import io.ight.gradle.builder.DockerCompose
import io.ight.gradle.stencil.Stencil

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
fun Stencil.Yml.`docker-compose surreal db`(
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
