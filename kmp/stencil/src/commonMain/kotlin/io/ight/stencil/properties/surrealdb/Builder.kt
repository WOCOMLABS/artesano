package io.ight.stencil.properties.surrealdb

import io.ight.annotation.dsl.ArtesanoBuilderDsl
import io.ight.stencil.properties.openapi.OpenApiType
import io.ight.stencil.properties.openapi.OpenApiType.Undefined


/**
 * Open api surreal db
 *
 * @property type
 * @property yamlFile
 * @property version
 * @property packageName
 * @constructor Create empty Open api surreal db
 */
data class OpenApiSurrealDb(
    val type : OpenApiType ,
    val yamlFile : String ,
    val version : String ,
    val packageName : String ,
    val skipValidateSpec : Boolean ,
)


/**
 * Open api surreal db builder
 *
 * @constructor Create empty Open api surreal db builder
 */
class OpenApiSurrealDbBuilder {


    private var _openApiType : OpenApiType = Undefined
    private var _yamlFile : String = "yamlFile.yaml"
    private var _version : String = "0.0.1"
    private var _packageName : String = "packageName"
    private var _skipValidateSpec : Boolean = false


    /**
     * Type
     *
     * @param block
     * @receiver
     */
    @ArtesanoBuilderDsl
    fun type(block : () -> OpenApiType) {
        _openApiType = block()
    }


    /**
     * Path to yaml
     *
     * @param block
     * @receiver
     */
    @ArtesanoBuilderDsl
    fun yamlFile(block : () -> String) {
        _yamlFile = block()
    }


    /**
     * Version
     *
     * @param block
     * @receiver
     */
    @ArtesanoBuilderDsl
    fun version(block : () -> String) {
        _version = block()
    }


    /**
     * Package name
     *
     * @param block
     * @receiver
     */
    @ArtesanoBuilderDsl
    fun packageName(block : () -> String) {
        _packageName = block()
    }


    /**
     * Skip validate spec
     *
     * @param block
     * @receiver
     */
    @ArtesanoBuilderDsl
    fun skipValidateSpec(block : () -> Boolean) {
        _skipValidateSpec = block()
    }


    /**
     * Build
     *
     */
    fun build() = OpenApiSurrealDb(
        type = _openApiType ,
        yamlFile = _yamlFile ,
        version = _version ,
        packageName = _packageName ,
        skipValidateSpec = _skipValidateSpec ,
    )

}
