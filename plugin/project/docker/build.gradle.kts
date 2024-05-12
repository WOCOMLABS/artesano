dependencies {
    implementation(project(":stencil"))
    implementation(project(":builder"))

    implementation(libs.openapi.generator.gradle.plugin)
    implementation(libs.gradle.kotlin.dsl.plugins)
    implementation(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    val WEBSITE : String by project
    val VCS_URL : String by project

    website = WEBSITE
    vcsUrl = VCS_URL

    plugins {
        create("dockerArtesano") {
            id = "$group.gradle.project.docker-artesano-plugin"
            implementationClass = "$group.gradle.project.DockerArtesanoPlugin"
            displayName = " Docker Artesano"
            description = """
            Docker Artesano Plugin
           
            • adds dockerComposeUp and dockerComposeDown tasks for the defined docker-compose at the root of the project
        """.trimIndent()
            tags = listOf("docker compose", "surrealdb", "nexus")
        }

    }
}
