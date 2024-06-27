package com.sim.api.model

import com.sim.postusecase.PostCreateUsecase

data class PostCreateRequest(
    val title: String,
    val content: String,
    val userId: String,
    val categoryId: String
) {

    fun toCommand(): PostCreateUsecase.Command{
        return PostCreateUsecase.Command(
            title = this.title,
            content = this.content,
            userId = this.userId,
            categoryId = this.categoryId
        )
    }
}