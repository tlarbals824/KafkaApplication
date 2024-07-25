package com.sim.core

import com.sim.domain.coupon.CouponEvent

interface CouponEventPort {

    fun findByIdOrNull(id: String): CouponEvent?
}