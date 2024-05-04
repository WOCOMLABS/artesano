package io.ight.gradle.stencil.gradle.openapi

import io.ight.gradle.builder.OpenApiType
import io.ight.gradle.builder.OpenApiType.Client
import io.ight.gradle.builder.OpenApiType.Server
import io.ight.gradle.builder.OpenApiType.Undefined
import io.ight.gradle.stencil.Stencil.Gradle

/**
 * Openapi
 *
 * @param type
 * @param yamlFile
 * @param version
 * @param packageName
 */
fun Gradle.openapi(
    type : OpenApiType ,
    yamlFile : String ,
    version : String ,
    packageName : String ,
) = """
import io.ight.gradle.builder.OpenApiType

plugins {
    id("io.ight.gradle.project.open-api-artesano-plugin")
}

openApiArtesano {
    openApiType = OpenApiType.${
        when(type){
            Client -> "Client"
            Server -> "Server"
            Undefined -> "Undefined"
        }
    }
    yamlFile = "${'$'}rootDir/openApi/$packageName/$yamlFile"
    version  = "$version"
    packageName = "$packageName"
}

""".trimIndent()