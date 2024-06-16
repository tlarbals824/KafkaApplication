package com.sim.kafkaapplication.consumer

import com.sim.kafkaapplication.model.MyMessage
import com.sim.kafkaapplication.model.Topic
import io.github.oshai.kotlinlogging.KotlinLogging
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

private val logger = KotlinLogging.logger {}

@Component
class KafkaJsonTopicConsumer {

    private val idHistoryMap = ConcurrentHashMap<String, Int>()

    @KafkaListener(
        topics = [Topic.MY_JSON_TOPIC],
        groupId = "test-consumer-group",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun apply(message: ConsumerRecord<String, MyMessage>, acknowledgment: Acknowledgment) {
        printPayloadIfFirstMessage(message.value())
        acknowledgment.acknowledge()
    }


    @Synchronized
    private fun printPayloadIfFirstMessage(message: MyMessage){
        idHistoryMap[message.id.toString()]?.let {
            logger.info { "[Single Consumer] Duplicated" }
        }?: run {
            logger.info { "[Single Consumer] Received message: $message" }
            idHistoryMap.put(message.id.toString(), 1)
        }
    }
}