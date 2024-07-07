package com.sim.postsearchusecase

import com.sim.domain.inspectedpost.InspectedPost

interface PostIndexingUsecase {

    fun save(post: InspectedPost)
    fun deleteById(postId: String)
}