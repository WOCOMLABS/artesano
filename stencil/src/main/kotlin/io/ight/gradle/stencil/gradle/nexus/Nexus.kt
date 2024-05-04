package io.ight.gradle.stencil.gradle.nexus

import io.ight.gradle.stencil.Stencil.Gradle

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