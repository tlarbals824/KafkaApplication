package com.sim.contentsubscribingworker.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.sim.kafka.adapter.originalpost.OriginalPostMessage
import com.sim.kafka.common.MessageConverter
import com.sim.kafka.common.OperationType
import com.sim.kafka.common.Topic
import com.sim.subscribingpostusecase.SubscribingPostAddToInboxUsecase
import com.sim.subscribingpostusecase.SubscribingPostRemoveFromInboxUsecase
import io.github.oshai.kotlinlogging.KotlinLogging
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

private val logger= KotlinLogging.logger {  }

@Component
class ContentSubscribingWorker(
    private val objectMapper: ObjectMapper,
    private val subscribingPostAddToInboxUsecase: SubscribingPostAddToInboxUsecase,
    private val subscribingPostRemoveFromInboxUsecase: SubscribingPostRemoveFromInboxUsecase
) {

    @KafkaListener(
        topics = [Topic.POST_ORIGINAL_TOPIC],
        groupId = "content-subscribing-consumer-group",
        concurrency = "3"
    )
    fun listenOriginalPostMessage(message: ConsumerRecord<String, String>, acknowledgment: Acknowledgment){
        val originalMessage = objectMapper.readValue(message.value(), OriginalPostMessage::class.java)
        val post = MessageConverter.toModel(originalMessage)
        when(originalMessage.operationType){
            OperationType.CREATE-> subscribingPostAddToInboxUsecase.saveSubscribingInboxPost(post)
            OperationType.DELETE -> subscribingPostRemoveFromInboxUsecase.deleteSubscribingInboxPost(postId = post.id)
            else -> {}
        }
    }



}