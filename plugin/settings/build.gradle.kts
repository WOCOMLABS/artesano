dependencies {
    implementation(libs.kotlin.gradle.plugin)

    implementation(project(":kmp:stencil"))
    implementation(project(":kmp:annotation"))
}

gradlePlugin {
    val WEBSITE : String by project
    val VCS_URL : String by project

    website = WEBSITE
    vcsUrl = VCS_URL

    plugins {

        create("settingsArtesano") {
            id = "$group.gradle.settings.settings-artesano-plugin"
            implementationClass = "$group.gradle.settings.SettingsArtesanoPlugin"
            displayName = "Settings Artesano"
            description = """
            Settings Artesano Plugin
            • creates a dsl called artesano that defines 4 different types of subprojects , in essence is a project generator.
            
        """.trimIndent()
            tags = listOf("open api", "docker compose", "surrealdb", "project template")
        }
    }
}
