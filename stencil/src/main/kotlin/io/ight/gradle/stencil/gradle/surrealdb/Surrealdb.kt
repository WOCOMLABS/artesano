package io.ight.gradle.stencil.gradle.surrealdb

import io.ight.gradle.stencil.Stencil.Gradle
import io.ight.gradle.stencil.properties.Property


/**
 * Surrealdb
 *
 */
fun Gradle.surrealdb() = """
plugins {
   id("io.ight.gradle.project.docker-artesano-plugin")
}

dockerArtesano {
    environment = mapOf<String , String>(
        "DB_USER" to System.getenv("${Property.Surrealdb.user}") ,
        "DB_PASSWORD" to System.getenv("${Property.Surrealdb.secret}") ,
    )

}

""".trimIndent()