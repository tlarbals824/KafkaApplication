package com.sim.core

import com.sim.domain.post.Post

interface PostPort {
    fun save(post: Post): Post

    fun findById(id: String): Post?

    fun findAllByIds(ids: List<String>): List<Post>
}