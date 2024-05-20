package io.ight.stencil.properties.dockercompose

import io.ight.annotation.dsl.ArtesanoBuilderDsl
import io.ight.stencil.properties.dockercompose.DockerCompose.Type

/**
 * Docker compose
 *
 * @property type
 * @property containerName
 * @property containerVolume
 * @property containerPort
 * @constructor Create empty Docker compose
 */
data class DockerCompose(
    val type : Type ,
    val containerName : String ,
    val containerVolume : String ,
    val containerPort : String ,
) {


    /**
     * Type
     *
     * @constructor Create empty Type
     */
    enum class Type {


        /**
         * Surrealdb
         *
         * @constructor Create empty Surrealdb
         */
        Surrealdb ,


        /**
         * Nexus
         *
         * @constructor Create empty Nexus
         */
        Nexus ;

    }

    init {

        check(containerName.isNotBlank()) {
            "Docker compose container name is not defined"
        }

        check(containerVolume.isNotBlank()) {
            "Docker compose container volume is not defined"
        }

    }

}


/**
 * Docker compose builder
 *
 * @constructor Create empty Docker compose builder
 */
class DockerComposeBuilder {


    private lateinit var _type : Type
    private var _containerName : String = ""
    private var _containerVolume : String = ""
    private var _containerPort : String = ""


    /**
     * Type
     *
     * @param block
     * @receiver
     */
    @ArtesanoBuilderDsl
    fun type(block : () -> Type) {
        _type = block()
    }


    /**
     * Container name
     *
     * @param block
     * @receiver
     */
    @ArtesanoBuilderDsl
    fun containerName(block : () -> String) {
        _containerName = block()
    }


    /**
     * Container volume
     *
     * @param block
     * @receiver
     */
    @ArtesanoBuilderDsl
    fun containerVolume(block : () -> String) {
        _containerVolume = block()
    }


    /**
     * Container port
     *
     * @param block
     * @receiver
     */
    @ArtesanoBuilderDsl
    fun containerPort(block : () -> String) {
        _containerPort = block()
    }


    /**
     * Build
     *
     */
    fun build() = DockerCompose(
        _type ,
        _containerName ,
        _containerVolume ,
        _containerPort
    )

}
