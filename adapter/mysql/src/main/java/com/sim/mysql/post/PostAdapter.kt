package com.sim.mysql.post

import com.sim.core.PostPort
import com.sim.domain.post.Post
import com.sim.mysql.post.PostEntityConverter.toEntity
import com.sim.mysql.post.PostEntityConverter.toModel
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

    override fun findAllByIds(ids: List<String>): List<Post> {
        return postJpaRepository.findAllById(ids).map { toModel(it) }
    }
}
