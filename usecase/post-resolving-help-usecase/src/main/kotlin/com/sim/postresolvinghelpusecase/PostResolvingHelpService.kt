package com.sim.postresolvinghelpusecase

import com.sim.core.MetadataPort
import com.sim.core.PostPort
import com.sim.core.ResolvedPostCachePort
import com.sim.domain.post.Post
import com.sim.domain.post.ResolvedPost
import org.springframework.stereotype.Service

@Service
internal class PostResolvingHelpService(
    private val metadataPort: MetadataPort,
    private val postPort: PostPort,
    private val resolvedPostCachePort: ResolvedPostCachePort
) : PostResolvingHelpUsecase {
    override fun resolvePostById(id: String): ResolvedPost {
        return resolvedPostCachePort.getOrSet(id) {
            resolvePost(id)
        }
    }

    override fun resolvePostsByIds(ids: List<String>): List<ResolvedPost> {
        return resolvedPostCachePort.multiGetAndSetIfNotExist(ids) { postIds ->
            postPort.findAllByIds(postIds).map { convertToResolvedPost(it) }
        }
    }

    override fun deleteResolvedPostById(id: String) {
        resolvedPostCachePort.delete(id)
    }

    override fun resolvePostAndSave(post: Post) {
        val resolvedPost = resolvePost(post.id)
        resolvedPostCachePort.set(resolvedPost)
    }

    private fun resolvePost(postId: String): ResolvedPost {
        return postPort.findById(postId)?.let {
            convertToResolvedPost(it)
        } ?: throw IllegalArgumentException("Post not found")
    }

    private fun convertToResolvedPost(post: Post): ResolvedPost {
        val username = metadataPort.getUserNameByUserId(post.userId)
        val categoryName = metadataPort.getCategoryNameByCategoryId(post.categoryId)
        return ResolvedPost(post, username, categoryName)
    }
}