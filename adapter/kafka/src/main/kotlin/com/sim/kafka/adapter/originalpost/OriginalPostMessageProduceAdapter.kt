package com.sim.kafka.adapter.originalpost

import com.fasterxml.jackson.databind.ObjectMapper
import com.sim.core.OriginalPostMessageProducePort
import com.sim.domain.post.Post
import com.sim.kafka.common.OperationType
import com.sim.kafka.common.Topic
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class OriginalPostMessageProduceAdapter(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
): OriginalPostMessageProducePort {
    override fun sendCreateMessage(post: Post) {
        val message = OriginalPostMessage.from(post, OperationType.CREATE)
        kafkaTemplate.send(Topic.POST_ORIGINAL_TOPIC, message.id, objectMapper.writeValueAsString(message))
    }

    override fun sendUpdateMessage(post: Post) {
        val message = OriginalPostMessage.from(post, OperationType.UPDATE)
        kafkaTemplate.send(Topic.POST_ORIGINAL_TOPIC, message.id, objectMapper.writeValueAsString(message))
    }

    override fun sendDeleteMessage(post: Post) {
        val message = OriginalPostMessage.from(post, OperationType.DELETE)
        kafkaTemplate.send(Topic.POST_ORIGINAL_TOPIC, message.id, objectMapper.writeValueAsString(message))
    }
}