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
 * io.ight.surrealdb.user=elUsuario
 * io.ight.surrealdb.secret=elM1st3r10sO
 * io.ight.surrealdb.port=8000
 * io.ight.surrealdb.test.user=elTestUser
 * io.ight.surrealdb.test.secret=elSecret0DelTest
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
${Surrealdb.testUser}=${openApiSurrealDb.testUser}
${Surrealdb.testSecret}=${openApiSurrealDb.testSecret}
""".trimIndent()
