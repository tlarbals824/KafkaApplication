package com.sim.postusecase

import com.sim.domain.post.Post

interface PostCreateUsecase {
    fun create(command: Command): Post

    data class Command(
        val userId: String,
        val title: String,
        val content: String,
        val categoryId: String
    )
}