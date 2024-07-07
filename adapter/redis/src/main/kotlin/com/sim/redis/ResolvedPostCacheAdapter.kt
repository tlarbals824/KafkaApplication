package com.sim.redis

import com.fasterxml.jackson.databind.ObjectMapper
import com.sim.core.ResolvedPostCachePort
import com.sim.domain.post.ResolvedPost
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class ResolvedPostCacheAdapter(
    private val redisTemplate: RedisTemplate<String, String>,
    private val objectMapper: ObjectMapper
) : ResolvedPostCachePort {
    override fun set(resolvedPost: ResolvedPost) {
        redisTemplate.opsForValue()
            .set(
                KeyGenerator.generateKey(Version.ResolvedPost.V1, resolvedPost.postId),
                objectMapper.writeValueAsString(resolvedPost),
                Duration.ofMinutes(EXPIRED_TIME)
            );
    }

    override fun get(postId: String): ResolvedPost? {
        return objectMapper.readValue(
            redisTemplate
                .opsForValue()
                .get(KeyGenerator.generateKey(Version.ResolvedPost.V1, postId)),
            ResolvedPost::class.java
        )
    }

    override fun getOrSet(postId: String, postSet: () -> ResolvedPost): ResolvedPost {
        return get(postId) ?: run {
            val resolvedPost = postSet()
            set(resolvedPost)
            return resolvedPost
        }
    }

    override fun delete(postId: String) {
        redisTemplate.delete(KeyGenerator.generateKey(Version.ResolvedPost.V1, postId))
    }

    override fun multiGet(postIds: List<String>): List<ResolvedPost> {
        val keys = postIds.map {
            KeyGenerator.generateKey(Version.ResolvedPost.V1, it)
        }
        return redisTemplate.opsForValue().multiGet(keys)?.mapNotNull {
            it?.let { objectMapper.readValue(it, ResolvedPost::class.java) }
        } ?: emptyList()
    }

    override fun multiSet(posts: List<ResolvedPost>) {
        posts.associateBy(
            { KeyGenerator.generateKey(Version.ResolvedPost.V1, it.postId) },
            { objectMapper.writeValueAsString(it) }
        ).let {
            redisTemplate.opsForValue().multiSet(it)
        }
    }

    override fun multiGetAndSetIfNotExist(
        postIds: List<String>,
        postQuery: (List<String>) -> List<ResolvedPost>
    ): List<ResolvedPost> {
        val resolvedPosts = multiGet(postIds)
        val nonCachedPosts = postQuery(
            postIds.filter { postId ->
                resolvedPosts.none { it.postId == postId }
            }
        ).apply { multiSet(this) }

        return (nonCachedPosts + resolvedPosts).sortedBy { it.postId } // post id order
    }


    companion object {
        private const val EXPIRED_TIME: Long = 60 * 60 * 2;
    }
}