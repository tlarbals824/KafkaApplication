package com.sim.postresolvinghelpusecase

import com.sim.core.MetadataPort
import com.sim.core.PostPort
import com.sim.domain.post.ResolvedPost
import org.springframework.stereotype.Service

@Service
internal class PostResolvingHelpService(
    private val metadataPort: MetadataPort,
    private val postPort: PostPort
) : PostResolvingHelpUsecase {
    override fun resolvePostById(id: String): ResolvedPost {
        postPort.findById(id)?.let {
            val username = metadataPort.getUserNameByUserId(it.userId)
            val categoryName = metadataPort.getCategoryNameByCategoryId(it.categoryId)
            return ResolvedPost(it, username, categoryName)
        }?: throw IllegalArgumentException("Post not found")
    }

    override fun resolvePostsByIds(ids: List<String>): List<ResolvedPost> {
        return ids.map { resolvePostById(it) }
    }
}