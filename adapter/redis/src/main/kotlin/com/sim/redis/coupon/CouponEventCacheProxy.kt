package com.sim.redis.coupon

import com.fasterxml.jackson.databind.ObjectMapper
import com.sim.core.CouponEventPort
import com.sim.domain.coupon.CouponEvent
import com.sim.redis.KeyGenerator
import com.sim.redis.Version
import com.sim.redis.coupon.model.CouponEventCache
import org.springframework.data.redis.core.RedisTemplate

class CouponEventCacheProxy(
    private val targetInstance: CouponEventPort,
    private val redisTemplate: RedisTemplate<String, String>,
    private val objectMapper: ObjectMapper
) : CouponEventPort {
    override fun findByIdOrNull(id: String): CouponEvent? {
        return getCache(id) ?: run {
            targetInstance.findByIdOrNull(id)?.let {
                setCache(CouponCacheConverter.toEntity(it))
                return@let it
            }
        }
    }

    private fun getCache(id: String): CouponEvent? {
        return redisTemplate.opsForValue()
            .get(KeyGenerator.generateKey(Version.Coupon.COUPON_EVENT_CACHE_KEY, id))?.let {
                val cache = objectMapper.readValue(
                    it,
                    CouponEventCache::class.java
                )
                CouponCacheConverter.toModel(cache)
            }
    }

    private fun setCache(couponEventCache: CouponEventCache) {
        redisTemplate.opsForValue().set(
            KeyGenerator.generateKey(Version.Coupon.COUPON_EVENT_CACHE_KEY, couponEventCache.id),
            objectMapper.writeValueAsString(couponEventCache)
        )
    }


}