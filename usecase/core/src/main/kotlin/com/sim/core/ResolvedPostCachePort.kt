package com.sim.core

import com.sim.domain.post.ResolvedPost

interface ResolvedPostCachePort {

    fun set(resolvedPost: ResolvedPost)

    fun get(postId: String) : ResolvedPost?

    fun getOrSet(postId: String, postSet: () -> ResolvedPost): ResolvedPost
}