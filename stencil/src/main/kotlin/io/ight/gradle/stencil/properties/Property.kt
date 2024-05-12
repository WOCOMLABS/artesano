package io.ight.gradle.stencil.properties

/**
 * This object holds the properties related to different components of the application.
 */
object Property {

    /**
     * This object holds the properties related to Docker Compose.
     */
    object DockerCompose {

        /**
         * The name of the Docker container.
         */
        const val containerName : String = "io.ight.docker.compose.name"

        /**
         * The volume of the Docker container.
         */
        const val containerVolume : String = "io.ight.docker.compose.volume"

        /**
         * The port of the Docker container.
         */
        const val containerPort : String = "io.ight.docker.compose.port"
    }

    /**
     * This object holds the properties related to Surrealdb.
     */
    object Surrealdb {

        /**
         * The user of the Surrealdb.
         */
        const val user : String = "io.ight.surrealdb.root.user"

        /**
         * The secret of the Surrealdb.
         */
        const val secret : String = "io.ight.surrealdb.root.secret"

        /**
         * The port of the Surrealdb.
         */
        const val port : String = "io.ight.surrealdb.port"

        /**
         * The namespace of the Surrealdb.
         */
        const val namespace : String = "io.ight.surrealdb.namespace"

        /**
         * The database of the Surrealdb.
         */
        const val database : String = "io.ight.surrealdb.database"

        /**
         * The test user of the Surrealdb.
         */
        const val testUser : String = "io.ight.surrealdb.test.user"

        /**
         * The test secret of the Surrealdb.
         */
        const val testSecret : String = "io.ight.surrealdb.test.secret"
    }

    /**
     * This object holds the properties related to OpenApi.
     */
    object OpenApi {

        /**
         * The file of the OpenApi.
         */
        const val file : String = "io.ight.surrealdb.openapi.yaml"

        /**
         * The generator of the OpenApi.
         */
        const val generator : String = "io.ight.surrealdb.openapi.generator"
    }
}