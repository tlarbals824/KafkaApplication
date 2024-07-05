package com.sim.postusecase

import com.sim.domain.post.ResolvedPost
import com.sim.postresolvinghelpusecase.PostResolvingHelpUsecase
import org.springframework.stereotype.Service

@Service
internal class PostReadService(
    private val resolvingHelpUsecase: PostResolvingHelpUsecase
) : PostReadUseCase {
    override fun read(postId: String): ResolvedPost {
        return resolvingHelpUsecase.resolvePostById(postId)
    }
}