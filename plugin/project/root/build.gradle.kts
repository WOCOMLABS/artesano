dependencies {
    implementation(libs.gradle.kotlin.dsl.plugins)
    implementation(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    val WEBSITE : String by project
    val VCS_URL : String by project

    website = WEBSITE
    vcsUrl = VCS_URL

    plugins {
        create("rootArtesano") {
            id = "$group.gradle.project.root-artesano-plugin"
            implementationClass = "$group.gradle.project.ArtesanoPluginRoot"
            displayName = "Root Artesano Plugin"
            description = """
            Root Artesano Plugin
           
            should be applied to the Artesano multi-build root project
        """.trimIndent()
            tags = listOf("docker compose", "surrealdb", "nexus", "ijhttp")
        }

    }
}
