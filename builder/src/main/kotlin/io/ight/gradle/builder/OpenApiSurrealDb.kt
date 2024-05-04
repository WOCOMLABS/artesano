package io.ight.gradle.builder

import io.ight.gradle.dsl.ArtesanoBuilderDsl


/**
 * Open api surreal db
 *
 * @property type
 * @property yamlFile
 * @property version
 * @property packageName
 * @property namespace
 * @property database
 * @property user
 * @property secret
 * @property port
 * @property testUser
 * @property testSecret
 * @constructor Create empty Open api surreal db
 */
data class OpenApiSurrealDb(
    val type : OpenApiType ,
    val yamlFile :String ,
    val version :String ,
    val packageName :String ,
    val namespace : String ,
    val database : String ,
    val user : String ,
    val secret : String ,
    val port : String ,
    val testUser : String ,
    val testSecret : String ,
) {


}


/**
 * Open api surreal db builder
 *
 * @constructor Create empty Open api surreal db builder
 */
class OpenApiSurrealDbBuilder {


    private var _openApiType : OpenApiType = OpenApiType.Undefined
    private var _yamlFile : String = "yamlFile.yaml"
    private var _version : String = "0.0.1"
    private var _packageName : String = "packageName"
    private var _namespace : String = "elNamespace"
    private var _database : String = "laBaseDeDatos"
    private var _user : String = "elUsuario"
    private var _secret : String = "elM1st3r10sO"
    private var _port : String = "8000"
    private var _testUser : String = "elTestUser"
    private var _testSecret : String = "elSecret0DelTest"


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
     * Namespace
     *
     * @param block
     * @receiver
     */
    @ArtesanoBuilderDsl
    fun namespace(block : () -> String) {
        _namespace = block()
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
     * Database
     *
     * @param block
     * @receiver
     */
    @ArtesanoBuilderDsl
    fun database(block : () -> String) {
        _database = block()
    }


    /**
     * User
     *
     * @param block
     * @receiver
     */
    @ArtesanoBuilderDsl
    fun user(block : () -> String) {
        _user = block()
    }


    /**
     * Secret
     *
     * @param block
     * @receiver
     */
    @ArtesanoBuilderDsl
    fun secret(block : () -> String) {
        _secret = block()
    }


    /**
     * Port
     *
     * @param block
     * @receiver
     */
    @ArtesanoBuilderDsl
    fun port(block : () -> String) {
        _port = block()
    }


    /**
     * Test user
     *
     * @param block
     * @receiver
     */
    @ArtesanoBuilderDsl
    fun testUser(block : () -> String) {
        _testUser = block()
    }


    /**
     * Test secret
     *
     * @param block
     * @receiver
     */
    @ArtesanoBuilderDsl
    fun testSecret(block : () -> String) {
        _testSecret = block()
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
        namespace = _namespace ,
        database = _database ,
        user = _user ,
        secret = _secret ,
        port = _port ,
        testUser = _testUser ,
        testSecret = _testSecret ,
    )

}
