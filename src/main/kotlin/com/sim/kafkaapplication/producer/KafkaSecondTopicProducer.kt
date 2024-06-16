package com.sim.kafkaapplication.producer

import com.sim.kafkaapplication.model.MyMessage
import com.sim.kafkaapplication.model.Topic
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

//@Component
class KafkaSecondTopicProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
) {
    fun sendMessage(key: String, message: String) {
        kafkaTemplate.send(Topic.MY_SECOND_TOPIC, key,message)
    }
}