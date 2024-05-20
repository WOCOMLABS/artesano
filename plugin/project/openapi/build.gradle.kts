dependencies {
    implementation(project(":kmp:stencil"))

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

        create("openApiArtesano") {
            id = "$group.gradle.project.open-api-artesano-plugin"
            implementationClass = "$group.gradle.project.OpenApiArtesanoPlugin"
            displayName = " Open API Artesano"
            description = """
            Open API Artesano Plugin

            • Creates a gradle project scaffold from a openapi.yaml definition under de hood uses org.openapitools:openapi-generator-gradle-plugin
            • The generated project can be a server or a kotlin multiplatform client that uses ktor
            • because this is not a official surreal db library the resulting project still requires some manual updates
        """.trimIndent()
            tags = listOf("open api" , "docker compose", "surrealdb")
        }

    }
}
