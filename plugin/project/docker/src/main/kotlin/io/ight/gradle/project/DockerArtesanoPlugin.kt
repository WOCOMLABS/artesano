package io.ight.gradle.project

import io.ight.gradle.task.DockerArtesanoPluginTask
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
            val dockerArtesanoPluginTaskUp = tasks.create("dockerComposeUp" , DockerArtesanoPluginTask::class) {
                envMap = dockerArtesano.environment
                type = DockerArtesanoPluginTask.Type.Up
            }

            val dockerArtesanoPluginTaskDown = tasks.create("dockerComposeDown" , DockerArtesanoPluginTask::class) {
                type = DockerArtesanoPluginTask.Type.Down
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

