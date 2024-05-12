package io.ight.gradle.stencil.properties.surrealdb

import io.ight.gradle.builder.OpenApiSurrealDb
import io.ight.gradle.stencil.Stencil.Properties
import io.ight.gradle.stencil.properties.Property.Surrealdb

/**
 * Open api surrealdb
 * ```properties
 *
 * io.ight.surrealdb.namespace=elNamespace
 * io.ight.surrealdb.database=laBaseDeDatos
 * io.ight.surrealdb.root.user=**************
 * io.ight.surrealdb.root.secret=**************
 * io.ight.surrealdb.port=8000
 *
 * ```
 * @param openApiSurrealDb
 */
fun Properties.openApiSurrealdb(
    openApiSurrealDb : OpenApiSurrealDb ,
) = """ 

${Surrealdb.namespace}=${openApiSurrealDb.namespace}
${Surrealdb.database}=${openApiSurrealDb.database}
${Surrealdb.user}=${openApiSurrealDb.user}
${Surrealdb.secret}=${openApiSurrealDb.secret}
${Surrealdb.port}=${openApiSurrealDb.port}
""".trimIndent()
