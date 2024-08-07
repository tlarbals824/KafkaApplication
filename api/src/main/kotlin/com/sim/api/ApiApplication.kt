package com.sim.api

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.sim"])
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}

private val logger = KotlinLogging.logger {}
