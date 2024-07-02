package com.sim.subscribingpostusecase

import com.sim.domain.post.Post

interface SubscribingPostAddToInboxUsecase {

    fun saveSubscribingInboxPost(post: Post)
}