package com.sim.kafkaapplication.producer

import com.sim.kafkaapplication.model.MyMessage
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaMyProducer(
    private val kafkaTemplate: KafkaTemplate<String, MyMessage>,
) {

    fun sendMessage(message: MyMessage) {
        kafkaTemplate.send("my-json-topic", message.age.toString() ,message)
    }
}