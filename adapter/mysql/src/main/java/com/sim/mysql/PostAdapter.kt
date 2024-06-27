package com.sim.mysql

import com.sim.core.PostPort
import com.sim.domain.post.Post
import com.sim.mysql.PostEntityConverter.toEntity
import com.sim.mysql.PostEntityConverter.toModel
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class PostAdapter(private val postJpaRepository: PostJpaRepository) : PostPort {
    override fun save(post: Post): Post {
        val postEntity = toEntity(post)
        val savedPostEntity = postJpaRepository.save(postEntity)
        return toModel(savedPostEntity)
    }

    override fun findById(id: String): Post? {
        return postJpaRepository.findByIdOrNull(id)?.let { toModel(it) }
    }
}
