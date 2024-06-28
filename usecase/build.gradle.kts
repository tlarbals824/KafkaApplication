subprojects{
    dependencies{
        implementation(project(":common"))
        implementation(project(":domain"))

        if(project != project(":usecase:core"))

        // spring tx not jpa
        implementation("org.springframework:spring-tx")
    }
}