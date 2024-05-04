rootProject.name = "artesano"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
    }
}

include(
    ":dsl" ,
    ":stencil" ,
    ":builder" ,

    ":plugin:settings" ,

    ":plugin:project:docker" ,
    ":plugin:project:openapi" ,
    ":plugin:task" ,


)

