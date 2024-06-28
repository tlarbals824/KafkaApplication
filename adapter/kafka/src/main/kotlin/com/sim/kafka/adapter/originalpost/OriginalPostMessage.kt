package com.sim.kafka.adapter.originalpost

import com.sim.domain.post.Post
import com.sim.kafka.common.OperationType
import java.time.LocalDateTime

data class OriginalPostMessage(
    val id: String,
    val operationType: OperationType,
    val payload: Payload
) {

    data class Payload(
        val id: String,
        val title: String,
        val content: String,
        val userId: String,
        val categoryId: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime,
        val deletedAt: LocalDateTime?,
    ){
        companion object{
            fun from(post: Post): Payload{
                return Payload(
                    id = post.id,
                    title = post.title,
                    content = post.content,
                    userId = post.userId,
                    categoryId = post.categoryId,
                    createdAt = post.createdAt,
                    updatedAt = post.updatedAt,
                    deletedAt = post.deletedAt
                )
            }
        }
    }

    companion object{
        fun from(post: Post, operationType: OperationType): OriginalPostMessage{
            return OriginalPostMessage(
                id = post.id,
                operationType = operationType,
                payload = Payload.from(post)
            )
        }
    }
}
