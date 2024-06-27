package com.sim.postusecase

import com.sim.core.PostPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal class PostDeleteService(
    private val postPort: PostPort
) : PostDeleteUsecase {

    @Transactional
    override fun delete(command: PostDeleteUsecase.Command) {
        postPort.findById(command.postId)?.let {
            it.delete()
            postPort.save(it)
        }
    }
}