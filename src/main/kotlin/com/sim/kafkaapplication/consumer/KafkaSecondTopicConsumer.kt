package com.sim.kafkaapplication.consumer

import com.sim.kafkaapplication.model.Topic
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaSecondTopicConsumer{

    @KafkaListener(
        topics = [Topic.MY_SECOND_TOPIC],
        groupId = "test-consumer-group",
        containerFactory = "secondKafkaListenerContainerFactory"
    )
    fun apply(message: ConsumerRecord<String, String>) {
        println("[${Topic.MY_SECOND_TOPIC}] Received message: ${message.value()}")
        println("[${Topic.MY_SECOND_TOPIC}] Offset: ${message.offset()}, Partition: ${message.partition()}")
    }
}