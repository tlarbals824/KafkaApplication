package com.sim.mysql.coupon

import com.sim.domain.coupon.Coupon
import com.sim.domain.coupon.CouponEvent
import com.sim.domain.coupon.ResolvedCoupon

object CouponEntityConverter {

    fun toEntity(couponEvent: CouponEvent): CouponEventEntity{
        return CouponEventEntity(
            couponEvent.id,
            couponEvent.displayName,
            couponEvent.expiresAt,
            couponEvent.issueLimit
        )
    }

    fun toModel(couponEventEntity: CouponEventEntity): CouponEvent{
        return CouponEvent(
            id = couponEventEntity.id,
            displayName =  couponEventEntity.displayName,
            expiresAt = couponEventEntity.expiresAt,
            issueLimit = couponEventEntity.issueLimit
        )
    }

    fun toEntity(coupon: Coupon): CouponEntity{
        return CouponEntity(
            coupon.id,
            coupon.couponEventId,
            coupon.userId,
            coupon.issuedAt,
            coupon.usedAt
        )
    }

    fun toModel(couponEntity: CouponEntity): Coupon{
        return Coupon(
            id = couponEntity.id,
            couponEventId = couponEntity.couponEventId,
            userId = couponEntity.userId,
            issuedAt = couponEntity.issuedAt,
            usedAt = couponEntity.usedAt
        )
    }

    fun toResolveCoupon(couponEntity: CouponEntity): ResolvedCoupon {
        return ResolvedCoupon(
            coupon = toModel(couponEntity),
            couponEvent = toModel(couponEntity.couponEvent)
        )
    }
}