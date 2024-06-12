package com.sim.kafkaapplication.consumer

import com.sim.kafkaapplication.model.MyMessage
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.Message
import org.springframework.stereotype.Component

@Component
class KafkaMyConsumer {


    @KafkaListener(
        topics = ["my-json-topic"],
        groupId = "test-consumer-group",
    )
    fun apply(message: ConsumerRecord<String, MyMessage>) {
        println("Received message: ${message.value()}")
    }
}