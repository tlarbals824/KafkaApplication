package com.sim.core

interface CouponIssueRequestPort {

    fun sendMessage(couponEventId: String, userId: String)
}