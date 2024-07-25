package com.sim.domain.coupon

import com.sim.common.util.ModelIdGenerator
import java.time.LocalDateTime

class Coupon(
    val id: String = ModelIdGenerator.generateId(),
    val couponEventId: String,
    val userId: String,
    val issuedAt: LocalDateTime = LocalDateTime.now(),
    usedAt: LocalDateTime? = null
) {

    var usedAt: LocalDateTime? = usedAt
        private set

    fun use() {
        validateCouponUsable()
        usedAt = LocalDateTime.now()
    }

    fun isUsable(): Boolean {
        return usedAt == null;
    }

    private fun validateCouponUsable() {
        require(usedAt != null) {
            "Coupon is already used"
        }
    }
}