package com.sim.couponusecase

interface CouponIssueHistoryUsecase {

    fun isFirstRequestFromUser(couponEventId: String, userId: String): Boolean

    fun hasRemainingCoupon(couponEventId: String): Boolean
}