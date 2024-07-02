package com.sim.mongodb.adapter

import com.sim.core.SubscribingPostPort
import com.sim.domain.post.Post
import com.sim.mongodb.subscribepost.SubscribingPostDocument
import com.sim.mongodb.subscribepost.SubscribingPostRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {  }

@Component
class SubscribingPostAdapter(
    private val subscribingPostRepository: SubscribingPostRepository
) : SubscribingPostPort {
    override fun addPostToFollowerInboxes(post: Post, followerUserIds: List<String>) {
        val postDocuments = followerUserIds.map {
            SubscribingPostDocument(
                post.id,
                it,
                post.createdAt
            )
        }
        subscribingPostRepository.saveAll(postDocuments)
    }

    override fun removePostFromFollowerInboxes(postId: String) {
        subscribingPostRepository.deleteAllByPostId(postId)
    }

    override fun listPostIdsByFollowerUserIdWithPagination(
        followerUserId: String,
        pageNumber: Int,
        size: Int
    ): List<String> {
        return subscribingPostRepository.findByFollowerUserIdWithPagination(followerUserId, pageNumber, size)
            .map { it.postId }
    }
}