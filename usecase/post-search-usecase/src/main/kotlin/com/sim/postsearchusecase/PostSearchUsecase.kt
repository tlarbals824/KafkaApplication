package com.sim.postsearchusecase

import com.sim.domain.post.ResolvedPost

interface PostSearchUsecase {

    fun searchPostsByKeyword(keyword: String, pageNumber: Int): List<ResolvedPost>
}