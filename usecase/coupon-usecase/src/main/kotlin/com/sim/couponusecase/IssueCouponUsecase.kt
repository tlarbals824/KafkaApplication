package com.sim.couponusecase

import com.sim.domain.coupon.Coupon

interface IssueCouponUsecase {

    fun save(couponEventId: String, userId: String): Coupon
}