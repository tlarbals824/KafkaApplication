package com.sim.core

import com.sim.domain.coupon.Coupon
import com.sim.domain.coupon.ResolvedCoupon

interface CouponPort {

    fun save(coupon: Coupon): Coupon

    fun listByUserId(userId: String): List<ResolvedCoupon>
}