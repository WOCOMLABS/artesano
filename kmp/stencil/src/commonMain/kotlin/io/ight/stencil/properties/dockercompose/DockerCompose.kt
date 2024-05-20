package io.ight.stencil.properties.dockercompose


import io.ight.stencil.Stencil.Properties
import io.ight.stencil.properties.Property.DockerCompose as DockerComposeProperty
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
fun Properties.dockerCompose(
    dockerCompose : DockerCompose ,
) = """ 
${DockerComposeProperty.containerName}=${dockerCompose.containerName}
${DockerComposeProperty.containerVolume}=${dockerCompose.containerVolume}
${DockerComposeProperty.containerPort}=${dockerCompose.containerPort}

""".trimIndent()
