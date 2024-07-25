package com.sim.redis.coupon

import com.sim.domain.coupon.CouponEvent
import com.sim.redis.coupon.model.CouponEventCache

object CouponCacheConverter {

    fun toModel(couponEventCache: CouponEventCache): CouponEvent {
        return CouponEvent(
            id = couponEventCache.id,
            displayName = couponEventCache.displayName,
            expiresAt = couponEventCache.expiresAt,
            issueLimit = couponEventCache.issueLimit
        )
    }

    fun toEntity(couponEvent: CouponEvent): CouponEventCache {
        return CouponEventCache(
            id = couponEvent.id,
            displayName = couponEvent.displayName,
            expiresAt = couponEvent.expiresAt,
            issueLimit = couponEvent.issueLimit
        )
    }
}