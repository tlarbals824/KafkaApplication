package com.sim.kafkaapplication.producer

import com.sim.kafkaapplication.model.MyMessage
import com.sim.kafkaapplication.model.Topic
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaJsonTopicProducer(
    private val kafkaTemplate: KafkaTemplate<String, MyMessage>,
) {

    fun sendMessage(message: MyMessage) {
        kafkaTemplate.send(Topic.MY_JSON_TOPIC, message.age.toString() ,message)
    }
}