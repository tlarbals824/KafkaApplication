package com.sim.postusecase

import com.sim.domain.post.Post

interface PostUpdateUsecase {
    fun update(command: Command): Post

    data class Command(
        val postId: String,
        val title: String,
        val content: String,
        val categoryId: String
    )
}