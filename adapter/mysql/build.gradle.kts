//plugins {
//    kotlin("plugin.jpa") version "1.9.24"
//}
//
apply{
    plugin("kotlin-jpa")
    plugin("org.jetbrains.kotlin.plugin.jpa")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.mysql:mysql-connector-j")

    implementation(project(":domain"))
}
