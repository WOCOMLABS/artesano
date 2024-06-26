package io.ight.stencil.gradle.surrealdb

import io.ight.stencil.Stencil.Gradle
import io.ight.stencil.properties.Property


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
        "DB_USER" to properties["${Property.Surrealdb.user}"] as String ,
        "DB_PASSWORD" to properties["${Property.Surrealdb.secret}"] as String ,
    )

}

""".trimIndent()