package com.sim.contentindexingworker.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.sim.kafka.adapter.inspectedpost.InspectedPostMessage
import com.sim.kafka.common.MessageConverter
import com.sim.kafka.common.OperationType
import com.sim.kafka.common.Topic
import com.sim.postsearchusecase.PostIndexingUsecase
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ContentIndexingWorker(
    private val postIndexingUsecase: PostIndexingUsecase,
    private val objectMapper: ObjectMapper
) {

    @KafkaListener(
        topics = [Topic.POST_INSPECTED_TOPIC],
        groupId = "content-indexing-consumer-group",
        concurrency = "3"
    )
    fun listen(message: ConsumerRecord<String,String>){
        val inspectedMessage = objectMapper.readValue(message.value(), InspectedPostMessage::class.java)
        when(inspectedMessage.operationType){
            OperationType.CREATE, OperationType.UPDATE -> postIndexingUsecase.save(MessageConverter.toModel(inspectedMessage))
            OperationType.DELETE -> postIndexingUsecase.deleteById(inspectedMessage.id)
        }
    }
}