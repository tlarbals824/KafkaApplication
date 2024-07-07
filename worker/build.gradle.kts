subprojects{
    dependencies{
        implementation(project(":common"))
        implementation(project(":domain"))

        implementation(project(":adapter:kafka"))

        implementation("org.springframework.kafka:spring-kafka")
        testImplementation("org.springframework.kafka:spring-kafka-test")
    }
}