package com.sim.core

import com.sim.domain.post.ResolvedPost

interface ResolvedPostCachePort {

    fun set(resolvedPost: ResolvedPost)

    fun get(postId: String): ResolvedPost?

    fun getOrSet(postId: String, postSet: () -> ResolvedPost): ResolvedPost

    fun delete(postId: String)

    fun multiGet(postIds: List<String>): List<ResolvedPost>

    fun multiSet(posts: List<ResolvedPost>)

    fun multiGetAndSetIfNotExist(
        postIds: List<String>,
        postQuery: (List<String>) -> List<ResolvedPost>
    ): List<ResolvedPost>
}