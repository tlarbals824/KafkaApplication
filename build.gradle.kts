import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "3.3.0" apply false
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}


allprojects {
    apply {
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("kotlin")
        plugin("kotlin-spring")

        plugin("org.jetbrains.kotlin.plugin.spring")
    }

    val springCloudVersion by extra("2023.0.2")
    val springModulithVersion by extra("1.2.1")



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
        implementation("org.springframework.modulith:spring-modulith-starter-core")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testImplementation("org.springframework.modulith:spring-modulith-starter-test")

        testRuntimeOnly("org.junit.platform:junit-platform-launcher")

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
            mavenBom("org.springframework.modulith:spring-modulith-bom:$springModulithVersion")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}



