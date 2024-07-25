package com.sim.couponusecase

import com.sim.domain.coupon.ResolvedCoupon

interface ListUsableCouponUsecase {
    fun listByUserId(userId: String): List<ResolvedCoupon>
}