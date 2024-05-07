plugins {

}

rootProject.name = "artesano"

include(
    ":catalog" ,

    ":dsl" ,
    ":stencil" ,
    ":builder" ,

    ":plugin:settings" ,

    ":plugin:project:docker" ,
    ":plugin:project:openapi" ,
    ":plugin:task" ,

    )


//includeBuild("sample/dockercompose")



