package com.sim.postresolvinghelpusecase

import com.sim.domain.post.Post
import com.sim.domain.post.ResolvedPost

interface PostResolvingHelpUsecase {

    fun resolvePostById(id: String): ResolvedPost

    fun resolvePostsByIds(ids: List<String>) : List<ResolvedPost>

    fun deleteResolvedPostById(id: String)

    fun resolvePostAndSave(post: Post)
}