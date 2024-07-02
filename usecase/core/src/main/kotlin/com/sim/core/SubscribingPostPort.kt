package com.sim.core

import com.sim.domain.post.Post

interface SubscribingPostPort {

    fun addPostToFollowerInboxes(post: Post, followerUserIds: List<String>)

    fun removePostFromFollowerInboxes(postId: String)

    fun listPostIdsByFollowerUserIdWithPagination(followerUserId: String, pageNumber: Int, size: Int): List<String>
}