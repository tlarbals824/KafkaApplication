package com.sim.domain.coupon

import com.sim.common.util.ModelIdGenerator
import java.time.LocalDateTime

class CouponEvent(
    val id: String = ModelIdGenerator.generateId(),
    val displayName: String,
    val expiresAt: LocalDateTime,
    val issueLimit: Long,
) {

    fun isExpired(): Boolean {
        return LocalDateTime.now().isAfter(expiresAt)
    }


}