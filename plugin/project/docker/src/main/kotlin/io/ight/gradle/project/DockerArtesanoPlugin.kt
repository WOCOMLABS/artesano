package io.ight.gradle.project

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.gradle.kotlin.dsl.create

/**
 * Docker artesano plugin
 *
 * responsible for creating dockerComposeUp and dockerComposeDown tasks
 *
 * @constructor Create empty Docker artesano plugin
 */
class DockerArtesanoPlugin : Plugin<Project> {


    override fun apply(project : Project) = project.run {
        val dockerArtesano = extensions.create<DockerArtesanoPluginExtension>("dockerArtesano")

        afterEvaluate {
            val dockerArtesanoPluginTaskUp = tasks.create("dockerComposeUp" , Exec::class) {
                group = "artesano"
                description = """
                |Docker compose Task
                | • Creates and starts services defined in docker-compose.yml
        """.trimMargin()

                dockerArtesano.environment.forEach { (key , value) -> environment(key , value) }

                commandLine("docker-compose" , "up" , "-d")

            }

            val dockerArtesanoPluginTaskDown = tasks.create("dockerComposeDown" , Exec::class) {

                group = "artesano"
                description = """
                |Docker compose Task
                | • Removes the services defined in docker-compose.yml
        """.trimMargin()

                commandLine("docker-compose" , "down" , "-v")

            }
        }
        Unit
    }
}


/**
 * Docker artesano plugin extension
 *
 * @constructor Create empty Docker artesano plugin extension
 */
open class DockerArtesanoPluginExtension {


    /**
     * Environment
     */
    var environment : Map<String , String> = mapOf()

}

