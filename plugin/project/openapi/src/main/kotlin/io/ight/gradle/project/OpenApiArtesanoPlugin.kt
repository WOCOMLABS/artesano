package io.ight.gradle.project


import io.ight.stencil.properties.openapi.OpenApiType
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.openapitools.generator.gradle.plugin.OpenApiGeneratorPlugin
import org.openapitools.generator.gradle.plugin.extensions.OpenApiGeneratorGenerateExtension


/**
 * Open api artesano plugin
 *
 * @constructor Create empty Open api artesano plugin
 */
class OpenApiArtesanoPlugin : Plugin<Project> {


    override fun apply(project : Project) = project.run {
        val openApiArtesano = extensions.create<OpenApiArtesanoPluginExtension>("openApiArtesano")

        //https://github.com/JetBrains/kotlin/blob/77dba281a34d708127fb40f55745a00b739586fd/libraries/tools/kotlin-gradle-plugin/build.gradle.kts#L176
        pluginManager.apply(KotlinPluginWrapper::class)
        //https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator-gradle-plugin/build.gradle#L92C36-L92C99
        pluginManager.apply(OpenApiGeneratorPlugin::class)
        //val openApiArtesano = extensions.create<OpenApiArtesanoPluginExtension>("openApiArtesano")

        afterEvaluate {

            extensions.configure(OpenApiGeneratorGenerateExtension::class) {
                when (openApiArtesano.openApiType) {
                    //https://github.com/OpenAPITools/openapi-generator/blob/master/docs/generators/kotlin.md
                    OpenApiType.Client -> {
                        generatorName.set("kotlin")
                        packageName.set("io.ight.${openApiArtesano.packageName}.client")
                        groupId.set("io.ight")
                        library.set("multiplatform")
                        additionalProperties.set(
                            mapOf(
                                "dateLibrary" to "kotlinx-datetime" ,
                                "serializationLibrary" to "kotlinx_serialization" ,
                            )
                        )
                        //https://github.com/surrealdb/openapi/blob/main/openapi.yml
                        inputSpec.set(openApiArtesano.yamlFile)
                        outputDir.set("$rootDir/${openApiArtesano.packageName}/client")
                        version.set(openApiArtesano.version)
                        skipValidateSpec.set(openApiArtesano.skipValidateSpec)
                    }
                    //https://github.com/OpenAPITools/openapi-generator/blob/master/docs/generators/kotlin-server.md
                    OpenApiType.Server -> {
                        generatorName.set("kotlin-server")
                        packageName.set("io.ight.${openApiArtesano.packageName}.server")
                        groupId.set("io.ight")
                        library.set("ktor")
                        //https://github.com/surrealdb/openapi/blob/main/openapi.yml
                        inputSpec.set(openApiArtesano.yamlFile)
                        outputDir.set("$rootDir/${openApiArtesano.packageName}/server")
                        version.set(openApiArtesano.version)
                        skipValidateSpec.set(openApiArtesano.skipValidateSpec)
                    }


                    OpenApiType.Undefined -> throw IllegalArgumentException("Open api config must be OpenApiSurrealDb.Type.Client or OpenApiSurrealDb.Type.Server")
                }
            }
        }
        Unit
    }
}


/**
 * Open api artesano plugin extension
 *
 * @constructor Create empty Open api artesano plugin extension
 */
open class OpenApiArtesanoPluginExtension {


    var openApiType : OpenApiType = OpenApiType.Undefined
    var yamlFile : String = "yamlName.yaml"
    var version : String = "0.0.1"
    var packageName : String = "packageName"
    var skipValidateSpec : Boolean = false

}