package com.sim.contentsubscribingworker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.sim"])
class ContentSubscribingWorkerApplication

fun main(args: Array<String>) {
    runApplication<ContentSubscribingWorkerApplication>(*args)
}
