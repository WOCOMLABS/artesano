package io.ight.gradle.stencil.properties.dockercompose


import io.ight.gradle.builder.DockerCompose
import io.ight.gradle.stencil.Stencil.Properties
import io.ight.gradle.stencil.properties.Property.DockerCompose as DockerComposeProperty
/**
 * Docker compose
 * ```properties
 *
 * io.ight.docker.compose.name=surrealdb
 * io.ight.docker.compose.volume=surrealdbData
 * io.ight.docker.compose.port=8000
 *
 * ```
 * @param dockerCompose
 */
fun Properties.`docker-compose`(
    dockerCompose : DockerCompose ,
) = """ 
${DockerComposeProperty.containerName}=${dockerCompose.containerName}
${DockerComposeProperty.containerVolume}=${dockerCompose.containerVolume}
${DockerComposeProperty.containerPort}=${dockerCompose.containerPort}

""".trimIndent()
