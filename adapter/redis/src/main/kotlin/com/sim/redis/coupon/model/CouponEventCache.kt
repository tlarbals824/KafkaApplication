package com.sim.redis.coupon.model

import java.time.LocalDateTime

data class CouponEventCache(
    val id: String,
    val displayName: String,
    val expiresAt: LocalDateTime,
    val issueLimit: Long
)
