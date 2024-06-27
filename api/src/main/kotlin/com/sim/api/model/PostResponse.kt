package com.sim.api.model

import java.time.LocalDateTime

data class PostResponse(
    val postId: String,
    val title: String,
    val content: String,
    val userId: String,
    val categoryId: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val deletedAt: LocalDateTime?,
) {
    companion object{
        fun from(post: com.sim.domain.post.Post): PostResponse {
            return PostResponse(
                postId = post.id,
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

