package com.sim.kafkaapplication.api

import com.sim.kafkaapplication.model.MyMessage
import com.sim.kafkaapplication.producer.KafkaJsonTopicProducer
import com.sim.kafkaapplication.producer.KafkaSecondTopicProducer
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MyController(
//    private val cloudStreamMyProducer: CloudStreamMyProducer
    private val kafkaJsonTopicProducer: KafkaJsonTopicProducer,
    private val kafkaSecondTopicProducer: KafkaSecondTopicProducer
) {

    @PostMapping("/message")
    fun message(
        @RequestBody message: MyMessage
    ){
        kafkaJsonTopicProducer.sendMessage(message)
    }


    @PostMapping("/second-message/{key}")
    fun secondMessage(
        @RequestBody message: String,
        @PathVariable key: String
    ){
        kafkaSecondTopicProducer.sendMessage(key, message)
    }
}