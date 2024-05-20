package io.ight.stencil.gradle.openapi

import io.ight.stencil.properties.openapi.OpenApiType
import io.ight.stencil.properties.openapi.OpenApiType.Client
import io.ight.stencil.properties.openapi.OpenApiType.Server
import io.ight.stencil.properties.openapi.OpenApiType.Undefined
import io.ight.stencil.Stencil.Gradle

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