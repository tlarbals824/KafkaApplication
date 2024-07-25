package com.sim.couponusecase

interface RequestCouponIssueUsecase {

    fun queue(couponEventId: String, userId: String)
}