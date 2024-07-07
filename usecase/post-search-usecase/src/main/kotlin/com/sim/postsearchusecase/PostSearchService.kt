package com.sim.postsearchusecase

import com.sim.core.PostSearchPort
import com.sim.domain.post.ResolvedPost
import com.sim.postresolvinghelpusecase.PostResolvingHelpUsecase
import org.springframework.stereotype.Service

@Service
class PostSearchService(
    private val postSearchPort: PostSearchPort,
    private val postResolvingHelpUsecase: PostResolvingHelpUsecase
) : PostSearchUsecase {
    override fun searchPostsByKeyword(keyword: String, pageNumber: Int): List<ResolvedPost> {
        val postIds = postSearchPort.searchPostIdsByKeyword(keyword, pageNumber, PAGE_SIZE)
        return postResolvingHelpUsecase.resolvePostsByIds(postIds)
    }

    companion object{
        private const val PAGE_SIZE = 10
    }
}