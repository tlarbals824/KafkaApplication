package com.sim.redis

import com.sim.core.ResolvedPostCachePort
import com.sim.domain.post.ResolvedPost
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class ResolvedPostCacheAdapter(
    private val redisTemplate: RedisTemplate<String, ResolvedPost>
) : ResolvedPostCachePort {
    override fun set(resolvedPost: ResolvedPost) {
        redisTemplate.opsForValue()
            .set(
                KeyGenerator.generateKey(Version.ResolvedPost.V1, resolvedPost.postId),
                resolvedPost,
                Duration.ofMinutes(EXPIRED_TIME)
            );
    }

    override fun get(postId: String): ResolvedPost? {
        return redisTemplate
            .opsForValue()
            .get(KeyGenerator.generateKey(Version.ResolvedPost.V1, postId))
    }

    override fun getOrSet(postId: String, postSet: () -> ResolvedPost): ResolvedPost {
        return get(postId) ?: run {
            val resolvedPost = postSet()
            set(resolvedPost)
            return resolvedPost
        }
    }

    companion object {
        private const val EXPIRED_TIME: Long = 60 * 60 * 2;
    }
}