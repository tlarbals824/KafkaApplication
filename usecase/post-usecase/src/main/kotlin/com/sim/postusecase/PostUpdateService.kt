package com.sim.postusecase

import com.sim.core.OriginalPostMessageProducePort
import com.sim.core.PostPort
import com.sim.domain.post.Post
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal class PostUpdateService(
    private val postPort: PostPort,
    private val postMessageProducer: OriginalPostMessageProducePort
) : PostUpdateUsecase {


    @Transactional
    override fun update(command: PostUpdateUsecase.Command): Post {
        postPort.findById(command.postId)?.let {
            val updatedPost = it.update(
                title = command.title,
                content = command.content,
                categoryId = command.categoryId
            )
            postMessageProducer.sendUpdateMessage(updatedPost)
            return postPort.save(updatedPost)
        }?: throw IllegalArgumentException("Post not found")
    }
}