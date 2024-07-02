package com.sim.subscribingpostusecase

import com.sim.core.MetadataPort
import com.sim.core.SubscribingPostPort
import com.sim.domain.post.Post
import org.springframework.stereotype.Service

@Service
internal class SubscribingPostAddToInboxService(
    private val subscribingPostPort: SubscribingPostPort,
    private val metadataPort: MetadataPort
) : SubscribingPostAddToInboxUsecase {
    override fun saveSubscribingInboxPost(post: Post) {
        val followersUserId = metadataPort.listFollowerIdsByUserId(post.userId)
        subscribingPostPort.addPostToFollowerInboxes(post, followersUserId)
    }
}