package io.ight.gradle.stencil.properties.openapi

import io.ight.gradle.stencil.Stencil.Properties
import io.ight.gradle.stencil.properties.Property.OpenApi

/**
 * Open api
 * ```properties
 *
 * io.ight.surrealdb.openapi.yaml=surrealdb.yaml
 * io.ight.surrealdb.openapi.generator=client
 *
 * ```
 * @param openApiProperties
 */
fun Properties.openApi(
    openApiProperties : OpenApiProperties = OpenApiProperties() ,
) = """ 
${OpenApi.file}=${openApiProperties.file}
${OpenApi.generator}=${openApiProperties.generator}
""".trimIndent()

/**
 * Open api properties
 *
 * @property file
 * @property generator
 */
data class OpenApiProperties(
    val file : String = "surrealdb.yaml" ,
    val generator : String = "client" ,
)
