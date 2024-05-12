plugins {

}

rootProject.name = "artesano"

include(
    ":catalog" ,

    ":dsl" ,
    ":stencil" ,
    ":builder" ,

    ":plugin:settings" ,

    ":plugin:project:root" ,
    ":plugin:project:docker" ,
    ":plugin:project:openapi" ,
    ":plugin:project:ijhttp" ,

    )


//includeBuild("sample/dockercompose")



