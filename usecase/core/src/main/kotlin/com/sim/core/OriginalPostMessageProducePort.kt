package com.sim.core

import com.sim.domain.post.Post

interface OriginalPostMessageProducePort {

    fun sendCreateMessage(post: Post)
    fun sendUpdateMessage(post: Post)
    fun sendDeleteMessage(post: Post)
}