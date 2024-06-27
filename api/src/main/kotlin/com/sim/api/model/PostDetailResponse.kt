package com.sim.api.model

import com.sim.domain.post.ResolvedPost
import java.time.LocalDateTime

data class PostDetailResponse(
    val postId: String,
    val title: String,
    val content: String,
    val userName: String,
    val categoryName: String,
    val createdAt: LocalDateTime,
    val updated: Boolean
) {
    companion object{
        fun from(resolvedPost: ResolvedPost): PostDetailResponse {
            return PostDetailResponse(
                postId = resolvedPost.postId,
                title = resolvedPost.title,
                content = resolvedPost.content,
                userName = resolvedPost.username,
                categoryName = resolvedPost.categoryName,
                createdAt = resolvedPost.createdAt,
                updated = resolvedPost.updated
            )
        }
    }
}