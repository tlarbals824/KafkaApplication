package com.sim.couponusecase

import com.sim.core.CouponPort
import com.sim.domain.coupon.ResolvedCoupon
import org.springframework.stereotype.Service

@Service
internal class ListUsableCouponService(
    private val couponPort: CouponPort
) : ListUsableCouponUsecase {
    override fun listByUserId(userId: String): List<ResolvedCoupon> {
        return couponPort.listByUserId(userId).filter { it.isUsable() }
    }
}