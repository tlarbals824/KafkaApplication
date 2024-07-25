package com.sim.couponusecase

import com.sim.core.CouponPort
import com.sim.domain.coupon.Coupon
import org.springframework.stereotype.Service

@Service
internal class IssueCouponService(
    private val couponPort: CouponPort
) : IssueCouponUsecase {
    override fun save(couponEventId: String, userId: String): Coupon {
        return couponPort.save(
            Coupon(
                couponEventId = couponEventId,
                userId = userId
            )
        )
    }
}