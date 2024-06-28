package com.sim.kafka.common

import com.sim.domain.post.Post
import com.sim.kafka.adapter.originalpost.OriginalPostMessage

object MessageConverter {

    fun toModel(originalPostMessage: OriginalPostMessage): Post {
        val payload = originalPostMessage.payload
        return Post(
            id = payload.id,
            title = payload.title,
            content = payload.content,
            userId = payload.userId,
            categoryId = payload.categoryId,
            createdAt = payload.createdAt,
            updatedAt = payload.updatedAt,
            deletedAt = payload.deletedAt
        )
    }
}