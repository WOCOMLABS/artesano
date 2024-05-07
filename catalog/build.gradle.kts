catalog {
    versionCatalog {
        from(files("${rootDir}/gradle/libs.versions.toml"))
        version("artesano", properties["VERSION_NAME"] as String)
    }
}
