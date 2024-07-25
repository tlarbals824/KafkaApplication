package com.sim.domain.coupon

class ResolvedCoupon(
    val coupon: Coupon,
    val couponEvent: CouponEvent
) {

    fun isUsable(): Boolean{
        return this.coupon.isUsable() and this.couponEvent.isExpired().not()
    }
}