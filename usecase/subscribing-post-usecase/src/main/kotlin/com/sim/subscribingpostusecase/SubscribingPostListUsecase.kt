package com.sim.subscribingpostusecase

import com.sim.domain.post.ResolvedPost

interface SubscribingPostListUsecase {

    fun listSubscribingInboxPosts(request: Request): List<ResolvedPost>

    data class Request(
        val pageNumber: Int,
        val followerUserId: String
    )
}