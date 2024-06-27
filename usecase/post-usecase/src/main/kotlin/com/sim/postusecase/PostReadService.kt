package com.sim.postusecase

import com.sim.domain.post.Post
import com.sim.domain.post.ResolvedPost
import org.springframework.stereotype.Service

@Service
internal class PostReadService : PostReadUseCase {
    override fun read(postId: String): ResolvedPost {
        return ResolvedPost(
            Post(
                title = "test",
                content = "test",
                userId =  "test",
                categoryId = " "
            ),
            "test",
            "test"
        )
    }
}