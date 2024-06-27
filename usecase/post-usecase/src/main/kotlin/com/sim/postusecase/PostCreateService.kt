package com.sim.postusecase

import com.sim.core.PostPort
import com.sim.domain.post.Post
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal class PostCreateService(
    private val postPort: PostPort
) : PostCreateUsecase {

    @Transactional
    override fun create(command: PostCreateUsecase.Command): Post {
        val post = Post(
            title = command.title,
            content = command.content,
            userId = command.userId,
            categoryId = command.categoryId
        )
        return postPort.save(post)
    }
}