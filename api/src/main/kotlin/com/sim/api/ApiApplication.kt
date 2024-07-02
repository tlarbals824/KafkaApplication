package com.sim.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.event.EventListener

@SpringBootApplication(scanBasePackages = ["com.sim"])
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}

//@Component
class BeanScanner(
    private val applicationContext: ApplicationContext
){

    @EventListener(ApplicationReadyEvent::class)
    fun scan(){
        applicationContext.beanDefinitionNames.forEach {
            println(it)
        }
    }

}
