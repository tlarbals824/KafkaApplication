package com.sim.subscribingpostusecase

import com.sim.core.SubscribingPostPort
import com.sim.domain.post.ResolvedPost
import com.sim.postresolvinghelpusecase.PostResolvingHelpUsecase
import org.springframework.stereotype.Service

@Service
internal class SubscribingPostListService(
    private val subscribingPostPort: SubscribingPostPort,
    private val resolvingHelpUsecase: PostResolvingHelpUsecase
) : SubscribingPostListUsecase {
    override fun listSubscribingInboxPosts(request: SubscribingPostListUsecase.Request): List<ResolvedPost> {
        val subscribingPostIds = subscribingPostPort.listPostIdsByFollowerUserIdWithPagination(
            followerUserId = request.followerUserId,
            pageNumber = request.pageNumber,
            PAGE_SIZE
        )
        return resolvingHelpUsecase.resolvePostsByIds(subscribingPostIds)
    }

    companion object {
        private const val PAGE_SIZE = 10
    }
}