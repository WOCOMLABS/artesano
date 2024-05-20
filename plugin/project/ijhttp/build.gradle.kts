dependencies {
    implementation(project(":kmp:annotation"))
    implementation(libs.gradle.kotlin.dsl.plugins)
    implementation(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    val WEBSITE : String by project
    val VCS_URL : String by project

    website = WEBSITE
    vcsUrl = VCS_URL

    plugins {
        create("ijhttpArtesano") {
            id = "$group.gradle.project.ijhttp-artesano-plugin"
            implementationClass = "$group.gradle.project.ArtesanoPluginIjhttp"
            displayName = "Ijhttp Artesano Plugin"
            description = """
            Ijhttp Artesano Plugin
            
            wrapper for ijhttp the http-cli from jetbrains 
            
            see https://www.jetbrains.com/help/idea/http-client-cli.html
            
        """.trimIndent()
            tags = listOf("artesano" , "ijhttp",)
        }

    }
}
