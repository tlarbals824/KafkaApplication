package com.sim.domain.post

import java.time.LocalDateTime

class ResolvedPost private constructor(
    val postId: String,
    val title: String,
    val content: String,
    val userId: String,
    val username: String,
    val categoryId: String,
    val categoryName: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val updated: Boolean = false,
) { // post + 메타정보

    constructor(
        post: Post,
        username: String,
        categoryName: String,
    ):this(
        postId = post.id,
        title = post.title,
        content = post.content,
        userId = post.userId,
        username = username,
        categoryId = post.categoryId,
        categoryName = categoryName,
        createdAt = post.createdAt,
        updatedAt = post.updatedAt,
        updated = post.updatedAt != post.createdAt
    )
}