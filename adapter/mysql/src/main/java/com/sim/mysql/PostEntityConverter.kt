package com.sim.mysql

import com.sim.domain.post.Post


object PostEntityConverter {
    @JvmStatic
    fun toEntity(post: Post): PostEntity {
        return PostEntity(
            post.id,
            post.title,
            post.content,
            post.userId,
            post.categoryId,
            post.createdAt,
            post.updatedAt,
            post.deletedAt
        )
    }

    @JvmStatic
    fun toModel(entity: PostEntity): Post {
        return Post(
            entity.id,
            entity.title,
            entity.content,
            entity.userId,
            entity.categoryId,
            entity.createdAt,
            entity.updatedAt,
            entity.deletedAt
        )
    }
}