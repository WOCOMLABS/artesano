pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        mavenLocal()
    }
}

rootProject.name = "artesano"

include(
    ":catalog" ,

    ":kmp:annotation" ,
    ":kmp:stencil" ,

    ":plugin:settings" ,

    ":plugin:project:root" ,
    ":plugin:project:docker" ,
    ":plugin:project:openapi" ,
    ":plugin:project:ijhttp" ,

    )


//includeBuild("build-logic")



