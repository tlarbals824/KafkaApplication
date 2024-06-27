subprojects{
    dependencies{
        implementation(project(":common"))
        implementation(project(":domain"))

        // spring tx not jpa
        implementation("org.springframework:spring-tx")
    }
}