package com.sim.api.model

import java.time.LocalDateTime

data class UsableCouponDetailResponse(
    val id: String,
    val name: String,
    val expiresAt: LocalDateTime
) {
}