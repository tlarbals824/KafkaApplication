package com.sim.postusecase

import com.sim.domain.post.ResolvedPost

interface PostReadUseCase {
    fun read(postId: String): ResolvedPost

}