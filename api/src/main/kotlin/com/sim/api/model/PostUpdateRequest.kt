package com.sim.api.model

import com.sim.postusecase.PostUpdateUsecase

data class PostUpdateRequest(
    val title: String,
    val content: String,
    val categoryId: String
) {
    fun toCommand(postId: String): PostUpdateUsecase.Command{
        return PostUpdateUsecase.Command(
            postId=postId,
            title = this.title,
            content = this.content,
            categoryId = this.categoryId
        )
    }
}