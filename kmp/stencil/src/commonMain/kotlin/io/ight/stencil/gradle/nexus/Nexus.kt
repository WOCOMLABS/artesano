package io.ight.stencil.gradle.nexus

import io.ight.stencil.Stencil.Gradle

/**
 * Nexus
 *
 */
fun Gradle.nexus() = """
plugins {
   id("io.ight.gradle.project.docker-artesano-plugin")
}

dockerArtesano {
    environment = mapOf()
}

""".trimIndent()