subprojects{
    dependencies{
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        implementation(project(":usecase:core"))
        implementation(project(":domain"))
    }
}