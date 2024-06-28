package com.sim.kafka.adapter.inspectedpost

import com.fasterxml.jackson.databind.ObjectMapper
import com.sim.core.InspectedPostMessageProducePort
import com.sim.domain.inspectedpost.InspectedPost
import com.sim.kafka.common.OperationType
import com.sim.kafka.common.Topic
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class InspectedPostMessageProduceAdapter(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) : InspectedPostMessageProducePort {

    override fun sendCreateMessage(inspectedPost: InspectedPost) {
        sendMessage {
            InspectedPostMessage.from(inspectedPost, OperationType.CREATE)
        }
    }

    override fun sendUpdateMessage(inspectedPost: InspectedPost) {
        sendMessage {
            InspectedPostMessage.from(inspectedPost, OperationType.UPDATE)
        }
    }

    override fun sendDeleteMessage(id: String) {
        sendMessage {
            InspectedPostMessage.from(id, OperationType.DELETE)
        }
    }

    private fun sendMessage(generateMessage: () -> InspectedPostMessage) {
        val message = generateMessage()
        kafkaTemplate.send(Topic.POST_INSPECTED_TOPIC, message.id, objectMapper.writeValueAsString(message))
    }
}