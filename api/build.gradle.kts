dependencies{
    implementation(project(":usecase:post-usecase"))
    implementation(project(":usecase:coupon-usecase"))
    implementation(project(":usecase:post-resolving-help-usecase"))
    implementation(project(":usecase:inspected-post-usecase"))
    implementation(project(":usecase:subscribing-post-usecase"))
    implementation(project(":usecase:post-search-usecase"))

    implementation(project(":domain"))

    implementation(project(":adapter:metadata-client"))
    implementation(project(":adapter:mysql"))
    implementation(project(":adapter:kafka"))
    implementation(project(":adapter:chat-gpt-client"))
    implementation(project(":adapter:mongodb"))
    implementation(project(":adapter:redis"))
    implementation(project(":adapter:elasticsearch"))

    // swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    // web
    implementation("org.springframework.boot:spring-boot-starter-web")
}