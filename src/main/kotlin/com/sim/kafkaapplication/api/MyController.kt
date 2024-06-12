package com.sim.kafkaapplication.api

import com.sim.kafkaapplication.model.MyMessage
import com.sim.kafkaapplication.producer.KafkaMyProducer
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MyController(
//    private val cloudStreamMyProducer: CloudStreamMyProducer
    private val kafkaMyProducer: KafkaMyProducer
) {

    @PostMapping("/message")
    fun message(
        @RequestBody message: MyMessage
    ){
        kafkaMyProducer.sendMessage(message)
    }
}