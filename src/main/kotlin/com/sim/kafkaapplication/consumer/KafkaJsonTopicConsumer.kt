package com.sim.kafkaapplication.consumer

import com.sim.kafkaapplication.model.MyMessage
import com.sim.kafkaapplication.model.Topic
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaJsonTopicConsumer {


    @KafkaListener(
        topics = [Topic.MY_JSON_TOPIC],
        groupId = "test-consumer-group",
    )
    fun apply(message: ConsumerRecord<String, MyMessage>) {
        println("Received message: ${message.value()}")
    }
}