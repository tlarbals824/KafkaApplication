package com.sim.api.model

data class PostListResponse(
    val postId: String,
    val title: String,
    val userName: String,
    val createdAt: String
) {
}