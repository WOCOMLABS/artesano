package io.ight.gradle.settings

import io.ight.gradle.dsl.ArtesanoDsl
import io.ight.gradle.builder.DockerCompose.Type.Nexus
import io.ight.gradle.builder.DockerCompose.Type.Surrealdb
import io.ight.gradle.builder.DockerComposeBuilder
import io.ight.gradle.builder.OpenApiType
import io.ight.gradle.builder.OpenApiType.Client
import io.ight.gradle.builder.OpenApiType.Server
import io.ight.gradle.builder.OpenApiSurrealDbBuilder
import io.ight.gradle.stencil.Stencil
import io.ight.gradle.stencil.gradle.nexus.nexus
import io.ight.gradle.stencil.gradle.openapi.openapi
import io.ight.gradle.stencil.gradle.surrealdb.surrealdb
import io.ight.gradle.stencil.properties.dockercompose.`docker-compose`
import io.ight.gradle.stencil.properties.surrealdb.openApiSurrealdb
import io.ight.gradle.stencil.yml.nexus.`docker-compose nexus`
import io.ight.gradle.stencil.yml.surrealdb.`docker-compose surreal db`
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.api.plugins.PluginAware
import org.gradle.kotlin.dsl.getPlugin
import org.jetbrains.kotlin.konan.properties.loadProperties
import java.io.File
import java.util.*


/**
 * Settings artesano plugin
 *
 * @constructor Create empty Settings artesano plugin
 */
class SettingsArtesanoPlugin : Plugin<Settings> {


    override fun apply(target : Settings) = Unit


    /**
     * Artesano settings
     *
     * @param block
     * @receiver
     */
    @ArtesanoDsl
    fun Settings.dockerCompose(block : DockerComposeBuilder.() -> Unit) {
        val dockerCompose = DockerComposeBuilder().apply(block).build()

        val path = "/infrastructure/${dockerCompose.containerName}"

        val folder = File("${settingsDir}$path")

        if (folder.exists().not()) {
            folder.mkdirs()
        }

        verifyArtesanoProperties(
            "${settingsDir}$path/artesano.properties" ,
            Stencil.Properties.`docker-compose`(dockerCompose)
        )

        val dockerComposeFile = File("${settingsDir}$path/docker-compose.yml")
        if (dockerComposeFile.exists().not()) {
            dockerComposeFile.writeText(
                when (dockerCompose.type) {
                    Surrealdb -> Stencil.Yml.`docker-compose surreal db`(dockerCompose)
                    Nexus -> Stencil.Yml.`docker-compose nexus`(dockerCompose)
                }
            )
        }

        val projectName = path.replace("/" , ":")

        include(projectName)

        with(project(projectName)) {
            buildFileName = "build.gradle.kts"
            buildFile.writeText(
                when (dockerCompose.type) {
                    Surrealdb -> Stencil.Gradle.surrealdb()
                    Nexus -> Stencil.Gradle.nexus()
                }
            )
        }

    }
}


@ArtesanoDsl
fun Settings.openApiSurrealDb(block : OpenApiSurrealDbBuilder.() -> Unit) {
    val surrealDb = OpenApiSurrealDbBuilder().apply(block).build()
    val path = "/openapi/${
        when (surrealDb.type) {
            Client -> "client"
            Server -> "server"
            OpenApiType.Undefined -> throw IllegalArgumentException("Undefined surreal db type")
        }
    }"
    val folder = File("${settingsDir}/$path")

    if (folder.exists().not()) {
        folder.mkdirs()
    }

    verifyArtesanoProperties(
        "${settingsDir}$path/artesano.properties" ,
        Stencil.Properties.openApiSurrealdb(surrealDb)
    )

    val buildFile = File("${settingsDir}$path/build.gradle.kts")
    if (buildFile.exists().not()) {
        buildFile.writeText(
            Stencil.Gradle.openapi(
                type = surrealDb.type ,
                yamlFile = surrealDb.yamlFile ,
                version = surrealDb.version ,
                packageName = surrealDb.packageName
            )
        )
    }


    val apiFolder = File("${settingsDir}/openapi/${surrealDb.packageName}")
    if (apiFolder.exists().not()) {
        apiFolder.mkdirs()
    }
    val yamlFile = File("${settingsDir}/openapi/${surrealDb.packageName}/${surrealDb.yamlFile}")
    if (yamlFile.exists().not()) {
        yamlFile.writeText("")
    }

    include(path.replace("/" , ":"))

}


/**
 * Artesano
 *
 * @receiver
 * @return
 */
@ArtesanoDsl
fun Settings.artesano(block : SettingsArtesanoPlugin.() -> Unit) : Unit {
    plugins.getPlugin(SettingsArtesanoPlugin::class).apply(block)
}


internal fun PluginAware.verifyArtesanoProperties(
    path : String ,
    fileContent : String ,
) = run {

    val props = Properties().apply {
        load(fileContent.byteInputStream())
    }

    when {
        File(path).exists().not() -> File(path).writeText(fileContent)

        ! loadProperties(path).keys.containsAll(props.keys) -> {

            props.keys
                .map { it.toString() }
                .toSet()
                .subtract(
                    loadProperties(path).keys
                        .map { it.toString() }
                        .toSet()
                )
                .forEach {
                    File(path).appendText("\n${it}=${props.getProperty(it)}")
                }
        }

        else -> Unit
    }

}
