package com.sim.autoinspectionworker.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.sim.core.InspectedPostMessageProducePort
import com.sim.inspectedpostusecase.PostInspectUsecase
import com.sim.kafka.adapter.originalpost.OriginalPostMessage
import com.sim.kafka.common.MessageConverter
import com.sim.kafka.common.OperationType
import com.sim.kafka.common.Topic
import io.github.oshai.kotlinlogging.KotlinLogging
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger { }

@Component
class AutoInspectionWorker(
    private val objectMapper: ObjectMapper,
    private val postInspectUsecase: PostInspectUsecase,
    private val inspectedPostMessageProducePort: InspectedPostMessageProducePort
) {


    @KafkaListener(
        topics = [Topic.POST_ORIGINAL_TOPIC],
        groupId = "auto-inspection-consumer-group",
        concurrency = "3"
    )
    fun listen(message: ConsumerRecord<String, String>) {
        val originalMessage = objectMapper.readValue(message.value(), OriginalPostMessage::class.java)
        postInspectUsecase.inspectAndGetIfValid(
            MessageConverter.toModel(originalMessage)
        )?.let {
            when (originalMessage.operationType) {
                OperationType.CREATE -> inspectedPostMessageProducePort.sendCreateMessage(it)
                OperationType.UPDATE -> inspectedPostMessageProducePort.sendUpdateMessage(it)
                OperationType.DELETE -> inspectedPostMessageProducePort.sendDeleteMessage(it.post.id)
            }
        }
    }
}