package com.sim.autoinspectionworker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.sim"])
class AutoInspectionWorkerApplication {
}

fun main(args: Array<String>) {
    runApplication<AutoInspectionWorkerApplication>(*args)
}