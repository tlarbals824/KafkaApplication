package com.sim.kafkaapplication.consumer

import com.sim.kafkaapplication.model.MyMessage
import com.sim.kafkaapplication.model.Topic
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaBatchJsonTopicConsumer {

     @KafkaListener(
         topics = [Topic.MY_JSON_TOPIC],
         groupId = "batch-test-consumer-group",
         containerFactory = "batchKafkaListenerContainerFactory"
     )
     fun apply(messages: List<ConsumerRecord<String, MyMessage>>) {
         messages.forEach { println("[Batch Consumer]Received message: ${it.value()}") }
     }
}