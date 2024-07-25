package com.sim.redis.coupon

import com.sim.core.CouponIssueRequestHistoryPort
import com.sim.redis.KeyGenerator
import com.sim.redis.Version
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.concurrent.TimeUnit

@Component
class CouponIssueRequestHistoryAdapter(
    private val redisTemplate: RedisTemplate<String, String>
) : CouponIssueRequestHistoryPort {
    override fun persistHistoryIfNotExists(couponEventId: String, userId: String): Boolean {
        return redisTemplate.opsForValue().setIfAbsent(
            KeyGenerator.generateKey(Version.Coupon.USER_REQUEST_HISTORY_KEY_PREFIX, "$couponEventId:$userId"),
            "1",
            EXPIRE_SECONDS,
            TimeUnit.SECONDS
        ) ?: true
    }

    override fun retrieveRequestSequentialNumber(couponEventId: String): Long {
        val key = KeyGenerator.generateKey(Version.Coupon.REQUEST_COUNT_HISTORY_KEY_PREFIX, couponEventId)
        return redisTemplate.opsForValue().increment(key)?.let {
            if (it == 1L) {
                redisTemplate.expire(key, Duration.ofSeconds(EXPIRE_SECONDS))
            }
            it
        } ?: Long.MAX_VALUE
    }

    private companion object {
        const val EXPIRE_SECONDS: Long = 60 * 60 * 24 * 7;
    }


}