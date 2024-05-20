package io.ight.gradle.project

import de.undercouch.gradle.tasks.download.Download
import io.ight.annotation.dsl.ArtesanoDsl
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import io.ight.gradle.task.IjhttpTask
import io.ight.gradle.task.IjhttpTask.Environment
import io.ight.gradle.task.IjhttpTask.LogLevel
import org.gradle.api.tasks.Copy
import org.gradle.kotlin.dsl.apply
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

/**
 * Artesano plugin ijhttp
 *
 * creates a downloadIjhttp task that downloads the latest ijhttp
 * creates a downloadIjhttpAndUnzip task that unzips the downloaded ijhttp
 * creates a ijhttp task that runs the downloaded ijhttp
 *
 * @constructor Create empty Artesano plugin ijhttp
 */
open class ArtesanoPluginIjhttp : Plugin<Project> {


    override fun apply(target : Project) = target.run {

        //https://github.com/JetBrains/kotlin/blob/77dba281a34d708127fb40f55745a00b739586fd/libraries/tools/kotlin-gradle-plugin/build.gradle.kts#L176
        pluginManager.apply(KotlinPluginWrapper::class)

        val ijhttpArtesano = extensions.create<ArtesanoPluginIjhttpExtension>("ijhttpArtesano")


        afterEvaluate {
            val dirWithIjhttpTests = project.layout.projectDirectory.dir(".ijhttp").asFile
            with(dirWithIjhttpTests) {
                if (exists().not()) mkdirs()
            }

            val downloadIjhttpTask = tasks.create("downloadIjhttp" , Download::class) {
                group = "artesano"
                src("https://jb.gg/ijhttp/latest")
                dest(rootProject.layout.buildDirectory.file("ijhttp/download/ijhttp.zip"))
                onlyIfModified(true)
                overwrite(true)

            }

            val downloadIjhttpAndUnzip = tasks.create("downloadIjhttpAndUnzip" , Copy::class) {
                group = "artesano"
                dependsOn(downloadIjhttpTask)
                from(zipTree(downloadIjhttpTask.dest))
                into(rootProject.layout.buildDirectory.dir("ijhttp"))
            }


            tasks.create("ijhttp" , IjhttpTask::class) {
                group = "artesano"
                dependsOn(downloadIjhttpAndUnzip)
                connectTimeout = ijhttpArtesano.connectTimeout
                connectTimeout = ijhttpArtesano.connectTimeout
                environment = ijhttpArtesano.environment
                insecure = ijhttpArtesano.insecure
                logLevel = ijhttpArtesano.logLevel
                privateEnvFile = ijhttpArtesano.privateEnvFile
                privateEnvVariables = ijhttpArtesano.privateEnvVariables
                report = ijhttpArtesano.report
                socketTimeout = ijhttpArtesano.socketTimeout
                publicEnvFile = ijhttpArtesano.publicEnvFile
                envVariables = ijhttpArtesano.envVariables
                proxyUrl = ijhttpArtesano.proxyUrl
                if(ijhttpArtesano.files.isNotEmpty()) {
                    files = ijhttpArtesano.files
                }

            }

            tasks.named("assemble").configure {
                dependsOn(downloadIjhttpAndUnzip)
            }
        }

        Unit
    }
}


/**
 * Artesano plugin ijhttp extension
 *
 * @constructor Create empty Artesano plugin ijhttp extension
 */
@ArtesanoDsl
open class ArtesanoPluginIjhttpExtension {

    @ArtesanoDsl
    var dockerMode : Boolean = false

    @ArtesanoDsl
    var insecure : Boolean = false

    @ArtesanoDsl
    var report : String? = null

    @ArtesanoDsl
    var connectTimeout : Long = 3000

    @ArtesanoDsl
    var environment : Environment = Environment.dev

    @ArtesanoDsl
    var logLevel : LogLevel = LogLevel.BASIC

    @ArtesanoDsl
    var privateEnvFile : String = "http-client.private.env.json"

    @ArtesanoDsl
    var proxyUrl : String? = null

    @ArtesanoDsl
    var socketTimeout : Long = 10_000

    @ArtesanoDsl
    var publicEnvFile : String = "http-client.env.json"

    @ArtesanoDsl
    var privateEnvVariables : Map<String , String> = emptyMap()

    @ArtesanoDsl
    var envVariables : Map<String , String> = emptyMap()

    @ArtesanoDsl
    var files : Set<String> = emptySet()

    init {
        check(files.isEmpty() || files.all { each -> each.endsWith(".rest") || each.endsWith(".http") }) {
            "all files must have *.rest or *.http"
        }
    }
}
