dependencies{
    implementation(project(":usecase:core"))
    implementation(project(":usecase:post-resolving-help-usecase"))

    implementation(project(":adapter:redis"))
    implementation(project(":adapter:mysql"))
    implementation(project(":adapter:metadata-client"))


}