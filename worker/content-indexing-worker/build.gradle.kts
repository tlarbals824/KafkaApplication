dependencies{
    implementation(project(":usecase:core"))
    implementation(project(":usecase:post-search-usecase"))

    implementation(project(":adapter:elasticsearch"))
    implementation(project(":adapter:metadata-client"))
    implementation(project(":adapter:mysql"))
    implementation(project(":adapter:redis"))


}