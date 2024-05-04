package io.ight.gradle.project

import io.ight.gradle.task.dockercompose.DockerCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
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
            val dockerComposeUp = tasks.create("dockerComposeUp" , DockerCompose::class) {
                envMap = dockerArtesano.environment
                type = DockerCompose.Type.Up
            }

            val dockerComposeDown = tasks.create("dockerComposeDown" , DockerCompose::class) {
                type = DockerCompose.Type.Down
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

