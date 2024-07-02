package com.sim.api.model

import java.time.LocalDateTime

data class PostListResponse(
    val postId: String,
    val title: String,
    val userName: String,
    val createdAt: LocalDateTime
) {
}