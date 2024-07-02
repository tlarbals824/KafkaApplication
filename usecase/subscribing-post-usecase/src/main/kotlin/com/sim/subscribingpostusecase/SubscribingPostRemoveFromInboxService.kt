package com.sim.subscribingpostusecase

import com.sim.core.SubscribingPostPort
import org.springframework.stereotype.Service

@Service
internal class SubscribingPostRemoveFromInboxService(
    private val subscribingPostPort: SubscribingPostPort
) : SubscribingPostRemoveFromInboxUsecase {
    override fun deleteSubscribingInboxPost(postId: String) {
        subscribingPostPort.removePostFromFollowerInboxes(postId)
    }
}