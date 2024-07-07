package com.sim.postsearchusecase

import com.sim.core.PostSearchPort
import com.sim.domain.inspectedpost.InspectedPost
import org.springframework.stereotype.Service

@Service
internal class PostIndexingService(
    private val postSearchPort: PostSearchPort
) : PostIndexingUsecase {
    override fun save(post: InspectedPost) {
        postSearchPort.indexPost(post)
    }

    override fun deleteById(postId: String) {
        postSearchPort.deletePostById(postId)
    }
}