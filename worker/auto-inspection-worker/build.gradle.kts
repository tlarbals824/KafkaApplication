dependencies{
    implementation(project(":usecase:inspected-post-usecase"))
    implementation(project(":usecase:core"))

    implementation(project(":adapter:kafka"))
    implementation(project(":adapter:metadata-client"))
    implementation(project(":adapter:chat-gpt-client"))

    implementation("org.springframework.kafka:spring-kafka")
    testImplementation("org.springframework.kafka:spring-kafka-test")
}