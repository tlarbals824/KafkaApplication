import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "3.3.0" apply false
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
    kotlin("plugin.jpa") version "1.9.24"
}


allprojects {
    apply {
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("kotlin")
        plugin("kotlin-spring")
        plugin("kotlin-jpa")


        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.jetbrains.kotlin.plugin.jpa")
    }

    val springCloudVersion by extra("2023.0.2")



    group = "com.sim"
    version = "0.0.1-SNAPSHOT"

    tasks.withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    tasks.withType(Jar::class) {
        enabled = true
    }
    tasks.withType(BootJar::class) {
        if (this.project.name == "api") {
            enabled = true
        } else {
            enabled = false
        }
    }


    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.jetbrains.kotlin:kotlin-reflect")


        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testImplementation("org.springframework.cloud:spring-cloud-stream-test-binder")
        testImplementation("io.projectreactor:reactor-test")

        testRuntimeOnly("org.junit.platform:junit-platform-launcher")



        // spring cloud
//    implementation("org.springframework.cloud:spring-cloud-stream")
//    implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka")

        // kafka
        implementation("org.springframework.kafka:spring-kafka")


        // jackson
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        // configuration processor
        implementation("org.springframework.boot:spring-boot-configuration-processor")

        // Kotlin logger
        implementation("io.github.oshai:kotlin-logging-jvm:5.1.0")
    }



    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}


