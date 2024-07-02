dependencies{
    implementation(project(":usecase:core"))
    implementation(project(":usecase:subscribing-post-usecase"))

    implementation(project(":adapter:kafka"))
    implementation(project(":adapter:mysql"))
    implementation(project(":adapter:mongodb"))
    implementation(project(":adapter:metadata-client"))

    implementation("org.springframework.kafka:spring-kafka")
    testImplementation("org.springframework.kafka:spring-kafka-test")
}