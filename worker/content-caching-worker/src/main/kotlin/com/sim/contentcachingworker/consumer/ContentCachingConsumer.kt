package com.sim.contentcachingworker.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.sim.kafka.adapter.originalpost.OriginalPostMessage
import com.sim.kafka.common.MessageConverter
import com.sim.kafka.common.OperationType
import com.sim.kafka.common.Topic
import com.sim.postresolvinghelpusecase.PostResolvingHelpUsecase
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ContentCachingConsumer(
    private val objectMapper: ObjectMapper,
    private val postResolvingHelpUsecase: PostResolvingHelpUsecase
) {

    @KafkaListener(
        topics = [Topic.POST_ORIGINAL_TOPIC],
        groupId = "content-caching-consumer-group",
    )
    fun listen(message: ConsumerRecord<String, String>){
        val originPost = objectMapper.readValue(message.value(), OriginalPostMessage::class.java)
        when(originPost.operationType){
            OperationType.CREATE, OperationType.UPDATE -> postResolvingHelpUsecase.resolvePostAndSave(MessageConverter.toModel(originPost))
            OperationType.DELETE -> postResolvingHelpUsecase.deleteResolvedPostById(originPost.id)
        }
    }
}