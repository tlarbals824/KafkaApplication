package com.sim.postusecase

import com.sim.core.OriginalPostMessageProducePort
import com.sim.core.PostPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal class PostDeleteService(
    private val postPort: PostPort,
    private val postMessageProducer: OriginalPostMessageProducePort
) : PostDeleteUsecase {

    @Transactional
    override fun delete(command: PostDeleteUsecase.Command) {
        postPort.findById(command.postId)?.let {
            val deletedPost = it.delete()
            postMessageProducer.sendDeleteMessage(deletedPost)
            postPort.save(deletedPost)
        }
    }
}