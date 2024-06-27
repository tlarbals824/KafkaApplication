package com.sim.domain.post

import com.sim.common.util.ModelIdGenerator
import java.time.LocalDateTime


data class Post(
    val id: String = ModelIdGenerator.generateId(),
    val title: String,
    val content: String,
    val userId: String,
    val categoryId: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = createdAt,
    val deletedAt: LocalDateTime? = null
) {

    fun update(title: String, content: String, categoryId: String): Post {
        return this.copy(
            id=this.id,
            title = title,
            content = content,
            userId = this.userId,
            categoryId = categoryId,
            createdAt = this.createdAt,
            updatedAt = LocalDateTime.now()
        )
    }

    fun delete(): Post {
        return this.copy(
            id=this.id,
            title = this.title,
            content = this.content,
            userId = this.userId,
            categoryId = this.categoryId,
            createdAt = this.createdAt,
            updatedAt = LocalDateTime.now(),
            deletedAt = LocalDateTime.now()
        )
    }

    fun unDelete(): Post{
        return this.copy(
            id=this.id,
            title = this.title,
            content = this.content,
            userId = this.userId,
            categoryId = this.categoryId,
            createdAt = this.createdAt,
            updatedAt = LocalDateTime.now(),
            deletedAt = null
        )
    }
}