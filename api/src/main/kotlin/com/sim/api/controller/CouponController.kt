package com.sim.api.controller

import com.sim.api.model.CouponIssueRequest
import com.sim.api.model.UsableCouponDetailResponse
import com.sim.couponusecase.CouponIssueHistoryUsecase
import com.sim.couponusecase.ListUsableCouponUsecase
import com.sim.couponusecase.RequestCouponIssueUsecase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/coupons")
class CouponController(
    private val couponIssueHistoryUsecase: CouponIssueHistoryUsecase,
    private val requestCouponIssueUsecase: RequestCouponIssueUsecase,
    private val listUsableCouponUsecase: ListUsableCouponUsecase
) {


    @PostMapping
    fun issueCoupon(
        @RequestBody request: CouponIssueRequest
    ): ResponseEntity<String> {
        if (couponIssueHistoryUsecase.isFirstRequestFromUser(request.couponEventId, request.userId).not()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("already issued coupon")
        }
        if (couponIssueHistoryUsecase.hasRemainingCoupon(request.couponEventId).not()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("not enough available coupons")
        }
        requestCouponIssueUsecase.queue(request.couponEventId, request.userId)

        return ResponseEntity.ok("success to issue coupon")
    }

    @GetMapping("/users/{userId}")
    fun listByUserId(
        @PathVariable userId: String
    ): List<UsableCouponDetailResponse> {
        return listUsableCouponUsecase.listByUserId(userId).map {
            UsableCouponDetailResponse(
                it.coupon.id,
                it.couponEvent.displayName,
                it.couponEvent.expiresAt
            )
        }
    }

}