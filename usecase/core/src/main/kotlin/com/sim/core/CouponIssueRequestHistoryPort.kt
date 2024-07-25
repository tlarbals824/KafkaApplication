package com.sim.core

interface CouponIssueRequestHistoryPort {

    fun persistHistoryIfNotExists(couponEventId: String, userId: String): Boolean

    fun retrieveRequestSequentialNumber(couponEventId: String): Long
}