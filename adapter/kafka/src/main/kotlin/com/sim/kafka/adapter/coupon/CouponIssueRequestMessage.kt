package com.sim.kafka.adapter.coupon

data class CouponIssueRequestMessage(
    val couponEventId: String,
    val userId: String
) {
}