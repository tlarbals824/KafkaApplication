package com.sim.couponusecase

import com.sim.core.CouponIssueRequestPort
import org.springframework.stereotype.Service

@Service
internal class RequestCouponIssueService(
    private val couponIssueRequestPort: CouponIssueRequestPort
) : RequestCouponIssueUsecase {
    override fun queue(couponEventId: String, userId: String) {
        couponIssueRequestPort.sendMessage(couponEventId, userId)
    }
}