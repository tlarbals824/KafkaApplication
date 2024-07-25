package com.sim.couponusecase

import com.sim.core.CouponEventPort
import com.sim.core.CouponIssueRequestHistoryPort
import org.springframework.stereotype.Service

@Service
internal class CouponIssueHistoryService(
    private val couponIssueRequestHistoryPort: CouponIssueRequestHistoryPort,
    private val couponEventPort: CouponEventPort
) : CouponIssueHistoryUsecase {
    override fun isFirstRequestFromUser(couponEventId: String, userId: String): Boolean {
        return couponIssueRequestHistoryPort.persistHistoryIfNotExists(couponEventId, userId)
    }

    override fun hasRemainingCoupon(couponEventId: String): Boolean {
        val couponEvent = couponEventPort.findByIdOrNull(couponEventId)
        return couponEvent?.let {
            return couponIssueRequestHistoryPort.retrieveRequestSequentialNumber(couponEventId) <= it.issueLimit
        }?: false
    }
}