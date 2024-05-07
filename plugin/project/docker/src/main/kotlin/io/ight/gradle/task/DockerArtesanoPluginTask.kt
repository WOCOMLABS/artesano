package io.ight.gradle.task

import io.ight.gradle.task.DockerArtesanoPluginTask.Type.Down
import io.ight.gradle.task.DockerArtesanoPluginTask.Type.Unknown
import io.ight.gradle.task.DockerArtesanoPluginTask.Type.Up
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File


/**
 * Docker compose
 *
 * this task is used to create and start services defined in docker-compose.yml
 * created by docker-compose stencil
 *
 * @constructor Create empty Docker compose
 */
open class DockerArtesanoPluginTask : Exec() {


    @Input
    var type : Type = Type.Unknown


    @Input
    var envMap : Map<String , String> = mapOf()

    init {
        group = ArtesanoTask.group
        description = """
            |Docker compose Task
            | • Creates and starts services defined in docker-compose.yml
            | • Removes the services defined in docker-compose.yml
        """.trimMargin()
    }


    @TaskAction
    override fun exec() {

        workingDir = File("${project.projectDir}")

        when (type) {
            Up -> {
                envMap.forEach { (key , value) -> environment(key , value) }
                commandLine(
                    "docker-compose" , "up" , "-d"
                )
            }

            Down -> commandLine(
                "docker-compose" , "down" , "-v"
            )

            Unknown -> logger.info("docker-compose command wont be executed because type is unknown. it should be Up or Down.")
        }
        super.exec()
    }


    enum class Type {

        Up ,
        Down ,
        Unknown;

    }

}
