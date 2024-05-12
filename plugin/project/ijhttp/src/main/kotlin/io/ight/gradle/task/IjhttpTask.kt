package io.ight.gradle.task

import io.ight.gradle.dsl.ArtesanoDsl


import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity

/**
 * Ijhttp test
 *
 * This task wraps the IntelliJ IDEA HTTP Client
 *
 * and was tested with
 *
 * ```bash
 * IntelliJ HTTP Client 2024.1
 * Build: 241.14494.240
 * Runtime version: 22.0.1+8 aarch64
 * VM: OpenJDK 64-Bit Server VM by Azul Systems, Inc.
 * macOS 14.4.1
 *
 * ```
 *
 * IntelliJ IDEA HTTP Client [how to use ](https://www.jetbrains.com/help/idea/http-client-cli.html)
 *
 * ```bash
 * Usage: ijhttp [-D] [--insecure] [--version] [-r[=<reportPath>]]...
 *               [--connect-timeout=<connectTimeout>] [-e=<environmentName>]
 *               [-L=<logLevel>] [-p=<privateEnvFile>] [--proxy=<proxyUrl>]
 *               [-t=<socketTimeout>] [-v=<publicEnvFile>] [-P=<String=String>]...
 *               [-V=<String=String>]... files...
 * IntelliJ IDEA HTTP Client
 *       files...             HTTP file paths
 *       --connect-timeout=<connectTimeout>
 *                            Number of milliseconds for connection
 *                              Default: 3000
 *   -D, --docker-mode        Enables Docker mode. Treat 'localhost' as 'host.
 *                              docker.internal'
 *                              Default: false
 *   -e, --env=<environmentName>
 *                            Name of the environment in config file
 *       --insecure           Allow insecure SSL connection
 *                              Default: false
 *   -L, --log-level=<logLevel>
 *                            Logging level: BASIC, HEADERS, VERBOSE
 *                              Default: BASIC
 *   -p, --private-env-file=<privateEnvFile>
 *                            Name of the private environment file
 *                            http-client.private.env.json
 *   -P, --private-env-variables=<String=String>
 *                            Private environment variables
 *       --proxy=<proxyUrl>   Proxy setting in format
 *                            'scheme://login:password@host:port',
 *                            'scheme' can be 'socks' for SOCKS or 'http' for HTTP
 *   -r, --report[=<reportPath>]
 *                            Creates report about execution in JUnit XML Format.
 *                            Without value puts the report in folder 'reports' in
 *                              the current directory.
 *                            With value puts the report in the specified
 *                              directory.
 *                            Disabled by default.
 *   -t, --socket-timeout=<socketTimeout>
 *                            Number of milliseconds for socket read
 *                              Default: 10000
 *   -v, --env-file=<publicEnvFile>
 *                            Name of the public environment file
 *                            http-client.env.json
 *   -V, --env-variables=<String=String>
 *                            Public environment variables
 *       --version            Prints version of HTTP Client and exits
 *
 *```
 * @constructor Create empty Ijhttp test
 */
@CacheableTask
open class IjhttpTask : Exec() {


    @ArtesanoDsl
    @Input
    var dockerMode : Boolean = false


    @ArtesanoDsl
    @Input
    var insecure : Boolean = false


    @Optional
    @ArtesanoDsl
    @Input
    var report : String? = null


    @ArtesanoDsl
    @Input
    var connectTimeout : Long = 3000


    @ArtesanoDsl
    @Input
    var environment : Environment = Environment.dev


    @ArtesanoDsl
    @Input
    var logLevel : LogLevel = LogLevel.BASIC


    @ArtesanoDsl
    @Input
    var privateEnvFile : String = "http-client.private.env.json"


    @Optional
    @ArtesanoDsl
    @Input
    var proxyUrl : String? = null


    @ArtesanoDsl
    @Input
    var socketTimeout : Long = 10_000


    @ArtesanoDsl
    @Input
    var publicEnvFile : String = "http-client.env.json"


    @ArtesanoDsl
    @Input
    var privateEnvVariables : Map<String , String> = emptyMap()


    @ArtesanoDsl
    @Input
    var envVariables : Map<String , String> = emptyMap()


    @ArtesanoDsl
    @InputFiles
    @PathSensitive(PathSensitivity.RELATIVE)
    var files : Set<String> = project.layout.projectDirectory.dir(".ijhttp")
        .asFile
        .walkTopDown()
        .filter { it.isFile && (it.extension == "rest" || it.extension == "http") }
        .map { it.path }
        .toSet()


    @TaskAction
    override fun exec() {

        executable = project.rootProject.layout.buildDirectory.dir("ijhttp/ijhttp/ijhttp").get().asFile.path

        workingDir(project.layout.projectDirectory.dir(".ijhttp"))
        setArgs(
            listOf<String>(
                *mapOf<Option , String?>(
                    Option.DockerMode to dockerMode.toString() ,
                    Option.Insecure to insecure.toString() ,
                    Option.Report to report ,
                    Option.ConnectTimeout to connectTimeout.toString() ,
                    Option.Environment to environment.name ,
                    Option.LogLevel to logLevel.name ,
                    Option.PrivateEnvFile to privateEnvFile ,
                    Option.ProxyUrl to proxyUrl ,
                    Option.SocketTimeout to socketTimeout.toString() ,
                    Option.PublicEnvFile to publicEnvFile ,
                ).map<Option , String? , String?> { (option , arg) ->

                    if (arg == null) {
                        // --proxy (default value)
                        null
                    } else if (arg.isBlank()) {
                        // --report (default value)
                        option.alt
                    } else {
                        "${option.alt}=$arg"
                    }

                }.filterNotNull<String>()
                    .toTypedArray<String>() ,

                *privateEnvVariables.flatMap { (key : String , value : String) ->
                    listOf(Option.PrivateEnvVariables.alt , "$key=$value")
                }.toTypedArray() ,

                *envVariables.flatMap { (key , value) ->
                    listOf(Option.EnvVariables.alt , "$key=$value")
                }.toTypedArray() ,

                *files.toTypedArray()
            )
        )
        super.exec()
    }


    enum class Environment {
        dev ,
        prod ,
        qa;
    }

    enum class LogLevel { BASIC ,
        HEADERS ,
        VERBOSE;
    }

    enum class Option(
        val option : String ,
        val alt : String ,
    ) {


        DockerMode("--docker-mode" , "-D") ,
        Insecure("--insecure" , "--insecure") ,
        Version("--version" , "--version") ,
        Report("--report" , "--report") ,
        ConnectTimeout("--connect-timeout" , "--connect-timeout") ,
        Environment("--env" , "-e") ,
        LogLevel("--log-level" , "-L") ,
        PrivateEnvFile("--private-env-file" , "-p") ,
        ProxyUrl("--proxy" , "--proxy") ,
        SocketTimeout("--socket-timeout" , "-t") ,
        PublicEnvFile("--env-file" , "-v") ,
        PrivateEnvVariables("--private-env-variables" , "-P") ,
        EnvVariables("--env-variables" , "-V");
    }
}
