package com.sim.kafkaapplication.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.sim.kafkaapplication.model.MyMessage
import com.sim.kafkaapplication.model.Topic
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class KafkaBatchJsonTopicConsumer(
    private val objectMapper: ObjectMapper
) {

     @KafkaListener(
         topics = [Topic.MY_JSON_TOPIC],
         groupId = "batch-test-consumer-group",
         containerFactory = "batchKafkaListenerContainerFactory"
     )
     fun apply(messages: List<ConsumerRecord<String, String>>, acknowledgment: Acknowledgment) {
         println("[Batch Consumer]Received ${messages.size} messages")
         messages.forEach{
             val message = objectMapper.readValue(it.value(), MyMessage::class.java)
             println("[Batch Consumer]Received message: $message, raw message: ${it.value()}")
         }
         acknowledgment.acknowledge()
     }
}