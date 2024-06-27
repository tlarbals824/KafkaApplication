dependencies{
    implementation(project(":usecase:post-usecase"))
    implementation(project(":usecase:post-resolving-help-usecase"))


    implementation(project(":domain"))


    implementation(project(":adapter:metadata-client"))
    implementation(project(":adapter:mysql"))

    // swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    // web
    implementation("org.springframework.boot:spring-boot-starter-web")
}