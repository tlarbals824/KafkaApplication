package com.sim.inspectedpostusecase

import com.sim.domain.inspectedpost.InspectedPost
import com.sim.domain.post.Post

interface PostInspectUsecase {
    fun inspectAndGetIfValid(post: Post): InspectedPost?
}